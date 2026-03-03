package com.example.week11.mediator;

/**
 * Mediator Pattern - Demo
 *
 * Intent:
 *   Define an object that encapsulates how a set of objects interact.
 *   Mediator promotes loose coupling by keeping objects from referring
 *   to each other explicitly, and it lets you vary their interaction
 *   independently.
 *
 * Structure:
 *   <<interface>> ChatMediator
 *           ^
 *           |
 *       ChatRoom (Concrete Mediator)
 *           |
 *           +--- manages ---> User, User, User ...
 *
 *   Users communicate ONLY through the mediator.
 *   Without mediator: N*(N-1)/2 direct connections.
 *   With mediator:    N connections (star topology).
 *
 * When to Use:
 *   - A set of objects communicate in well-defined but complex ways
 *   - Reusing an object is difficult because it refers to many other objects
 *   - A behavior distributed among classes should be customizable without subclassing
 *
 * Real-World Examples:
 *   - Chat rooms and messaging systems
 *   - Air traffic control (ATC) towers
 *   - UI dialog box components (form fields coordinated by dialog)
 *   - Event bus systems in microservices
 */
public class MediatorDemo {

    public static void demo() {
        System.out.println("==============================================================");
        System.out.println("  Pattern 4: MEDIATOR");
        System.out.println("  Centralize complex communication between objects");
        System.out.println("==============================================================");

        // Create the mediator (chat room)
        ChatMediator chatRoom = new ChatRoom();

        // Create users (colleagues) linked to the mediator
        User alice = new User(chatRoom, "Alice");
        User bob = new User(chatRoom, "Bob");
        User charlie = new User(chatRoom, "Charlie");

        // Add users to the chat room
        System.out.println("\n  --- Users joining the chat room ---");
        chatRoom.addUser(alice);
        chatRoom.addUser(bob);
        chatRoom.addUser(charlie);

        // Users communicate through the mediator
        System.out.println("\n  --- Alice sends a message ---");
        alice.send("Hello everyone!");

        System.out.println("\n  --- Bob sends a message ---");
        bob.send("Hi Alice, how are you?");

        System.out.println("\n  --- Charlie sends a message ---");
        charlie.send("Good morning team!");

        System.out.println();
    }
}
