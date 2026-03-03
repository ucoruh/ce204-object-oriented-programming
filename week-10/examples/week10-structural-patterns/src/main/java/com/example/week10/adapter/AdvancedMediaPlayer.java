package com.example.week10.adapter;

/**
 * Adaptee Interface - AdvancedMediaPlayer
 *
 * This interface represents a different, incompatible system that provides
 * advanced media playback capabilities (VLC and MP4 formats).
 *
 * In the Adapter pattern, this is the "Adaptee" role:
 *   - It has the functionality we need
 *   - But its interface is incompatible with what the client expects
 *   - The Adapter will translate calls from the Target interface to this one
 */
public interface AdvancedMediaPlayer {

    /**
     * Plays a VLC format file.
     *
     * @param fileName the name of the VLC file
     */
    void playVlc(String fileName);

    /**
     * Plays an MP4 format file.
     *
     * @param fileName the name of the MP4 file
     */
    void playMp4(String fileName);
}
