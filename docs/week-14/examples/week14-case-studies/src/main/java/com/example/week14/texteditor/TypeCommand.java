package com.example.week14.texteditor;

/**
 * =============================================================================
 * Text Editor Case Study: TypeCommand (Concrete Command)
 * =============================================================================
 *
 * Encapsulates a "type text" operation.  When executed, it inserts text
 * at the current cursor position.  When undone, it removes that text.
 *
 * This is a Concrete Command in the Command pattern.
 *
 * Design Notes:
 *   - Stores the text to be typed so that undo() can remove exactly
 *     that text.
 *   - References the TextEditor (Receiver) to perform the actual work.
 * =============================================================================
 */
public class TypeCommand implements Command {

    private final TextEditor editor;
    private final String text;

    /**
     * Creates a type command.
     *
     * @param editor the text editor (Receiver)
     * @param text   the text to insert
     */
    public TypeCommand(TextEditor editor, String text) {
        this.editor = editor;
        this.text = text;
    }

    /**
     * Executes the command: inserts the text at the cursor position.
     */
    @Override
    public void execute() {
        editor.insertText(text);
    }

    /**
     * Undoes the command: deletes the same number of characters that
     * were inserted.
     */
    @Override
    public void undo() {
        editor.deleteText(text.length());
    }

    @Override
    public String describe() {
        return "Type: \"" + text + "\"";
    }
}
