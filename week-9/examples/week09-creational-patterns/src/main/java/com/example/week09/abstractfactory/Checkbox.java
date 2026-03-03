package com.example.week09.abstractfactory;

/**
 * Abstract Product B - Checkbox
 *
 * Another product family member. The abstract factory ensures that
 * Checkbox and Button from the same factory are always compatible
 * (e.g., both Windows-style or both Mac-style).
 */
public interface Checkbox {

    /**
     * Paints/renders the checkbox on screen.
     */
    void paint();

    /**
     * Toggles the checked state.
     */
    void toggle();
}
