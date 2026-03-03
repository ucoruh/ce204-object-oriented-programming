package com.example.week10.adapter;

/**
 * Target Interface - MediaPlayer
 *
 * This is the interface that the client code expects to work with.
 * It defines the standard contract for playing audio content.
 *
 * In the Adapter pattern, this is the "Target" role:
 *   - The client uses this interface
 *   - The Adapter will implement this interface
 *   - It bridges the gap between client expectations and the Adaptee
 */
public interface MediaPlayer {

    /**
     * Plays audio content of the specified type.
     *
     * @param audioType the format type (e.g., "mp3", "vlc", "mp4")
     * @param fileName  the name of the file to play
     */
    void play(String audioType, String fileName);
}
