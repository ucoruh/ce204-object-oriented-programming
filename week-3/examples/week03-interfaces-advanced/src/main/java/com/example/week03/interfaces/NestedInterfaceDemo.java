package com.example.week03.interfaces;

/**
 * ============================================================================
 * DEMO 2: Nested Interfaces
 * ============================================================================
 *
 * Java allows interfaces to be NESTED inside:
 *   1. A class   - The interface becomes a static member of that class.
 *   2. Another interface - The nested interface is implicitly public and static.
 *
 * Why use nested interfaces?
 *   - To logically group related interfaces with the class/interface they serve.
 *   - To improve encapsulation by limiting the interface's visibility.
 *   - Common in real-world Java: Map.Entry is an interface nested inside Map.
 *
 * Key points:
 *   - A nested interface inside a class can have any access modifier
 *     (public, protected, package-private, or private).
 *   - A nested interface inside another interface is always public and static.
 *   - You access a nested interface using OuterType.InnerInterface syntax.
 *
 * ============================================================================
 */
public class NestedInterfaceDemo {

    // =======================================================================
    // PART 1: Interface nested inside a CLASS
    // =======================================================================

    /**
     * A Button class that defines a nested ClickListener interface.
     * This is similar to how Android's View has OnClickListener.
     */
    static class Button {
        private String label;
        private ClickListener listener;

        // Nested interface inside a class.
        // It can be public, protected, package-private, or private.
        public interface ClickListener {
            void onClick(String buttonLabel);
        }

        public Button(String label) {
            this.label = label;
        }

        // Set the click listener
        public void setClickListener(ClickListener listener) {
            this.listener = listener;
        }

        // Simulate a click event
        public void click() {
            System.out.println("    Button '" + label + "' was clicked.");
            if (listener != null) {
                listener.onClick(label);
            } else {
                System.out.println("    (No listener attached)");
            }
        }
    }

    /**
     * A DataProcessor class with a private nested interface.
     * Only code inside DataProcessor can implement or use this interface.
     */
    static class DataProcessor {
        // Private nested interface - only visible inside DataProcessor
        private interface Validator {
            boolean isValid(String data);
        }

        // Internal validator implementation
        private static class NonEmptyValidator implements Validator {
            @Override
            public boolean isValid(String data) {
                return data != null && !data.isEmpty();
            }
        }

        // Public method that uses the private interface internally
        public void process(String data) {
            Validator validator = new NonEmptyValidator();
            if (validator.isValid(data)) {
                System.out.println("    Processing valid data: " + data);
            } else {
                System.out.println("    Rejected invalid data (null or empty).");
            }
        }
    }

    // =======================================================================
    // PART 2: Interface nested inside another INTERFACE
    // =======================================================================

    /**
     * An outer interface with a nested interface inside it.
     * Think of this like java.util.Map and Map.Entry.
     */
    interface Container {
        // Nested interface (implicitly public static)
        interface Iterator {
            boolean hasNext();
            Object next();
        }

        // Method that returns the nested interface type
        Iterator createIterator();
        int size();
    }

    /**
     * A simple implementation of Container using an array.
     * It also provides an implementation of Container.Iterator.
     */
    static class SimpleContainer implements Container {
        private Object[] items;
        private int count;

        public SimpleContainer(int capacity) {
            items = new Object[capacity];
            count = 0;
        }

        public void add(Object item) {
            if (count < items.length) {
                items[count++] = item;
            }
        }

        @Override
        public int size() {
            return count;
        }

        // Returns an implementation of the nested Container.Iterator interface
        @Override
        public Iterator createIterator() {
            return new SimpleIterator();
        }

        // Inner class implementing the nested interface
        private class SimpleIterator implements Container.Iterator {
            private int index = 0;

            @Override
            public boolean hasNext() {
                return index < count;
            }

            @Override
            public Object next() {
                return items[index++];
            }
        }
    }

    // =======================================================================
    // PART 3: Interface nested inside an interface - another example
    // =======================================================================

    /**
     * A Database interface with nested Query and Result interfaces.
     * This groups all database-related types under one namespace.
     */
    interface Database {
        // Nested interface for queries
        interface Query {
            String getSQL();
        }

        // Nested interface for results
        interface Result {
            void printResult();
        }

        // Method using nested interface types
        Result execute(Query query);
    }

    /**
     * A mock database implementation.
     */
    static class MockDatabase implements Database {
        @Override
        public Result execute(Query query) {
            System.out.println("    Executing SQL: " + query.getSQL());
            // Return a mock result
            return new Result() {
                @Override
                public void printResult() {
                    System.out.println("    Result: [Mock data for query]");
                }
            };
        }
    }

    // =======================================================================
    // DEMO METHOD
    // =======================================================================

    public static void demo() {
        // --- Section A: Interface nested inside a class ---
        System.out.println("  [A] Interface Nested Inside a Class");
        System.out.println("  ------------------------------------");

        Button button = new Button("Submit");

        // First click without a listener
        button.click();

        // Now set a listener using the nested interface
        // Note the syntax: Button.ClickListener
        button.setClickListener(new Button.ClickListener() {
            @Override
            public void onClick(String buttonLabel) {
                System.out.println("    --> Listener received click from: " + buttonLabel);
            }
        });

        // Click again - now the listener responds
        button.click();
        System.out.println();

        // --- Private nested interface ---
        System.out.println("  [A2] Private Nested Interface (internal use)");
        System.out.println("  ---------------------------------------------");

        DataProcessor processor = new DataProcessor();
        processor.process("Hello, World!");
        processor.process("");
        processor.process(null);
        System.out.println();

        // --- Section B: Interface nested inside another interface ---
        System.out.println("  [B] Interface Nested Inside Another Interface");
        System.out.println("  ----------------------------------------------");

        SimpleContainer container = new SimpleContainer(5);
        container.add("Alpha");
        container.add("Beta");
        container.add("Gamma");

        System.out.println("    Container size: " + container.size());

        // Use the nested Container.Iterator interface
        Container.Iterator it = container.createIterator();
        System.out.print("    Iterating: ");
        while (it.hasNext()) {
            System.out.print(it.next() + " ");
        }
        System.out.println();
        System.out.println();

        // --- Section C: Database example with multiple nested interfaces ---
        System.out.println("  [C] Multiple Nested Interfaces (Database Example)");
        System.out.println("  --------------------------------------------------");

        Database db = new MockDatabase();

        // Create a query using the nested Database.Query interface
        Database.Query query = new Database.Query() {
            @Override
            public String getSQL() {
                return "SELECT * FROM students WHERE grade > 80";
            }
        };

        // Execute and print result
        Database.Result result = db.execute(query);
        result.printResult();
        System.out.println();

        // --- Section D: Real-world analogy ---
        System.out.println("  [D] Real-World Analogy: Similar to java.util.Map.Entry");
        System.out.println("  -------------------------------------------------------");
        System.out.println("    java.util.Map has a nested interface: Map.Entry<K,V>");
        System.out.println("    This groups the Entry concept logically inside Map.");
        System.out.println("    Our Container.Iterator follows the same pattern.");
    }
}
