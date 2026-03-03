package com.example.week09.builder;

/**
 * Product - House
 *
 * A complex object that is constructed step by step using a builder.
 * A house has many optional parts: foundation, walls, roof, garage,
 * swimming pool, garden, etc. The Builder pattern lets us construct
 * different configurations without a telescoping constructor.
 */
public class House {

    // Required parts
    private String foundation;
    private String walls;
    private String roof;

    // Optional parts
    private int numWindows;
    private int numDoors;
    private boolean hasGarage;
    private boolean hasSwimmingPool;
    private boolean hasGarden;
    private String interiorStyle;

    // --- Getters and Setters ---

    public String getFoundation() {
        return foundation;
    }

    public void setFoundation(String foundation) {
        this.foundation = foundation;
    }

    public String getWalls() {
        return walls;
    }

    public void setWalls(String walls) {
        this.walls = walls;
    }

    public String getRoof() {
        return roof;
    }

    public void setRoof(String roof) {
        this.roof = roof;
    }

    public int getNumWindows() {
        return numWindows;
    }

    public void setNumWindows(int numWindows) {
        this.numWindows = numWindows;
    }

    public int getNumDoors() {
        return numDoors;
    }

    public void setNumDoors(int numDoors) {
        this.numDoors = numDoors;
    }

    public boolean isHasGarage() {
        return hasGarage;
    }

    public void setHasGarage(boolean hasGarage) {
        this.hasGarage = hasGarage;
    }

    public boolean isHasSwimmingPool() {
        return hasSwimmingPool;
    }

    public void setHasSwimmingPool(boolean hasSwimmingPool) {
        this.hasSwimmingPool = hasSwimmingPool;
    }

    public boolean isHasGarden() {
        return hasGarden;
    }

    public void setHasGarden(boolean hasGarden) {
        this.hasGarden = hasGarden;
    }

    public String getInteriorStyle() {
        return interiorStyle;
    }

    public void setInteriorStyle(String interiorStyle) {
        this.interiorStyle = interiorStyle;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("    House Details:\n");
        sb.append("      Foundation    : ").append(foundation).append("\n");
        sb.append("      Walls         : ").append(walls).append("\n");
        sb.append("      Roof          : ").append(roof).append("\n");
        sb.append("      Windows       : ").append(numWindows).append("\n");
        sb.append("      Doors         : ").append(numDoors).append("\n");
        sb.append("      Garage        : ").append(hasGarage ? "Yes" : "No").append("\n");
        sb.append("      Swimming Pool : ").append(hasSwimmingPool ? "Yes" : "No").append("\n");
        sb.append("      Garden        : ").append(hasGarden ? "Yes" : "No").append("\n");
        sb.append("      Interior      : ").append(interiorStyle != null ? interiorStyle : "N/A");
        return sb.toString();
    }
}
