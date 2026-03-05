package com.example.week14.texteditor;

/**
 * =============================================================================
 * Text Editor Case Study: Command Interface (Command Pattern)
 * =============================================================================
 *
 * Command Pattern:
 *   Encapsulates a request as an object, thereby letting you parameterize
 *   clients with different requests, queue or log requests, and support
 *   undoable operations.
 *
 * Participants:
 *   - Command (this interface)                  -- declares execute/undo
 *   - TypeCommand, DeleteCommand                -- Concrete Commands
 *   - TextEditor                                -- the Receiver
 *   - TextEditorDemo                            -- the Invoker/Client
 *
 * Each command knows how to execute itself AND how to undo itself,
 * enabling full undo/redo support.
 * =============================================================================
 */
public interface Command {

    /**
     * Executes this command, modifying the editor's state.
     */
    void execute();

    /**
     * Undoes this command, reverting the editor to its previous state.
     */
    void undo();

    /**
     * Returns a human-readable description of this command.
     */
    String describe();
}
