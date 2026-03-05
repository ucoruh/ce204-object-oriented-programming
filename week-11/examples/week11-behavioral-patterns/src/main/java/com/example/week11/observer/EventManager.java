package com.example.week11.observer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Observer Pattern - Subject / Publisher (Event Manager)
 *
 * Manages event subscriptions and notifications. Observers can
 * subscribe to specific event types and are notified only when
 * that event type occurs. This is a reusable event infrastructure
 * component that any concrete subject can use via composition.
 *
 * Structure:
 *   EventManager (Subject)
 *       - listeners: Map<String, List<EventListener>>
 *       + subscribe(eventType, listener): void
 *       + unsubscribe(eventType, listener): void
 *       + notify(eventType, data): void
 */
public class EventManager {

    /**
     * Map of event types to their registered listeners.
     * Key = event type string, Value = list of listeners.
     */
    private final Map<String, List<EventListener>> listeners = new HashMap<>();

    /**
     * Creates an EventManager that supports the given event types.
     *
     * @param operations the event types this manager supports (e.g., "open", "save")
     */
    public EventManager(String... operations) {
        for (String operation : operations) {
            listeners.put(operation, new ArrayList<>());
        }
    }

    /**
     * Subscribes a listener to a specific event type.
     *
     * @param eventType the event type to subscribe to
     * @param listener  the listener to add
     */
    public void subscribe(String eventType, EventListener listener) {
        List<EventListener> eventListeners = listeners.get(eventType);
        if (eventListeners != null) {
            eventListeners.add(listener);
        }
    }

    /**
     * Unsubscribes a listener from a specific event type.
     *
     * @param eventType the event type to unsubscribe from
     * @param listener  the listener to remove
     */
    public void unsubscribe(String eventType, EventListener listener) {
        List<EventListener> eventListeners = listeners.get(eventType);
        if (eventListeners != null) {
            eventListeners.remove(listener);
        }
    }

    /**
     * Notifies all listeners subscribed to the given event type.
     *
     * @param eventType the type of event that occurred
     * @param data      additional event data
     */
    public void notifyListeners(String eventType, String data) {
        List<EventListener> eventListeners = listeners.get(eventType);
        if (eventListeners != null) {
            for (EventListener listener : eventListeners) {
                listener.update(eventType, data);
            }
        }
    }
}
