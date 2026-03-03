package com.example.week02.advanced;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * ==========================================================================
 * DEMO 10: Encapsulation Patterns
 * ==========================================================================
 *
 * This demo covers advanced encapsulation patterns that build on the
 * basic encapsulation concepts from Week 01:
 *
 *   1. Builder Pattern (Preview)
 *      - Solves the "telescoping constructor" problem
 *      - Creates objects step-by-step with a fluent interface
 *      - Makes object creation readable and flexible
 *
 *   2. Immutable Objects (Advanced)
 *      - Objects that cannot be modified after creation
 *      - Thread-safe by design
 *      - Defensive copying for mutable fields
 *
 * These patterns leverage encapsulation (private fields, controlled access)
 * to create robust, maintainable, and safe code.
 */
public class EncapsulationPatternsDemo {

    // ======================================================================
    // Example 1: Builder Pattern
    // ======================================================================

    /**
     * The Problem: "Telescoping Constructor Anti-Pattern"
     *
     * Without the Builder, you'd have constructors like:
     *   Pizza(size)
     *   Pizza(size, cheese)
     *   Pizza(size, cheese, pepperoni)
     *   Pizza(size, cheese, pepperoni, mushrooms)
     *   ... (exponential growth!)
     *
     * Or a constructor with many parameters:
     *   Pizza(size, cheese, pepperoni, mushrooms, olives, onions, bacon)
     *   -> Which is which? Easy to mix up!
     *
     * The Builder Pattern solves this elegantly.
     */
    static class Pizza {
        // All fields are private and final (immutable after construction)
        private final String size;          // required
        private final String crustType;     // required
        private final boolean cheese;       // optional
        private final boolean pepperoni;    // optional
        private final boolean mushrooms;    // optional
        private final boolean olives;       // optional
        private final boolean onions;       // optional
        private final boolean bacon;        // optional
        private final String specialNotes;  // optional

        // Private constructor - can only be called by the Builder
        private Pizza(Builder builder) {
            this.size = builder.size;
            this.crustType = builder.crustType;
            this.cheese = builder.cheese;
            this.pepperoni = builder.pepperoni;
            this.mushrooms = builder.mushrooms;
            this.olives = builder.olives;
            this.onions = builder.onions;
            this.bacon = builder.bacon;
            this.specialNotes = builder.specialNotes;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(size).append(" pizza on ").append(crustType).append(" crust");

            List<String> toppings = new ArrayList<>();
            if (cheese) toppings.add("cheese");
            if (pepperoni) toppings.add("pepperoni");
            if (mushrooms) toppings.add("mushrooms");
            if (olives) toppings.add("olives");
            if (onions) toppings.add("onions");
            if (bacon) toppings.add("bacon");

            if (!toppings.isEmpty()) {
                sb.append(" with ").append(String.join(", ", toppings));
            } else {
                sb.append(" (plain)");
            }

            if (specialNotes != null && !specialNotes.isEmpty()) {
                sb.append(" [Note: ").append(specialNotes).append("]");
            }
            return sb.toString();
        }

        // ---- The Builder (static inner class) ----
        static class Builder {
            // Required parameters
            private final String size;
            private final String crustType;

            // Optional parameters - initialized to defaults
            private boolean cheese = false;
            private boolean pepperoni = false;
            private boolean mushrooms = false;
            private boolean olives = false;
            private boolean onions = false;
            private boolean bacon = false;
            private String specialNotes = "";

            // Builder constructor takes required parameters
            Builder(String size, String crustType) {
                this.size = size;
                this.crustType = crustType;
            }

            // Each optional parameter has a setter that returns 'this'
            // for method chaining (fluent interface)
            Builder cheese(boolean value) { this.cheese = value; return this; }
            Builder pepperoni(boolean value) { this.pepperoni = value; return this; }
            Builder mushrooms(boolean value) { this.mushrooms = value; return this; }
            Builder olives(boolean value) { this.olives = value; return this; }
            Builder onions(boolean value) { this.onions = value; return this; }
            Builder bacon(boolean value) { this.bacon = value; return this; }
            Builder specialNotes(String notes) { this.specialNotes = notes; return this; }

            // The build() method creates the Pizza object
            Pizza build() {
                return new Pizza(this);
            }
        }
    }

    // ======================================================================
    // Example 2: Builder Pattern for a complex object (HttpRequest-style)
    // ======================================================================

    /**
     * Another Builder example: constructing an HTTP request object.
     * This is similar to how real HTTP client libraries work.
     */
    static class HttpRequest {
        private final String method;
        private final String url;
        private final String body;
        private final List<String> headers;
        private final int timeout;

        private HttpRequest(Builder builder) {
            this.method = builder.method;
            this.url = builder.url;
            this.body = builder.body;
            this.headers = Collections.unmodifiableList(new ArrayList<>(builder.headers));
            this.timeout = builder.timeout;
        }

        void prettyPrint() {
            System.out.println("    " + method + " " + url);
            for (String header : headers) {
                System.out.println("    Header: " + header);
            }
            if (body != null && !body.isEmpty()) {
                System.out.println("    Body: " + body);
            }
            System.out.println("    Timeout: " + timeout + "ms");
        }

        static class Builder {
            private final String method;
            private final String url;
            private String body = "";
            private List<String> headers = new ArrayList<>();
            private int timeout = 30000;  // Default: 30 seconds

            Builder(String method, String url) {
                this.method = method;
                this.url = url;
            }

            Builder body(String body) { this.body = body; return this; }
            Builder timeout(int ms) { this.timeout = ms; return this; }

            Builder header(String name, String value) {
                headers.add(name + ": " + value);
                return this;
            }

            HttpRequest build() {
                return new HttpRequest(this);
            }
        }
    }

    // ======================================================================
    // Example 3: Immutable Object with Defensive Copying
    // ======================================================================

    /**
     * Demonstrates a truly immutable class that handles mutable fields
     * (like List) using defensive copying.
     *
     * Rules for Immutability:
     *   1. Make class final (or all methods final)
     *   2. Make all fields private and final
     *   3. No setter methods
     *   4. Defensive copy of mutable constructor arguments
     *   5. Return defensive copies from getters of mutable fields
     */
    static final class StudentRecord {
        private final String name;
        private final int id;
        private final List<String> courses;  // List is mutable - needs protection!
        private final double gpa;

        // Constructor takes defensive copy of the mutable list
        StudentRecord(String name, int id, List<String> courses, double gpa) {
            this.name = name;
            this.id = id;
            // DEFENSIVE COPY: create a new list instead of storing the reference
            // This prevents the caller from modifying our internal state
            this.courses = new ArrayList<>(courses);
            this.gpa = gpa;
        }

        // Getters for immutable fields - safe to return directly
        public String getName() { return name; }
        public int getId() { return id; }
        public double getGpa() { return gpa; }

        // Getter for mutable field - return DEFENSIVE COPY
        // This prevents callers from modifying our internal list
        public List<String> getCourses() {
            return Collections.unmodifiableList(courses);
        }

        // "Modification" methods return NEW objects
        public StudentRecord withName(String newName) {
            return new StudentRecord(newName, id, courses, gpa);
        }

        public StudentRecord withGpa(double newGpa) {
            return new StudentRecord(name, id, courses, newGpa);
        }

        public StudentRecord withAddedCourse(String course) {
            List<String> newCourses = new ArrayList<>(courses);
            newCourses.add(course);
            return new StudentRecord(name, id, newCourses, gpa);
        }

        @Override
        public String toString() {
            return "StudentRecord{name='" + name + "', id=" + id
                    + ", courses=" + courses + ", gpa=" + gpa + "}";
        }
    }

    // ======================================================================
    // Demo method
    // ======================================================================

    public static void demo() {

        // ------------------------------------------------------------------
        // Part 1: The Problem - Telescoping Constructors
        // ------------------------------------------------------------------
        System.out.println("[Part 1: The Telescoping Constructor Problem]");
        System.out.println();
        System.out.println("  Without Builder, you'd need many constructors:");
        System.out.println("    new Pizza(\"Large\", \"Thin\", true, false, true, false, true, false, \"\")");
        System.out.println("    Which boolean is cheese? Which is pepperoni? Confusing!");
        System.out.println();

        // ------------------------------------------------------------------
        // Part 2: Builder Pattern Solution
        // ------------------------------------------------------------------
        System.out.println("[Part 2: Builder Pattern - Clean Object Creation]");
        System.out.println();

        // Clear, readable, self-documenting construction
        Pizza pizza1 = new Pizza.Builder("Large", "Thin Crust")
                .cheese(true)
                .pepperoni(true)
                .mushrooms(true)
                .build();

        Pizza pizza2 = new Pizza.Builder("Medium", "Thick Crust")
                .cheese(true)
                .bacon(true)
                .onions(true)
                .specialNotes("Extra crispy please")
                .build();

        Pizza pizza3 = new Pizza.Builder("Small", "Stuffed Crust")
                .cheese(true)
                .olives(true)
                .build();

        System.out.println("  Order 1: " + pizza1);
        System.out.println("  Order 2: " + pizza2);
        System.out.println("  Order 3: " + pizza3);
        System.out.println();
        System.out.println("  Benefits of Builder Pattern:");
        System.out.println("  - Each parameter is named (self-documenting)");
        System.out.println("  - Optional parameters have sensible defaults");
        System.out.println("  - Order of setter calls doesn't matter");
        System.out.println("  - Resulting object is immutable");

        // ------------------------------------------------------------------
        // Part 3: Builder for HTTP Request
        // ------------------------------------------------------------------
        System.out.println();
        System.out.println("[Part 3: Builder for HTTP Request]");
        System.out.println();

        HttpRequest getRequest = new HttpRequest.Builder("GET", "https://api.example.com/users")
                .header("Accept", "application/json")
                .header("Authorization", "Bearer token123")
                .timeout(5000)
                .build();

        HttpRequest postRequest = new HttpRequest.Builder("POST", "https://api.example.com/users")
                .header("Content-Type", "application/json")
                .body("{\"name\": \"Alice\", \"role\": \"student\"}")
                .timeout(10000)
                .build();

        System.out.println("  GET Request:");
        getRequest.prettyPrint();
        System.out.println();
        System.out.println("  POST Request:");
        postRequest.prettyPrint();

        // ------------------------------------------------------------------
        // Part 4: Immutable Objects with Defensive Copying
        // ------------------------------------------------------------------
        System.out.println();
        System.out.println("[Part 4: Immutable Objects with Defensive Copying]");
        System.out.println();

        List<String> courses = new ArrayList<>();
        courses.add("CEN206");
        courses.add("CEN208");

        StudentRecord student = new StudentRecord("Alice", 1001, courses, 3.8);
        System.out.println("  Original: " + student);

        // Try to modify the original list - should NOT affect student!
        courses.add("CEN210");
        System.out.println("  After modifying original list:");
        System.out.println("  Student courses: " + student.getCourses());
        System.out.println("  (Not affected! Defensive copy in constructor protects it)");

        // Try to modify the returned list - should throw exception!
        System.out.println();
        System.out.println("  Trying to modify returned courses list...");
        try {
            student.getCourses().add("HACK!");
        } catch (UnsupportedOperationException e) {
            System.out.println("  UnsupportedOperationException! Cannot modify the list.");
            System.out.println("  (Getter returns unmodifiable view - true immutability!)");
        }

        // ------------------------------------------------------------------
        // Part 5: "Modifying" Immutable Objects
        // ------------------------------------------------------------------
        System.out.println();
        System.out.println("[Part 5: Creating Modified Copies of Immutable Objects]");
        System.out.println();

        StudentRecord updated = student.withGpa(3.9);
        StudentRecord withNewCourse = student.withAddedCourse("CEN301");

        System.out.println("  Original:       " + student);
        System.out.println("  With new GPA:   " + updated);
        System.out.println("  With new course: " + withNewCourse);
        System.out.println();
        System.out.println("  The original object is NEVER changed.");
        System.out.println("  Each 'modification' creates a NEW object.");
        System.out.println("  This is how Java's String and LocalDate work!");

        // ------------------------------------------------------------------
        // Summary
        // ------------------------------------------------------------------
        System.out.println();
        System.out.println("[Summary: Encapsulation Patterns]");
        System.out.println();
        System.out.println("  Builder Pattern:");
        System.out.println("  - Solves telescoping constructor problem");
        System.out.println("  - Fluent interface with method chaining");
        System.out.println("  - Separates construction from representation");
        System.out.println("  - Use when class has 4+ optional parameters");
        System.out.println();
        System.out.println("  Immutable Objects:");
        System.out.println("  - final class, private final fields, no setters");
        System.out.println("  - Defensive copy mutable arguments in constructor");
        System.out.println("  - Return defensive copies from getters");
        System.out.println("  - 'with' methods return new objects");
        System.out.println("  - Thread-safe, cacheable, reliable");
    }
}
