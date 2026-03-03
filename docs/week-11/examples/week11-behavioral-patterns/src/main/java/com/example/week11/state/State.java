package com.example.week11.state;

/**
 * State Pattern - State Interface
 *
 * Defines the interface for encapsulating the behavior associated
 * with a particular state of the context (MusicPlayer). Each concrete
 * state implements this interface to provide state-specific behavior.
 *
 * Structure:
 *   <<interface>> State
 *       + clickPlay(player): void
 *       + clickPause(player): void
 *       + clickStop(player): void
 */
public interface State {

    /**
     * Handles the "play" action in this state.
     *
     * @param player the context (music player) whose state may change
     */
    void clickPlay(MusicPlayer player);

    /**
     * Handles the "pause" action in this state.
     *
     * @param player the context (music player) whose state may change
     */
    void clickPause(MusicPlayer player);

    /**
     * Handles the "stop" action in this state.
     *
     * @param player the context (music player) whose state may change
     */
    void clickStop(MusicPlayer player);
}
