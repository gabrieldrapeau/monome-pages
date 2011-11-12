package org.monome.pages.configuration;

import java.io.Serializable;

public class MIDIPageChangeRule implements Serializable {
    static final long serialVersionUID = 42L;
	
	private int note;
	private int channel;
	private int pageIndex;
	private String linkedSerial;
	private int linkedPageIndex;
	
	public MIDIPageChangeRule(int note, int channel, int pageIndex) {
		this.note = note;
		this.channel = channel;
		this.pageIndex = pageIndex;
	}
	
	public boolean checkRule(int note, int channel) {
		if (this.note == note && this.channel == channel) {
			return true;
		} else {
			return false;
		}
	}
	
	public int getNote() {
		return note;
	}
	
	public int getChannel() {
		return channel;
	}

	public int getPageIndex() {
		return pageIndex;
	}
	
	public String getLinkedSerial() {
	    return linkedSerial;
	}
	
	public void setLinkedSerial(String serial) {
	    linkedSerial = serial;
	}
	
	public int getLinkedPageIndex() {
	    return linkedPageIndex;
	}
	
	public void setLinkedPageIndex(int newPageIndex) {
	    linkedPageIndex = newPageIndex; 
	}

}
