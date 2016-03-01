# Page Description #

The External Application page allows external applications to be routed through pages.  When you create an external application page you'll be able to configure the prefix, hostname, OSC in port and OSC out port.  Note that these settings correspond to the ports and prefix that the -application- is communicating on, not your monome.

Each external application should use its own set of OSC in and OSC out ports.  How you configure these in the application will depend on the application itself.  You should contact the application's author if you're having trouble reconfiguring these ports.

This page may take some trial and error to configure correctly.

## Quick Setup for mlr ##

I generally use the AES edit of mlr, which contains rewire support and 2 additional groups.  It integrates nicely with Ableton Live and works well with pages.  The way I configure it is as follows:

  1. Set Monome Serial set to ports 7000 / 7070 and prefix /40h (for my 40h).  This frees up mlr and the external application page to use 8000 / 8080, which are mlr's defaults.
  1. Launch Ableton Live so it is the rewire master.
  1. Launch Max/MSP and load mlr.  Set the audio output to ad\_rewire.
  1. Record enable an audio track in Ableton and select Max as the audio input device.
  1. Check Monome Serial because my version of mlr will change the monome prefix to '/mlr' in Monome Serial.  If yours does this as well then make sure you set the prefix back to normal in Monome Serial.
  1. Create an external application page.  The default settings should work: prefix /mlr, hostname localhost, OSC in port 8080, OSC out port 8000.
  1. Click on Update Preferences, you should now be able to use mlr on this page.  You should also be able to switch to a different page and switch back to mlr at any time.