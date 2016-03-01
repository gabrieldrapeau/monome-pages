# Page Description #

The Ableton Clip Launcher page uses the LiveOSC project to communicate with Ableton Live.  Setup of LiveOSC is described in the document below.  Note that at the time of this writing LiveOSC is only available for Windows users.

## 64 Layout ##

| clip | clip | clip | clip | clip | clip | clip | pvco |
|:-----|:-----|:-----|:-----|:-----|:-----|:-----|:-----|
| clip | clip | clip | clip | clip | clip | clip | nxco |
| clip | clip | clip | clip | clip | clip | clip | pvtk |
| clip | clip | clip | clip | clip | clip | clip | nxtk |
| clip | clip | clip | clip | clip | clip | clip | -bpm |
| clip | clip | clip | clip | clip | clip | clip | +bpm |
| rec1 | rec2 | rec3 | rec4 | rec5 | rec6 | rec7 | ovdb |
| stp1 | stp2 | stp3 | stp4 | stp5 | stp6 | stp7 | undo |

## 128 Layout ##

| clip | clip | clip | clip | clip | clip | clip | clip | clip | clip | clip | clip | clip | clip | clip | pvco |
|:-----|:-----|:-----|:-----|:-----|:-----|:-----|:-----|:-----|:-----|:-----|:-----|:-----|:-----|:-----|:-----|
| clip | clip | clip | clip | clip | clip | clip | clip | clip | clip | clip | clip | clip | clip | clip | nxco |
| clip | clip | clip | clip | clip | clip | clip | clip | clip | clip | clip | clip | clip | clip | clip | pvtk |
| clip | clip | clip | clip | clip | clip | clip | clip | clip | clip | clip | clip | clip | clip | clip | nxtk |
| clip | clip | clip | clip | clip | clip | clip | clip | clip | clip | clip | clip | clip | clip | clip | -bpm |
| clip | clip | clip | clip | clip | clip | clip | clip | clip | clip | clip | clip | clip | clip | clip | +bpm |
| rec1 | rec2 | rec3 | rec4 | rec5 | rec6 | rec7 | rec8 | rec9 | rc10 | rc11 | rc12 | rc13 | rc14 | rc15 | ovdb |
| stp1 | stp2 | stp3 | stp4 | stp5 | stp6 | stp7 | stp8 | stp9 | st10 | st11 | st12 | st13 | st14 | st15 | undo |

## 256 Layout ##

| clip | clip | clip | clip | clip | clip | clip | clip | clip | clip | clip | clip | clip | clip | clip | pvco |
|:-----|:-----|:-----|:-----|:-----|:-----|:-----|:-----|:-----|:-----|:-----|:-----|:-----|:-----|:-----|:-----|
| clip | clip | clip | clip | clip | clip | clip | clip | clip | clip | clip | clip | clip | clip | clip | nxco |
| clip | clip | clip | clip | clip | clip | clip | clip | clip | clip | clip | clip | clip | clip | clip | pvtk |
| clip | clip | clip | clip | clip | clip | clip | clip | clip | clip | clip | clip | clip | clip | clip | nxtk |
| clip | clip | clip | clip | clip | clip | clip | clip | clip | clip | clip | clip | clip | clip | clip | -bpm |
| clip | clip | clip | clip | clip | clip | clip | clip | clip | clip | clip | clip | clip | clip | clip | +bpm |
| clip | clip | clip | clip | clip | clip | clip | clip | clip | clip | clip | clip | clip | clip | clip | ovdb |
| clip | clip | clip | clip | clip | clip | clip | clip | clip | clip | clip | clip | clip | clip | clip | undo |
| clip | clip | clip | clip | clip | clip | clip | clip | clip | clip | clip | clip | clip | clip | clip | null |
| clip | clip | clip | clip | clip | clip | clip | clip | clip | clip | clip | clip | clip | clip | clip | null |
| clip | clip | clip | clip | clip | clip | clip | clip | clip | clip | clip | clip | clip | clip | clip | null |
| clip | clip | clip | clip | clip | clip | clip | clip | clip | clip | clip | clip | clip | clip | clip | null |
| clip | clip | clip | clip | clip | clip | clip | clip | clip | clip | clip | clip | clip | clip | clip | null |
| clip | clip | clip | clip | clip | clip | clip | clip | clip | clip | clip | clip | clip | clip | clip | null |
| rec1 | rec2 | rec3 | rec4 | rec5 | rec6 | rec7 | rec8 | rec9 | rc10 | rc11 | rc12 | rc13 | rc14 | rc15 | null |
| stp1 | stp2 | stp3 | stp4 | stp5 | stp6 | stp7 | stp8 | stp9 | st10 | st11 | st12 | st13 | st14 | st15 | null |

Each column represents one track in Ableton Live's session view.  The clip buttons trigger clips from top to bottom in the track.  The recX buttons arm or disarm their associated track for recording.  The stpX buttons send a stop signal to their associated track.

The pvco and nxco buttons switch to the previous clip offset or the next clip offset.  Each clip offset shows you a particular slice of the Ableton session view (from top to bottom).  These buttons move your view up and down the Ableton clip matrix.

The pvtk and nxtk buttons switch to the previous track offset or the next track offset.  Each track offset shows you a particular slice of the Ableton session view (from left to right).  These buttons move your view left and right in the Ableton clip matrix.

The -bpm and +bpm buttons subtract 1 or add 1 to the current tempo.

The ovdb button toggles overdub mode on and off in Ableton.

The undo button performs the undo action in Ableton.

# Recording #

You can record audio and MIDI clips on the fly by record enabling the track and triggering a clip slot that doesn't have a clip yet (the monome button isn't lit).  This will create a new clip and start recording the audio input into it.