package com.example.week09.factorymethod;

/**
 * Concrete Product - WindowsButton
 *
 * Implements the Button interface with Windows-specific rendering and behavior.
 * This class is created by the WindowsDialog (Concrete Creator).
 */
public class WindowsButton implements Button {

    @Override
    public void render() {
        System.out.println("    [Rendering a Windows-style button with flat design]");
    }

    @Override
    public void onClick() {
        System.out.println("    [Windows button clicked - executing WinAPI callback]");
    }
}
