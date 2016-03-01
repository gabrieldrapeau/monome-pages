# Introduction #

This page allows you to create your own custom page types that interact with the Pages subsystem.  You get the benefits of Pages' built-in MIDI/OSC routing, monomeserial and serialosc protocol implementation, pattern recorders, Ableton LiveOSC integration, page changing mechanisms and multiple monome support without the hassle of coding all that junk yourself.

# Details #

There are 4 buttons on the page:

|**Save**|saves the current script to a file on your hard drive|
|:-------|:----------------------------------------------------|
|**Load**|loads a script into the text box, the script is not started automatically|
|**Log Window**|shows a log window with any script errors            |
|**Run** |starts the script running                            |
|**Stop**|stops the running script                             |

Syntax errors will prevent the script from running.  Check the log window if nothing is happening.  If an error occurs while the application is running however, the application will continue to run.

Here is the template script that loads when you create a new Groovy Page:

```
import org.monome.pages.configuration.GroovyAPI;

class GroovyTemplatePage extends GroovyAPI {

    void init() {
        println "GroovyPage starting up";
    }

    void stop() {
        println "GroovyPage shutting down";
    }

    void press(int x, int y, int val) {
        led(x, y, val);
    }

    void redraw() {
        clear(0);
        led(0, 0, 1);
        row(1, 255, 255);
        col(2, 255, 255);
    }

    void note(int num, int velo, int chan, int on) {
        noteOut(num, velo, chan, on);
    }

    void cc(int num, int val, int chan) {
        ccOut(num, val, chan);
    }

    void clock() {
        clockOut();
    }

    void clockReset() {
        clockResetOut();
    }
}
```

This example script basically echoes back all events it receives.  This is how the script works:

```
import org.monome.pages.configuration.GroovyAPI;

class GroovyTemplatePage extends GroovyAPI {
```

This is basic initialization code.  It's saying we want to use the GroovyAPI class and we want to create a new class named "GroovyTemplatePage" that extends it.  This API hooks in to the Pages event system and provides a number of utility functions.  You will want to change GroovyTemplatePage to a name of your choosing.

```
    void init() {
        println "GroovyTemplatePage starting up";
    }
```

The init() method is called when the script is first started.  This init method will print a message to the console window.  You will only see the console if running Pages via command line.

```
    void stop() {
        println "GroovyTemplatePage shutting down";
    }
```

The stop() method is called when the script is stopped with the stop button.  It's important to terminate any threads you may have spawned in the stop method -- see the MIDIXYPage.groovy file for an example.

```
    void press(int x, int y, int val) {
        led(x, y, val);
    }
```

This is the press event method.  Whenever this page receives a press, this method will be called.  In this case we're lighting up the led that is pressed.

```
    void redraw() {
        clear(0);
        led(0, 0, 1);
        row(1, 255, 255);
        col(2, 255, 255);
    }
```

The redraw() method is called whenever Pages needs to redraw the entire monome (ie. when a page is changed).  In this case we are clearing the monome, turning on a led at 0,0, drawing a row at y=1, and drawing a column at y=2.  Pages' LED-caching layer will optimize your LED messages, so keep your code simple.

```
    void note(int num, int velo, int chan, int on) {
        noteOut(num, velo, chan, on);
    }

    void cc(int num, int val, int chan) {
        ccOut(num, val, chan);
    }

    void clock() {
        clockOut();
    }

    void clockReset() {
        clockResetOut();
    }
```

These are MIDI event methods.  They will be called when an enabled MIDI input receives an event of the appropriate type.  They each echo the received messaged back to any enabled MIDI outputs.

You can also send OSC messages:

```
Object[] args = [0, 0, 1];
sendOSC("/grid/led/set", args, "localhost", 8000);
```

# Ableton #

Here's an example of a basic clip launcher page:

```
import org.monome.pages.configuration.GroovyAPI;
import org.monome.pages.ableton.AbletonTrack;
import org.monome.pages.ableton.AbletonClip;

class SimpleClipLauncherPage extends GroovyAPI {

    void init() {
        println "SimpleClipLauncherPage initialized";
    }

    void press(int x, int y, int val) {
        if (val == 1) {
            abletonOut().playClip(x, y);
        }
    }

    void redraw() {
        boolean drewLed;
        for (int x = 0; x < sizeX(); x++) {
            for (int y = 0; y < sizeY(); y++) {
                drewLed = false;
                AbletonTrack track = ableton().getTrack(x);
                if (track != null) {
                    AbletonClip clip = track.getClip(y);
                    if (clip != null) {
                        int state = clip.getState();
                        if (state > 0) {
                            led(x, y, 1);
                            drewLed = true;
                        }
                    }
                }
                if (!drewLed) {
                    led(x, y, 0);
                }
            }
        }
    }

    void note(int num, int velo, int chan, int on) {
    }

    void cc(int num, int val, int chan) {
    }

    void clock() {
    }

    void clockReset() {
    }

    public boolean redrawOnAbletonEvent() {
        return true;
    }
}
```

A few things to note:

```
import org.monome.pages.ableton.AbletonTrack;
import org.monome.pages.ableton.AbletonClip;
```

These imports will pull in Ableton objects from Pages and make them available to the script.  A full list of Ableton objects and their functionality is included below.

```
abletonOut().playClip(x, y);
```

There are two exposed Ableton objects, ableton() and abletonOut().  abletonOut() is used to send commands to Ableton Live.  In this case we're instructing it to play clip number y on track x.

```
AbletonTrack track = ableton().getTrack(x);
```

The ableton() object is used to query information about Ableton's state.  This pulls out an AbletonTrack object which contains AbletonClip objects and track information.

```
    public boolean redrawOnAbletonEvent() {
        return true;
    }
```

The redrawOnAbletonEvent() method is an interface method you can define to control when the redraw() method is called.  By default the page will not be redrawn when a new Ableton event comes in (for example, a new clip is created), but if this method is declared to return true as it is above, the page will be redrawn on an Ableton event.

Here's a full list of the Ableton objects and methods available:

## ableton() ##

ableton() will return an AbletonState object that can be used to check the current state of Ableton.  It has the following methods:

```
AbletonTrack getTrack(int x)
-- returns an AbletonTrack object

HashMap<Integer, AbletonTrack> getTracks()
-- returns a the current AbletonTracks in a HashMap

int getOverdub()
-- returns the state of the overdub button

float getTempo()
-- returns the current project tempo

int getSelectedScene()
-- returns the currently selected scene number
```

## abletonOut() ##

abletonOut() returns an AbletonControl object that can be used to send commands to Ableton.  It has the following methods:

```
void playClip(int track, int clip)
-- plays the clip on the given track number, clip/track numbers start at 0

void stopClip(int track, int clip)
-- stops the clip on the given track number

void armTrack(int track)
-- arms track

void disarmTrack(int track)
-- disarms track

void muteTrack(int track)
-- 'mutes' a track (disables it)

void unmuteTrack(int track)
-- 'unmutes' a track (enables it)

void stopTrack(int track)
-- stops the currently playing clip on track

void viewTrack(int track)
-- forces the view to select track

void trackJump(int track, float amount)
-- makes the currently playing clip in track jump by amount bars

void undo()
-- forces an undo in Ableton (ctrl-Z)

void redo()
-- forces a redo in Ableton (ctrl-Y)

void setOverdub(int state)
-- sets overdub mode off/on (0/1)

void setTempo(float tempo)
-- sets the Ableton project tempo

void launchScene(int scene)
-- launches a scene

void soloTrack(int track)
-- solos a track

void unsoloTrack(int track)
-- unsolos a track

void setSelection(int offsetX, int offsetY, int width, int height)
-- sets the red ring
```

# AbletonTrack #

An AbletonTrack object is returned from the ableton().getTrack() method.  These objects contain information about the track and clips contained within the track.

You will need to import AbletonTrack to use it:

```
import org.monome.pages.ableton.AbletonTrack;
```

The methods of an AbletonTrack object are:

```
AbletonClip getClip(int clip)
-- returns the AbletonClip in slot clip

HashMap<Integer, AbletonClip> getClips()
-- returns a HashMap of AbletonClips in this track

AbletonLooper getLooper(int looper)
-- returns the AbletonLooper device number looper on the track

HashMap<Integer, AbletonLooper> getLoopers()
-- returns a HashMap of AbletonLooper devices on the track

int getSolo()
-- gets the solo state of the track (0=off, 1=on)

int getArm()
-- gets the arm state of the track (0=off, 1=on)

int getMute()
-- gets the mute state of the track (0=off, 1=on)
```

# AbletonClip #

An AbletonClip object is returned from the AbletonTrack getClip() method.  These objects contain information about a particular clip.

You will need to import AbletonClip to use it:

```
import org.monome.pages.ableton.AbletonClip;
```

The methods of an AbletonClip object are:

```
int getState()
-- gets the state of the clipslot: 0=empty, 1=stopped, 2=playing, 3=triggered

float getLength()
-- gets the length of the clip in bars

float getPosition()
-- gets the current playhead position of the clip (UNRELIABLE)
```

# AbletonLooper #

An AbletonLooper object is returned from the AbletonTrack getLooper() method.  These objects contain information about a particular Looper device.

You will need to import AbletonLooper to use it:

```
import org.monome.pages.ableton.AbletonLooper;
```

The methods of an AbletonLooper object are:

```
int getState()
-- gets the state of the Looper: 0=stopped, 1=recording, 2=playing, 3=overdub
```