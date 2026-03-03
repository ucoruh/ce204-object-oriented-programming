package com.example.week11.chain;

/**
 * Chain of Responsibility Pattern - Concrete Handler: Authentication
 *
 * This handler checks whether the request contains valid authentication
 * credentials. If the request is authenticated, it passes the request
 * to the next handler; otherwise, it rejects the request.
 */
public class AuthHandler extends Handler {

    /**
     * Handles the request by checking for authentication.
     * Requests containing "authenticated" are allowed through;
     * all others are rejected at this stage.
     *
     * @param request the request string to process
     * @return the result of handling
     */
    @Override
    public String handle(String request) {
        if (!request.contains("authenticated")) {
            return "  [AuthHandler] REJECTED - Request is not authenticated: " + request;
        }
        System.out.println("  [AuthHandler] PASSED - Request is authenticated.");
        return super.handle(request);
    }
}
