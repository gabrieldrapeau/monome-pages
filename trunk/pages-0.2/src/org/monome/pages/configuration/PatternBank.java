package org.monome.pages.configuration;

import java.io.Serializable;
import java.util.ArrayList;

public class PatternBank implements Serializable {
	
	ArrayList<Pattern> patterns = new ArrayList<Pattern>();
	private int[] patternState;
	private int[] patternPosition;
	public static final int PATTERN_STATE_EMPTY = 0;
	public static final int PATTERN_STATE_RECORDED = 1;
	public static final int PATTERN_STATE_TRIGGERED = 2;
	private int numPatterns;
	private int patternLength = 96 * 4;
	private int quantify = 6;
	private int curPattern = 0;
	private int[] recordPosition;
	
	public PatternBank(int numPatterns) {
		this.numPatterns = numPatterns;
		for (int i=0; i < numPatterns; i++) {
			patterns.add(i, new Pattern());
		}
		this.patternState = new int[numPatterns];
		this.patternPosition = new int[numPatterns];
		this.recordPosition = new int[numPatterns];
	}

	public void handlePress(int patternNum) {
		/*
		for (int i=0; i < this.numPatterns; i++) {
			if (i == patternNum) {
				continue;
			}
			if (this.patternState[i] == PATTERN_STATE_TRIGGERED) {
				this.patternState[i] = PATTERN_STATE_RECORDED;
			}
		}
		*/
		curPattern = patternNum;
		if (this.patternState[patternNum] == PATTERN_STATE_EMPTY) {
			this.patternState[patternNum] = PATTERN_STATE_TRIGGERED;
		} else if (this.patternState[patternNum] == PATTERN_STATE_TRIGGERED) {
			this.patternState[patternNum] = PATTERN_STATE_EMPTY;
			this.patterns.get(patternNum).clearPattern();
		} else if (this.patternState[patternNum] == PATTERN_STATE_RECORDED) {
			this.patternState[patternNum] = PATTERN_STATE_TRIGGERED;
		}	
	}
	
	public void recordPress(int x, int y, int value) {
		if (this.patternState[curPattern] == PATTERN_STATE_TRIGGERED) {
			Pattern pattern = this.patterns.get(curPattern);
			pattern.recordPress(this.recordPosition[curPattern], x, y, value);
		}
	}
	
	public ArrayList<Press> getRecordedPresses() {
		ArrayList<Press> recordedPresses = new ArrayList<Press>();
		for (int i=0; i < this.numPatterns; i++) {
			if (this.patternState[i] == PATTERN_STATE_TRIGGERED) {
				Pattern pattern = this.patterns.get(i);
				ArrayList<Press> patternPresses = pattern.getRecordedPress(this.patternPosition[i]);
				if (patternPresses != null) {
					for (int j = 0; j < patternPresses.size(); j++) {
						recordedPresses.add(patternPresses.get(j));
					}
				}
			}
		}
		return recordedPresses;
	}
	
	public void handleTick() {
		for (int i=0; i < this.numPatterns; i++) {
			this.patternPosition[i]++;
			if ((this.patternPosition[i] % quantify) == (this.quantify / 2)) {
				this.recordPosition[i] += quantify;
			}
			if (this.patternPosition[i] == this.patternLength) {
				this.patternPosition[i] = 0;
			}
			if (this.recordPosition[i] >= this.patternLength) {
				this.recordPosition[i] = 0;
			}
		}
	}
	
	public int getPatternState(int patternNum) {
		return patternState[patternNum];
	}

	public void setQuantization(int i) {
		this.quantify = i;
	}
	
	public int getQuantization() {
		return this.quantify;
	}
	
	public void setPatternLength(int bars) {
		for (int i=0; i < this.numPatterns; i++) {
			this.patternPosition[i] = 0;
			this.recordPosition[i] = 0;
		}
		this.patternLength = 96 * bars;
	}
	
	public int getPatternLength() {
		return this.patternLength / 96;
	}
	
	public void handleReset() {
		for (int i=0; i < this.numPatterns; i++) {
			this.patternPosition[i] = 0;
			this.recordPosition[i] = 0;
		}
	}

}
