package com.example.week11.template;

/**
 * Template Method Pattern - Demo
 *
 * Intent:
 *   Define the skeleton of an algorithm in an operation, deferring
 *   some steps to subclasses. Template Method lets subclasses redefine
 *   certain steps of an algorithm without changing its structure.
 *
 * Structure:
 *   DataProcessor (Abstract Class)
 *       + process(): void  [template method - FINAL]
 *       # readData()       [abstract step]
 *       # parseData()      [abstract step]
 *       # analyzeData()    [abstract step]
 *       # logStart()       [hook - optional override]
 *       # logEnd()         [hook - optional override]
 *           ^
 *           |
 *     +-----+------+
 *     |            |
 * CSVProcessor  JSONProcessor
 *
 * When to Use:
 *   - You want to let clients extend only particular steps of an algorithm
 *   - You have several classes with nearly identical algorithms (code duplication)
 *   - You want to control the extension points (what subclasses can and cannot override)
 *
 * Real-World Examples:
 *   - Java's AbstractList, AbstractSet (collection frameworks)
 *   - Servlet's HttpServlet.service() calling doGet(), doPost()
 *   - JUnit's setUp() / tearDown() lifecycle
 *   - Spring's JdbcTemplate
 *
 * Template Method vs. Strategy:
 *   - Template Method: Uses inheritance (subclass overrides steps)
 *   - Strategy: Uses composition (swap the entire algorithm object)
 */
public class TemplateDemo {

    public static void demo() {
        System.out.println("==============================================================");
        System.out.println("  Pattern 9: TEMPLATE METHOD");
        System.out.println("  Define algorithm skeleton, defer steps to subclasses");
        System.out.println("==============================================================");

        // Process CSV data using the template method
        System.out.println("\n  --- Processing CSV Data ---");
        DataProcessor csvProcessor = new CSVProcessor();
        csvProcessor.process();

        // Process JSON data using the same template method
        System.out.println("\n  --- Processing JSON Data ---");
        DataProcessor jsonProcessor = new JSONProcessor();
        jsonProcessor.process();

        System.out.println("\n  Note: Both processors follow the same algorithm:");
        System.out.println("    logStart -> readData -> parseData -> analyzeData -> logEnd");
        System.out.println("    But each step is implemented differently per format.");
        System.out.println("    CSVProcessor overrides hooks; JSONProcessor uses defaults.");

        System.out.println();
    }
}
