package ca.vanzeben.ld25.sound;

import java.io.IOException;
import java.net.URL;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Receiver;
import javax.sound.midi.Sequence;
import javax.sound.midi.Sequencer;
import javax.sound.midi.Synthesizer;
import javax.sound.midi.Transmitter;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public enum Sound {

  TIER_1_UNIT("/sounds/tier_1_unit.wav"), TIER_4_UNIT("/sounds/tier_4_unit.wav"), PHOTON_FIRE("/sounds/photon_fire.wav"), TARGET_REACH(
      "/sounds/reach_target.wav"), EXPLOSION("/sounds/explosion.wav"), COIN_UP("/sounds/coin_up.wav"), WARNING(
      "/sounds/warning.wav"), NINJA_STAR_FIRE("/sounds/ninja_star.wav"), AMBIENT("/sounds/ambient.mid"), MAIN_LEVEL_BG(
      "/sounds/main_level.mid");

  private Sequence sequence;
  private Sequencer sequencer;
  private Thread thread;
  private Clip clip;
  private boolean isMidi = false;

  private Sound(String path) {
    URL url = Sound.class.getResource(path);
    try {
      MidiSystem.getMidiFileFormat(url);
      isMidi = true;
    } catch (InvalidMidiDataException e) {
      isMidi = false;
    } catch (IOException e) {
      e.printStackTrace();
    }
    try {
      if (isMidi) {
        sequencer = MidiSystem.getSequencer();
        sequencer.open();

        Synthesizer synth = MidiSystem.getSynthesizer();
        synth.open();

        Transmitter transmitter = sequencer.getTransmitter();
        Receiver receiver = sequencer.getTransmitters().iterator().next().getReceiver();
        transmitter.setReceiver(receiver);

        sequence = MidiSystem.getSequence(url);
        sequencer.setSequence(sequence);

      } else {
        AudioInputStream audioIn = AudioSystem.getAudioInputStream(url);
        DataLine.Info info = new DataLine.Info(Clip.class, audioIn.getFormat());
        clip = (Clip) AudioSystem.getLine(info);
        clip.open(audioIn);
        audioIn.close();
      }
    } catch (InvalidMidiDataException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    } catch (MidiUnavailableException e) {
      e.printStackTrace();
    } catch (LineUnavailableException e) {
      e.printStackTrace();
    } catch (UnsupportedAudioFileException e) {
      e.printStackTrace();
    }
  }

  public void play() {
    this.thread = new Thread() {
      public void run() {
        if (isMidi) {
          sequencer.start();
          int timer = 5;
          while (sequencer != null && sequencer.isRunning() && timer >= 0) {
            try {
              Thread.sleep(500);
            } catch (InterruptedException e) {
              e.printStackTrace();
            }
            timer--;
          }
        } else {
          clip.start();
          int timer = 5;
          while (clip != null && clip.isActive() && timer >= 0) {
            try {
              Thread.sleep(500);
            } catch (InterruptedException e) {
              e.printStackTrace();
            }
            timer--;
          }
        }
        reset();
      }
    };
    thread.start();
  }

  public void loop() {
    if (this.thread == null) {
      reset();
      this.thread = new Thread() {
        public void run() {
          if (isMidi) {
            sequencer.setLoopCount(Sequencer.LOOP_CONTINUOUSLY);
            sequencer.start();
          } else {
            clip.loop(Clip.LOOP_CONTINUOUSLY);
          }
        }
      };
      thread.start();
    }
  }

  public void stop() {
    if (isMidi) {
      sequencer.stop();
    } else {
      clip.stop();
    }
  }

  public void reset() {
    stop();
    if (isMidi) {
      sequencer.setTickPosition(0);
    } else {
      clip.setMicrosecondPosition(0);
    }
  }
}
