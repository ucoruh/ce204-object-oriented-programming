package com.example.week09.abstractfactory;

/**
 * Concrete Product - WindowsButton
 *
 * Windows-style implementation of the Button interface.
 * Part of the "Windows" product family.
 */
public class WindowsButton implements Button {

    @Override
    public void paint() {
        System.out.println("    [Painting a Windows-style flat button]");
    }

    @Override
    public String getStyle() {
        return "Windows Fluent Design";
    }
}
