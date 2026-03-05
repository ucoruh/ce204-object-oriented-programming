package com.example.week10;

import com.example.week10.adapter.AdapterDemo;
import com.example.week10.bridge.BridgeDemo;
import com.example.week10.composite.CompositeDemo;
import com.example.week10.decorator.DecoratorDemo;
import com.example.week10.facade.FacadeDemo;
import com.example.week10.flyweight.FlyweightDemo;
import com.example.week10.proxy.ProxyDemo;

/**
 * =============================================================================
 * CEN206 Object-Oriented Programming - Week 10
 * STRUCTURAL DESIGN PATTERNS
 * =============================================================================
 *
 * Structural patterns deal with how classes and objects are composed to form
 * larger structures. They help ensure that when one part of a system changes,
 * the entire structure does not need to change.
 *
 * This project demonstrates all seven GoF structural patterns:
 *
 *   1. Adapter   - Makes incompatible interfaces work together
 *   2. Bridge    - Separates abstraction from implementation
 *   3. Composite - Treats individual objects and compositions uniformly
 *   4. Decorator - Adds responsibilities to objects dynamically
 *   5. Facade    - Provides a simplified interface to a complex subsystem
 *   6. Flyweight - Shares state to support large numbers of fine-grained objects
 *   7. Proxy     - Provides a surrogate or placeholder for another object
 *
 * Run with:  mvn compile exec:java
 * =============================================================================
 */
public class Main {

    public static void main(String[] args) {
        System.out.println("=============================================================");
        System.out.println("  CEN206 - Week 10: Structural Design Patterns");
        System.out.println("=============================================================");
        System.out.println();

        // 1. Adapter Pattern
        AdapterDemo.demo();

        // 2. Bridge Pattern
        BridgeDemo.demo();

        // 3. Composite Pattern
        CompositeDemo.demo();

        // 4. Decorator Pattern
        DecoratorDemo.demo();

        // 5. Facade Pattern
        FacadeDemo.demo();

        // 6. Flyweight Pattern
        FlyweightDemo.demo();

        // 7. Proxy Pattern
        ProxyDemo.demo();

        System.out.println("=============================================================");
        System.out.println("  All Structural Pattern demos completed successfully!");
        System.out.println("=============================================================");
    }
}
