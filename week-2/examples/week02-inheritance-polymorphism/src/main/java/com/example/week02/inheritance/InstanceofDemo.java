package com.example.week02.inheritance;

import java.util.ArrayList;
import java.util.List;

/**
 * ==========================================================================
 * DEMO 4: instanceof Operator
 * ==========================================================================
 *
 * The 'instanceof' operator tests whether an object is an instance of a
 * specific class or implements a specific interface.
 *
 * Syntax: object instanceof ClassName
 * Returns: true or false
 *
 * Key Points:
 *   - Returns true if the object IS-A that type (including parent types)
 *   - Returns false if the object is null
 *   - Used for safe downcasting (converting parent type to child type)
 *   - Java 16+ supports pattern matching with instanceof
 *
 * Common Use Cases:
 *   - Type checking before casting
 *   - Processing heterogeneous collections
 *   - Implementing type-specific behavior
 */
public class InstanceofDemo {

    // ======================================================================
    // Hierarchy for instanceof demonstrations
    // ======================================================================

    static class Animal {
        String name;

        Animal(String name) {
            this.name = name;
        }

        void speak() {
            System.out.println("    " + name + " makes a sound");
        }
    }

    static class Dog extends Animal {
        String breed;

        Dog(String name, String breed) {
            super(name);
            this.breed = breed;
        }

        void fetch() {
            System.out.println("    " + name + " fetches the ball!");
        }
    }

    static class Cat extends Animal {
        boolean isIndoor;

        Cat(String name, boolean isIndoor) {
            super(name);
            this.isIndoor = isIndoor;
        }

        void purr() {
            System.out.println("    " + name + " purrs... prrrr");
        }
    }

    static class GuideDog extends Dog {
        String ownerName;

        GuideDog(String name, String breed, String ownerName) {
            super(name, breed);
            this.ownerName = ownerName;
        }

        void guide() {
            System.out.println("    " + name + " guides " + ownerName);
        }
    }

    // Interface for additional instanceof checking
    interface Trainable {
        void train();
    }

    static class TrainedDog extends Dog implements Trainable {
        TrainedDog(String name, String breed) {
            super(name, breed);
        }

        @Override
        public void train() {
            System.out.println("    " + name + " is training!");
        }
    }

    // ======================================================================
    // Demo method
    // ======================================================================

    public static void demo() {

        // ------------------------------------------------------------------
        // Part 1: Basic instanceof Usage
        // ------------------------------------------------------------------
        System.out.println("[Part 1: Basic instanceof Checks]");
        System.out.println();

        Dog dog = new Dog("Rex", "German Shepherd");
        Cat cat = new Cat("Whiskers", true);
        GuideDog guide = new GuideDog("Buddy", "Labrador", "Alice");

        // instanceof checks against own type
        System.out.println("  dog instanceof Dog:      " + (dog instanceof Dog));       // true
        System.out.println("  cat instanceof Cat:      " + (cat instanceof Cat));       // true

        // instanceof checks against parent type (IS-A relationship)
        System.out.println("  dog instanceof Animal:   " + (dog instanceof Animal));    // true
        System.out.println("  cat instanceof Animal:   " + (cat instanceof Animal));    // true

        // instanceof checks against sibling type (NOT IS-A)
        // Note: dog instanceof Cat -> compile error! (unrelated types)
        // We use Animal reference to demonstrate runtime check:
        Animal animalDog = dog;
        Animal animalCat = cat;
        System.out.println("  animalDog instanceof Dog:  " + (animalDog instanceof Dog));   // true
        System.out.println("  animalDog instanceof Cat:  " + (animalDog instanceof Cat));   // false
        System.out.println("  animalCat instanceof Dog:  " + (animalCat instanceof Dog));   // false
        System.out.println("  animalCat instanceof Cat:  " + (animalCat instanceof Cat));   // true

        // Multi-level: GuideDog IS-A Dog IS-A Animal
        System.out.println();
        System.out.println("  GuideDog is a multi-level check:");
        System.out.println("  guide instanceof GuideDog: " + (guide instanceof GuideDog)); // true
        System.out.println("  guide instanceof Dog:      " + (guide instanceof Dog));      // true
        System.out.println("  guide instanceof Animal:   " + (guide instanceof Animal));   // true

        // ------------------------------------------------------------------
        // Part 2: null Check
        // ------------------------------------------------------------------
        System.out.println();
        System.out.println("[Part 2: null and instanceof]");
        System.out.println();

        Animal nullAnimal = null;
        System.out.println("  null instanceof Animal: " + (nullAnimal instanceof Animal));  // false
        System.out.println("  null instanceof Dog:    " + (nullAnimal instanceof Dog));     // false
        System.out.println("  (instanceof always returns false for null - this is safe!)");

        // ------------------------------------------------------------------
        // Part 3: Safe Downcasting
        // ------------------------------------------------------------------
        System.out.println();
        System.out.println("[Part 3: Safe Downcasting with instanceof]");
        System.out.println();

        Animal[] animals = {
            new Dog("Rex", "German Shepherd"),
            new Cat("Whiskers", true),
            new GuideDog("Buddy", "Labrador", "Alice"),
            new Cat("Luna", false),
            new TrainedDog("Max", "Border Collie")
        };

        System.out.println("  Processing array of animals:");
        System.out.println();

        for (Animal animal : animals) {
            System.out.println("  " + animal.name + " (" + animal.getClass().getSimpleName() + "):");

            // Safe downcasting: check BEFORE casting
            if (animal instanceof GuideDog) {
                GuideDog gd = (GuideDog) animal;  // Safe cast
                gd.guide();
            } else if (animal instanceof Dog) {
                Dog d = (Dog) animal;  // Safe cast
                d.fetch();
            }

            if (animal instanceof Cat) {
                Cat c = (Cat) animal;  // Safe cast
                c.purr();
            }

            if (animal instanceof Trainable) {
                Trainable t = (Trainable) animal;
                t.train();
            }
            System.out.println();
        }

        // ------------------------------------------------------------------
        // Part 4: Pattern Matching instanceof (Java 16+)
        // ------------------------------------------------------------------
        System.out.println("[Part 4: Pattern Matching instanceof (Java 16+)]");
        System.out.println();

        System.out.println("  Modern syntax combines instanceof check with variable declaration:");
        System.out.println();

        for (Animal animal : animals) {
            // Pattern matching: if the check is true, 'd' is automatically
            // declared and cast. No explicit cast needed!
            if (animal instanceof Dog d) {
                System.out.println("  " + d.name + " is a Dog (breed: " + d.breed + ")");
            }
            if (animal instanceof Cat c) {
                System.out.println("  " + c.name + " is a Cat (indoor: " + c.isIndoor + ")");
            }
        }

        // ------------------------------------------------------------------
        // Part 5: Unsafe Casting (ClassCastException)
        // ------------------------------------------------------------------
        System.out.println();
        System.out.println("[Part 5: Unsafe Casting Causes ClassCastException]");
        System.out.println();

        Animal animal = new Cat("Mittens", true);
        System.out.println("  animal is actually a Cat.");
        System.out.println("  Trying to cast to Dog WITHOUT instanceof check...");

        try {
            Dog wrongCast = (Dog) animal;  // This will throw!
            wrongCast.fetch();  // Never reached
        } catch (ClassCastException e) {
            System.out.println("  ClassCastException: " + e.getMessage());
            System.out.println("  (Always use instanceof before downcasting!)");
        }

        // ------------------------------------------------------------------
        // Part 6: Interface instanceof Check
        // ------------------------------------------------------------------
        System.out.println();
        System.out.println("[Part 6: instanceof with Interfaces]");
        System.out.println();

        TrainedDog trained = new TrainedDog("Max", "Border Collie");
        System.out.println("  trained instanceof Trainable: " + (trained instanceof Trainable));
        System.out.println("  trained instanceof Dog:       " + (trained instanceof Dog));
        System.out.println("  trained instanceof Animal:    " + (trained instanceof Animal));
        System.out.println("  (An object can be instanceof multiple types including interfaces)");

        // ------------------------------------------------------------------
        // Summary
        // ------------------------------------------------------------------
        System.out.println();
        System.out.println("[Summary]");
        System.out.println("  - instanceof checks IS-A relationships at runtime");
        System.out.println("  - Returns false for null (never throws NullPointerException)");
        System.out.println("  - Always use before downcasting to avoid ClassCastException");
        System.out.println("  - Works with classes AND interfaces");
        System.out.println("  - Java 16+ pattern matching simplifies cast + check into one step");
    }
}
