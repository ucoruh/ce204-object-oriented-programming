package com.example.week04.classdiagram;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * CEN206 - Week 4: Association Relationships
 *
 * In UML a plain line between two classes is an <b>association</b>.
 * It means "knows about" -- one object holds a reference to another.
 *
 * <pre>
 * ┌──────────┐          ┌──────────┐
 * │  Teacher  │ 1    * │  Student  │      (1-to-many)
 * └──────────┘──────────└──────────┘
 *
 * ┌────────────┐  1    * ┌────────────┐
 * │ Department │─────────│  Employee  │   (1-to-many)
 * └────────────┘         └────────────┘
 * </pre>
 */
public class Association {

    // ----------------------------------------------------------------
    // 1-to-1 association:  Teacher  ────  Classroom
    // A teacher is assigned to exactly one classroom, and vice-versa.
    // ----------------------------------------------------------------

    /** Represents a physical classroom. */
    public static class Classroom {
        private final String roomNumber;

        public Classroom(String roomNumber) {
            this.roomNumber = roomNumber;
        }

        public String getRoomNumber() {
            return roomNumber;
        }

        @Override
        public String toString() {
            return "Classroom(" + roomNumber + ")";
        }
    }

    /**
     * A Teacher has a 1-to-1 association with a Classroom
     * and a 1-to-many association with Students.
     */
    public static class Teacher {
        private final String name;
        private Classroom classroom;                       // 1-to-1
        private final List<Student> students = new ArrayList<>();  // 1-to-many

        public Teacher(String name) {
            this.name = name;
        }

        // --- 1-to-1: classroom ---
        public void assignClassroom(Classroom classroom) {
            this.classroom = classroom;
        }

        public Classroom getClassroom() {
            return classroom;
        }

        // --- 1-to-many: students ---
        public void addStudent(Student student) {
            if (!students.contains(student)) {
                students.add(student);
            }
        }

        public List<Student> getStudents() {
            return Collections.unmodifiableList(students);
        }

        public String getName() {
            return name;
        }

        @Override
        public String toString() {
            return "Teacher(" + name + ")";
        }
    }

    /** A Student is associated with a Teacher. */
    public static class Student {
        private final String name;
        private final String studentId;

        public Student(String name, String studentId) {
            this.name = name;
            this.studentId = studentId;
        }

        public String getName() {
            return name;
        }

        public String getStudentId() {
            return studentId;
        }

        @Override
        public String toString() {
            return "Student(" + name + ", " + studentId + ")";
        }
    }

    // ----------------------------------------------------------------
    // 1-to-many association:  Department  ────  Employee
    // A department has many employees; an employee belongs to one dept.
    // ----------------------------------------------------------------

    /** A Department holds references to its Employees. */
    public static class Department {
        private final String name;
        private final List<Employee> employees = new ArrayList<>();

        public Department(String name) {
            this.name = name;
        }

        public void addEmployee(Employee employee) {
            if (!employees.contains(employee)) {
                employees.add(employee);
                employee.setDepartment(this);  // maintain bidirectional link
            }
        }

        public List<Employee> getEmployees() {
            return Collections.unmodifiableList(employees);
        }

        public String getName() {
            return name;
        }

        @Override
        public String toString() {
            return "Department(" + name + ")";
        }
    }

    /** An Employee belongs to one Department. */
    public static class Employee {
        private final String name;
        private Department department; // back-reference (bidirectional)

        public Employee(String name) {
            this.name = name;
        }

        void setDepartment(Department department) {
            this.department = department;
        }

        public Department getDepartment() {
            return department;
        }

        public String getName() {
            return name;
        }

        @Override
        public String toString() {
            return "Employee(" + name + ")";
        }
    }

    // ----------------------------------------------------------------
    // Demo
    // ----------------------------------------------------------------

    /** Runs the demonstration. */
    public static void demo() {
        // 1-to-1: Teacher <-> Classroom
        Teacher teacher = new Teacher("Dr. Smith");
        Classroom room = new Classroom("B-201");
        teacher.assignClassroom(room);

        System.out.println(teacher + " is assigned to " + teacher.getClassroom());

        // 1-to-many: Teacher -> Students
        Student s1 = new Student("Alice", "2024001");
        Student s2 = new Student("Bob", "2024002");
        Student s3 = new Student("Carol", "2024003");

        teacher.addStudent(s1);
        teacher.addStudent(s2);
        teacher.addStudent(s3);

        System.out.println(teacher + " teaches " + teacher.getStudents().size() + " students:");
        for (Student s : teacher.getStudents()) {
            System.out.println("  - " + s);
        }

        // 1-to-many: Department -> Employees
        Department engineering = new Department("Engineering");
        Employee e1 = new Employee("Diana");
        Employee e2 = new Employee("Edward");
        engineering.addEmployee(e1);
        engineering.addEmployee(e2);

        System.out.println("\n" + engineering + " has employees:");
        for (Employee e : engineering.getEmployees()) {
            System.out.println("  - " + e + " (dept: " + e.getDepartment() + ")");
        }
    }
}
