package com.example.week13.generalization;

import java.util.ArrayList;
import java.util.List;

/**
 * =============================================================================
 * REFACTORING TECHNIQUE: Replace Inheritance with Delegation
 * =============================================================================
 *
 * Intent:
 *   When a subclass uses only part of its superclass interface, or the
 *   inheritance does not model an "is-a" relationship, replace inheritance
 *   with a field (delegation) and forward only the needed methods.
 *
 * When to use:
 *   - A subclass uses only a few methods of its superclass.
 *   - The inheritance violates the Liskov Substitution Principle.
 *   - The relationship is "has-a" rather than "is-a".
 *   - You want to hide the superclass's full interface.
 *
 * Mechanics:
 *   1. Create a field in the subclass for the former superclass.
 *   2. Change the subclass to delegate calls through the field.
 *   3. Remove the extends clause.
 *   4. Create forwarding methods for any needed superclass methods.
 *
 * Classic example: Stack should NOT extend ArrayList.
 * =============================================================================
 */
public class ReplaceInheritanceWithDelegationDemo {

    // =========================================================================
    // BEFORE: Stack extends ArrayList -- BAD "is-a" relationship
    // =========================================================================

    /**
     * BEFORE: Stack inherits ALL of ArrayList's methods, including
     * add(index, element), remove(index), get(index), etc.
     * Users can break the stack abstraction by calling these methods.
     */
    static class StackBefore<T> extends ArrayList<T> {
        public void push(T item) {
            add(item);  // adds to end
        }

        public T pop() {
            if (isEmpty()) {
                throw new RuntimeException("Stack is empty!");
            }
            return remove(size() - 1);
        }

        public T peek() {
            if (isEmpty()) {
                throw new RuntimeException("Stack is empty!");
            }
            return get(size() - 1);
        }
    }

    // =========================================================================
    // AFTER: Stack uses delegation -- "has-a" ArrayList
    // =========================================================================

    /**
     * AFTER: Stack contains an ArrayList but does NOT expose ArrayList's
     * full interface.  Only stack operations (push, pop, peek) are public.
     */
    static class StackAfter<T> {
        private final List<T> elements = new ArrayList<>();  // delegation

        public void push(T item) {
            elements.add(item);
        }

        public T pop() {
            if (elements.isEmpty()) {
                throw new RuntimeException("Stack is empty!");
            }
            return elements.remove(elements.size() - 1);
        }

        public T peek() {
            if (elements.isEmpty()) {
                throw new RuntimeException("Stack is empty!");
            }
            return elements.get(elements.size() - 1);
        }

        public boolean isEmpty() {
            return elements.isEmpty();
        }

        public int size() {
            return elements.size();
        }
    }

    /**
     * Demonstrates Replace Inheritance with Delegation.
     */
    public static void demo() {
        System.out.println("  [Replace Inheritance with Delegation - Before and After]");
        System.out.println();

        // Before: stack IS an ArrayList -- dangerous
        System.out.println("    BEFORE (Stack extends ArrayList):");
        StackBefore<String> before = new StackBefore<>();
        before.push("A");
        before.push("B");
        before.push("C");
        System.out.println("      Pushed A, B, C. Peek: " + before.peek());

        // Danger: user can call ArrayList methods that break stack semantics!
        before.add(0, "INJECTED");  // inserts at index 0 -- not a stack op!
        System.out.println("      After add(0, 'INJECTED'): peek = "
                + before.peek());
        System.out.println("      Stack integrity broken! ArrayList methods exposed.");

        System.out.println();

        // After: stack HAS an ArrayList -- safe
        System.out.println("    AFTER (Stack uses delegation):");
        StackAfter<String> after = new StackAfter<>();
        after.push("A");
        after.push("B");
        after.push("C");
        System.out.println("      Pushed A, B, C. Peek: " + after.peek());
        System.out.println("      Pop: " + after.pop());
        System.out.println("      Peek after pop: " + after.peek());

        // The following line would NOT compile:
        // after.add(0, "INJECTED");  // ERROR: add() is not exposed!
        System.out.println("      add(0, ...) is NOT available -- stack integrity safe.");

        System.out.println();
        System.out.println("    Delegation hides the internal data structure and");
        System.out.println("    exposes only the operations that make sense.");
    }
}
