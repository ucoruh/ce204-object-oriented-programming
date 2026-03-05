package com.example.week12;

import com.example.week12.bloaters.LongMethodBefore;
import com.example.week12.bloaters.LongMethodAfter;
import com.example.week12.bloaters.LargeClassBefore;
import com.example.week12.bloaters.LargeClassAfter;
import com.example.week12.bloaters.PrimitiveObsessionBefore;
import com.example.week12.bloaters.PrimitiveObsessionAfter;
import com.example.week12.ooabusers.SwitchStatementBefore;
import com.example.week12.ooabusers.SwitchStatementAfter;
import com.example.week12.couplers.FeatureEnvyBefore;
import com.example.week12.couplers.FeatureEnvyAfter;
import com.example.week12.dispensables.DeadCodeDemo;
import com.example.week12.dispensables.DuplicateCodeBefore;
import com.example.week12.dispensables.DuplicateCodeAfter;

/**
 * =============================================================================
 * CEN206 - Object-Oriented Programming
 * Week 12 - Code Smells and Refactoring Fundamentals
 * =============================================================================
 *
 * This project demonstrates the major categories of code smells as catalogued
 * by Martin Fowler and Kent Beck, along with before/after refactoring examples.
 *
 * Code Smell Categories Covered:
 *   1. Bloaters       - Code that has grown too large (Long Method, Large Class,
 *                        Primitive Obsession, Long Parameter List, Data Clumps)
 *   2. OO Abusers     - Misuse of OO principles (Switch Statements, Refused
 *                        Bequest, Temporary Field, Alternative Classes)
 *   3. Change Preventers - Code that makes changes difficult (Divergent Change,
 *                          Shotgun Surgery, Parallel Inheritance)
 *   4. Dispensables    - Unnecessary code (Dead Code, Duplicate Code, Lazy Class,
 *                        Data Class, Speculative Generality)
 *   5. Couplers       - Excessive coupling (Feature Envy, Inappropriate Intimacy,
 *                        Message Chains, Middle Man)
 *
 * How to run:
 *   mvn compile exec:java
 *
 * @author CEN206 Course
 */
public class Main {

    public static void main(String[] args) {
        System.out.println("=============================================================");
        System.out.println(" CEN206 - Week 12: Code Smells and Refactoring Fundamentals");
        System.out.println("=============================================================");
        System.out.println();

        // -----------------------------------------------------------------
        // Section 1: Bloaters
        // -----------------------------------------------------------------
        System.out.println("*************************************************************");
        System.out.println("  SECTION 1: BLOATERS");
        System.out.println("  Code that has grown excessively large over time.");
        System.out.println("*************************************************************");
        System.out.println();

        System.out.println("--- 1a. Long Method (Before) ---");
        LongMethodBefore.demo();
        System.out.println();

        System.out.println("--- 1b. Long Method (After Refactoring) ---");
        LongMethodAfter.demo();
        System.out.println();

        System.out.println("--- 1c. Large Class (Before) ---");
        LargeClassBefore.demo();
        System.out.println();

        System.out.println("--- 1d. Large Class (After Refactoring) ---");
        LargeClassAfter.demo();
        System.out.println();

        System.out.println("--- 1e. Primitive Obsession (Before) ---");
        PrimitiveObsessionBefore.demo();
        System.out.println();

        System.out.println("--- 1f. Primitive Obsession (After Refactoring) ---");
        PrimitiveObsessionAfter.demo();
        System.out.println();

        // -----------------------------------------------------------------
        // Section 2: OO Abusers
        // -----------------------------------------------------------------
        System.out.println("*************************************************************");
        System.out.println("  SECTION 2: OO ABUSERS");
        System.out.println("  Code that does not properly use OO principles.");
        System.out.println("*************************************************************");
        System.out.println();

        System.out.println("--- 2a. Switch Statement (Before) ---");
        SwitchStatementBefore.demo();
        System.out.println();

        System.out.println("--- 2b. Switch Statement (After Refactoring) ---");
        SwitchStatementAfter.demo();
        System.out.println();

        // -----------------------------------------------------------------
        // Section 3: Couplers
        // -----------------------------------------------------------------
        System.out.println("*************************************************************");
        System.out.println("  SECTION 3: COUPLERS");
        System.out.println("  Code with excessive coupling between classes.");
        System.out.println("*************************************************************");
        System.out.println();

        System.out.println("--- 3a. Feature Envy (Before) ---");
        FeatureEnvyBefore.demo();
        System.out.println();

        System.out.println("--- 3b. Feature Envy (After Refactoring) ---");
        FeatureEnvyAfter.demo();
        System.out.println();

        // -----------------------------------------------------------------
        // Section 4: Dispensables
        // -----------------------------------------------------------------
        System.out.println("*************************************************************");
        System.out.println("  SECTION 4: DISPENSABLES");
        System.out.println("  Code that is unnecessary and can be removed.");
        System.out.println("*************************************************************");
        System.out.println();

        System.out.println("--- 4a. Dead Code ---");
        DeadCodeDemo.demo();
        System.out.println();

        System.out.println("--- 4b. Duplicate Code (Before) ---");
        DuplicateCodeBefore.demo();
        System.out.println();

        System.out.println("--- 4c. Duplicate Code (After Refactoring) ---");
        DuplicateCodeAfter.demo();
        System.out.println();

        // -----------------------------------------------------------------
        // Summary
        // -----------------------------------------------------------------
        System.out.println("=============================================================");
        System.out.println(" SUMMARY");
        System.out.println("=============================================================");
        System.out.println(" Code smells are indicators of deeper design problems.");
        System.out.println(" They are NOT bugs -- the code works -- but they make the");
        System.out.println(" code harder to understand, maintain, and extend.");
        System.out.println();
        System.out.println(" Key takeaways:");
        System.out.println("   - Bloaters: Keep methods/classes small and focused.");
        System.out.println("   - OO Abusers: Use polymorphism instead of conditionals.");
        System.out.println("   - Couplers: Each method should work with its own data.");
        System.out.println("   - Dispensables: Remove unused and duplicated code.");
        System.out.println("=============================================================");
    }
}
