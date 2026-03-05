package com.example.week11.command;

/**
 * Command Pattern - Demo
 *
 * Intent:
 *   Encapsulate a request as an object, thereby letting you parameterize
 *   clients with different requests, queue or log requests, and support
 *   undoable operations.
 *
 * Structure:
 *   Client creates Command --> Invoker stores/executes --> Receiver does work
 *
 *   RemoteControl (Invoker)
 *       |
 *       v
 *   <<interface>> Command
 *       |               |
 *   LightOnCommand  LightOffCommand
 *       |               |
 *       +-------+-------+
 *               |
 *           Light (Receiver)
 *
 * When to Use:
 *   - You want to parameterize objects with an action to perform
 *   - You want to specify, queue, and execute requests at different times
 *   - You need to support undo/redo operations
 *   - You want to structure a system around high-level operations built on primitives
 *
 * Real-World Examples:
 *   - GUI button actions and menu items
 *   - Transaction-based systems (database operations)
 *   - Task schedulers and job queues
 *   - Macro recording in text editors
 */
public class CommandDemo {

    public static void demo() {
        System.out.println("==============================================================");
        System.out.println("  Pattern 2: COMMAND");
        System.out.println("  Encapsulate a request as an object with undo support");
        System.out.println("==============================================================");

        // Create the receiver
        Light livingRoomLight = new Light("Living Room");

        // Create commands
        Command lightOn = new LightOnCommand(livingRoomLight);
        Command lightOff = new LightOffCommand(livingRoomLight);

        // Create the invoker
        RemoteControl remote = new RemoteControl();

        // Execute commands via the invoker
        System.out.println("\n  --- Pressing buttons ---");
        remote.pressButton(lightOn);   // Turn on
        remote.pressButton(lightOff);  // Turn off

        // Demonstrate undo
        System.out.println("\n  --- Pressing undo (should turn on again) ---");
        remote.pressUndo();

        System.out.println("\n  --- Pressing undo again (should turn off) ---");
        remote.pressUndo();

        System.out.println("\n  --- Pressing undo with empty history ---");
        remote.pressUndo();

        System.out.println();
    }
}
