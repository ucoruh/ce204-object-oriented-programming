package com.example.week10.flyweight;

/**
 * Context - Tree
 *
 * Contains the extrinsic (unique) state of a tree - its position.
 * It references a shared TreeType flyweight for the intrinsic state.
 *
 * In the Flyweight pattern:
 *   - This is the "Context" (sometimes called "unshared state holder")
 *   - It stores extrinsic state (x, y coordinates)
 *   - It holds a reference to a shared Flyweight (TreeType)
 *
 * MEMORY SAVINGS:
 *   If we have 1,000,000 trees with 5 different types:
 *     Without Flyweight: 1,000,000 objects each with name+color+texture+x+y
 *     With Flyweight:    1,000,000 objects with just x+y+reference
 *                        + 5 shared TreeType objects with name+color+texture
 */
public class Tree {

    // Extrinsic state - unique to each tree instance
    private final int x;
    private final int y;

    // Reference to shared flyweight (intrinsic state)
    private final TreeType type;

    /**
     * Creates a tree at the given position with the given type.
     *
     * @param x    the x-coordinate (extrinsic state)
     * @param y    the y-coordinate (extrinsic state)
     * @param type the shared tree type flyweight (intrinsic state)
     */
    public Tree(int x, int y, TreeType type) {
        this.x = x;
        this.y = y;
        this.type = type;
    }

    /**
     * Draws this tree by delegating to the shared TreeType flyweight.
     */
    public void draw() {
        type.draw(x, y);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public TreeType getType() {
        return type;
    }
}
