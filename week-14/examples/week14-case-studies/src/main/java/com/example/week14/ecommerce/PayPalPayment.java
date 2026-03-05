package com.example.week14.ecommerce;

/**
 * =============================================================================
 * E-Commerce Case Study: PayPalPayment (Concrete Strategy)
 * =============================================================================
 *
 * Implements the PaymentStrategy interface for PayPal payments.
 *
 * In a real system, this would redirect to PayPal's OAuth flow and
 * use their REST API.  Here we simulate the process for demonstration.
 * =============================================================================
 */
public class PayPalPayment implements PaymentStrategy {

    private final String email;

    /**
     * Creates a PayPal payment strategy.
     *
     * @param email the PayPal account email
     */
    public PayPalPayment(String email) {
        if (email == null || !email.contains("@")) {
            throw new IllegalArgumentException("Invalid PayPal email.");
        }
        this.email = email;
    }

    /**
     * Simulates processing a PayPal payment.
     *
     * @param amount the amount to charge
     * @return true (simulated success)
     */
    @Override
    public boolean pay(double amount) {
        System.out.println("    [PayPal] Redirecting to PayPal for $"
                + String.format("%.2f", amount) + "...");
        System.out.println("    [PayPal] Payment from " + email
                + " completed successfully.");
        return true;
    }

    @Override
    public String getMethodName() {
        return "PayPal";
    }
}
