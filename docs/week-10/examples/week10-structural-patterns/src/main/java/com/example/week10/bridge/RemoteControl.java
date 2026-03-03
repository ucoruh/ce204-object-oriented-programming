package com.example.week10.bridge;

/**
 * Abstraction - RemoteControl
 *
 * This is the "Abstraction" in the Bridge pattern. It defines the high-level
 * control interface and delegates the actual work to the Device (Implementor).
 *
 * KEY CONCEPT - The Bridge:
 *   The reference 'device' IS the bridge. It connects the abstraction
 *   (remote control logic) to the implementation (device behavior).
 *
 *   RemoteControl  ----bridge---->  Device
 *   (abstraction)                   (implementation)
 *
 *   Both hierarchies can vary independently:
 *     - New remotes (AdvancedRemoteControl) can be added
 *     - New devices (TV, Radio, Projector...) can be added
 *     - Neither side needs to know about the other's changes
 */
public class RemoteControl {

    // The "bridge" - a reference to the implementation side
    protected Device device;

    /**
     * Creates a remote control for the given device.
     *
     * @param device the device this remote will control
     */
    public RemoteControl(Device device) {
        this.device = device;
    }

    /** Toggles the device power on or off. */
    public void togglePower() {
        System.out.println("    [Remote] Toggle power");
        if (device.isEnabled()) {
            device.disable();
        } else {
            device.enable();
        }
    }

    /** Increases the volume by 10. */
    public void volumeUp() {
        System.out.println("    [Remote] Volume up");
        device.setVolume(device.getVolume() + 10);
    }

    /** Decreases the volume by 10. */
    public void volumeDown() {
        System.out.println("    [Remote] Volume down");
        device.setVolume(device.getVolume() - 10);
    }

    /** Switches to the next channel. */
    public void channelUp() {
        System.out.println("    [Remote] Channel up");
        device.setChannel(device.getChannel() + 1);
    }

    /** Switches to the previous channel. */
    public void channelDown() {
        System.out.println("    [Remote] Channel down");
        device.setChannel(device.getChannel() - 1);
    }

    /** Prints the current state of the controlled device. */
    public void printDeviceStatus() {
        System.out.println("    [Remote] Device status: " + device.getDeviceInfo());
    }
}
