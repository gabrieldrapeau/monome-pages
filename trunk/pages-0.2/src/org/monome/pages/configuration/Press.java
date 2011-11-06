package org.monome.pages.configuration;

public class Press {

	private int x = -1;
	private int y = -1;
	private int value = -1;
	private int position = -1;
	private int patternNum = 0;
	private int pageNum = -1;
	
	public Press(int position, int x, int y, int value, int patternNum, int pageNum) {
		this.position = position;
		this.x = x;
		this.y = y;
		this.value = value;
		this.patternNum = patternNum;
	}
	
	public int[] getPress() {
		int[] press = {this.x, this.y, this.value};
		return press;
	}

	public int getPosition() {
		return this.position;
	}
	
	public int getPatternNum() {
		return patternNum;
	}
	
	public int getPageNum() {
		return pageNum;
	}
	
}
