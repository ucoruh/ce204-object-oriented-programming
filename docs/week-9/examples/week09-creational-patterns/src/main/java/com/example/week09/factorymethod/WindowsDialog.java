package com.example.week09.factorymethod;

/**
 * Concrete Creator - WindowsDialog
 *
 * Overrides the factory method to return a WindowsButton instance.
 * The rest of the dialog behavior (renderWindow) is inherited from the
 * abstract Dialog class and works with the WindowsButton transparently.
 */
public class WindowsDialog extends Dialog {

    /**
     * Factory method implementation that creates Windows-specific buttons.
     */
    @Override
    public Button createButton() {
        System.out.println("    WindowsDialog: Creating a Windows button.");
        return new WindowsButton();
    }
}
