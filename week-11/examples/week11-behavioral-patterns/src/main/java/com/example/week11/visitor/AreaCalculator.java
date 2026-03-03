package com.example.week11.visitor;

/**
 * Visitor Pattern - Concrete Visitor: Area Calculator
 *
 * Calculates the area of each shape it visits. This visitor adds
 * the "calculate area" operation to all shapes without modifying
 * the Shape classes. New operations (like perimeter calculation)
 * can be added by creating additional visitors.
 */
public class AreaCalculator implements ShapeVisitor {

    /** Running total of all areas calculated */
    private double totalArea = 0;

    /**
     * Calculates and prints the area of a circle.
     * Formula: pi * r^2
     *
     * @param circle the circle to calculate area for
     */
    @Override
    public void visit(Circle circle) {
        double area = Math.PI * circle.getRadius() * circle.getRadius();
        totalArea += area;
        System.out.printf("    [AreaCalculator] Circle (r=%.1f): area = %.2f%n",
            circle.getRadius(), area);
    }

    /**
     * Calculates and prints the area of a rectangle.
     * Formula: width * height
     *
     * @param rectangle the rectangle to calculate area for
     */
    @Override
    public void visit(Rectangle rectangle) {
        double area = rectangle.getWidth() * rectangle.getHeight();
        totalArea += area;
        System.out.printf("    [AreaCalculator] Rectangle (%.1f x %.1f): area = %.2f%n",
            rectangle.getWidth(), rectangle.getHeight(), area);
    }

    /**
     * Returns the total area accumulated across all visited shapes.
     *
     * @return the total area
     */
    public double getTotalArea() {
        return totalArea;
    }
}
