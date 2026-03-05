package com.example.week04;

import com.example.week04.classdiagram.*;
import com.example.week04.sequence.OrderProcessDemo;
import com.example.week04.state.TrafficLightStateMachine;
import com.example.week04.state.VendingMachineStateMachine;

/**
 * CEN206 - Object-Oriented Programming
 * Week 4: UML to Java Code Mapping
 *
 * This main class runs every demonstration in the project so that
 * students can see how each UML concept translates into executable Java.
 *
 * Build and run:
 *   mvn compile exec:java
 */
public class Main {

    public static void main(String[] args) {

        System.out.println("==========================================================");
        System.out.println("  CEN206 - Week 4: UML Diagram to Java Code Examples");
        System.out.println("==========================================================\n");

        // --- Class Diagram Relationships ---

        System.out.println("----------------------------------------------------------");
        System.out.println("  1. Association (1-to-1 and 1-to-many)");
        System.out.println("----------------------------------------------------------");
        Association.demo();

        System.out.println("\n----------------------------------------------------------");
        System.out.println("  2. Aggregation (University has Departments)");
        System.out.println("----------------------------------------------------------");
        Aggregation.demo();

        System.out.println("\n----------------------------------------------------------");
        System.out.println("  3. Composition (House has Rooms)");
        System.out.println("----------------------------------------------------------");
        Composition.demo();

        System.out.println("\n----------------------------------------------------------");
        System.out.println("  4. Generalization / Inheritance");
        System.out.println("----------------------------------------------------------");
        Generalization.demo();

        System.out.println("\n----------------------------------------------------------");
        System.out.println("  5. Dependency");
        System.out.println("----------------------------------------------------------");
        Dependency.demo();

        // --- Sequence Diagram ---

        System.out.println("\n----------------------------------------------------------");
        System.out.println("  6. Sequence Diagram - Order Processing");
        System.out.println("----------------------------------------------------------");
        OrderProcessDemo.demo();

        // --- State Diagrams ---

        System.out.println("\n----------------------------------------------------------");
        System.out.println("  7. State Diagram - Traffic Light");
        System.out.println("----------------------------------------------------------");
        TrafficLightStateMachine.demo();

        System.out.println("\n----------------------------------------------------------");
        System.out.println("  8. State Diagram - Vending Machine");
        System.out.println("----------------------------------------------------------");
        VendingMachineStateMachine.demo();

        System.out.println("\n==========================================================");
        System.out.println("  All demonstrations completed successfully.");
        System.out.println("==========================================================");
    }
}
