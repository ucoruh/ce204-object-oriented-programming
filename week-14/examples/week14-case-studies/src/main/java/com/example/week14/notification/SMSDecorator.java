package com.example.week14.notification;

/**
 * =============================================================================
 * Notification Case Study: SMSDecorator (Concrete Decorator)
 * =============================================================================
 *
 * A decorator that adds SMS notification on top of whatever notification
 * service it wraps.
 *
 * Decorator Pattern Mechanics:
 *   1. SMSDecorator implements NotificationService (same interface as the
 *      component it wraps).
 *   2. It holds a reference to the wrapped NotificationService.
 *   3. In send(), it first delegates to the wrapped service, then adds
 *      its own SMS behavior.
 *
 * This allows stacking: BaseNotification -> SMSDecorator -> SlackDecorator
 * Each layer adds one channel.
 * =============================================================================
 */
public class SMSDecorator implements NotificationService {

    private final NotificationService wrapped;
    private final String phoneNumber;

    /**
     * Wraps an existing notification service and adds SMS capability.
     *
     * @param wrapped     the notification service to decorate
     * @param phoneNumber the phone number to send SMS to
     */
    public SMSDecorator(NotificationService wrapped, String phoneNumber) {
        if (wrapped == null) {
            throw new IllegalArgumentException("Wrapped service cannot be null.");
        }
        this.wrapped = wrapped;
        this.phoneNumber = phoneNumber;
    }

    /**
     * Sends the notification via all wrapped channels PLUS SMS.
     *
     * @param message the notification message
     */
    @Override
    public void send(String message) {
        // First, delegate to the wrapped service (email, or email+others)
        wrapped.send(message);

        // Then, add SMS notification
        System.out.println("    [SMS] Sending to " + phoneNumber
                + ": " + message);
    }

    @Override
    public String getChannelDescription() {
        return wrapped.getChannelDescription() + " + SMS(" + phoneNumber + ")";
    }
}
