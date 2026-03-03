package com.example.week14.notification;

/**
 * =============================================================================
 * Notification Case Study: SlackDecorator (Concrete Decorator)
 * =============================================================================
 *
 * A decorator that adds Slack notification on top of whatever notification
 * service it wraps.
 *
 * Like SMSDecorator, this class:
 *   1. Implements NotificationService (same interface).
 *   2. Wraps another NotificationService.
 *   3. Delegates to the wrapped service, then adds Slack-specific behavior.
 *
 * Decorators can be stacked in any order:
 *   - Email + SMS + Slack
 *   - Email + Slack  (no SMS)
 *   - Email + SMS    (no Slack)
 *   - Email only     (no decorators)
 * =============================================================================
 */
public class SlackDecorator implements NotificationService {

    private final NotificationService wrapped;
    private final String slackChannel;

    /**
     * Wraps an existing notification service and adds Slack capability.
     *
     * @param wrapped      the notification service to decorate
     * @param slackChannel the Slack channel to post to (e.g., "#alerts")
     */
    public SlackDecorator(NotificationService wrapped, String slackChannel) {
        if (wrapped == null) {
            throw new IllegalArgumentException("Wrapped service cannot be null.");
        }
        this.wrapped = wrapped;
        this.slackChannel = slackChannel;
    }

    /**
     * Sends the notification via all wrapped channels PLUS Slack.
     *
     * @param message the notification message
     */
    @Override
    public void send(String message) {
        // First, delegate to the wrapped service
        wrapped.send(message);

        // Then, add Slack notification
        System.out.println("    [Slack] Posting to " + slackChannel
                + ": " + message);
    }

    @Override
    public String getChannelDescription() {
        return wrapped.getChannelDescription()
                + " + Slack(" + slackChannel + ")";
    }
}
