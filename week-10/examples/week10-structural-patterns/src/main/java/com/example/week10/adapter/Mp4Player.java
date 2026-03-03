package com.example.week10.adapter;

/**
 * Concrete Adaptee - Mp4Player
 *
 * A concrete implementation of the AdvancedMediaPlayer interface
 * that knows how to play MP4 format files.
 *
 * This class is part of the "existing system" that the Adapter makes
 * compatible with the Target interface (MediaPlayer).
 */
public class Mp4Player implements AdvancedMediaPlayer {

    @Override
    public void playVlc(String fileName) {
        // Mp4Player does not handle VLC files - do nothing
    }

    @Override
    public void playMp4(String fileName) {
        System.out.println("    [Mp4Player] Playing MP4 file: " + fileName);
    }
}
