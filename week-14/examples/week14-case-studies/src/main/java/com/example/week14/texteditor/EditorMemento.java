package com.example.week14.texteditor;

/**
 * =============================================================================
 * Text Editor Case Study: EditorMemento (Memento Pattern)
 * =============================================================================
 *
 * Memento Pattern:
 *   Without violating encapsulation, captures and externalizes an object's
 *   internal state so that the object can be restored to this state later.
 *
 * Participants:
 *   - EditorMemento (this class)  -- the Memento (stores state snapshot)
 *   - TextEditor                  -- the Originator (creates/restores mementos)
 *   - EditorHistory               -- the Caretaker (manages memento storage)
 *
 * Design Notes:
 *   - The memento is immutable: once created, its state cannot be changed.
 *   - Only TextEditor (the Originator) should read the memento's internals.
 *     In this demo, we use package-private access for simplicity.
 *   - The Caretaker (EditorHistory) treats mementos as opaque tokens.
 * =============================================================================
 */
public class EditorMemento {

    private final String content;
    private final int cursorPosition;

    /**
     * Creates a memento storing the editor's state.
     *
     * @param content        the text content at the time of the snapshot
     * @param cursorPosition the cursor position at the time of the snapshot
     */
    EditorMemento(String content, int cursorPosition) {
        this.content = content;
        this.cursorPosition = cursorPosition;
    }

    /**
     * Returns the stored text content.
     * Package-private: only TextEditor (the Originator) should access this.
     */
    String getContent() {
        return content;
    }

    /**
     * Returns the stored cursor position.
     * Package-private: only TextEditor (the Originator) should access this.
     */
    int getCursorPosition() {
        return cursorPosition;
    }

    @Override
    public String toString() {
        return "Memento[content=\"" + content + "\", cursor=" + cursorPosition + "]";
    }
}
