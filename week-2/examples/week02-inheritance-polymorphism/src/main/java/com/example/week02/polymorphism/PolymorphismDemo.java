package com.example.week02.polymorphism;

import java.util.ArrayList;
import java.util.List;

/**
 * ==========================================================================
 * DEMO 6: Polymorphism
 * ==========================================================================
 *
 * POLYMORPHISM (Greek: "many forms") means the same interface can behave
 * differently depending on the actual object type at runtime.
 *
 * Types of Polymorphism:
 *   1. Compile-time (Static)  - Method overloading
 *   2. Runtime (Dynamic)      - Method overriding + parent reference
 *
 * Runtime Polymorphism:
 *   - A parent reference variable can hold a child object
 *   - When you call a method, the JVM decides at RUNTIME which version to run
 *   - This is called "dynamic method dispatch" or "late binding"
 *
 * Key Principle:
 *   "Program to the interface (supertype), not the implementation (subtype)"
 *
 * Benefits:
 *   - Write flexible, extensible code
 *   - Add new types without changing existing code (Open/Closed Principle)
 *   - Reduce conditional logic (if/else chains)
 */
public class PolymorphismDemo {

    // ======================================================================
    // Example 1: Payment system - classic polymorphism example
    // ======================================================================

    /**
     * Base class for all payment methods.
     * Different payment types will override processPayment().
     */
    static class Payment {
        double amount;

        Payment(double amount) {
            this.amount = amount;
        }

        // This method will be overridden by each payment type
        void processPayment() {
            System.out.println("    Processing generic payment of $"
                    + String.format("%.2f", amount));
        }

        String getReceipt() {
            return "Payment of $" + String.format("%.2f", amount);
        }
    }

    static class CreditCardPayment extends Payment {
        String cardNumber;

        CreditCardPayment(double amount, String cardNumber) {
            super(amount);
            this.cardNumber = cardNumber;
        }

        @Override
        void processPayment() {
            String maskedCard = "****-****-****-" + cardNumber.substring(cardNumber.length() - 4);
            System.out.println("    Processing credit card payment of $"
                    + String.format("%.2f", amount) + " on card " + maskedCard);
        }

        @Override
        String getReceipt() {
            return "Credit Card Payment: $" + String.format("%.2f", amount);
        }
    }

    static class PayPalPayment extends Payment {
        String email;

        PayPalPayment(double amount, String email) {
            super(amount);
            this.email = email;
        }

        @Override
        void processPayment() {
            System.out.println("    Processing PayPal payment of $"
                    + String.format("%.2f", amount) + " from " + email);
        }

        @Override
        String getReceipt() {
            return "PayPal Payment: $" + String.format("%.2f", amount) + " (" + email + ")";
        }
    }

    static class BankTransferPayment extends Payment {
        String bankName;

        BankTransferPayment(double amount, String bankName) {
            super(amount);
            this.bankName = bankName;
        }

        @Override
        void processPayment() {
            System.out.println("    Processing bank transfer of $"
                    + String.format("%.2f", amount) + " via " + bankName);
        }

        @Override
        String getReceipt() {
            return "Bank Transfer: $" + String.format("%.2f", amount) + " via " + bankName;
        }
    }

    static class CryptoPayment extends Payment {
        String walletAddress;

        CryptoPayment(double amount, String walletAddress) {
            super(amount);
            this.walletAddress = walletAddress;
        }

        @Override
        void processPayment() {
            System.out.println("    Processing crypto payment of $"
                    + String.format("%.2f", amount) + " to wallet " + walletAddress);
        }

        @Override
        String getReceipt() {
            return "Crypto Payment: $" + String.format("%.2f", amount);
        }
    }

    /**
     * PaymentProcessor works with the BASE type (Payment).
     * It does NOT know about specific payment types!
     * New payment types can be added without modifying this class.
     */
    static class PaymentProcessor {
        private List<Payment> processedPayments = new ArrayList<>();

        // This method accepts ANY Payment subclass - polymorphism!
        void process(Payment payment) {
            payment.processPayment();  // Calls the OVERRIDDEN version
            processedPayments.add(payment);
        }

        void printReceipts() {
            System.out.println("    === Payment Receipts ===");
            double total = 0;
            for (Payment p : processedPayments) {
                System.out.println("    - " + p.getReceipt());
                total += p.amount;
            }
            System.out.println("    === Total: $" + String.format("%.2f", total) + " ===");
        }
    }

    // ======================================================================
    // Example 2: Polymorphic arrays and collections
    // ======================================================================

    static class Employee {
        String name;
        double baseSalary;

        Employee(String name, double baseSalary) {
            this.name = name;
            this.baseSalary = baseSalary;
        }

        double calculatePay() {
            return baseSalary;
        }

        String getRole() {
            return "Employee";
        }
    }

    static class FullTimeEmployee extends Employee {
        double bonus;

        FullTimeEmployee(String name, double baseSalary, double bonus) {
            super(name, baseSalary);
            this.bonus = bonus;
        }

        @Override
        double calculatePay() {
            return baseSalary + bonus;
        }

        @Override
        String getRole() {
            return "Full-Time";
        }
    }

    static class PartTimeEmployee extends Employee {
        int hoursWorked;
        double hourlyRate;

        PartTimeEmployee(String name, int hoursWorked, double hourlyRate) {
            super(name, 0);
            this.hoursWorked = hoursWorked;
            this.hourlyRate = hourlyRate;
        }

        @Override
        double calculatePay() {
            return hoursWorked * hourlyRate;
        }

        @Override
        String getRole() {
            return "Part-Time";
        }
    }

    static class Intern extends Employee {
        boolean isPaid;

        Intern(String name, boolean isPaid) {
            super(name, isPaid ? 1500 : 0);
            this.isPaid = isPaid;
        }

        @Override
        double calculatePay() {
            return isPaid ? baseSalary : 0;
        }

        @Override
        String getRole() {
            return isPaid ? "Paid Intern" : "Unpaid Intern";
        }
    }

    // ======================================================================
    // Demo method
    // ======================================================================

    public static void demo() {

        // ------------------------------------------------------------------
        // Part 1: Runtime Polymorphism - Payment System
        // ------------------------------------------------------------------
        System.out.println("[Part 1: Runtime Polymorphism - Payment System]");
        System.out.println();

        PaymentProcessor processor = new PaymentProcessor();

        // All these are stored as Payment references internally
        // but the ACTUAL type determines which processPayment() is called
        processor.process(new CreditCardPayment(99.99, "4532015112830366"));
        processor.process(new PayPalPayment(45.50, "user@example.com"));
        processor.process(new BankTransferPayment(250.00, "First National Bank"));
        processor.process(new CryptoPayment(75.00, "0x1a2b3c..."));

        System.out.println();
        processor.printReceipts();

        System.out.println();
        System.out.println("  Key point: PaymentProcessor never uses 'instanceof' or");
        System.out.println("  'if/else' to check the payment type. Polymorphism handles it!");

        // ------------------------------------------------------------------
        // Part 2: Polymorphic Arrays
        // ------------------------------------------------------------------
        System.out.println();
        System.out.println("[Part 2: Polymorphic Arrays and Collections]");
        System.out.println();

        // An array of the BASE type can hold ANY subclass object
        Employee[] employees = {
            new FullTimeEmployee("Alice", 5000, 1000),
            new FullTimeEmployee("Bob", 4500, 800),
            new PartTimeEmployee("Charlie", 80, 25),
            new PartTimeEmployee("Diana", 60, 30),
            new Intern("Eve", true),
            new Intern("Frank", false)
        };

        System.out.println("  Payroll Report:");
        System.out.println("  " + String.format("%-12s %-15s %10s", "Name", "Role", "Pay"));
        System.out.println("  " + "-".repeat(40));

        double totalPayroll = 0;
        for (Employee emp : employees) {
            double pay = emp.calculatePay();  // Calls the OVERRIDDEN version
            totalPayroll += pay;
            System.out.println("  " + String.format("%-12s %-15s $%9.2f",
                    emp.name, emp.getRole(), pay));
        }
        System.out.println("  " + "-".repeat(40));
        System.out.println("  " + String.format("%-12s %-15s $%9.2f",
                "", "TOTAL", totalPayroll));

        // ------------------------------------------------------------------
        // Part 3: Polymorphism with Method Parameters
        // ------------------------------------------------------------------
        System.out.println();
        System.out.println("[Part 3: Polymorphism with Method Parameters]");
        System.out.println();

        System.out.println("  A method that accepts a parent type works with ANY subtype:");
        System.out.println();

        printEmployeeInfo(new FullTimeEmployee("Alice", 5000, 1000));
        printEmployeeInfo(new PartTimeEmployee("Bob", 80, 25));
        printEmployeeInfo(new Intern("Charlie", true));

        // ------------------------------------------------------------------
        // Part 4: Dynamic Method Dispatch
        // ------------------------------------------------------------------
        System.out.println();
        System.out.println("[Part 4: Dynamic Method Dispatch]");
        System.out.println();

        // The DECLARED type is Employee, but the ACTUAL type varies
        Employee emp;

        emp = new FullTimeEmployee("Test1", 5000, 500);
        System.out.println("  Declared: Employee, Actual: FullTimeEmployee");
        System.out.println("  emp.calculatePay() = $" + String.format("%.2f", emp.calculatePay()));
        System.out.println("  emp.getRole() = " + emp.getRole());
        System.out.println();

        emp = new PartTimeEmployee("Test2", 40, 30);
        System.out.println("  Declared: Employee, Actual: PartTimeEmployee");
        System.out.println("  emp.calculatePay() = $" + String.format("%.2f", emp.calculatePay()));
        System.out.println("  emp.getRole() = " + emp.getRole());
        System.out.println();

        System.out.println("  The JVM determines at RUNTIME which method version to call");
        System.out.println("  based on the ACTUAL object type, not the declared type.");

        // ------------------------------------------------------------------
        // Summary
        // ------------------------------------------------------------------
        System.out.println();
        System.out.println("[Summary: Why Polymorphism Matters]");
        System.out.println("  1. Write code that works with base types");
        System.out.println("  2. New subtypes work automatically (Open/Closed Principle)");
        System.out.println("  3. Eliminates if/else chains for type checking");
        System.out.println("  4. Makes code more flexible and maintainable");
        System.out.println("  5. Enables 'programming to an interface'");
    }

    /**
     * Helper method that accepts ANY Employee subtype.
     * This works because of polymorphism.
     */
    private static void printEmployeeInfo(Employee emp) {
        System.out.println("  " + emp.name + " (" + emp.getRole()
                + "): $" + String.format("%.2f", emp.calculatePay()));
    }
}
