package com.example.week09.abstractfactory;

/**
 * Abstract Product A - Button
 *
 * Each distinct product of a product family should have a base interface.
 * All variants of the product (WindowsButton, MacButton) must implement
 * this interface.
 */
public interface Button {

    /**
     * Paints/renders the button on screen.
     */
    void paint();

    /**
     * Returns a description of this button's style.
     */
    String getStyle();
}
