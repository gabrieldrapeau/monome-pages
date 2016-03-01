# Page Description #

The Ableton Clip Skipper page uses LiveOSC to move the current play position back and forth within currently playing clips.  Each row of the monome represents the currently playing clip in an Ableton track.  The LEDs will move from left to right as the clip plays, and pushing a button on the clip's row will jump to (roughly) that position in the clip.  Row 1 is equivalent to track 1, row 2 to track 2, etc.  The tracks/clips can be audio or MIDI.  This page requires MIDI clock signal to work properly, so be sure to enable it (select a MIDI input device in Pages and send MIDI clock from Ableton or another source to that MIDI input device).

Due to the limited information available via LiveAPI, the position of the clip is an approximation based on the MIDI clock signal, the length of the looped area in the clip, and the time when the clip was first discovered to be playing.  The synchronization will eventually fall off a bit, but stopping the clip and restarting it or starting another clip on the same track will reset the position.

See the AbletonClipLauncherPage wiki page for information on setting up LiveAPI/LiveOSC.

## 64 Layout ##

| row1 | row1 | row1 | row1 | row1 | row1 | row1 | row1 |
|:-----|:-----|:-----|:-----|:-----|:-----|:-----|:-----|
| row2 | row2 | row2 | row2 | row2 | row2 | row2 | row2 |
| row3 | row3 | row3 | row3 | row3 | row3 | row3 | row3 |
| row4 | row4 | row4 | row4 | row4 | row4 | row4 | row4 |
| row5 | row5 | row5 | row5 | row5 | row5 | row5 | row5 |
| row6 | row6 | row6 | row6 | row6 | row6 | row6 | row6 |
| row7 | row7 | row7 | row7 | row7 | row7 | row7 | row7 |
| row8 | row8 | row8 | row8 | row8 | row8 | row8 | row8 |

## 128 Layout ##

| row1 | row1 | row1 | row1 | row1 | row1 | row1 | row1 | row1 | row1 | row1 | row1 | row1 | row1 | row1 | row1 |
|:-----|:-----|:-----|:-----|:-----|:-----|:-----|:-----|:-----|:-----|:-----|:-----|:-----|:-----|:-----|:-----|
| row2 | row2 | row2 | row2 | row2 | row2 | row2 | row2 | row2 | row2 | row2 | row2 | row2 | row2 | row2 | row2 |
| row3 | row3 | row3 | row3 | row3 | row3 | row3 | row3 | row3 | row3 | row3 | row3 | row3 | row3 | row3 | row3 |
| row4 | row4 | row4 | row4 | row4 | row4 | row4 | row4 | row4 | row4 | row4 | row4 | row4 | row4 | row4 | row4 |
| row5 | row5 | row5 | row5 | row5 | row5 | row5 | row5 | row5 | row5 | row5 | row5 | row5 | row5 | row5 | row5 |
| row6 | row6 | row6 | row6 | row6 | row6 | row6 | row6 | row6 | row6 | row6 | row6 | row6 | row6 | row6 | row6 |
| row7 | row7 | row7 | row7 | row7 | row7 | row7 | row7 | row7 | row7 | row7 | row7 | row7 | row7 | row7 | row7 |
| row8 | row8 | row8 | row8 | row8 | row8 | row8 | row8 | row8 | row8 | row8 | row8 | row8 | row8 | row8 | row8 |

## 256 Layout ##

| row1 | row1 | row1 | row1 | row1 | row1 | row1 | row1 | row1 | row1 | row1 | row1 | row1 | row1 | row1 | row1 |
|:-----|:-----|:-----|:-----|:-----|:-----|:-----|:-----|:-----|:-----|:-----|:-----|:-----|:-----|:-----|:-----|
| row2 | row2 | row2 | row2 | row2 | row2 | row2 | row2 | row2 | row2 | row2 | row2 | row2 | row2 | row2 | row2 |
| row3 | row3 | row3 | row3 | row3 | row3 | row3 | row3 | row3 | row3 | row3 | row3 | row3 | row3 | row3 | row3 |
| row4 | row4 | row4 | row4 | row4 | row4 | row4 | row4 | row4 | row4 | row4 | row4 | row4 | row4 | row4 | row4 |
| row5 | row5 | row5 | row5 | row5 | row5 | row5 | row5 | row5 | row5 | row5 | row5 | row5 | row5 | row5 | row5 |
| row6 | row6 | row6 | row6 | row6 | row6 | row6 | row6 | row6 | row6 | row6 | row6 | row6 | row6 | row6 | row6 |
| row7 | row7 | row7 | row7 | row7 | row7 | row7 | row7 | row7 | row7 | row7 | row7 | row7 | row7 | row7 | row7 |
| row8 | row8 | row8 | row8 | row8 | row8 | row8 | row8 | row8 | row8 | row8 | row8 | row8 | row8 | row8 | row8 |
| row9 | row9 | row9 | row9 | row9 | row9 | row9 | row9 | row9 | row9 | row9 | row9 | row9 | row9 | row9 | row9 |
| ro10 | ro10 | ro10 | ro10 | ro10 | ro10 | ro10 | ro10 | ro10 | ro10 | ro10 | ro10 | ro10 | ro10 | ro10 | ro10 |
| ro11 | ro11 | ro11 | ro11 | ro11 | ro11 | ro11 | ro11 | ro11 | ro11 | ro11 | ro11 | ro11 | ro11 | ro11 | ro11 |
| ro12 | ro12 | ro12 | ro12 | ro12 | ro12 | ro12 | ro12 | ro12 | ro12 | ro12 | ro12 | ro12 | ro12 | ro12 | ro12 |
| ro13 | ro13 | ro13 | ro13 | ro13 | ro13 | ro13 | ro13 | ro13 | ro13 | ro13 | ro13 | ro13 | ro13 | ro13 | ro13 |
| ro14 | ro14 | ro14 | ro14 | ro14 | ro14 | ro14 | ro14 | ro14 | ro14 | ro14 | ro14 | ro14 | ro14 | ro14 | ro14 |
| ro15 | ro15 | ro15 | ro15 | ro15 | ro15 | ro15 | ro15 | ro15 | ro15 | ro15 | ro15 | ro15 | ro15 | ro15 | ro15 |
| ro16 | ro16 | ro16 | ro16 | ro16 | ro16 | ro16 | ro16 | ro16 | ro16 | ro16 | ro16 | ro16 | ro16 | ro16 | ro16 |