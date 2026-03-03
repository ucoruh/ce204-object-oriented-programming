package com.example.week11.memento;

import java.util.Stack;

/**
 * Memento Pattern - Caretaker
 *
 * The History class acts as the caretaker: it manages the collection
 * of mementos (undo/redo stacks) without examining or modifying
 * their contents. It provides undo and redo operations by pushing
 * and popping mementos between two stacks.
 *
 * Structure:
 *   History (Caretaker)
 *       - undoStack: Stack<EditorMemento>
 *       - redoStack: Stack<EditorMemento>
 *       + save(memento): void
 *       + undo(): EditorMemento
 *       + redo(): EditorMemento
 *
 * Key Principle:
 *   The caretaker never looks inside the memento. It only stores,
 *   passes, and retrieves mementos.
 */
public class History {

    /** Stack of mementos for undo operations */
    private final Stack<EditorMemento> undoStack = new Stack<>();

    /** Stack of mementos for redo operations */
    private final Stack<EditorMemento> redoStack = new Stack<>();

    /**
     * Saves a memento to the undo stack. Clears the redo stack
     * because a new action invalidates the redo history.
     *
     * @param memento the memento to save
     */
    public void save(EditorMemento memento) {
        undoStack.push(memento);
        redoStack.clear(); // New action invalidates redo history
    }

    /**
     * Pops the most recent memento from the undo stack and pushes
     * the current state onto the redo stack for possible redo.
     *
     * @param currentState the current state memento (before undoing)
     * @return the previous state memento, or null if nothing to undo
     */
    public EditorMemento undo(EditorMemento currentState) {
        if (undoStack.isEmpty()) {
            System.out.println("    [History] Nothing to undo.");
            return null;
        }
        redoStack.push(currentState);
        return undoStack.pop();
    }

    /**
     * Pops the most recent memento from the redo stack and pushes
     * the current state onto the undo stack.
     *
     * @param currentState the current state memento (before redoing)
     * @return the next state memento, or null if nothing to redo
     */
    public EditorMemento redo(EditorMemento currentState) {
        if (redoStack.isEmpty()) {
            System.out.println("    [History] Nothing to redo.");
            return null;
        }
        undoStack.push(currentState);
        return redoStack.pop();
    }
}
