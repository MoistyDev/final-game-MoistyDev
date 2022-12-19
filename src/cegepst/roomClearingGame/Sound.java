package cegepst.roomClearingGame;

import javax.sound.sampled.*;
import java.io.IOException;

public enum Sound {

    PISTOL_FIRE("pistol_fire.wav"),
    PISTOL_RELOAD("pistol_reload.wav"),
    ROUND_CHANGE("round_change.wav"),
    FINAL_ROUND_CHANGE("final_round_change.wav"),
    THEME("theme.wav"),
    FINAL_ROUND("final_round.wav"),
    HURT_1("hurt_1.wav"),
    HURT_2("hurt_2.wav"),
    HURT_3("hurt_3.wav"),
    HURT_4("hurt_4.wav"),
    HURT_5("hurt_5.wav"),
    HURT_6("hurt_6.wav"),
    HURT_7("hurt_7.wav"),
    HURT_8("hurt_8.wav");

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

    public void stop() {
        try {
            Clip clip =  AudioSystem.getClip();
            AudioInputStream stream = AudioSystem.getAudioInputStream(this.getClass().getClassLoader().getResourceAsStream("audios/" + this.audioResourceName));
            clip.open(stream);
            clip.stop();
        } catch (LineUnavailableException e) {
            throw new RuntimeException(e);
        } catch (UnsupportedAudioFileException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
