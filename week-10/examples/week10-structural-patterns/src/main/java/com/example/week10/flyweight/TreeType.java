package com.example.week10.flyweight;

/**
 * Flyweight - TreeType
 *
 * Contains the intrinsic (shared) state of a tree. Multiple Tree objects
 * that share the same name, color, and texture will reference the SAME
 * TreeType instance, saving memory.
 *
 * In the Flyweight pattern:
 *   - This is the "Flyweight" role
 *   - It stores intrinsic state (shared, immutable data)
 *   - It is shared among many contexts (Tree objects)
 *
 * KEY CONCEPT - Intrinsic vs Extrinsic state:
 *   - INTRINSIC (shared):  name, color, texture - same for all oak trees
 *   - EXTRINSIC (unique):  x, y position - different for each tree
 *
 *   By separating these, we avoid duplicating the intrinsic data
 *   across thousands of tree objects.
 */
public class TreeType {

    // Intrinsic state - shared across all trees of this type
    private final String name;
    private final String color;
    private final String texture;

    /**
     * Creates a new tree type with the given intrinsic properties.
     *
     * @param name    the species name (e.g., "Oak", "Pine")
     * @param color   the color description
     * @param texture the texture description
     */
    public TreeType(String name, String color, String texture) {
        this.name = name;
        this.color = color;
        this.texture = texture;
    }

    public String getName() {
        return name;
    }

    public String getColor() {
        return color;
    }

    public String getTexture() {
        return texture;
    }

    /**
     * Draws a tree of this type at the specified coordinates.
     * The coordinates (x, y) are EXTRINSIC state passed in from outside.
     *
     * @param x the x-coordinate (extrinsic state)
     * @param y the y-coordinate (extrinsic state)
     */
    public void draw(int x, int y) {
        System.out.printf("      Drawing [%s] tree (color=%s) at (%d, %d)%n",
                name, color, x, y);
    }

    @Override
    public String toString() {
        return "TreeType{name='" + name + "', color='" + color
                + "', texture='" + texture + "'}";
    }
}
