package com.example.week11.state;

/**
 * State Pattern - Context
 *
 * The MusicPlayer is the context that maintains a reference to the
 * current state. It delegates all state-specific behavior to the
 * current state object. The player's behavior changes when its
 * state changes, as if the object changed its class.
 *
 * Structure:
 *   MusicPlayer (Context)
 *       - state: State
 *       + clickPlay(): void    --> delegates to state.clickPlay(this)
 *       + clickPause(): void   --> delegates to state.clickPause(this)
 *       + clickStop(): void    --> delegates to state.clickStop(this)
 *       + setState(state): void
 */
public class MusicPlayer {

    /** The current state of the music player */
    private State state;

    /**
     * Creates a MusicPlayer in the initial Stopped state.
     */
    public MusicPlayer() {
        this.state = new StoppedState();
    }

    /**
     * Changes the player's current state. Called by concrete
     * state objects when a transition occurs.
     *
     * @param state the new state
     */
    public void setState(State state) {
        this.state = state;
        System.out.println("    [MusicPlayer] State changed to: " + state);
    }

    /**
     * Returns the current state of the player.
     *
     * @return the current state
     */
    public State getState() {
        return state;
    }

    /**
     * Delegates the play action to the current state.
     */
    public void clickPlay() {
        state.clickPlay(this);
    }

    /**
     * Delegates the pause action to the current state.
     */
    public void clickPause() {
        state.clickPause(this);
    }

    /**
     * Delegates the stop action to the current state.
     */
    public void clickStop() {
        state.clickStop(this);
    }
}
