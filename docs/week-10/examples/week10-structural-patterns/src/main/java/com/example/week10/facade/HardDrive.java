package com.example.week10.facade;

/**
 * Subsystem Class - HardDrive
 *
 * Part of the complex computer subsystem. The HardDrive reads raw data
 * from sectors on disk.
 *
 * In the Facade pattern:
 *   - This is a "Subsystem class"
 *   - Its low-level read operations are complex for the end user
 *   - The Facade abstracts away sector-level disk access
 */
public class HardDrive {

    /**
     * Reads data from the hard drive starting at the given sector.
     *
     * @param lba  the logical block address (sector number) to start reading
     * @param size the number of bytes to read
     * @return the data read from the drive
     */
    public byte[] read(long lba, int size) {
        System.out.println("      [HardDrive] Reading " + size
                + " bytes from sector " + lba);
        // Simulate reading data (return dummy bytes)
        return new byte[size];
    }
}
