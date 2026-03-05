package com.example.week14.ecommerce;

/**
 * =============================================================================
 * E-Commerce Case Study: InventoryUpdater (Concrete Observer)
 * =============================================================================
 *
 * A concrete observer that updates the inventory system when an order
 * is placed.
 *
 * In a real system, this would call an inventory management service or
 * update a database.  Here we simulate the process for demonstration.
 *
 * Design Notes:
 *   - Completely independent of EmailNotifier and other observers.
 *   - Can be unit-tested in isolation by calling onOrderPlaced() directly.
 *   - Adding this observer to an Order does not require any code changes
 *     in the Order class.
 * =============================================================================
 */
public class InventoryUpdater implements OrderObserver {

    /**
     * Called when an order is placed. Updates inventory for each item.
     *
     * @param order the order that was placed
     */
    @Override
    public void onOrderPlaced(Order order) {
        System.out.println("    [InventoryUpdater] Updating inventory for order "
                + order.getOrderId());
        for (Product item : order.getItems()) {
            System.out.println("    [InventoryUpdater]   Reducing stock for: "
                    + item.getName() + " (ID: " + item.getId() + ")");
        }
        System.out.println("    [InventoryUpdater] Inventory updated successfully.");
    }
}
