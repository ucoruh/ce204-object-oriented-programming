package com.example.week03.interfaces;

/**
 * ============================================================================
 * DEMO 1: Basic Interfaces
 * ============================================================================
 *
 * An INTERFACE in Java is a reference type that defines a contract:
 *   - It specifies WHAT a class must do, but not HOW it does it.
 *   - All methods declared in an interface are implicitly public and abstract
 *     (before Java 8).
 *   - All fields declared in an interface are implicitly public, static, and final
 *     (i.e., they are constants).
 *   - A class "implements" an interface, promising to provide concrete
 *     implementations of all abstract methods.
 *
 * Key points covered:
 *   1. Declaring a simple interface
 *   2. Implementing a single interface
 *   3. Implementing multiple interfaces (Java supports this!)
 *   4. Using interface types for polymorphism
 *   5. Interface constants
 *
 * ============================================================================
 */
public class BasicInterfaceDemo {

    // -----------------------------------------------------------------------
    // 1. A simple interface with abstract methods
    // -----------------------------------------------------------------------
    // All methods here are implicitly "public abstract".
    // All fields here are implicitly "public static final".

    /**
     * Drawable represents anything that can be drawn on a canvas.
     * Any class implementing Drawable must provide a draw() method.
     */
    interface Drawable {
        // Interface constant (public static final by default)
        String CANVAS_TYPE = "2D";

        // Abstract method (public abstract by default)
        void draw();

        // Another abstract method
        String getShapeName();
    }

    // -----------------------------------------------------------------------
    // 2. Another simple interface
    // -----------------------------------------------------------------------

    /**
     * Resizable represents anything that can be resized.
     */
    interface Resizable {
        void resize(double factor);
        double getArea();
    }

    // -----------------------------------------------------------------------
    // 3. A third interface for coloring
    // -----------------------------------------------------------------------

    /**
     * Colorable represents anything that can have a color applied to it.
     */
    interface Colorable {
        void setColor(String color);
        String getColor();
    }

    // -----------------------------------------------------------------------
    // 4. Implementing a SINGLE interface
    // -----------------------------------------------------------------------

    /**
     * Circle implements Drawable.
     * It MUST provide concrete implementations of draw() and getShapeName().
     */
    static class Circle implements Drawable {
        private double radius;

        public Circle(double radius) {
            this.radius = radius;
        }

        // Implementing the abstract method from Drawable
        @Override
        public void draw() {
            System.out.println("    Drawing a circle with radius " + radius
                    + " on " + CANVAS_TYPE + " canvas.");
        }

        // Implementing the abstract method from Drawable
        @Override
        public String getShapeName() {
            return "Circle";
        }
    }

    // -----------------------------------------------------------------------
    // 5. Implementing MULTIPLE interfaces
    // -----------------------------------------------------------------------

    /**
     * Rectangle implements THREE interfaces: Drawable, Resizable, and Colorable.
     * Java allows a class to implement as many interfaces as it needs.
     * This is Java's way of achieving a form of "multiple inheritance of type".
     */
    static class Rectangle implements Drawable, Resizable, Colorable {
        private double width;
        private double height;
        private String color = "black";

        public Rectangle(double width, double height) {
            this.width = width;
            this.height = height;
        }

        // --- Drawable methods ---
        @Override
        public void draw() {
            System.out.println("    Drawing a " + color + " rectangle ("
                    + width + " x " + height + ") on " + CANVAS_TYPE + " canvas.");
        }

        @Override
        public String getShapeName() {
            return "Rectangle";
        }

        // --- Resizable methods ---
        @Override
        public void resize(double factor) {
            width *= factor;
            height *= factor;
            System.out.println("    Resized rectangle to " + width + " x " + height);
        }

        @Override
        public double getArea() {
            return width * height;
        }

        // --- Colorable methods ---
        @Override
        public void setColor(String color) {
            this.color = color;
            System.out.println("    Set rectangle color to: " + color);
        }

        @Override
        public String getColor() {
            return color;
        }
    }

    // -----------------------------------------------------------------------
    // 6. Using interface type for polymorphism
    // -----------------------------------------------------------------------

    /**
     * This method accepts ANY Drawable object.
     * It does not care whether the object is a Circle, Rectangle, or anything else.
     * This is the power of programming to an interface.
     */
    static void renderShape(Drawable shape) {
        System.out.println("    Rendering: " + shape.getShapeName());
        shape.draw();
    }

    // =======================================================================
    // DEMO METHOD
    // =======================================================================

    public static void demo() {
        // --- Section A: Single interface implementation ---
        System.out.println("  [A] Single Interface Implementation");
        System.out.println("  ------------------------------------");

        Circle circle = new Circle(5.0);
        circle.draw();
        System.out.println("    Shape name: " + circle.getShapeName());
        System.out.println("    Canvas type constant: " + Drawable.CANVAS_TYPE);
        System.out.println();

        // --- Section B: Multiple interface implementation ---
        System.out.println("  [B] Multiple Interface Implementation");
        System.out.println("  --------------------------------------");

        Rectangle rect = new Rectangle(10, 4);
        rect.draw();                       // From Drawable
        rect.setColor("blue");             // From Colorable
        rect.draw();                       // Redrawn with new color
        System.out.println("    Area before resize: " + rect.getArea());  // From Resizable
        rect.resize(2.0);                  // From Resizable
        System.out.println("    Area after resize: " + rect.getArea());
        System.out.println();

        // --- Section C: Interface polymorphism ---
        System.out.println("  [C] Interface Polymorphism");
        System.out.println("  --------------------------");

        // Both Circle and Rectangle are Drawable, so they can be stored
        // in a Drawable reference and passed to renderShape().
        Drawable shape1 = new Circle(3.0);
        Drawable shape2 = new Rectangle(6, 2);

        renderShape(shape1);
        renderShape(shape2);
        System.out.println();

        // --- Section D: instanceof check with interfaces ---
        System.out.println("  [D] instanceof with Interfaces");
        System.out.println("  --------------------------------");

        Object obj = new Rectangle(5, 5);
        System.out.println("    obj instanceof Drawable?   " + (obj instanceof Drawable));
        System.out.println("    obj instanceof Resizable?  " + (obj instanceof Resizable));
        System.out.println("    obj instanceof Colorable?  " + (obj instanceof Colorable));

        Object obj2 = new Circle(1.0);
        System.out.println("    circle instanceof Drawable?   " + (obj2 instanceof Drawable));
        System.out.println("    circle instanceof Resizable?  " + (obj2 instanceof Resizable));
        System.out.println();

        // --- Section E: Array of interface type ---
        System.out.println("  [E] Array of Interface Type");
        System.out.println("  ----------------------------");

        Drawable[] shapes = {
            new Circle(1.0),
            new Rectangle(3, 4),
            new Circle(2.5),
            new Rectangle(7, 3)
        };

        for (Drawable s : shapes) {
            System.out.print("    ");
            s.draw();
        }
    }
}
