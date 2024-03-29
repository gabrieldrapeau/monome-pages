package org.monome.pages;

import javax.sound.midi.Receiver;
import javax.sound.midi.ShortMessage;

public class NoteEvent implements Runnable {
	
	private Receiver recv;
	private ShortMessage msg;
	private int delayTime;

	public NoteEvent(Receiver recv, ShortMessage msg, int delayTime) {
		this.recv = recv;
		this.msg = msg;
		this.delayTime = delayTime;
	}
	
	public void run() {
		try {
			Thread.sleep(this.delayTime);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		this.recv.send(msg, -1);
	}
}
