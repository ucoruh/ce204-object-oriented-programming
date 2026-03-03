package com.example.week09.abstractfactory;

/**
 * Concrete Product - MacCheckbox
 *
 * macOS-style implementation of the Checkbox interface.
 * Part of the "Mac" product family -- always compatible with MacButton.
 */
public class MacCheckbox implements Checkbox {

    private boolean checked = false;

    @Override
    public void paint() {
        String mark = checked ? "\u2713" : " ";
        System.out.println("    [Painting a macOS-style round checkbox: (" + mark + ")]");
    }

    @Override
    public void toggle() {
        checked = !checked;
        System.out.println("    [Mac checkbox toggled to: " + (checked ? "CHECKED" : "UNCHECKED") + "]");
    }
}
