# Page Description #

The Machine Drum Interface page is useful for controlling an Elektron Machine Drum from a monome.  It can be used to wildly alter all parameters of the current kit, switch quickly between kits, or even to construct kits one machine at a time.  Currently only a 64 / 40h layout is available, but it will work on any sized monome.

## 64 Layout ##

| mac1 | mac2 | mac3 | mac4 | mac5 | mac6 | mac7 | mac8 |
|:-----|:-----|:-----|:-----|:-----|:-----|:-----|:-----|
| mac9 | ma10 | ma11 | ma12 | ma13 | ma14 | ma15 | ma16 |
| prm1 | prm2 | prm3 | prm4 | prm5 | prm6 | prm7 | prm8 |
| prm9 | pr10 | pr11 | pr12 | pr13 | pr14 | pr15 | pr16 |
| pr17 | pr18 | pr19 | pr20 | pr21 | pr22 | pr23 | pr24 |
| kgen | init | null | null | null | null | null | null |
| lod1 | lod2 | lod3 | lod4 | sav1 | sav2 | sav3 | sav4 |
| mrph | echo | gate | eq   | comp | null | null | null |

  * mac1-16

The mac1 - mac16 buttons are toggle buttons.  They enable sending random parameter changes to the machine they represent.  mac1 = BD, mac2 = SD, ... mac16 = M4.

  * prm1-24

The prm1-prm24 buttons are toggle buttons as well.  They enable sending random parameter changes to the parameter they represent.  prm1-8 are the first page of parameters, prm9-16 are the second page, and prm17-24 are the third page.

  * Machines and Parameters

When a machine's toggle (mac1-16) is enabled, and a parameter's toggle (prm1-24) is enabled, the enabled machine will have the enabled parameter randomly set.  Each enabled machine / parameter combination will have random messages sent to it.  For example, if mac1 and mac2 are enabled, and prm1, prm2, and prm3 are enabled, then machines 1 and 2 (BD and SD) will receive random parameter changes on parameters 1, 2, and 3.

This is all driven by MIDI clock, so be sure to have a MIDI clock source running in to pages.  The speed parameter on the GUI controls how fast random parameter changes are sent.  Lower is faster, and you shouldn't set it below 1 (it will probably crash).  Note that if you set it to 1 you'll be sending an awful lot of MIDI data very fast, so try something more reasonable for a more stable experience.

  * kgen

This is the kit generator button.  When pressed, it will initialize all machine slots with a random machine.  Certain machines are chosen for certain positions (ie. BD will have kick drums, SD will have snare drums).  All changes to the current kit will be lost when this button is pressed, so be careful.

  * clr

This is the kit clear / initialize button.  It will remove all machines from all kits when pressed.  Not very useful unless you want to start fresh and construct a kit.

  * lod1-4 and sav1-4

lod1-4 are the quick load buttons and sav1-4 are the quick save buttons.  The save button will tell Machine Drum to save the current kit, including all machines, their parameter settings, and all global effects settings, to a particular kit slot in the Machine Drum's internal memory.  They are very fast functions and are the key to performing live with this page.  Each one represents a particular kit in the Machine Drum--lod1 and sav1 represent kit slot 1, lod2 and sav2 are slot 2, etc.  The idea is to save off kits you like as parameters are morphing around and quickly recall them to morph them in a different direction.

  * mrph

The morph button enables random enabling and disabling of the mac and prm buttons.  It was basically just turn them on and off over time to give variation to the parameter changes.  When this mode is enabled the BD and SD slots will never be randomly turned on or off (to prevent losing the backbone of the beat when running this in the background).

  * echo, gate, eq, comp

These buttons enable random parameter changing of the global kit effects (echo, gate/reverb, eq, and compressor).

### Suggested Use ###

Here's a few examples of how this page can be used

  * Live Performance

This page was designed for live performance.  It's designed to allow you control over the Machine Drum at a meta-level and at a moment's notice.  This page might be incorporated into a performance in the following way:

  1. Load up a kit you like using the LCD / physical controls on the Machine Drum
  1. Hit sav1 to save the kit to slot 1
  1. Feed a sequence to the Machine Drum so it's playing something
  1. Enable the mrph button to randomly change up sounds of the drums
  1. When you hear a variation you like, hit another save button like sav2 (keep slot 1 with the original kit)
  1. If it goes off into a direction you don't like, hit a load button to bring back the original kit or a variation you've saved
  1. You can do other things like trigger samples, adjust knobs, etc. while this page is running.  If it starts doing something you don't like so much, flip back and load a default kit to reset it.
  1. When you're ready to move on to another default kit, load it up in the Machine Drum interface and repeat.

  * Studio

The page can also be useful in the studio for coming up with new kits.  Because you can enable random changes on certain parameters and for certain machines, you can mold kits by simply enabling the cc morphers and listening for variations you like.  Use the save and load buttons to keep your progress backed up as you randomize parameters.  If you don't like the direction it's going, load a backup, and if you hear something you like, quickly disable the parameter change and save the kit to a slot.  The 4 save/load slots gives you room to explore different paths as you construct a kit.