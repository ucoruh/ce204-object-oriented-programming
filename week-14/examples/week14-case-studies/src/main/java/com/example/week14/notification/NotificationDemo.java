package com.example.week14.notification;

/**
 * =============================================================================
 * Notification Case Study: Full Demo
 * =============================================================================
 *
 * This demo ties together the Decorator and Factory patterns:
 *
 *   1. Manual decoration: building decorator chains step by step to
 *      show how decorators wrap each other.
 *
 *   2. Factory creation: using NotificationFactory to hide the
 *      complexity and create pre-configured notification services.
 *
 * Patterns Demonstrated:
 *   - Decorator Pattern: NotificationService with BaseNotification,
 *     SMSDecorator, SlackDecorator
 *   - Factory Pattern: NotificationFactory for building decorator chains
 * =============================================================================
 */
public class NotificationDemo {

    /**
     * Runs the complete notification demonstration.
     */
    public static void demo() {
        // =====================================================================
        // Part A: Manual Decorator Composition
        // =====================================================================
        System.out.println("  Part A: Manual Decorator Composition");
        System.out.println("  (Building decorator chains step by step)");
        System.out.println();

        // Configuration 1: Email only (no decorators)
        System.out.println("  Config 1: Email only");
        NotificationService emailOnly = new BaseNotification("user@example.com");
        System.out.println("    Channels: " + emailOnly.getChannelDescription());
        emailOnly.send("Server is healthy.");
        System.out.println();

        // Configuration 2: Email + SMS (one decorator)
        System.out.println("  Config 2: Email + SMS");
        NotificationService emailAndSms = new SMSDecorator(
                new BaseNotification("user@example.com"),
                "+1-555-0100");
        System.out.println("    Channels: " + emailAndSms.getChannelDescription());
        emailAndSms.send("CPU usage above 80%.");
        System.out.println();

        // Configuration 3: Email + SMS + Slack (two decorators stacked)
        System.out.println("  Config 3: Email + SMS + Slack");
        NotificationService allChannels = new SlackDecorator(
                new SMSDecorator(
                        new BaseNotification("admin@example.com"),
                        "+1-555-0200"),
                "#ops-alerts");
        System.out.println("    Channels: " + allChannels.getChannelDescription());
        allChannels.send("CRITICAL: Database connection lost!");
        System.out.println();

        // =====================================================================
        // Part B: Factory-Created Notification Services
        // =====================================================================
        System.out.println("  Part B: Factory-Created Notification Services");
        System.out.println("  (Factory hides decorator chain construction)");
        System.out.println();

        // Factory: email only
        System.out.println("  Factory: emailOnly()");
        NotificationService factoryEmail =
                NotificationFactory.emailOnly("dev@example.com");
        System.out.println("    Channels: " + factoryEmail.getChannelDescription());
        factoryEmail.send("Build succeeded.");
        System.out.println();

        // Factory: email + SMS (no Slack)
        System.out.println("  Factory: create(email, sms=true, slack=false)");
        NotificationService factoryEmailSms = NotificationFactory.create(
                "oncall@example.com", true, "+1-555-0300", false, null);
        System.out.println("    Channels: "
                + factoryEmailSms.getChannelDescription());
        factoryEmailSms.send("Warning: Disk usage at 90%.");
        System.out.println();

        // Factory: all channels
        System.out.println("  Factory: allChannels()");
        NotificationService factoryAll = NotificationFactory.allChannels(
                "admin@example.com", "+1-555-0400", "#critical-alerts");
        System.out.println("    Channels: " + factoryAll.getChannelDescription());
        factoryAll.send("ALERT: Service outage detected!");
        System.out.println();

        // =====================================================================
        // Summary
        // =====================================================================
        System.out.println("  --- Summary ---");
        System.out.println("  Decorator: compose notification channels dynamically.");
        System.out.println("  Factory: hide decorator chain construction from clients.");
        System.out.println("  Adding a new channel (e.g., Push) requires only:");
        System.out.println("    1. A new PushDecorator class");
        System.out.println("    2. A new flag in NotificationFactory");
        System.out.println("  No existing classes need modification (Open/Closed).");
    }
}
