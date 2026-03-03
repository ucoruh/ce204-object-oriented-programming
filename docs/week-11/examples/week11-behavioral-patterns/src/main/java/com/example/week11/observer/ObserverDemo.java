package com.example.week11.observer;

/**
 * Observer Pattern - Demo
 *
 * Intent:
 *   Define a one-to-many dependency between objects so that when one
 *   object changes state, all its dependents are notified and updated
 *   automatically.
 *
 * Structure:
 *   Editor (Concrete Subject)
 *       |
 *       +-- uses --> EventManager (Subject infrastructure)
 *                        |
 *                        +-- notifies --> <<interface>> EventListener
 *                                              |              |
 *                                        EmailListener   LogListener
 *
 * When to Use:
 *   - A change to one object requires changing others, and you don't know how many
 *   - An object should notify other objects without assumptions about who they are
 *   - You want to decouple the subject from its observers
 *
 * Real-World Examples:
 *   - GUI event handling (button click listeners)
 *   - Publish-subscribe messaging systems
 *   - MVC architecture (Model notifies Views)
 *   - Java's PropertyChangeListener / Spring ApplicationEvent
 */
public class ObserverDemo {

    public static void demo() {
        System.out.println("==============================================================");
        System.out.println("  Pattern 6: OBSERVER");
        System.out.println("  Notify dependents of state changes automatically");
        System.out.println("==============================================================");

        // Create the subject
        Editor editor = new Editor();

        // Create observers
        EmailListener emailListener = new EmailListener("admin@example.com");
        LogListener logListener = new LogListener("/var/log/editor.log");

        // Subscribe observers to events
        System.out.println("\n  --- Subscribing listeners ---");
        editor.events.subscribe("open", emailListener);
        editor.events.subscribe("open", logListener);
        editor.events.subscribe("save", emailListener);
        System.out.println("    EmailListener subscribed to: open, save");
        System.out.println("    LogListener subscribed to: open");

        // Trigger events
        System.out.println("\n  --- Opening a file (both listeners notified) ---");
        editor.openFile("design-patterns.txt");

        System.out.println("\n  --- Saving the file (only email listener notified) ---");
        editor.saveFile();

        // Unsubscribe and trigger again
        System.out.println("\n  --- Unsubscribing email from 'open' event ---");
        editor.events.unsubscribe("open", emailListener);

        System.out.println("\n  --- Opening another file (only log listener notified) ---");
        editor.openFile("behavioral-patterns.txt");

        System.out.println();
    }
}
