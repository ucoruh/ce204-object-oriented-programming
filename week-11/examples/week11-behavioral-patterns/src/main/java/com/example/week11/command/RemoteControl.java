package com.example.week11.command;

import java.util.Stack;

/**
 * Command Pattern - Invoker with Undo Support
 *
 * The RemoteControl acts as the invoker: it stores commands and
 * triggers their execution. It also maintains a history stack
 * to support undo operations, demonstrating a key benefit of
 * the Command pattern.
 *
 * Structure:
 *   RemoteControl (Invoker) ---uses---> Command (interface)
 *       - commandHistory: Stack<Command>
 *       + pressButton(cmd): void
 *       + pressUndo(): void
 */
public class RemoteControl {

    /** Stack of executed commands for undo support */
    private final Stack<Command> commandHistory = new Stack<>();

    /**
     * Executes the given command and stores it in the history
     * for possible later undo.
     *
     * @param command the command to execute
     */
    public void pressButton(Command command) {
        command.execute();
        commandHistory.push(command);
    }

    /**
     * Undoes the most recently executed command by popping it
     * from the history stack and calling its undo() method.
     * If there is no command to undo, a message is printed.
     */
    public void pressUndo() {
        if (!commandHistory.isEmpty()) {
            Command lastCommand = commandHistory.pop();
            System.out.println("    [RemoteControl] Undoing last command...");
            lastCommand.undo();
        } else {
            System.out.println("    [RemoteControl] Nothing to undo.");
        }
    }
}
