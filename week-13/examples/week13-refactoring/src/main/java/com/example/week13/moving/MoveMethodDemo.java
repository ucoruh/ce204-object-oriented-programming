package com.example.week13.moving;

/**
 * =============================================================================
 * REFACTORING TECHNIQUE: Move Method
 * =============================================================================
 *
 * Intent:
 *   Move a method from one class to the class that uses (or provides) most
 *   of the data the method needs.
 *
 * When to use:
 *   - A method uses more features of another class than of its own class
 *     (Feature Envy).
 *   - Moving the method reduces coupling and increases cohesion.
 *
 * Mechanics:
 *   1. Examine all features (fields, methods) used by the source method.
 *   2. Check the subclass/superclass chain for overrides.
 *   3. Declare the method in the target class.
 *   4. Copy the body, adjusting references.
 *   5. Make the source method delegate to the target, or remove it.
 *
 * This demo shows a before/after comparison.
 * =============================================================================
 */
public class MoveMethodDemo {

    // =========================================================================
    // BEFORE: overdraftCharge() lives in Account but uses BankAccountType data
    // =========================================================================

    /**
     * BEFORE: Account type determines overdraft charge, but the charge
     * logic is on Account -- it should be on BankAccountType.
     */
    static class BankAccountTypeBefore {
        private final String name;
        private final boolean premium;

        BankAccountTypeBefore(String name, boolean premium) {
            this.name = name;
            this.premium = premium;
        }

        boolean isPremium() { return premium; }
        String getName() { return name; }
    }

    static class AccountBefore {
        private double balance;
        private int daysOverdrawn;
        private BankAccountTypeBefore type;

        AccountBefore(double balance, int daysOverdrawn,
                      BankAccountTypeBefore type) {
            this.balance = balance;
            this.daysOverdrawn = daysOverdrawn;
            this.type = type;
        }

        /**
         * BAD: This method mostly uses data from BankAccountTypeBefore.
         * It should live there, not here.
         */
        double overdraftCharge() {
            if (type.isPremium()) {
                double baseCharge = 10;
                if (daysOverdrawn > 7) {
                    return baseCharge + (daysOverdrawn - 7) * 0.85;
                }
                return baseCharge;
            } else {
                return daysOverdrawn * 1.75;
            }
        }
    }

    // =========================================================================
    // AFTER: overdraftCharge() moved to BankAccountType where it belongs
    // =========================================================================

    /**
     * AFTER: BankAccountType now owns the overdraft charge logic.
     */
    static class BankAccountTypeAfter {
        private final String name;
        private final boolean premium;

        BankAccountTypeAfter(String name, boolean premium) {
            this.name = name;
            this.premium = premium;
        }

        String getName() { return name; }

        /**
         * MOVED HERE: The charge logic now lives with the type data.
         * 'daysOverdrawn' is passed as a parameter since it belongs to Account.
         */
        double overdraftCharge(int daysOverdrawn) {
            if (premium) {
                double baseCharge = 10;
                if (daysOverdrawn > 7) {
                    return baseCharge + (daysOverdrawn - 7) * 0.85;
                }
                return baseCharge;
            } else {
                return daysOverdrawn * 1.75;
            }
        }
    }

    static class AccountAfter {
        private double balance;
        private int daysOverdrawn;
        private BankAccountTypeAfter type;

        AccountAfter(double balance, int daysOverdrawn,
                     BankAccountTypeAfter type) {
            this.balance = balance;
            this.daysOverdrawn = daysOverdrawn;
            this.type = type;
        }

        /** Now simply delegates to the type object. */
        double overdraftCharge() {
            return type.overdraftCharge(daysOverdrawn);
        }
    }

    /**
     * Demonstrates Move Method refactoring.
     */
    public static void demo() {
        System.out.println("  [Move Method - Before and After]");
        System.out.println();

        // Before
        System.out.println("    BEFORE (charge logic on Account):");
        BankAccountTypeBefore premBefore = new BankAccountTypeBefore("Premium", true);
        AccountBefore acctBefore = new AccountBefore(-200, 10, premBefore);
        System.out.println("      Premium account, 10 days overdrawn -> $"
                + String.format("%.2f", acctBefore.overdraftCharge()));

        BankAccountTypeBefore stdBefore = new BankAccountTypeBefore("Standard", false);
        AccountBefore acctBefore2 = new AccountBefore(-100, 5, stdBefore);
        System.out.println("      Standard account, 5 days overdrawn -> $"
                + String.format("%.2f", acctBefore2.overdraftCharge()));

        System.out.println();

        // After
        System.out.println("    AFTER (charge logic moved to BankAccountType):");
        BankAccountTypeAfter premAfter = new BankAccountTypeAfter("Premium", true);
        AccountAfter acctAfter = new AccountAfter(-200, 10, premAfter);
        System.out.println("      Premium account, 10 days overdrawn -> $"
                + String.format("%.2f", acctAfter.overdraftCharge()));

        BankAccountTypeAfter stdAfter = new BankAccountTypeAfter("Standard", false);
        AccountAfter acctAfter2 = new AccountAfter(-100, 5, stdAfter);
        System.out.println("      Standard account, 5 days overdrawn -> $"
                + String.format("%.2f", acctAfter2.overdraftCharge()));

        System.out.println();
        System.out.println("    The charge logic now lives where the type data lives.");
    }
}
