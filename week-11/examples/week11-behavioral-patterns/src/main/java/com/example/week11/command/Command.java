package com.example.week11.command;

/**
 * Command Pattern - Command Interface
 *
 * Declares the interface for executing an operation. All concrete
 * commands implement this interface, encapsulating a request as
 * an object. This decouples the invoker from the receiver.
 *
 * Structure:
 *   <<interface>> Command
 *       + execute(): void
 *       + undo(): void
 */
public interface Command {

    /**
     * Executes the command action.
     */
    void execute();

    /**
     * Undoes the command action, reverting the receiver
     * to its state before execute() was called.
     */
    void undo();
}
