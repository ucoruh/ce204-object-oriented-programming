package com.example.week03.classes;

import java.util.Arrays;
import java.util.Comparator;

/**
 * ============================================================================
 * DEMO 7: Anonymous Classes
 * ============================================================================
 *
 * An ANONYMOUS CLASS is a class without a name that is declared and
 * instantiated in a single expression. It is useful when:
 *   - You need a one-time-use implementation of an interface or abstract class.
 *   - The implementation is short and creating a named class would be overkill.
 *   - You want to define behavior inline, close to where it's used.
 *
 * Syntax:
 *   InterfaceOrClass ref = new InterfaceOrClass() {
 *       // method implementations
 *   };
 *
 * Key points:
 *   - Anonymous classes can implement an interface OR extend a class (not both).
 *   - They can access effectively final local variables from the enclosing scope.
 *   - They can have their own fields and methods (but extra methods are not
 *     accessible through the interface/superclass reference).
 *   - Before Java 8, anonymous classes were the primary way to pass behavior
 *     as a parameter (now largely replaced by lambdas for functional interfaces).
 *
 * ============================================================================
 */
public class AnonymousClassDemo {

    // =======================================================================
    // Interfaces and classes to be used with anonymous classes
    // =======================================================================

    /**
     * A simple Greeting interface.
     */
    interface Greeting {
        String greet(String name);
    }

    /**
     * An abstract Shape class.
     */
    static abstract class Shape {
        String color;

        Shape(String color) {
            this.color = color;
        }

        abstract double area();
        abstract String describe();
    }

    /**
     * A callback interface for asynchronous operations.
     */
    interface Callback {
        void onSuccess(String result);
        void onError(String error);
    }

    /**
     * An event listener interface (similar to GUI programming).
     */
    interface EventListener {
        void onEvent(String eventName, String data);
    }

    /**
     * A task executor that uses a Callback.
     */
    static class TaskExecutor {
        void execute(String taskName, Callback callback) {
            System.out.println("    Executing task: " + taskName);
            // Simulate success or failure
            if (taskName.contains("fail")) {
                callback.onError("Task '" + taskName + "' failed!");
            } else {
                callback.onSuccess("Task '" + taskName + "' completed successfully!");
            }
        }
    }

    // =======================================================================
    // DEMO METHOD
    // =======================================================================

    public static void demo() {
        // --- Section A: Anonymous class implementing an interface ---
        System.out.println("  [A] Anonymous Class Implementing an Interface");
        System.out.println("  -----------------------------------------------");

        // Creating an anonymous class that implements Greeting
        Greeting formalGreeting = new Greeting() {
            @Override
            public String greet(String name) {
                return "Good day, " + name + ". How do you do?";
            }
        };

        // Another anonymous implementation of the same interface
        Greeting casualGreeting = new Greeting() {
            @Override
            public String greet(String name) {
                return "Hey " + name + "! What's up?";
            }
        };

        System.out.println("    Formal: " + formalGreeting.greet("Professor"));
        System.out.println("    Casual: " + casualGreeting.greet("Alice"));
        System.out.println();

        // --- Section B: Anonymous class extending an abstract class ---
        System.out.println("  [B] Anonymous Class Extending an Abstract Class");
        System.out.println("  -------------------------------------------------");

        // Anonymous class that extends Shape
        // Note: we pass "red" to the Shape constructor
        Shape circle = new Shape("red") {
            double radius = 5.0;

            @Override
            double area() {
                return Math.PI * radius * radius;
            }

            @Override
            String describe() {
                return "A " + color + " circle with radius " + radius;
            }
        };

        // Another anonymous Shape
        Shape rectangle = new Shape("blue") {
            double width = 4.0, height = 6.0;

            @Override
            double area() {
                return width * height;
            }

            @Override
            String describe() {
                return "A " + color + " rectangle (" + width + " x " + height + ")";
            }
        };

        System.out.println("    " + circle.describe());
        System.out.println("    Area: " + String.format("%.2f", circle.area()));
        System.out.println("    " + rectangle.describe());
        System.out.println("    Area: " + String.format("%.2f", rectangle.area()));
        System.out.println();

        // --- Section C: Anonymous class as method argument ---
        System.out.println("  [C] Anonymous Class as Method Argument");
        System.out.println("  ----------------------------------------");

        TaskExecutor executor = new TaskExecutor();

        // Passing an anonymous Callback directly as an argument
        executor.execute("build project", new Callback() {
            @Override
            public void onSuccess(String result) {
                System.out.println("    SUCCESS: " + result);
            }

            @Override
            public void onError(String error) {
                System.out.println("    ERROR: " + error);
            }
        });

        // Another call with a different anonymous Callback
        executor.execute("fail deployment", new Callback() {
            @Override
            public void onSuccess(String result) {
                System.out.println("    SUCCESS: " + result);
            }

            @Override
            public void onError(String error) {
                System.out.println("    ERROR: " + error);
            }
        });
        System.out.println();

        // --- Section D: Anonymous class with Comparator ---
        System.out.println("  [D] Anonymous Class with java.util.Comparator");
        System.out.println("  ------------------------------------------------");

        String[] names = {"Charlie", "Alice", "Bob", "Diana", "Eve"};
        System.out.println("    Before sorting: " + Arrays.toString(names));

        // Using anonymous Comparator to sort by string length
        Arrays.sort(names, new Comparator<String>() {
            @Override
            public int compare(String a, String b) {
                return Integer.compare(a.length(), b.length());
            }
        });
        System.out.println("    Sorted by length: " + Arrays.toString(names));

        // Sort by reverse alphabetical order
        Arrays.sort(names, new Comparator<String>() {
            @Override
            public int compare(String a, String b) {
                return b.compareTo(a); // reversed
            }
        });
        System.out.println("    Sorted reverse alpha: " + Arrays.toString(names));
        System.out.println();

        // --- Section E: Anonymous class accessing local variables ---
        System.out.println("  [E] Anonymous Class Accessing Local Variables");
        System.out.println("  -----------------------------------------------");

        // These local variables must be effectively final to be used
        // inside the anonymous class.
        final String eventPrefix = "APP";
        String separator = "::"; // effectively final (never reassigned)

        EventListener listener = new EventListener() {
            @Override
            public void onEvent(String eventName, String data) {
                // Using effectively final local variables from enclosing scope
                System.out.println("    " + eventPrefix + separator + eventName + " -> " + data);
            }
        };

        listener.onEvent("CLICK", "button-submit");
        listener.onEvent("SCROLL", "position-500");
        listener.onEvent("RESIZE", "1920x1080");
        System.out.println();

        // --- Section F: Anonymous class with additional members ---
        System.out.println("  [F] Anonymous Class with Extra Members");
        System.out.println("  ----------------------------------------");

        // An anonymous class can have its own fields and methods,
        // but they are only accessible within the anonymous class itself
        // or if the reference type includes them.
        Greeting verboseGreeting = new Greeting() {
            // Extra field (only accessible within this anonymous class)
            int greetCount = 0;

            @Override
            public String greet(String name) {
                greetCount++;
                return "[Call #" + greetCount + "] Hello, " + name + "!";
            }

            // Extra method - NOT accessible through the Greeting reference!
            // This is a limitation of anonymous classes.
            public int getGreetCount() {
                return greetCount;
            }
        };

        // The greetCount increments with each call
        System.out.println("    " + verboseGreeting.greet("Alice"));
        System.out.println("    " + verboseGreeting.greet("Bob"));
        System.out.println("    " + verboseGreeting.greet("Charlie"));

        // Note: verboseGreeting.getGreetCount() would NOT compile because
        // the reference type is Greeting, which doesn't have getGreetCount().
        // System.out.println(verboseGreeting.getGreetCount()); // COMPILE ERROR
        System.out.println("    (Note: Extra methods in anonymous classes are not");
        System.out.println("     accessible through the interface reference type)");
        System.out.println();

        // --- Section G: Runnable as anonymous class (common pattern) ---
        System.out.println("  [G] Runnable as Anonymous Class (Common Pattern)");
        System.out.println("  --------------------------------------------------");

        // Before Java 8, this was the standard way to create Runnables
        Runnable task = new Runnable() {
            @Override
            public void run() {
                System.out.println("    Anonymous Runnable is executing...");
                System.out.println("    Thread: " + Thread.currentThread().getName());
            }
        };

        task.run(); // Run directly (not in a new thread, for simplicity)
        System.out.println();

        // --- Section H: Anonymous class vs Lambda preview ---
        System.out.println("  [H] Preview: Anonymous Class vs Lambda");
        System.out.println("  -----------------------------------------");
        System.out.println("    Anonymous class (verbose):");
        Greeting g1 = new Greeting() {
            @Override
            public String greet(String name) {
                return "Hi, " + name + "!";
            }
        };
        System.out.println("      " + g1.greet("World"));

        // Lambda equivalent (covered in detail in LambdaDemo)
        // This works because Greeting has exactly ONE abstract method
        System.out.println("    Lambda equivalent (concise):");
        Greeting g2 = name -> "Hi, " + name + "!";
        System.out.println("      " + g2.greet("World"));
        System.out.println("    (Lambdas are covered in detail in the Lambda demo)");
    }
}
