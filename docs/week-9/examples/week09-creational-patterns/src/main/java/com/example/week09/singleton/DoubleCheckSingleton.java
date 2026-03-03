package com.example.week09.singleton;

/**
 * Double-Checked Locking Singleton
 *
 * An optimization over the lazy approach: synchronization is only used
 * during the first creation. After the instance exists, subsequent calls
 * skip the synchronized block entirely, avoiding the performance penalty.
 *
 * The 'volatile' keyword is CRITICAL here. Without it, the JVM's memory
 * model might allow a thread to see a partially constructed instance.
 *
 * Pros:
 *   - Lazy initialization
 *   - Thread-safe
 *   - High performance after first creation (no synchronization overhead)
 *
 * Cons:
 *   - More complex code than eager or simple lazy approaches
 *   - Requires 'volatile' keyword (Java 5+)
 *   - Can be tricky to implement correctly
 */
public class DoubleCheckSingleton {

    // 'volatile' ensures all threads see the fully constructed instance
    private static volatile DoubleCheckSingleton instance;

    private int accessCount = 0;

    /**
     * Private constructor prevents external instantiation.
     */
    private DoubleCheckSingleton() {
        System.out.println("      DoubleCheckSingleton: Constructor called (lazy, double-checked).");
    }

    /**
     * Double-checked locking: only synchronizes on first creation.
     *
     * 1st check: If instance exists, return immediately (no lock needed).
     * 2nd check: Inside synchronized block, check again because another
     *            thread might have created it between the 1st check and
     *            acquiring the lock.
     */
    public static DoubleCheckSingleton getInstance() {
        if (instance == null) {                          // 1st check (no lock)
            synchronized (DoubleCheckSingleton.class) {
                if (instance == null) {                  // 2nd check (with lock)
                    instance = new DoubleCheckSingleton();
                }
            }
        }
        return instance;
    }

    public void doSomething() {
        accessCount++;
        System.out.println("      DoubleCheckSingleton.doSomething() called. Access count: " + accessCount);
    }

    public int getAccessCount() {
        return accessCount;
    }

    /**
     * Reset for demo purposes only.
     */
    static void resetForDemo() {
        instance = null;
    }
}
