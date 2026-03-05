package com.example.week10.composite;

import java.util.ArrayList;
import java.util.List;

/**
 * Composite - Directory
 *
 * Represents a composite node that can contain other FileSystemComponents
 * (both Files and other Directories). This creates a tree structure.
 *
 * In the Composite pattern:
 *   - This is the "Composite" role
 *   - It stores child components (both Leaf and Composite)
 *   - It implements child-related operations (add, remove)
 *   - It delegates operations to its children
 *
 * KEY CONCEPT:
 *   getSize() recursively sums all children's sizes. The client does not
 *   need to know whether it is dealing with a File or Directory - the
 *   tree structure handles everything uniformly through recursion.
 */
public class Directory implements FileSystemComponent {

    private final String name;
    private final List<FileSystemComponent> children = new ArrayList<>();

    /**
     * Creates an empty directory with the given name.
     *
     * @param name the directory name
     */
    public Directory(String name) {
        this.name = name;
    }

    /**
     * Adds a child component (file or subdirectory) to this directory.
     *
     * @param component the component to add
     */
    public void add(FileSystemComponent component) {
        children.add(component);
    }

    /**
     * Removes a child component from this directory.
     *
     * @param component the component to remove
     */
    public void remove(FileSystemComponent component) {
        children.remove(component);
    }

    /**
     * Returns the children of this directory.
     *
     * @return list of child components
     */
    public List<FileSystemComponent> getChildren() {
        return children;
    }

    @Override
    public String getName() {
        return name;
    }

    /**
     * Recursively calculates the total size of all contents.
     * This is the power of the Composite pattern - the client
     * calls getSize() the same way on both File and Directory.
     */
    @Override
    public long getSize() {
        long totalSize = 0;
        for (FileSystemComponent child : children) {
            totalSize += child.getSize(); // Recursive call for directories
        }
        return totalSize;
    }

    @Override
    public void display(String indent) {
        System.out.printf("%s|+ %s/ (total: %d bytes)%n", indent, name, getSize());
        for (FileSystemComponent child : children) {
            child.display(indent + "   "); // Increase indent for children
        }
    }
}
