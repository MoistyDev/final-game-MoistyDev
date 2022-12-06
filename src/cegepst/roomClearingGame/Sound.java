package cegepst.roomClearingGame;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public enum Sound {

    FIRE("fire.wav");

    private String audioResourceName;

    Sound(String audioResourceName) {
        this.audioResourceName = audioResourceName;
    }

    public void play() {
        try {
            Clip clip = AudioSystem.getClip();
            AudioInputStream stream = AudioSystem.getAudioInputStream(
                    this.getClass().getClassLoader().getResourceAsStream("audios/" + this.audioResourceName));
            clip.open(stream);
            clip.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
