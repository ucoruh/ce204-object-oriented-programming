package com.example.week04.classdiagram;

import java.util.ArrayList;
import java.util.List;

/**
 * CEN206 - Week 4: Generalization (Inheritance) Relationship
 *
 * In UML generalization is drawn as a solid line with a <b>hollow
 * triangle</b> arrowhead pointing from the subclass to the superclass.
 *
 * <pre>
 *                  ┌───────────┐
 *                  │   Shape   │  (abstract)
 *                  │───────────│
 *                  │ +area()   │
 *                  │ +draw()   │
 *                  └─────┬─────┘
 *             ┌──────────┼──────────┐
 *             ▽          ▽          ▽
 *       ┌──────────┐ ┌──────────┐ ┌──────────┐
 *       │  Circle  │ │Rectangle │ │ Triangle │
 *       └──────────┘ └──────────┘ └──────────┘
 * </pre>
 *
 * We also demonstrate an <b>interface realization</b> (dashed line
 * with hollow triangle) via the {@code Drawable} interface.
 */
public class Generalization {

    // ----------------------------------------------------------------
    // Interface (realization in UML)
    // ----------------------------------------------------------------

    /** Drawable -- realised by all shapes. */
    public interface Drawable {
        /** Draw the shape to the console. */
        void draw();
    }

    // ----------------------------------------------------------------
    // Abstract superclass
    // ----------------------------------------------------------------

    /** Abstract base mapped from the UML Shape class. */
    public static abstract class Shape implements Drawable {
        private final String color;

        protected Shape(String color) {
            this.color = color;
        }

        public String getColor() {
            return color;
        }

        /** Each concrete shape must compute its own area. */
        public abstract double area();

        @Override
        public String toString() {
            return getClass().getSimpleName()
                    + "(color=" + color
                    + ", area=" + String.format("%.2f", area()) + ")";
        }
    }

    // ----------------------------------------------------------------
    // Concrete subclasses
    // ----------------------------------------------------------------

    /** Circle -- inherits from Shape. */
    public static class Circle extends Shape {
        private final double radius;

        public Circle(String color, double radius) {
            super(color);
            this.radius = radius;
        }

        @Override
        public double area() {
            return Math.PI * radius * radius;
        }

        @Override
        public void draw() {
            System.out.println("  Drawing a " + getColor()
                    + " circle with radius " + radius);
        }
    }

    /** Rectangle -- inherits from Shape. */
    public static class Rectangle extends Shape {
        private final double width;
        private final double height;

        public Rectangle(String color, double width, double height) {
            super(color);
            this.width = width;
            this.height = height;
        }

        @Override
        public double area() {
            return width * height;
        }

        @Override
        public void draw() {
            System.out.println("  Drawing a " + getColor()
                    + " rectangle (" + width + " x " + height + ")");
        }
    }

    /** Triangle -- inherits from Shape. */
    public static class Triangle extends Shape {
        private final double base;
        private final double height;

        public Triangle(String color, double base, double height) {
            super(color);
            this.base = base;
            this.height = height;
        }

        @Override
        public double area() {
            return 0.5 * base * height;
        }

        @Override
        public void draw() {
            System.out.println("  Drawing a " + getColor()
                    + " triangle (base=" + base + ", h=" + height + ")");
        }
    }

    // ----------------------------------------------------------------
    // Demo
    // ----------------------------------------------------------------

    /** Runs the generalization / inheritance demonstration. */
    public static void demo() {
        // Polymorphism in action -- we refer to all shapes via the base type
        List<Shape> shapes = new ArrayList<>();
        shapes.add(new Circle("red", 5.0));
        shapes.add(new Rectangle("blue", 4.0, 6.0));
        shapes.add(new Triangle("green", 3.0, 7.0));

        for (Shape shape : shapes) {
            shape.draw();                 // polymorphic call
            System.out.println("    " + shape);  // toString shows area
        }

        // Demonstrate the Drawable interface (realization)
        System.out.println("\n  Using the Drawable interface reference:");
        for (Shape shape : shapes) {
            Drawable d = shape;   // upcast to interface
            d.draw();
        }
    }
}
