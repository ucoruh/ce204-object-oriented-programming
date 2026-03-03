package com.example.week09.abstractfactory;

/**
 * Concrete Product - WindowsCheckbox
 *
 * Windows-style implementation of the Checkbox interface.
 * Part of the "Windows" product family -- always compatible with WindowsButton.
 */
public class WindowsCheckbox implements Checkbox {

    private boolean checked = false;

    @Override
    public void paint() {
        String mark = checked ? "X" : " ";
        System.out.println("    [Painting a Windows-style checkbox: [" + mark + "]]");
    }

    @Override
    public void toggle() {
        checked = !checked;
        System.out.println("    [Windows checkbox toggled to: " + (checked ? "CHECKED" : "UNCHECKED") + "]");
    }
}
