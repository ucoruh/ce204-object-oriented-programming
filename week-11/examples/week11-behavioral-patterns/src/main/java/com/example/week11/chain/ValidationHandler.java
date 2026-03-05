package com.example.week11.chain;

/**
 * Chain of Responsibility Pattern - Concrete Handler: Validation
 *
 * This handler validates that the request data meets the required criteria.
 * Requests containing "valid-data" pass validation; others are rejected.
 * If validation passes, the request is forwarded to the next handler.
 */
public class ValidationHandler extends Handler {

    /**
     * Handles the request by validating its data content.
     * Requests containing "valid-data" are accepted; others are rejected.
     *
     * @param request the request string to process
     * @return the result of handling
     */
    @Override
    public String handle(String request) {
        if (!request.contains("valid-data")) {
            return "  [ValidationHandler] REJECTED - Invalid data in request: " + request;
        }
        System.out.println("  [ValidationHandler] PASSED - Data is valid.");
        return "  [ValidationHandler] SUCCESS - Request fully processed: " + request;
    }
}
