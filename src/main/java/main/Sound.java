package main;

import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Sound {

    Clip clip;
    URL soundURL[] = new URL[20];

    public Sound() {
        soundURL[0] = getClass().getResource("/GameSounds/collectReward.wav");
        soundURL[1] = getClass().getResource("/GameSounds/gameWon.wav");
        soundURL[2] = getClass().getResource("/GameSounds/stabbedByTrapTile.wav");
        soundURL[3] = getClass().getResource("/GameSounds/GameLost.wav");

    }

    public void setFile(int i) {
        try {
            AudioInputStream inputStream = AudioSystem.getAudioInputStream(soundURL[i]);
            clip = AudioSystem.getClip();
            clip.open(inputStream);
        } catch (Exception e) {

        }
    }

    public void play() {
        clip.start();
    }

    public void loop() {
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    public void stop() {
        clip.stop();
    }
    
}
