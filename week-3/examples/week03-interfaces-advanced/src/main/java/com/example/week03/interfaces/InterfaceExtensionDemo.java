package com.example.week03.interfaces;

/**
 * ============================================================================
 * DEMO 3: Extending Interfaces (Interface Inheritance)
 * ============================================================================
 *
 * Just like classes can extend other classes, INTERFACES can EXTEND other
 * interfaces using the "extends" keyword.
 *
 * Key points:
 *   1. An interface can extend ONE or MORE interfaces (multiple inheritance
 *      of type is allowed for interfaces).
 *   2. The sub-interface inherits all abstract methods from its parent(s).
 *   3. A class implementing the sub-interface must provide implementations
 *      for ALL methods: those in the sub-interface AND those inherited
 *      from parent interfaces.
 *   4. This creates a hierarchy of contracts, from general to specific.
 *
 * Real-world examples:
 *   - java.util.SortedSet extends java.util.Set
 *   - java.util.List extends java.util.Collection extends java.util.Iterable
 *
 * ============================================================================
 */
public class InterfaceExtensionDemo {

    // =======================================================================
    // PART 1: Simple interface extension (single inheritance)
    // =======================================================================

    /**
     * Base interface: anything that has a position.
     */
    interface Positionable {
        double getX();
        double getY();
        void moveTo(double x, double y);
    }

    /**
     * Extended interface: adds rotation capability ON TOP OF positioning.
     * A RotatableShape IS-A Positionable (inherits all its methods)
     * plus it can rotate.
     */
    interface RotatableShape extends Positionable {
        void rotate(double degrees);
        double getRotation();
    }

    /**
     * A class implementing RotatableShape must implement ALL methods
     * from both RotatableShape AND Positionable.
     */
    static class Arrow implements RotatableShape {
        private double x, y;
        private double rotation;

        public Arrow(double x, double y) {
            this.x = x;
            this.y = y;
            this.rotation = 0;
        }

        // From Positionable
        @Override
        public double getX() { return x; }

        @Override
        public double getY() { return y; }

        @Override
        public void moveTo(double x, double y) {
            this.x = x;
            this.y = y;
        }

        // From RotatableShape
        @Override
        public void rotate(double degrees) {
            this.rotation = (this.rotation + degrees) % 360;
        }

        @Override
        public double getRotation() { return rotation; }

        @Override
        public String toString() {
            return String.format("Arrow at (%.1f, %.1f) rotated %.1f deg", x, y, rotation);
        }
    }

    // =======================================================================
    // PART 2: Multiple interface inheritance
    // =======================================================================

    /**
     * Interface for things that can be serialized to a string.
     */
    interface Serializable {
        String serialize();
    }

    /**
     * Interface for things that can be cloned.
     */
    interface Copyable {
        Object copy();
    }

    /**
     * Interface for things that can be compared.
     */
    interface Rankable {
        int getRank();
    }

    /**
     * PersistableEntity extends THREE interfaces at once.
     * This is MULTIPLE INHERITANCE of type, which is perfectly legal
     * for interfaces in Java (but NOT for classes).
     */
    interface PersistableEntity extends Serializable, Copyable, Rankable {
        // Additional method specific to this sub-interface
        String getId();
    }

    /**
     * Student implements PersistableEntity, and therefore must implement
     * ALL methods from Serializable, Copyable, Rankable, AND PersistableEntity.
     */
    static class Student implements PersistableEntity {
        private String id;
        private String name;
        private int grade;

        public Student(String id, String name, int grade) {
            this.id = id;
            this.name = name;
            this.grade = grade;
        }

        // From PersistableEntity
        @Override
        public String getId() { return id; }

        // From Serializable
        @Override
        public String serialize() {
            return "Student{id=" + id + ",name=" + name + ",grade=" + grade + "}";
        }

        // From Copyable
        @Override
        public Object copy() {
            return new Student(id, name, grade);
        }

        // From Rankable
        @Override
        public int getRank() { return grade; }

        @Override
        public String toString() {
            return name + " (ID: " + id + ", Grade: " + grade + ")";
        }
    }

    // =======================================================================
    // PART 3: Diamond pattern in interface inheritance
    // =======================================================================

    /**
     * Base interface at the top of the diamond.
     */
    interface Identifiable {
        String getName();
    }

    /**
     * Left branch of the diamond.
     */
    interface Printable extends Identifiable {
        void print();
    }

    /**
     * Right branch of the diamond.
     */
    interface Loggable extends Identifiable {
        void log();
    }

    /**
     * Bottom of the diamond - extends BOTH Printable and Loggable.
     * Both parent interfaces extend Identifiable, creating a diamond shape.
     * In Java, this is fine because interfaces only define contracts, not state.
     *
     *        Identifiable
     *         /        \
     *    Printable    Loggable
     *         \        /
     *      ReportItem
     */
    interface ReportItem extends Printable, Loggable {
        String getContent();
    }

    /**
     * Implementation of the diamond pattern.
     * getName() from Identifiable only needs to be implemented once,
     * even though it's inherited through two paths.
     */
    static class Report implements ReportItem {
        private String name;
        private String content;

        public Report(String name, String content) {
            this.name = name;
            this.content = content;
        }

        // From Identifiable (inherited through both Printable and Loggable)
        @Override
        public String getName() { return name; }

        // From Printable
        @Override
        public void print() {
            System.out.println("    [PRINT] " + name + ": " + content);
        }

        // From Loggable
        @Override
        public void log() {
            System.out.println("    [LOG] " + name + " accessed at "
                    + java.time.LocalTime.now());
        }

        // From ReportItem
        @Override
        public String getContent() { return content; }
    }

    // =======================================================================
    // PART 4: Interface hierarchy chain
    // =======================================================================

    /**
     * Demonstrating a chain of interface extensions, similar to
     * java.util.Collection hierarchy.
     *
     *   Iterable -> Collection -> List
     *                          -> Set -> SortedSet
     */
    interface BasicCollection {
        int size();
        boolean isEmpty();
    }

    interface OrderedCollection extends BasicCollection {
        Object get(int index);
        void add(Object item);
    }

    interface SearchableCollection extends OrderedCollection {
        int indexOf(Object item);
        boolean contains(Object item);
    }

    /**
     * Implementation must provide ALL methods in the chain.
     */
    static class SimpleList implements SearchableCollection {
        private Object[] items = new Object[100];
        private int count = 0;

        // From BasicCollection
        @Override
        public int size() { return count; }

        @Override
        public boolean isEmpty() { return count == 0; }

        // From OrderedCollection
        @Override
        public Object get(int index) {
            if (index >= 0 && index < count) return items[index];
            throw new IndexOutOfBoundsException("Index: " + index);
        }

        @Override
        public void add(Object item) {
            if (count < items.length) items[count++] = item;
        }

        // From SearchableCollection
        @Override
        public int indexOf(Object item) {
            for (int i = 0; i < count; i++) {
                if (items[i].equals(item)) return i;
            }
            return -1;
        }

        @Override
        public boolean contains(Object item) {
            return indexOf(item) >= 0;
        }
    }

    // =======================================================================
    // DEMO METHOD
    // =======================================================================

    public static void demo() {
        // --- Section A: Simple extension ---
        System.out.println("  [A] Simple Interface Extension (Single)");
        System.out.println("  ----------------------------------------");

        Arrow arrow = new Arrow(0, 0);
        System.out.println("    Initial: " + arrow);
        arrow.moveTo(10, 20);
        arrow.rotate(45);
        System.out.println("    After move and rotate: " + arrow);
        arrow.rotate(90);
        System.out.println("    After another rotate: " + arrow);

        // The arrow can be referenced by either interface type
        Positionable pos = arrow;
        System.out.println("    As Positionable - x: " + pos.getX() + ", y: " + pos.getY());
        System.out.println();

        // --- Section B: Multiple interface inheritance ---
        System.out.println("  [B] Multiple Interface Inheritance");
        System.out.println("  ------------------------------------");

        Student student = new Student("S001", "Alice", 95);
        System.out.println("    Student: " + student);
        System.out.println("    Serialized: " + student.serialize());
        System.out.println("    Rank: " + student.getRank());

        // Copy and show it's a separate object
        Student copy = (Student) student.copy();
        System.out.println("    Copied: " + copy);
        System.out.println("    Same object? " + (student == copy));

        // Can be referenced by any of its interface types
        Serializable ser = student;
        Copyable cop = student;
        Rankable rnk = student;
        PersistableEntity pe = student;
        System.out.println("    As Serializable: " + ser.serialize());
        System.out.println("    As Rankable: rank = " + rnk.getRank());
        System.out.println("    As PersistableEntity: id = " + pe.getId());
        System.out.println();

        // --- Section C: Diamond pattern ---
        System.out.println("  [C] Diamond Inheritance Pattern");
        System.out.println("  --------------------------------");
        System.out.println("         Identifiable       ");
        System.out.println("          /        \\        ");
        System.out.println("     Printable    Loggable   ");
        System.out.println("          \\        /        ");
        System.out.println("       ReportItem            ");
        System.out.println();

        Report report = new Report("Q1 Sales", "Revenue increased by 15%");
        System.out.println("    Report name: " + report.getName());
        report.print();
        report.log();
        System.out.println("    Content: " + report.getContent());
        System.out.println();

        // --- Section D: Interface hierarchy chain ---
        System.out.println("  [D] Interface Hierarchy Chain");
        System.out.println("  ------------------------------");
        System.out.println("    BasicCollection -> OrderedCollection -> SearchableCollection");
        System.out.println();

        SimpleList list = new SimpleList();
        System.out.println("    Is empty? " + list.isEmpty());
        list.add("Java");
        list.add("Python");
        list.add("C++");
        System.out.println("    Size: " + list.size());
        System.out.println("    Get(1): " + list.get(1));
        System.out.println("    Contains 'Java'? " + list.contains("Java"));
        System.out.println("    Contains 'Ruby'? " + list.contains("Ruby"));
        System.out.println("    IndexOf 'C++': " + list.indexOf("C++"));
    }
}
