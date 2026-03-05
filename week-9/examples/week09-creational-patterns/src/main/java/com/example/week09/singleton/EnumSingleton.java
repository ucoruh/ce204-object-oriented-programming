package com.example.week09.singleton;

/**
 * Enum-Based Singleton (Best Practice - Recommended by Joshua Bloch)
 *
 * Using an enum is the simplest and most robust way to implement a
 * singleton in Java. The JVM guarantees:
 *   - Only one instance is ever created
 *   - Thread safety (handled by the JVM's enum loading mechanism)
 *   - Serialization safety (enum serialization is handled by the JVM)
 *   - Protection against reflection attacks (you cannot reflectively
 *     create enum instances)
 *
 * Pros:
 *   - Simplest implementation
 *   - Thread-safe (guaranteed by JVM)
 *   - Serialization-safe (no special handling needed)
 *   - Reflection-safe (cannot create extra instances)
 *   - Recommended by "Effective Java" by Joshua Bloch
 *
 * Cons:
 *   - Cannot extend other classes (enums cannot inherit)
 *   - Eager initialization (loaded when enum class is first referenced)
 *   - Less intuitive for developers unfamiliar with enum singletons
 */
public enum EnumSingleton {

    /** The one and only instance. */
    INSTANCE;

    private int accessCount = 0;

    /**
     * Enum constructor (always private, implicitly).
     */
    EnumSingleton() {
        System.out.println("      EnumSingleton: Enum instance created by JVM.");
    }

    public void doSomething() {
        accessCount++;
        System.out.println("      EnumSingleton.doSomething() called. Access count: " + accessCount);
    }

    public int getAccessCount() {
        return accessCount;
    }
}
