package com.example.week09.factorymethod;

/**
 * Abstract Creator - Dialog
 *
 * Declares the factory method createButton() that returns a Button object.
 * Subclasses override this method to produce different types of buttons.
 *
 * Key insight: The creator's primary responsibility is NOT creating products.
 * It contains core business logic (renderWindow) that relies on the product
 * objects returned by the factory method. Subclasses can indirectly change
 * that business logic by overriding the factory method.
 */
public abstract class Dialog {

    /**
     * Factory Method - subclasses must override this to create specific buttons.
     *
     * @return a Button instance (Windows, Linux, etc.)
     */
    public abstract Button createButton();

    /**
     * Core business logic that uses the product created by the factory method.
     * Notice this method does NOT know which concrete Button it is working with.
     */
    public void renderWindow() {
        // Call the factory method to create a product object
        Button okButton = createButton();

        // Use the product
        System.out.println("    Dialog is rendering its window...");
        okButton.render();
        okButton.onClick();
    }
}
