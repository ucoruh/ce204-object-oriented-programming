package com.example.week11.mediator;

/**
 * Mediator Pattern - Colleague
 *
 * Represents a participant in the chat. Each User communicates only
 * through the ChatMediator, never directly with other users. This
 * loose coupling makes it easy to add/remove participants.
 *
 * Structure:
 *   User (Colleague)
 *       - mediator: ChatMediator
 *       - name: String
 *       + send(message): void
 *       + receive(message): void
 */
public class User {

    /** Reference to the mediator (chat room) */
    private final ChatMediator mediator;

    /** Display name of this user */
    private final String name;

    /**
     * Creates a user that communicates through the given mediator.
     *
     * @param mediator the chat mediator to use for communication
     * @param name     the display name of this user
     */
    public User(ChatMediator mediator, String name) {
        this.mediator = mediator;
        this.name = name;
    }

    /**
     * Sends a message to all other users via the mediator.
     * The user does not need to know about the other participants.
     *
     * @param message the message to send
     */
    public void send(String message) {
        System.out.println("    [" + name + "] sends: " + message);
        mediator.sendMessage(message, this);
    }

    /**
     * Receives a message from another user via the mediator.
     *
     * @param message the message received
     */
    public void receive(String message) {
        System.out.println("    [" + name + "] received: " + message);
    }

    /**
     * Returns the name of this user.
     *
     * @return the user's display name
     */
    public String getName() {
        return name;
    }
}
