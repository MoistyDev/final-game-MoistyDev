package cegepst.finalGame;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public enum Music {

    WORLD_BACKGROUND("music.wav");

    private Clip clip;

    Music(String audioResourceName) {
        try {
            clip = AudioSystem.getClip();
            AudioInputStream stream = AudioSystem.getAudioInputStream(
                    this.getClass().getClassLoader().getResourceAsStream("audios/" + audioResourceName));
            clip.open(stream);
            clip.loop(Clip.LOOP_CONTINUOUSLY);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void play() {
        clip.start();
    }

    public void stop() {
        clip.stop();
    }

    public void toggle() {
        if (clip.isRunning()) {
            clip.stop();
        } else {
            clip.start();
        }
    }
}
