package com.example.week11.memento;

/**
 * Memento Pattern - Originator
 *
 * The TextEditor is the originator: it creates mementos to capture
 * its current state and can restore itself from a memento. The
 * editor's internal state (content) can be saved and restored
 * without violating encapsulation.
 *
 * Structure:
 *   TextEditor (Originator)
 *       - content: String
 *       + save(): EditorMemento
 *       + restore(memento): void
 *       + type(text): void
 *       + getContent(): String
 */
public class TextEditor {

    /** The current text content of the editor */
    private String content = "";

    /**
     * Appends text to the editor's content, simulating typing.
     *
     * @param text the text to append
     */
    public void type(String text) {
        content += text;
    }

    /**
     * Returns the current content of the editor.
     *
     * @return the current text content
     */
    public String getContent() {
        return content;
    }

    /**
     * Creates a memento that captures the editor's current state.
     * This snapshot can later be used to restore the editor.
     *
     * @return a memento containing the current content
     */
    public EditorMemento save() {
        return new EditorMemento(content);
    }

    /**
     * Restores the editor's state from a memento, reverting the
     * content to the state captured when the memento was created.
     *
     * @param memento the memento to restore from
     */
    public void restore(EditorMemento memento) {
        this.content = memento.getContent();
    }
}
