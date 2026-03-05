package com.example.week10.facade;

/**
 * Subsystem Class - CPU
 *
 * Part of the complex computer subsystem. The CPU has its own detailed
 * interface for freezing, jumping to memory addresses, and executing
 * instructions.
 *
 * In the Facade pattern:
 *   - This is a "Subsystem class"
 *   - It implements subsystem functionality
 *   - It handles work assigned by the Facade
 *   - It has no knowledge of the Facade (no reference to it)
 */
public class CPU {

    /** Freezes the CPU to prepare for bootstrapping. */
    public void freeze() {
        System.out.println("      [CPU] Freezing processor...");
    }

    /**
     * Jumps to the specified memory address to begin execution.
     *
     * @param position the memory address to jump to
     */
    public void jump(long position) {
        System.out.println("      [CPU] Jumping to address 0x"
                + Long.toHexString(position).toUpperCase());
    }

    /**
     * Executes instructions loaded at the current position.
     */
    public void execute() {
        System.out.println("      [CPU] Executing instructions...");
    }
}
