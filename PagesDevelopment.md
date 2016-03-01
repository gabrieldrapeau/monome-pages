# How to make your own page with Eclipse #

## Setting up the Development Environment ##

I develop pages using the Eclipse IDE, which is free and available for all platforms.  Prerequisites for this tutorial include:

  * JDK 1.5 or later
  * Eclipse version 3.6 (Helios)
  * Subclipse plugin
  * Visual Editor plugin

First install JDK 1.5 or later (1.6 is fine).  Just go to java.sun.com and navigate around the site until you find the Downloads area, shouldn't be too hard.  OSX has an Apple installer for this.

Next get Eclipse.  The Ganymede release is available here:

http://www.eclipse.org

Download this and unzip it somewhere, it doesn't have an installer so you just need to put it where you want it.

Now run Eclipse.  You'll get some funny circular icons on a welcome page.  Find the "Go To Workbench" icon and click it.  It'll probably ask you to create a workspace, go ahead and pick some folder where all the code and project settings should be stored.

You should be in the application now.  First install Subclipse and Visual Editor.  Go to Help -> Software Updates.  Click the available software tab if you aren't already there.  Click 'Add Site' and add this URL:

http://download.eclipse.org/tools/ve/updates/1.5.0/

Name it Visual Editor.  Now do this again for Subclipse:

http://subclipse.tigris.org/update_1.6.x

Now, back in the Available Software page, open up the Subclipse 1.6 update site and choose the Subclipse option.  For Visual Editor, all you actually need is "Visual Editor", not the SDK / Java EMF model / etc, so just select that.  Install your software, you probably have to accept some licenses.  You'll be asked to restart Eclipse when this is done, do that.

It's time to pull down the Pages code.  First, create a new project by going to the File menu -> New -> Project... You will be see the new project wizard. Expand the SVN folder and choose Checkout Projects from SVN and click Next.

On the next page keep 'Create a new repository location' selected and click Next. When it asks for the URL, give it this URL:

http://monome-pages.googlecode.com/svn/trunk

The next screen will show you a hierarchical view of the code repository. Click to highlight the "pages-0.2" folder and click Next. You will see a Check Out As window, keep all the defaults the same and click on Finish

You are now ready to start developing and testing. You should see a new entry on the left entitled "pages-0.2", if you expand that you'll see src and doc, and if you expand src and org.monome.pages underneath it you'll see the Pages source code.

## Making a Page ##

First, go to the File menu, pick New -> Class.  Set the package org.monome.pages.pages.  Add the Interface "Page" (org.monome.pages.pages.Page).  Click the "Add..." button by Interfaces and type Page, select it, hit OK.  We're going to make the MIDI Pads page today so type "MIDIPadsPage" into the Name field.  You will now have MIDIPadsPage.java that has stub methods for all of the Page functions.

Next let's make a GUI window for the page.  Go to the File menu, pick New -> Other..., open [+] Java, and choose "Visual Class".  Click Next.  Name it MIDIPadsGUI and change the Superclass to JPanel (click Browse..., type JPanel, select it, click OK).  Click Finish to create MIDIPadsGUI.java.

Open MIDIPadsPage.java and make the following changes:

```
package org.monome.pages.pages;

import javax.sound.midi.MidiMessage;
import javax.swing.JPanel;

import org.monome.pages.configuration.MonomeConfiguration;
import org.monome.pages.pages.gui.MIDIPadsGUI;
import org.w3c.dom.Element;

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
	
	public MIDIPadsPage(MonomeConfiguration monome, int index) {
		this.index = index;
		this.monome = monome;
		this.gui = new MIDIPadsGUI(this);
	}

	public void configure(Element pageElement) {

	}
...
```

We imported MIDIPadsGUI, defined 3 member variables for the class (monome, index, and gui), and created a constructor that takes two arguments, a MonomeConfiguration object and an index:

```
	public MIDIPadsPage(MonomeConfiguration monome, int index) {
```

This is called when the page is created.  Its purpose is to give the instance of this page a copy of the MonomeConfiguration object it exists in as well as its index within that MonomeConfiguration object.  We also create a new instance of the MIDIPadsGUI object and store a reference to it in the this.gui variable.

You probably have an error right nowthis.gui = new MIDIPadsGUI(this).  That's because MIDIPadsGUI doesn't have a constructor defined that takes an argument of type MIDIPadsPage.  Hover over the line and you should see a pop-up window with suggested fixes.  Pick "Change constructor 'MIDIPagesGUI()': Add parameter 'MIDIPadPage'".  You will be taken to MIDIPagesGUI.java with the change made for you, save the file and go back to MIDIPadsPage.java.

Let's implement some basic methods first:

```
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
	
...

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
		return xml;
	}
```

The first three are simply getters for some of the globals.  The setIndex/setName methods also call the gui's setName() method to update the name label on the screen.  Let's add the name label to the GUI.  Right click on MIDIPadsGUI and select Open With -> Visual Editor.  You should see a grey box in the top half of your editor window and code on the bottom half.  Right click on the grey box and choose "Set Layout -> null".  Right click on the grey box again and choose 'Customize Layout'.  Drag the Width or Height slider down to 5 and check "Snap to Grid", then close the window.

If you don't see the Palette on the right then look for a small grey arrow in the upper right of the editor area and click on it.  This should keep it open.  Choose JLabel from the Swing Components section and click somewhere on the grey box (or outside of it, it doesn't matter).  You'll be asked for a name, change it to pageLabel and click OK.  Now move it towards the upper left corner, make it a little bigger so it occupies 20px (4 dots), and resize it so it's a little longer.  Right click on it, choose Set Text, and change the text to "MIDI Pads Page".

Now go to the code and make the following changes:

```
public class MIDIPadsGUI extends JPanel {

	private static final long serialVersionUID = 1L;
	private JLabel pageLabel = null;
	
	/**
	 * The MIDIPadsPage this GUI is attached to
	 */
	private MIDIPadsPage page;

	/**
	 * This is the default constructor
	 * @param padsPage 
	 * @param padsPage 
	 */
	public MIDIPadsGUI(MIDIPadsPage padsPage) {
		super();
		this.page = padsPage;
		initialize();
	}
	
	public void setName(String name) {
		pageLabel.setText((page.getIndex() + 1) + ": " + name);
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		pageLabel = new JLabel();
		pageLabel.setBounds(new Rectangle(10, 10, 206, 21));
		pageLabel.setText("MIDI Pads Page");
		setName("MIDI Pads Page");
		this.setSize(231, 199);
		this.setLayout(null);
		this.add(pageLabel, null);
	}
...
```

What we did here:

  1. We defined the "page" member variable as the type MIDIPadsPage.
  1. In the MIDIPadsGUI constructor we set this.page = padsPage.
  1. We defined the setName function to change the text of pageLabel based on the page index.
  1. We added a call to setName in the initialize() method.

What it accomplished:

The pageLabel text will now have the proper page number in front of the name.  We also have an instance of the MIDIPadsPage that this GUI belongs to so that changes in the GUI can be easily set in the page object.

One last thing we should do at this point is add our page to the META-INF file so that it can be selected and used.  Open META-INF/services and open up org.monome.pages.pages.Page.  This file has a list of Page classes.  Add org.monome.pages.pages.MIDIPadsPage to that list:

```
...
org.monome.pages.pages.MIDIGeneratorPage
org.monome.pages.pages.MIDIPadsPage
org.monome.pages.pages.MIDISequencerPage
...
```

We now have a fully functioning page, although it doesn't actually do anything yet, but it can be selected, created, saved and loaded in a configuration, etc.  Now it's time for the fun stuff: to define what this page is actually going to do.  A lot of the hard work in doing monome and MIDI communication is done by the framework so this shouldn't be too painful.  Let's start implementing the methods that are actually going to do stuff.

We're going to focus on two methods for this page: handlePress() and redrawMonome().  We'll also use toXml() and configure() once we have some options to save and load.  The other methods are mostly for receiving MIDI messages and ADC information but we aren't interested in them right now: all we want to do is convert button presses to MIDI messages.

redrawMonome() is called every time the entire page needs to be redrawn, ie. when you switch pages.  The page may want to call redrawMonome() itself if you have sub-pages within that page (like the MIDI Sequencer page).  Since this page doesn't maintain any sort of state that is represented on the monome itself, it seems reasonable to just clear the monome when this is called.  We'll light up the LEDs as buttons are pressed instead.  So here's what redrawMonome() looks like:

```
	public void redrawMonome() {
		this.monome.clear(0, index);
	}
```

We're calling the clear() method of the MonomeConfiguration object that this page resides in and telling it to set all LEDs to 0 (the first argument), and we're giving it our page index.  All of the monome communication calls (led, led\_row, led\_col) take a page index argument.  This is because you might be on another page and don't actually want this to happen.  The nice part is that pages don't have to worry about the MonomeConfiguration state, they just ask it to light up a LED.  If the MonomeConfiguration object decides that the LED should actually be lit up, it will be, otherwise it will be stored in the page's own LED cache.  This cache exists so that when you switch back to the page, if events have happened since you were last on it that have changed the LED state, the current state will be represented.

While we're talking about caches, there's also a monome state cache that is independent of the pages.  This helps to draw entire pages faster.  It works by determining if a LED actually needs to be turned off or on before sending the message over USB to do it.  If the LED is already off when a led() call comes in to turn it off, it will ignore the request.  This means you don't have to worry about trying to optimize your page drawing code to check the current LED state.  This helps keep your page logic cleaner and simpler.

Now for the rest of the page, the part that actually does something:

First, add these member variables to the top by the gui / index / monome variables:

```
	int[][] velocities = new int[16][16];
	PadDelay[][] padDelays = new PadDelay[16][16];
```

Now overwrite the handlePress method with this:

```
	public void handlePress(int x, int y, int value) {
		this.monome.led(x, y, value, index);
		int velX = x / 2;
		int velY = y / 2;
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
				try {
					ShortMessage midiMsg = new ShortMessage();
					midiMsg.setMessage(ShortMessage.NOTE_OFF, 0, midiNote, 0);
					monome.sendMidi(midiMsg, index);
				} catch (InvalidMidiDataException e) {
					e.printStackTrace();
				}
			}
		}
	}
```

So what's going on here: first, I ask the MonomeConfiguration object to light up the button that was pushed with the value passed in.  This makes the buttons light when pressed and turn off when released:

```
		this.monome.led(x, y, value, index);
```

Next I calculate the offsets into the "velocities" and "padDelays" arrays above based on the X and Y received.  I'm making each group of four represent one note, and then calculating the actual MIDI note based on that:

```
		int velX = x / 2;
		int velY = y / 2;
		int midiNote = 31 + velX + (velY * (this.monome.sizeY));
```

Now if the value is 1 (if this is a press), we add 32 to the velocities array.  This makes each button worth 32 velocity out of a total of about 128 (127 actually).  Next we check if the padDelay object for this group (velX/velY) exists or is complete.  A MIDINoteDelay is an object that delays a MIDI note for a period of time.  The velocity can also be adjusted until that period of time is up, at which point the note is sent.  So, if the current pad delay for this group doesn't exist (is null) or is complete (the note has been sent and it's done delaying), we'll create a new one and start it.  After that we'll set the velocity for the current MIDINoteDelay to our newly calculated velocity:

```
			if (padDelays[velX][velY] == null || padDelays[velX][velY].isComplete()) {
				padDelays[velX][velY] = new PadDelay(monome, index, 5, midiNote);
				new Thread(padDelays[velX][velY]).start();
			}
			padDelays[velX][velY].setVelocity(velocities[velX][velY]);
```

This covers it for the "user pressed a button case".  Now let's handle the "user released a button" case.  First, we'll decrement the velocity for this group by 32.  If the velocity is now 0 (no more buttons in this group are held) we will send a MIDI Note Off message over any selected MIDI devices.  It's a decent chunk of code but that's all it's really doing:

```
		} else {
			velocities[velX][velY] -= 32;
			if (velocities[velX][velY] == 0) {
				try {
					ShortMessage midiMsg = new ShortMessage();
					midiMsg.setMessage(ShortMessage.NOTE_OFF, 0, midiNote, 0);
					monome.sendMidi(midiMsg, index);
				} catch (InvalidMidiDataException e) {
					e.printStackTrace();
				}
			}
		}
```

That's it for handlePress.  The only piece left is the MIDINoteDelay object itself.  I decided to implement it right in the MIDIPadsPage.java file.  Here's the complete code:

```
	public class MIDINoteDelay implements Runnable {
		private int delayTime;
		private int pageIndex;
		private int midiNote;
		private int velocity;
		private boolean complete;
		private MonomeConfiguration monome;
		
		public MIDINoteDelay(MonomeConfiguration monome, int pageIndex, int delayTime, int midiNote) {
			this.monome = monome;
			this.delayTime = delayTime;
			this.pageIndex = pageIndex;
			this.midiNote = midiNote;
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
				if (velocity > 127) {
					velocity = 127;
				}
				ShortMessage midiMsg = new ShortMessage();
				midiMsg.setMessage(ShortMessage.NOTE_ON, 0, midiNote, velocity);
				monome.sendMidi(midiMsg, index);
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (InvalidMidiDataException e) {
				e.printStackTrace();
			}
			this.complete = true;
		}
	}
```

First, the standard object setup and member variables.  delayTime is how long to delay before sending the note, pageIndex is the current MIDIPadsPage's index, midiNote is the midi note number to send, velocity is the velocity to send it at, complete represents if the note has been sent and this is now complete or not, and the monome object is used for sending out the MIDI message.  We initialize most of these via the constructor:

```
	public class MIDINoteDelay implements Runnable {
		private int delayTime;
		private int pageIndex;
		private int midiNote;
		private int velocity;
		private boolean complete;
		private MonomeConfiguration monome;
		
		public MIDINoteDelay(MonomeConfiguration monome, int pageIndex, int delayTime, int midiNote) {
			this.monome = monome;
			this.delayTime = delayTime;
			this.pageIndex = pageIndex;
			this.midiNote = midiNote;
			this.complete = false;
			this.velocity = 0;
		}
```

Next are two utility methods meant to be called from the MIDIPadsPage class itself.  The first sets the velocity that will eventually be sent and the second checks if this object is complete yet:

```
		public void setVelocity(int velocity) {
			this.velocity = velocity;
		}
		
		public boolean isComplete() {
			return this.complete;
		}
```

Next is the run() method.  This is the method that does the actual work and is what gets executed when start() is called.  The first thing we do is delay the specified delayTime (Thread.sleep()).  Next we initialize a MIDI message and make sure the velocity isn't over 127.  Then we simply send the MIDI message out to all activated MIDI out ports:

```
		public void run() {
			try {
				Thread.sleep(delayTime);
				if (velocity > 127) {
					velocity = 127;
				}
				ShortMessage midiMsg = new ShortMessage();
				midiMsg.setMessage(ShortMessage.NOTE_ON, 0, midiNote, velocity);
				monome.sendMidi(midiMsg, index);
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (InvalidMidiDataException e) {
				e.printStackTrace();
			}
			this.complete = true;
		}
```

That's it, the page is now fully functional, can be created, and can be saved into configurations.  The last thing we want to do is actually give it some configuration options.  It would be nice if you could configure the following things:

  1. The MIDI start note number (right now it's hard coded to 31).
  1. The amount of velocity each button represents (right now it's hard coded to 32)
  1. The delay time before the MIDI note is sent out (right now it's hard coded to 5ms).
  1. The MIDI channel that is used (right now it's hard coded to 0--this is actually MIDI channel 1).

There's other things you could come up with but this is enough for the tutorial.  First let's create member variables in MIDIPadsPage.java to store these values.  They're all integers and pretty straightforward.  Let's also initialize them in the constructor to the defaults above:

```
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
	
	public void setVelocityFactor(int velocityFactor) {
		this.velocityFactor = velocityFactor;
		this.gui.setVelocityFactor(velocityFactor);
	}
	
	public void setDelayTime(int delayTime) {
		this.delayTime = delayTime;
		this.gui.setDelayTime(delayTime);
	}
	
	public void setMidiChannel(int midiChannel) {
		this.midiChannel = midiChannel;
		this.gui.setMidiChannel(midiChannel + 1);
	}
```

You probably have a bunch of errors right now, don't worry about them at the moment.  You also may be wondering why I created set methods for all of these variables -- this is because they're going to be called from multiple places, and I want both the GUI and the page's internal state to be updated, so this saves a bit of code.

Next let's define what our XML is going to look like in the configuration file:

```
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
```

This will now get saved to the configuration file.  We need to do something with this information when we load it, so let's implement the configure() method:

```
	public void configure(Element pageElement) {		
		this.setName(monome.readConfigValue(pageElement, "pageName"));
		this.setMidiStartNote(Integer.parseInt(monome.readConfigValue(pageElement, "midiStartNote")));
		this.setVelocityFactor(Integer.parseInt(monome.readConfigValue(pageElement, "velocityFactor")));
		this.setDelayTime(Integer.parseInt(monome.readConfigValue(pageElement, "delayTime")));
		this.setMidiChannel(Integer.parseInt(monome.readConfigValue(pageElement, "midiChannel")));
	}
```

These properties will now be saved and loaded from the configuration file.  We don't have anywhere on the GUI to see them or update them though so let's build that part.  Ignore the errors in MIDIPadsPage for now and open up MIDIPadsGUI.java in Visual Editor.  Create a label and name it midiStartLBL.  Place it in the GUI and set its text to "MIDI Start Note".  Now create a JTextField and name it midiStartTF.

Repeat this process for the other 3 attributes: velocityFactorLBL/TF, delayTimeLBL/TF, and midiChannelLBL/TF.

Now create a JButton named updatePrefsBtn, place it below everything, make it big and set its text to "Update Preferences".  We'll make this button control updating these settings.  Right click on the button and choose Events -> actionPerformed.  This will create a stub method in the code that will be called whenever the button is clicked.

When the button is clicked we want to update the MIDIPadsPage with the values contained in the text fields.  The values in the fields are String objects so we need to convert them to Integers and make sure they're valid before sending them over to the page:

```
	private JButton getUpdatePrefsBtn() {
		if (updatePrefsBtn == null) {
			updatePrefsBtn = new JButton();
			updatePrefsBtn.setBounds(new Rectangle(10, 145, 161, 21));
			updatePrefsBtn.setText("Update Preferences");
			updatePrefsBtn.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					try {
						int midiStartNote = Integer.parseInt(midiStartTF.getText()); 
						int velocityFactor = Integer.parseInt(velocityFactorTF.getText());
						int delayTime = Integer.parseInt(delayTimeTF.getText());
						int midiChannel = Integer.parseInt(midiChannelTF.getText()) - 1;
						if (midiChannel < 0 || midiChannel > 15) {
							midiChannel = 0;
						}
						page.setMidiStartNote(midiStartNote);
						page.setVelocityFactor(velocityFactor);
						page.setDelayTime(delayTime);
						page.setMidiChannel(midiChannel);
					} catch (NumberFormatException ex) {
						ex.printStackTrace();
					}
				}
			});
		}
		return updatePrefsBtn;
	}
```

Now let's make these new parameters actually modify the behavior of things.  For MIDI start note, make the following change:

```
int midiNote = 31+ velX + (velY * (this.monome.sizeY));
```
to
```
int midiNote = midiStartNote + velX + (velY * (this.monome.sizeY));
```

For velocity factor:

```
velocities[velX][velY] += 32;
```
to
```
velocities[velX][velY] += velocityFactor;
```

and

```
velocities[velX][velY] -= 32;
```
to
```
velocities[velX][velY] -= velocityFactor;
```

For delay time:

```
midiNoteDelays[velX][velY] = new MIDINoteDelay(monome, index, 5, midiNote);
```
to
```
midiNoteDelays[velX][velY] = new MIDINoteDelay(monome, index, delayTime, midiNote);
```

For MIDI channel, woops I didn't plan this well, we don't have a way to send it in yet.  Let's add it as an argument to the MIDINoteDelay constructor:

```
midiNoteDelays[velX][velY] = new MIDINoteDelay(monome, index, delayTime, midiNote, midiChannel);
...
midiMsg.setMessage(ShortMessage.NOTE_OFF, midiChannel, midiNote, 0);
```

and in the MIDINoteDelay class:

```
private int midiChannel;
...
public MIDINoteDelay(MonomeConfiguration monome, int pageIndex, int delayTime, int midiNote, int midiChannel) {
...
this.midiChannel = midiChannel;
...
midiMsg.setMessage(ShortMessage.NOTE_ON, midiChannel, midiNote, velocity);
```

The only thing left is to implement the setMidiStartNote, setDelayTime, setMidiChannel, and setVelocityFactor methods in the MIDIPadsGUI.java file.  You can hover over the errors in MIDIPadsPage.java and choose "Create method..." to quickly create stub methods for each of these.  Finally, implement the stub methods to update the text field values when called:

```
	public void setMidiStartNote(int midiStartNote) {
		midiStartTF.setText("" + midiStartNote);
	}

	public void setDelayTime(int delayTime) {
		delayTimeTF.setText("" + delayTime);
	}

	public void setMidiChannel(int midiChannel) {
		midiChannelTF.setText("" + midiChannel);
	}

	public void setVelocityFactor(int velocityFactor) {
		velocityFactorTF.setText("" + velocityFactor);
	}
```

That's it, fully working page with save/load, GUI, and everything.