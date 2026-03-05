package com.example.week01.basics;

/**
 * ==========================================================================
 * DEMO 4: Access Modifiers
 * ==========================================================================
 *
 * Access modifiers control the VISIBILITY of classes, fields, methods,
 * and constructors. They are a key part of ENCAPSULATION.
 *
 * Java has four access levels (from most to least restrictive):
 *
 * +-----------+-------+--------+-----------+-----------+
 * | Modifier  | Class | Package| Subclass  | World     |
 * +-----------+-------+--------+-----------+-----------+
 * | private   |  YES  |   NO   |    NO     |    NO     |
 * | (default) |  YES  |  YES   |    NO     |    NO     |
 * | protected |  YES  |  YES   |   YES     |    NO     |
 * | public    |  YES  |  YES   |   YES     |   YES     |
 * +-----------+-------+--------+-----------+-----------+
 *
 * Best Practices:
 *   - Fields should typically be PRIVATE
 *   - Provide PUBLIC getters/setters when needed
 *   - Use PROTECTED for members that subclasses need
 *   - Use DEFAULT (package-private) for implementation details
 */
public class AccessModifierDemo {

    // ======================================================================
    // Example: Employee class with all four access levels
    // ======================================================================

    /**
     * The Employee class uses all four access modifiers to demonstrate
     * different levels of visibility.
     */
    static class Employee {

        // PUBLIC: Accessible from everywhere
        // Use for constants and methods that form the public API
        public String name;

        // PROTECTED: Accessible within package and by subclasses
        // Use for members that child classes might need
        protected String department;

        // DEFAULT (no modifier): Accessible within the same package only
        // Also called "package-private"
        // Use for implementation details shared within a package
        String employeeId;  // No modifier = default access

        // PRIVATE: Accessible ONLY within this class
        // Use for internal data that should be hidden
        private double salary;
        private String socialSecurityNumber;

        // --- Constructor ---
        public Employee(String name, String department, String employeeId,
                        double salary, String ssn) {
            this.name = name;
            this.department = department;
            this.employeeId = employeeId;
            this.salary = salary;
            this.socialSecurityNumber = ssn;
        }

        // --- PUBLIC method: part of the class's public API ---
        public void displayPublicInfo() {
            System.out.println("  Name: " + name);
            System.out.println("  Department: " + department);
            System.out.println("  Employee ID: " + employeeId);
            // We can access private members HERE because we are inside the class
            System.out.println("  (Salary and SSN are private - not shown publicly)");
        }

        // --- PRIVATE method: internal helper ---
        private String maskSSN() {
            // Only show last 4 digits
            return "XXX-XX-" + socialSecurityNumber.substring(socialSecurityNumber.length() - 4);
        }

        // --- PUBLIC method that uses private data safely ---
        public String getMaskedSSN() {
            return maskSSN();  // Public method delegates to private helper
        }

        // --- PROTECTED method: accessible to subclasses ---
        protected double getSalary() {
            return salary;
        }

        // --- DEFAULT (package-private) method ---
        void internalAudit() {
            System.out.println("  [AUDIT] " + name + " | Salary: $"
                    + String.format("%.2f", salary)
                    + " | SSN: " + maskSSN());
        }

        // --- PRIVATE setter with validation ---
        private void setSalary(double salary) {
            if (salary < 0) {
                throw new IllegalArgumentException("Salary cannot be negative");
            }
            this.salary = salary;
        }

        // --- PUBLIC method that uses private setter ---
        public void raiseSalary(double percentage) {
            if (percentage <= 0 || percentage > 50) {
                System.out.println("  Invalid raise percentage: " + percentage + "%");
                return;
            }
            double newSalary = this.salary * (1 + percentage / 100);
            setSalary(newSalary);  // Uses private setter with validation
            System.out.println("  Salary raised by " + percentage
                    + "%. New salary: $" + String.format("%.2f", this.salary));
        }
    }

    // ======================================================================
    // Example: Manager extends Employee - demonstrates protected access
    // ======================================================================

    /**
     * Manager extends Employee to show how protected members are inherited.
     */
    static class Manager extends Employee {
        private int teamSize;

        public Manager(String name, String department, String employeeId,
                       double salary, String ssn, int teamSize) {
            super(name, department, employeeId, salary, ssn);
            this.teamSize = teamSize;
        }

        public void displayManagerInfo() {
            // Can access PUBLIC members
            System.out.println("  Manager: " + name);

            // Can access PROTECTED members (inherited)
            System.out.println("  Department: " + department);
            System.out.println("  Salary: $" + String.format("%.2f", getSalary()));

            // Can access DEFAULT members (same package)
            System.out.println("  ID: " + employeeId);

            System.out.println("  Team Size: " + teamSize);

            // CANNOT access private members:
            // System.out.println(salary);                // Compile error!
            // System.out.println(socialSecurityNumber);   // Compile error!
            // maskSSN();                                  // Compile error!
        }
    }

    // ======================================================================
    // Example: AccessLevelChecker - shows what's accessible from outside
    // ======================================================================

    /**
     * This class demonstrates accessing Employee members from OUTSIDE the class.
     * Some members are accessible, others are not.
     */
    static class AccessLevelChecker {

        static void checkAccess(Employee emp) {
            System.out.println("  Accessing from OUTSIDE the Employee class:");
            System.out.println();

            // PUBLIC - accessible everywhere
            System.out.println("  [public]    emp.name = " + emp.name + "  -> OK");

            // PROTECTED - accessible in same package (we are in the same package here)
            System.out.println("  [protected] emp.department = " + emp.department + "  -> OK (same package)");

            // DEFAULT - accessible in same package
            System.out.println("  [default]   emp.employeeId = " + emp.employeeId + "  -> OK (same package)");

            // PRIVATE - NOT accessible!
            // emp.salary                  -> Compile Error!
            // emp.socialSecurityNumber    -> Compile Error!
            // emp.maskSSN()               -> Compile Error!
            System.out.println("  [private]   emp.salary          -> COMPILE ERROR (not accessible)");
            System.out.println("  [private]   emp.socialSecurityNumber -> COMPILE ERROR (not accessible)");
            System.out.println("  [private]   emp.maskSSN()       -> COMPILE ERROR (not accessible)");
            System.out.println();
            System.out.println("  But we CAN use public methods that expose private data safely:");
            System.out.println("  emp.getMaskedSSN() = " + emp.getMaskedSSN());
        }
    }

    // ======================================================================
    // Demo method
    // ======================================================================

    /**
     * Demonstrates access modifiers with practical examples.
     */
    public static void demo() {

        // ------------------------------------------------------------------
        // Part 1: Creating and using an Employee
        // ------------------------------------------------------------------
        System.out.println("[Part 1: Employee with Different Access Levels]");
        System.out.println();

        Employee emp = new Employee("Alice Johnson", "Engineering",
                "EMP-001", 85000.00, "123-45-6789");
        emp.displayPublicInfo();

        // ------------------------------------------------------------------
        // Part 2: Accessing members from outside
        // ------------------------------------------------------------------
        System.out.println();
        System.out.println("[Part 2: Accessing Members from Outside the Class]");
        System.out.println();

        AccessLevelChecker.checkAccess(emp);

        // ------------------------------------------------------------------
        // Part 3: Protected access in subclass
        // ------------------------------------------------------------------
        System.out.println();
        System.out.println("[Part 3: Protected Access in Subclass (Manager)]");
        System.out.println();

        Manager mgr = new Manager("Bob Smith", "Engineering",
                "MGR-001", 120000.00, "987-65-4321", 8);
        mgr.displayManagerInfo();

        // ------------------------------------------------------------------
        // Part 4: Using public API to modify private data
        // ------------------------------------------------------------------
        System.out.println();
        System.out.println("[Part 4: Modifying Private Data Through Public Methods]");
        System.out.println();

        System.out.println("  Giving Alice a 10% raise...");
        emp.raiseSalary(10);

        System.out.println("  Trying an invalid raise of 75%...");
        emp.raiseSalary(75);

        // ------------------------------------------------------------------
        // Part 5: Default (package-private) access
        // ------------------------------------------------------------------
        System.out.println();
        System.out.println("[Part 5: Default (Package-Private) Access]");
        System.out.println();

        System.out.println("  Package-private method accessible within same package:");
        emp.internalAudit();
        System.out.println("  (This method would NOT be accessible from a different package)");

        // ------------------------------------------------------------------
        // Summary Table
        // ------------------------------------------------------------------
        System.out.println();
        System.out.println("[Summary: Access Modifier Table]");
        System.out.println();
        System.out.println("  +-----------+-------+---------+----------+-------+");
        System.out.println("  | Modifier  | Class | Package | Subclass | World |");
        System.out.println("  +-----------+-------+---------+----------+-------+");
        System.out.println("  | private   |  YES  |   NO    |    NO    |  NO   |");
        System.out.println("  | default   |  YES  |   YES   |    NO    |  NO   |");
        System.out.println("  | protected |  YES  |   YES   |   YES    |  NO   |");
        System.out.println("  | public    |  YES  |   YES   |   YES    |  YES  |");
        System.out.println("  +-----------+-------+---------+----------+-------+");
    }
}
