package com.example.week09.builder;

/**
 * Builder Interface - HouseBuilder
 *
 * Specifies the steps required to build each part of a house.
 * The order in which the steps are called is controlled by the Director,
 * while the implementation of each step is controlled by the ConcreteBuilder.
 *
 * Each method returns the builder itself to allow fluent/chained calls.
 */
public interface HouseBuilder {

    /**
     * Builds the foundation of the house.
     */
    HouseBuilder buildFoundation();

    /**
     * Builds the walls of the house.
     */
    HouseBuilder buildWalls();

    /**
     * Builds the roof of the house.
     */
    HouseBuilder buildRoof();

    /**
     * Installs windows in the house.
     */
    HouseBuilder buildWindows(int count);

    /**
     * Installs doors in the house.
     */
    HouseBuilder buildDoors(int count);

    /**
     * Optionally builds a garage.
     */
    HouseBuilder buildGarage();

    /**
     * Optionally builds a swimming pool.
     */
    HouseBuilder buildSwimmingPool();

    /**
     * Optionally builds a garden.
     */
    HouseBuilder buildGarden();

    /**
     * Sets the interior design style.
     */
    HouseBuilder setInterior(String style);

    /**
     * Returns the final constructed House.
     */
    House build();
}
