package com.example.week10.bridge;

/**
 * Concrete Implementation - Radio
 *
 * Another specific device implementation. The Radio manages its own
 * power, volume, and station (channel) state.
 *
 * This is a "ConcreteImplementor" in Bridge pattern terminology.
 * It demonstrates that new device types can be added without modifying
 * the RemoteControl hierarchy.
 */
public class Radio implements Device {

    private boolean on = false;
    private int volume = 20;
    private int channel = 88; // FM frequency-style channel

    @Override
    public boolean isEnabled() {
        return on;
    }

    @Override
    public void enable() {
        on = true;
        System.out.println("      [Radio] Powered ON");
    }

    @Override
    public void disable() {
        on = false;
        System.out.println("      [Radio] Powered OFF");
    }

    @Override
    public int getVolume() {
        return volume;
    }

    @Override
    public void setVolume(int volume) {
        this.volume = Math.max(0, Math.min(100, volume));
        System.out.println("      [Radio] Volume set to " + this.volume);
    }

    @Override
    public int getChannel() {
        return channel;
    }

    @Override
    public void setChannel(int channel) {
        this.channel = channel;
        System.out.println("      [Radio] Station set to " + this.channel + " FM");
    }

    @Override
    public String getDeviceInfo() {
        return "Radio [on=" + on + ", volume=" + volume + ", station=" + channel + " FM]";
    }
}
