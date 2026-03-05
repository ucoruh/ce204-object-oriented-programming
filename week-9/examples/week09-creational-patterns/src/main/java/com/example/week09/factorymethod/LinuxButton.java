package com.example.week09.factorymethod;

/**
 * Concrete Product - LinuxButton
 *
 * Implements the Button interface with Linux/GTK-specific rendering and behavior.
 * This class is created by the LinuxDialog (Concrete Creator).
 */
public class LinuxButton implements Button {

    @Override
    public void render() {
        System.out.println("    [Rendering a Linux/GTK-style button with rounded edges]");
    }

    @Override
    public void onClick() {
        System.out.println("    [Linux button clicked - emitting GTK signal]");
    }
}
