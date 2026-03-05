package com.example.week11;

import com.example.week11.chain.ChainDemo;
import com.example.week11.command.CommandDemo;
import com.example.week11.iterator.IteratorDemo;
import com.example.week11.mediator.MediatorDemo;
import com.example.week11.memento.MementoDemo;
import com.example.week11.observer.ObserverDemo;
import com.example.week11.state.StateDemo;
import com.example.week11.strategy.StrategyDemo;
import com.example.week11.template.TemplateDemo;
import com.example.week11.visitor.VisitorDemo;

/**
 * CEN206 Object-Oriented Programming - Week 11
 * =============================================
 * Behavioral Design Patterns
 *
 * This program demonstrates all 10 GoF Behavioral Design Patterns:
 *
 *  1. Chain of Responsibility - Pass request along a chain of handlers
 *  2. Command               - Encapsulate a request as an object
 *  3. Iterator              - Access elements sequentially without exposing internals
 *  4. Mediator              - Centralize complex communication between objects
 *  5. Memento               - Capture and restore object state (undo/redo)
 *  6. Observer              - Notify dependents of state changes automatically
 *  7. State                 - Alter behavior when internal state changes
 *  8. Strategy              - Define a family of interchangeable algorithms
 *  9. Template Method       - Define algorithm skeleton, defer steps to subclasses
 * 10. Visitor               - Add new operations without changing element classes
 *
 * Run with:  mvn compile exec:java
 */
public class Main {

    public static void main(String[] args) {
        System.out.println("╔══════════════════════════════════════════════════════════════╗");
        System.out.println("║   CEN206 - Week 11: Behavioral Design Patterns              ║");
        System.out.println("║   All 10 GoF Behavioral Patterns Demonstrated                ║");
        System.out.println("╚══════════════════════════════════════════════════════════════╝");
        System.out.println();

        // 1. Chain of Responsibility
        ChainDemo.demo();

        // 2. Command
        CommandDemo.demo();

        // 3. Iterator
        IteratorDemo.demo();

        // 4. Mediator
        MediatorDemo.demo();

        // 5. Memento
        MementoDemo.demo();

        // 6. Observer
        ObserverDemo.demo();

        // 7. State
        StateDemo.demo();

        // 8. Strategy
        StrategyDemo.demo();

        // 9. Template Method
        TemplateDemo.demo();

        // 10. Visitor
        VisitorDemo.demo();

        System.out.println("╔══════════════════════════════════════════════════════════════╗");
        System.out.println("║   All 10 Behavioral Design Pattern demos completed!          ║");
        System.out.println("╚══════════════════════════════════════════════════════════════╝");
    }
}
