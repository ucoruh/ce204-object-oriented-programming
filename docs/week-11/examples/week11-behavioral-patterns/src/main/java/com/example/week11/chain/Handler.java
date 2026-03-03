package com.example.week11.chain;

/**
 * Chain of Responsibility Pattern - Abstract Handler
 *
 * Defines the interface for handling requests and maintains a reference
 * to the next handler in the chain. Each handler decides either to
 * process the request or to pass it along the chain.
 *
 * Structure:
 *   Handler (abstract) --next--> Handler (abstract)
 *       ^                            ^
 *       |                            |
 *   ConcreteHandlerA           ConcreteHandlerB
 */
public abstract class Handler {

    /** Reference to the next handler in the chain */
    protected Handler nextHandler;

    /**
     * Sets the next handler in the chain.
     *
     * @param next the next handler to be called if this handler cannot process the request
     * @return the next handler, enabling fluent chaining: h1.setNext(h2).setNext(h3)
     */
    public Handler setNext(Handler next) {
        this.nextHandler = next;
        return next;
    }

    /**
     * Handles the request. Concrete handlers override this method to provide
     * specific handling logic. If the handler cannot process the request,
     * it delegates to the next handler in the chain.
     *
     * @param request the request string to be processed
     * @return a response string describing how the request was handled
     */
    public String handle(String request) {
        if (nextHandler != null) {
            return nextHandler.handle(request);
        }
        return "  [End of chain] No handler could process: " + request;
    }
}
