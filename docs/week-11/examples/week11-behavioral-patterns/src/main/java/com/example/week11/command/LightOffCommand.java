package com.example.week11.command;

/**
 * Command Pattern - Concrete Command: Turn Light Off
 *
 * Encapsulates the action of turning off a light. Binds together
 * the receiver (Light) and the action (turnOff), supporting
 * both execute and undo operations.
 */
public class LightOffCommand implements Command {

    /** The receiver that this command operates on */
    private final Light light;

    /**
     * Creates a command to turn off the specified light.
     *
     * @param light the receiver light object
     */
    public LightOffCommand(Light light) {
        this.light = light;
    }

    /**
     * Executes the command: turns the light OFF.
     */
    @Override
    public void execute() {
        light.turnOff();
    }

    /**
     * Undoes the command: turns the light ON.
     */
    @Override
    public void undo() {
        light.turnOn();
    }
}
