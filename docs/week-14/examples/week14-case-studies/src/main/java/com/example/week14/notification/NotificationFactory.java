package com.example.week14.notification;

/**
 * =============================================================================
 * Notification Case Study: NotificationFactory (Factory Pattern)
 * =============================================================================
 *
 * Factory Pattern:
 *   Provides a method for creating objects without specifying the exact
 *   class of object that will be created.  Here, the factory builds
 *   decorator chains based on configuration flags.
 *
 * Combining Decorator + Factory:
 *   - The Decorator pattern provides flexible, composable notifications.
 *   - The Factory pattern hides the complexity of building decorator chains.
 *   - Client code simply says: "I want email + SMS + Slack" and gets
 *     a ready-to-use NotificationService.
 *
 * Benefits:
 *   - Client code does not need to know about decorator wrapping order.
 *   - Configuration can come from user preferences, a database, or a file.
 *   - Adding a new channel (e.g., Push) requires only a new decorator
 *     and a new flag in the factory -- no changes to existing code.
 * =============================================================================
 */
public class NotificationFactory {

    /**
     * Creates a NotificationService with the requested channels.
     *
     * The base channel is always email.  SMS and Slack are added
     * as decorators based on the boolean flags.
     *
     * @param email        the recipient's email address (always used)
     * @param enableSMS    whether to add SMS notifications
     * @param phoneNumber  the phone number (required if enableSMS is true)
     * @param enableSlack  whether to add Slack notifications
     * @param slackChannel the Slack channel (required if enableSlack is true)
     * @return a fully configured NotificationService
     */
    public static NotificationService create(String email,
                                              boolean enableSMS,
                                              String phoneNumber,
                                              boolean enableSlack,
                                              String slackChannel) {
        // Start with the base email notification
        NotificationService service = new BaseNotification(email);

        // Wrap with SMS decorator if requested
        if (enableSMS) {
            service = new SMSDecorator(service, phoneNumber);
        }

        // Wrap with Slack decorator if requested
        if (enableSlack) {
            service = new SlackDecorator(service, slackChannel);
        }

        return service;
    }

    /**
     * Convenience method: creates an email-only notification service.
     *
     * @param email the recipient's email address
     * @return a NotificationService that sends only emails
     */
    public static NotificationService emailOnly(String email) {
        return create(email, false, null, false, null);
    }

    /**
     * Convenience method: creates a notification service with all channels.
     *
     * @param email        the recipient's email
     * @param phoneNumber  the SMS phone number
     * @param slackChannel the Slack channel
     * @return a NotificationService with email + SMS + Slack
     */
    public static NotificationService allChannels(String email,
                                                   String phoneNumber,
                                                   String slackChannel) {
        return create(email, true, phoneNumber, true, slackChannel);
    }
}
