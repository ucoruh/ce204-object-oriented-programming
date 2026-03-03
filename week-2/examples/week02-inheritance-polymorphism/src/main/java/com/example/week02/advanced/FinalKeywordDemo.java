package com.example.week02.advanced;

/**
 * ==========================================================================
 * DEMO 8: final Keyword
 * ==========================================================================
 *
 * The 'final' keyword in Java means "cannot be changed/extended."
 * It can be applied to:
 *
 *   1. FINAL VARIABLES  - Value cannot be changed after initialization
 *      - Local variables: assigned once
 *      - Instance fields: assigned in constructor, never changed
 *      - Static fields:   constants (convention: UPPER_SNAKE_CASE)
 *
 *   2. FINAL METHODS    - Cannot be overridden by subclasses
 *      - Guarantees consistent behavior across the hierarchy
 *      - Slight performance benefit (JVM can inline)
 *
 *   3. FINAL CLASSES    - Cannot be extended (no subclasses)
 *      - Examples: String, Integer, Math
 *      - Ensures the class behavior cannot be altered through inheritance
 *
 * final vs immutability:
 *   - final prevents reassignment of the REFERENCE
 *   - The object itself might still be mutable!
 *   - Example: final List<String> list = new ArrayList<>();
 *     list.add("item");  // OK - modifying the object
 *     list = new ArrayList<>();  // ERROR - reassigning the reference
 */
public class FinalKeywordDemo {

    // ======================================================================
    // Example 1: Final Variables
    // ======================================================================

    /**
     * Demonstrates final variables at different levels.
     */
    static class Constants {
        // Static final = class constant (compile-time constant)
        static final double PI = 3.14159265358979;
        static final String UNIVERSITY = "Engineering University";
        static final int MAX_STUDENTS = 40;

        // Instance final = set once per object (in constructor)
        final String courseName;
        final int courseId;

        Constants(String courseName, int courseId) {
            // Final fields MUST be assigned in the constructor (or initializer)
            this.courseName = courseName;
            this.courseId = courseId;
        }

        void display() {
            System.out.println("    Course: " + courseName + " (ID: " + courseId + ")");
            System.out.println("    University: " + UNIVERSITY);
            System.out.println("    Max Students: " + MAX_STUDENTS);
        }
    }

    // ======================================================================
    // Example 2: Final Methods
    // ======================================================================

    static class BankAccount {
        private double balance;
        private final String accountNumber;

        BankAccount(String accountNumber, double balance) {
            this.accountNumber = accountNumber;
            this.balance = balance;
        }

        // FINAL method: cannot be overridden
        // This ensures the security check is ALWAYS performed
        final boolean authenticate(String inputAccount) {
            System.out.println("    Authenticating account " + inputAccount + "...");
            return inputAccount.equals(accountNumber);
        }

        // FINAL method: ensures consistent balance calculation
        final double getBalance() {
            return balance;
        }

        // Non-final: subclasses CAN override this
        void displayInfo() {
            System.out.println("    Account: " + accountNumber
                    + " | Balance: $" + String.format("%.2f", balance));
        }

        void deposit(double amount) {
            balance += amount;
        }
    }

    static class SavingsAccount extends BankAccount {
        double interestRate;

        SavingsAccount(String accountNumber, double balance, double interestRate) {
            super(accountNumber, balance);
            this.interestRate = interestRate;
        }

        // Can override displayInfo (not final)
        @Override
        void displayInfo() {
            super.displayInfo();
            System.out.println("    Interest Rate: " + (interestRate * 100) + "%");
        }

        // CANNOT override authenticate() or getBalance() - they are final!
        // @Override
        // boolean authenticate(String input) { return true; }  // COMPILE ERROR!

        void applyInterest() {
            double interest = getBalance() * interestRate;  // Uses final getBalance()
            deposit(interest);
            System.out.println("    Interest applied: $" + String.format("%.2f", interest));
        }
    }

    // ======================================================================
    // Example 3: Final Classes
    // ======================================================================

    /**
     * A final class CANNOT be extended.
     * This prevents modification of its behavior through inheritance.
     */
    static final class ImmutablePoint {
        private final double x;
        private final double y;

        ImmutablePoint(double x, double y) {
            this.x = x;
            this.y = y;
        }

        double getX() { return x; }
        double getY() { return y; }

        double distanceTo(ImmutablePoint other) {
            double dx = this.x - other.x;
            double dy = this.y - other.y;
            return Math.sqrt(dx * dx + dy * dy);
        }

        @Override
        public String toString() {
            return "(" + x + ", " + y + ")";
        }
    }

    // This would cause a COMPILE ERROR:
    // static class ExtendedPoint extends ImmutablePoint { }  // Cannot extend final class!

    // ======================================================================
    // Example 4: final reference vs immutability
    // ======================================================================

    static class MutableBox {
        int value;

        MutableBox(int value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return "MutableBox(" + value + ")";
        }
    }

    // ======================================================================
    // Demo method
    // ======================================================================

    public static void demo() {

        // ------------------------------------------------------------------
        // Part 1: Final Variables
        // ------------------------------------------------------------------
        System.out.println("[Part 1: Final Variables]");
        System.out.println();

        System.out.println("  Static final constants:");
        System.out.println("    PI = " + Constants.PI);
        System.out.println("    UNIVERSITY = " + Constants.UNIVERSITY);
        System.out.println("    MAX_STUDENTS = " + Constants.MAX_STUDENTS);
        System.out.println();

        // Constants.PI = 3.0;  // COMPILE ERROR: cannot assign to final variable

        System.out.println("  Instance final fields (set in constructor):");
        Constants c1 = new Constants("CEN206", 204);
        Constants c2 = new Constants("CEN208", 208);
        c1.display();
        System.out.println();
        c2.display();
        System.out.println();

        // c1.courseName = "Different";  // COMPILE ERROR!
        System.out.println("  c1.courseName = \"Different\";  // COMPILE ERROR!");
        System.out.println("  (Final fields cannot be changed after construction)");

        // Local final variable
        System.out.println();
        System.out.println("  Local final variables:");
        final int maxRetries = 3;
        System.out.println("    final int maxRetries = " + maxRetries);
        // maxRetries = 5;  // COMPILE ERROR
        System.out.println("    maxRetries = 5;  // COMPILE ERROR!");

        // ------------------------------------------------------------------
        // Part 2: Final Methods
        // ------------------------------------------------------------------
        System.out.println();
        System.out.println("[Part 2: Final Methods]");
        System.out.println();

        SavingsAccount savings = new SavingsAccount("SAV-001", 10000, 0.05);
        savings.displayInfo();  // Overridden version
        System.out.println();

        // authenticate() is final - guaranteed to use the secure implementation
        boolean auth = savings.authenticate("SAV-001");
        System.out.println("    Authentication result: " + auth);
        System.out.println();

        savings.applyInterest();
        savings.displayInfo();

        System.out.println();
        System.out.println("  Final methods ensure critical behavior cannot be changed:");
        System.out.println("  - authenticate() always uses the secure check");
        System.out.println("  - getBalance() always returns the real balance");
        System.out.println("  - No subclass can weaken these guarantees!");

        // ------------------------------------------------------------------
        // Part 3: Final Classes
        // ------------------------------------------------------------------
        System.out.println();
        System.out.println("[Part 3: Final Classes]");
        System.out.println();

        ImmutablePoint p1 = new ImmutablePoint(3, 4);
        ImmutablePoint p2 = new ImmutablePoint(6, 8);

        System.out.println("  Point 1: " + p1);
        System.out.println("  Point 2: " + p2);
        System.out.println("  Distance: " + String.format("%.2f", p1.distanceTo(p2)));
        System.out.println();

        System.out.println("  ImmutablePoint is final - cannot be subclassed.");
        System.out.println("  class ExtendedPoint extends ImmutablePoint { }  // COMPILE ERROR!");
        System.out.println();
        System.out.println("  Java standard final classes: String, Integer, Double, Math, System");

        // ------------------------------------------------------------------
        // Part 4: final Reference vs Object Mutability
        // ------------------------------------------------------------------
        System.out.println();
        System.out.println("[Part 4: final Reference vs Object Mutability]");
        System.out.println();

        final MutableBox box = new MutableBox(10);
        System.out.println("  final MutableBox box = " + box);

        // Cannot reassign the reference
        // box = new MutableBox(20);  // COMPILE ERROR!
        System.out.println("  box = new MutableBox(20);  // COMPILE ERROR! (can't reassign)");

        // BUT the object itself is still mutable!
        box.value = 20;  // This is FINE!
        System.out.println("  box.value = 20;            // OK! (object is mutable)");
        System.out.println("  box = " + box);

        System.out.println();
        System.out.println("  IMPORTANT: final prevents REFERENCE reassignment,");
        System.out.println("  NOT object mutation. For true immutability, also:");
        System.out.println("  - Make all fields private final");
        System.out.println("  - No setter methods");
        System.out.println("  - Return defensive copies of mutable fields");

        // ------------------------------------------------------------------
        // Summary
        // ------------------------------------------------------------------
        System.out.println();
        System.out.println("[Summary: final Keyword]");
        System.out.println("  final variable -> cannot be reassigned");
        System.out.println("  final method   -> cannot be overridden");
        System.out.println("  final class    -> cannot be extended (subclassed)");
    }
}
