package com.example.week11.iterator;

/**
 * Iterator Pattern - Concrete Aggregate with Inner Iterator
 *
 * NameCollection stores a list of names and provides an iterator
 * to traverse them without exposing the internal array. The
 * iterator is implemented as a private inner class, encapsulating
 * the traversal logic within the collection itself.
 *
 * Structure:
 *   NameCollection (Aggregate)
 *       + getIterator(): Iterator<String>
 *       - NameIterator (inner class implements Iterator<String>)
 */
public class NameCollection {

    /** Internal storage of names - hidden from clients */
    private final String[] names;

    /**
     * Creates a NameCollection with a predefined set of names
     * for demonstration purposes.
     */
    public NameCollection() {
        names = new String[]{
            "Alice", "Bob", "Charlie", "Diana", "Edward"
        };
    }

    /**
     * Returns an iterator over the names in this collection.
     * The client uses the iterator without knowing the internal
     * storage mechanism (array, list, tree, etc.).
     *
     * @return an Iterator over the name strings
     */
    public Iterator<String> getIterator() {
        return new NameIterator();
    }

    /**
     * Inner class that implements the Iterator interface.
     * It maintains its own traversal state (index) and accesses
     * the enclosing class's private array directly.
     */
    private class NameIterator implements Iterator<String> {

        /** Current position in the iteration */
        private int index = 0;

        @Override
        public boolean hasNext() {
            return index < names.length;
        }

        @Override
        public String next() {
            if (hasNext()) {
                return names[index++];
            }
            return null;
        }
    }
}
