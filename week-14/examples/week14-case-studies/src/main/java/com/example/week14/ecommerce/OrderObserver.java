package com.example.week14.ecommerce;

/**
 * =============================================================================
 * E-Commerce Case Study: OrderObserver (Observer Pattern)
 * =============================================================================
 *
 * Observer Pattern:
 *   Defines a one-to-many dependency between objects so that when one
 *   object (the Subject/Order) changes state, all its dependents
 *   (Observers) are notified automatically.
 *
 * Participants:
 *   - OrderObserver (this interface) -- the Observer
 *   - EmailNotifier, InventoryUpdater -- Concrete Observers
 *   - Order                           -- the Subject
 *
 * Benefits:
 *   - Order does not know (or care) who is listening.
 *   - New observers can be added without modifying Order.
 *   - Side effects (email, inventory, logging) are decoupled from core logic.
 * =============================================================================
 */
public interface OrderObserver {

    /**
     * Called when an order is placed.
     *
     * @param order the order that was placed
     */
    void onOrderPlaced(Order order);
}
