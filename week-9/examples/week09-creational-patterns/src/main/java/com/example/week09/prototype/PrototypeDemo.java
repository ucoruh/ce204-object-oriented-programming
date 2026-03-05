package com.example.week09.prototype;

/**
 * ============================================================================
 * PROTOTYPE PATTERN - Demo
 * ============================================================================
 *
 * Intent:
 *   Specify the kinds of objects to create using a prototypical instance,
 *   and create new objects by copying (cloning) this prototype.
 *
 * Structure:
 *   - Prototype (Shape)         : Declares the cloning interface
 *   - ConcretePrototype (Circle, Rectangle) : Implements cloning
 *   - Registry (ShapeCache)     : Stores pre-built prototypes for lookup
 *   - Client                    : Creates objects by asking a prototype to clone
 *
 * When to use:
 *   - When object creation is expensive and a similar object already exists
 *   - When you want to avoid building a class hierarchy of factories
 *   - When instances of a class can have only a few different states
 *   - When the client should not know the concrete class of the cloned object
 *
 * Real-world examples:
 *   - java.lang.Object.clone()
 *   - Copying cells in a spreadsheet
 *   - Duplicating layers in a graphics editor
 * ============================================================================
 */
public class PrototypeDemo {

    public static void demo() {
        System.out.println("=============================================================");
        System.out.println("  4. PROTOTYPE PATTERN");
        System.out.println("=============================================================");
        System.out.println();
        System.out.println("  Scenario: A graphics editor maintains a palette of reusable");
        System.out.println("  shape templates. Users clone shapes from the palette and");
        System.out.println("  customize them, rather than building each shape from scratch.");
        System.out.println();

        // --- 1) Direct cloning ---
        System.out.println("  >> Direct Cloning:");
        Circle original = new Circle();
        original.setColor("Red");
        original.setX(10);
        original.setY(20);
        original.setRadius(50.0);
        System.out.println("    Original : " + original);

        Circle cloned = (Circle) original.cloneShape();
        System.out.println("    Cloned   : " + cloned);

        // Verify they are different objects with equal state
        System.out.println("    Same object?  " + (original == cloned));        // false
        System.out.println("    Equal state?  " + original.equals(cloned));     // true

        // Modify the clone -- original is unaffected
        cloned.setColor("Yellow");
        cloned.setX(100);
        System.out.println("    After modifying clone:");
        System.out.println("    Original : " + original);
        System.out.println("    Cloned   : " + cloned);
        System.out.println();

        // --- 2) Using a Prototype Registry (ShapeCache) ---
        System.out.println("  >> Using Prototype Registry (ShapeCache):");
        ShapeCache cache = new ShapeCache();
        cache.loadDefaults();

        Shape shape1 = cache.get("red-circle");
        System.out.println("    Clone of 'red-circle'    : " + shape1);

        Shape shape2 = cache.get("blue-rectangle");
        System.out.println("    Clone of 'blue-rectangle': " + shape2);

        Shape shape3 = cache.get("green-circle");
        System.out.println("    Clone of 'green-circle'  : " + shape3);

        // Clones are independent copies
        shape1.setColor("Purple");
        shape1.setX(999);
        Shape shape4 = cache.get("red-circle");
        System.out.println();
        System.out.println("    After modifying first clone, get another from cache:");
        System.out.println("    Modified clone : " + shape1);
        System.out.println("    Fresh clone    : " + shape4);
        System.out.println("    (Cache prototype remains unchanged)");
        System.out.println();

        // Key takeaway
        System.out.println("  KEY TAKEAWAY:");
        System.out.println("  - Cloning creates a new object without calling a constructor.");
        System.out.println("  - Each clone is independent -- modifying one does not affect");
        System.out.println("    the original or other clones.");
        System.out.println("  - The ShapeCache registry lets clients clone by key, without");
        System.out.println("    knowing the concrete class (Circle, Rectangle, etc.).");
        System.out.println("  - Useful when object setup is expensive or complex.");
        System.out.println();
    }
}
