package com.example.week10.adapter;

/**
 * =============================================================================
 * ADAPTER PATTERN DEMO
 * =============================================================================
 *
 * Intent:
 *   Convert the interface of a class into another interface that clients
 *   expect. Adapter lets classes work together that could not otherwise
 *   because of incompatible interfaces.
 *
 * Structure:
 *   Client --> [Target Interface] <|-- [Adapter] --> [Adaptee]
 *
 *   - Target (MediaPlayer):         The interface the client works with
 *   - Adaptee (AdvancedMediaPlayer): The existing interface to adapt
 *   - Adapter (MediaAdapter):        Bridges Target and Adaptee
 *
 * When to Use:
 *   - You want to use an existing class but its interface does not match
 *   - You want to create a reusable class that cooperates with unrelated
 *     or unforeseen classes
 *   - You need to integrate a third-party library with a different API
 *
 * Real-World Analogy:
 *   A power plug adapter lets you use a US plug in a European socket.
 *   The adapter does not change the plug or the socket - it translates
 *   between the two incompatible interfaces.
 * =============================================================================
 */
public class AdapterDemo {

    /**
     * An AudioPlayer that natively supports MP3, and uses
     * MediaAdapter for other formats (VLC, MP4).
     */
    static class AudioPlayer implements MediaPlayer {

        @Override
        public void play(String audioType, String fileName) {
            // Built-in support for MP3
            if (audioType.equalsIgnoreCase("mp3")) {
                System.out.println("    [AudioPlayer] Playing MP3 file: " + fileName);
            }
            // Use the adapter for VLC and MP4 formats
            else if (audioType.equalsIgnoreCase("vlc")
                    || audioType.equalsIgnoreCase("mp4")) {
                MediaAdapter adapter = new MediaAdapter(audioType);
                adapter.play(audioType, fileName);
            }
            // Unsupported format
            else {
                System.out.println("    [AudioPlayer] ERROR: Format '"
                        + audioType + "' is not supported.");
            }
        }
    }

    /**
     * Demonstrates the Adapter pattern with a media player example.
     */
    public static void demo() {
        System.out.println("-------------------------------------------------------------");
        System.out.println("  PATTERN 1: ADAPTER");
        System.out.println("  Making incompatible interfaces work together");
        System.out.println("-------------------------------------------------------------");

        AudioPlayer player = new AudioPlayer();

        // MP3 is natively supported by AudioPlayer
        System.out.println("  Playing various audio formats:");
        player.play("mp3", "song.mp3");

        // VLC format - handled through MediaAdapter -> VlcPlayer
        player.play("vlc", "movie.vlc");

        // MP4 format - handled through MediaAdapter -> Mp4Player
        player.play("mp4", "video.mp4");

        // Unsupported format
        player.play("avi", "clip.avi");

        System.out.println();
        System.out.println("  Key Takeaway: The AudioPlayer client code stays the same.");
        System.out.println("  The Adapter transparently delegates to the correct player.");
        System.out.println();
    }
}
