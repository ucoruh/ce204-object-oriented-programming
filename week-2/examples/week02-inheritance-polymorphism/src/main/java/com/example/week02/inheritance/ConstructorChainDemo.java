package com.example.week02.inheritance;

/**
 * ==========================================================================
 * DEMO 2: Constructor Chaining in Inheritance
 * ==========================================================================
 *
 * When a subclass object is created, the constructors execute in order
 * from the TOP of the hierarchy DOWN:
 *   1. Object constructor (implicit)
 *   2. Grandparent constructor
 *   3. Parent constructor
 *   4. Child constructor
 *
 * Key Rules:
 *   - super() MUST be the FIRST statement in a constructor
 *   - If you don't write super(), Java automatically inserts super() (no-arg)
 *   - If the parent has NO no-arg constructor, you MUST explicitly call
 *     super(...) with the appropriate arguments
 *   - You can use this() OR super() but NOT BOTH in the same constructor
 */
public class ConstructorChainDemo {

    // ======================================================================
    // Example 1: Three-level hierarchy showing constructor order
    // ======================================================================

    static class Grandparent {
        String familyName;

        Grandparent() {
            this.familyName = "Unknown";
            System.out.println("    1. Grandparent() default constructor called");
        }

        Grandparent(String familyName) {
            this.familyName = familyName;
            System.out.println("    1. Grandparent(\"" + familyName + "\") constructor called");
        }
    }

    static class Parent extends Grandparent {
        String firstName;

        // Calls Grandparent() automatically (implicit super())
        Parent() {
            // super() is implicitly inserted here by the compiler
            this.firstName = "Unknown";
            System.out.println("    2. Parent() default constructor called");
        }

        // Explicitly calls Grandparent(String)
        Parent(String firstName, String familyName) {
            super(familyName);  // Explicit call to parent constructor
            this.firstName = firstName;
            System.out.println("    2. Parent(\"" + firstName + "\", \"" + familyName + "\") constructor called");
        }
    }

    static class Child extends Parent {
        int age;

        // Uses this() to chain to another constructor in the same class
        Child() {
            this("Unknown", "Unknown", 0);  // Chain to the parameterized constructor
            System.out.println("    3. Child() default constructor called (chained)");
        }

        Child(String firstName, String familyName, int age) {
            super(firstName, familyName);  // Chain to Parent constructor
            this.age = age;
            System.out.println("    3. Child(\"" + firstName + "\", \"" + familyName
                    + "\", " + age + ") constructor called");
        }

        void displayInfo() {
            System.out.println("    " + firstName + " " + familyName + ", age " + age);
        }
    }

    // ======================================================================
    // Example 2: Shape hierarchy - parent has NO default constructor
    // ======================================================================

    /**
     * Shape has ONLY a parameterized constructor. This means:
     * - There is NO automatic no-arg constructor
     * - ALL subclasses MUST call super(name) explicitly
     */
    static class Shape {
        String name;
        String color;

        // ONLY parameterized constructor - no default!
        Shape(String name, String color) {
            this.name = name;
            this.color = color;
            System.out.println("    Shape(\"" + name + "\", \"" + color + "\") called");
        }

        void display() {
            System.out.println("    " + color + " " + name);
        }
    }

    static class Circle extends Shape {
        double radius;

        Circle(double radius, String color) {
            // MUST call super() explicitly because Shape has no no-arg constructor!
            // Removing this line would cause a COMPILE ERROR.
            super("Circle", color);
            this.radius = radius;
            System.out.println("    Circle(radius=" + radius + ") called");
        }

        double getArea() {
            return Math.PI * radius * radius;
        }
    }

    static class Rectangle extends Shape {
        double width, height;

        Rectangle(double width, double height, String color) {
            super("Rectangle", color);  // Must call super explicitly
            this.width = width;
            this.height = height;
            System.out.println("    Rectangle(" + width + "x" + height + ") called");
        }

        double getArea() {
            return width * height;
        }
    }

    // ======================================================================
    // Example 3: Combining this() and super() chaining
    // ======================================================================

    static class Employee {
        String name;
        String department;
        double salary;

        Employee(String name, String department, double salary) {
            this.name = name;
            this.department = department;
            this.salary = salary;
            System.out.println("    Employee(\"" + name + "\") constructed");
        }
    }

    static class Manager extends Employee {
        int teamSize;

        // Full constructor
        Manager(String name, String department, double salary, int teamSize) {
            super(name, department, salary);
            this.teamSize = teamSize;
            System.out.println("    Manager(teamSize=" + teamSize + ") constructed");
        }

        // Convenience constructor with default team size
        Manager(String name, String department, double salary) {
            this(name, department, salary, 0);
            // Note: this() chains to the full Manager constructor above,
            //        which in turn calls super() to chain to Employee.
            System.out.println("    Manager() convenience constructor (default team size)");
        }

        // Convenience constructor with department default
        Manager(String name) {
            this(name, "General", 50000, 1);
            System.out.println("    Manager() minimal constructor");
        }

        void displayInfo() {
            System.out.println("    " + name + " | Dept: " + department
                    + " | Salary: $" + String.format("%.0f", salary)
                    + " | Team: " + teamSize);
        }
    }

    // ======================================================================
    // Demo method
    // ======================================================================

    public static void demo() {

        // ------------------------------------------------------------------
        // Part 1: Constructor Execution Order
        // ------------------------------------------------------------------
        System.out.println("[Part 1: Constructor Execution Order]");
        System.out.println();

        System.out.println("  Creating Child with parameterized constructor:");
        System.out.println("  (Watch the order: Grandparent -> Parent -> Child)");
        System.out.println();
        Child child1 = new Child("Alice", "Smith", 10);
        System.out.println();
        child1.displayInfo();

        System.out.println();
        System.out.println("  Creating Child with default constructor:");
        System.out.println("  (Uses this() to chain within Child, then super() goes up)");
        System.out.println();
        Child child2 = new Child();
        System.out.println();
        child2.displayInfo();

        // ------------------------------------------------------------------
        // Part 2: Parent Without Default Constructor
        // ------------------------------------------------------------------
        System.out.println();
        System.out.println("[Part 2: Parent Without Default Constructor]");
        System.out.println();

        System.out.println("  Shape has NO default constructor.");
        System.out.println("  Subclasses MUST call super(name, color) explicitly.");
        System.out.println();

        System.out.println("  Creating Circle:");
        Circle circle = new Circle(5.0, "Red");
        circle.display();
        System.out.println("    Area: " + String.format("%.2f", circle.getArea()));

        System.out.println();
        System.out.println("  Creating Rectangle:");
        Rectangle rect = new Rectangle(4.0, 6.0, "Blue");
        rect.display();
        System.out.println("    Area: " + String.format("%.2f", rect.getArea()));

        // ------------------------------------------------------------------
        // Part 3: Combining this() and super() Chaining
        // ------------------------------------------------------------------
        System.out.println();
        System.out.println("[Part 3: this() and super() Chaining Together]");
        System.out.println();

        System.out.println("  Full constructor:");
        Manager m1 = new Manager("Alice", "Engineering", 120000, 8);
        m1.displayInfo();

        System.out.println();
        System.out.println("  Convenience constructor (default team size):");
        Manager m2 = new Manager("Bob", "Marketing", 90000);
        m2.displayInfo();

        System.out.println();
        System.out.println("  Minimal constructor:");
        Manager m3 = new Manager("Charlie");
        m3.displayInfo();

        // ------------------------------------------------------------------
        // Summary
        // ------------------------------------------------------------------
        System.out.println();
        System.out.println("[Summary: Constructor Chain Rules]");
        System.out.println("  1. Constructors execute top-down: parent before child");
        System.out.println("  2. super() must be the first statement (if used)");
        System.out.println("  3. Java inserts super() automatically if you don't write it");
        System.out.println("  4. If parent has no no-arg constructor, you MUST call super(...)");
        System.out.println("  5. this() and super() cannot both appear in same constructor");
    }
}
