package com.example.week09.builder;

/**
 * Concrete Builder - ConcreteHouseBuilder
 *
 * Builds a standard/basic house with default materials.
 * Implements all the building steps defined in HouseBuilder.
 * Keeps track of the product being built internally.
 */
public class ConcreteHouseBuilder implements HouseBuilder {

    private House house;

    public ConcreteHouseBuilder() {
        this.house = new House();
    }

    @Override
    public HouseBuilder buildFoundation() {
        house.setFoundation("Concrete slab foundation");
        System.out.println("      Step: Laying concrete slab foundation...");
        return this;
    }

    @Override
    public HouseBuilder buildWalls() {
        house.setWalls("Standard brick walls");
        System.out.println("      Step: Building standard brick walls...");
        return this;
    }

    @Override
    public HouseBuilder buildRoof() {
        house.setRoof("Asphalt shingle roof");
        System.out.println("      Step: Installing asphalt shingle roof...");
        return this;
    }

    @Override
    public HouseBuilder buildWindows(int count) {
        house.setNumWindows(count);
        System.out.println("      Step: Installing " + count + " standard windows...");
        return this;
    }

    @Override
    public HouseBuilder buildDoors(int count) {
        house.setNumDoors(count);
        System.out.println("      Step: Installing " + count + " wooden doors...");
        return this;
    }

    @Override
    public HouseBuilder buildGarage() {
        house.setHasGarage(true);
        System.out.println("      Step: Building a single-car garage...");
        return this;
    }

    @Override
    public HouseBuilder buildSwimmingPool() {
        house.setHasSwimmingPool(true);
        System.out.println("      Step: Digging and building a basic pool...");
        return this;
    }

    @Override
    public HouseBuilder buildGarden() {
        house.setHasGarden(true);
        System.out.println("      Step: Planting a small garden...");
        return this;
    }

    @Override
    public HouseBuilder setInterior(String style) {
        house.setInteriorStyle(style);
        System.out.println("      Step: Applying '" + style + "' interior design...");
        return this;
    }

    @Override
    public House build() {
        House result = this.house;
        // Reset builder for potential reuse
        this.house = new House();
        return result;
    }
}
