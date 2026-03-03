package com.example.week01.basics;

import java.util.ArrayList;
import java.util.List;

/**
 * ==========================================================================
 * DEMO 7: OCSF - Reusable Framework Pattern (Simplified)
 * ==========================================================================
 *
 * OCSF = Object Client-Server Framework
 *
 * OCSF is an excellent example of REUSABLE TECHNOLOGY in OOP.
 * It demonstrates how abstract classes can create a framework that
 * developers extend for their specific needs.
 *
 * Key OOP Concepts Demonstrated:
 *   1. Abstract Classes   - Define the skeleton with abstract methods
 *   2. Template Method     - Base class defines the algorithm structure
 *   3. Inheritance         - Concrete classes extend the abstract framework
 *   4. Encapsulation       - Framework hides complex internal logic
 *
 * How OCSF Works (simplified):
 *   - AbstractServer: provides connection handling, you implement handleMessage()
 *   - AbstractClient: provides server communication, you implement handleMessage()
 *   - You extend these classes and fill in the application-specific logic
 *
 * This demo provides a SIMPLIFIED version to illustrate the concept.
 * The actual OCSF library handles real TCP/IP networking.
 */
public class OCSFDemo {

    // ======================================================================
    // Abstract Framework Layer - the reusable part
    // ======================================================================

    /**
     * AbstractServer is the reusable framework class.
     * It provides the common server behavior (managing clients, sending messages).
     * Subclasses only need to implement the application-specific methods.
     *
     * In the real OCSF, this would handle TCP/IP socket connections.
     * Here we simulate it with simple method calls for clarity.
     */
    static abstract class AbstractServer {
        private int port;
        private boolean running;
        private List<ClientConnection> connectedClients;

        public AbstractServer(int port) {
            this.port = port;
            this.running = false;
            this.connectedClients = new ArrayList<>();
        }

        // --- Framework methods (provided by the framework) ---

        public void startServer() {
            running = true;
            System.out.println("    Server started on port " + port);
            serverStarted();  // Hook method - subclass can override
        }

        public void stopServer() {
            running = false;
            System.out.println("    Server stopped");
            serverStopped();  // Hook method
        }

        public boolean isRunning() {
            return running;
        }

        // Simulate a client connecting
        public void simulateClientConnect(ClientConnection client) {
            connectedClients.add(client);
            System.out.println("    Client connected: " + client.getName());
            clientConnected(client);  // Hook method
        }

        // Send a message to all connected clients
        public void sendToAllClients(Object message) {
            System.out.println("    Server broadcasting: " + message);
            for (ClientConnection client : connectedClients) {
                client.receiveFromServer(message);
            }
        }

        // Receive a message from a client
        public void receiveMessageFromClient(Object message, ClientConnection client) {
            System.out.println("    Server received from " + client.getName() + ": " + message);
            handleMessageFromClient(message, client);  // Abstract method!
        }

        // --- Abstract methods (MUST be implemented by subclass) ---
        // This is the key to reusability: the framework defines WHAT
        // needs to happen, the subclass defines HOW.

        protected abstract void handleMessageFromClient(Object message, ClientConnection client);

        // --- Hook methods (CAN be overridden by subclass) ---
        // These have default (empty) implementations.

        protected void serverStarted() { }
        protected void serverStopped() { }
        protected void clientConnected(ClientConnection client) { }
    }

    /**
     * AbstractClient is the reusable client framework class.
     * Subclasses implement handleMessageFromServer() for their specific needs.
     */
    static abstract class AbstractClient {
        private String host;
        private int port;
        private String name;
        private AbstractServer connectedServer;

        public AbstractClient(String host, int port, String name) {
            this.host = host;
            this.port = port;
            this.name = name;
        }

        public String getName() {
            return name;
        }

        // Connect to a server (simplified - no real networking)
        public void connectToServer(AbstractServer server) {
            this.connectedServer = server;
            ClientConnection connection = new ClientConnection(this);
            server.simulateClientConnect(connection);
            System.out.println("    " + name + " connected to server at " + host + ":" + port);
            connectionEstablished();  // Hook method
        }

        // Send a message to the server
        public void sendToServer(Object message) {
            if (connectedServer != null) {
                System.out.println("    " + name + " sending: " + message);
                connectedServer.receiveMessageFromClient(message,
                        new ClientConnection(this));
            }
        }

        // Called when a message arrives from the server
        public void receiveFromServer(Object message) {
            System.out.println("    " + name + " received from server: " + message);
            handleMessageFromServer(message);
        }

        // --- Abstract method (MUST be implemented by subclass) ---
        protected abstract void handleMessageFromServer(Object message);

        // --- Hook method ---
        protected void connectionEstablished() { }
    }

    /**
     * Represents a client connection on the server side.
     */
    static class ClientConnection {
        private AbstractClient client;

        ClientConnection(AbstractClient client) {
            this.client = client;
        }

        String getName() {
            return client.getName();
        }

        void receiveFromServer(Object message) {
            client.receiveFromServer(message);
        }
    }

    // ======================================================================
    // Concrete Application Layer - the application-specific part
    // ======================================================================

    /**
     * ChatServer extends AbstractServer for a specific application: a chat room.
     * It only needs to implement the abstract method(s) - the framework
     * handles everything else!
     */
    static class ChatServer extends AbstractServer {

        public ChatServer(int port) {
            super(port);
        }

        // THIS is the only method we MUST implement.
        // The framework calls this when a message arrives.
        @Override
        protected void handleMessageFromClient(Object message, ClientConnection client) {
            // Application-specific logic: broadcast chat messages to all
            String chatMessage = "[" + client.getName() + "]: " + message;
            sendToAllClients(chatMessage);
        }

        // Override hook methods for chat-specific behavior
        @Override
        protected void serverStarted() {
            System.out.println("    >> Chat room is open!");
        }

        @Override
        protected void clientConnected(ClientConnection client) {
            sendToAllClients(">> " + client.getName() + " has joined the chat");
        }
    }

    /**
     * ChatClient extends AbstractClient for the chat application.
     */
    static class ChatClient extends AbstractClient {
        private List<String> messageHistory;

        public ChatClient(String host, int port, String name) {
            super(host, port, name);
            this.messageHistory = new ArrayList<>();
        }

        // Application-specific message handling
        @Override
        protected void handleMessageFromServer(Object message) {
            messageHistory.add(message.toString());
            // In a real app, this would update the GUI
        }

        @Override
        protected void connectionEstablished() {
            System.out.println("    >> " + getName() + ": Connected to chat!");
        }

        public List<String> getMessageHistory() {
            return new ArrayList<>(messageHistory);  // Defensive copy
        }
    }

    // ======================================================================
    // Demo method
    // ======================================================================

    /**
     * Demonstrates the OCSF-style reusable framework pattern.
     */
    public static void demo() {

        // ------------------------------------------------------------------
        // Part 1: Understanding the Framework Pattern
        // ------------------------------------------------------------------
        System.out.println("[Part 1: The OCSF Framework Pattern]");
        System.out.println();
        System.out.println("  OCSF provides ABSTRACT classes that handle the complex");
        System.out.println("  networking code. Developers only need to implement");
        System.out.println("  application-specific methods.");
        System.out.println();
        System.out.println("  Framework Layer (reusable):");
        System.out.println("    AbstractServer -> manages connections, message routing");
        System.out.println("    AbstractClient -> manages server communication");
        System.out.println();
        System.out.println("  Application Layer (your code):");
        System.out.println("    ChatServer extends AbstractServer");
        System.out.println("    ChatClient extends AbstractClient");
        System.out.println();

        // ------------------------------------------------------------------
        // Part 2: Running the Chat Demo
        // ------------------------------------------------------------------
        System.out.println("[Part 2: Chat Application Demo]");
        System.out.println();

        // Create the chat server (extends AbstractServer)
        System.out.println("  Step 1: Start the server");
        ChatServer server = new ChatServer(5555);
        server.startServer();
        System.out.println();

        // Create chat clients (extend AbstractClient)
        System.out.println("  Step 2: Clients connect");
        ChatClient alice = new ChatClient("localhost", 5555, "Alice");
        alice.connectToServer(server);
        System.out.println();

        ChatClient bob = new ChatClient("localhost", 5555, "Bob");
        bob.connectToServer(server);
        System.out.println();

        // Clients send messages
        System.out.println("  Step 3: Clients exchange messages");
        System.out.println();
        alice.sendToServer("Hello everyone!");
        System.out.println();
        bob.sendToServer("Hi Alice! Welcome to CEN206!");
        System.out.println();

        // ------------------------------------------------------------------
        // Part 3: Why This Pattern is Powerful
        // ------------------------------------------------------------------
        System.out.println("[Part 3: Why OCSF/Framework Pattern is Powerful]");
        System.out.println();
        System.out.println("  Reusability:");
        System.out.println("  - The SAME framework classes can be used for:");
        System.out.println("    * Chat applications");
        System.out.println("    * Online games");
        System.out.println("    * File sharing systems");
        System.out.println("    * Remote monitoring tools");
        System.out.println();
        System.out.println("  What the developer writes:");
        System.out.println("  - Only the handleMessageFromClient() method");
        System.out.println("  - Only the handleMessageFromServer() method");
        System.out.println("  - Optional: override hook methods for custom behavior");
        System.out.println();
        System.out.println("  What the framework provides:");
        System.out.println("  - Connection management");
        System.out.println("  - Message routing");
        System.out.println("  - Client tracking");
        System.out.println("  - Error handling");
        System.out.println();
        System.out.println("  OOP Principles Used:");
        System.out.println("  - Abstraction: abstract classes define the contract");
        System.out.println("  - Inheritance: concrete classes extend the framework");
        System.out.println("  - Encapsulation: complex networking is hidden");
        System.out.println("  - Polymorphism: framework calls overridden methods");
    }
}
