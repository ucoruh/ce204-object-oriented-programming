package com.example.week10.flyweight;

import java.util.ArrayList;
import java.util.List;

/**
 * =============================================================================
 * FLYWEIGHT PATTERN DEMO
 * =============================================================================
 *
 * Intent:
 *   Use sharing to support large numbers of fine-grained objects efficiently.
 *   Flyweight separates intrinsic state (shared) from extrinsic state (unique).
 *
 * Structure:
 *   [FlyweightFactory] ---creates/caches--> [Flyweight] (intrinsic state)
 *   [Context] ---references--> [Flyweight]
 *   [Context] stores extrinsic state (unique per object)
 *
 * Participants in this demo:
 *   - Flyweight:        TreeType (shared intrinsic state: name, color, texture)
 *   - Context:          Tree (extrinsic state: x, y position)
 *   - FlyweightFactory: TreeFactory (creates/caches flyweight objects)
 *
 * When to Use:
 *   - An application uses a large number of objects
 *   - Storage costs are high because of the sheer quantity of objects
 *   - Most object state can be made extrinsic
 *   - Many groups of objects can be replaced by relatively few shared objects
 *   - The application does not depend on object identity
 *
 * Real-World Analogy:
 *   In a forest simulation with 1,000,000 trees, there might be only
 *   5 species. Instead of storing species data in each tree, share it.
 * =============================================================================
 */
public class FlyweightDemo {

    public static void demo() {
        System.out.println("-------------------------------------------------------------");
        System.out.println("  PATTERN 6: FLYWEIGHT");
        System.out.println("  Sharing state to support large numbers of objects");
        System.out.println("-------------------------------------------------------------");

        // Clear factory cache for a clean demo
        TreeFactory.clearCache();

        // Simulate planting a forest with many trees but few types
        List<Tree> forest = new ArrayList<>();

        System.out.println("  Planting trees (notice when types are created vs reused):");
        System.out.println();

        // Plant several Oak trees - the TreeType is created once and reused
        forest.add(new Tree(10, 20, TreeFactory.getTreeType("Oak", "Green", "Rough bark")));
        forest.add(new Tree(50, 80, TreeFactory.getTreeType("Oak", "Green", "Rough bark")));
        forest.add(new Tree(90, 40, TreeFactory.getTreeType("Oak", "Green", "Rough bark")));

        // Plant Pine trees - new type created, then reused
        forest.add(new Tree(30, 60, TreeFactory.getTreeType("Pine", "Dark Green", "Scaly bark")));
        forest.add(new Tree(70, 10, TreeFactory.getTreeType("Pine", "Dark Green", "Scaly bark")));

        // Plant Birch trees - new type created, then reused
        forest.add(new Tree(15, 95, TreeFactory.getTreeType("Birch", "Light Green", "White bark")));
        forest.add(new Tree(45, 55, TreeFactory.getTreeType("Birch", "Light Green", "White bark")));
        forest.add(new Tree(85, 75, TreeFactory.getTreeType("Birch", "Light Green", "White bark")));

        // More Oaks - definitely reused from cache
        forest.add(new Tree(25, 35, TreeFactory.getTreeType("Oak", "Green", "Rough bark")));
        forest.add(new Tree(65, 45, TreeFactory.getTreeType("Oak", "Green", "Rough bark")));

        System.out.println();

        // Draw the forest
        System.out.println("  Drawing the forest (" + forest.size() + " trees):");
        for (Tree tree : forest) {
            tree.draw();
        }

        System.out.println();

        // Show memory savings
        System.out.println("  --- Memory Savings Analysis ---");
        System.out.println("    Total trees planted:    " + forest.size());
        System.out.println("    Unique TreeType objects: " + TreeFactory.getCachedTypeCount());
        System.out.println();
        System.out.println("    Without Flyweight: " + forest.size()
                + " objects, each with name+color+texture+x+y");
        System.out.println("    With Flyweight:    " + forest.size()
                + " objects with x+y+reference");
        System.out.println("                       + " + TreeFactory.getCachedTypeCount()
                + " shared TreeType objects");
        System.out.println();
        System.out.println("    In a real forest with 1,000,000 trees and 5 species,");
        System.out.println("    the savings would be enormous!");

        System.out.println();
        System.out.println("  Key Takeaway: Shared flyweight objects avoid duplicating");
        System.out.println("  intrinsic state across thousands/millions of objects.");
        System.out.println();
    }
}
