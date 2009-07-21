/*
 *  MonomeConfiguration.java
 * 
 *  copyright (c) 2008, tom dinchak
 * 
 *  This file is part of pages.
 *
 *  pages is free software; you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation; either version 2 of the License, or
 *  (at your option) any later version.
 *
 *  pages is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *  You should have received a copy of the GNU General Public License
 *  along with pages; if not, write to the Free Software
 *  Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA
 *
 */

package org.monome.pages;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import java.util.ArrayList;

import javax.sound.midi.MidiMessage;
import javax.sound.midi.Receiver;
import javax.sound.midi.Transmitter;
import javax.swing.BoxLayout;
import javax.swing.JInternalFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.illposed.osc.OSCMessage;

/**
 * @author Administrator
 *
 */
@SuppressWarnings("serial")
public class MonomeConfiguration extends JInternalFrame implements ActionListener {

	/**
	 * The monome's prefix (ie. "/40h")
	 */
	public String prefix;

	/**
	 * The monome's width (ie. 8 or 16)
	 */
	public int sizeX;

	/**
	 * The monome's height (ie. 8 or 16) 
	 */
	public int sizeY;

	/**
	 * The main Configuration object 
	 */
	public Configuration configuration;

	/**
	 * This monome's index 
	 */
	private int index;

	/**
	 * ledState[x][y] - The LED state cache for the monome
	 */
	public int[][] ledState;

	/**
	 * pageState[page_num][x][y] - The LED state cache for each page
	 */
	public int[][][] pageState = new int[255][32][32];

	/**
	 * The pages that belong to this monome
	 */
	private ArrayList<Page> pages = new ArrayList<Page>();
	private ArrayList<PatternBank> patternBanks = new ArrayList<PatternBank>();

	/**
	 * The number of pages this monome has 
	 */
	private int numPages = 0;

	/**
	 * The currently selected page
	 */
	public int curPage = 0;

	/**
	 * The options dropdown when creating a new page (contains a list of all page names)
	 */
	private String options[];

	/**
	 * The current page panel being displayed 
	 */
	private JPanel curPanel;

	/**
	 * 1 when the page change button is held down (bottom right button) 
	 */
	private int pageChangeMode = 0;

	/**
	 * true if a page has been changed while the page change button was held down 
	 */
	private boolean pageChanged = false;
	
	private String[] quantizationOptions = {"1", "1/2", "1/4", "1/8", "1/16", "1/32", "1/48", "1/96"};

	private int tickNum = 0;
	
	
	public ADC adcObj = new ADC();
	public boolean calibrationMode = false;
	
	

	/**
	 * @param configuration The main Configuration object
	 * @param index The index of this monome
	 * @param prefix The prefix of this monome
	 * @param sizeX The width of this monome
	 * @param sizeY The height of this monome
	 */
	public MonomeConfiguration(Configuration configuration, int index, String prefix, int sizeX, int sizeY) {
		// call the parent's constructor, build the window, initialize the options dropdown choices
		super(prefix, true, false, true, true);
		this.clearMonome();
		
		this.options = PagesRepository.getPageNames();
		
		//don't know if this is the best way to do this...but I was getting tired of the long messy classnames :)
		for (int i=0; i<options.length; i++) {
			options[i] = options[i].substring(17);					
		}
		
//		this.options[0] = "MIDI Sequencer";
//		this.options[1] = "MIDI Keyboard";
//		this.options[2] = "MIDI Faders";
//		this.options[3] = "MIDI Triggers";
//		this.options[4] = "External Application";
//		this.options[5] = "Ableton Clip Launcher";
//		this.options[6] = "Ableton Clip Skipper";
//		this.options[7] = "Ableton Live Looper";
//		this.options[8] = "Machine Drum Interface";
//		this.options[9] = "Ableton Clip Control";
//		this.options[10] = "MIDI Sequencer Poly";
		
		this.configuration = configuration;
		this.prefix = prefix;
		this.sizeX = sizeX;
		this.sizeY = sizeY;

		this.ledState = new int[32][32];

		JPanel monomePanel = new JPanel();
		monomePanel.setLayout(new BoxLayout(monomePanel, BoxLayout.PAGE_AXIS));		
		this.setJMenuBar(this.createMenuBar());
		this.pack();
	}

	/**
	 * Adds a new page to this monome
	 * 
	 * @param className The class name of the page to add
	 * @return The new Page object
	 */
	public Page addPage(String className) {
		Page page;		

		page = PagesRepository.getPageInstance(className, this, this.numPages);
		this.pages.add(this.numPages, page);
		this.switchPage(page, this.numPages, true);

		int numPatterns = this.sizeX;
		this.patternBanks.add(this.numPages, new PatternBank(numPatterns));
		
		this.numPages++;
		// recreate the menu bar to include this page in the show page list
		this.setJMenuBar(this.createMenuBar());
		return page;
	}

	/**
	 * Adds a new page to this monome.
	 * only for compatibility with previous versions.
	 * use addPage(String pageName) when possible.
	 * 
	 * @param pageName The name of the page to add
	 * @return The new Page object
	 * 
	 */
	public Page addPageByName(String pageName) {
		Page page = null;
		if (pageName.compareTo("MIDI Sequencer") == 0) {
			page = addPage("org.monome.pages.MIDISequencerPage");
		} 
		else if (pageName.compareTo("MIDI Keyboard") == 0) {
			page = addPage("org.monome.pages.MIDIKeyboardPage");
		}
		else if (pageName.compareTo("MIDI Faders") == 0) {
			page = addPage("org.monome.pages.MIDIFadersPage");
		}
		else if (pageName.compareTo("MIDI Triggers") == 0) {
			page = addPage("org.monome.pages.MIDITriggersPage");
		}
		else if (pageName.compareTo("External Application") == 0) {
			page = addPage("org.monome.pages.ExternalApplicationPage");
		}
		else if (pageName.compareTo("Ableton Clip Launcher") == 0) {
			page = addPage("org.monome.pages.AbletonClipLauncherPage");
		}
		else if (pageName.compareTo("Ableton Clip Skipper") == 0) {
			page = addPage("org.monome.pages.AbletonClipSkipperPage");
		}
		else if (pageName.compareTo("Ableton Live Looper") == 0) {
			page = addPage("org.monome.pages.AbletonLiveLooperPage");
		}
		else if (pageName.compareTo("Machine Drum Interface") == 0) {
			page = addPage("org.monome.pages.MachineDrumInterfacePage");
		}
		else if (pageName.compareTo("Ableton Clip Control") == 0) {
			page = addPage("org.monome.pages.AbletonClipControlPage");
		}
		else if (pageName.compareTo("MIDI Keyboard Julienb (work in progress)") == 0) {
			page = addPage("org.monome.pages.MIDIKeyboardJulienBPage");
		}
		else if (pageName.compareTo("MIDI Sequencer Poly") == 0) {
			page = addPage("org.monome.pages.MidiSequencerPagePoly");
		}
		
		return page;
	}
	
	
	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	public void actionPerformed(ActionEvent e) {
		// create a new page
		if (e.getActionCommand().equals("New Page")) {
			String name = (String)JOptionPane.showInputDialog(
					this,
					"Select a new page type",
					"New Page",
					JOptionPane.PLAIN_MESSAGE,
					null,
					options,
					"");
			if (name == null) {
				return;
			}
			name = "org.monome.pages." + name; 
			this.addPage(name);
		}
		// remove this monome configuration
		if (e.getActionCommand().equals("Remove Configuration")) {
			this.configuration.closeMonome(this.index);
		}
		// Switch page
		if (e.getActionCommand().contains("Show Page")) {
			String[] pieces = e.getActionCommand().split(":");
			int index = Integer.parseInt(pieces[0]);
			this.switchPage(this.pages.get(index - 1), index - 1, true);
		}
		// Delete page
		if (e.getActionCommand().contains("Delete Page")) {
			String[] pieces = e.getActionCommand().split(":");
			int index = Integer.parseInt(pieces[0]);
			this.deletePage((index - 1));
		}
		// Set quantization
		if (e.getActionCommand().contains("Set Quantization")) {
			String[] pieces = e.getActionCommand().split(":");
			int index = Integer.parseInt(pieces[0]) - 1;
			String option = (String)JOptionPane.showInputDialog(
					this,
					"Select new pattern quantization value",
					"Set Quantization",
					JOptionPane.PLAIN_MESSAGE,
					null,
					quantizationOptions,
					"");
			if (option == null) {
				return;
			}
			
			if (option.equals("1/96")) {
				this.patternBanks.get(index).setQuantization(1);
			} else if (option.equals("1/48")) {
				this.patternBanks.get(index).setQuantization(2);
			} else if (option.equals("1/32")) {
				this.patternBanks.get(index).setQuantization(3);
			} else if (option.equals("1/16")) {
				this.patternBanks.get(index).setQuantization(6);
			} else if (option.equals("1/8")) {
				this.patternBanks.get(index).setQuantization(12);
			} else if (option.equals("1/4")) {
				this.patternBanks.get(index).setQuantization(24);
			} else if (option.equals("1/2")) {
				this.patternBanks.get(index).setQuantization(48);
			} else if (option.equals("1")) {
				this.patternBanks.get(index).setQuantization(96);
			}
		}
			// Set quantization
		if (e.getActionCommand().contains("Set Pattern Length")) {
			String[] pieces = e.getActionCommand().split(":");
			int index = Integer.parseInt(pieces[0]) - 1;
			String option = (String)JOptionPane.showInputDialog(
					this,
					"Set new pattern length in bars (between 1 and 16)",
					"Set Pattern Length",
					JOptionPane.PLAIN_MESSAGE,
					null,
					null,
					"");
			
			if (option == null) {
				return;
			}
			
			try {
				int patternLength = Integer.parseInt(option);
				if (patternLength > 0 && patternLength <= 16) {
					this.patternBanks.get(index).setPatternLength(patternLength);
				}
			} catch (NumberFormatException nfe) {
				nfe.printStackTrace();
			}
		}
		
		// tilt/adc configuration
		if (e.getActionCommand().contains("Configure Tilt/ADC")) {
			this.calibrationMode = true;
			Page page = new ConfigADCPage(this, this.numPages);		
			
			this.pages.add(this.numPages, page);
			this.switchPage(page, this.numPages, true);

			int numPatterns = this.sizeX;
			this.patternBanks.add(this.numPages, new PatternBank(numPatterns));
			
			this.numPages++;
			// recreate the menu bar to include this page in the show page list
			this.setJMenuBar(this.createMenuBar());			
		}
	}

	private void deletePage(int i) {
		this.pages.get(i).destroyPage();
		this.pages.remove(i);
		this.numPages--;
		if (this.curPage >= i) {
			this.curPage--;
			System.out.println("cur page is " + this.curPage);
		}
		
		for (int x=0; x < this.pages.size(); x++) {
			this.pages.get(x).setIndex(x);
		}
		
		if (this.curPage > -1) {
			this.remove(this.curPanel);
			pages.get(this.curPage).clearPanel();
			this.curPanel = pages.get(this.curPage).getPanel();
			this.curPanel.setVisible(true);
			this.add(this.curPanel);
			this.validate();
			this.pack();
		} else {
			this.remove(this.curPanel);
			this.curPanel = null;
			this.validate();
			this.pack();
		}
		
		this.setJMenuBar(this.createMenuBar());
	}

	/**
	 * Switch pages on this monome.
	 * 
	 * @param page The page to switch to
	 * @param pageIndex The index of the page to switch to
	 * @param redrawPanel true if the GUI panel should be redrawn
	 */
	private void switchPage(Page page, int pageIndex, boolean redrawPanel) {
		this.curPage = pageIndex;
		page.redrawMonome();

		if (redrawPanel == true) {
			if (this.curPanel != null) {
				this.curPanel.setVisible(false);
				//this.remove(this.curPanel);
			}
			this.curPanel = page.getPanel();
			this.curPanel.setVisible(true);
			this.add(this.curPanel);
			this.validate();
			this.pack();
		}
	}
	
	public void redrawPanel() {
		if (this.curPanel != null) {
			this.curPanel.setVisible(false);
			this.curPanel.setEnabled(false);
			this.remove(this.curPanel);
		}
		this.curPanel = this.pages.get(this.curPage).getPanel();
		this.curPanel.setVisible(true);
		this.add(this.curPanel);
		this.validate();
		this.pack();
	}

	/**
	 * Called by AbletonClipUpdater to update the state of Ableton clips.
	 * 
	 * @param track Ableton track number (0 = track 1)
	 * @param clip Ableton clip number (0 = first clip)
	 * @param state State of the clip (1 = playing)
	 */
	public void updateAbletonClipState(int track, int clip, int state, float length) {
		if (this.pages.size() == 0) {
			return;
		}

		for (int i = 0; i < this.pages.size(); i++) {
			if (pages.get(i) instanceof AbletonLiveLooperPage) {
				AbletonLiveLooperPage page = (AbletonLiveLooperPage) pages.get(i);
				page.updateClipState(track, clip, state);
			} else if (pages.get(i) instanceof AbletonClipLauncherPage) {
				AbletonClipLauncherPage page = (AbletonClipLauncherPage) pages.get(i);
				page.updateClipState(track, clip, state);
			} else if (pages.get(i) instanceof AbletonClipSkipperPage) {
				AbletonClipSkipperPage page = (AbletonClipSkipperPage) pages.get(i);
				page.updateClipState(track, clip, state, length);
			} else if (pages.get(i) instanceof AbletonClipControlPage) {
				 AbletonClipControlPage page = (AbletonClipControlPage) pages.get(i);
				page.updateClipState(track, clip, state);
			} else if (pages.get(i) instanceof AbletonSceneLauncherPage) {
				AbletonSceneLauncherPage page = (AbletonSceneLauncherPage) pages.get(i);
				page.updateClipState(track, clip, state);
			}
		}
	}
	
	public void updateAbletonState(float tempo, int overdub) {
		if (this.pages.size() == 0) {
			return;
		}

		for (int i = 0; i < this.pages.size(); i++) {
			if (pages.get(i) instanceof AbletonLiveLooperPage) {
				AbletonLiveLooperPage page = (AbletonLiveLooperPage) pages.get(i);
				page.updateAbletonState(tempo, overdub);
			}
			if (pages.get(i) instanceof AbletonClipLauncherPage) {
				AbletonClipLauncherPage page = (AbletonClipLauncherPage) pages.get(i);
				page.updateAbletonState(tempo, overdub);
			}
			if (pages.get(i) instanceof AbletonClipControlPage) {
				AbletonClipControlPage page = (AbletonClipControlPage) pages.get(i);
				page.updateAbletonState(tempo, overdub);
			}
			if (pages.get(i) instanceof AbletonSceneLauncherPage) {
				AbletonSceneLauncherPage page = (AbletonSceneLauncherPage) pages.get(i);
				page.updateAbletonState(tempo, overdub);
			}
		}
	}
	
	public void updateAbletonArmState(int track, int state) {
		if (this.pages.size() == 0) {
			return;
		}

		for (int i = 0; i < this.pages.size(); i++) {
			if (pages.get(i) instanceof AbletonLiveLooperPage) {
				AbletonLiveLooperPage page = (AbletonLiveLooperPage) pages.get(i);
				page.updateAbletonArmState(track, state);
			}
			if (pages.get(i) instanceof AbletonClipLauncherPage) {
				AbletonClipLauncherPage page = (AbletonClipLauncherPage) pages.get(i);
				page.updateAbletonArmState(track, state);
			}
			if (pages.get(i) instanceof AbletonClipControlPage) {
				AbletonClipControlPage page = (AbletonClipControlPage) pages.get(i);
				page.updateAbletonArmState(track, state);
			}
			if (pages.get(i) instanceof AbletonSceneLauncherPage) {
				AbletonSceneLauncherPage page = (AbletonSceneLauncherPage) pages.get(i);
				page.updateAbletonArmState(track, state);
			}
		}
	}
	
	public void updateAbletonMuteState(int track, int state) {
		if (this.pages.size() == 0) {
			return;
		}

		for (int i = 0; i < this.pages.size(); i++) {
			if (pages.get(i) instanceof AbletonLiveLooperPage) {
				AbletonLiveLooperPage page = (AbletonLiveLooperPage) pages.get(i);
				page.updateAbletonMuteState(track, state);
			}
			if (pages.get(i) instanceof AbletonClipLauncherPage) {
				AbletonClipLauncherPage page = (AbletonClipLauncherPage) pages.get(i);
				page.updateAbletonMuteState(track, state);
			}
			if (pages.get(i) instanceof AbletonClipControlPage) {
				AbletonClipControlPage page = (AbletonClipControlPage) pages.get(i);
				page.updateAbletonMuteState(track, state);
			}
			if (pages.get(i) instanceof AbletonSceneLauncherPage) {
				AbletonSceneLauncherPage page = (AbletonSceneLauncherPage) pages.get(i);
				page.updateAbletonMuteState(track, state);
			}
		}
	}
	
	public void updateAbletonSoloState(int track, int state) {
		if (this.pages.size() == 0) {
			return;
		}

		for (int i = 0; i < this.pages.size(); i++) {
			if (pages.get(i) instanceof AbletonLiveLooperPage) {
				AbletonLiveLooperPage page = (AbletonLiveLooperPage) pages.get(i);
				page.updateAbletonSoloState(track, state);
			}
			if (pages.get(i) instanceof AbletonClipLauncherPage) {
				AbletonClipLauncherPage page = (AbletonClipLauncherPage) pages.get(i);
				page.updateAbletonSoloState(track, state);
			}
			if (pages.get(i) instanceof AbletonClipControlPage) {
				AbletonClipControlPage page = (AbletonClipControlPage) pages.get(i);
				page.updateAbletonSoloState(track, state);
			}
			if (pages.get(i) instanceof AbletonSceneLauncherPage) {
				AbletonSceneLauncherPage page = (AbletonSceneLauncherPage) pages.get(i);
				page.updateAbletonSoloState(track, state);
			}
		}
	}
	
	public void redrawAbletonPages() {
		if (this.pages.size() == 0) {
			return;
		}

		for (int i = 0; i < this.pages.size(); i++) {
			if (pages.get(i) instanceof AbletonClipLauncherPage) {
				AbletonClipLauncherPage page = (AbletonClipLauncherPage) pages.get(i);
				page.redrawMonome();
			}			
			if (pages.get(i) instanceof AbletonLiveLooperPage) {
				AbletonLiveLooperPage page = (AbletonLiveLooperPage) pages.get(i);
				page.redrawMonome();
			}
			if (pages.get(i) instanceof AbletonClipSkipperPage) {
				AbletonClipSkipperPage page = (AbletonClipSkipperPage) pages.get(i);
				page.redrawMonome();
			}
			if (pages.get(i) instanceof AbletonSceneLauncherPage) {
				AbletonSceneLauncherPage page = (AbletonSceneLauncherPage) pages.get(i);
				page.redrawMonome();
			}
			
			if (pages.get(i) instanceof AbletonClipControlPage) {
				AbletonClipControlPage page = (AbletonClipControlPage) pages.get(i);
				page.redrawMonome();
			}
			
		}
	}

	/**
	 * Update the record enabled/disabled state of an Ableton track.
	 * 
	 * @param track The track number to update
	 * @param armed The state of the track (1 = armed)
	 */
	public void updateTrackState(int track, int armed) {
		if (this.pages.size() == 0) {
			return;
		}

		for (int i = 0; i < this.pages.size(); i++) {
			if (pages.get(i) instanceof AbletonLiveLooperPage) {
				AbletonLiveLooperPage page = (AbletonLiveLooperPage) pages.get(i);
				page.updateTrackState(track, armed);
			}
			if (pages.get(i) instanceof AbletonClipLauncherPage) {
				AbletonClipLauncherPage page = (AbletonClipLauncherPage) pages.get(i);
				page.updateTrackState(track, armed);
			}
			if (pages.get(i) instanceof AbletonClipControlPage) {
				AbletonClipControlPage page = (AbletonClipControlPage) pages.get(i);
				page.updateTrackState(track, armed);
			}
			if (pages.get(i) instanceof AbletonSceneLauncherPage) {
				AbletonSceneLauncherPage page = (AbletonSceneLauncherPage) pages.get(i);
				page.updateTrackState(track, armed);
			}
		}
	}
	
	public void updateAbletonSceneState(int sceneNum) {
		if (this.pages.size() == 0) {
			return;
		}

		for (int i = 0; i < this.pages.size(); i++) {
			if (pages.get(i) instanceof AbletonSceneLauncherPage) {
				AbletonSceneLauncherPage page = (AbletonSceneLauncherPage) pages.get(i);
				page.updateSceneState(sceneNum);
			}
		}
	}

	
	/**
	 * Handles a press event from the monome.
	 * 
	 * @param x The x coordinate of the button pressed.
	 * @param y The y coordinate of the button pressed.
	 * @param value The type of event (1 = press, 0 = release)
	 */
	public void handlePress(int x, int y, int value) {
		// if we have no pages then dont handle any button presses
		if (this.pages.size() == 0) {
			return;
		}

		// if the monome isn't configured to handle this button then don't handle it
		// ie if you config a 256 as a 64 and hit a button out of range
		if (y >= this.sizeY || x >= this.sizeX) {
			return;
		}
		// if page change mode is on and this is a button on the bottom row then change page and return
		if (this.pageChangeMode == 1 && value == 1) {
			int next_page = x + ((this.sizeY - y - 1) * this.sizeX);
			int patternNum = x;
			int numPages = this.pages.size();
			if (numPages > this.sizeY - 1) {
				numPages++;
			}
			if (numPages > next_page && next_page < (this.sizeX * this.sizeY) / 2) {
				// offset back by one because of the page change button
				if (next_page > this.sizeY - 1) {
					next_page--;
				}
				this.curPage = next_page;
				this.switchPage(this.pages.get(this.curPage), this.curPage, true);
			} else if (y == 0) {
				this.patternBanks.get(this.curPage).handlePress(patternNum);
			}
			this.pageChanged = true;
			return;
		}

		// if this is the bottom right button and we pressed the button (value == 1), turn page change mode on
		if (x == (this.sizeX - 1) && y == (this.sizeY - 1) && value == 1) {
			this.pageChangeMode = 1;
			this.pageChanged = false;
			this.drawPatternState();
			return;
		}

		// if this is the bottom right button and we let go turn it off
		// and send the value == 1 press along to the page
		if (x == (this.sizeX - 1) && y == (this.sizeY - 1) && value == 0) {
			this.pageChangeMode = 0;
			if (this.pageChanged == false) {
				if (this.pages.get(curPage) != null) {
					this.pages.get(curPage).handlePress(x, y, 1);
					this.patternBanks.get(curPage).recordPress(x, y, 1);
					this.pages.get(curPage).handlePress(x, y, 0);
					this.patternBanks.get(curPage).recordPress(x, y, 0);
				}
			}
			this.pages.get(curPage).redrawMonome();
			return;
		}

		if (this.pages.get(curPage) != null) {
			this.patternBanks.get(curPage).recordPress(x, y, value);
			this.pages.get(curPage).handlePress(x, y, value);
		}
	}
	
	public void handleADC(int adcNum, float value) {
		// if we have no pages then dont handle any adc events
		if (this.pages.size() == 0) {
			return;
		}
		
		if (this.curPage > -1) {
			if (this.pages.get(curPage) != null) {			
				this.pages.get(curPage).handleADC(adcNum, value);
			}
		}
	}
	public void handleADC(float x, float y) {
		// if we have no pages then dont handle any adc events
		if (this.pages.size() == 0) {
			return;
		}
		
		if (this.curPage > -1) {
			if (this.pages.get(curPage) != null) {			
				this.pages.get(curPage).handleADC(x, y);
			}
		}
	}

	public void drawPatternState() {
		for (int x=0; x < this.sizeX; x++) {
			if (this.patternBanks.get(curPage).getPatternState(x) == PatternBank.PATTERN_STATE_TRIGGERED) {
				if (this.ledState[x][0] == 1) {
					this.led(x, 0, 0, -1);
				} else {
					this.led(x, 0, 1, -1);
				}
			} else if (this.patternBanks.get(curPage).getPatternState(x) == PatternBank.PATTERN_STATE_RECORDED) {
				this.led(x, 0, 1, -1);
			} else if (this.patternBanks.get(curPage).getPatternState(x) == PatternBank.PATTERN_STATE_EMPTY) {
				this.led(x, 0, 0, -1);
			}
		}
	}
	/**
	 * Builds the monome configuration window's Page menu
	 * 
	 * @return The Page menu
	 */
	public JMenuBar createMenuBar() {
		JMenuBar menuBar;
		JMenu fileMenu;
		JMenuItem menuItem;

		menuBar = new JMenuBar();
		
		if(this.calibrationMode)
		{
			//do nothing...menu disabled
		} else
		{	
			fileMenu = new JMenu("Page");
			fileMenu.setMnemonic(KeyEvent.VK_P);
			fileMenu.getAccessibleContext().setAccessibleDescription("Page Menu");
	
			menuBar.add(fileMenu);
	
			menuItem = new JMenuItem("New Page", KeyEvent.VK_N);
			menuItem.getAccessibleContext().setAccessibleDescription("Create a new page");
			menuItem.addActionListener(this);
			fileMenu.add(menuItem);
	
			JMenu subMenu = new JMenu("Show Page");
	
			if (this.numPages == 0) {
				menuItem = new JMenuItem("No Pages Defined");
				subMenu.add(menuItem);
			} else {
				for (int i=0; i < this.numPages; i++) {
					menuItem = new JMenuItem(i+1 + ": Show Page " + this.pages.get(i).getName());
					menuItem.addActionListener(this);
					subMenu.add(menuItem);
				}
			}
			fileMenu.add(subMenu);
			
		    subMenu = new JMenu("Delete Page");
	
			if (this.numPages == 0) {
				menuItem = new JMenuItem("No Pages Defined");
				subMenu.add(menuItem);
			} else {
				for (int i=0; i < this.numPages; i++) {
					menuItem = new JMenuItem(i+1 + ": Delete Page " + this.pages.get(i).getName());
					menuItem.addActionListener(this);
					subMenu.add(menuItem);
				}
			}
			fileMenu.add(subMenu);
	
			subMenu = new JMenu("Set Quantization");
	
			if (this.numPages == 0) {
				menuItem = new JMenuItem("No Pages Defined");
				subMenu.add(menuItem);
			} else {
				for (int i=0; i < this.numPages; i++) {
					menuItem = new JMenuItem(i+1 + ": Set Quantization for " + this.pages.get(i).getName());
					menuItem.addActionListener(this);
					subMenu.add(menuItem);
				}
			}
			fileMenu.add(subMenu);
	
			subMenu = new JMenu("Set Pattern Length");
	
			if (this.numPages == 0) {
				menuItem = new JMenuItem("No Pages Defined");
				subMenu.add(menuItem);
			} else {
				for (int i=0; i < this.numPages; i++) {
					menuItem = new JMenuItem(i+1 + ": Set Pattern Length for " + this.pages.get(i).getName());
					menuItem.addActionListener(this);
					subMenu.add(menuItem);
				}
			}
			fileMenu.add(subMenu);
			
			menuItem = new JMenuItem ("Configure Tilt/ADC");
			menuItem.getAccessibleContext().setAccessibleDescription("Configure Tilt/ADC");	
			menuItem.addActionListener(this);
			fileMenu.add(menuItem);
			
			menuItem = new JMenuItem("Remove Configuration", KeyEvent.VK_R);
			menuItem.getAccessibleContext().setAccessibleDescription("Create a new configuration");
			menuItem.addActionListener(this);
			fileMenu.add(menuItem);
		}

		return menuBar;
	}

	/**
	 * Called every time a MIDI clock sync 'tick' is received, this triggers each page's handleTick() method
	 */
	public void tick() {
		for (int i=0; i < this.numPages; i++) {
			ArrayList<Press> presses = patternBanks.get(i).getRecordedPresses();
			if (presses != null) {
				for (int j=0; j < presses.size(); j++) {
					int[] press = presses.get(j).getPress();
					this.pages.get(i).handlePress(press[0], press[1], press[2]);
				}
			}
			this.pages.get(i).handleTick();
			this.patternBanks.get(i).handleTick();
		}
		if (this.pageChangeMode == 1 && this.tickNum % 12 == 0) {			
			this.drawPatternState();
		}
		this.tickNum++;
		if (this.tickNum == 96) {
			this.tickNum = 0;
		}
	}

	/**
	 * Called every time a MIDI clock sync 'reset' is received, this triggers each page's handleReset() method.
	 */
	public void reset() {
		for (int i=0; i < this.numPages; i++) {
			this.pages.get(i).handleReset();
			this.patternBanks.get(i).handleReset();
		}
		this.tickNum = 0;
	}

	/**
	 * Called every time a MIDI message is received, the messages are passed along to each page.
	 * 
	 * @param message The MIDI message received
	 * @param timeStamp The timestamp of the MIDI message
	 */
	public void send(MidiMessage message, long timeStamp) {
		for (int i=0; i < this.numPages; i++) {
			this.pages.get(i).send(message, timeStamp);
		}
	}

	/**
	 * Sends a /led x y value command to the monome if index is the selected page.
	 * 
	 * @param x The x coordinate of the led
	 * @param y The y coordinate of the led
	 * @param value The value of the led (1 = on, 0 = off)
	 * @param index The index of the page making the request
	 */
	public void led(int x, int y, int value, int index) {
		if (x < 0 || y < 0 || value < 0) {
			return;
		}
		
		if (index > -1) {
			this.pageState[index][x][y] = value;
	
			if (index != this.curPage) {
				return;
			}
	
			if (this.pages.get(index) == null) {
				return;
			}
	
			if (this.pages.get(index).getCacheDisabled() == false) {
				if (this.ledState[x][y] == value) {
					return;
				}	
			}
		}

		this.ledState[x][y] = value;

		Object args[] = new Object[3];
		args[0] = new Integer(x);
		args[1] = new Integer(y);
		args[2] = new Integer(value);
		OSCMessage msg = new OSCMessage(this.prefix + "/led", args);
		try {
			this.configuration.monomeSerialOSCPortOut.send(msg);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Clear the monome.
	 */
	public void clearMonome() {
		for (int x=0; x < this.sizeX; x++) {
			for (int y=0; y < this.sizeY; y++) {
				this.ledState[x][y] = 0;
				Object args[] = new Object[3];
				args[0] = new Integer(x);
				args[1] = new Integer(y);
				args[2] = new Integer(0);
				OSCMessage msg = new OSCMessage(this.prefix + "/led", args);
				try {
					this.configuration.monomeSerialOSCPortOut.send(msg);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * Sends a led_col message to the monome if index is the selected page.
	 * 
	 * @param col The column to effect
	 * @param value1 The first 8 bits of the value
	 * @param value2 The second 8 bits of the value
	 * @param index The index of the page making the call
	 */
	public void led_col(ArrayList<Integer> intArgs, int index) {
		int col = intArgs.get(0);
		int[] values = {0, 0, 0, 0};
		int numValues = 0;
		for (int i = 1; i < intArgs.size(); i++) {
			if (i > 4) {
				break;
			}
			values[i] = intArgs.get(i);
			numValues++;
		}
		int fullvalue = (values[3] << 24) + (values[2] << 16) + (values[1] << 8) + values[0];
		for (int y=0; y < this.sizeY; y++) {
			int bit = (fullvalue >> (this.sizeY - y - 1)) & 1;
			this.pageState[index][col][y] = bit;
		}

		if (index != this.curPage) {
			return;
		}

		for (int y=0; y < this.sizeY; y++) {
			int bit = (fullvalue >> (this.sizeY - y - 1)) & 1;
			this.ledState[col][y] = bit;
		}

		Object args[] = new Object[numValues + 1];
		args[0] = new Integer(col);
		for (int i = 1; i < numValues + 1; i++) {
			args[i] = (Integer) intArgs.get(i);
		}
		OSCMessage msg = new OSCMessage(this.prefix + "/led_col", args);

		try {
			this.configuration.monomeSerialOSCPortOut.send(msg);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Sends a led_row message to the monome if index is the selected page.
	 * 
	 * @param row The row to effect
	 * @param value1 The first 8 bits of the value
	 * @param value2 The second 8 bits of the value
	 * @param index The index of the page making the call
	 */
	public void led_row(ArrayList<Integer> intArgs, int index) {
		int row = intArgs.get(0);
		int[] values = {0, 0, 0, 0};
		int numValues = 0;
		for (int i = 1; i < intArgs.size(); i++) {
			if (i > 4) {
				break;
			}
			values[i] = intArgs.get(i);
			numValues++;
		}
		int fullvalue = (values[3] << 24) + (values[2] << 16) + (values[1] << 8) + values[0];
		for (int x=0; x < this.sizeX; x++) {
			int bit = (fullvalue >> (this.sizeX - x- 1)) & 1;
			this.pageState[index][x][row] = bit;
		}

		if (index != this.curPage) {
			return;
		}

		for (int x=0; x < this.sizeX; x++) {
			int bit = (fullvalue >> (this.sizeX - x - 1)) & 1;
			this.ledState[x][row] = bit;
		}

		Object args[] = new Object[numValues + 1];
		args[0] = new Integer(row);
		for (int i = 1; i < numValues + 1; i++) {
			args[i] = (Integer) intArgs.get(i);
		}
		OSCMessage msg = new OSCMessage(this.prefix + "/led_row", args);

		try {
			this.configuration.monomeSerialOSCPortOut.send(msg);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Sends a frame message to the monome if index is the selected page
	 * TODO: implement this method
	 * 
	 * @param x 
	 * @param y
	 * @param values
	 * @param index
	 */
	public void frame(int x, int y, int[] values, int index) {
		for (int i=0; i < values.length; i++) {
		}
	}

	/**
	 * Sends a clear message to the monome if index is the selected page
	 * 
	 * @param state See monome OSC spec 
	 * @param index The index of the page making the call
	 */
	public void clear(int state, int index) {		
		if (state == 0 || state == 1) {
			for (int x = 0; x < this.sizeX; x++) {
				for (int y = 0; y < this.sizeY; y++) {
					this.pageState[index][x][y] = state;
				}
			}

			if (index != this.curPage) {
				return;
			}

			for (int x = 0; x < this.sizeX; x++) {
				for (int y = 0; y < this.sizeY; y++) {
					this.ledState[x][y] = state;
				}
			}

			Object args[] = new Object[1];
			args[0] = new Integer(state);
			OSCMessage msg = new OSCMessage(this.prefix + "/clear", args);

			try {
				this.configuration.monomeSerialOSCPortOut.send(msg);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Converts the current monome configuration to XML.
	 * 
	 * @return XML representing the current monome configuration
	 */
	public String toXml() {
		String xml = "";
		xml += "  <monome>\n";
		xml += "    <prefix>" + this.prefix + "</prefix>\n";
		xml += "    <sizeX>" + this.sizeX + "</sizeX>\n";
		xml += "    <sizeY>" + this.sizeY + "</sizeY>\n";
		
		//xml += "    <adcVersion>" + this.adcObj.getMonomeVersion() + "</adcVersion>\n";
		
		float [] min = this.adcObj.getMin();
		xml += "    <min>" + min[0] + "</min>\n"; 
		xml += "    <min>" + min[1] + "</min>\n";
		xml += "    <min>" + min[2] + "</min>\n";
		xml += "    <min>" + min[3] + "</min>\n";
		
		float [] max = this.adcObj.getMax();
		xml += "    <max>" + max[0] + "</max>\n"; 
		xml += "    <max>" + max[1] + "</max>\n";
		xml += "    <max>" + max[2] + "</max>\n";
		xml += "    <max>" + max[3] + "</max>\n";
		
		for (int i=0; i < this.numPages; i++) {
			if (this.pages.get(i).toXml() != null) {
				xml += "    <page class=\"" + this.pages.get(i).getClass().getName() + "\">\n";
				xml += this.pages.get(i).toXml();
				xml += "    </page>\n";
			}
		}
		for (int i=0; i < this.numPages; i++) {
			int patternLength = this.patternBanks.get(i).getPatternLength();
			int quantization = this.patternBanks.get(i).getQuantization();
			xml += "    <patternlength>" + patternLength + "</patternlength>\n";
			xml += "    <quantization>" + quantization + "</quantization>\n";
		}
		xml += "  </monome>\n";
		return xml;
	}
	
	public void setPatternLength(int pageNum, int length) {
		this.patternBanks.get(pageNum).setPatternLength(length);
	}
	
	public void setQuantization(int pageNum, int quantization) {
		this.patternBanks.get(pageNum).setQuantization(quantization);
	}

	/**
	 * @return The MIDI outputs that have been enabled in the main configuration.
	 */
	public String[] getMidiOutOptions() {
		return this.configuration.getMidiOutOptions();
	}

	/**
	 * The Receiver object for the MIDI device named midiDeviceName. 
	 * 
	 * @param midiDeviceName The name of the MIDI device to get the Receiver for
	 * @return The MIDI receiver
	 */
	public Receiver getMidiReceiver(String midiDeviceName) {
		return this.configuration.getMIDIReceiverByName(midiDeviceName);
	}
	
	public Transmitter getMidiTransmitter(String midiDeviceName) {
		return this.configuration.getMIDITransmitterByName(midiDeviceName);
	}

	/**
	 * Used to clean up OSC connections held by individual pages.
	 */
	public void destroyPage() {
		for (int i = 0; i < this.numPages; i++) {
			this.pages.get(i).destroyPage();
		}
	}
	
	/**
	 * Used so the ConfigADCPage can delete it's self on exit
	 */
	public void deletePageX(int index) {
		deletePage(index);
	}

}