package com.sheep.farmingGame.util;

import javax.sound.sampled.*;
import java.io.IOException;

public class AudioManager {
    public static String SFX_FIREBALL = "/fireball.wav";
    public static String SFX_HIT_ENEMY = "/hitEnemy.wav";
    public static String SFX_HIT_PLAYER = "/hitPlayer.wav";

    public static String MUSIC_DRUMS = "/music.wav";

    private Clip clip;

    public void loadSound(String path) {
        try
        {
            AudioInputStream stream = AudioSystem.getAudioInputStream(AudioManager.class.getResource(path));
            clip = AudioSystem.getClip();
            clip.open(stream);
        }
        catch (UnsupportedAudioFileException | LineUnavailableException | IOException e) {
            e.printStackTrace();
        }
    }

    public void play(){
        clip.start();
    }

    public void stop(){
        clip.stop();
    }

    public void loop(){
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }
}
