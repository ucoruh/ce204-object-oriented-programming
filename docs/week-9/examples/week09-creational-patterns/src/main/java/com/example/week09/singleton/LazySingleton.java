package com.example.week09.singleton;

/**
 * Lazy Initialization Singleton (with synchronized method)
 *
 * The instance is created only when first requested. The entire
 * getInstance() method is synchronized to ensure thread safety.
 *
 * Pros:
 *   - Lazy initialization (created only when needed)
 *   - Thread-safe
 *
 * Cons:
 *   - Synchronized method causes performance overhead on EVERY call,
 *     even after the instance has been created
 *   - Not recommended for high-performance scenarios
 */
public class LazySingleton {

    // Not created until first access
    private static LazySingleton instance;

    private int accessCount = 0;

    /**
     * Private constructor prevents external instantiation.
     */
    private LazySingleton() {
        System.out.println("      LazySingleton: Constructor called (lazy, on first access).");
    }

    /**
     * Synchronized method ensures only one thread can create the instance.
     * However, synchronization happens on EVERY call (even after creation).
     */
    public static synchronized LazySingleton getInstance() {
        if (instance == null) {
            instance = new LazySingleton();
        }
        return instance;
    }

    public void doSomething() {
        accessCount++;
        System.out.println("      LazySingleton.doSomething() called. Access count: " + accessCount);
    }

    public int getAccessCount() {
        return accessCount;
    }

    /**
     * Reset for demo purposes only. In production, singletons are NOT reset.
     */
    static void resetForDemo() {
        instance = null;
    }
}
