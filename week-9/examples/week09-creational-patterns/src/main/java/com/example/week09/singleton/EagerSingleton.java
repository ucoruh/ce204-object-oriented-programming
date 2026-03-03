package com.example.week09.singleton;

/**
 * Eager Initialization Singleton
 *
 * The instance is created at class loading time. This is the simplest
 * approach and is thread-safe because the JVM guarantees that static
 * initializers run in a thread-safe manner.
 *
 * Pros:
 *   - Simple and straightforward
 *   - Thread-safe without synchronization
 *   - No performance overhead on getInstance()
 *
 * Cons:
 *   - Instance is created even if it is never used (wastes memory)
 *   - Cannot handle exceptions during construction gracefully
 *   - No lazy initialization
 */
public class EagerSingleton {

    // Instance created eagerly at class loading time
    private static final EagerSingleton INSTANCE = new EagerSingleton();

    // Track how many times getInstance() is called (for demo purposes)
    private int accessCount = 0;

    /**
     * Private constructor prevents external instantiation.
     */
    private EagerSingleton() {
        // Simulate some initialization work
        System.out.println("      EagerSingleton: Constructor called (this happens once, at class load).");
    }

    /**
     * Global access point. Always returns the same instance.
     */
    public static EagerSingleton getInstance() {
        return INSTANCE;
    }

    public void doSomething() {
        accessCount++;
        System.out.println("      EagerSingleton.doSomething() called. Access count: " + accessCount);
    }

    public int getAccessCount() {
        return accessCount;
    }
}
