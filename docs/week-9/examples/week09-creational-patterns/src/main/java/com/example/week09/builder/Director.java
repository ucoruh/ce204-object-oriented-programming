package com.example.week09.builder;

/**
 * Director - Controls the construction process
 *
 * The Director defines the ORDER in which to call building steps.
 * It works with any builder instance that follows the HouseBuilder interface.
 *
 * The Director is optional -- the client can control builders directly.
 * However, the Director is useful for encapsulating common construction
 * routines so they can be reused across the program.
 */
public class Director {

    /**
     * Constructs a minimal house (just the basic structure, no extras).
     */
    public House constructMinimalHouse(HouseBuilder builder) {
        System.out.println("    Director: Building a MINIMAL house...");
        return builder
                .buildFoundation()
                .buildWalls()
                .buildRoof()
                .buildWindows(4)
                .buildDoors(2)
                .build();
    }

    /**
     * Constructs a full-featured house with all options.
     */
    public House constructFullFeaturedHouse(HouseBuilder builder) {
        System.out.println("    Director: Building a FULL-FEATURED house...");
        return builder
                .buildFoundation()
                .buildWalls()
                .buildRoof()
                .buildWindows(12)
                .buildDoors(6)
                .buildGarage()
                .buildSwimmingPool()
                .buildGarden()
                .setInterior("Modern Minimalist")
                .build();
    }
}
