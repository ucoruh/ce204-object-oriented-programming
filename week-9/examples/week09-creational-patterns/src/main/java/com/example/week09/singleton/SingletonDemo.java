package com.example.week09.singleton;

/**
 * ============================================================================
 * SINGLETON PATTERN - Demo
 * ============================================================================
 *
 * Intent:
 *   Ensure a class has only one instance and provide a global point of
 *   access to it.
 *
 * Structure:
 *   - Singleton class with:
 *     1. A private constructor
 *     2. A private static field holding the single instance
 *     3. A public static method (getInstance) to access it
 *
 * Variants demonstrated:
 *   - EagerSingleton       : Instance created at class-load time
 *   - LazySingleton        : Created on first access (synchronized method)
 *   - DoubleCheckSingleton : Lazy + double-checked locking (high performance)
 *   - EnumSingleton        : Enum-based (best practice per Effective Java)
 *
 * When to use:
 *   - When exactly one instance of a class is needed (e.g., configuration,
 *     connection pool, logger, thread pool, cache manager)
 *   - When that instance should be accessible globally
 *   - When the single instance should be extensible by subclassing
 *
 * Real-world examples:
 *   - java.lang.Runtime.getRuntime()
 *   - java.awt.Desktop.getDesktop()
 *   - Spring Framework beans (default scope is singleton)
 *
 * Caution:
 *   - Singletons can make unit testing harder (global state)
 *   - Overuse is considered an anti-pattern; prefer dependency injection
 * ============================================================================
 */
public class SingletonDemo {

    public static void demo() {
        System.out.println("=============================================================");
        System.out.println("  5. SINGLETON PATTERN");
        System.out.println("=============================================================");
        System.out.println();
        System.out.println("  Scenario: We compare four Singleton implementations and");
        System.out.println("  verify that getInstance() always returns the same object.");
        System.out.println();

        // --- 1) Eager Singleton ---
        System.out.println("  >> Eager Initialization Singleton:");
        EagerSingleton eager1 = EagerSingleton.getInstance();
        EagerSingleton eager2 = EagerSingleton.getInstance();
        eager1.doSomething();
        eager2.doSomething();
        System.out.println("    Same instance? " + (eager1 == eager2));  // true
        System.out.println();

        // --- 2) Lazy Singleton ---
        System.out.println("  >> Lazy Initialization Singleton (synchronized):");
        LazySingleton lazy1 = LazySingleton.getInstance();
        LazySingleton lazy2 = LazySingleton.getInstance();
        lazy1.doSomething();
        lazy2.doSomething();
        System.out.println("    Same instance? " + (lazy1 == lazy2));    // true
        System.out.println();

        // --- 3) Double-Checked Locking Singleton ---
        System.out.println("  >> Double-Checked Locking Singleton:");
        DoubleCheckSingleton dc1 = DoubleCheckSingleton.getInstance();
        DoubleCheckSingleton dc2 = DoubleCheckSingleton.getInstance();
        dc1.doSomething();
        dc2.doSomething();
        System.out.println("    Same instance? " + (dc1 == dc2));        // true
        System.out.println();

        // --- 4) Enum-Based Singleton ---
        System.out.println("  >> Enum-Based Singleton (best practice):");
        EnumSingleton enum1 = EnumSingleton.INSTANCE;
        EnumSingleton enum2 = EnumSingleton.INSTANCE;
        enum1.doSomething();
        enum2.doSomething();
        System.out.println("    Same instance? " + (enum1 == enum2));    // true
        System.out.println();

        // --- Comparison summary ---
        System.out.println("  +---------------------------+--------+--------+-----------+");
        System.out.println("  | Approach                  | Lazy?  | Safe?  | Best Use  |");
        System.out.println("  +---------------------------+--------+--------+-----------+");
        System.out.println("  | Eager Initialization      |  No    |  Yes   | Simple    |");
        System.out.println("  | Lazy (synchronized)       |  Yes   |  Yes   | Low perf  |");
        System.out.println("  | Double-Checked Locking    |  Yes   |  Yes   | High perf |");
        System.out.println("  | Enum (Bloch recommended)  |  No    |  Yes   | Default   |");
        System.out.println("  +---------------------------+--------+--------+-----------+");
        System.out.println();

        // Key takeaway
        System.out.println("  KEY TAKEAWAY:");
        System.out.println("  - All four approaches guarantee a single instance.");
        System.out.println("  - Enum singleton is the simplest, safest, and recommended.");
        System.out.println("  - Double-checked locking is best when lazy init + high");
        System.out.println("    performance are both required.");
        System.out.println("  - Use singletons sparingly; prefer dependency injection");
        System.out.println("    frameworks (Spring, Guice) in real applications.");
        System.out.println();
    }
}
