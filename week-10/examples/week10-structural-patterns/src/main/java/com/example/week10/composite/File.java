package com.example.week10.composite;

/**
 * Leaf - File
 *
 * Represents a leaf node in the composite tree. A File has no children.
 * It provides concrete implementations of the Component operations.
 *
 * In the Composite pattern:
 *   - This is the "Leaf" role
 *   - It represents end objects of a composition (no children)
 *   - It defines behavior for primitive objects in the composition
 */
public class File implements FileSystemComponent {

    private final String name;
    private final long size; // size in bytes

    /**
     * Creates a file with the given name and size.
     *
     * @param name the file name
     * @param size the file size in bytes
     */
    public File(String name, long size) {
        this.name = name;
        this.size = size;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public long getSize() {
        return size;
    }

    @Override
    public void display(String indent) {
        System.out.printf("%s|- %s (%d bytes)%n", indent, name, size);
    }
}
