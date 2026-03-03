package com.example.week09;

import com.example.week09.factorymethod.FactoryMethodDemo;
import com.example.week09.abstractfactory.AbstractFactoryDemo;
import com.example.week09.builder.BuilderDemo;
import com.example.week09.prototype.PrototypeDemo;
import com.example.week09.singleton.SingletonDemo;

/**
 * CEN206 Object-Oriented Programming - Week 9
 * =============================================
 * Creational Design Patterns (GoF)
 *
 * This program demonstrates all five Creational Design Patterns from the
 * Gang of Four (GoF) catalog. Creational patterns deal with object creation
 * mechanisms, trying to create objects in a manner suitable to the situation.
 *
 * The five patterns covered are:
 *   1. Factory Method   - Defines an interface for creating objects, letting
 *                          subclasses decide which class to instantiate.
 *   2. Abstract Factory - Provides an interface for creating families of
 *                          related objects without specifying concrete classes.
 *   3. Builder          - Separates the construction of a complex object from
 *                          its representation.
 *   4. Prototype        - Creates new objects by copying an existing object
 *                          (the prototype).
 *   5. Singleton        - Ensures a class has only one instance and provides
 *                          a global point of access to it.
 *
 * Run with:  mvn compile exec:java
 */
public class Main {

    public static void main(String[] args) {
        System.out.println("╔══════════════════════════════════════════════════════════════╗");
        System.out.println("║  CEN206 - Week 9: Creational Design Patterns (GoF)         ║");
        System.out.println("║  Object-Oriented Programming                               ║");
        System.out.println("╚══════════════════════════════════════════════════════════════╝");
        System.out.println();

        // 1. Factory Method Pattern
        FactoryMethodDemo.demo();

        // 2. Abstract Factory Pattern
        AbstractFactoryDemo.demo();

        // 3. Builder Pattern
        BuilderDemo.demo();

        // 4. Prototype Pattern
        PrototypeDemo.demo();

        // 5. Singleton Pattern
        SingletonDemo.demo();

        System.out.println("══════════════════════════════════════════════════════════════");
        System.out.println("  All Creational Pattern demos completed successfully!");
        System.out.println("══════════════════════════════════════════════════════════════");
    }
}
