package com.example.week10.bridge;

/**
 * Concrete Implementation - TV
 *
 * A specific device implementation. The TV knows how to manage its own
 * power, volume, and channel state.
 *
 * This is a "ConcreteImplementor" in Bridge pattern terminology.
 * It can be swapped with any other Device (e.g., Radio) without
 * changing the RemoteControl abstraction.
 */
public class TV implements Device {

    private boolean on = false;
    private int volume = 30;
    private int channel = 1;

    @Override
    public boolean isEnabled() {
        return on;
    }

    @Override
    public void enable() {
        on = true;
        System.out.println("      [TV] Powered ON");
    }

    @Override
    public void disable() {
        on = false;
        System.out.println("      [TV] Powered OFF");
    }

    @Override
    public int getVolume() {
        return volume;
    }

    @Override
    public void setVolume(int volume) {
        // Clamp volume to valid range
        this.volume = Math.max(0, Math.min(100, volume));
        System.out.println("      [TV] Volume set to " + this.volume);
    }

    @Override
    public int getChannel() {
        return channel;
    }

    @Override
    public void setChannel(int channel) {
        this.channel = channel;
        System.out.println("      [TV] Channel set to " + this.channel);
    }

    @Override
    public String getDeviceInfo() {
        return "TV [on=" + on + ", volume=" + volume + ", channel=" + channel + "]";
    }
}
