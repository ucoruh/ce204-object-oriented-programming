package com.example.week10.facade;

/**
 * Facade - ComputerFacade
 *
 * Provides a simplified interface to the complex computer subsystem.
 * Instead of interacting with CPU, Memory, and HardDrive directly,
 * the client simply calls start() and shutdown().
 *
 * In the Facade pattern:
 *   - This is the "Facade" role
 *   - It knows which subsystem classes are responsible for a request
 *   - It delegates client requests to appropriate subsystem objects
 *   - It does NOT add new functionality - it just simplifies access
 *
 * KEY CONCEPT:
 *   The Facade does NOT prevent access to subsystem classes. Clients
 *   CAN still use subsystem classes directly if they need fine-grained
 *   control. The Facade just provides a convenient default.
 */
public class ComputerFacade {

    // Subsystem components
    private final CPU cpu;
    private final Memory memory;
    private final HardDrive hardDrive;

    // Constants for boot process
    private static final long BOOT_ADDRESS = 0x7C00;
    private static final long BOOT_SECTOR = 0;
    private static final int SECTOR_SIZE = 512;

    /**
     * Creates a ComputerFacade, initializing all subsystem components.
     */
    public ComputerFacade() {
        this.cpu = new CPU();
        this.memory = new Memory();
        this.hardDrive = new HardDrive();
    }

    /**
     * Starts the computer. This single method call orchestrates a complex
     * sequence of operations across multiple subsystem components.
     *
     * Without the Facade, the client would need to:
     *   1. Know about CPU, Memory, and HardDrive
     *   2. Know the correct order of operations
     *   3. Know memory addresses and sector numbers
     *   4. Handle coordination between components
     */
    public void start() {
        System.out.println("    [ComputerFacade] Starting computer...");
        cpu.freeze();
        byte[] bootData = hardDrive.read(BOOT_SECTOR, SECTOR_SIZE);
        memory.load(BOOT_ADDRESS, bootData);
        cpu.jump(BOOT_ADDRESS);
        cpu.execute();
        System.out.println("    [ComputerFacade] Computer started successfully!");
    }

    /**
     * Shuts down the computer gracefully.
     */
    public void shutdown() {
        System.out.println("    [ComputerFacade] Shutting down computer...");
        System.out.println("      [CPU] Saving state...");
        System.out.println("      [Memory] Flushing caches...");
        System.out.println("      [HardDrive] Parking heads...");
        System.out.println("    [ComputerFacade] Computer shut down.");
    }
}
