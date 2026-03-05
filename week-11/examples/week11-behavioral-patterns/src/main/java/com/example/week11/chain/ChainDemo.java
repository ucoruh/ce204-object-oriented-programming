package com.example.week11.chain;

/**
 * Chain of Responsibility Pattern - Demo
 *
 * Intent:
 *   Avoid coupling the sender of a request to its receiver by giving
 *   more than one object a chance to handle the request. Chain the
 *   receiving objects and pass the request along the chain until an
 *   object handles it.
 *
 * Structure:
 *   Client --> Handler (abstract)
 *                  |
 *          +-------+-------+
 *          |       |       |
 *        Auth    Log    Validation
 *
 * When to Use:
 *   - More than one object may handle a request, and the handler is not known a priori
 *   - You want to issue a request to one of several objects without specifying the receiver
 *   - The set of handlers should be specified dynamically
 *
 * Real-World Examples:
 *   - Servlet filters in Java web applications
 *   - Middleware pipelines in web frameworks
 *   - Event handling in UI frameworks (event bubbling)
 *   - Logging frameworks with multiple log levels
 */
public class ChainDemo {

    public static void demo() {
        System.out.println("==============================================================");
        System.out.println("  Pattern 1: CHAIN OF RESPONSIBILITY");
        System.out.println("  Avoid coupling sender to receiver; pass along a chain");
        System.out.println("==============================================================");

        // Build the chain: Log -> Auth -> Validation
        Handler logHandler = new LogHandler();
        Handler authHandler = new AuthHandler();
        Handler validationHandler = new ValidationHandler();

        logHandler.setNext(authHandler).setNext(validationHandler);

        // Test 1: A fully valid request (passes all handlers)
        System.out.println("\n  --- Test 1: Valid authenticated request ---");
        String request1 = "authenticated|valid-data|user=admin";
        String result1 = logHandler.handle(request1);
        System.out.println(result1);

        // Test 2: Unauthenticated request (stopped by AuthHandler)
        System.out.println("\n  --- Test 2: Unauthenticated request ---");
        String request2 = "valid-data|user=guest";
        String result2 = logHandler.handle(request2);
        System.out.println(result2);

        // Test 3: Authenticated but invalid data (stopped by ValidationHandler)
        System.out.println("\n  --- Test 3: Authenticated but invalid data ---");
        String request3 = "authenticated|bad-data|user=admin";
        String result3 = logHandler.handle(request3);
        System.out.println(result3);

        System.out.println();
    }
}
