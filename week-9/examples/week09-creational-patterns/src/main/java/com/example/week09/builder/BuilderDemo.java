package com.example.week09.builder;

/**
 * ============================================================================
 * BUILDER PATTERN - Demo
 * ============================================================================
 *
 * Intent:
 *   Separate the construction of a complex object from its representation
 *   so that the same construction process can create different representations.
 *
 * Structure:
 *   - Product (House)                  : The complex object being built
 *   - Builder (HouseBuilder)           : Declares building steps interface
 *   - ConcreteBuilder (ConcreteHouseBuilder, LuxuryHouseBuilder) : Implements steps
 *   - Director                         : Controls the order of construction steps
 *
 * When to use:
 *   - When the algorithm for creating a complex object should be independent
 *     of the parts and how they are assembled
 *   - When the construction process must allow different representations
 *   - When you want to avoid "telescoping constructor" anti-pattern
 *   - When building composite or tree structures step by step
 *
 * Real-world examples:
 *   - java.lang.StringBuilder
 *   - java.util.stream.Stream.Builder
 *   - Lombok @Builder annotation
 * ============================================================================
 */
public class BuilderDemo {

    public static void demo() {
        System.out.println("=============================================================");
        System.out.println("  3. BUILDER PATTERN");
        System.out.println("=============================================================");
        System.out.println();
        System.out.println("  Scenario: A real-estate company builds different types of");
        System.out.println("  houses. The construction steps are the same, but the");
        System.out.println("  materials and options differ (standard vs. luxury).");
        System.out.println();

        Director director = new Director();

        // --- 1) Minimal standard house ---
        System.out.println("  >> Minimal Standard House (via Director):");
        ConcreteHouseBuilder standardBuilder = new ConcreteHouseBuilder();
        House minimalHouse = director.constructMinimalHouse(standardBuilder);
        System.out.println(minimalHouse);
        System.out.println();

        // --- 2) Full-featured luxury house ---
        System.out.println("  >> Full-Featured Luxury House (via Director):");
        LuxuryHouseBuilder luxuryBuilder = new LuxuryHouseBuilder();
        House luxuryHouse = director.constructFullFeaturedHouse(luxuryBuilder);
        System.out.println(luxuryHouse);
        System.out.println();

        // --- 3) Fluent builder usage without Director ---
        System.out.println("  >> Custom House (fluent builder, no Director):");
        System.out.println("    Client directly controls the builder steps:");
        House customHouse = new ConcreteHouseBuilder()
                .buildFoundation()
                .buildWalls()
                .buildRoof()
                .buildWindows(8)
                .buildDoors(3)
                .buildGarden()
                .setInterior("Rustic Country")
                .build();
        System.out.println(customHouse);
        System.out.println();

        // Key takeaway
        System.out.println("  KEY TAKEAWAY:");
        System.out.println("  - The Director controls the construction process (WHAT to build).");
        System.out.println("  - The Builder controls the details (HOW to build each step).");
        System.out.println("  - Same Director + different Builder = different product.");
        System.out.println("  - The client can also bypass the Director and use the");
        System.out.println("    fluent builder interface directly for custom configurations.");
        System.out.println();
    }
}
