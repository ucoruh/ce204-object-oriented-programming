package com.example.week14.texteditor;

import java.util.ArrayList;
import java.util.List;

/**
 * =============================================================================
 * Text Editor Case Study: Full Demo
 * =============================================================================
 *
 * This demo ties together the Command and Memento patterns in a text
 * editor scenario:
 *
 *   - Command Pattern: Each edit operation (type, delete) is encapsulated
 *     as a Command object with execute() and undo() methods.
 *
 *   - Memento Pattern: Before each command is executed, the editor's state
 *     is saved to an EditorHistory (Caretaker).  Undo restores the editor
 *     from the most recent memento.
 *
 * The two patterns complement each other:
 *   - Command provides the "what to do" and "how to undo" abstraction.
 *   - Memento provides a full state snapshot as a safety net, enabling
 *     restoration even for complex multi-step operations.
 * =============================================================================
 */
public class TextEditorDemo {

    /**
     * Runs the complete text editor demonstration.
     */
    public static void demo() {
        TextEditor editor = new TextEditor();
        EditorHistory history = new EditorHistory();
        List<Command> executedCommands = new ArrayList<>();

        System.out.println("  --- Text Editor: Command + Memento Demo ---");
        System.out.println();

        // =====================================================================
        // Step 1: Type "Hello"
        // =====================================================================
        System.out.println("  Step 1: Typing 'Hello'");
        history.save(editor);  // Memento: save state before command
        Command cmd1 = new TypeCommand(editor, "Hello");
        cmd1.execute();
        executedCommands.add(cmd1);
        editor.printState();
        System.out.println();

        // =====================================================================
        // Step 2: Type " World"
        // =====================================================================
        System.out.println("  Step 2: Typing ' World'");
        history.save(editor);
        Command cmd2 = new TypeCommand(editor, " World");
        cmd2.execute();
        executedCommands.add(cmd2);
        editor.printState();
        System.out.println();

        // =====================================================================
        // Step 3: Type "!"
        // =====================================================================
        System.out.println("  Step 3: Typing '!'");
        history.save(editor);
        Command cmd3 = new TypeCommand(editor, "!");
        cmd3.execute();
        executedCommands.add(cmd3);
        editor.printState();
        System.out.println();

        // =====================================================================
        // Step 4: Delete 1 character (the "!")
        // =====================================================================
        System.out.println("  Step 4: Deleting 1 character");
        history.save(editor);
        Command cmd4 = new DeleteCommand(editor, 1);
        cmd4.execute();
        executedCommands.add(cmd4);
        System.out.println("    Command: " + cmd4.describe());
        editor.printState();
        System.out.println();

        // =====================================================================
        // Step 5: Undo using Command pattern (undo last command)
        // =====================================================================
        System.out.println("  Step 5: Undo via Command.undo() -- re-inserts '!'");
        Command lastCmd = executedCommands.remove(executedCommands.size() - 1);
        lastCmd.undo();
        editor.printState();
        System.out.println();

        // =====================================================================
        // Step 6: Undo using Memento pattern (restore previous snapshot)
        // =====================================================================
        System.out.println("  Step 6: Undo via Memento -- restore to before '!'");
        history.undo(editor);  // restores to state before Step 3
        editor.printState();
        System.out.println();

        // =====================================================================
        // Step 7: Multiple undos using Memento
        // =====================================================================
        System.out.println("  Step 7: Two more Memento undos...");
        history.undo(editor);  // restores to state before Step 2
        System.out.println("    After undo 1:");
        editor.printState();

        history.undo(editor);  // restores to state before Step 1
        System.out.println("    After undo 2:");
        editor.printState();
        System.out.println();

        // =====================================================================
        // Summary
        // =====================================================================
        System.out.println("  --- Summary ---");
        System.out.println("  Command pattern: each operation is an object with");
        System.out.println("    execute() and undo() methods.");
        System.out.println("  Memento pattern: full state snapshots enable");
        System.out.println("    restoration to any previous point.");
        System.out.println("  Together: robust, flexible undo/redo system.");
    }
}
