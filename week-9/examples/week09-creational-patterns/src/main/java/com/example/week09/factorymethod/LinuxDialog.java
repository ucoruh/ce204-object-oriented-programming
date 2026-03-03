package com.example.week09.factorymethod;

/**
 * Concrete Creator - LinuxDialog
 *
 * Overrides the factory method to return a LinuxButton instance.
 * Demonstrates how adding a new platform only requires a new Dialog
 * subclass and a new Button implementation -- no existing code changes.
 */
public class LinuxDialog extends Dialog {

    /**
     * Factory method implementation that creates Linux/GTK-specific buttons.
     */
    @Override
    public Button createButton() {
        System.out.println("    LinuxDialog: Creating a Linux/GTK button.");
        return new LinuxButton();
    }
}
