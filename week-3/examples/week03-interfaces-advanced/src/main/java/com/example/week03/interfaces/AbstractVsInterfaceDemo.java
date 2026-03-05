package com.example.week03.interfaces;

/**
 * ============================================================================
 * DEMO 5: Abstract Class vs Interface - Side-by-Side Comparison
 * ============================================================================
 *
 * Both abstract classes and interfaces define contracts, but they differ:
 *
 * +---------------------------+---------------------+---------------------+
 * | Feature                   | Abstract Class      | Interface           |
 * +---------------------------+---------------------+---------------------+
 * | Methods                   | Abstract + Concrete | Abstract + Default  |
 * | Fields                    | Any (instance/stat) | Only constants      |
 * | Constructors              | YES                 | NO                  |
 * | State (instance fields)   | YES                 | NO                  |
 * | Multiple inheritance      | NO (single only)    | YES (implement many)|
 * | Access modifiers          | Any                 | public (abstract)   |
 * | When to use               | IS-A + shared state | CAN-DO capability   |
 * +---------------------------+---------------------+---------------------+
 *
 * Rule of thumb:
 *   - Use ABSTRACT CLASS when classes share common state/behavior AND
 *     have an IS-A relationship (e.g., Dog IS-A Animal).
 *   - Use INTERFACE when you want to define a capability that unrelated
 *     classes can implement (e.g., Serializable, Comparable, Runnable).
 *
 * ============================================================================
 */
public class AbstractVsInterfaceDemo {

    // =======================================================================
    // SCENARIO: We model a "payment system" using BOTH approaches.
    // =======================================================================

    // -----------------------------------------------------------------------
    // APPROACH 1: Using an ABSTRACT CLASS
    // -----------------------------------------------------------------------

    /**
     * Abstract class approach for payment processing.
     * Provides shared state (id, amount) and common behavior (getReceipt).
     */
    static abstract class AbstractPayment {
        // Instance fields - abstract classes CAN have state
        protected String id;
        protected double amount;
        protected String currency;

        // Constructor - abstract classes CAN have constructors
        public AbstractPayment(String id, double amount, String currency) {
            this.id = id;
            this.amount = amount;
            this.currency = currency;
        }

        // Abstract method - subclasses MUST implement
        public abstract boolean processPayment();

        // Abstract method
        public abstract String getPaymentType();

        // CONCRETE method - shared behavior that all subclasses inherit
        public String getReceipt() {
            return String.format("Receipt [%s] %s: %.2f %s",
                    id, getPaymentType(), amount, currency);
        }

        // Another concrete method using instance state
        public boolean isValidAmount() {
            return amount > 0;
        }
    }

    /**
     * CreditCardPayment extends the abstract class.
     */
    static class CreditCardPayment extends AbstractPayment {
        private String cardNumber;

        public CreditCardPayment(String id, double amount, String currency, String cardNumber) {
            super(id, amount, currency);  // Call abstract class constructor
            this.cardNumber = cardNumber;
        }

        @Override
        public boolean processPayment() {
            System.out.println("    Processing credit card payment...");
            System.out.println("    Card: ****" + cardNumber.substring(cardNumber.length() - 4));
            return true;
        }

        @Override
        public String getPaymentType() {
            return "Credit Card";
        }
    }

    /**
     * BankTransferPayment extends the abstract class.
     */
    static class BankTransferPayment extends AbstractPayment {
        private String bankAccount;

        public BankTransferPayment(String id, double amount, String currency, String bankAccount) {
            super(id, amount, currency);
            this.bankAccount = bankAccount;
        }

        @Override
        public boolean processPayment() {
            System.out.println("    Processing bank transfer...");
            System.out.println("    Account: " + bankAccount);
            return true;
        }

        @Override
        public String getPaymentType() {
            return "Bank Transfer";
        }
    }

    // -----------------------------------------------------------------------
    // APPROACH 2: Using an INTERFACE
    // -----------------------------------------------------------------------

    /**
     * Interface approach for payment processing.
     * Defines the contract WITHOUT any state.
     */
    interface PaymentProcessor {
        // Constants only (public static final)
        String DEFAULT_CURRENCY = "USD";

        // Abstract methods
        boolean processPayment();
        String getPaymentType();
        double getAmount();
        String getId();

        // Default method - provides common behavior
        default String getReceipt() {
            return String.format("Receipt [%s] %s: %.2f",
                    getId(), getPaymentType(), getAmount());
        }

        // Default method
        default boolean isValidAmount() {
            return getAmount() > 0;
        }
    }

    /**
     * CryptoPayment implements the interface.
     * Each implementing class manages its own state.
     */
    static class CryptoPayment implements PaymentProcessor {
        private String id;
        private double amount;
        private String walletAddress;

        public CryptoPayment(String id, double amount, String walletAddress) {
            this.id = id;
            this.amount = amount;
            this.walletAddress = walletAddress;
        }

        @Override
        public boolean processPayment() {
            System.out.println("    Processing crypto payment...");
            System.out.println("    Wallet: " + walletAddress);
            return true;
        }

        @Override
        public String getPaymentType() { return "Cryptocurrency"; }

        @Override
        public double getAmount() { return amount; }

        @Override
        public String getId() { return id; }
    }

    // -----------------------------------------------------------------------
    // ADVANTAGE OF INTERFACES: Multiple implementation
    // -----------------------------------------------------------------------

    /**
     * Auditable is an additional capability.
     * A class can implement PaymentProcessor AND Auditable.
     * This is NOT possible with abstract classes (no multiple inheritance).
     */
    interface Auditable {
        String getAuditLog();
    }

    /**
     * Refundable is another capability.
     */
    interface Refundable {
        boolean refund();
    }

    /**
     * DigitalWalletPayment implements THREE interfaces.
     * Try doing this with abstract classes - you CAN'T!
     */
    static class DigitalWalletPayment implements PaymentProcessor, Auditable, Refundable {
        private String id;
        private double amount;
        private String walletProvider;

        public DigitalWalletPayment(String id, double amount, String walletProvider) {
            this.id = id;
            this.amount = amount;
            this.walletProvider = walletProvider;
        }

        // PaymentProcessor methods
        @Override
        public boolean processPayment() {
            System.out.println("    Processing " + walletProvider + " payment...");
            return true;
        }

        @Override
        public String getPaymentType() { return walletProvider; }

        @Override
        public double getAmount() { return amount; }

        @Override
        public String getId() { return id; }

        // Auditable method
        @Override
        public String getAuditLog() {
            return "Audit: " + id + " via " + walletProvider + " for $" + amount;
        }

        // Refundable method
        @Override
        public boolean refund() {
            System.out.println("    Refunding $" + amount + " to " + walletProvider + " wallet...");
            return true;
        }
    }

    // =======================================================================
    // COMBINING BOTH: Abstract class + Interface
    // =======================================================================

    /**
     * In practice, you often use BOTH abstract classes and interfaces together.
     * The abstract class provides shared state/behavior, and interfaces add capabilities.
     */
    interface Trackable {
        String getTrackingId();
        String getStatus();
    }

    static abstract class AbstractTrackedPayment extends AbstractPayment implements Trackable {
        protected String status;

        public AbstractTrackedPayment(String id, double amount, String currency) {
            super(id, amount, currency);
            this.status = "PENDING";
        }

        @Override
        public String getTrackingId() { return "TRK-" + id; }

        @Override
        public String getStatus() { return status; }
    }

    static class TrackedCardPayment extends AbstractTrackedPayment {
        public TrackedCardPayment(String id, double amount) {
            super(id, amount, "USD");
        }

        @Override
        public boolean processPayment() {
            status = "PROCESSED";
            System.out.println("    Tracked card payment processed. Status: " + status);
            return true;
        }

        @Override
        public String getPaymentType() { return "Tracked Credit Card"; }
    }

    // =======================================================================
    // DEMO METHOD
    // =======================================================================

    public static void demo() {
        // --- Section A: Abstract class approach ---
        System.out.println("  [A] Abstract Class Approach");
        System.out.println("  ----------------------------");

        CreditCardPayment ccPayment = new CreditCardPayment(
                "PAY-001", 99.99, "USD", "4111222233334444");
        ccPayment.processPayment();
        System.out.println("    Receipt: " + ccPayment.getReceipt());
        System.out.println("    Valid amount? " + ccPayment.isValidAmount());
        System.out.println();

        BankTransferPayment btPayment = new BankTransferPayment(
                "PAY-002", 500.00, "EUR", "DE89370400440532013000");
        btPayment.processPayment();
        System.out.println("    Receipt: " + btPayment.getReceipt());
        System.out.println();

        // Polymorphism with abstract class type
        AbstractPayment[] absPayments = { ccPayment, btPayment };
        System.out.println("    Polymorphism via abstract class:");
        for (AbstractPayment p : absPayments) {
            System.out.println("      " + p.getPaymentType() + " -> " + p.getReceipt());
        }
        System.out.println();

        // --- Section B: Interface approach ---
        System.out.println("  [B] Interface Approach");
        System.out.println("  -----------------------");

        CryptoPayment cryptoPayment = new CryptoPayment(
                "PAY-003", 0.5, "0xABC123DEF456");
        cryptoPayment.processPayment();
        System.out.println("    Receipt: " + cryptoPayment.getReceipt());
        System.out.println();

        // --- Section C: Multiple interface implementation ---
        System.out.println("  [C] Multiple Interface Implementation (Interface Advantage)");
        System.out.println("  ------------------------------------------------------------");

        DigitalWalletPayment dwPayment = new DigitalWalletPayment(
                "PAY-004", 25.00, "PayPal");
        dwPayment.processPayment();
        System.out.println("    Receipt: " + dwPayment.getReceipt());
        System.out.println("    Audit: " + dwPayment.getAuditLog());
        dwPayment.refund();
        System.out.println();

        // Can be referenced by ANY of its interface types
        PaymentProcessor pp = dwPayment;
        Auditable aud = dwPayment;
        Refundable ref = dwPayment;
        System.out.println("    As PaymentProcessor: " + pp.getPaymentType());
        System.out.println("    As Auditable: " + aud.getAuditLog());
        System.out.println("    As Refundable: refund = " + ref.refund());
        System.out.println();

        // --- Section D: Combining abstract class + interfaces ---
        System.out.println("  [D] Combining Abstract Class + Interface");
        System.out.println("  ------------------------------------------");

        TrackedCardPayment trackedPayment = new TrackedCardPayment("PAY-005", 150.00);
        System.out.println("    Before processing:");
        System.out.println("      Status: " + trackedPayment.getStatus());
        System.out.println("      Tracking ID: " + trackedPayment.getTrackingId());
        trackedPayment.processPayment();
        System.out.println("    After processing:");
        System.out.println("      Status: " + trackedPayment.getStatus());
        System.out.println("      Receipt: " + trackedPayment.getReceipt());
        System.out.println();

        // --- Section E: Summary comparison ---
        System.out.println("  [E] Summary: When to Use Which?");
        System.out.println("  ---------------------------------");
        System.out.println("    ABSTRACT CLASS when:");
        System.out.println("      - Classes share common STATE (fields)");
        System.out.println("      - There is a clear IS-A relationship");
        System.out.println("      - You need constructors for initialization");
        System.out.println("      - You want to share concrete method implementations");
        System.out.println();
        System.out.println("    INTERFACE when:");
        System.out.println("      - You want to define a CAPABILITY (CAN-DO)");
        System.out.println("      - Unrelated classes need the same contract");
        System.out.println("      - You need multiple inheritance of type");
        System.out.println("      - You want maximum flexibility and loose coupling");
        System.out.println();
        System.out.println("    BOTH TOGETHER when:");
        System.out.println("      - Abstract class for shared state + core behavior");
        System.out.println("      - Interfaces for additional capabilities");
    }
}
