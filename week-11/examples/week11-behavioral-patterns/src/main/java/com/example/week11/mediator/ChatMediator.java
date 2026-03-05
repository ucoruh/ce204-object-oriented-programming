package com.example.week11.mediator;

/**
 * Mediator Pattern - Mediator Interface
 *
 * Defines the interface used by colleague objects (Users) to
 * communicate. The mediator encapsulates how colleagues interact,
 * preventing them from referring to each other directly.
 *
 * Structure:
 *   <<interface>> ChatMediator
 *       + sendMessage(message, sender): void
 *       + addUser(user): void
 */
public interface ChatMediator {

    /**
     * Sends a message from one user to all other users in the chat.
     * The mediator handles the routing logic.
     *
     * @param message the message content to send
     * @param sender  the user who sent the message
     */
    void sendMessage(String message, User sender);

    /**
     * Adds a new user to the chat room.
     *
     * @param user the user to add
     */
    void addUser(User user);
}
