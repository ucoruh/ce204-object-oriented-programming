package com.example.week11.observer;

/**
 * Observer Pattern - Observer Interface
 *
 * Defines the interface for objects that should be notified of
 * changes in the subject. All concrete observers implement this
 * interface to receive update notifications.
 *
 * Structure:
 *   <<interface>> EventListener
 *       + update(eventType, data): void
 */
public interface EventListener {

    /**
     * Called by the subject (publisher) when an event occurs.
     * Each observer implements this method to react to the event.
     *
     * @param eventType the type of event that occurred (e.g., "open", "save")
     * @param data      additional data about the event (e.g., filename)
     */
    void update(String eventType, String data);
}
