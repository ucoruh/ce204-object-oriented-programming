package com.example.week10.flyweight;

import java.util.HashMap;
import java.util.Map;

/**
 * Flyweight Factory - TreeFactory
 *
 * Creates and manages flyweight (TreeType) objects. Ensures that
 * flyweights are shared properly - if a TreeType with the same
 * properties already exists, it returns the existing one instead
 * of creating a duplicate.
 *
 * In the Flyweight pattern:
 *   - This is the "FlyweightFactory" role
 *   - It creates and manages flyweight objects
 *   - It ensures flyweights are shared (uses a cache/pool)
 *   - Clients should obtain flyweights only through this factory
 *
 * The factory uses a HashMap as a cache. The key is a composite
 * string of the tree type properties. If a matching TreeType exists,
 * it is reused; otherwise, a new one is created and cached.
 */
public class TreeFactory {

    // Cache of flyweight objects, keyed by their intrinsic state
    private static final Map<String, TreeType> treeTypes = new HashMap<>();

    /**
     * Returns a shared TreeType flyweight. Creates a new one only if
     * no matching type exists in the cache.
     *
     * @param name    the tree species name
     * @param color   the tree color
     * @param texture the tree texture
     * @return a shared TreeType instance
     */
    public static TreeType getTreeType(String name, String color, String texture) {
        // Create a cache key from the intrinsic state
        String key = name + "_" + color + "_" + texture;

        // Check if this type already exists in the cache
        if (!treeTypes.containsKey(key)) {
            // Create and cache a new flyweight
            TreeType newType = new TreeType(name, color, texture);
            treeTypes.put(key, newType);
            System.out.println("    [TreeFactory] Created NEW TreeType: " + newType);
        } else {
            System.out.println("    [TreeFactory] Reusing existing TreeType: "
                    + treeTypes.get(key).getName());
        }

        return treeTypes.get(key);
    }

    /**
     * Returns the number of unique TreeType flyweights currently cached.
     *
     * @return the number of cached tree types
     */
    public static int getCachedTypeCount() {
        return treeTypes.size();
    }

    /**
     * Clears the factory cache. Useful for resetting state between demos.
     */
    public static void clearCache() {
        treeTypes.clear();
    }
}
