package com.example.week09.abstractfactory;

/**
 * Concrete Product - MacButton
 *
 * macOS-style implementation of the Button interface.
 * Part of the "Mac" product family.
 */
public class MacButton implements Button {

    @Override
    public void paint() {
        System.out.println("    [Painting a macOS-style rounded aqua button]");
    }

    @Override
    public String getStyle() {
        return "macOS Aqua Design";
    }
}
