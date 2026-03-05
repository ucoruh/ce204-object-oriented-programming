package com.example.week11.memento;

/**
 * Memento Pattern - Demo (Undo/Redo)
 *
 * Intent:
 *   Without violating encapsulation, capture and externalize an object's
 *   internal state so that the object can be restored to this state later.
 *
 * Structure:
 *   TextEditor (Originator) ---creates---> EditorMemento (Memento)
 *                                                ^
 *                                                |
 *   History (Caretaker) ---stores/retrieves------+
 *
 * When to Use:
 *   - You need to save and restore state (undo/redo, checkpoints)
 *   - A direct interface for obtaining state would expose implementation details
 *   - You want to maintain encapsulation of the originator's internals
 *
 * Real-World Examples:
 *   - Text editor undo/redo (Ctrl+Z / Ctrl+Y)
 *   - Game save points and checkpoints
 *   - Database transaction rollback
 *   - Browser back/forward navigation
 */
public class MementoDemo {

    public static void demo() {
        System.out.println("==============================================================");
        System.out.println("  Pattern 5: MEMENTO");
        System.out.println("  Capture and restore object state (undo/redo)");
        System.out.println("==============================================================");

        TextEditor editor = new TextEditor();
        History history = new History();

        // Type some text and save states BEFORE each change
        System.out.println("\n  --- Typing and saving states ---");

        // Save initial (empty) state, then type
        history.save(editor.save());
        editor.type("Hello");
        System.out.println("    After typing 'Hello':        \"" + editor.getContent() + "\"");

        // Save "Hello" state, then type more
        history.save(editor.save());
        editor.type(" World");
        System.out.println("    After typing ' World':       \"" + editor.getContent() + "\"");

        // Save "Hello World" state, then type more
        history.save(editor.save());
        editor.type("! Welcome to OOP.");
        System.out.println("    After typing '! Welcome...': \"" + editor.getContent() + "\"");

        // Undo operations: each undo pops a previous state and pushes current to redo
        System.out.println("\n  --- Undo operations ---");

        EditorMemento previous = history.undo(editor.save());
        if (previous != null) {
            editor.restore(previous);
            System.out.println("    After undo #1: \"" + editor.getContent() + "\"");
        }

        previous = history.undo(editor.save());
        if (previous != null) {
            editor.restore(previous);
            System.out.println("    After undo #2: \"" + editor.getContent() + "\"");
        }

        // Redo operation: pops from redo stack and pushes current to undo
        System.out.println("\n  --- Redo operation ---");

        EditorMemento next = history.redo(editor.save());
        if (next != null) {
            editor.restore(next);
            System.out.println("    After redo #1: \"" + editor.getContent() + "\"");
        }

        System.out.println();
    }
}
