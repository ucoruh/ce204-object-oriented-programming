package com.example.week10.bridge;

/**
 * Refined Abstraction - AdvancedRemoteControl
 *
 * Extends the basic RemoteControl with additional features (like mute).
 * This demonstrates that the abstraction hierarchy can grow independently
 * of the implementation hierarchy (Device).
 *
 * In Bridge pattern terminology, this is the "RefinedAbstraction":
 *   - It adds new operations on top of the base Abstraction
 *   - It still uses the same Device bridge for implementation
 *   - Any new feature here works with ALL existing devices automatically
 */
public class AdvancedRemoteControl extends RemoteControl {

    /**
     * Creates an advanced remote for the given device.
     *
     * @param device the device this remote will control
     */
    public AdvancedRemoteControl(Device device) {
        super(device);
    }

    /**
     * Mutes the device by setting volume to 0.
     * This is an additional capability not present in the basic remote.
     */
    public void mute() {
        System.out.println("    [AdvancedRemote] Mute!");
        device.setVolume(0);
    }

    /**
     * Jumps directly to a specific channel.
     * This is an additional capability not present in the basic remote.
     *
     * @param channel the channel number to jump to
     */
    public void goToChannel(int channel) {
        System.out.println("    [AdvancedRemote] Go to channel " + channel);
        device.setChannel(channel);
    }
}
