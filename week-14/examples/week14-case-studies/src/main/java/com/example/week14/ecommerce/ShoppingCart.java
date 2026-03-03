package com.example.week14.ecommerce;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * =============================================================================
 * E-Commerce Case Study: ShoppingCart
 * =============================================================================
 *
 * Manages a collection of products that a customer intends to purchase.
 *
 * Design Notes:
 *   - Uses encapsulation: the internal list is not exposed directly.
 *     getItems() returns an unmodifiable view.
 *   - Provides domain-specific operations: addItem, removeItem, getTotal.
 *   - This is a "has-a" relationship with Product (composition, not inheritance).
 * =============================================================================
 */
public class ShoppingCart {

    private final List<Product> items = new ArrayList<>();

    /**
     * Adds a product to the cart.
     *
     * @param product the product to add
     */
    public void addItem(Product product) {
        if (product == null) {
            throw new IllegalArgumentException("Cannot add null product.");
        }
        items.add(product);
    }

    /**
     * Removes the first occurrence of a product with the given ID.
     *
     * @param productId the ID of the product to remove
     * @return true if a product was removed, false if not found
     */
    public boolean removeItem(String productId) {
        return items.removeIf(p -> p.getId().equals(productId));
    }

    /**
     * Returns an unmodifiable view of the items in the cart.
     */
    public List<Product> getItems() {
        return Collections.unmodifiableList(items);
    }

    /**
     * Calculates the total price of all items in the cart.
     */
    public double getTotal() {
        double total = 0;
        for (Product item : items) {
            total += item.getPrice();
        }
        return total;
    }

    /**
     * Returns the number of items in the cart.
     */
    public int size() {
        return items.size();
    }

    /**
     * Removes all items from the cart.
     */
    public void clear() {
        items.clear();
    }

    /**
     * Prints the cart contents.
     */
    public void printContents() {
        if (items.isEmpty()) {
            System.out.println("    (cart is empty)");
            return;
        }
        for (int i = 0; i < items.size(); i++) {
            System.out.println("    " + (i + 1) + ". " + items.get(i));
        }
        System.out.println("    ---------------------------------");
        System.out.println("    Total: $" + String.format("%.2f", getTotal()));
    }
}
