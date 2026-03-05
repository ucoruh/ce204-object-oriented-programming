package com.example.week11.command;

/**
 * Command Pattern - Receiver
 *
 * The Light class is the receiver that knows how to perform the
 * actual operations. The command objects delegate the work to
 * this receiver, decoupling the invoker from the receiver logic.
 */
public class Light {

    /** Descriptive name for this light (e.g., "Living Room Light") */
    private final String location;

    /** Current state of the light */
    private boolean isOn;

    /**
     * Creates a Light at the given location.
     *
     * @param location descriptive name for this light
     */
    public Light(String location) {
        this.location = location;
        this.isOn = false;
    }

    /**
     * Turns the light on.
     */
    public void turnOn() {
        isOn = true;
        System.out.println("    [Light] " + location + " light is ON");
    }

    /**
     * Turns the light off.
     */
    public void turnOff() {
        isOn = false;
        System.out.println("    [Light] " + location + " light is OFF");
    }

    /**
     * Returns whether the light is currently on.
     *
     * @return true if on, false if off
     */
    public boolean isOn() {
        return isOn;
    }
}
