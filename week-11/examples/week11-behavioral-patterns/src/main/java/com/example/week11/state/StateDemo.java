package com.example.week11.state;

/**
 * State Pattern - Demo
 *
 * Intent:
 *   Allow an object to alter its behavior when its internal state
 *   changes. The object will appear to change its class.
 *
 * Structure:
 *   MusicPlayer (Context)
 *       |
 *       +-- delegates to --> <<interface>> State
 *                                 |
 *                     +-----------+-----------+
 *                     |           |           |
 *               PlayingState PausedState StoppedState
 *
 * When to Use:
 *   - An object's behavior depends on its state and must change at run-time
 *   - Operations have large, multi-part conditional statements depending on state
 *   - You want to avoid large switch/if-else blocks for state-dependent behavior
 *
 * Real-World Examples:
 *   - Media player states (play/pause/stop)
 *   - TCP connection states (established/listening/closed)
 *   - Order processing (pending/shipped/delivered/returned)
 *   - Document workflow (draft/review/published)
 */
public class StateDemo {

    public static void demo() {
        System.out.println("==============================================================");
        System.out.println("  Pattern 7: STATE");
        System.out.println("  Alter behavior when internal state changes");
        System.out.println("==============================================================");

        MusicPlayer player = new MusicPlayer();
        System.out.println("\n  Initial state: " + player.getState());

        // Stopped -> Play -> Playing
        System.out.println("\n  --- Click Play (from Stopped) ---");
        player.clickPlay();

        // Playing -> Pause -> Paused
        System.out.println("\n  --- Click Pause (from Playing) ---");
        player.clickPause();

        // Paused -> Play -> Playing (resume)
        System.out.println("\n  --- Click Play (from Paused - resume) ---");
        player.clickPlay();

        // Playing -> Play (already playing)
        System.out.println("\n  --- Click Play (already Playing) ---");
        player.clickPlay();

        // Playing -> Stop -> Stopped
        System.out.println("\n  --- Click Stop (from Playing) ---");
        player.clickStop();

        // Stopped -> Pause (invalid)
        System.out.println("\n  --- Click Pause (from Stopped - invalid) ---");
        player.clickPause();

        System.out.println();
    }
}
