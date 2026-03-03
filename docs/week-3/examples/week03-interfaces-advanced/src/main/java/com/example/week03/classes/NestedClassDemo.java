package com.example.week03.classes;

/**
 * ============================================================================
 * DEMO 6: Nested Classes (Inner, Static Nested, Local)
 * ============================================================================
 *
 * Java supports FOUR types of nested classes:
 *
 *   1. INNER CLASS (Non-static nested class)
 *      - Defined inside a class WITHOUT the "static" keyword.
 *      - Has access to ALL members (including private) of the outer class.
 *      - Requires an instance of the outer class to be created.
 *      - Syntax: OuterClass.InnerClass inner = outer.new InnerClass();
 *
 *   2. STATIC NESTED CLASS
 *      - Defined inside a class WITH the "static" keyword.
 *      - Does NOT have access to instance members of the outer class.
 *      - Can access static members of the outer class.
 *      - Does NOT require an instance of the outer class.
 *      - Syntax: OuterClass.StaticNested sn = new OuterClass.StaticNested();
 *
 *   3. LOCAL CLASS
 *      - Defined INSIDE a method (or any block scope).
 *      - Can access local variables of the method ONLY if they are
 *        effectively final (not modified after initialization).
 *      - Exists only within the scope of the method.
 *
 *   4. ANONYMOUS CLASS (covered in AnonymousClassDemo.java)
 *
 * ============================================================================
 */
public class NestedClassDemo {

    // =======================================================================
    // PART 1: Inner Class (Non-static Nested Class)
    // =======================================================================

    /**
     * LinkedList demonstrates inner classes.
     * The Node class is an inner class because it needs access to
     * the outer class's context.
     */
    static class LinkedList {
        private Node head;
        private int size;

        // INNER CLASS: Node is a non-static nested class.
        // It can access private members of LinkedList.
        class Node {
            String data;
            Node next;

            Node(String data) {
                this.data = data;
                this.next = null;
                // Inner class can access outer class's private members
                // (we'll demonstrate this in add() below)
            }

            // Inner class method that references the outer class
            String getListInfo() {
                // Accessing the outer class instance using OuterClass.this
                return "Node[" + data + "] in list of size " + LinkedList.this.size;
            }
        }

        public void add(String data) {
            Node newNode = new Node(data);
            newNode.next = head;
            head = newNode;
            size++;
        }

        public void printAll() {
            Node current = head;
            System.out.print("    List: ");
            while (current != null) {
                System.out.print(current.data);
                if (current.next != null) System.out.print(" -> ");
                current = current.next;
            }
            System.out.println();
        }

        // Method to demonstrate inner class accessing outer members
        public void showNodeInfo() {
            if (head != null) {
                System.out.println("    " + head.getListInfo());
            }
        }

        public int getSize() { return size; }
    }

    // =======================================================================
    // PART 2: Static Nested Class
    // =======================================================================

    /**
     * MathUtils contains a static nested class.
     * The nested class does NOT need an instance of MathUtils.
     */
    static class MathUtils {
        // Static field accessible by the static nested class
        private static final double PI = 3.14159265358979;

        // Instance field - NOT accessible by static nested class
        private int instanceCounter = 0;

        /**
         * STATIC NESTED CLASS: Point
         * - Does NOT have access to MathUtils instance members.
         * - CAN access MathUtils static members.
         * - Created WITHOUT an instance of MathUtils.
         */
        static class Point {
            double x, y;

            Point(double x, double y) {
                this.x = x;
                this.y = y;
            }

            double distanceTo(Point other) {
                double dx = this.x - other.x;
                double dy = this.y - other.y;
                return Math.sqrt(dx * dx + dy * dy);
            }

            // Can access the outer class's STATIC members
            double angleInRadians() {
                return Math.atan2(y, x);
            }

            double angleInDegrees() {
                return angleInRadians() * 180.0 / PI; // Accessing static PI
            }

            @Override
            public String toString() {
                return String.format("(%.1f, %.1f)", x, y);
            }
        }

        /**
         * Another static nested class that uses Point.
         */
        static class Circle {
            Point center;
            double radius;

            Circle(Point center, double radius) {
                this.center = center;
                this.radius = radius;
            }

            double area() {
                return PI * radius * radius; // Accessing outer static PI
            }

            double circumference() {
                return 2 * PI * radius;
            }

            @Override
            public String toString() {
                return String.format("Circle[center=%s, radius=%.1f]", center, radius);
            }
        }
    }

    // =======================================================================
    // PART 3: Local Class (defined inside a method)
    // =======================================================================

    /**
     * This method demonstrates LOCAL CLASSES.
     * A local class is defined inside a method body.
     */
    static void localClassDemo() {
        // A local variable that the local class will access.
        // It must be effectively final (not reassigned).
        final String prefix = "ITEM";
        int startId = 100;  // effectively final - never reassigned

        // LOCAL CLASS defined inside this method
        // It exists only within this method's scope.
        class InventoryItem {
            private String name;
            private int id;

            InventoryItem(String name, int id) {
                this.name = name;
                this.id = id;
            }

            // Can access effectively final local variables
            String getCode() {
                return prefix + "-" + id; // 'prefix' is effectively final
            }

            @Override
            public String toString() {
                return getCode() + ": " + name;
            }
        }

        // Use the local class within this method
        InventoryItem item1 = new InventoryItem("Keyboard", startId);
        InventoryItem item2 = new InventoryItem("Mouse", startId + 1);
        InventoryItem item3 = new InventoryItem("Monitor", startId + 2);

        System.out.println("    " + item1);
        System.out.println("    " + item2);
        System.out.println("    " + item3);
    }

    // =======================================================================
    // PART 4: Practical example combining inner and static nested
    // =======================================================================

    /**
     * A simple event system demonstrating both inner and static nested classes.
     */
    static class EventSystem {
        // Static nested class - does not need an EventSystem instance
        static class Event {
            private String type;
            private String data;
            private long timestamp;

            Event(String type, String data) {
                this.type = type;
                this.data = data;
                this.timestamp = System.currentTimeMillis();
            }

            @Override
            public String toString() {
                return "Event[" + type + ": " + data + "]";
            }
        }

        // Inner class - has access to EventSystem's state
        class EventHandler {
            private String name;

            EventHandler(String name) {
                this.name = name;
            }

            void handle(Event event) {
                // Can access outer class's private members
                System.out.println("    Handler '" + name + "' processing " + event);
                EventSystem.this.eventCount++; // Access outer instance field
            }
        }

        // Instance field
        private int eventCount = 0;
        private java.util.List<EventHandler> handlers = new java.util.ArrayList<>();

        public EventHandler createHandler(String name) {
            EventHandler handler = new EventHandler(name);
            handlers.add(handler);
            return handler;
        }

        public void dispatch(Event event) {
            for (EventHandler handler : handlers) {
                handler.handle(event);
            }
        }

        public int getEventCount() { return eventCount; }
    }

    // =======================================================================
    // DEMO METHOD
    // =======================================================================

    public static void demo() {
        // --- Section A: Inner class ---
        System.out.println("  [A] Inner Class (Non-static Nested Class)");
        System.out.println("  -------------------------------------------");

        LinkedList list = new LinkedList();
        list.add("Charlie");
        list.add("Bob");
        list.add("Alice");
        list.printAll();
        list.showNodeInfo();

        // Creating an inner class instance from outside requires an outer instance
        LinkedList.Node externalNode = list.new Node("External");
        System.out.println("    External node info: " + externalNode.getListInfo());
        System.out.println();

        // --- Section B: Static nested class ---
        System.out.println("  [B] Static Nested Class");
        System.out.println("  -------------------------");

        // Static nested class does NOT need an outer class instance
        MathUtils.Point p1 = new MathUtils.Point(3, 4);
        MathUtils.Point p2 = new MathUtils.Point(7, 1);
        System.out.println("    Point 1: " + p1);
        System.out.println("    Point 2: " + p2);
        System.out.println("    Distance: " + String.format("%.2f", p1.distanceTo(p2)));
        System.out.println("    P1 angle: " + String.format("%.2f", p1.angleInDegrees()) + " degrees");

        MathUtils.Circle circle = new MathUtils.Circle(p1, 5.0);
        System.out.println("    " + circle);
        System.out.println("    Area: " + String.format("%.2f", circle.area()));
        System.out.println("    Circumference: " + String.format("%.2f", circle.circumference()));
        System.out.println();

        // --- Section C: Local class ---
        System.out.println("  [C] Local Class (Defined Inside a Method)");
        System.out.println("  -------------------------------------------");

        localClassDemo();
        // Note: InventoryItem class is NOT accessible here - it only exists
        // inside the localClassDemo() method.
        System.out.println();

        // --- Section D: Practical combined example ---
        System.out.println("  [D] Practical Example: Event System");
        System.out.println("  -------------------------------------");

        EventSystem eventSystem = new EventSystem();
        eventSystem.createHandler("Logger");
        eventSystem.createHandler("Analytics");

        // Event is a static nested class - no EventSystem instance needed
        EventSystem.Event loginEvent = new EventSystem.Event("LOGIN", "user=alice");
        EventSystem.Event clickEvent = new EventSystem.Event("CLICK", "button=submit");

        eventSystem.dispatch(loginEvent);
        eventSystem.dispatch(clickEvent);
        System.out.println("    Total events processed: " + eventSystem.getEventCount());
        System.out.println();

        // --- Section E: Key differences summary ---
        System.out.println("  [E] Summary of Nested Class Types");
        System.out.println("  ------------------------------------");
        System.out.println("    INNER CLASS:");
        System.out.println("      - No 'static' keyword");
        System.out.println("      - Accesses outer instance members");
        System.out.println("      - Created via: outer.new InnerClass()");
        System.out.println();
        System.out.println("    STATIC NESTED CLASS:");
        System.out.println("      - Has 'static' keyword");
        System.out.println("      - Only accesses outer static members");
        System.out.println("      - Created via: new Outer.StaticNested()");
        System.out.println();
        System.out.println("    LOCAL CLASS:");
        System.out.println("      - Defined inside a method");
        System.out.println("      - Accesses effectively final local variables");
        System.out.println("      - Only visible within that method");
    }
}
