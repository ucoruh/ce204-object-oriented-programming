package com.example.week02.polymorphism;

/**
 * ==========================================================================
 * DEMO 5: Method Overriding
 * ==========================================================================
 *
 * METHOD OVERRIDING means a subclass provides its own implementation of
 * a method that is already defined in the parent class.
 *
 * Rules for Overriding:
 *   1. Method signature (name + parameters) must be EXACTLY the same
 *   2. Return type must be same or a subtype (covariant return type)
 *   3. Access modifier can be SAME or LESS restrictive (not MORE)
 *   4. Cannot override static methods (that's method hiding)
 *   5. Cannot override final methods
 *   6. Cannot override private methods (they're not inherited)
 *
 * @Override Annotation:
 *   - Not required but STRONGLY recommended
 *   - Compiler checks that the method actually overrides a parent method
 *   - Prevents accidental method signature typos
 *
 * Overriding vs Overloading:
 *   - OVERRIDING: same name, same parameters, different class (parent/child)
 *   - OVERLOADING: same name, different parameters, same class
 */
public class MethodOverridingDemo {

    // ======================================================================
    // Example 1: Basic method overriding
    // ======================================================================

    static class Shape {
        String name;

        Shape(String name) {
            this.name = name;
        }

        // This method will be overridden by subclasses
        double area() {
            return 0.0;  // Base implementation
        }

        String describe() {
            return "Shape: " + name + " with area " + String.format("%.2f", area());
        }
    }

    static class Circle extends Shape {
        double radius;

        Circle(double radius) {
            super("Circle");
            this.radius = radius;
        }

        // OVERRIDES parent's area() method
        @Override
        double area() {
            return Math.PI * radius * radius;
        }

        // OVERRIDES parent's describe() method
        @Override
        String describe() {
            return "Circle: radius=" + radius + ", area=" + String.format("%.2f", area());
        }
    }

    static class Rectangle extends Shape {
        double width, height;

        Rectangle(double width, double height) {
            super("Rectangle");
            this.width = width;
            this.height = height;
        }

        @Override
        double area() {
            return width * height;
        }

        @Override
        String describe() {
            return "Rectangle: " + width + "x" + height + ", area=" + String.format("%.2f", area());
        }
    }

    static class Triangle extends Shape {
        double base, height;

        Triangle(double base, double height) {
            super("Triangle");
            this.base = base;
            this.height = height;
        }

        @Override
        double area() {
            return 0.5 * base * height;
        }

        @Override
        String describe() {
            return "Triangle: base=" + base + ", height=" + height
                    + ", area=" + String.format("%.2f", area());
        }
    }

    // ======================================================================
    // Example 2: Covariant return types
    // ======================================================================

    /**
     * Covariant return type: the overriding method can return a SUBTYPE
     * of the type returned by the parent method.
     */
    static class Animal {
        String name;

        Animal(String name) {
            this.name = name;
        }

        // Returns Animal
        Animal create(String name) {
            return new Animal(name);
        }

        @Override
        public String toString() {
            return "Animal(" + name + ")";
        }
    }

    static class Dog extends Animal {
        String breed;

        Dog(String name, String breed) {
            super(name);
            this.breed = breed;
        }

        // COVARIANT RETURN TYPE: returns Dog (a subtype of Animal)
        // This is valid because Dog IS-A Animal
        @Override
        Dog create(String name) {
            return new Dog(name, "Unknown");
        }

        @Override
        public String toString() {
            return "Dog(" + name + ", " + breed + ")";
        }
    }

    // ======================================================================
    // Example 3: Access modifier rules in overriding
    // ======================================================================

    static class Base {
        // Protected method
        protected String getInfo() {
            return "Base info";
        }

        // Package-private method
        String getData() {
            return "Base data";
        }
    }

    static class Derived extends Base {
        // Can make it MORE accessible (protected -> public) - VALID
        @Override
        public String getInfo() {
            return "Derived info (now public!)";
        }

        // Can keep the same access level - VALID
        @Override
        String getData() {
            return "Derived data";
        }

        // INVALID: Cannot make it LESS accessible (protected -> private)
        // @Override
        // private String getInfo() { ... }  // COMPILE ERROR!
    }

    // ======================================================================
    // Example 4: The @Override annotation importance
    // ======================================================================

    static class Vehicle {
        int speed;

        void accelerate(int amount) {
            speed += amount;
        }

        // Method to be overridden
        String getType() {
            return "Generic Vehicle";
        }
    }

    static class ElectricCar extends Vehicle {

        // WITH @Override - compiler checks this actually overrides something
        @Override
        String getType() {
            return "Electric Car";
        }

        // WITHOUT @Override - this is a NEW method, NOT an override!
        // If you misspell the method name, no error without @Override
        // String gettype() { return "Oops"; }  // This would be a typo!

        // @Override catches typos:
        // @Override
        // String gettype() { return "Oops"; }  // COMPILE ERROR! No such method in parent
    }

    // ======================================================================
    // Demo method
    // ======================================================================

    public static void demo() {

        // ------------------------------------------------------------------
        // Part 1: Basic Method Overriding
        // ------------------------------------------------------------------
        System.out.println("[Part 1: Basic Method Overriding]");
        System.out.println();

        Shape[] shapes = {
            new Circle(5.0),
            new Rectangle(4.0, 6.0),
            new Triangle(3.0, 8.0)
        };

        for (Shape shape : shapes) {
            // Each shape's describe() and area() call their OVERRIDDEN version
            System.out.println("  " + shape.describe());
        }

        System.out.println();
        System.out.println("  Even though we use Shape references, the OVERRIDDEN");
        System.out.println("  methods are called (this is polymorphism in action!)");

        // ------------------------------------------------------------------
        // Part 2: Covariant Return Types
        // ------------------------------------------------------------------
        System.out.println();
        System.out.println("[Part 2: Covariant Return Types]");
        System.out.println();

        Animal animal = new Animal("Generic");
        Dog dog = new Dog("Rex", "Shepherd");

        Animal created1 = animal.create("NewAnimal");
        Dog created2 = dog.create("NewDog");  // Returns Dog, not just Animal!

        System.out.println("  animal.create() returns: " + created1);
        System.out.println("  dog.create() returns:    " + created2);
        System.out.println();
        System.out.println("  Dog.create() has covariant return type:");
        System.out.println("  Parent returns Animal, child returns Dog (Dog IS-A Animal)");

        // ------------------------------------------------------------------
        // Part 3: Access Modifier Rules
        // ------------------------------------------------------------------
        System.out.println();
        System.out.println("[Part 3: Access Modifier Rules in Overriding]");
        System.out.println();

        Derived derived = new Derived();
        System.out.println("  derived.getInfo(): " + derived.getInfo());
        System.out.println("  derived.getData(): " + derived.getData());
        System.out.println();
        System.out.println("  Overriding can WIDEN access (protected -> public)");
        System.out.println("  Overriding CANNOT NARROW access (protected -> private)");
        System.out.println();
        System.out.println("  Access levels from narrow to wide:");
        System.out.println("  private -> default -> protected -> public");

        // ------------------------------------------------------------------
        // Part 4: @Override Annotation
        // ------------------------------------------------------------------
        System.out.println();
        System.out.println("[Part 4: @Override Annotation]");
        System.out.println();

        ElectricCar ec = new ElectricCar();
        System.out.println("  ec.getType(): " + ec.getType());
        System.out.println();
        System.out.println("  Why use @Override?");
        System.out.println("  1. Compiler verifies the method actually overrides something");
        System.out.println("  2. Prevents accidental typos in method names");
        System.out.println("  3. Makes code self-documenting");
        System.out.println("  4. Shows intent clearly to other developers");

        // ------------------------------------------------------------------
        // Part 5: What Cannot Be Overridden
        // ------------------------------------------------------------------
        System.out.println();
        System.out.println("[Part 5: What Cannot Be Overridden]");
        System.out.println();
        System.out.println("  Cannot override:");
        System.out.println("  1. final methods   - explicitly prevented");
        System.out.println("  2. static methods   - they belong to the class, not instances");
        System.out.println("  3. private methods  - not inherited, so nothing to override");
        System.out.println("  4. constructors     - not inherited, not methods");

        // ------------------------------------------------------------------
        // Summary: Overriding vs Overloading
        // ------------------------------------------------------------------
        System.out.println();
        System.out.println("[Summary: Overriding vs Overloading]");
        System.out.println();
        System.out.println("  +------------------+--------------------+--------------------+");
        System.out.println("  | Feature          | Overriding         | Overloading        |");
        System.out.println("  +------------------+--------------------+--------------------+");
        System.out.println("  | Method name      | Same               | Same               |");
        System.out.println("  | Parameters       | Same               | Different          |");
        System.out.println("  | Classes          | Parent & Child     | Same class         |");
        System.out.println("  | Binding          | Runtime (dynamic)  | Compile (static)   |");
        System.out.println("  | @Override        | Yes                | No                 |");
        System.out.println("  | Return type      | Same or covariant  | Can be different   |");
        System.out.println("  +------------------+--------------------+--------------------+");
    }
}
