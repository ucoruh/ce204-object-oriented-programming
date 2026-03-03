package com.example.week02.advanced;

import java.util.Objects;

/**
 * ==========================================================================
 * DEMO 9: The Object Class
 * ==========================================================================
 *
 * In Java, EVERY class implicitly extends java.lang.Object.
 * The Object class is the root of the class hierarchy.
 *
 * Important methods from Object that you should know:
 *
 *   1. toString()    - Returns a string representation of the object
 *   2. equals()      - Tests logical equality between objects
 *   3. hashCode()    - Returns a hash code integer for hash-based collections
 *   4. getClass()    - Returns the runtime class of the object
 *   5. clone()       - Creates a copy of the object (requires Cloneable)
 *   6. finalize()    - Called by GC before reclaiming (deprecated in Java 9+)
 *
 * The Contract:
 *   - If equals() returns true for two objects, hashCode() MUST return
 *     the same value for both. (But equal hashCodes don't imply equals.)
 *   - Always override hashCode() when you override equals().
 */
public class ObjectClassDemo {

    // ======================================================================
    // Example 1: toString()
    // ======================================================================

    /**
     * Without overriding toString(), Java returns ClassName@hashcode.
     * Override it to return a meaningful string representation.
     */
    static class StudentWithoutToString {
        String name;
        int id;

        StudentWithoutToString(String name, int id) {
            this.name = name;
            this.id = id;
        }
        // No toString() override - uses Object's default
    }

    static class StudentWithToString {
        String name;
        int id;
        double gpa;

        StudentWithToString(String name, int id, double gpa) {
            this.name = name;
            this.id = id;
            this.gpa = gpa;
        }

        @Override
        public String toString() {
            return "Student{name='" + name + "', id=" + id + ", gpa=" + gpa + "}";
        }
    }

    // ======================================================================
    // Example 2: equals() and hashCode()
    // ======================================================================

    /**
     * Demonstrates proper equals() and hashCode() implementation.
     * By default, equals() checks REFERENCE equality (same object in memory).
     * Override it for LOGICAL equality (same data values).
     */
    static class Point {
        final int x;
        final int y;

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        // Override equals() for logical equality
        @Override
        public boolean equals(Object obj) {
            // Step 1: Check if it's the same reference
            if (this == obj) return true;

            // Step 2: Check if the object is null or a different class
            if (obj == null || getClass() != obj.getClass()) return false;

            // Step 3: Cast and compare fields
            Point other = (Point) obj;
            return this.x == other.x && this.y == other.y;
        }

        // Override hashCode() - MUST be consistent with equals()
        // If two objects are equal, they MUST have the same hash code
        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }

        @Override
        public String toString() {
            return "Point(" + x + ", " + y + ")";
        }
    }

    /**
     * A class WITHOUT proper equals/hashCode - demonstrates the problem.
     */
    static class PointBroken {
        int x, y;

        PointBroken(int x, int y) {
            this.x = x;
            this.y = y;
        }
        // No equals/hashCode override - uses Object's default (reference equality)
    }

    // ======================================================================
    // Example 3: clone()
    // ======================================================================

    /**
     * Demonstrates object cloning.
     * The class must implement Cloneable interface.
     */
    static class Coordinate implements Cloneable {
        double latitude;
        double longitude;
        String label;

        Coordinate(double latitude, double longitude, String label) {
            this.latitude = latitude;
            this.longitude = longitude;
            this.label = label;
        }

        // Override clone() to make it public and handle CloneNotSupportedException
        @Override
        public Coordinate clone() {
            try {
                return (Coordinate) super.clone();
            } catch (CloneNotSupportedException e) {
                // This won't happen since we implement Cloneable
                throw new RuntimeException(e);
            }
        }

        @Override
        public String toString() {
            return label + "(" + latitude + ", " + longitude + ")";
        }
    }

    // ======================================================================
    // Example 4: getClass()
    // ======================================================================

    static class Animal {
        String name;
        Animal(String name) { this.name = name; }
    }

    static class Dog extends Animal {
        Dog(String name) { super(name); }
    }

    static class Cat extends Animal {
        Cat(String name) { super(name); }
    }

    // ======================================================================
    // Demo method
    // ======================================================================

    public static void demo() {

        // ------------------------------------------------------------------
        // Part 1: toString()
        // ------------------------------------------------------------------
        System.out.println("[Part 1: toString()]");
        System.out.println();

        StudentWithoutToString s1 = new StudentWithoutToString("Alice", 1001);
        StudentWithToString s2 = new StudentWithToString("Alice", 1001, 3.8);

        System.out.println("  Without toString() override:");
        System.out.println("    System.out.println(s1): " + s1);
        System.out.println("    (Shows: ClassName@hashcode - not useful!)");
        System.out.println();
        System.out.println("  With toString() override:");
        System.out.println("    System.out.println(s2): " + s2);
        System.out.println("    (Shows: meaningful information - much better!)");
        System.out.println();
        System.out.println("  toString() is called automatically when:");
        System.out.println("    - Printing: System.out.println(object)");
        System.out.println("    - String concatenation: \"Value: \" + object");

        // ------------------------------------------------------------------
        // Part 2: equals()
        // ------------------------------------------------------------------
        System.out.println();
        System.out.println("[Part 2: equals() - Logical vs Reference Equality]");
        System.out.println();

        // Without proper equals()
        PointBroken pb1 = new PointBroken(3, 4);
        PointBroken pb2 = new PointBroken(3, 4);

        System.out.println("  WITHOUT proper equals() (PointBroken):");
        System.out.println("    pb1 == pb2:       " + (pb1 == pb2));          // false (different objects)
        System.out.println("    pb1.equals(pb2):  " + pb1.equals(pb2));       // false (Object's equals = ==)
        System.out.println("    (Both have x=3, y=4 but equals() says false!)");

        System.out.println();

        // With proper equals()
        Point p1 = new Point(3, 4);
        Point p2 = new Point(3, 4);
        Point p3 = new Point(5, 6);
        Point p4 = p1;  // Same reference

        System.out.println("  WITH proper equals() (Point):");
        System.out.println("    p1 == p2:       " + (p1 == p2));          // false (different objects)
        System.out.println("    p1.equals(p2):  " + p1.equals(p2));       // true (same values!)
        System.out.println("    p1.equals(p3):  " + p1.equals(p3));       // false (different values)
        System.out.println("    p1 == p4:       " + (p1 == p4));          // true (same reference)
        System.out.println("    p1.equals(p4):  " + p1.equals(p4));       // true (same object)
        System.out.println("    p1.equals(null): " + p1.equals(null));    // false (null check)
        System.out.println("    p1.equals(\"hello\"): " + p1.equals("hello")); // false (wrong type)

        // ------------------------------------------------------------------
        // Part 3: hashCode()
        // ------------------------------------------------------------------
        System.out.println();
        System.out.println("[Part 3: hashCode() - The equals/hashCode Contract]");
        System.out.println();

        System.out.println("  Hash codes for equal objects:");
        System.out.println("    p1.hashCode(): " + p1.hashCode());
        System.out.println("    p2.hashCode(): " + p2.hashCode());
        System.out.println("    Equal? " + p1.equals(p2) + " | Same hash? " + (p1.hashCode() == p2.hashCode()));
        System.out.println();

        System.out.println("  Hash codes for unequal objects:");
        System.out.println("    p1.hashCode(): " + p1.hashCode());
        System.out.println("    p3.hashCode(): " + p3.hashCode());
        System.out.println("    Equal? " + p1.equals(p3) + " | Same hash? " + (p1.hashCode() == p3.hashCode()));

        System.out.println();
        System.out.println("  The Contract:");
        System.out.println("    - If a.equals(b) is true, then a.hashCode() == b.hashCode()");
        System.out.println("    - If hashCodes differ, equals MUST be false");
        System.out.println("    - Same hashCode does NOT guarantee equals (hash collisions)");

        // ------------------------------------------------------------------
        // Part 4: clone()
        // ------------------------------------------------------------------
        System.out.println();
        System.out.println("[Part 4: clone() - Object Copying]");
        System.out.println();

        Coordinate original = new Coordinate(41.0082, 28.9784, "Istanbul");
        Coordinate cloned = original.clone();

        System.out.println("  Original: " + original);
        System.out.println("  Cloned:   " + cloned);
        System.out.println("  Same object? " + (original == cloned));  // false
        System.out.println();

        // Modify the clone - original should not be affected
        cloned.label = "Modified";
        cloned.latitude = 0.0;
        System.out.println("  After modifying clone:");
        System.out.println("  Original: " + original);
        System.out.println("  Cloned:   " + cloned);
        System.out.println("  (Clone is independent - changes don't affect original)");
        System.out.println();
        System.out.println("  Note: clone() performs SHALLOW copy by default.");
        System.out.println("  For deep copy of objects with mutable fields,");
        System.out.println("  you must manually clone nested objects.");

        // ------------------------------------------------------------------
        // Part 5: getClass()
        // ------------------------------------------------------------------
        System.out.println();
        System.out.println("[Part 5: getClass() - Runtime Type Information]");
        System.out.println();

        Animal animal = new Animal("Generic");
        Animal dog = new Dog("Rex");      // Declared as Animal, actual Dog
        Animal cat = new Cat("Whiskers"); // Declared as Animal, actual Cat

        System.out.println("  animal.getClass(): " + animal.getClass().getName());
        System.out.println("  dog.getClass():    " + dog.getClass().getName());
        System.out.println("  cat.getClass():    " + cat.getClass().getName());
        System.out.println();

        System.out.println("  getClass().getSimpleName():");
        System.out.println("    animal: " + animal.getClass().getSimpleName());
        System.out.println("    dog:    " + dog.getClass().getSimpleName());
        System.out.println("    cat:    " + cat.getClass().getSimpleName());
        System.out.println();

        System.out.println("  Class comparison:");
        System.out.println("    dog.getClass() == cat.getClass(): "
                + (dog.getClass() == cat.getClass()));
        System.out.println("    dog.getClass() == Dog.class: "
                + (dog.getClass() == Dog.class));

        // ------------------------------------------------------------------
        // Summary
        // ------------------------------------------------------------------
        System.out.println();
        System.out.println("[Summary: Object Class Methods]");
        System.out.println("  toString()  - Override for meaningful string representation");
        System.out.println("  equals()    - Override for logical equality (compare fields)");
        System.out.println("  hashCode()  - Override together with equals() (contract!)");
        System.out.println("  clone()     - Override for object copying (implement Cloneable)");
        System.out.println("  getClass()  - Get runtime type information (rarely overridden)");
    }
}
