package com.example.week01.basics;

/**
 * ==========================================================================
 * DEMO 6: Encapsulation
 * ==========================================================================
 *
 * ENCAPSULATION is one of the four pillars of OOP. It means:
 *   1. Bundling data (fields) and methods that operate on that data together
 *   2. Hiding internal state and requiring all interaction through methods
 *
 * Benefits:
 *   - Data Validation: setters can validate data before storing it
 *   - Read-only or Write-only fields: expose only what's needed
 *   - Implementation flexibility: internal representation can change
 *     without affecting code that uses the class
 *   - Better debugging: changes to data go through a single point
 *
 * Implementation:
 *   - Make fields PRIVATE
 *   - Provide PUBLIC getter methods (accessors) to read field values
 *   - Provide PUBLIC setter methods (mutators) to modify field values
 *   - Add validation logic in setters
 */
public class EncapsulationDemo {

    // ======================================================================
    // Example 1: Temperature - encapsulation with validation
    // ======================================================================

    /**
     * A Temperature class that stores temperature internally in Celsius
     * but can provide it in different units.
     * This demonstrates how encapsulation hides internal representation.
     */
    static class Temperature {
        // Private field - internal state is hidden
        private double celsius;

        // Constructor with validation
        public Temperature(double celsius) {
            setCelsius(celsius);  // Use setter to validate!
        }

        // --- Getter ---
        public double getCelsius() {
            return celsius;
        }

        // --- Setter with validation ---
        public void setCelsius(double celsius) {
            // Absolute zero is -273.15 C. No temperature can be lower.
            if (celsius < -273.15) {
                throw new IllegalArgumentException(
                        "Temperature cannot be below absolute zero (-273.15 C). Got: " + celsius);
            }
            this.celsius = celsius;
        }

        // --- Derived getter: Fahrenheit ---
        // This is computed from the internal Celsius value.
        // The caller doesn't need to know the internal representation!
        public double getFahrenheit() {
            return celsius * 9.0 / 5.0 + 32;
        }

        // --- Derived setter: set from Fahrenheit ---
        public void setFahrenheit(double fahrenheit) {
            setCelsius((fahrenheit - 32) * 5.0 / 9.0);
        }

        // --- Derived getter: Kelvin ---
        public double getKelvin() {
            return celsius + 273.15;
        }

        @Override
        public String toString() {
            return String.format("%.1f C / %.1f F / %.1f K",
                    celsius, getFahrenheit(), getKelvin());
        }
    }

    // ======================================================================
    // Example 2: BankAccount - encapsulation for security
    // ======================================================================

    /**
     * A bank account that protects its balance through encapsulation.
     * The balance can only change through controlled deposit/withdraw methods.
     */
    static class BankAccount {
        private final String accountNumber;  // final = cannot be changed after construction
        private String ownerName;
        private double balance;
        private int transactionCount;

        public BankAccount(String accountNumber, String ownerName, double initialDeposit) {
            this.accountNumber = accountNumber;
            this.ownerName = ownerName;
            this.balance = 0;
            this.transactionCount = 0;
            if (initialDeposit > 0) {
                deposit(initialDeposit);
            }
        }

        // --- Getters only (no setters for accountNumber - it's read-only) ---
        public String getAccountNumber() {
            return accountNumber;
        }

        public String getOwnerName() {
            return ownerName;
        }

        public double getBalance() {
            return balance;
        }

        public int getTransactionCount() {
            return transactionCount;
        }

        // --- Setter with validation ---
        public void setOwnerName(String ownerName) {
            if (ownerName == null || ownerName.trim().isEmpty()) {
                throw new IllegalArgumentException("Owner name cannot be empty");
            }
            this.ownerName = ownerName.trim();
        }

        // --- Controlled methods for modifying balance ---
        // Note: there is NO setBalance() method!
        // Balance can only change through deposit() and withdraw().

        public void deposit(double amount) {
            if (amount <= 0) {
                System.out.println("    Deposit failed: amount must be positive");
                return;
            }
            balance += amount;
            transactionCount++;
            System.out.println("    Deposited $" + String.format("%.2f", amount)
                    + " | Balance: $" + String.format("%.2f", balance));
        }

        public boolean withdraw(double amount) {
            if (amount <= 0) {
                System.out.println("    Withdrawal failed: amount must be positive");
                return false;
            }
            if (amount > balance) {
                System.out.println("    Withdrawal failed: insufficient funds"
                        + " (requested $" + String.format("%.2f", amount)
                        + ", available $" + String.format("%.2f", balance) + ")");
                return false;
            }
            balance -= amount;
            transactionCount++;
            System.out.println("    Withdrew $" + String.format("%.2f", amount)
                    + " | Balance: $" + String.format("%.2f", balance));
            return true;
        }

        public void displayStatement() {
            System.out.println("    Account: " + accountNumber);
            System.out.println("    Owner: " + ownerName);
            System.out.println("    Balance: $" + String.format("%.2f", balance));
            System.out.println("    Transactions: " + transactionCount);
        }
    }

    // ======================================================================
    // Example 3: ImmutablePerson - fully immutable class
    // ======================================================================

    /**
     * An IMMUTABLE class cannot be changed after creation.
     * Rules for immutability:
     *   1. Make the class final (cannot be subclassed)
     *   2. Make all fields private and final
     *   3. No setter methods
     *   4. If fields are mutable objects, return defensive copies
     */
    static final class ImmutablePerson {
        private final String name;
        private final int age;
        private final String email;

        // All values set in constructor - no changes allowed after this
        public ImmutablePerson(String name, int age, String email) {
            // Validate at construction time
            if (name == null || name.trim().isEmpty()) {
                throw new IllegalArgumentException("Name cannot be empty");
            }
            if (age < 0 || age > 150) {
                throw new IllegalArgumentException("Invalid age: " + age);
            }
            if (email == null || !email.contains("@")) {
                throw new IllegalArgumentException("Invalid email: " + email);
            }

            this.name = name;
            this.age = age;
            this.email = email;
        }

        // Only getters - NO setters
        public String getName() { return name; }
        public int getAge() { return age; }
        public String getEmail() { return email; }

        // "Modification" returns a NEW object instead
        public ImmutablePerson withName(String newName) {
            return new ImmutablePerson(newName, this.age, this.email);
        }

        public ImmutablePerson withAge(int newAge) {
            return new ImmutablePerson(this.name, newAge, this.email);
        }

        @Override
        public String toString() {
            return "ImmutablePerson{name='" + name + "', age=" + age + ", email='" + email + "'}";
        }
    }

    // ======================================================================
    // Demo method
    // ======================================================================

    /**
     * Demonstrates encapsulation concepts.
     */
    public static void demo() {

        // ------------------------------------------------------------------
        // Part 1: Encapsulation with Validation (Temperature)
        // ------------------------------------------------------------------
        System.out.println("[Part 1: Temperature - Validation in Setters]");
        System.out.println();

        Temperature temp = new Temperature(100);
        System.out.println("  Water boiling point: " + temp);

        temp.setCelsius(0);
        System.out.println("  Water freezing point: " + temp);

        temp.setFahrenheit(98.6);
        System.out.println("  Human body temperature: " + temp);

        System.out.println();
        System.out.println("  Trying to set temperature below absolute zero...");
        try {
            temp.setCelsius(-300);
        } catch (IllegalArgumentException e) {
            System.out.println("  Exception: " + e.getMessage());
            System.out.println("  (Setter validation prevented invalid state!)");
        }

        // ------------------------------------------------------------------
        // Part 2: BankAccount - Controlled Access
        // ------------------------------------------------------------------
        System.out.println();
        System.out.println("[Part 2: BankAccount - Controlled Access to Balance]");
        System.out.println();

        BankAccount account = new BankAccount("ACC-12345", "Alice Johnson", 1000.00);
        System.out.println();

        System.out.println("  Making transactions:");
        account.deposit(500.00);
        account.withdraw(200.00);
        account.withdraw(2000.00);  // Will fail - insufficient funds
        account.deposit(-50);       // Will fail - negative amount

        System.out.println();
        System.out.println("  Account Statement:");
        account.displayStatement();

        System.out.println();
        System.out.println("  Key point: there is NO setBalance() method!");
        System.out.println("  The balance can ONLY change through deposit() and withdraw().");
        System.out.println("  This prevents unauthorized modifications.");

        // ------------------------------------------------------------------
        // Part 3: Read-only fields
        // ------------------------------------------------------------------
        System.out.println();
        System.out.println("[Part 3: Read-Only Fields]");
        System.out.println();

        System.out.println("  account.getAccountNumber() = " + account.getAccountNumber());
        System.out.println("  The account number has NO setter - it is read-only.");
        System.out.println("  It was set in the constructor and can never be changed.");

        // ------------------------------------------------------------------
        // Part 4: Immutable Objects
        // ------------------------------------------------------------------
        System.out.println();
        System.out.println("[Part 4: Immutable Objects]");
        System.out.println();

        ImmutablePerson person = new ImmutablePerson("Alice", 30, "alice@example.com");
        System.out.println("  Original: " + person);

        // "Modifying" returns a NEW object, original is unchanged
        ImmutablePerson olderPerson = person.withAge(31);
        ImmutablePerson renamedPerson = person.withName("Alice Johnson");

        System.out.println("  After withAge(31): " + olderPerson);
        System.out.println("  After withName(\"Alice Johnson\"): " + renamedPerson);
        System.out.println("  Original unchanged: " + person);

        System.out.println();
        System.out.println("  Benefits of immutable objects:");
        System.out.println("  - Thread-safe (no synchronization needed)");
        System.out.println("  - Can be freely shared and cached");
        System.out.println("  - Cannot be corrupted after creation");
        System.out.println("  - Example in Java: String, Integer, LocalDate");

        // ------------------------------------------------------------------
        // Summary
        // ------------------------------------------------------------------
        System.out.println();
        System.out.println("[Summary: Encapsulation Best Practices]");
        System.out.println("  1. Make fields PRIVATE");
        System.out.println("  2. Provide getters for read access");
        System.out.println("  3. Provide setters WITH VALIDATION for write access");
        System.out.println("  4. Use 'final' for fields that should not change");
        System.out.println("  5. Consider immutability when objects don't need to change");
    }
}
