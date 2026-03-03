package com.example.week04.sequence;

/**
 * CEN206 - Week 4: Sequence Diagram Simulation
 *
 * This class simulates the message flow depicted in a UML sequence
 * diagram.  Each print statement corresponds to a message arrow on
 * the diagram.
 *
 * <pre>
 *   ┌────────┐   ┌──────────────┐   ┌────────────────┐   ┌─────────────────────┐
 *   │ Client │   │ OrderService │   │ PaymentService │   │ NotificationService │
 *   └───┬────┘   └──────┬───────┘   └───────┬────────┘   └──────────┬──────────┘
 *       │  placeOrder()  │                   │                       │
 *       │───────────────>│                   │                       │
 *       │                │  validateOrder()  │                       │
 *       │                │──────┐            │                       │
 *       │                │<─────┘            │                       │
 *       │                │  processPayment() │                       │
 *       │                │──────────────────>│                       │
 *       │                │  paymentResult    │                       │
 *       │                │<──────────────────│                       │
 *       │                │                   │  sendConfirmation()   │
 *       │                │───────────────────────────────────────────>│
 *       │                │                   │  notificationSent     │
 *       │                │<──────────────────────────────────────────│
 *       │  orderResult   │                   │                       │
 *       │<───────────────│                   │                       │
 * </pre>
 */
public class OrderProcessDemo {

    // ----------------------------------------------------------------
    // Service classes (participants in the sequence diagram)
    // ----------------------------------------------------------------

    /** Simulates a notification service (e-mail, SMS, etc.). */
    public static class NotificationService {

        public boolean sendConfirmation(String orderId, String email) {
            System.out.println("  [NotificationService] <-- sendConfirmation("
                    + orderId + ", " + email + ")");
            System.out.println("  [NotificationService]     Sending confirmation email...");
            System.out.println("  [NotificationService] --> returns true (email sent)");
            return true;
        }
    }

    /** Simulates a payment gateway. */
    public static class PaymentService {

        public boolean processPayment(String orderId, double amount) {
            System.out.println("  [PaymentService] <-- processPayment("
                    + orderId + ", $" + String.format("%.2f", amount) + ")");
            System.out.println("  [PaymentService]     Charging credit card...");

            boolean success = amount <= 10_000.0; // simple business rule
            System.out.println("  [PaymentService] --> returns "
                    + (success ? "true (approved)" : "false (declined)"));
            return success;
        }
    }

    /** Central order processing service. */
    public static class OrderService {
        private final PaymentService paymentService;
        private final NotificationService notificationService;

        public OrderService(PaymentService paymentService,
                            NotificationService notificationService) {
            this.paymentService = paymentService;
            this.notificationService = notificationService;
        }

        /**
         * Process an order -- this method's internal calls mirror the
         * arrows on the sequence diagram.
         */
        public String placeOrder(String orderId, String customerEmail,
                                 double amount) {

            System.out.println("  [OrderService] <-- placeOrder("
                    + orderId + ", " + customerEmail + ", $"
                    + String.format("%.2f", amount) + ")");

            // Self-call: validate order (loop-back arrow in sequence diagram)
            System.out.println("  [OrderService]     validateOrder() [self-call]");
            boolean valid = validateOrder(orderId, amount);
            if (!valid) {
                System.out.println("  [OrderService] --> returns \"INVALID_ORDER\"");
                return "INVALID_ORDER";
            }

            // Call PaymentService
            boolean paid = paymentService.processPayment(orderId, amount);
            if (!paid) {
                System.out.println("  [OrderService] --> returns \"PAYMENT_FAILED\"");
                return "PAYMENT_FAILED";
            }

            // Call NotificationService
            notificationService.sendConfirmation(orderId, customerEmail);

            System.out.println("  [OrderService] --> returns \"ORDER_CONFIRMED\"");
            return "ORDER_CONFIRMED";
        }

        /** Internal validation (self-message in sequence diagram). */
        private boolean validateOrder(String orderId, double amount) {
            System.out.println("  [OrderService]     Validating order "
                    + orderId + "...");
            return orderId != null && !orderId.isBlank() && amount > 0;
        }
    }

    // ----------------------------------------------------------------
    // Client (the actor on the left of the sequence diagram)
    // ----------------------------------------------------------------

    /** Simulates the actor that initiates the sequence. */
    public static class Client {
        private final OrderService orderService;

        public Client(OrderService orderService) {
            this.orderService = orderService;
        }

        public void checkout(String orderId, String email, double amount) {
            System.out.println("  [Client] --> placeOrder()");
            String result = orderService.placeOrder(orderId, email, amount);
            System.out.println("  [Client] <-- received result: " + result);
        }
    }

    // ----------------------------------------------------------------
    // Demo
    // ----------------------------------------------------------------

    /** Runs the sequence diagram demonstration. */
    public static void demo() {
        // Wire up participants
        NotificationService notification = new NotificationService();
        PaymentService payment = new PaymentService();
        OrderService orderService = new OrderService(payment, notification);
        Client client = new Client(orderService);

        System.out.println("  --- Scenario 1: Successful Order ---");
        client.checkout("ORD-1001", "student@university.edu", 149.99);

        System.out.println("\n  --- Scenario 2: Payment Declined ---");
        client.checkout("ORD-1002", "student@university.edu", 50_000.00);

        System.out.println("\n  --- Scenario 3: Invalid Order ---");
        client.checkout("", "student@university.edu", 29.99);
    }
}
