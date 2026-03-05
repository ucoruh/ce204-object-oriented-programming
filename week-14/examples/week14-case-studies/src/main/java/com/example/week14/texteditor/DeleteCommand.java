package com.example.week14.texteditor;

/**
 * =============================================================================
 * Text Editor Case Study: DeleteCommand (Concrete Command)
 * =============================================================================
 *
 * Encapsulates a "delete text" operation.  When executed, it removes the
 * specified number of characters before the cursor.  When undone, it
 * re-inserts the deleted text.
 *
 * This is a Concrete Command in the Command pattern.
 *
 * Design Notes:
 *   - Stores the deleted text (captured during execute) so that undo()
 *     can re-insert it exactly.
 *   - This is a key benefit of the Command pattern: each command carries
 *     enough information to reverse itself.
 * =============================================================================
 */
public class DeleteCommand implements Command {

    private final TextEditor editor;
    private final int count;
    private String deletedText;  // captured during execute for undo

    /**
     * Creates a delete command.
     *
     * @param editor the text editor (Receiver)
     * @param count  the number of characters to delete before the cursor
     */
    public DeleteCommand(TextEditor editor, int count) {
        this.editor = editor;
        this.count = count;
    }

    /**
     * Executes the command: deletes characters and stores them for undo.
     */
    @Override
    public void execute() {
        deletedText = editor.deleteText(count);
    }

    /**
     * Undoes the command: re-inserts the previously deleted text.
     */
    @Override
    public void undo() {
        if (deletedText != null) {
            editor.insertText(deletedText);
        }
    }

    @Override
    public String describe() {
        if (deletedText != null) {
            return "Delete " + count + " char(s): \"" + deletedText + "\"";
        }
        return "Delete " + count + " char(s)";
    }
}
