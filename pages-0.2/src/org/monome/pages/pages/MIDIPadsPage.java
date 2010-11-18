package org.monome.pages.pages;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiMessage;
import javax.sound.midi.Receiver;
import javax.sound.midi.ShortMessage;
import javax.swing.JPanel;

import org.monome.pages.configuration.MonomeConfiguration;
import org.monome.pages.pages.gui.MIDIPadsGUI;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class MIDIPadsPage implements Page {

	/**
	 * The MonomeConfiguration that this page belongs to
	 */
	MonomeConfiguration monome;

	/**
	 * The index of this page (the page number) 
	 */
	int index;
	
	/**
	 * The MIDI Pads Page GUI
	 */
	MIDIPadsGUI gui;
	
	/**
	 * The friendly name of the page
	 */
	private String pageName = "MIDI Pads";
	
	int[][] velocities = new int[16][16];
	MIDINoteDelay[][] midiNoteDelays = new MIDINoteDelay[16][16];
	int midiStartNote;
	int velocityFactor;
	int delayTime;
	int midiChannel;
	
	public MIDIPadsPage(MonomeConfiguration monome, int index) {
		this.index = index;
		this.monome = monome;
		this.gui = new MIDIPadsGUI(this);
		setMidiStartNote(31);
		setVelocityFactor(32);
		setDelayTime(5);
		setMidiChannel(0);
	}
	
	public void setMidiStartNote(int midiStartNote) {
		this.midiStartNote = midiStartNote;
		this.gui.setMidiStartNote(midiStartNote);
	}
	
	public void setDelayTime(int delayTime) {
		this.delayTime = delayTime;
		this.gui.setDelayTime(delayTime);
	}
	
	public void setMidiChannel(int midiChannel) {
		this.midiChannel = midiChannel;
		this.gui.setMidiChannel(midiChannel + 1);
	}
	
	public void setVelocityFactor(int velocityFactor) {
		this.velocityFactor = velocityFactor;
		this.gui.setVelocityFactor(velocityFactor);
	}

	public void configure(Element pageElement) {
		NodeList nameNL = pageElement.getElementsByTagName("pageName");
		Element el = (Element) nameNL.item(0);
		if (el != null) {
			NodeList nl = el.getChildNodes();
			String	name = ((Node) nl.item(0)).getNodeValue();
			this.setName(name);			
		}
		
		NodeList msNL = pageElement.getElementsByTagName("midiStartNote");
		el = (Element) msNL.item(0);
		if (el != null) {
			NodeList nl = el.getChildNodes();
			String sMidiStartNote = ((Node) nl.item(0)).getNodeValue();
			int midiStartNote = Integer.parseInt(sMidiStartNote);
			this.setMidiStartNote(midiStartNote);
		}
		
		NodeList vfNL = pageElement.getElementsByTagName("velocityFactor");
		el = (Element) vfNL.item(0);
		if (el != null) {
			NodeList nl = el.getChildNodes();
			String sVelocityFactor = ((Node) nl.item(0)).getNodeValue();
			int velocityFactor = Integer.parseInt(sVelocityFactor);
			this.setVelocityFactor(velocityFactor);
		}
		
		NodeList delayNL = pageElement.getElementsByTagName("delayTime");
		el = (Element) delayNL.item(0);
		if (el != null) {
			NodeList nl = el.getChildNodes();
			String sDelayTime = ((Node) nl.item(0)).getNodeValue();
			int delayTime = Integer.parseInt(sDelayTime);
			this.setDelayTime(delayTime);
		}
		
		NodeList mcNL = pageElement.getElementsByTagName("midiChannel");
		el = (Element) mcNL.item(0);
		if (el != null) {
			NodeList nl = el.getChildNodes();
			String sMidiChannel = ((Node) nl.item(0)).getNodeValue();
			int midiChannel = Integer.parseInt(sMidiChannel);
			this.setMidiChannel(midiChannel);
		}
	}
	
	public void destroyPage() {
		// TODO Auto-generated method stub

	}

	public boolean getCacheDisabled() {
		// TODO Auto-generated method stub
		return false;
	}

	public int getIndex() {
		// TODO Auto-generated method stub
		return index;
	}

	public String getName() {
		// TODO Auto-generated method stub
		return pageName;
	}

	public JPanel getPanel() {
		// TODO Auto-generated method stub
		return gui;
	}

	public void handleADC(int adcNum, float value) {
		// TODO Auto-generated method stub

	}

	public void handleADC(float x, float y) {
		// TODO Auto-generated method stub

	}

	public void handlePress(int x, int y, int value) {
		this.monome.led(x, y, value, index);
		int velX = (int) Math.floor((double) x / 2.0);
		int velY = (int) Math.floor((double) y / 2.0);
		int midiNote = midiStartNote + velX + (velY * (this.monome.sizeY));
		
		if (value == 1) {
			velocities[velX][velY] += velocityFactor;
			if (midiNoteDelays[velX][velY] == null || midiNoteDelays[velX][velY].isComplete()) {
				midiNoteDelays[velX][velY] = new MIDINoteDelay(monome, index, delayTime, midiNote, midiChannel);
				new Thread(midiNoteDelays[velX][velY]).start();
			}
			midiNoteDelays[velX][velY].setVelocity(velocities[velX][velY]);
		} else {
			velocities[velX][velY] -= velocityFactor;
			if (velocities[velX][velY] == 0) {
				ShortMessage midiMsg = new ShortMessage();
				try {
					midiMsg.setMessage(ShortMessage.NOTE_OFF, 0, midiNote, 0);
					String[] midiOutOptions = monome.getMidiOutOptions(index);
					for (int i = 0; i < midiOutOptions.length; i++) {
						if (midiOutOptions[i] == null) {
							continue;
						}
						Receiver recv = monome.getMidiReceiver(midiOutOptions[i]);
						if (recv != null) {
							recv.send(midiMsg, -1);
						}
					}
				} catch (InvalidMidiDataException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public class MIDINoteDelay implements Runnable {
		private int delayTime;
		private int pageIndex;
		private int midiNote;
		private int midiChannel;
		private int velocity;
		private boolean complete;
		private MonomeConfiguration monome;
		
		public MIDINoteDelay(MonomeConfiguration monome, int pageIndex, int delayTime, int midiNote, int midiChannel) {
			this.monome = monome;
			this.delayTime = delayTime;
			this.pageIndex = pageIndex;
			this.midiNote = midiNote;
			this.midiChannel = midiChannel;
			this.complete = false;
			this.velocity = 0;
		}
		
		public void setVelocity(int velocity) {
			this.velocity = velocity;
		}
		
		public boolean isComplete() {
			return this.complete;
		}
		
		public void run() {
			try {
				Thread.sleep(delayTime);
				ShortMessage midiMsg = new ShortMessage();
				try {
					if (velocity > 127) {
						velocity = 127;
					}
					midiMsg.setMessage(ShortMessage.NOTE_ON, midiChannel, midiNote, velocity);
				} catch (InvalidMidiDataException e) {
					e.printStackTrace();
				}
				String[] midiOutOptions = monome.getMidiOutOptions(this.pageIndex);
				for (int i = 0; i < midiOutOptions.length; i++) {
					if (midiOutOptions[i] == null) {
						continue;
					}
					Receiver recv = monome.getMidiReceiver(midiOutOptions[i]);
					if (recv != null) {
						recv.send(midiMsg, -1);
					}
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			this.complete = true;
		}
	}

	public void handleReset() {
		// TODO Auto-generated method stub

	}

	public void handleTick() {
		// TODO Auto-generated method stub

	}

	public boolean isTiltPage() {
		// TODO Auto-generated method stub
		return false;
	}

	public void redrawMonome() {
		this.monome.clear(0, index);
	}

	public void send(MidiMessage message, long timeStamp) {
		// TODO Auto-generated method stub

	}

	public void setIndex(int index) {
		this.index = index;
		setName(this.pageName);
	}

	public void setName(String name) {
		this.pageName = name;
		this.gui.setName(name);
	}

	public String toXml() {
		String xml = "";
		xml += "      <name>MIDI Pads</name>\n";
		xml += "      <pageName>" + this.pageName + "</pageName>\n";
		xml += "      <midiStartNote>" + this.midiStartNote + "</midiStartNote>\n";
		xml += "      <velocityFactor>" + this.velocityFactor + "</velocityFactor>\n";
		xml += "      <delayTime>" + this.delayTime + "</delayTime>\n";
		xml += "      <midiChannel>" + this.midiChannel + "</midiChannel>\n";
		return xml;
	}

}
