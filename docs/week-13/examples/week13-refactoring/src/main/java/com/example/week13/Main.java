package com.example.week13;

import com.example.week13.composing.ExtractMethodDemo;
import com.example.week13.composing.InlineMethodDemo;
import com.example.week13.composing.ReplaceTempWithQueryDemo;
import com.example.week13.moving.MoveMethodDemo;
import com.example.week13.moving.ExtractClassDemo;
import com.example.week13.organizing.EncapsulateFieldDemo;
import com.example.week13.organizing.ReplaceTypeCodeWithClassDemo;
import com.example.week13.conditionals.DecomposeConditionalDemo;
import com.example.week13.conditionals.ReplaceConditionalWithPolymorphismDemo;
import com.example.week13.generalization.PullUpMethodDemo;
import com.example.week13.generalization.ReplaceInheritanceWithDelegationDemo;

/**
 * =============================================================================
 * CEN206 - Object-Oriented Programming
 * Week 13 - Refactoring Techniques
 * =============================================================================
 *
 * This project demonstrates the major categories of refactoring techniques
 * as catalogued by Martin Fowler in "Refactoring: Improving the Design of
 * Existing Code."
 *
 * Refactoring Categories Covered:
 *   1. Composing Methods        - Extract Method, Inline Method,
 *                                  Replace Temp with Query
 *   2. Moving Features          - Move Method, Extract Class
 *   3. Organizing Data          - Encapsulate Field,
 *                                  Replace Type Code with Class
 *   4. Simplifying Conditionals - Decompose Conditional,
 *                                  Replace Conditional with Polymorphism
 *   5. Dealing with Generalization - Pull Up Method,
 *                                     Replace Inheritance with Delegation
 *
 * How to run:
 *   mvn compile exec:java
 *
 * @author CEN206 Course
 */
public class Main {

    public static void main(String[] args) {
        System.out.println("=============================================================");
        System.out.println(" CEN206 - Week 13: Refactoring Techniques");
        System.out.println("=============================================================");
        System.out.println();

        // -----------------------------------------------------------------
        // Section 1: Composing Methods
        // -----------------------------------------------------------------
        System.out.println("*************************************************************");
        System.out.println("  SECTION 1: COMPOSING METHODS");
        System.out.println("  Restructuring methods for clarity and reuse.");
        System.out.println("*************************************************************");
        System.out.println();

        System.out.println("--- 1a. Extract Method ---");
        ExtractMethodDemo.demo();
        System.out.println();

        System.out.println("--- 1b. Inline Method ---");
        InlineMethodDemo.demo();
        System.out.println();

        System.out.println("--- 1c. Replace Temp with Query ---");
        ReplaceTempWithQueryDemo.demo();
        System.out.println();

        // -----------------------------------------------------------------
        // Section 2: Moving Features Between Objects
        // -----------------------------------------------------------------
        System.out.println("*************************************************************");
        System.out.println("  SECTION 2: MOVING FEATURES BETWEEN OBJECTS");
        System.out.println("  Placing responsibilities in the right classes.");
        System.out.println("*************************************************************");
        System.out.println();

        System.out.println("--- 2a. Move Method ---");
        MoveMethodDemo.demo();
        System.out.println();

        System.out.println("--- 2b. Extract Class ---");
        ExtractClassDemo.demo();
        System.out.println();

        // -----------------------------------------------------------------
        // Section 3: Organizing Data
        // -----------------------------------------------------------------
        System.out.println("*************************************************************");
        System.out.println("  SECTION 3: ORGANIZING DATA");
        System.out.println("  Improving how data is stored and accessed.");
        System.out.println("*************************************************************");
        System.out.println();

        System.out.println("--- 3a. Encapsulate Field ---");
        EncapsulateFieldDemo.demo();
        System.out.println();

        System.out.println("--- 3b. Replace Type Code with Class ---");
        ReplaceTypeCodeWithClassDemo.demo();
        System.out.println();

        // -----------------------------------------------------------------
        // Section 4: Simplifying Conditional Expressions
        // -----------------------------------------------------------------
        System.out.println("*************************************************************");
        System.out.println("  SECTION 4: SIMPLIFYING CONDITIONAL EXPRESSIONS");
        System.out.println("  Making complex conditions readable and maintainable.");
        System.out.println("*************************************************************");
        System.out.println();

        System.out.println("--- 4a. Decompose Conditional ---");
        DecomposeConditionalDemo.demo();
        System.out.println();

        System.out.println("--- 4b. Replace Conditional with Polymorphism ---");
        ReplaceConditionalWithPolymorphismDemo.demo();
        System.out.println();

        // -----------------------------------------------------------------
        // Section 5: Dealing with Generalization
        // -----------------------------------------------------------------
        System.out.println("*************************************************************");
        System.out.println("  SECTION 5: DEALING WITH GENERALIZATION");
        System.out.println("  Managing inheritance hierarchies effectively.");
        System.out.println("*************************************************************");
        System.out.println();

        System.out.println("--- 5a. Pull Up Method ---");
        PullUpMethodDemo.demo();
        System.out.println();

        System.out.println("--- 5b. Replace Inheritance with Delegation ---");
        ReplaceInheritanceWithDelegationDemo.demo();
        System.out.println();

        // -----------------------------------------------------------------
        // Summary
        // -----------------------------------------------------------------
        System.out.println("=============================================================");
        System.out.println(" SUMMARY");
        System.out.println("=============================================================");
        System.out.println(" Refactoring is the disciplined technique of improving");
        System.out.println(" code structure WITHOUT changing its external behavior.");
        System.out.println();
        System.out.println(" Key takeaways:");
        System.out.println("   - Composing Methods: small, well-named methods.");
        System.out.println("   - Moving Features: put behavior where the data lives.");
        System.out.println("   - Organizing Data: encapsulate and use proper types.");
        System.out.println("   - Simplifying Conditionals: readable, polymorphic.");
        System.out.println("   - Generalization: prefer delegation over inheritance.");
        System.out.println("=============================================================");
    }
}
