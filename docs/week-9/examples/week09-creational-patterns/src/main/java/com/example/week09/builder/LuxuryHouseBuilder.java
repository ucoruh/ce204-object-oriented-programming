package com.example.week09.builder;

/**
 * Concrete Builder - LuxuryHouseBuilder
 *
 * Builds a luxury/premium house with high-end materials.
 * Same building steps as ConcreteHouseBuilder, but produces a
 * very different product. This demonstrates how the same construction
 * process can create different representations.
 */
public class LuxuryHouseBuilder implements HouseBuilder {

    private House house;

    public LuxuryHouseBuilder() {
        this.house = new House();
    }

    @Override
    public HouseBuilder buildFoundation() {
        house.setFoundation("Reinforced concrete with waterproofing");
        System.out.println("      Step: Laying reinforced foundation with waterproofing...");
        return this;
    }

    @Override
    public HouseBuilder buildWalls() {
        house.setWalls("Insulated stone masonry walls");
        System.out.println("      Step: Building insulated stone masonry walls...");
        return this;
    }

    @Override
    public HouseBuilder buildRoof() {
        house.setRoof("Slate tile roof with skylights");
        System.out.println("      Step: Installing premium slate tile roof with skylights...");
        return this;
    }

    @Override
    public HouseBuilder buildWindows(int count) {
        house.setNumWindows(count);
        System.out.println("      Step: Installing " + count + " double-glazed panoramic windows...");
        return this;
    }

    @Override
    public HouseBuilder buildDoors(int count) {
        house.setNumDoors(count);
        System.out.println("      Step: Installing " + count + " solid oak doors with smart locks...");
        return this;
    }

    @Override
    public HouseBuilder buildGarage() {
        house.setHasGarage(true);
        System.out.println("      Step: Building a heated three-car garage...");
        return this;
    }

    @Override
    public HouseBuilder buildSwimmingPool() {
        house.setHasSwimmingPool(true);
        System.out.println("      Step: Building a heated infinity pool...");
        return this;
    }

    @Override
    public HouseBuilder buildGarden() {
        house.setHasGarden(true);
        System.out.println("      Step: Creating a landscaped garden with fountain...");
        return this;
    }

    @Override
    public HouseBuilder setInterior(String style) {
        house.setInteriorStyle(style);
        System.out.println("      Step: Applying premium '" + style + "' interior design...");
        return this;
    }

    @Override
    public House build() {
        House result = this.house;
        this.house = new House();
        return result;
    }
}
