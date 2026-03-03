package com.example.week14.notification;

/**
 * =============================================================================
 * Notification Case Study: BaseNotification (Concrete Component)
 * =============================================================================
 *
 * The base notification sends messages via email.  This is the Concrete
 * Component in the Decorator pattern -- the simplest, undecorated version.
 *
 * Decorators wrap this class to add additional channels (SMS, Slack, etc.)
 * without modifying this class.
 *
 * Design Notes:
 *   - In a real system, this would integrate with an email service API.
 *   - The recipient email is stored as a field and used in every send().
 * =============================================================================
 */
public class BaseNotification implements NotificationService {

    private final String recipientEmail;

    /**
     * Creates a base email notification.
     *
     * @param recipientEmail the email address to send notifications to
     */
    public BaseNotification(String recipientEmail) {
        if (recipientEmail == null || !recipientEmail.contains("@")) {
            throw new IllegalArgumentException("Invalid email: " + recipientEmail);
        }
        this.recipientEmail = recipientEmail;
    }

    /**
     * Sends the notification via email (the base channel).
     *
     * @param message the notification message
     */
    @Override
    public void send(String message) {
        System.out.println("    [Email] Sending to " + recipientEmail
                + ": " + message);
    }

    @Override
    public String getChannelDescription() {
        return "Email(" + recipientEmail + ")";
    }
}
