package com.example.week04.state;

/**
 * CEN206 - Week 4: State Diagram -- Vending Machine
 *
 * A more complex state machine that models a vending machine with
 * guard conditions and actions.
 *
 * <pre>
 *  ●──> IDLE
 *         │
 *         │ insertCoin(amount) / addToBalance
 *         ▼
 *     ACCEPTING_COINS ◄──┐
 *         │               │
 *         │ insertCoin()  │
 *         └───────────────┘
 *         │
 *         │ selectProduct(price)
 *         │   [balance >= price] / dispense, return change
 *         ▼
 *     DISPENSING
 *         │
 *         │ / done
 *         ▼
 *       IDLE
 *
 *   selectProduct(price)
 *     [balance < price] / show "Insufficient funds"
 *     ---> stay in ACCEPTING_COINS
 *
 *   cancel() from ACCEPTING_COINS / refund
 *     ---> IDLE
 * </pre>
 */
public class VendingMachineStateMachine {

    // ----------------------------------------------------------------
    // State enum
    // ----------------------------------------------------------------

    public enum State {
        IDLE,
        ACCEPTING_COINS,
        DISPENSING
    }

    // ----------------------------------------------------------------
    // Context class
    // ----------------------------------------------------------------

    /**
     * Vending machine with explicit state transitions matching the
     * state diagram above.
     */
    public static class VendingMachine {
        private State currentState;
        private double balance;

        public VendingMachine() {
            this.currentState = State.IDLE;
            this.balance = 0.0;
            System.out.println("  VendingMachine initialised in state: " + currentState);
        }

        // ---------- Events ----------

        /** Event: customer inserts a coin. */
        public void insertCoin(double amount) {
            switch (currentState) {
                case IDLE -> {
                    balance += amount;
                    currentState = State.ACCEPTING_COINS;
                    System.out.println("    IDLE -> ACCEPTING_COINS"
                            + " (inserted $" + fmt(amount)
                            + ", balance=$" + fmt(balance) + ")");
                }
                case ACCEPTING_COINS -> {
                    balance += amount;
                    // Self-transition: stay in ACCEPTING_COINS
                    System.out.println("    ACCEPTING_COINS -> ACCEPTING_COINS"
                            + " (inserted $" + fmt(amount)
                            + ", balance=$" + fmt(balance) + ")");
                }
                case DISPENSING -> {
                    System.out.println("    [DISPENSING] Cannot insert coins"
                            + " while dispensing.");
                }
            }
        }

        /** Event: customer selects a product. */
        public void selectProduct(String productName, double price) {
            switch (currentState) {
                case IDLE -> {
                    System.out.println("    [IDLE] Please insert coins first.");
                }
                case ACCEPTING_COINS -> {
                    // Guard: balance >= price
                    if (balance >= price) {
                        currentState = State.DISPENSING;
                        System.out.println("    ACCEPTING_COINS -> DISPENSING"
                                + " (selected \"" + productName + "\" $"
                                + fmt(price) + ")");

                        // Action: dispense product
                        System.out.println("    [DISPENSING] Dispensing \""
                                + productName + "\"...");

                        double change = balance - price;
                        if (change > 0) {
                            System.out.println("    [DISPENSING] Returning change: $"
                                    + fmt(change));
                        }

                        // Automatic transition back to IDLE
                        balance = 0.0;
                        currentState = State.IDLE;
                        System.out.println("    DISPENSING -> IDLE (done)");
                    } else {
                        // Guard failed -- stay in current state
                        System.out.println("    [ACCEPTING_COINS] Insufficient"
                                + " funds for \"" + productName + "\" ($"
                                + fmt(price) + "). Balance=$"
                                + fmt(balance)
                                + ". Insert $" + fmt(price - balance)
                                + " more.");
                    }
                }
                case DISPENSING -> {
                    System.out.println("    [DISPENSING] Please wait...");
                }
            }
        }

        /** Event: customer presses cancel. */
        public void cancel() {
            switch (currentState) {
                case IDLE -> {
                    System.out.println("    [IDLE] Nothing to cancel.");
                }
                case ACCEPTING_COINS -> {
                    System.out.println("    ACCEPTING_COINS -> IDLE"
                            + " (cancelled, refunding $" + fmt(balance) + ")");
                    balance = 0.0;
                    currentState = State.IDLE;
                }
                case DISPENSING -> {
                    System.out.println("    [DISPENSING] Cannot cancel while"
                            + " dispensing.");
                }
            }
        }

        public State getCurrentState() {
            return currentState;
        }

        public double getBalance() {
            return balance;
        }

        private static String fmt(double v) {
            return String.format("%.2f", v);
        }
    }

    // ----------------------------------------------------------------
    // Demo
    // ----------------------------------------------------------------

    /** Runs the vending machine state machine demonstration. */
    public static void demo() {
        VendingMachine vm = new VendingMachine();

        // Scenario 1: successful purchase
        System.out.println("\n  --- Scenario 1: Successful Purchase ---");
        vm.insertCoin(1.00);
        vm.insertCoin(0.50);
        vm.selectProduct("Cola", 1.25);

        // Scenario 2: insufficient funds, then add more
        System.out.println("\n  --- Scenario 2: Insufficient Funds ---");
        vm.insertCoin(0.50);
        vm.selectProduct("Water", 0.75);   // not enough
        vm.insertCoin(0.50);
        vm.selectProduct("Water", 0.75);   // now enough

        // Scenario 3: cancel and refund
        System.out.println("\n  --- Scenario 3: Cancel and Refund ---");
        vm.insertCoin(2.00);
        vm.cancel();

        System.out.println("\n  Final state: " + vm.getCurrentState()
                + ", balance: $" + String.format("%.2f", vm.getBalance()));
    }
}
