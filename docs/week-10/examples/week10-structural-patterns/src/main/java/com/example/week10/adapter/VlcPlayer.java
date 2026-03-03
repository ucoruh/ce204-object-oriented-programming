package com.example.week10.adapter;

/**
 * Concrete Adaptee - VlcPlayer
 *
 * A concrete implementation of the AdvancedMediaPlayer interface
 * that knows how to play VLC format files.
 *
 * This class is part of the "existing system" that the Adapter makes
 * compatible with the Target interface (MediaPlayer).
 */
public class VlcPlayer implements AdvancedMediaPlayer {

    @Override
    public void playVlc(String fileName) {
        System.out.println("    [VlcPlayer] Playing VLC file: " + fileName);
    }

    @Override
    public void playMp4(String fileName) {
        // VlcPlayer does not handle MP4 files - do nothing
    }
}
