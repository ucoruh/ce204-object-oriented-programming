package com.example.week09.abstractfactory;

/**
 * Concrete Factory - WindowsFactory
 *
 * Produces a family of Windows-style GUI components.
 * All products created by this factory are mutually compatible
 * (WindowsButton + WindowsCheckbox look consistent together).
 */
public class WindowsFactory implements GUIFactory {

    @Override
    public Button createButton() {
        System.out.println("    WindowsFactory: Creating a Windows button.");
        return new WindowsButton();
    }

    @Override
    public Checkbox createCheckbox() {
        System.out.println("    WindowsFactory: Creating a Windows checkbox.");
        return new WindowsCheckbox();
    }
}
