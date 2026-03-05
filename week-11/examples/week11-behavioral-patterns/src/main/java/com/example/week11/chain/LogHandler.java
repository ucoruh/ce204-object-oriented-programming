package com.example.week11.chain;

/**
 * Chain of Responsibility Pattern - Concrete Handler: Logging
 *
 * This handler logs every request that passes through it and always
 * forwards the request to the next handler in the chain. It acts
 * as a pass-through handler that performs a side effect (logging).
 */
public class LogHandler extends Handler {

    /**
     * Handles the request by logging it, then always forwards
     * to the next handler in the chain.
     *
     * @param request the request string to process
     * @return the result of handling from downstream handlers
     */
    @Override
    public String handle(String request) {
        System.out.println("  [LogHandler] LOGGED - Request recorded: " + request);
        return super.handle(request);
    }
}
