# Page Description #

The MIDI Sequencer page is a basic MIDI clock driven step sequencer.

## Sequence Mode ##

### 64 Layout ###

| grid | grid | grid | grid | grid | grid | grid | grid |
|:-----|:-----|:-----|:-----|:-----|:-----|:-----|:-----|
| grid | grid | grid | grid | grid | grid | grid | grid |
| grid | grid | grid | grid | grid | grid | grid | grid |
| grid | grid | grid | grid | grid | grid | grid | grid |
| grid | grid | grid | grid | grid | grid | grid | grid |
| grid | grid | grid | grid | grid | grid | grid | grid |
| grid | grid | grid | grid | grid | grid | grid | grid |
| pat1 | pat2 | pat3 | pat4 | copy | clr  | bkmd | null |

### 128 Layout ###

| grid | grid | grid | grid | grid | grid | grid | grid | grid | grid | grid | grid | grid | grid | grid | grid |
|:-----|:-----|:-----|:-----|:-----|:-----|:-----|:-----|:-----|:-----|:-----|:-----|:-----|:-----|:-----|:-----|
| grid | grid | grid | grid | grid | grid | grid | grid | grid | grid | grid | grid | grid | grid | grid | grid |
| grid | grid | grid | grid | grid | grid | grid | grid | grid | grid | grid | grid | grid | grid | grid | grid |
| grid | grid | grid | grid | grid | grid | grid | grid | grid | grid | grid | grid | grid | grid | grid | grid |
| grid | grid | grid | grid | grid | grid | grid | grid | grid | grid | grid | grid | grid | grid | grid | grid |
| grid | grid | grid | grid | grid | grid | grid | grid | grid | grid | grid | grid | grid | grid | grid | grid |
| grid | grid | grid | grid | grid | grid | grid | grid | grid | grid | grid | grid | grid | grid | grid | grid |
| pat1 | pat2 | null | null | copy | clr  | bkmd | null | null | null | null | null | null | null | null | null |

### 256 Layout ###

| grid | grid | grid | grid | grid | grid | grid | grid | grid | grid | grid | grid | grid | grid | grid | grid |
|:-----|:-----|:-----|:-----|:-----|:-----|:-----|:-----|:-----|:-----|:-----|:-----|:-----|:-----|:-----|:-----|
| grid | grid | grid | grid | grid | grid | grid | grid | grid | grid | grid | grid | grid | grid | grid | grid |
| grid | grid | grid | grid | grid | grid | grid | grid | grid | grid | grid | grid | grid | grid | grid | grid |
| grid | grid | grid | grid | grid | grid | grid | grid | grid | grid | grid | grid | grid | grid | grid | grid |
| grid | grid | grid | grid | grid | grid | grid | grid | grid | grid | grid | grid | grid | grid | grid | grid |
| grid | grid | grid | grid | grid | grid | grid | grid | grid | grid | grid | grid | grid | grid | grid | grid |
| grid | grid | grid | grid | grid | grid | grid | grid | grid | grid | grid | grid | grid | grid | grid | grid |
| grid | grid | grid | grid | grid | grid | grid | grid | grid | grid | grid | grid | grid | grid | grid | grid |
| grid | grid | grid | grid | grid | grid | grid | grid | grid | grid | grid | grid | grid | grid | grid | grid |
| grid | grid | grid | grid | grid | grid | grid | grid | grid | grid | grid | grid | grid | grid | grid | grid |
| grid | grid | grid | grid | grid | grid | grid | grid | grid | grid | grid | grid | grid | grid | grid | grid |
| grid | grid | grid | grid | grid | grid | grid | grid | grid | grid | grid | grid | grid | grid | grid | grid |
| grid | grid | grid | grid | grid | grid | grid | grid | grid | grid | grid | grid | grid | grid | grid | grid |
| grid | grid | grid | grid | grid | grid | grid | grid | grid | grid | grid | grid | grid | grid | grid | grid |
| grid | grid | grid | grid | grid | grid | grid | grid | grid | grid | grid | grid | grid | grid | grid | grid |
| pat1 | pat2 | null | null | copy | clr  | bkmd | null | null | null | null | null | null | null | null | null |


The grid buttons are used to input sequences.  Each row of grid buttons represents one instrument.  A vertical bar will travel across the grid and trigger midi notes for each enabled grid button.

The bottom row is used for control.  The pat1, pat2, pat3, and pat4 buttons switch the selected pattern.  The sequencer will move from pat1 to pat2, pat3, and pat4, and back around to pat1.  On the 128 and 256, there are only 2 patterns of 16 steps each.

The copy button is used to copy the contents of a pattern to another pattern.  When the button is pressed, copy mode is enabled.  The currently selected pattern will be copied to the next pattern button that is pressed.  For example, if pat1 is selected, copy mode is enabled, and pat2 is pressed, pattern 1 will be copied to pattern 2.

The clr button is used to clear a pattern.  Pushing it will enable clear mode, and the next patX button pressed will have its corresponding pattern cleared.

The bkmd button toggles bank mode which changes the function of all the buttons when enabled.  Bank mode is described in the section below.

Null buttons don't do anything.  Sorry.

## Bank Mode ##

### 64 Layout ###

| bank | bank | bank | bank | bank | bank | bank | bank |
|:-----|:-----|:-----|:-----|:-----|:-----|:-----|:-----|
| bank | bank | bank | bank | bank | bank | bank | bank |
| bank | bank | bank | bank | bank | bank | bank | bank |
| bank | bank | bank | bank | bank | bank | bank | bank |
| bank | bank | bank | bank | bank | bank | bank | bank |
| bank | bank | bank | bank | bank | bank | bank | bank |
| bank | bank | bank | bank | bank | bank | bank | bank |
| off1 | off2 | genp | altp | copy | clr  | bkmd | page |

### 128 Layout ###

| bank | bank | bank | bank | bank | bank | bank | bank | bank | bank | bank | bank | bank | bank | bank | bank |
|:-----|:-----|:-----|:-----|:-----|:-----|:-----|:-----|:-----|:-----|:-----|:-----|:-----|:-----|:-----|:-----|
| bank | bank | bank | bank | bank | bank | bank | bank | bank | bank | bank | bank | bank | bank | bank | bank |
| bank | bank | bank | bank | bank | bank | bank | bank | bank | bank | bank | bank | bank | bank | bank | bank |
| bank | bank | bank | bank | bank | bank | bank | bank | bank | bank | bank | bank | bank | bank | bank | bank |
| bank | bank | bank | bank | bank | bank | bank | bank | bank | bank | bank | bank | bank | bank | bank | bank |
| bank | bank | bank | bank | bank | bank | bank | bank | bank | bank | bank | bank | bank | bank | bank | bank |
| bank | bank | bank | bank | bank | bank | bank | bank | bank | bank | bank | bank | bank | bank | bank | bank |
| off1 | off2 | genp | altp | copy | clr  | bkmd | null | null | null | null | null | null | null | null | null |

### 256 Layout ###

| bank | bank | bank | bank | bank | bank | bank | bank | bank | bank | bank | bank | bank | bank | bank | bank |
|:-----|:-----|:-----|:-----|:-----|:-----|:-----|:-----|:-----|:-----|:-----|:-----|:-----|:-----|:-----|:-----|
| bank | bank | bank | bank | bank | bank | bank | bank | bank | bank | bank | bank | bank | bank | bank | bank |
| bank | bank | bank | bank | bank | bank | bank | bank | bank | bank | bank | bank | bank | bank | bank | bank |
| bank | bank | bank | bank | bank | bank | bank | bank | bank | bank | bank | bank | bank | bank | bank | bank |
| bank | bank | bank | bank | bank | bank | bank | bank | bank | bank | bank | bank | bank | bank | bank | bank |
| bank | bank | bank | bank | bank | bank | bank | bank | bank | bank | bank | bank | bank | bank | bank | bank |
| bank | bank | bank | bank | bank | bank | bank | bank | bank | bank | bank | bank | bank | bank | bank | bank |
| bank | bank | bank | bank | bank | bank | bank | bank | bank | bank | bank | bank | bank | bank | bank | bank |
| bank | bank | bank | bank | bank | bank | bank | bank | bank | bank | bank | bank | bank | bank | bank | bank |
| bank | bank | bank | bank | bank | bank | bank | bank | bank | bank | bank | bank | bank | bank | bank | bank |
| bank | bank | bank | bank | bank | bank | bank | bank | bank | bank | bank | bank | bank | bank | bank | bank |
| bank | bank | bank | bank | bank | bank | bank | bank | bank | bank | bank | bank | bank | bank | bank | bank |
| bank | bank | bank | bank | bank | bank | bank | bank | bank | bank | bank | bank | bank | bank | bank | bank |
| bank | bank | bank | bank | bank | bank | bank | bank | bank | bank | bank | bank | bank | bank | bank | bank |
| bank | bank | bank | bank | bank | bank | bank | bank | bank | bank | bank | bank | bank | bank | bank | bank |
| null | null | genp | altp | copy | clr  | bkmd | null | null | null | null | null | null | null | null | null |

The bank buttons are used to select banks.  Each bank contains four patterns of 8 steps each on a 64/40h or 16 steps each on a 128/256 (the sequence view represents one pattern).

The off1 and off2 buttons represent the instrument offset on the 64 and 128 (they don't exist on the 256).  By default, off1 will be selected, but pressing off2 will change the rows on the sequencer view to correspond to the next 7 rows/instruments/MIDI notes.  This lets you sequence up to 14 instruments on a 64. 256 allows for 15 instruments with no offsetting required.

The genp button is the pattern generator and it will generate a pseudo-random pattern on the currently selected bank.  All existing patterns in the selected bank will be overwritten so use with caution.

The altp button will alter the current pattern somewhat (ie. randomly disable and enable steps).

The copy button enables bank copy mode.  The currently selected bank will be copied to the next bank button that is pressed when bank copy mode is enabled.

The clr button enables bank clear mode.  The next bank button that is pressed will be cleared when bank clear mode is enabled.

The bkmd button switches back to sequence mode.