package com.example.week04.classdiagram;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * CEN206 - Week 4: Aggregation Relationship
 *
 * Aggregation is a "has-a" relationship represented in UML by an
 * <b>open diamond</b> on the container side.
 *
 * Key point: the contained objects can exist independently of the
 * container.  If the University is dissolved, the Department objects
 * still make conceptual sense on their own.
 *
 * <pre>
 * ┌──────────────┐       ┌──────────────┐
 * │  University   │◇─────│  Department   │
 * └──────────────┘  1  * └──────────────┘
 *       (aggregation: open diamond)
 *
 * ┌──────────────┐       ┌──────────────┐
 * │  Department   │◇─────│  Professor    │
 * └──────────────┘  1  * └──────────────┘
 * </pre>
 */
public class Aggregation {

    // ----------------------------------------------------------------
    // Professor -- can exist without a Department
    // ----------------------------------------------------------------

    /** A Professor has an independent lifecycle. */
    public static class Professor {
        private final String name;
        private final String specialization;

        public Professor(String name, String specialization) {
            this.name = name;
            this.specialization = specialization;
        }

        public String getName() {
            return name;
        }

        public String getSpecialization() {
            return specialization;
        }

        @Override
        public String toString() {
            return "Professor(" + name + ", " + specialization + ")";
        }
    }

    // ----------------------------------------------------------------
    // Department -- aggregates Professors
    // ----------------------------------------------------------------

    /**
     * A Department aggregates Professors.
     * Professors are created externally and passed in;
     * they are not destroyed when the department is removed.
     */
    public static class Department {
        private final String name;
        private final List<Professor> professors = new ArrayList<>();

        public Department(String name) {
            this.name = name;
        }

        /** Add an externally-created professor (aggregation). */
        public void addProfessor(Professor professor) {
            if (!professors.contains(professor)) {
                professors.add(professor);
            }
        }

        public void removeProfessor(Professor professor) {
            professors.remove(professor);
        }

        public List<Professor> getProfessors() {
            return Collections.unmodifiableList(professors);
        }

        public String getName() {
            return name;
        }

        @Override
        public String toString() {
            return "Department(" + name + ")";
        }
    }

    // ----------------------------------------------------------------
    // University -- aggregates Departments
    // ----------------------------------------------------------------

    /**
     * A University aggregates Departments.
     * Departments are created externally and can outlive the University.
     */
    public static class University {
        private final String name;
        private final List<Department> departments = new ArrayList<>();

        public University(String name) {
            this.name = name;
        }

        /** Add an externally-created department (aggregation). */
        public void addDepartment(Department department) {
            if (!departments.contains(department)) {
                departments.add(department);
            }
        }

        public void removeDepartment(Department department) {
            departments.remove(department);
        }

        public List<Department> getDepartments() {
            return Collections.unmodifiableList(departments);
        }

        public String getName() {
            return name;
        }

        @Override
        public String toString() {
            return "University(" + name + ")";
        }
    }

    // ----------------------------------------------------------------
    // Demo
    // ----------------------------------------------------------------

    /** Runs the aggregation demonstration. */
    public static void demo() {
        // Professors are created independently
        Professor p1 = new Professor("Dr. Aydin", "Software Engineering");
        Professor p2 = new Professor("Dr. Yilmaz", "Algorithms");
        Professor p3 = new Professor("Dr. Kaya", "Networking");

        // Departments are created independently
        Department cse = new Department("Computer Engineering");
        Department ee = new Department("Electrical Engineering");

        cse.addProfessor(p1);
        cse.addProfessor(p2);
        ee.addProfessor(p3);

        // University aggregates departments
        University uni = new University("Sakarya University");
        uni.addDepartment(cse);
        uni.addDepartment(ee);

        System.out.println(uni + " has the following departments:");
        for (Department dept : uni.getDepartments()) {
            System.out.println("  " + dept);
            for (Professor prof : dept.getProfessors()) {
                System.out.println("    - " + prof);
            }
        }

        // Demonstrate that parts survive removal from the whole
        uni.removeDepartment(cse);
        System.out.println("\nAfter removing " + cse + " from the university:");
        System.out.println("  University departments: " + uni.getDepartments());
        System.out.println("  The removed department still exists: " + cse);
        System.out.println("  Its professors are still accessible: " + cse.getProfessors());
    }
}
