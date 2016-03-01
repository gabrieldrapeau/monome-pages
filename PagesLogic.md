1. Start monomeserial

2. Go to Audio Midi preferences and make sure IAC device is online

![http://www.post-digital.net/~phortran/pages/pages-logic/1-iac.jpg](http://www.post-digital.net/~phortran/pages/pages-logic/1-iac.jpg)

3. Start Pages - set up a new configuration, choose IAC bus as output and input. (At this point I also normally add the pages that I want but this can be done later - as an example though I will set up 3 midi sequencer pages transmitting midi on 3 different channels)

4. Start Logic. In a new project, you need to go to the recording settings page (File - Project Settings or click the settings button top left of arrange page) In the midi tab make sure 'Auto Demix by Channel if Multitrack Recording' is ticked. This allows you to record arm multiple instrument tracks at the same time and send midi to them on different channels.

![http://www.post-digital.net/~phortran/pages/pages-logic/2-record_settings.jpg](http://www.post-digital.net/~phortran/pages/pages-logic/2-record_settings.jpg)

5. Next go to the Synchronization page, choose the MIDI tab, and where it says 'transmit midi clock' make sure 'destination 1' is ticked, and select IAC bus 1 (or whichever you are using) from the drop down menu. This sends clock to the monome so you get the timeline movement in midi sequencer pages.

![http://www.post-digital.net/~phortran/pages/pages-logic/3-project_settings.jpg](http://www.post-digital.net/~phortran/pages/pages-logic/3-project_settings.jpg)

6. Next (using 3 midi sequencer pages as an example) set up 3 virtual instrument tracks, and choose the relevant midi input channel for each one (say channels 1 thru 3). Put your choice of instruments on each track (Drums/Bass/Lead?) and set a 2 bar loop in logics arrange window. Hit play and you should see the timeline scroll across the monome (remember that if you are on a 64 there are up to 4 pages per sequence, so it will disappear and reappear!).

7. If you want to record the midi output of pages, turn loop off in Logic, drop it in to record making sure all channels are record armed, and it will record all midi parts on the relevant tracks.

Don’t forget you may need to edit the midi notes of sequencer pages to control drum machines. Also remember to save your logic project and your pages configuration (I try to give them both the same name for convenience).

If you want to use the fader’s page, you can easily set this up per instrument as well. Add a faders page, choose the relevant midi channel, and either use midi learn on the virtual instrument, or 'apple L' for controller assignments in logic.