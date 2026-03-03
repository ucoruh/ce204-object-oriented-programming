package com.example.week14.ecommerce;

import java.util.ArrayList;
import java.util.List;

/**
 * =============================================================================
 * E-Commerce Case Study: Order (Subject in Observer Pattern + Context in Strategy)
 * =============================================================================
 *
 * The Order class plays two pattern roles:
 *
 *   1. Context (Strategy Pattern):
 *      It holds a PaymentStrategy and delegates payment processing to it.
 *      The specific payment method is injected, not hard-coded.
 *
 *   2. Subject (Observer Pattern):
 *      It maintains a list of OrderObserver objects and notifies them
 *      when an order is placed.  This decouples side effects (email,
 *      inventory) from the order processing logic.
 *
 * Design Notes:
 *   - The order ID is generated from a simple counter for demonstration.
 *   - In production, you would use a UUID or database-generated ID.
 *   - Items are copied from the ShoppingCart so the order is self-contained.
 * =============================================================================
 */
public class Order {

    private static int nextId = 1000;

    private final String orderId;
    private final List<Product> items;
    private final double totalAmount;
    private final PaymentStrategy paymentStrategy;
    private final List<OrderObserver> observers = new ArrayList<>();
    private boolean paid = false;

    /**
     * Creates an order from a shopping cart with a chosen payment method.
     *
     * @param cart            the shopping cart (items are copied)
     * @param paymentStrategy the payment method to use
     */
    public Order(ShoppingCart cart, PaymentStrategy paymentStrategy) {
        if (cart == null || cart.size() == 0) {
            throw new IllegalArgumentException("Cart cannot be empty.");
        }
        if (paymentStrategy == null) {
            throw new IllegalArgumentException("Payment strategy is required.");
        }
        this.orderId = "ORD-" + (nextId++);
        this.items = new ArrayList<>(cart.getItems());  // defensive copy
        this.totalAmount = cart.getTotal();
        this.paymentStrategy = paymentStrategy;
    }

    // =========================================================================
    // Observer management
    // =========================================================================

    /**
     * Registers an observer to be notified when the order is placed.
     */
    public void addObserver(OrderObserver observer) {
        observers.add(observer);
    }

    /**
     * Removes a previously registered observer.
     */
    public void removeObserver(OrderObserver observer) {
        observers.remove(observer);
    }

    /**
     * Notifies all registered observers that the order has been placed.
     */
    private void notifyObservers() {
        for (OrderObserver observer : observers) {
            observer.onOrderPlaced(this);
        }
    }

    // =========================================================================
    // Order processing
    // =========================================================================

    /**
     * Processes the order: charges payment (Strategy) and notifies
     * observers (Observer).
     *
     * @return true if the order was successfully placed
     */
    public boolean placeOrder() {
        System.out.println("    Processing order " + orderId + "...");
        System.out.println("    Payment method: " + paymentStrategy.getMethodName());

        // Strategy pattern: delegate to the chosen payment method
        boolean success = paymentStrategy.pay(totalAmount);

        if (success) {
            this.paid = true;
            System.out.println("    Order " + orderId + " placed successfully!");

            // Observer pattern: notify all registered observers
            notifyObservers();
        } else {
            System.out.println("    Order " + orderId + " FAILED: payment declined.");
        }

        return success;
    }

    // =========================================================================
    // Getters
    // =========================================================================

    public String getOrderId() { return orderId; }
    public List<Product> getItems() { return List.copyOf(items); }
    public double getTotalAmount() { return totalAmount; }
    public boolean isPaid() { return paid; }

    /**
     * Prints a summary of the order.
     */
    public void printSummary() {
        System.out.println("    Order: " + orderId);
        System.out.println("    Items: " + items.size());
        for (Product item : items) {
            System.out.println("      - " + item);
        }
        System.out.println("    Total: $" + String.format("%.2f", totalAmount));
        System.out.println("    Paid:  " + (paid ? "YES" : "NO"));
    }
}
