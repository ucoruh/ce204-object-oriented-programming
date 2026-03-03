package com.example.week11.observer;

/**
 * Observer Pattern - Concrete Observer: Email Notification
 *
 * Sends an email notification when it receives an event update.
 * In a real application, this would integrate with an email service;
 * here we simulate the behavior with console output.
 */
public class EmailListener implements EventListener {

    /** The email address to send notifications to */
    private final String email;

    /**
     * Creates an EmailListener that sends notifications to the given address.
     *
     * @param email the email address for notifications
     */
    public EmailListener(String email) {
        this.email = email;
    }

    /**
     * Handles the event by "sending" an email notification.
     *
     * @param eventType the type of event
     * @param data      the event data (e.g., filename)
     */
    @Override
    public void update(String eventType, String data) {
        System.out.println("    [EmailListener] Sending email to " + email
            + ": Event '" + eventType + "' occurred with data '" + data + "'");
    }
}
