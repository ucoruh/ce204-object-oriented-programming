package com.example.week10.facade;

/**
 * =============================================================================
 * FACADE PATTERN DEMO
 * =============================================================================
 *
 * Intent:
 *   Provide a unified interface to a set of interfaces in a subsystem.
 *   Facade defines a higher-level interface that makes the subsystem
 *   easier to use.
 *
 * Structure:
 *   Client --> [Facade] --> [Subsystem Class A]
 *                       --> [Subsystem Class B]
 *                       --> [Subsystem Class C]
 *
 * Participants in this demo:
 *   - Facade:            ComputerFacade
 *   - Subsystem Classes: CPU, Memory, HardDrive
 *
 * When to Use:
 *   - You want a simple interface to a complex subsystem
 *   - There are many dependencies between clients and implementation classes
 *   - You want to layer your subsystems (each layer uses a Facade)
 *   - You want to decouple the client from subsystem internals
 *
 * Real-World Analogy:
 *   A customer service representative is a Facade. Instead of navigating
 *   multiple departments (billing, shipping, returns), you call one
 *   person who coordinates everything for you.
 *
 * Important Note:
 *   The Facade does NOT encapsulate subsystem classes. The client can
 *   still access them directly if needed. It just provides a convenient
 *   shortcut for common operations.
 * =============================================================================
 */
public class FacadeDemo {

    public static void demo() {
        System.out.println("-------------------------------------------------------------");
        System.out.println("  PATTERN 5: FACADE");
        System.out.println("  Providing a simplified interface to a complex subsystem");
        System.out.println("-------------------------------------------------------------");

        // Using the Facade - simple and clean
        System.out.println("  --- Using the Facade (simple) ---");
        ComputerFacade computer = new ComputerFacade();
        computer.start();
        System.out.println();
        computer.shutdown();
        System.out.println();

        // Without the Facade, the client would need to do this:
        System.out.println("  --- Without Facade (complex, low-level) ---");
        CPU cpu = new CPU();
        Memory memory = new Memory();
        HardDrive hardDrive = new HardDrive();

        // The client must know the correct sequence and details
        cpu.freeze();
        byte[] bootData = hardDrive.read(0, 512);
        memory.load(0x7C00, bootData);
        cpu.jump(0x7C00);
        cpu.execute();

        System.out.println();
        System.out.println("  Key Takeaway: The Facade hides the complexity of boot");
        System.out.println("  sequencing. Clients call start() instead of managing");
        System.out.println("  CPU, Memory, and HardDrive individually.");
        System.out.println();
    }
}
