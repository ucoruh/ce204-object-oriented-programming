package com.example.week03.interfaces;

/**
 * ============================================================================
 * DEMO 4: Modern Interface Features (Java 8 / 9+)
 * ============================================================================
 *
 * Before Java 8, interfaces could ONLY contain:
 *   - Abstract methods
 *   - Constants (public static final fields)
 *
 * Java 8 added:
 *   - DEFAULT methods    : Methods with a body, using the "default" keyword.
 *                          Classes that implement the interface inherit these
 *                          methods but can override them if needed.
 *   - STATIC methods     : Utility methods that belong to the interface itself,
 *                          called via InterfaceName.methodName().
 *
 * Java 9 added:
 *   - PRIVATE methods    : Helper methods used internally by default/static
 *                          methods. Not visible to implementing classes.
 *   - PRIVATE STATIC     : Static helper methods, also internal-only.
 *
 * Why were these added?
 *   - To allow interfaces to evolve WITHOUT breaking existing implementations.
 *   - To reduce code duplication among implementing classes.
 *   - To provide utility/helper methods directly in the interface.
 *
 * ============================================================================
 */
public class ModernInterfaceDemo {

    // =======================================================================
    // PART 1: Default Methods (Java 8)
    // =======================================================================

    /**
     * Logger interface with a default method.
     * Implementing classes get log() for free but can override it.
     */
    interface Logger {
        // Abstract method - must be implemented
        String getLoggerName();

        // DEFAULT method - has a body, classes inherit this
        default void log(String message) {
            System.out.println("    [" + getLoggerName() + "] " + message);
        }

        // Another default method that builds on the first
        default void logWarning(String message) {
            log("WARNING: " + message);
        }

        default void logError(String message) {
            log("ERROR: " + message);
        }
    }

    /**
     * ConsoleApp uses the default log methods without overriding them.
     */
    static class ConsoleApp implements Logger {
        @Override
        public String getLoggerName() {
            return "ConsoleApp";
        }
        // Inherits log(), logWarning(), logError() as-is
    }

    /**
     * WebApp overrides the default log method to add timestamps.
     */
    static class WebApp implements Logger {
        @Override
        public String getLoggerName() {
            return "WebApp";
        }

        // OVERRIDING the default method to customize behavior
        @Override
        public void log(String message) {
            System.out.println("    [" + getLoggerName() + " @ "
                    + java.time.LocalTime.now().withNano(0) + "] " + message);
        }
    }

    // =======================================================================
    // PART 2: Static Methods (Java 8)
    // =======================================================================

    /**
     * Validator interface with static utility methods.
     * Static methods belong to the interface, not to instances.
     */
    interface Validator {
        boolean validate(String input);

        // STATIC method - called via Validator.isNullOrEmpty()
        static boolean isNullOrEmpty(String input) {
            return input == null || input.trim().isEmpty();
        }

        // Another static utility method
        static boolean isNumeric(String input) {
            if (isNullOrEmpty(input)) return false;
            try {
                Double.parseDouble(input);
                return true;
            } catch (NumberFormatException e) {
                return false;
            }
        }

        // Factory-like static method that returns an implementation
        static Validator emailValidator() {
            return input -> input != null && input.contains("@") && input.contains(".");
        }
    }

    // =======================================================================
    // PART 3: Private Methods (Java 9+)
    // =======================================================================

    /**
     * Formatter interface demonstrating private methods.
     * Private methods are HELPER methods used by default/static methods.
     * They CANNOT be accessed by implementing classes.
     */
    interface Formatter {
        // Abstract method
        String getPrefix();

        // DEFAULT method that uses a PRIVATE helper
        default String formatTitle(String text) {
            return decorateLine(text, '=');
        }

        default String formatSubtitle(String text) {
            return decorateLine(text, '-');
        }

        default String formatBulletList(String... items) {
            StringBuilder sb = new StringBuilder();
            for (String item : items) {
                sb.append(addPrefix(item)).append("\n");
            }
            return sb.toString();
        }

        // PRIVATE method - helper used by default methods above
        // Not visible to implementing classes
        private String decorateLine(String text, char borderChar) {
            String border = String.valueOf(borderChar).repeat(text.length() + 4);
            return border + "\n" + addPrefix(text) + "\n" + border;
        }

        // Another PRIVATE helper method
        private String addPrefix(String text) {
            return getPrefix() + " " + text;
        }

        // PRIVATE STATIC method - used by static methods in the interface
        private static String sanitize(String input) {
            return input == null ? "" : input.trim();
        }

        // STATIC method that uses the private static helper
        static String cleanAndUpperCase(String input) {
            return sanitize(input).toUpperCase();
        }
    }

    /**
     * ReportFormatter implements Formatter.
     * It provides getPrefix() but CANNOT access the private helper methods.
     */
    static class ReportFormatter implements Formatter {
        @Override
        public String getPrefix() {
            return ">>";
        }
    }

    // =======================================================================
    // PART 4: Resolving Default Method Conflicts
    // =======================================================================

    /**
     * When a class implements two interfaces with the SAME default method,
     * the class MUST override the method to resolve the conflict.
     */
    interface InterfaceA {
        default String greet() {
            return "Hello from InterfaceA";
        }
    }

    interface InterfaceB {
        default String greet() {
            return "Hello from InterfaceB";
        }
    }

    /**
     * ConflictResolver MUST override greet() because both InterfaceA
     * and InterfaceB define it as a default method.
     * It can choose one, combine both, or provide its own implementation.
     */
    static class ConflictResolver implements InterfaceA, InterfaceB {
        @Override
        public String greet() {
            // Option 1: Choose one parent's implementation
            // return InterfaceA.super.greet();

            // Option 2: Combine both
            return InterfaceA.super.greet() + " AND " + InterfaceB.super.greet();

            // Option 3: Provide your own
            // return "Hello from ConflictResolver";
        }
    }

    // =======================================================================
    // PART 5: Interface with all modern features combined
    // =======================================================================

    /**
     * A comprehensive interface showcasing all modern features together.
     */
    interface SmartCollection {
        // Abstract methods (traditional)
        void add(String item);
        int size();
        String get(int index);

        // Default method
        default boolean isEmpty() {
            return size() == 0;
        }

        // Default method with logic
        default String getFirst() {
            if (isEmpty()) return null;
            return get(0);
        }

        default String getLast() {
            if (isEmpty()) return null;
            return get(size() - 1);
        }

        // Default method that provides a summary
        default String summary() {
            if (isEmpty()) return "Empty collection";
            return formatSummary(size(), getFirst(), getLast());
        }

        // Private helper method
        private String formatSummary(int size, String first, String last) {
            return String.format("Collection[size=%d, first='%s', last='%s']", size, first, last);
        }

        // Static factory method
        static SmartCollection create() {
            return new SmartCollection() {
                private final java.util.ArrayList<String> items = new java.util.ArrayList<>();

                @Override
                public void add(String item) { items.add(item); }

                @Override
                public int size() { return items.size(); }

                @Override
                public String get(int index) { return items.get(index); }
            };
        }
    }

    // =======================================================================
    // DEMO METHOD
    // =======================================================================

    public static void demo() {
        // --- Section A: Default methods ---
        System.out.println("  [A] Default Methods (Java 8)");
        System.out.println("  -----------------------------");

        ConsoleApp consoleApp = new ConsoleApp();
        consoleApp.log("Application started");          // Uses default implementation
        consoleApp.logWarning("Low memory");             // Uses default implementation
        consoleApp.logError("Connection failed");        // Uses default implementation
        System.out.println();

        WebApp webApp = new WebApp();
        webApp.log("Server started");                    // Uses OVERRIDDEN implementation
        webApp.logWarning("High CPU usage");             // Uses default, which calls overridden log()
        webApp.logError("Database timeout");              // Uses default, which calls overridden log()
        System.out.println();

        // --- Section B: Static methods ---
        System.out.println("  [B] Static Methods (Java 8)");
        System.out.println("  -----------------------------");

        // Static methods are called on the INTERFACE, not on instances
        System.out.println("    isNullOrEmpty(null): " + Validator.isNullOrEmpty(null));
        System.out.println("    isNullOrEmpty(''): " + Validator.isNullOrEmpty(""));
        System.out.println("    isNullOrEmpty('hello'): " + Validator.isNullOrEmpty("hello"));
        System.out.println("    isNumeric('42'): " + Validator.isNumeric("42"));
        System.out.println("    isNumeric('abc'): " + Validator.isNumeric("abc"));

        // Factory-style static method
        Validator emailVal = Validator.emailValidator();
        System.out.println("    emailValidator('user@mail.com'): " + emailVal.validate("user@mail.com"));
        System.out.println("    emailValidator('invalid'): " + emailVal.validate("invalid"));
        System.out.println();

        // --- Section C: Private methods ---
        System.out.println("  [C] Private Methods (Java 9+)");
        System.out.println("  -------------------------------");

        ReportFormatter fmt = new ReportFormatter();
        System.out.println("    " + fmt.formatTitle("Annual Report"));
        System.out.println();
        System.out.println("    " + fmt.formatSubtitle("Revenue Section"));
        System.out.println();
        System.out.print("    " + fmt.formatBulletList("Item A", "Item B", "Item C"));

        // Private static helper used by static method
        System.out.println("    cleanAndUpperCase('  hello world  '): "
                + Formatter.cleanAndUpperCase("  hello world  "));
        System.out.println();

        // --- Section D: Default method conflicts ---
        System.out.println("  [D] Resolving Default Method Conflicts");
        System.out.println("  ----------------------------------------");

        ConflictResolver resolver = new ConflictResolver();
        System.out.println("    resolver.greet(): " + resolver.greet());
        System.out.println("    (Combined both parent implementations using InterfaceX.super.method())");
        System.out.println();

        // --- Section E: All features combined ---
        System.out.println("  [E] All Modern Features Combined");
        System.out.println("  ----------------------------------");

        // Using static factory method to create instance
        SmartCollection coll = SmartCollection.create();
        System.out.println("    Empty? " + coll.isEmpty());       // default method
        System.out.println("    Summary: " + coll.summary());     // default method using private helper

        coll.add("First");
        coll.add("Second");
        coll.add("Third");

        System.out.println("    After adding 3 items:");
        System.out.println("    Empty? " + coll.isEmpty());
        System.out.println("    First: " + coll.getFirst());      // default method
        System.out.println("    Last: " + coll.getLast());         // default method
        System.out.println("    Summary: " + coll.summary());     // default + private helper
    }
}
