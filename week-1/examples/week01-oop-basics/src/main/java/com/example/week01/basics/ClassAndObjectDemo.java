package com.example.week01.basics;

/**
 * ==========================================================================
 * DEMO 1: Classes and Objects
 * ==========================================================================
 *
 * A CLASS is a blueprint or template that defines:
 *   - Fields (attributes/properties) - the data an object holds
 *   - Methods (behaviors/operations) - what an object can do
 *
 * An OBJECT is a specific instance of a class, created using the 'new' keyword.
 * Each object has its own copy of instance fields.
 *
 * Key Concepts:
 *   - Class definition syntax
 *   - Object instantiation with 'new'
 *   - Instance fields vs local variables
 *   - Instance methods
 *   - The dot (.) operator for accessing members
 *   - Multiple objects from the same class
 */
public class ClassAndObjectDemo {

    // ======================================================================
    // Inner class: Car
    // This is our first class definition - a simple model of a car.
    // ======================================================================

    /**
     * The Car class represents a simple automobile.
     * It demonstrates how a class bundles data (fields) with behavior (methods).
     */
    static class Car {
        // ----- FIELDS (Instance Variables) -----
        // Each Car object will have its own copy of these fields.

        String brand;       // The manufacturer of the car
        String model;       // The specific model name
        int year;           // The year the car was manufactured
        String color;       // The color of the car
        double mileage;     // Current mileage in kilometers

        // ----- METHODS (Behaviors) -----
        // Methods define what a Car object can do.

        /**
         * Displays the full details of this car.
         * Notice how methods can access the object's fields directly.
         */
        void displayInfo() {
            System.out.println("  Car: " + year + " " + brand + " " + model);
            System.out.println("  Color: " + color);
            System.out.println("  Mileage: " + mileage + " km");
        }

        /**
         * Simulates driving the car for a given distance.
         * This method modifies the object's state (mileage field).
         *
         * @param distance the distance to drive in kilometers
         */
        void drive(double distance) {
            mileage += distance;  // Update the mileage
            System.out.println("  Drove " + distance + " km. New mileage: " + mileage + " km");
        }

        /**
         * Returns a short description of this car.
         *
         * @return a string describing the car
         */
        String getDescription() {
            return year + " " + brand + " " + model + " (" + color + ")";
        }
    }

    // ======================================================================
    // Inner class: Student
    // A second example to reinforce the concept with a different domain.
    // ======================================================================

    /**
     * The Student class models a university student.
     * It shows how classes can represent real-world entities.
     */
    static class Student {
        String name;
        String studentId;
        String department;
        double gpa;

        /**
         * Displays student information.
         */
        void displayInfo() {
            System.out.println("  Student: " + name + " (ID: " + studentId + ")");
            System.out.println("  Department: " + department);
            System.out.println("  GPA: " + gpa);
        }

        /**
         * Checks if the student is on the honor roll (GPA >= 3.5).
         *
         * @return true if the student has a GPA of 3.5 or higher
         */
        boolean isHonorRoll() {
            return gpa >= 3.5;
        }
    }

    // ======================================================================
    // Demo method
    // ======================================================================

    /**
     * Demonstrates class definition and object creation concepts.
     */
    public static void demo() {

        // ------------------------------------------------------------------
        // Part 1: Creating Objects
        // ------------------------------------------------------------------
        // The 'new' keyword allocates memory for a new object and returns
        // a reference to it. The reference is stored in a variable.
        System.out.println("[Part 1: Creating Objects]");
        System.out.println();

        // Create the first Car object
        Car car1 = new Car();
        car1.brand = "Toyota";
        car1.model = "Corolla";
        car1.year = 2023;
        car1.color = "Silver";
        car1.mileage = 15000;

        // Create the second Car object - different data, same structure
        Car car2 = new Car();
        car2.brand = "Honda";
        car2.model = "Civic";
        car2.year = 2024;
        car2.color = "Blue";
        car2.mileage = 5000;

        System.out.println("Car 1:");
        car1.displayInfo();
        System.out.println();

        System.out.println("Car 2:");
        car2.displayInfo();

        // ------------------------------------------------------------------
        // Part 2: Objects Are Independent
        // ------------------------------------------------------------------
        // Each object maintains its own state. Modifying one does not
        // affect the other, even though they are instances of the same class.
        System.out.println();
        System.out.println("[Part 2: Objects Are Independent]");
        System.out.println();

        System.out.println("Driving car1 for 100 km...");
        car1.drive(100);

        System.out.println("Driving car2 for 250 km...");
        car2.drive(250);

        System.out.println();
        System.out.println("After driving:");
        System.out.println("  Car 1 mileage: " + car1.mileage + " km");
        System.out.println("  Car 2 mileage: " + car2.mileage + " km");
        System.out.println("  (Notice each car's mileage changed independently)");

        // ------------------------------------------------------------------
        // Part 3: Methods that Return Values
        // ------------------------------------------------------------------
        System.out.println();
        System.out.println("[Part 3: Methods that Return Values]");
        System.out.println();

        String desc1 = car1.getDescription();
        String desc2 = car2.getDescription();
        System.out.println("  Car 1 description: " + desc1);
        System.out.println("  Car 2 description: " + desc2);

        // ------------------------------------------------------------------
        // Part 4: Reference Variables
        // ------------------------------------------------------------------
        // In Java, object variables hold REFERENCES (addresses), not the
        // objects themselves. Assigning one variable to another copies
        // the reference, not the object.
        System.out.println();
        System.out.println("[Part 4: Reference Variables]");
        System.out.println();

        Car car3 = car1;  // car3 now points to the SAME object as car1
        System.out.println("  car3 = car1 (both reference the same object)");
        System.out.println("  car3 description: " + car3.getDescription());

        car3.color = "Red";  // Modifying through car3 affects car1 too!
        System.out.println("  After car3.color = \"Red\":");
        System.out.println("  car1 color: " + car1.color + "  (also changed!)");
        System.out.println("  car3 color: " + car3.color);
        System.out.println("  (Both variables point to the same object in memory)");

        // ------------------------------------------------------------------
        // Part 5: Second Example - Student class
        // ------------------------------------------------------------------
        System.out.println();
        System.out.println("[Part 5: Student Class Example]");
        System.out.println();

        Student s1 = new Student();
        s1.name = "Alice Johnson";
        s1.studentId = "2024001";
        s1.department = "Computer Engineering";
        s1.gpa = 3.75;

        Student s2 = new Student();
        s2.name = "Bob Smith";
        s2.studentId = "2024002";
        s2.department = "Computer Engineering";
        s2.gpa = 3.20;

        s1.displayInfo();
        System.out.println("  Honor Roll: " + s1.isHonorRoll());
        System.out.println();

        s2.displayInfo();
        System.out.println("  Honor Roll: " + s2.isHonorRoll());

        // ------------------------------------------------------------------
        // Part 6: null References
        // ------------------------------------------------------------------
        System.out.println();
        System.out.println("[Part 6: null References]");
        System.out.println();

        Car car4 = null;  // car4 doesn't point to any object
        System.out.println("  car4 is null: " + (car4 == null));
        System.out.println("  (Calling methods on null would throw NullPointerException)");
        System.out.println("  Always check for null before using an object reference!");
    }
}
