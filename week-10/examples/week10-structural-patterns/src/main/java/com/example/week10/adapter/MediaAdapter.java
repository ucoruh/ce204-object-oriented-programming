package com.example.week10.adapter;

/**
 * Adapter - MediaAdapter
 *
 * This class bridges the gap between the MediaPlayer (Target) interface
 * and the AdvancedMediaPlayer (Adaptee) interface.
 *
 * KEY CONCEPT:
 *   - Implements the Target interface (MediaPlayer)
 *   - Holds a reference to the Adaptee (AdvancedMediaPlayer)
 *   - Translates Target method calls into Adaptee method calls
 *
 * This is an "Object Adapter" (uses composition rather than inheritance).
 * The adapter wraps an AdvancedMediaPlayer and makes it look like a MediaPlayer.
 */
public class MediaAdapter implements MediaPlayer {

    // The Adaptee instance - the existing functionality we want to reuse
    private AdvancedMediaPlayer advancedPlayer;

    /**
     * Creates an adapter for the given audio type.
     * Internally selects the appropriate AdvancedMediaPlayer implementation.
     *
     * @param audioType the audio format to support ("vlc" or "mp4")
     */
    public MediaAdapter(String audioType) {
        if (audioType.equalsIgnoreCase("vlc")) {
            advancedPlayer = new VlcPlayer();
        } else if (audioType.equalsIgnoreCase("mp4")) {
            advancedPlayer = new Mp4Player();
        }
    }

    /**
     * Adapts the play() call from the MediaPlayer interface
     * to the appropriate method on the AdvancedMediaPlayer.
     *
     * This is where the "translation" happens:
     *   play("vlc", file)  -->  advancedPlayer.playVlc(file)
     *   play("mp4", file)  -->  advancedPlayer.playMp4(file)
     */
    @Override
    public void play(String audioType, String fileName) {
        if (audioType.equalsIgnoreCase("vlc")) {
            advancedPlayer.playVlc(fileName);
        } else if (audioType.equalsIgnoreCase("mp4")) {
            advancedPlayer.playMp4(fileName);
        }
    }
}
