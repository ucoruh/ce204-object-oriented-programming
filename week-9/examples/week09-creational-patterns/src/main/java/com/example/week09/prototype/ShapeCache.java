package com.example.week09.prototype;

import java.util.HashMap;
import java.util.Map;

/**
 * Prototype Registry - ShapeCache
 *
 * A registry (cache) of pre-built prototype objects. The client can
 * look up a shape by its key and receive a clone instead of a new instance.
 *
 * Benefits:
 *   - Avoids expensive object creation (imagine shapes loaded from a file/DB)
 *   - The client does not need to know the concrete class of the shape
 *   - Pre-configured shapes serve as templates for cloning
 */
public class ShapeCache {

    private final Map<String, Shape> cache = new HashMap<>();

    /**
     * Registers a prototype shape under the given key.
     */
    public void put(String key, Shape shape) {
        cache.put(key, shape);
    }

    /**
     * Returns a CLONE of the shape associated with the given key.
     * The original prototype remains untouched in the cache.
     *
     * @param key the identifier for the prototype
     * @return a new clone of the stored shape
     * @throws IllegalArgumentException if the key is not found
     */
    public Shape get(String key) {
        Shape prototype = cache.get(key);
        if (prototype == null) {
            throw new IllegalArgumentException("No prototype found for key: " + key);
        }
        return prototype.cloneShape();
    }

    /**
     * Loads some default prototypes into the cache.
     * In a real application, these might come from a database or config file.
     */
    public void loadDefaults() {
        // Red circle prototype
        Circle redCircle = new Circle();
        redCircle.setColor("Red");
        redCircle.setX(10);
        redCircle.setY(20);
        redCircle.setRadius(50.0);
        cache.put("red-circle", redCircle);

        // Blue rectangle prototype
        Rectangle blueRect = new Rectangle();
        blueRect.setColor("Blue");
        blueRect.setX(0);
        blueRect.setY(0);
        blueRect.setWidth(100.0);
        blueRect.setHeight(60.0);
        cache.put("blue-rectangle", blueRect);

        // Green circle prototype
        Circle greenCircle = new Circle();
        greenCircle.setColor("Green");
        greenCircle.setX(50);
        greenCircle.setY(50);
        greenCircle.setRadius(30.0);
        cache.put("green-circle", greenCircle);
    }
}
