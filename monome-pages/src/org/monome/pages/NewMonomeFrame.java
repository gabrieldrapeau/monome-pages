/*
 *  NewMonomeFrame.java
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

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * This class generates a configuration window for a new monome configuration.
 * 
 * @author Tom Dinchak
 *
 */
@SuppressWarnings("serial")
public class NewMonomeFrame extends JInternalFrame implements ActionListener {

	/**
	 * The main Configuration object
	 */
	private Configuration configuration;

	/**
	 * The main application GUI
	 */
	private JFrame frame;

	/**
	 * Text field to store the width of the new monome configuration
	 */
	private JTextField sizeX;

	/**
	 * Text field to store the height of the new monome configuration
	 */
	private JTextField sizeY;

	/**
	 * Text field to store the OSC prefix of the new monome configuration 
	 */
	private JTextField prefix;

	/**
	 * @param configuration The main Configuration object
	 * @param frame The main application GUI
	 */
	public NewMonomeFrame(Configuration configuration, JFrame frame) {

		super("New Monome", true, true);

		this.configuration = configuration;
		this.frame = frame;

		JLabel label;
		JPanel subPanel;
		JButton button;

		JPanel monomePanel = new JPanel();
		monomePanel.setLayout(new BoxLayout(monomePanel, BoxLayout.PAGE_AXIS));

		subPanel = new JPanel();
		subPanel.setLayout(new GridLayout(1, 1));
		label = new JLabel("Size X (Width)");
		subPanel.add(label);
		this.sizeX = new JTextField("8");
		this.sizeX.setEditable(true);
		subPanel.add(this.sizeX);

		monomePanel.add(subPanel);
		subPanel = new JPanel();
		subPanel.setLayout(new GridLayout(1, 1));
		label = new JLabel("Size Y (Height)");
		subPanel.add(label);
		this.sizeY = new JTextField("8");
		this.sizeY.setEditable(true);
		subPanel.add(this.sizeY);
		monomePanel.add(subPanel);

		subPanel = new JPanel();
		subPanel.setLayout(new GridLayout(1, 1));
		label = new JLabel("OSC Prefix");
		subPanel.add(label);
		this.prefix = new JTextField("/40h");
		this.prefix.setEditable(true);
		subPanel.add(this.prefix);
		monomePanel.add(subPanel);

		subPanel = new JPanel();
		button = new JButton("Save");
		button.addActionListener(this);		
		subPanel.add(button);

		button = new JButton("Cancel");
		button.addActionListener(this);
		subPanel.add(button);		
		monomePanel.add(subPanel);

		this.add(monomePanel);
		this.pack();		
	}

	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("Cancel")) {
			this.dispose();
		} else if (e.getActionCommand().equals("Save")) {
			int sizeX = Integer.parseInt(this.sizeX.getText());
			int sizeY = Integer.parseInt(this.sizeY.getText());
			String prefix = this.prefix.getText();
			ArrayList<MIDIPageChangeRule> midiPageChangeRules = new ArrayList<MIDIPageChangeRule>();
			int index = this.configuration.addMonomeConfiguration(prefix, sizeX, sizeY, true, false, midiPageChangeRules);
			MonomeConfiguration monomeFrame = this.configuration.getMonomeConfigurationFrame(index);
			monomeFrame.setVisible(true);
			this.frame.add(monomeFrame);
			this.frame.validate();
			this.dispose();
		}
	}
}

