package com.example.week11.state;

/**
 * State Pattern - Concrete State: Stopped
 *
 * Represents the state where the music player is stopped.
 * This is the initial state. In this state:
 *   - Play: Start playback, transition to PlayingState
 *   - Pause: Cannot pause when stopped
 *   - Stop: Already stopped, no effect
 */
public class StoppedState implements State {

    @Override
    public void clickPlay(MusicPlayer player) {
        System.out.println("    [StoppedState] Starting playback...");
        player.setState(new PlayingState());
    }

    @Override
    public void clickPause(MusicPlayer player) {
        System.out.println("    [StoppedState] Cannot pause - player is stopped.");
    }

    @Override
    public void clickStop(MusicPlayer player) {
        System.out.println("    [StoppedState] Already stopped.");
    }

    @Override
    public String toString() {
        return "STOPPED";
    }
}
