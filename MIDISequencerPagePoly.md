# Page Description #

The MIDI Sequencer page Poly is a polyphonic version of the great Midi Sequencer Page.

  * You can trigger many bank at once.
  * All fonctionnality of Midi Sequencer Page are kept: patterns recording, clear, paste... see http://code.google.com/p/monome-pages/wiki/MIDISequencerPage for further informations.
  * length, trigger mode (gate / toggle), hold mode, octave+2 mode,  and tempo\*2 can be differents for each bank


Video tutorial is here http://www.vimeo.com/3013717

## Sequence Mode ##

like Midi Sequencer Page

## Bank Mode ##

### 64 Layout ###

| bank | bank | bank | bank | bank | bank | bank | len 8 |
|:-----|:-----|:-----|:-----|:-----|:-----|:-----|:------|
| bank | bank | bank | bank | bank | bank | bank | len 16 |
| bank | bank | bank | bank | bank | bank | bank | len 32 |
| bank | bank | bank | bank | bank | bank | bank | gate / toggle |
| bank | bank | bank | bank | bank | bank | bank | hold  |
| note switch | note switch | note switch | note switch | note switch | note switch | note switch | oct +2 |
| note switch | note switch | note switch | note switch | note switch | note switch | note switch | tempo X2 |
| off1 | off2 | genp | altp | copy | clr  | bkmd | page  |

### 128 Layout (not tested) ###

| bank | bank | bank | bank | bank | bank | bank | bank | bank | bank | bank | bank | bank | bank | bank | len 8 |
|:-----|:-----|:-----|:-----|:-----|:-----|:-----|:-----|:-----|:-----|:-----|:-----|:-----|:-----|:-----|:------|
| bank | bank | bank | bank | bank | bank | bank | bank | bank | bank | bank | bank | bank | bank | bank | len 16 |
| bank | bank | bank | bank | bank | bank | bank | bank | bank | bank | bank | bank | bank | bank | bank | len 32 |
| bank | bank | bank | bank | bank | bank | bank | bank | bank | bank | bank | bank | bank | bank | bank | gate / toggle |
| bank | bank | bank | bank | bank | bank | bank | bank | bank | bank | bank | bank | bank | bank | bank | hold  |
| note switch | note switch | note switch | note switch | note switch | note switch | note switch | note switch | note switch | note switch | note switch | note switch | note switch | note switch | note switch | oct +2 |
| bank | bank | bank | bank | bank | bank | bank | bank | bank | bank | bank | bank | bank | bank | bank | tempo X2 |
| off1 | off2 | genp | altp | copy | clr  | bkmd | null | null | null | null | null | null | null | null | null  |

### 256 Layout (not tested) ###

| bank | bank | bank | bank | bank | bank | bank | bank | bank | bank | bank | bank | bank | bank | bank | len 8 |
|:-----|:-----|:-----|:-----|:-----|:-----|:-----|:-----|:-----|:-----|:-----|:-----|:-----|:-----|:-----|:------|
| bank | bank | bank | bank | bank | bank | bank | bank | bank | bank | bank | bank | bank | bank | bank | len 16 |
| bank | bank | bank | bank | bank | bank | bank | bank | bank | bank | bank | bank | bank | bank | bank | len 32 |
| bank | bank | bank | bank | bank | bank | bank | bank | bank | bank | bank | bank | bank | bank | bank | gate / toggle |
| bank | bank | bank | bank | bank | bank | bank | bank | bank | bank | bank | bank | bank | bank | bank | hold  |
| bank | bank | bank | bank | bank | bank | bank | bank | bank | bank | bank | bank | bank | bank | bank | oct +2 |
| bank | bank | bank | bank | bank | bank | bank | bank | bank | bank | bank | bank | bank | bank | bank | tempo X2 |
| bank | bank | bank | bank | bank | bank | bank | bank | bank | bank | bank | bank | bank | bank | bank | bank  |
| bank | bank | bank | bank | bank | bank | bank | bank | bank | bank | bank | bank | bank | bank | bank | bank  |
| bank | bank | bank | bank | bank | bank | bank | bank | bank | bank | bank | bank | bank | bank | bank | bank  |
| bank | bank | bank | bank | bank | bank | bank | bank | bank | bank | bank | bank | bank | bank | bank | bank  |
| bank | bank | bank | bank | bank | bank | bank | bank | bank | bank | bank | bank | bank | bank | bank | bank  |
| bank | bank | bank | bank | bank | bank | bank | bank | bank | bank | bank | bank | bank | bank | bank | bank  |
| note switch | note switch | note switch | note switch | note switch | note switch | note switch | note switch | note switch | note switch | note switch | note switch | note switch | note switch | note switch | bank  |
| bank | bank | bank | bank | bank | bank | bank | bank | bank | bank | bank | bank | bank | bank | bank | bank  |
| off1 | off2 | genp | altp | copy | clr  | bkmd | null | null | null | null | null | null | null | null | null  |


### Utilisation ###

  * len 8, len 16, len 32 allows to set length per bank.
  * gate / toggle allows to set trigger mode per bank. Light on=toggle. Light off = gate.
  * hold allows to set hold mode per bank. Light on=hold on , light off = hold off
  * oct +2 allows to add 2 octaves to midi notes. Light on=octave + 2. Light off=normal mode.
  * tempo X2 allows to  set tompo X 2 per bank. Light on=tempo X2. Light off = normal tempo.

  * note switchs are toggle buttons which switch off notes (for example for muting drum parts). Note switch are also light feedback of notes played.

  * Hold "oct +2" and "tempo X2" to see bliking active pattern
  * Hold "oct +2" and "tempo X2" and pattern to select a pattern for editing

**Exemple**
  * hold len 8 + bank to change length to 8 for pattern 1
  * hold gate / toggle + bank to change mode to gate (repeat to change to toggle mode)

Enjoy!