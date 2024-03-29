/*
 *  AbletonOSCListener.java
 * 
 *  Copyright (c) 2008, Tom Dinchak
 * 
 *  This file is part of Pages.
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

import java.util.Date;

import com.illposed.osc.OSCListener;
import com.illposed.osc.OSCMessage;

/**
 * The AbletonOSCListener object listens for OSC messages from Ableton
 * calls the Configuration object when messages are received to update
 * any Ableton Clip Launcher pages.
 * 
 * @author Tom Dinchak
 *
 */
public class AbletonOSCListener implements OSCListener {

	/**
	 * The Configuration object
	 */
	private Configuration configuration;
	
	/**
	 * @param config The Configuration object
	 */
	AbletonOSCListener(Configuration config) {
		this.configuration = config;
	}

	/* (non-Javadoc)
	 * @see com.illposed.osc.OSCListener#acceptMessage(java.util.Date, com.illposed.osc.OSCMessage)
	 */
	public void acceptMessage(Date arg0, OSCMessage msg) {
	    //System.out.print("OSC received: " + msg.getAddress());
	    //Object[] tmpargs = msg.getArguments();
	    //for (int x = 0; x < tmpargs.length; x++) {
	    //	System.out.print(" " + tmpargs[x].toString());
	    //}
	    //System.out.println();
	    
		if (msg.getAddress().contains("/live/track/info")) {
			Object[] args = msg.getArguments();
			int trackId = ((Integer) args[0]).intValue();
			int armed = ((Integer) args[1]).intValue();
			AbletonTrack track = this.configuration.abletonState.getTrack(trackId);
        	if (track == null) {
        		track = this.configuration.abletonState.createTrack(trackId);
        	}
			track.setArm(armed);
			for (int i=2; i < args.length; i+=3) {
				int clipId = ((Integer) args[i]).intValue();
				int clipState = ((Integer) args[i+1]).intValue();
				float length = ((Float) args[i+2]).floatValue();
				AbletonClip clip = track.getClip(clipId);
				if (clip == null) {
					clip = track.createClip(clipId);
				}
				clip.setState(clipState);
				clip.setLength(length);
			}
			this.configuration.redrawAbletonPages();
		}
		
		if (msg.getAddress().contains("/live/clip/info")) {
			Object[] args = msg.getArguments();
			int trackId = ((Integer) args[0]).intValue();
			int clipId = ((Integer) args[1]).intValue();
			int clipState = ((Integer) args[2]).intValue();
			AbletonTrack track = this.configuration.abletonState.getTrack(trackId);
        	if (track == null) {
        		track = this.configuration.abletonState.createTrack(trackId);
        	}
			AbletonClip clip = track.getClip(clipId);
        	if (clip == null) {
        		clip = track.createClip(clipId);
        	}

			clip.setState(clipState);
			this.configuration.redrawAbletonPages();
		}
		
        if (msg.getAddress().contains("/live/state")) {
            Object[] args = msg.getArguments();
            float tempo = ((Float) args[0]).floatValue();
            int overdub = ((Integer) args[1]).intValue();
            this.configuration.abletonState.setTempo(tempo);
            this.configuration.abletonState.setOverdub(overdub);
    		this.configuration.redrawAbletonPages();
        }
        
        if (msg.getAddress().contains("/live/scene")) {
        	Object[] args = msg.getArguments();
        	int selectedScene = ((Integer) args[0]).intValue();
        	this.configuration.abletonState.setSelectedScene(selectedScene);
    		this.configuration.redrawAbletonPages();
        }
        
        if (msg.getAddress().contains("/live/arm")) {
        	Object[] args = msg.getArguments();
        	int trackId = ((Integer) args[0]).intValue();
        	int armState = ((Integer) args[1]).intValue();
        	AbletonTrack track = this.configuration.abletonState.getTrack(trackId);
        	if (track == null) {
        		track = this.configuration.abletonState.createTrack(trackId);
        	}
        	track.setArm(armState);
        }

        if (msg.getAddress().contains("/live/solo")) {
        	Object[] args = msg.getArguments();
        	int trackId = ((Integer) args[0]).intValue();
        	int soloState = ((Integer) args[1]).intValue();
        	AbletonTrack track = this.configuration.abletonState.getTrack(trackId);
        	if (track == null) {
        		track = this.configuration.abletonState.createTrack(trackId);
        	}
        	track.setSolo(soloState);
        }
		
        if (msg.getAddress().contains("/live/mute")) {
        	Object[] args = msg.getArguments();
        	int trackId = ((Integer) args[0]).intValue();
        	int muteState = ((Integer) args[1]).intValue();
        	AbletonTrack track = this.configuration.abletonState.getTrack(trackId);
        	if (track == null) {
        		track = this.configuration.abletonState.createTrack(trackId);
        	}
        	track.setMute(muteState);
        }
        
        if (msg.getAddress().contains("/live/tempo")) {
        	Object[] args = msg.getArguments();
        	float tempo = ((Float) args[0]).floatValue();
        	this.configuration.abletonState.setTempo(tempo);
        }
        
        if (msg.getAddress().contains("/live/overdub")) {
        	Object[] args = msg.getArguments();
        	int overdub = ((Integer) args[0]).intValue();
        	this.configuration.abletonState.setOverdub(overdub - 1);
        }
        
        if (msg.getAddress().contains("/live/refresh")) {
        	this.configuration.getAbletonControl().refreshAbleton();
        }
        
        if (msg.getAddress().contains("/live/reset")) {
        	this.configuration.getAbletonControl().resetAbleton();
        }
        
	}
}