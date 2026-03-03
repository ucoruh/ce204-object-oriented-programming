package com.example.week01.basics;

/**
 * ==========================================================================
 * DEMO 3: Constructors
 * ==========================================================================
 *
 * A CONSTRUCTOR is a special method that is called when an object is created.
 * It initializes the new object's state (fields).
 *
 * Key Characteristics:
 *   - Same name as the class
 *   - No return type (not even void)
 *   - Called automatically with 'new'
 *   - Can be overloaded (multiple constructors)
 *
 * Types of Constructors:
 *   1. Default Constructor    - No parameters, uses default values
 *   2. Parameterized          - Takes arguments to initialize fields
 *   3. Copy Constructor       - Creates a copy of another object
 *
 * Constructor Chaining:
 *   - Using this() to call another constructor in the same class
 *   - Must be the FIRST statement in the constructor
 *   - Avoids code duplication
 */
public class ConstructorDemo {

    // ======================================================================
    // Example 1: Book - demonstrating different constructor types
    // ======================================================================

    /**
     * The Book class demonstrates default, parameterized, and copy constructors.
     */
    static class Book {
        String title;
        String author;
        int pages;
        double price;

        // --- Default Constructor ---
        // If we define NO constructors, Java provides a default one automatically.
        // But once we define ANY constructor, the automatic default is NOT provided.
        // So we define it explicitly here.
        Book() {
            this.title = "Unknown";
            this.author = "Unknown";
            this.pages = 0;
            this.price = 0.0;
            System.out.println("  [Default Constructor called]");
        }

        // --- Parameterized Constructor (2 params) ---
        // Allows creating a book with title and author.
        Book(String title, String author) {
            this.title = title;      // 'this.title' = field, 'title' = parameter
            this.author = author;
            this.pages = 0;
            this.price = 0.0;
            System.out.println("  [Constructor(title, author) called]");
        }

        // --- Parameterized Constructor (all params) ---
        // Allows creating a fully initialized book.
        Book(String title, String author, int pages, double price) {
            this.title = title;
            this.author = author;
            this.pages = pages;
            this.price = price;
            System.out.println("  [Constructor(title, author, pages, price) called]");
        }

        // --- Copy Constructor ---
        // Creates a new Book that is a copy of an existing Book.
        // This is useful when you need an independent copy.
        Book(Book other) {
            this.title = other.title;
            this.author = other.author;
            this.pages = other.pages;
            this.price = other.price;
            System.out.println("  [Copy Constructor called]");
        }

        void displayInfo() {
            System.out.println("  Title: " + title + " | Author: " + author
                    + " | Pages: " + pages + " | Price: $" + price);
        }
    }

    // ======================================================================
    // Example 2: Rectangle - Constructor Chaining with this()
    // ======================================================================

    /**
     * The Rectangle class demonstrates constructor chaining using this().
     * Each simpler constructor delegates to a more complete one.
     */
    static class Rectangle {
        double width;
        double height;
        String color;

        // Full constructor - the "target" of the chain
        Rectangle(double width, double height, String color) {
            this.width = width;
            this.height = height;
            this.color = color;
            System.out.println("  [Full Constructor called: " + width + "x" + height + " " + color + "]");
        }

        // Constructor with width and height only - chains to full constructor
        Rectangle(double width, double height) {
            this(width, height, "Black");  // this() MUST be the first statement!
            System.out.println("  [Chained from (width, height) constructor]");
        }

        // Square constructor - chains to width+height constructor
        Rectangle(double side) {
            this(side, side);  // Chains to Rectangle(double, double)
            System.out.println("  [Chained from (side) constructor - creates a square]");
        }

        // Default constructor - chains to square constructor
        Rectangle() {
            this(1.0);  // Chains to Rectangle(double)
            System.out.println("  [Chained from default constructor]");
        }

        double area() {
            return width * height;
        }

        void displayInfo() {
            System.out.println("  Rectangle: " + width + " x " + height
                    + " | Color: " + color + " | Area: " + area());
        }
    }

    // ======================================================================
    // Example 3: BankAccount - Practical constructor usage
    // ======================================================================

    /**
     * A more practical example showing how constructors enforce valid state.
     */
    static class BankAccount {
        private final String accountNumber;
        private String ownerName;
        private double balance;

        // Constructor that validates input
        BankAccount(String accountNumber, String ownerName, double initialBalance) {
            // Validation in the constructor ensures objects are always valid
            if (accountNumber == null || accountNumber.isEmpty()) {
                throw new IllegalArgumentException("Account number cannot be empty");
            }
            if (ownerName == null || ownerName.isEmpty()) {
                throw new IllegalArgumentException("Owner name cannot be empty");
            }
            if (initialBalance < 0) {
                throw new IllegalArgumentException("Initial balance cannot be negative");
            }

            this.accountNumber = accountNumber;
            this.ownerName = ownerName;
            this.balance = initialBalance;
            System.out.println("  [Account created: " + accountNumber + " for " + ownerName + "]");
        }

        // Convenience constructor - opens account with zero balance
        BankAccount(String accountNumber, String ownerName) {
            this(accountNumber, ownerName, 0.0);
        }

        void displayInfo() {
            System.out.println("  Account: " + accountNumber
                    + " | Owner: " + ownerName
                    + " | Balance: $" + String.format("%.2f", balance));
        }
    }

    // ======================================================================
    // Demo method
    // ======================================================================

    /**
     * Demonstrates all constructor concepts.
     */
    public static void demo() {

        // ------------------------------------------------------------------
        // Part 1: Different Constructor Types
        // ------------------------------------------------------------------
        System.out.println("[Part 1: Different Constructor Types]");
        System.out.println();

        System.out.println("Creating book with default constructor:");
        Book book1 = new Book();
        book1.displayInfo();
        System.out.println();

        System.out.println("Creating book with 2-parameter constructor:");
        Book book2 = new Book("Clean Code", "Robert C. Martin");
        book2.displayInfo();
        System.out.println();

        System.out.println("Creating book with full constructor:");
        Book book3 = new Book("Design Patterns", "Gang of Four", 395, 49.99);
        book3.displayInfo();
        System.out.println();

        System.out.println("Creating book with copy constructor:");
        Book book4 = new Book(book3);
        book4.displayInfo();

        // ------------------------------------------------------------------
        // Part 2: Copy Constructor Independence
        // ------------------------------------------------------------------
        System.out.println();
        System.out.println("[Part 2: Copy Constructor Creates Independent Copy]");
        System.out.println();

        book4.title = "Design Patterns (My Copy)";
        book4.price = 39.99;
        System.out.println("After modifying the copy:");
        System.out.println("  Original: ");
        book3.displayInfo();
        System.out.println("  Copy:     ");
        book4.displayInfo();
        System.out.println("  (Changes to copy do NOT affect the original)");

        // ------------------------------------------------------------------
        // Part 3: Constructor Chaining with this()
        // ------------------------------------------------------------------
        System.out.println();
        System.out.println("[Part 3: Constructor Chaining with this()]");
        System.out.println();

        System.out.println("Creating Rectangle with default constructor (chains through all):");
        Rectangle r1 = new Rectangle();
        r1.displayInfo();
        System.out.println();

        System.out.println("Creating Rectangle with one parameter (square):");
        Rectangle r2 = new Rectangle(5.0);
        r2.displayInfo();
        System.out.println();

        System.out.println("Creating Rectangle with two parameters:");
        Rectangle r3 = new Rectangle(4.0, 6.0);
        r3.displayInfo();
        System.out.println();

        System.out.println("Creating Rectangle with all parameters:");
        Rectangle r4 = new Rectangle(3.0, 7.0, "Red");
        r4.displayInfo();

        // ------------------------------------------------------------------
        // Part 4: Constructor Validation
        // ------------------------------------------------------------------
        System.out.println();
        System.out.println("[Part 4: Constructor Validation]");
        System.out.println();

        BankAccount acc1 = new BankAccount("ACC-001", "Alice Johnson", 1000.00);
        acc1.displayInfo();
        System.out.println();

        BankAccount acc2 = new BankAccount("ACC-002", "Bob Smith");
        acc2.displayInfo();
        System.out.println();

        // Demonstrate validation
        System.out.println("Trying to create account with negative balance...");
        try {
            BankAccount acc3 = new BankAccount("ACC-003", "Charlie", -500);
        } catch (IllegalArgumentException e) {
            System.out.println("  Exception caught: " + e.getMessage());
            System.out.println("  (Constructor validation prevented invalid object creation!)");
        }

        // ------------------------------------------------------------------
        // Summary
        // ------------------------------------------------------------------
        System.out.println();
        System.out.println("[Summary]");
        System.out.println("  - Default constructor: no parameters, sets default values");
        System.out.println("  - Parameterized: takes arguments to initialize fields");
        System.out.println("  - Copy constructor: creates a copy of an existing object");
        System.out.println("  - this() chains to another constructor (must be first statement)");
        System.out.println("  - Constructors can validate input to ensure valid object state");
    }
}
