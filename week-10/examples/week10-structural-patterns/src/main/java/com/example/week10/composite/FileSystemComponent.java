package com.example.week10.composite;

/**
 * Component Interface - FileSystemComponent
 *
 * This interface declares operations common to both simple (File) and
 * complex (Directory) elements of the file system tree.
 *
 * In the Composite pattern:
 *   - This is the "Component" role
 *   - It defines the interface for ALL objects in the composition
 *   - Both Leaf (File) and Composite (Directory) implement this
 *   - Clients use this interface uniformly, without knowing whether
 *     they are dealing with a single file or an entire directory tree
 */
public interface FileSystemComponent {

    /**
     * Returns the name of this file system component.
     *
     * @return the component name
     */
    String getName();

    /**
     * Returns the total size in bytes.
     * For files, this is the file size.
     * For directories, this is the sum of all children's sizes.
     *
     * @return size in bytes
     */
    long getSize();

    /**
     * Displays this component with proper indentation to show hierarchy.
     *
     * @param indent the indentation prefix for display
     */
    void display(String indent);
}
