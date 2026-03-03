package com.example.week14.ecommerce;

/**
 * =============================================================================
 * E-Commerce Case Study: EmailNotifier (Concrete Observer)
 * =============================================================================
 *
 * A concrete observer that sends an email confirmation when an order
 * is placed.
 *
 * In a real system, this would use JavaMail, SendGrid, or similar.
 * Here we simulate the email sending for demonstration purposes.
 *
 * Design Notes:
 *   - This observer is completely decoupled from the Order class.
 *   - It can be added or removed at runtime without changing Order.
 *   - It only depends on the OrderObserver interface and the Order's
 *     public API (getOrderId, getTotalAmount, etc.).
 * =============================================================================
 */
public class EmailNotifier implements OrderObserver {

    private final String customerEmail;

    /**
     * Creates an email notifier for the given customer.
     *
     * @param customerEmail the customer's email address
     */
    public EmailNotifier(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    /**
     * Called when an order is placed. Sends a confirmation email.
     *
     * @param order the order that was placed
     */
    @Override
    public void onOrderPlaced(Order order) {
        System.out.println("    [EmailNotifier] Sending confirmation to "
                + customerEmail);
        System.out.println("    [EmailNotifier] Subject: Order "
                + order.getOrderId() + " Confirmation");
        System.out.println("    [EmailNotifier] Body: Thank you! Your order"
                + " of $" + String.format("%.2f", order.getTotalAmount())
                + " has been placed.");
    }
}
