package com.example.week11.state;

/**
 * State Pattern - Concrete State: Playing
 *
 * Represents the state where the music player is actively playing audio.
 * In this state:
 *   - Play: Already playing, no effect
 *   - Pause: Transition to PausedState
 *   - Stop: Transition to StoppedState
 */
public class PlayingState implements State {

    @Override
    public void clickPlay(MusicPlayer player) {
        System.out.println("    [PlayingState] Already playing.");
    }

    @Override
    public void clickPause(MusicPlayer player) {
        System.out.println("    [PlayingState] Pausing playback...");
        player.setState(new PausedState());
    }

    @Override
    public void clickStop(MusicPlayer player) {
        System.out.println("    [PlayingState] Stopping playback...");
        player.setState(new StoppedState());
    }

    @Override
    public String toString() {
        return "PLAYING";
    }
}
