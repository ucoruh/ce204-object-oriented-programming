package com.example.week14.ecommerce;

/**
 * =============================================================================
 * E-Commerce Case Study: Product
 * =============================================================================
 *
 * Represents a product in the e-commerce catalog.
 *
 * Design Notes:
 *   - Immutable value object: once created, a product's core properties
 *     do not change (name, price).
 *   - Uses encapsulation: private fields with public getters.
 *   - Validates invariants in the constructor (price must be positive).
 * =============================================================================
 */
public class Product {

    private final String id;
    private final String name;
    private final double price;

    /**
     * Creates a new product.
     *
     * @param id    unique product identifier
     * @param name  product display name
     * @param price product price (must be positive)
     * @throws IllegalArgumentException if price is not positive
     */
    public Product(String id, String name, double price) {
        if (id == null || id.isBlank()) {
            throw new IllegalArgumentException("Product ID cannot be empty.");
        }
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Product name cannot be empty.");
        }
        if (price <= 0) {
            throw new IllegalArgumentException(
                    "Price must be positive, got: " + price);
        }
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public double getPrice() { return price; }

    @Override
    public String toString() {
        return name + " ($" + String.format("%.2f", price) + ")";
    }
}
