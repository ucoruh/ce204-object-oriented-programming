package com.example.week11.command;

/**
 * Command Pattern - Concrete Command: Turn Light On
 *
 * Encapsulates the action of turning on a light. Binds together
 * the receiver (Light) and the action (turnOn), supporting
 * both execute and undo operations.
 */
public class LightOnCommand implements Command {

    /** The receiver that this command operates on */
    private final Light light;

    /**
     * Creates a command to turn on the specified light.
     *
     * @param light the receiver light object
     */
    public LightOnCommand(Light light) {
        this.light = light;
    }

    /**
     * Executes the command: turns the light ON.
     */
    @Override
    public void execute() {
        light.turnOn();
    }

    /**
     * Undoes the command: turns the light OFF.
     */
    @Override
    public void undo() {
        light.turnOff();
    }
}
