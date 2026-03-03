package com.example.week14.texteditor;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * =============================================================================
 * Text Editor Case Study: EditorHistory (Caretaker in Memento Pattern)
 * =============================================================================
 *
 * The Caretaker is responsible for storing mementos but never examines
 * or modifies their contents.  It treats mementos as opaque snapshots.
 *
 * This implementation uses a stack (Deque) to support undo operations:
 *   - save()    pushes the current state onto the history stack
 *   - undo()    pops the most recent state and restores it
 *
 * Design Notes:
 *   - The history has a configurable maximum size to prevent unbounded
 *     memory growth.
 *   - When the maximum is reached, the oldest snapshot is discarded.
 *   - The Caretaker collaborates with TextEditor (the Originator) to
 *     create and restore mementos.
 * =============================================================================
 */
public class EditorHistory {

    private final Deque<EditorMemento> history = new ArrayDeque<>();
    private final int maxSize;

    /**
     * Creates a history with the given maximum number of snapshots.
     *
     * @param maxSize maximum number of mementos to keep
     */
    public EditorHistory(int maxSize) {
        this.maxSize = maxSize;
    }

    /**
     * Creates a history with a default capacity of 100 snapshots.
     */
    public EditorHistory() {
        this(100);
    }

    /**
     * Saves the current state of the editor.
     *
     * @param editor the text editor whose state should be saved
     */
    public void save(TextEditor editor) {
        if (history.size() >= maxSize) {
            history.removeLast();  // discard oldest
        }
        history.push(editor.createMemento());
    }

    /**
     * Restores the editor to its most recently saved state.
     *
     * @param editor the text editor to restore
     * @return true if a state was restored, false if history is empty
     */
    public boolean undo(TextEditor editor) {
        if (history.isEmpty()) {
            System.out.println("    [History] Nothing to undo.");
            return false;
        }
        EditorMemento memento = history.pop();
        editor.restore(memento);
        return true;
    }

    /**
     * Returns the number of snapshots currently stored.
     */
    public int size() {
        return history.size();
    }

    /**
     * Returns true if there are no saved snapshots.
     */
    public boolean isEmpty() {
        return history.isEmpty();
    }
}
