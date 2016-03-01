# Page Description #

The MIDI Triggers page turns the monome into a grid of MIDI triggers/toggles.  The buttons are grouped in rows or columns, depending on the selected page mode.  Each row or column can be set as a collection of triggers or toggles.

Triggers send a note on message when a button is pressed.  They send a note off message when a button is released.  The led will stay lit as long as the button is held.  These are useful for triggering audio loops or samples, transport buttons, etc--things that are triggered.

Toggles send a note on and off message whenever they are pressed.  They will change led state from on to off each time they are pressed as well.  These are useful for triggering things that have an on/off state, for example, enabling/disabling plug-ins, arming/disarming tracks, etc--things that are toggled on and off.

The first button in the row/column sends a C-1 note on the MIDI channel = the row/column number (row/column 1 = MIDI channel 1, row/column 2 = MIDI channel 2, etc.) The next button sends C#-1, then D-1, D#-1, etc.

## 64 Layout ##

### Rows ###

| row1 | row1 | row1 | row1 | row1 | row1 | row1 | row1 |
|:-----|:-----|:-----|:-----|:-----|:-----|:-----|:-----|
| row2 | row2 | row2 | row2 | row2 | row2 | row2 | row2 |
| row3 | row3 | row3 | row3 | row3 | row3 | row3 | row3 |
| row4 | row4 | row4 | row4 | row4 | row4 | row4 | row4 |
| row5 | row5 | row5 | row5 | row5 | row5 | row5 | row5 |
| row6 | row6 | row6 | row6 | row6 | row6 | row6 | row6 |
| row7 | row7 | row7 | row7 | row7 | row7 | row7 | row7 |
| row8 | row8 | row8 | row8 | row8 | row8 | row8 | row8 |

### Columns ###

| col1 | col2 | col3 | col4 | col5 | col6 | col7 | col8 |
|:-----|:-----|:-----|:-----|:-----|:-----|:-----|:-----|
| col1 | col2 | col3 | col4 | col5 | col6 | col7 | col8 |
| col1 | col2 | col3 | col4 | col5 | col6 | col7 | col8 |
| col1 | col2 | col3 | col4 | col5 | col6 | col7 | col8 |
| col1 | col2 | col3 | col4 | col5 | col6 | col7 | col8 |
| col1 | col2 | col3 | col4 | col5 | col6 | col7 | col8 |
| col1 | col2 | col3 | col4 | col5 | col6 | col7 | col8 |

## 128 Layout ##

### Rows ###

| row1 | row1 | row1 | row1 | row1 | row1 | row1 | row1 | row1 | row1 | row1 | row1 | row1 | row1 | row1 | row1 |
|:-----|:-----|:-----|:-----|:-----|:-----|:-----|:-----|:-----|:-----|:-----|:-----|:-----|:-----|:-----|:-----|
| row2 | row2 | row2 | row2 | row2 | row2 | row2 | row2 | row2 | row2 | row2 | row2 | row2 | row2 | row2 | row2 |
| row3 | row3 | row3 | row3 | row3 | row3 | row3 | row3 | row3 | row3 | row3 | row3 | row3 | row3 | row3 | row3 |
| row4 | row4 | row4 | row4 | row4 | row4 | row4 | row4 | row4 | row4 | row4 | row4 | row4 | row4 | row4 | row4 |
| row5 | row5 | row5 | row5 | row5 | row5 | row5 | row5 | row5 | row5 | row5 | row5 | row5 | row5 | row5 | row5 |
| row6 | row6 | row6 | row6 | row6 | row6 | row6 | row6 | row6 | row6 | row6 | row6 | row6 | row6 | row6 | row6 |
| row7 | row7 | row7 | row7 | row7 | row7 | row7 | row7 | row7 | row7 | row7 | row7 | row7 | row7 | row7 | row7 |
| row8 | row8 | row8 | row8 | row8 | row8 | row8 | row8 | row8 | row8 | row8 | row8 | row8 | row8 | row8 | row8 |

### Columns ###

| col1 | col2 | col3 | col4 | col5 | col6 | col7 | col8 | col9 | co10 | co11 | co12 | co13 | co14 | co15 | co16 |
|:-----|:-----|:-----|:-----|:-----|:-----|:-----|:-----|:-----|:-----|:-----|:-----|:-----|:-----|:-----|:-----|
| col1 | col2 | col3 | col4 | col5 | col6 | col7 | col8 | col9 | co10 | co11 | co12 | co13 | co14 | co15 | co16 |
| col1 | col2 | col3 | col4 | col5 | col6 | col7 | col8 | col9 | co10 | co11 | co12 | co13 | co14 | co15 | co16 |
| col1 | col2 | col3 | col4 | col5 | col6 | col7 | col8 | col9 | co10 | co11 | co12 | co13 | co14 | co15 | co16 |
| col1 | col2 | col3 | col4 | col5 | col6 | col7 | col8 | col9 | co10 | co11 | co12 | co13 | co14 | co15 | co16 |
| col1 | col2 | col3 | col4 | col5 | col6 | col7 | col8 | col9 | co10 | co11 | co12 | co13 | co14 | co15 | co16 |
| col1 | col2 | col3 | col4 | col5 | col6 | col7 | col8 | col9 | co10 | co11 | co12 | co13 | co14 | co15 | co16 |
| col1 | col2 | col3 | col4 | col5 | col6 | col7 | col8 | col9 | co10 | co11 | co12 | co13 | co14 | co15 | co16 |

## 256 Layout ##

### Rows ###

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

### Columns ###

| col1 | col2 | col3 | col4 | col5 | col6 | col7 | col8 | col9 | co10 | co11 | co12 | co13 | co14 | co15 | co16 |
|:-----|:-----|:-----|:-----|:-----|:-----|:-----|:-----|:-----|:-----|:-----|:-----|:-----|:-----|:-----|:-----|
| col1 | col2 | col3 | col4 | col5 | col6 | col7 | col8 | col9 | co10 | co11 | co12 | co13 | co14 | co15 | co16 |
| col1 | col2 | col3 | col4 | col5 | col6 | col7 | col8 | col9 | co10 | co11 | co12 | co13 | co14 | co15 | co16 |
| col1 | col2 | col3 | col4 | col5 | col6 | col7 | col8 | col9 | co10 | co11 | co12 | co13 | co14 | co15 | co16 |
| col1 | col2 | col3 | col4 | col5 | col6 | col7 | col8 | col9 | co10 | co11 | co12 | co13 | co14 | co15 | co16 |
| col1 | col2 | col3 | col4 | col5 | col6 | col7 | col8 | col9 | co10 | co11 | co12 | co13 | co14 | co15 | co16 |
| col1 | col2 | col3 | col4 | col5 | col6 | col7 | col8 | col9 | co10 | co11 | co12 | co13 | co14 | co15 | co16 |
| col1 | col2 | col3 | col4 | col5 | col6 | col7 | col8 | col9 | co10 | co11 | co12 | co13 | co14 | co15 | co16 |
| col1 | col2 | col3 | col4 | col5 | col6 | col7 | col8 | col9 | co10 | co11 | co12 | co13 | co14 | co15 | co16 |
| col1 | col2 | col3 | col4 | col5 | col6 | col7 | col8 | col9 | co10 | co11 | co12 | co13 | co14 | co15 | co16 |
| col1 | col2 | col3 | col4 | col5 | col6 | col7 | col8 | col9 | co10 | co11 | co12 | co13 | co14 | co15 | co16 |
| col1 | col2 | col3 | col4 | col5 | col6 | col7 | col8 | col9 | co10 | co11 | co12 | co13 | co14 | co15 | co16 |
| col1 | col2 | col3 | col4 | col5 | col6 | col7 | col8 | col9 | co10 | co11 | co12 | co13 | co14 | co15 | co16 |
| col1 | col2 | col3 | col4 | col5 | col6 | col7 | col8 | col9 | co10 | co11 | co12 | co13 | co14 | co15 | co16 |
| col1 | col2 | col3 | col4 | col5 | col6 | col7 | col8 | col9 | co10 | co11 | co12 | co13 | co14 | co15 | co16 |
| col1 | col2 | col3 | col4 | col5 | col6 | col7 | col8 | col9 | co10 | co11 | co12 | co13 | co14 | co15 | co16 |