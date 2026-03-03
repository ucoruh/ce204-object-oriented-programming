package com.example.week09.abstractfactory;

/**
 * ============================================================================
 * ABSTRACT FACTORY PATTERN - Demo
 * ============================================================================
 *
 * Intent:
 *   Provide an interface for creating families of related or dependent
 *   objects without specifying their concrete classes.
 *
 * Structure:
 *   - AbstractFactory (GUIFactory)   : Declares creation methods for each product
 *   - ConcreteFactory (WindowsFactory, MacFactory) : Creates a product family
 *   - AbstractProduct (Button, Checkbox) : Declares product interfaces
 *   - ConcreteProduct (WindowsButton, MacButton, ...) : Implements products
 *
 * Difference from Factory Method:
 *   - Factory Method creates ONE product; Abstract Factory creates a FAMILY.
 *   - Factory Method uses inheritance; Abstract Factory uses composition.
 *   - Abstract Factory guarantees that products from the same factory are
 *     compatible with each other.
 *
 * When to use:
 *   - When a system should be independent of how its products are created
 *   - When a system should be configured with one of multiple families
 *   - When a family of related product objects must be used together
 *   - When you want to enforce that products from the same family match
 *
 * Real-world examples:
 *   - javax.xml.parsers.DocumentBuilderFactory
 *   - javax.xml.transform.TransformerFactory
 *   - Cross-platform UI toolkits (Swing look-and-feels)
 * ============================================================================
 */
public class AbstractFactoryDemo {

    /**
     * Client code that works with factories and products only through
     * abstract interfaces. This method does NOT know whether it is dealing
     * with Windows or Mac widgets -- it just uses the factory it receives.
     */
    private static void buildUI(GUIFactory factory) {
        Button button = factory.createButton();
        Checkbox checkbox = factory.createCheckbox();

        System.out.println("    --- Rendering the UI ---");
        button.paint();
        System.out.println("    Button style: " + button.getStyle());
        checkbox.paint();
        checkbox.toggle();
        checkbox.paint();
    }

    public static void demo() {
        System.out.println("=============================================================");
        System.out.println("  2. ABSTRACT FACTORY PATTERN");
        System.out.println("=============================================================");
        System.out.println();
        System.out.println("  Scenario: A cross-platform app needs to create matching");
        System.out.println("  sets of UI widgets. All widgets in a set must be from the");
        System.out.println("  same OS family (Windows or Mac) to look consistent.");
        System.out.println();

        // --- Windows UI family ---
        System.out.println("  >> Building Windows UI:");
        GUIFactory windowsFactory = new WindowsFactory();
        buildUI(windowsFactory);
        System.out.println();

        // --- Mac UI family ---
        System.out.println("  >> Building macOS UI:");
        GUIFactory macFactory = new MacFactory();
        buildUI(macFactory);
        System.out.println();

        // Key takeaway
        System.out.println("  KEY TAKEAWAY:");
        System.out.println("  - The client (buildUI) never references concrete classes.");
        System.out.println("  - Swapping the factory changes the ENTIRE product family.");
        System.out.println("  - Windows buttons always come with Windows checkboxes, etc.");
        System.out.println("  - Compared to Factory Method, this pattern manages families,");
        System.out.println("    not just single products.");
        System.out.println();
    }
}
