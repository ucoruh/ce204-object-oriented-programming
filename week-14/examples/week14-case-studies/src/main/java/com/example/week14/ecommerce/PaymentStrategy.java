package com.example.week14.ecommerce;

/**
 * =============================================================================
 * E-Commerce Case Study: PaymentStrategy (Strategy Pattern)
 * =============================================================================
 *
 * Strategy Pattern:
 *   Defines a family of algorithms (payment methods), encapsulates each one,
 *   and makes them interchangeable.  The client (Order) can use any payment
 *   strategy without knowing the implementation details.
 *
 * Participants:
 *   - PaymentStrategy (this interface) -- the Strategy
 *   - CreditCardPayment, PayPalPayment  -- Concrete Strategies
 *   - Order                              -- the Context
 *
 * Benefits:
 *   - Adding a new payment method (e.g., Bitcoin) requires only a new class.
 *   - Payment processing logic is encapsulated and testable.
 *   - The Order class is decoupled from specific payment implementations.
 * =============================================================================
 */
public interface PaymentStrategy {

    /**
     * Processes a payment for the given amount.
     *
     * @param amount the amount to charge
     * @return true if the payment was successful, false otherwise
     */
    boolean pay(double amount);

    /**
     * Returns a human-readable name for this payment method.
     */
    String getMethodName();
}
