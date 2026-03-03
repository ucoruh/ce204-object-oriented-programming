package com.example.week14.notification;

/**
 * =============================================================================
 * Notification Case Study: NotificationService (Component in Decorator Pattern)
 * =============================================================================
 *
 * Decorator Pattern:
 *   Attaches additional responsibilities to an object dynamically.
 *   Decorators provide a flexible alternative to subclassing for
 *   extending functionality.
 *
 * This interface is the Component in the Decorator pattern.  Both the
 * base notification (BaseNotification) and the decorators (SMSDecorator,
 * SlackDecorator) implement this interface.
 *
 * Participants:
 *   - NotificationService (this interface)    -- Component
 *   - BaseNotification                        -- Concrete Component
 *   - SMSDecorator, SlackDecorator            -- Concrete Decorators
 *   - NotificationFactory                     -- Factory for creating chains
 *
 * Benefits:
 *   - Notifications can be composed dynamically at runtime.
 *   - Each decorator adds one channel without modifying existing classes.
 *   - The client code works with the NotificationService interface and
 *     does not know how many decorators are wrapped around it.
 * =============================================================================
 */
public interface NotificationService {

    /**
     * Sends a notification with the given message.
     *
     * @param message the notification message to send
     */
    void send(String message);

    /**
     * Returns a description of the notification channels configured.
     */
    String getChannelDescription();
}
