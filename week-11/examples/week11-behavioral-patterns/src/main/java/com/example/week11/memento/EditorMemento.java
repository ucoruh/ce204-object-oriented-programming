package com.example.week11.memento;

/**
 * Memento Pattern - Memento
 *
 * Stores the internal state of the TextEditor (Originator) at a
 * specific point in time. The memento is an opaque object: the
 * Caretaker (History) can store and pass mementos around, but
 * only the Originator can read the memento's internal state.
 *
 * Structure:
 *   EditorMemento (Memento)
 *       - content: String    (captured state)
 *       + getContent(): String
 *
 * Key Principle:
 *   The memento preserves encapsulation boundaries. External objects
 *   cannot tamper with the saved state.
 */
public class EditorMemento {

    /** The saved text content (snapshot of originator state) */
    private final String content;

    /**
     * Creates a memento that captures the given content.
     * Package-private constructor: only the originator (TextEditor)
     * should create mementos.
     *
     * @param content the text content to save
     */
    EditorMemento(String content) {
        this.content = content;
    }

    /**
     * Returns the saved content. Package-private access ensures
     * only the originator can read the state.
     *
     * @return the saved text content
     */
    String getContent() {
        return content;
    }
}
