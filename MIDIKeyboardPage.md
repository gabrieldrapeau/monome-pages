# Page Description #

The MIDI Keyboard page allows the monome to be played like a MIDI Keyboard.  All notes are mapped into the selected key/scale.

## 64 Layout ##

| not1 | not2 | not3 | not4 | not5 | not6 | not7 | chn1 |
|:-----|:-----|:-----|:-----|:-----|:-----|:-----|:-----|
| not1 | not2 | not3 | not4 | not5 | not6 | not7 | chn2 |
| not1 | not2 | not3 | not4 | not5 | not6 | not7 | chn3 |
| not1 | not2 | not3 | not4 | not5 | not6 | not7 | chn4 |
| not1 | not2 | not3 | not4 | not5 | not6 | not7 | tlt1 |
| not1 | not2 | not3 | not4 | not5 | not6 | not7 | tlt2 |
| C    | D    | E    | F    | G    | A    | B    | sust |
| --   | ++   | b    | #    | scl1 | scl2 | scl3 | lock |


Each not1, not2, not3, etc. row represents the notes of the selected scale in a particular octave.

The chn1 buttons allow toggling between the midi channel that note/CC messages will be sent on.

The tlt1 and tlt2 buttons select the second and third offsets for Tilt/ADC output. So if the default CC value sent by your accelerometer is CC0/CC1/CC2/CC3 (CC2/CC3 only apply to 40h ADC) then tlt1 will send CC4/CC5/CC6/CC7 and tlt2 CC8/CC9/CC10/CC11.

The C, D, E, etc. buttons allow toggling between the selected key.  You can access the sharp and flat versions of these keys by pressing the b or # buttons.

The sust button acts like a sustain pedal sending CC 64, so if you don't have a proper one handy, you can still get that effect!

-- and ++ transpose the keyboard up and down octaves.  When pressed together they return to 0 transpose and send an all notes stop message.

scl1 - scl3 select between six scales (three flashing + three solid lit).  They can be changed using the pages GUI.  The numbers in the text fields are the semitones for each scale.  (default: Major, Natural Minor, Blues, Major pentatonic, Hungarian minor, Harmonic minor  - OK, so I like my minor scales...luckily you can use whatever sort of scale you like!)

Function lock button locks most of the 'function' keys.  Sustain, transpose +/- and the tlt buttons are still available.  To engage or disengage the function lock, you must "triple click" the button.


## 128 Layout ##

| not1 | not2 | not3 | not4 | not5 | not6 | not7 | not1 | not2 | not3 | not4 | not5 | not6 | not7 | oup  | odwn |
|:-----|:-----|:-----|:-----|:-----|:-----|:-----|:-----|:-----|:-----|:-----|:-----|:-----|:-----|:-----|:-----|
| not1 | not2 | not3 | not4 | not5 | not6 | not7 | not1 | not2 | not3 | not4 | not5 | not6 | not7 | oup  | odwn |
| not1 | not2 | not3 | not4 | not5 | not6 | not7 | not1 | not2 | not3 | not4 | not5 | not6 | not7 | oup  | odwn |
| not1 | not2 | not3 | not4 | not5 | not6 | not7 | not1 | not2 | not3 | not4 | not5 | not6 | not7 | oup  | odwn |
| not1 | not2 | not3 | not4 | not5 | not6 | not7 | not1 | not2 | not3 | not4 | not5 | not6 | not7 | oup  | odwn |
| not1 | not2 | not3 | not4 | not5 | not6 | not7 | not1 | not2 | not3 | not4 | not5 | not6 | not7 | oup  | odwn |
| not1 | not2 | not3 | not4 | not5 | not6 | not7 | not1 | not2 | not3 | not4 | not5 | not6 | not7 | oup  | odwn |
| C    | D    | E    | F    | G    | A    | B    | b    | #    | scl1 | scl2   | scl3 | scl4 | scl5 | scl6 | lock |

Each not1, not2, not3, etc. row represents the notes of the selected scale in a particular octave.  In the 128 version, each row is 2 octaves and there's an octave toggle on the far right.  Every 3 rows represents one MIDI channel, starting with MIDI channel 1.

The C, D, E, etc. buttons allow toggling between the selected key.  You can access the sharp and flat versions of these keys by pressing the b or # buttons.

scl1 - scl6 select between six scales, which can be changed using the pages GUI.  The numbers in the text fields are the semitones for each scale.  (default: Major, Natural Minor, Blues, Major pentatonic, Hungarian minor, Harmonic minor  - OK, so I like my minor scales...luckily you can use whatever sort of scale you like!)

Function lock button locks the 'function' keys.  To engage or disengage the function lock, you must "triple click" the button.

## 256 Layout ##
| not1 | not2 | not3 | not4 | not5 | not6 | not7 | not1 | not2 | not3 | not4 | not5 | not6 | not7 | oup  | odwn |
|:-----|:-----|:-----|:-----|:-----|:-----|:-----|:-----|:-----|:-----|:-----|:-----|:-----|:-----|:-----|:-----|
| not1 | not2 | not3 | not4 | not5 | not6 | not7 | not1 | not2 | not3 | not4 | not5 | not6 | not7 | oup  | odwn |
| not1 | not2 | not3 | not4 | not5 | not6 | not7 | not1 | not2 | not3 | not4 | not5 | not6 | not7 | oup  | odwn |
| not1 | not2 | not3 | not4 | not5 | not6 | not7 | not1 | not2 | not3 | not4 | not5 | not6 | not7 | oup  | odwn |
| not1 | not2 | not3 | not4 | not5 | not6 | not7 | not1 | not2 | not3 | not4 | not5 | not6 | not7 | oup  | odwn |
| not1 | not2 | not3 | not4 | not5 | not6 | not7 | not1 | not2 | not3 | not4 | not5 | not6 | not7 | oup  | odwn |
| not1 | not2 | not3 | not4 | not5 | not6 | not7 | not1 | not2 | not3 | not4 | not5 | not6 | not7 | oup  | odwn |
| not1 | not2 | not3 | not4 | not5 | not6 | not7 | not1 | not2 | not3 | not4 | not5 | not6 | not7 | oup  | odwn |
| not1 | not2 | not3 | not4 | not5 | not6 | not7 | not1 | not2 | not3 | not4 | not5 | not6 | not7 | oup  | odwn |
| not1 | not2 | not3 | not4 | not5 | not6 | not7 | not1 | not2 | not3 | not4 | not5 | not6 | not7 | oup  | odwn |
| not1 | not2 | not3 | not4 | not5 | not6 | not7 | not1 | not2 | not3 | not4 | not5 | not6 | not7 | oup  | odwn |
| not1 | not2 | not3 | not4 | not5 | not6 | not7 | not1 | not2 | not3 | not4 | not5 | not6 | not7 | oup  | odwn |
| not1 | not2 | not3 | not4 | not5 | not6 | not7 | not1 | not2 | not3 | not4 | not5 | not6 | not7 | oup  | odwn |
| not1 | not2 | not3 | not4 | not5 | not6 | not7 | not1 | not2 | not3 | not4 | not5 | not6 | not7 | oup  | odwn |
| not1 | not2 | not3 | not4 | not5 | not6 | not7 | not1 | not2 | not3 | not4 | not5 | not6 | not7 | oup  | odwn |
| C    | D    | E    | F    | G    | A    | B    | b    | #    | scl1 | scl2   | scl3 | scl4 | scl5 | scl6 | lock |

Each not1, not2, not3, etc. row represents the notes of the selected scale in a particular octave.  In the 128 version, each row is 2 octaves and there's an octave toggle on the far right.  Every 3 rows represents one MIDI channel, starting with MIDI channel 1.

The C, D, E, etc. buttons allow toggling between the selected key.  You can access the sharp and flat versions of these keys by pressing the b or # buttons.

scl1 - scl6 select between six scales, which can be changed using the pages GUI.  The numbers in the text fields are the semitones for each scale.  (default: Major, Natural Minor, Blues, Major pentatonic, Hungarian minor, Harmonic minor  - OK, so I like my minor scales...luckily you can use whatever sort of scale you like!)

Function lock button locks the 'function' keys.  To engage or disengage the function lock, you must "triple click" the button.