package com.example.week09.factorymethod;

/**
 * Product Interface - Button
 *
 * Declares the interface for the type of object the factory method creates.
 * All buttons must implement render() and onClick() regardless of their
 * platform-specific implementation.
 */
public interface Button {

    /**
     * Renders the button on screen.
     */
    void render();

    /**
     * Handles the click event on this button.
     */
    void onClick();
}
