package com.example.week02.inheritance;

/**
 * ==========================================================================
 * DEMO 3: super and this Keywords
 * ==========================================================================
 *
 * 'this' keyword refers to the CURRENT object:
 *   - this.field      - accesses the current object's field
 *   - this.method()   - calls the current object's method
 *   - this()          - calls another constructor in the same class
 *
 * 'super' keyword refers to the PARENT class:
 *   - super.field     - accesses the parent's field (if hidden by child)
 *   - super.method()  - calls the parent's version of an overridden method
 *   - super()         - calls the parent's constructor
 *
 * Key Difference:
 *   - 'this' resolves to the current class
 *   - 'super' resolves to the immediate parent class
 */
public class SuperThisDemo {

    // ======================================================================
    // Example 1: Field hiding and super.field
    // ======================================================================

    /**
     * When a child class declares a field with the SAME NAME as a parent field,
     * the parent field is "hidden" (not overridden - fields don't get overridden).
     * Use super.field to access the hidden parent field.
     */
    static class ParentClass {
        String message = "Hello from Parent!";
        int value = 10;

        void showMessage() {
            System.out.println("    Parent's showMessage(): " + message);
        }
    }

    static class ChildClass extends ParentClass {
        // This HIDES the parent's 'message' field (not recommended in practice)
        String message = "Hello from Child!";
        int value = 20;

        void showMessage() {
            System.out.println("    Child's showMessage(): " + message);
        }

        void demonstrateSuperAndThis() {
            // 'this' refers to child's members
            System.out.println("    this.message = " + this.message);     // Child's field
            System.out.println("    this.value   = " + this.value);       // Child's field

            // 'super' refers to parent's members
            System.out.println("    super.message = " + super.message);   // Parent's field
            System.out.println("    super.value   = " + super.value);     // Parent's field

            System.out.println();
            System.out.println("    Calling this.showMessage():");
            this.showMessage();    // Child's version

            System.out.println("    Calling super.showMessage():");
            super.showMessage();   // Parent's version
        }
    }

    // ======================================================================
    // Example 2: super.method() for building on parent behavior
    // ======================================================================

    /**
     * A common pattern: override a method and call super.method() to
     * EXTEND rather than REPLACE the parent's behavior.
     */
    static class Logger {
        void log(String message) {
            System.out.println("    [LOG] " + message);
        }
    }

    static class TimestampLogger extends Logger {
        @Override
        void log(String message) {
            // EXTEND parent behavior: add timestamp, then call parent's log
            String timestamp = java.time.LocalTime.now().toString().substring(0, 8);
            super.log("[" + timestamp + "] " + message);
        }
    }

    static class PriorityLogger extends TimestampLogger {
        String priority;

        PriorityLogger(String priority) {
            this.priority = priority;
        }

        @Override
        void log(String message) {
            // Further extend: add priority, then call parent's (TimestampLogger) log
            super.log("[" + priority + "] " + message);
        }
    }

    // ======================================================================
    // Example 3: this() for constructor chaining
    // ======================================================================

    /**
     * Demonstrates using this() to chain constructors within the same class.
     */
    static class Point {
        double x, y, z;
        String label;

        // Full constructor
        Point(double x, double y, double z, String label) {
            this.x = x;
            this.y = y;
            this.z = z;
            this.label = label;
        }

        // 3D point with default label
        Point(double x, double y, double z) {
            this(x, y, z, "Point");  // Chains to full constructor
        }

        // 2D point (z = 0)
        Point(double x, double y) {
            this(x, y, 0.0);  // Chains to 3D constructor
        }

        // Origin point
        Point() {
            this(0.0, 0.0);  // Chains to 2D constructor
        }

        @Override
        public String toString() {
            return label + "(" + x + ", " + y + ", " + z + ")";
        }
    }

    // ======================================================================
    // Example 4: this as a reference to the current object
    // ======================================================================

    /**
     * Demonstrates using 'this' as a reference that can be passed around
     * and used for method chaining (fluent interface).
     */
    static class StringBuilder2 {
        private String content;

        StringBuilder2() {
            this.content = "";
        }

        // Returns 'this' to enable method chaining
        StringBuilder2 append(String text) {
            this.content += text;
            return this;  // Return the current object for chaining
        }

        StringBuilder2 appendLine(String text) {
            this.content += text + "\n";
            return this;
        }

        StringBuilder2 appendSeparator() {
            this.content += "---\n";
            return this;
        }

        String build() {
            return this.content;
        }
    }

    // ======================================================================
    // Demo method
    // ======================================================================

    public static void demo() {

        // ------------------------------------------------------------------
        // Part 1: super vs this for Fields
        // ------------------------------------------------------------------
        System.out.println("[Part 1: super vs this for Field Access]");
        System.out.println();

        ChildClass child = new ChildClass();
        child.demonstrateSuperAndThis();

        System.out.println();
        System.out.println("  Note: Field hiding (child declaring same name as parent)");
        System.out.println("  is generally discouraged. It leads to confusion.");
        System.out.println("  Methods use OVERRIDING instead, which is much cleaner.");

        // ------------------------------------------------------------------
        // Part 2: super.method() for Extending Behavior
        // ------------------------------------------------------------------
        System.out.println();
        System.out.println("[Part 2: super.method() - Extending Parent Behavior]");
        System.out.println();

        System.out.println("  Basic Logger:");
        Logger basic = new Logger();
        basic.log("Application started");

        System.out.println();
        System.out.println("  TimestampLogger (adds timestamp, then calls super.log):");
        TimestampLogger tsLogger = new TimestampLogger();
        tsLogger.log("Processing request");

        System.out.println();
        System.out.println("  PriorityLogger (adds priority, then calls super.log):");
        PriorityLogger pLogger = new PriorityLogger("HIGH");
        pLogger.log("Disk space low");

        System.out.println();
        System.out.println("  Each level ADDS functionality using super.method():");
        System.out.println("  PriorityLogger -> TimestampLogger -> Logger");

        // ------------------------------------------------------------------
        // Part 3: this() for Constructor Chaining
        // ------------------------------------------------------------------
        System.out.println();
        System.out.println("[Part 3: this() for Constructor Chaining]");
        System.out.println();

        Point p1 = new Point();
        Point p2 = new Point(3.0, 4.0);
        Point p3 = new Point(1.0, 2.0, 3.0);
        Point p4 = new Point(5.0, 6.0, 7.0, "Target");

        System.out.println("  Default (origin): " + p1);
        System.out.println("  2D point:         " + p2);
        System.out.println("  3D point:         " + p3);
        System.out.println("  Labeled point:    " + p4);

        // ------------------------------------------------------------------
        // Part 4: this for Method Chaining (Fluent Interface)
        // ------------------------------------------------------------------
        System.out.println();
        System.out.println("[Part 4: this for Method Chaining (Fluent Interface)]");
        System.out.println();

        // Traditional approach (verbose):
        StringBuilder2 sb1 = new StringBuilder2();
        sb1.appendLine("Title: CEN206 Report");
        sb1.appendSeparator();
        sb1.appendLine("Content goes here.");

        // Fluent approach using method chaining (concise):
        String result = new StringBuilder2()
                .appendLine("Title: CEN206 Report")
                .appendSeparator()
                .appendLine("Content goes here.")
                .appendLine("End of report.")
                .build();

        System.out.println("  Fluent interface result:");
        System.out.println("  " + result.replace("\n", "\n  "));

        System.out.println("  Method chaining works because each method returns 'this'.");

        // ------------------------------------------------------------------
        // Summary
        // ------------------------------------------------------------------
        System.out.println();
        System.out.println("[Summary]");
        System.out.println("  this.field      - current object's field");
        System.out.println("  this.method()   - current object's method");
        System.out.println("  this()          - chains to another constructor (same class)");
        System.out.println("  this (as value) - reference to current object (method chaining)");
        System.out.println("  super.field     - parent's (hidden) field");
        System.out.println("  super.method()  - parent's (overridden) method");
        System.out.println("  super()         - parent's constructor");
    }
}
