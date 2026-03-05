package com.example.week14.texteditor;

/**
 * =============================================================================
 * Text Editor Case Study: TextEditor (Receiver in Command Pattern)
 * =============================================================================
 *
 * The TextEditor is the Receiver in the Command pattern.  It knows how
 * to perform the actual text operations (insert, delete).  Commands
 * delegate to this class to do the real work.
 *
 * It also works with the Memento pattern through createMemento() and
 * restore(), allowing its state to be saved and restored.
 *
 * Design Notes:
 *   - Uses a StringBuilder for efficient text manipulation.
 *   - Maintains a cursor position for insert/delete operations.
 *   - Provides createMemento()/restore() for the Memento pattern.
 * =============================================================================
 */
public class TextEditor {

    private StringBuilder content;
    private int cursorPosition;

    /**
     * Creates an empty text editor.
     */
    public TextEditor() {
        this.content = new StringBuilder();
        this.cursorPosition = 0;
    }

    // =========================================================================
    // Text operations (used by Commands)
    // =========================================================================

    /**
     * Inserts text at the current cursor position.
     *
     * @param text the text to insert
     */
    public void insertText(String text) {
        content.insert(cursorPosition, text);
        cursorPosition += text.length();
    }

    /**
     * Deletes the specified number of characters before the cursor.
     *
     * @param count the number of characters to delete
     * @return the deleted text (for undo purposes)
     */
    public String deleteText(int count) {
        int start = Math.max(0, cursorPosition - count);
        String deleted = content.substring(start, cursorPosition);
        content.delete(start, cursorPosition);
        cursorPosition = start;
        return deleted;
    }

    // =========================================================================
    // State access
    // =========================================================================

    /**
     * Returns the current text content.
     */
    public String getContent() {
        return content.toString();
    }

    /**
     * Returns the current cursor position.
     */
    public int getCursorPosition() {
        return cursorPosition;
    }

    /**
     * Sets the cursor position.
     */
    public void setCursorPosition(int position) {
        this.cursorPosition = Math.max(0, Math.min(position, content.length()));
    }

    // =========================================================================
    // Memento support
    // =========================================================================

    /**
     * Creates a memento capturing the current state of the editor.
     * This is the "create memento" operation in the Memento pattern.
     *
     * @return a memento representing the current state
     */
    public EditorMemento createMemento() {
        return new EditorMemento(content.toString(), cursorPosition);
    }

    /**
     * Restores the editor to a previous state from a memento.
     * This is the "restore" operation in the Memento pattern.
     *
     * @param memento the memento to restore from
     */
    public void restore(EditorMemento memento) {
        this.content = new StringBuilder(memento.getContent());
        this.cursorPosition = memento.getCursorPosition();
    }

    /**
     * Prints the current state of the editor.
     */
    public void printState() {
        String text = content.toString();
        System.out.println("    Content: \"" + text + "\"");
        System.out.println("    Cursor:  " + cursorPosition
                + " (length: " + text.length() + ")");
    }
}
