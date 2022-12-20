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
    GAME_OVER("game_over.wav"),
    HURT_1("hurt_1.wav"),
    HURT_2("hurt_2.wav"),
    HURT_3("hurt_3.wav"),
    HURT_4("hurt_4.wav"),
    HURT_5("hurt_5.wav"),
    HURT_6("hurt_6.wav"),
    HURT_7("hurt_7.wav"),
    HURT_8("hurt_8.wav"),
    HURT_9("hurt_9.wav"),
    ZOMBIE_GRUNT_1("zombie_grunt_1.wav"),
    ZOMBIE_GRUNT_2("zombie_grunt_2.wav"),
    ZOMBIE_GRUNT_3("zombie_grunt_3.wav"),
    ZOMBIE_GRUNT_4("zombie_grunt_4.wav"),
    ZOMBIE_GRUNT_5("zombie_grunt_5.wav"),
    ZOMBIE_GRUNT_6("zombie_grunt_6.wav"),
    ZOMBIE_GRUNT_7("zombie_grunt_7.wav"),
    ZOMBIE_YELL_1("zombie_yell_1.wav"),
    ZOMBIE_YELL_2("zombie_yell_2.wav"),
    ZOMBIE_YELL_3("zombie_yell_3.wav"),
    ZOMBIE_YELL_4("zombie_yell_4.wav"),
    ZOMBIE_YELL_5("zombie_yell_5.wav"),
    ZOMBIE_YELL_6("zombie_yell_6.wav"),
    ZOMBIE_YELL_7("zombie_yell_7.wav"),
    ZOMBIE_YELL_8("zombie_yell_8.wav");

    private String audioResourceName;

    Sound(String audioResourceName) {
        this.audioResourceName = audioResourceName;
    }

    public void play(boolean loop) {
        try {
            Clip clip = AudioSystem.getClip();
            AudioInputStream stream = AudioSystem.getAudioInputStream(
                    this.getClass().getClassLoader().getResourceAsStream("audios/" + this.audioResourceName));
            clip.open(stream);
            clip.start();
            if (loop) {
                clip.loop(Integer.MAX_VALUE);
            }
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
