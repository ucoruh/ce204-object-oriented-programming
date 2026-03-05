package com.example.week11.state;

/**
 * State Pattern - Concrete State: Paused
 *
 * Represents the state where the music player has paused playback.
 * In this state:
 *   - Play: Resume playback, transition to PlayingState
 *   - Pause: Already paused, no effect
 *   - Stop: Transition to StoppedState
 */
public class PausedState implements State {

    @Override
    public void clickPlay(MusicPlayer player) {
        System.out.println("    [PausedState] Resuming playback...");
        player.setState(new PlayingState());
    }

    @Override
    public void clickPause(MusicPlayer player) {
        System.out.println("    [PausedState] Already paused.");
    }

    @Override
    public void clickStop(MusicPlayer player) {
        System.out.println("    [PausedState] Stopping from pause...");
        player.setState(new StoppedState());
    }

    @Override
    public String toString() {
        return "PAUSED";
    }
}
