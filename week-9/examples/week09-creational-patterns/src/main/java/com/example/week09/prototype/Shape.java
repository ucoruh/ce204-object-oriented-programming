package com.example.week09.prototype;

/**
 * Prototype Interface - Shape
 *
 * Declares the cloning interface. Any class that supports cloning
 * must implement this interface. In Java, we can also implement
 * java.lang.Cloneable, but here we use our own interface for clarity.
 *
 * The key idea: create new objects by copying existing ones rather than
 * instantiating classes directly. This is useful when:
 *   - Object creation is expensive (e.g., involves DB queries)
 *   - You want to hide complexity of creating new instances
 *   - You need copies with slightly different configurations
 */
public abstract class Shape {

    private String color;
    private int x;
    private int y;

    /** Default constructor. */
    public Shape() {
    }

    /**
     * Copy constructor - used by clone().
     * This ensures all fields are properly deep-copied.
     */
    protected Shape(Shape source) {
        this.color = source.color;
        this.x = source.x;
        this.y = source.y;
    }

    /**
     * The prototype method. Each concrete shape must implement this
     * to return a copy of itself.
     *
     * @return a new Shape that is a copy of this shape
     */
    public abstract Shape cloneShape();

    // --- Getters and Setters ---

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    /**
     * Two shapes are considered equal if they have the same type,
     * position, and color.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Shape other = (Shape) obj;
        return x == other.x && y == other.y
                && (color != null ? color.equals(other.color) : other.color == null);
    }

    @Override
    public int hashCode() {
        int result = color != null ? color.hashCode() : 0;
        result = 31 * result + x;
        result = 31 * result + y;
        return result;
    }
}
