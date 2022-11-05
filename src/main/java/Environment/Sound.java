package environment;

import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

/**
 * This class represents a Sound which has a Clip object used to play and stop
 * sounds and a soundURL list for the game's different sounds.
 * 
 * @author Marco LAnfranchi 
 */
public class Sound {

    Clip clip;
    URL soundURL[] = new URL[20];

    /**
     * Creates a Sound and sets indexes in its soundURL list to different .wav files
     */
    public Sound() {
        soundURL[0] = getClass().getResource("/sounds/collectReward.wav");
        soundURL[1] = getClass().getResource("/sounds/gameWon.wav");
        soundURL[2] = getClass().getResource("/sounds/stabbedByTrapTile.wav");
        soundURL[3] = getClass().getResource("/sounds/GameLost.wav");
        soundURL[4] = getClass().getResource("/sounds/Waiting.wav");

    }

    /**
     * Sets this Sound's Clip field to the sound in the soundURL list with the
     * given index i.
     * @param i an int represneting an index in this Sound's soundURL list.
     */
    public void setFile(int i) {
        try {
            AudioInputStream inputStream = AudioSystem.getAudioInputStream(soundURL[i]);
            clip = AudioSystem.getClip();
            clip.open(inputStream);
        } catch (Exception e) {

        }
    }


    /**
     * Call's Clip's start method to play this Sound's audio.
     */
    public void play() {
        clip.start();
    }


    /**
     * Loop this Sound's audio continuously.
     */
    public void loop() {
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }


    /**
     * Stop this Sound's audio from playing.
     */
    public void stop() {
        clip.stop();
    }
    
}
