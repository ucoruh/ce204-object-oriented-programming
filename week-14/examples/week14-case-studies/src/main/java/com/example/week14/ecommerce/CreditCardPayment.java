package com.example.week14.ecommerce;

/**
 * =============================================================================
 * E-Commerce Case Study: CreditCardPayment (Concrete Strategy)
 * =============================================================================
 *
 * Implements the PaymentStrategy interface for credit card payments.
 *
 * In a real system, this would integrate with a payment gateway API
 * (Stripe, Square, etc.).  Here we simulate the process for demonstration.
 * =============================================================================
 */
public class CreditCardPayment implements PaymentStrategy {

    private final String cardNumber;
    private final String cardHolderName;

    /**
     * Creates a credit card payment strategy.
     *
     * @param cardNumber     the credit card number (last 4 digits shown)
     * @param cardHolderName the name on the card
     */
    public CreditCardPayment(String cardNumber, String cardHolderName) {
        if (cardNumber == null || cardNumber.length() < 4) {
            throw new IllegalArgumentException("Invalid card number.");
        }
        this.cardNumber = cardNumber;
        this.cardHolderName = cardHolderName;
    }

    /**
     * Simulates processing a credit card payment.
     *
     * @param amount the amount to charge
     * @return true (simulated success)
     */
    @Override
    public boolean pay(double amount) {
        String maskedCard = "****-****-****-"
                + cardNumber.substring(cardNumber.length() - 4);
        System.out.println("    [CreditCard] Charging $"
                + String.format("%.2f", amount)
                + " to card " + maskedCard
                + " (holder: " + cardHolderName + ")");
        System.out.println("    [CreditCard] Payment authorized successfully.");
        return true;
    }

    @Override
    public String getMethodName() {
        return "Credit Card";
    }
}
