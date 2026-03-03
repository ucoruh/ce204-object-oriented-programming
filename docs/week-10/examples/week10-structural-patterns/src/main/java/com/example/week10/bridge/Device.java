package com.example.week10.bridge;

/**
 * Implementation Interface - Device
 *
 * This interface defines the "implementation" side of the Bridge pattern.
 * It represents the varying implementation that can be switched independently
 * of the abstraction (RemoteControl).
 *
 * In the Bridge pattern:
 *   - This is the "Implementor" role
 *   - Concrete classes (TV, Radio) provide specific implementations
 *   - The Abstraction (RemoteControl) holds a reference to this interface
 *   - Changes to Device do NOT affect RemoteControl, and vice versa
 */
public interface Device {

    /**
     * Checks whether the device is currently powered on.
     *
     * @return true if the device is on, false otherwise
     */
    boolean isEnabled();

    /** Turns the device on. */
    void enable();

    /** Turns the device off. */
    void disable();

    /**
     * Gets the current volume level.
     *
     * @return volume level (0-100)
     */
    int getVolume();

    /**
     * Sets the volume level.
     *
     * @param volume the new volume level (0-100)
     */
    void setVolume(int volume);

    /**
     * Gets the current channel number.
     *
     * @return the current channel
     */
    int getChannel();

    /**
     * Sets the channel number.
     *
     * @param channel the channel to switch to
     */
    void setChannel(int channel);

    /**
     * Returns a description of this device for display purposes.
     *
     * @return device description string
     */
    String getDeviceInfo();
}
