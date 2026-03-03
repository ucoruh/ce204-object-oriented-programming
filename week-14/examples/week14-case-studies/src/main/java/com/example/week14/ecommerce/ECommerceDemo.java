package com.example.week14.ecommerce;

/**
 * =============================================================================
 * E-Commerce Case Study: Full Demo
 * =============================================================================
 *
 * This demo ties together the entire e-commerce case study:
 *
 *   1. Create products and add them to a shopping cart.
 *   2. Choose a payment strategy (Credit Card or PayPal).
 *   3. Create an order and register observers (email, inventory).
 *   4. Place the order -- payment is processed via Strategy, and
 *      observers are notified automatically.
 *
 * Patterns Demonstrated:
 *   - Strategy Pattern: PaymentStrategy with CreditCardPayment, PayPalPayment
 *   - Observer Pattern: OrderObserver with EmailNotifier, InventoryUpdater
 *
 * This demonstrates how two patterns work together in a realistic scenario.
 * =============================================================================
 */
public class ECommerceDemo {

    /**
     * Runs the complete e-commerce demonstration.
     */
    public static void demo() {
        // =====================================================================
        // Step 1: Create products
        // =====================================================================
        System.out.println("  Step 1: Creating products...");
        Product laptop = new Product("P001", "Laptop Pro 15", 1299.99);
        Product mouse  = new Product("P002", "Wireless Mouse", 29.99);
        Product bag    = new Product("P003", "Laptop Bag", 49.99);
        System.out.println("    Created: " + laptop);
        System.out.println("    Created: " + mouse);
        System.out.println("    Created: " + bag);
        System.out.println();

        // =====================================================================
        // Step 2: Add products to cart
        // =====================================================================
        System.out.println("  Step 2: Building shopping cart...");
        ShoppingCart cart = new ShoppingCart();
        cart.addItem(laptop);
        cart.addItem(mouse);
        cart.addItem(bag);
        cart.printContents();
        System.out.println();

        // =====================================================================
        // Step 3: Place order with Credit Card (Strategy Pattern)
        // =====================================================================
        System.out.println("  Step 3: Placing order with Credit Card...");

        // Strategy: choose credit card payment
        PaymentStrategy creditCard = new CreditCardPayment(
                "4111111111111234", "Alice Johnson");

        // Create order and register observers
        Order order1 = new Order(cart, creditCard);
        order1.addObserver(new EmailNotifier("alice@example.com"));
        order1.addObserver(new InventoryUpdater());

        // Place the order -- Strategy processes payment, Observer notifies
        order1.placeOrder();
        System.out.println();

        // =====================================================================
        // Step 4: Place another order with PayPal (different Strategy)
        // =====================================================================
        System.out.println("  Step 4: Placing another order with PayPal...");

        // New cart with different items
        ShoppingCart cart2 = new ShoppingCart();
        cart2.addItem(new Product("P004", "USB-C Hub", 39.99));
        cart2.addItem(new Product("P005", "Monitor Stand", 89.99));

        // Strategy: choose PayPal this time
        PaymentStrategy paypal = new PayPalPayment("alice@example.com");

        Order order2 = new Order(cart2, paypal);
        order2.addObserver(new EmailNotifier("alice@example.com"));
        order2.addObserver(new InventoryUpdater());

        order2.placeOrder();
        System.out.println();

        // =====================================================================
        // Summary
        // =====================================================================
        System.out.println("  --- Order Summaries ---");
        order1.printSummary();
        System.out.println();
        order2.printSummary();
        System.out.println();
        System.out.println("  Key Insight: The Order class does not know HOW payment");
        System.out.println("  is processed (Strategy) or WHO is listening (Observer).");
        System.out.println("  Both concerns are fully decoupled.");
    }
}
