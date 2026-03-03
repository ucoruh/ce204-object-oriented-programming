package com.example.week09.abstractfactory;

/**
 * Concrete Factory - MacFactory
 *
 * Produces a family of macOS-style GUI components.
 * All products created by this factory are mutually compatible
 * (MacButton + MacCheckbox look consistent together).
 */
public class MacFactory implements GUIFactory {

    @Override
    public Button createButton() {
        System.out.println("    MacFactory: Creating a macOS button.");
        return new MacButton();
    }

    @Override
    public Checkbox createCheckbox() {
        System.out.println("    MacFactory: Creating a macOS checkbox.");
        return new MacCheckbox();
    }
}
