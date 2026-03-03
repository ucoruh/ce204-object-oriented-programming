package com.example.week11.mediator;

import java.util.ArrayList;
import java.util.List;

/**
 * Mediator Pattern - Concrete Mediator
 *
 * ChatRoom implements the ChatMediator interface and coordinates
 * communication between User objects. When a user sends a message,
 * the ChatRoom delivers it to all other users. This centralizes
 * the communication logic, so users remain decoupled from each other.
 */
public class ChatRoom implements ChatMediator {

    /** List of all users in this chat room */
    private final List<User> users = new ArrayList<>();

    /**
     * Adds a user to the chat room.
     *
     * @param user the user to add
     */
    @Override
    public void addUser(User user) {
        users.add(user);
        System.out.println("    [ChatRoom] " + user.getName() + " joined the chat.");
    }

    /**
     * Sends a message from the sender to all other users in the room.
     * The sender does not receive their own message.
     *
     * @param message the message to broadcast
     * @param sender  the user who sent the message
     */
    @Override
    public void sendMessage(String message, User sender) {
        for (User user : users) {
            // Don't send the message back to the sender
            if (user != sender) {
                user.receive(sender.getName() + ": " + message);
            }
        }
    }
}
