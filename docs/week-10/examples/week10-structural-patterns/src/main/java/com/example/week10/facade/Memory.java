package com.example.week10.facade;

/**
 * Subsystem Class - Memory
 *
 * Part of the complex computer subsystem. Memory handles loading
 * data from the hard drive into specific memory addresses.
 *
 * In the Facade pattern:
 *   - This is a "Subsystem class"
 *   - Clients would normally need to understand memory addressing
 *   - The Facade hides this complexity behind a simple start() call
 */
public class Memory {

    /**
     * Loads data into memory at the specified position.
     *
     * @param position the memory address to load data into
     * @param data     the data to load (e.g., boot sector contents)
     */
    public void load(long position, byte[] data) {
        System.out.println("      [Memory] Loading " + data.length
                + " bytes at address 0x"
                + Long.toHexString(position).toUpperCase());
    }
}
