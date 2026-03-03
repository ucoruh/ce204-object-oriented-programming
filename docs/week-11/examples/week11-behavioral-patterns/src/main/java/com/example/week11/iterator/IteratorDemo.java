package com.example.week11.iterator;

/**
 * Iterator Pattern - Demo
 *
 * Intent:
 *   Provide a way to access the elements of an aggregate object
 *   sequentially without exposing its underlying representation.
 *
 * Structure:
 *   Client --> <<interface>> Iterator<T>
 *                    ^
 *                    |
 *              NameIterator (inner class)
 *                    |
 *              NameCollection (Aggregate)
 *                    - names: String[]
 *
 * When to Use:
 *   - You need to access an aggregate object's contents without exposing internals
 *   - You want to support multiple simultaneous traversals of the same aggregate
 *   - You want to provide a uniform interface for traversing different structures
 *
 * Real-World Examples:
 *   - Java's java.util.Iterator and Iterable interfaces
 *   - Database result set cursors (java.sql.ResultSet)
 *   - File system directory traversal
 *   - Stream processing pipelines
 */
public class IteratorDemo {

    public static void demo() {
        System.out.println("==============================================================");
        System.out.println("  Pattern 3: ITERATOR");
        System.out.println("  Access elements sequentially without exposing internals");
        System.out.println("==============================================================");

        NameCollection names = new NameCollection();
        Iterator<String> iterator = names.getIterator();

        System.out.println("\n  Iterating over NameCollection:");
        int position = 1;
        while (iterator.hasNext()) {
            String name = iterator.next();
            System.out.println("    " + position + ". " + name);
            position++;
        }

        // Demonstrate creating a fresh iterator (independent traversal)
        System.out.println("\n  Creating a second iterator (independent traversal):");
        Iterator<String> iterator2 = names.getIterator();
        System.out.print("    Names: ");
        while (iterator2.hasNext()) {
            String name = iterator2.next();
            System.out.print(name);
            if (iterator2.hasNext()) {
                System.out.print(", ");
            }
        }
        System.out.println();

        System.out.println();
    }
}
