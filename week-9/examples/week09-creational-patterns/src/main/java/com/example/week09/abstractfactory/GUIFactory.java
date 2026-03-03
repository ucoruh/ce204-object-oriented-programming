package com.example.week09.abstractfactory;

/**
 * Abstract Factory - GUIFactory
 *
 * Declares creation methods for each distinct product type (Button, Checkbox).
 * Concrete factories implement these methods to produce products that belong
 * to the same family/variant (all Windows or all Mac).
 *
 * The client code works with factories and products only through their
 * abstract interfaces, making the client independent of the concrete classes.
 */
public interface GUIFactory {

    /**
     * Creates a platform-specific button.
     */
    Button createButton();

    /**
     * Creates a platform-specific checkbox.
     */
    Checkbox createCheckbox();
}
