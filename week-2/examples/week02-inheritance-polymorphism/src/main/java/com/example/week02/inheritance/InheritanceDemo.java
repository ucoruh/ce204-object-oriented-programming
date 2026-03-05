package com.example.week02.inheritance;

/**
 * ==========================================================================
 * DEMO 1: Basic Inheritance
 * ==========================================================================
 *
 * INHERITANCE is a mechanism where a new class (child/subclass) inherits
 * fields and methods from an existing class (parent/superclass).
 *
 * Key Concepts:
 *   - "extends" keyword creates an IS-A relationship
 *   - Child class inherits all non-private members from parent
 *   - Child class can add NEW fields and methods
 *   - Child class can OVERRIDE inherited methods
 *   - Java supports SINGLE inheritance only (one parent class)
 *
 * Terminology:
 *   - Parent / Superclass / Base class
 *   - Child / Subclass / Derived class
 *
 * Benefits:
 *   - Code reuse: common behavior defined once in parent
 *   - Logical hierarchy: models real-world relationships
 *   - Extensibility: easy to add new specialized classes
 */
public class InheritanceDemo {

    // ======================================================================
    // Three-level inheritance hierarchy: Animal -> Dog -> GoldenRetriever
    // ======================================================================

    /**
     * The base class (top of the hierarchy).
     * Defines common properties and behavior for ALL animals.
     */
    static class Animal {
        // Fields inherited by ALL subclasses
        String name;
        int age;
        String species;

        Animal(String name, int age, String species) {
            this.name = name;
            this.age = age;
            this.species = species;
            System.out.println("    [Animal constructor] Creating animal: " + name);
        }

        // Methods inherited by ALL subclasses
        void eat() {
            System.out.println("    " + name + " is eating.");
        }

        void sleep() {
            System.out.println("    " + name + " is sleeping. Zzz...");
        }

        void displayInfo() {
            System.out.println("    Name: " + name + " | Species: " + species + " | Age: " + age);
        }

        String describe() {
            return name + " (a " + age + "-year-old " + species + ")";
        }
    }

    /**
     * Dog extends Animal - inherits all Animal members and adds dog-specific ones.
     * A Dog IS-A Animal.
     */
    static class Dog extends Animal {
        // Additional field specific to dogs
        String breed;
        boolean isTrained;

        Dog(String name, int age, String breed) {
            // Must call parent constructor first using super()
            super(name, age, "Dog");
            this.breed = breed;
            this.isTrained = false;
            System.out.println("    [Dog constructor] Breed: " + breed);
        }

        // Dog-specific methods (NOT available in Animal)
        void bark() {
            System.out.println("    " + name + " says: Woof! Woof!");
        }

        void fetch(String item) {
            System.out.println("    " + name + " fetches the " + item + "!");
        }

        void train() {
            isTrained = true;
            System.out.println("    " + name + " has been trained. Good boy/girl!");
        }

        // Override parent's displayInfo to include breed
        @Override
        void displayInfo() {
            super.displayInfo();  // Call parent version first
            System.out.println("    Breed: " + breed + " | Trained: " + isTrained);
        }
    }

    /**
     * GoldenRetriever extends Dog - three levels of inheritance.
     * A GoldenRetriever IS-A Dog, which IS-A Animal.
     */
    static class GoldenRetriever extends Dog {
        // Additional field specific to Golden Retrievers
        boolean isServiceDog;

        GoldenRetriever(String name, int age) {
            super(name, age, "Golden Retriever");
            this.isServiceDog = false;
            System.out.println("    [GoldenRetriever constructor]");
        }

        // Golden Retriever specific method
        void swim() {
            System.out.println("    " + name + " loves swimming! Splash!");
        }

        void certifyAsServiceDog() {
            this.isServiceDog = true;
            this.isTrained = true;  // Inherited from Dog
            System.out.println("    " + name + " is now a certified service dog!");
        }

        @Override
        void displayInfo() {
            super.displayInfo();
            System.out.println("    Service Dog: " + isServiceDog);
        }
    }

    // ======================================================================
    // Second hierarchy example: Vehicle -> Car, Vehicle -> Motorcycle
    // ======================================================================

    /**
     * Vehicle base class for a simpler two-level hierarchy.
     */
    static class Vehicle {
        String make;
        String model;
        int year;
        double speed;

        Vehicle(String make, String model, int year) {
            this.make = make;
            this.model = model;
            this.year = year;
            this.speed = 0;
        }

        void accelerate(double amount) {
            speed += amount;
            System.out.println("    " + make + " " + model + " accelerates to " + speed + " km/h");
        }

        void brake(double amount) {
            speed = Math.max(0, speed - amount);
            System.out.println("    " + make + " " + model + " brakes to " + speed + " km/h");
        }

        String getDescription() {
            return year + " " + make + " " + model;
        }
    }

    /**
     * Car extends Vehicle - adds car-specific features.
     */
    static class Car extends Vehicle {
        int doors;
        int passengers;

        Car(String make, String model, int year, int doors) {
            super(make, model, year);
            this.doors = doors;
            this.passengers = 0;
        }

        void loadPassengers(int count) {
            passengers = count;
            System.out.println("    " + getDescription() + " loaded " + count + " passengers");
        }
    }

    /**
     * Motorcycle extends Vehicle - adds motorcycle-specific features.
     */
    static class Motorcycle extends Vehicle {
        boolean hasSidecar;

        Motorcycle(String make, String model, int year) {
            super(make, model, year);
            this.hasSidecar = false;
        }

        void wheelie() {
            System.out.println("    " + getDescription() + " does a wheelie!");
        }
    }

    // ======================================================================
    // Demo method
    // ======================================================================

    public static void demo() {

        // ------------------------------------------------------------------
        // Part 1: Three-Level Inheritance Hierarchy
        // ------------------------------------------------------------------
        System.out.println("[Part 1: Animal -> Dog -> GoldenRetriever Hierarchy]");
        System.out.println();

        System.out.println("  Creating a GoldenRetriever (watch constructor chain):");
        GoldenRetriever buddy = new GoldenRetriever("Buddy", 3);
        System.out.println();

        System.out.println("  Buddy's full info:");
        buddy.displayInfo();

        // ------------------------------------------------------------------
        // Part 2: Using Inherited Methods
        // ------------------------------------------------------------------
        System.out.println();
        System.out.println("[Part 2: Using Inherited Methods]");
        System.out.println();

        System.out.println("  Methods from Animal class (grandparent):");
        buddy.eat();       // Inherited from Animal
        buddy.sleep();     // Inherited from Animal

        System.out.println();
        System.out.println("  Methods from Dog class (parent):");
        buddy.bark();      // Inherited from Dog
        buddy.fetch("ball");  // Inherited from Dog

        System.out.println();
        System.out.println("  Methods from GoldenRetriever class (own):");
        buddy.swim();      // Own method
        buddy.certifyAsServiceDog();

        System.out.println();
        System.out.println("  Updated info after changes:");
        buddy.displayInfo();

        // ------------------------------------------------------------------
        // Part 3: Field Inheritance
        // ------------------------------------------------------------------
        System.out.println();
        System.out.println("[Part 3: Field Inheritance]");
        System.out.println();

        System.out.println("  GoldenRetriever can access fields from all levels:");
        System.out.println("    From Animal:            name = " + buddy.name);
        System.out.println("    From Animal:            age = " + buddy.age);
        System.out.println("    From Animal:            species = " + buddy.species);
        System.out.println("    From Dog:               breed = " + buddy.breed);
        System.out.println("    From Dog:               isTrained = " + buddy.isTrained);
        System.out.println("    From GoldenRetriever:   isServiceDog = " + buddy.isServiceDog);

        // ------------------------------------------------------------------
        // Part 4: Sibling Classes
        // ------------------------------------------------------------------
        System.out.println();
        System.out.println("[Part 4: Sibling Classes (Car and Motorcycle)]");
        System.out.println();

        Car car = new Car("Toyota", "Camry", 2024, 4);
        Motorcycle bike = new Motorcycle("Honda", "CBR", 2024);

        // Both share Vehicle methods
        car.accelerate(60);
        bike.accelerate(80);

        car.brake(20);
        bike.brake(30);

        // Each has its own specific methods
        car.loadPassengers(4);
        bike.wheelie();

        System.out.println();
        System.out.println("  Car has " + car.doors + " doors (Car-specific field)");
        System.out.println("  Motorcycle has sidecar: " + bike.hasSidecar + " (Motorcycle-specific)");

        // ------------------------------------------------------------------
        // Part 5: What is NOT Inherited
        // ------------------------------------------------------------------
        System.out.println();
        System.out.println("[Part 5: What is NOT Inherited]");
        System.out.println();
        System.out.println("  1. Private members      - not directly accessible in child");
        System.out.println("  2. Constructors          - not inherited (but called via super())");
        System.out.println("  3. Static methods        - belong to the class, not instances");
        System.out.println("  4. Final methods         - inherited but cannot be overridden");
    }
}
