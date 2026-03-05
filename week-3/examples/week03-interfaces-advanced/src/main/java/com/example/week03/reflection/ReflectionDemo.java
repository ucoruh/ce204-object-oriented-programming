package com.example.week03.reflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;

/**
 * ============================================================================
 * DEMO 9: Reflection
 * ============================================================================
 *
 * REFLECTION is Java's ability to inspect and manipulate classes, methods,
 * fields, and constructors at RUNTIME. It is provided by the java.lang.reflect
 * package.
 *
 * With reflection you can:
 *   1. Inspect a class's structure (fields, methods, constructors, interfaces).
 *   2. Create instances of classes dynamically.
 *   3. Invoke methods by name at runtime.
 *   4. Access and modify fields (even private ones!).
 *   5. Get information about annotations.
 *
 * When is reflection used?
 *   - Frameworks (Spring, Hibernate, JUnit) use it heavily.
 *   - Serialization libraries (Jackson, Gson).
 *   - Dependency injection containers.
 *   - Testing frameworks.
 *   - Plugin systems.
 *
 * Caution:
 *   - Reflection is SLOWER than direct method calls.
 *   - It can break encapsulation (accessing private members).
 *   - Use it sparingly; prefer compile-time type safety when possible.
 *
 * ============================================================================
 */
public class ReflectionDemo {

    // =======================================================================
    // A sample class to inspect with reflection
    // =======================================================================

    /**
     * A simple Person class that we will inspect using reflection.
     */
    static class Person {
        // Fields with different access modifiers
        public String name;
        private int age;
        protected String email;
        String city;  // package-private

        // Static field
        public static int instanceCount = 0;

        // Constructors
        public Person() {
            this("Unknown", 0);
        }

        public Person(String name, int age) {
            this.name = name;
            this.age = age;
            instanceCount++;
        }

        // Methods
        public String getName() { return name; }
        public int getAge() { return age; }

        public void setAge(int age) {
            this.age = age;
        }

        private String getSecret() {
            return "Secret info for " + name;
        }

        public String greet(String greeting) {
            return greeting + ", " + name + "!";
        }

        protected double calculateScore(double base, double multiplier) {
            return base * multiplier + age;
        }

        @Override
        public String toString() {
            return "Person[name=" + name + ", age=" + age + "]";
        }
    }

    /**
     * An interface for reflection inspection.
     */
    interface Identifiable {
        String getId();
    }

    /**
     * A subclass of Person implementing an interface.
     */
    static class Employee extends Person implements Identifiable, java.io.Serializable {
        private String employeeId;
        private String department;

        public Employee(String name, int age, String employeeId, String department) {
            super(name, age);
            this.employeeId = employeeId;
            this.department = department;
        }

        @Override
        public String getId() { return employeeId; }

        public String getDepartment() { return department; }
    }

    // =======================================================================
    // DEMO METHOD
    // =======================================================================

    public static void demo() {
        // --- Section A: Getting Class information ---
        System.out.println("  [A] Getting Class Information");
        System.out.println("  ------------------------------");

        // Three ways to get a Class object
        // Way 1: Using .class literal
        Class<Person> clazz1 = Person.class;

        // Way 2: Using getClass() on an instance
        Person person = new Person("Alice", 30);
        Class<?> clazz2 = person.getClass();

        // Way 3: Using Class.forName() with fully qualified name
        try {
            Class<?> clazz3 = Class.forName("com.example.week03.reflection.ReflectionDemo$Person");
            System.out.println("    Class name: " + clazz3.getName());
            System.out.println("    Simple name: " + clazz3.getSimpleName());
            System.out.println("    Package: " + clazz3.getPackageName());
            System.out.println("    Superclass: " + clazz3.getSuperclass().getSimpleName());
            System.out.println("    Is interface? " + clazz3.isInterface());
            System.out.println("    Is enum? " + clazz3.isEnum());
        } catch (ClassNotFoundException e) {
            System.out.println("    Class not found: " + e.getMessage());
        }
        System.out.println();

        // --- Section B: Inspecting Fields ---
        System.out.println("  [B] Inspecting Fields");
        System.out.println("  -----------------------");

        Field[] fields = Person.class.getDeclaredFields();
        System.out.println("    All declared fields of Person:");
        for (Field field : fields) {
            String modifiers = Modifier.toString(field.getModifiers());
            System.out.printf("      %-20s %-12s %s%n",
                    modifiers.isEmpty() ? "(package)" : modifiers,
                    field.getType().getSimpleName(),
                    field.getName());
        }

        // getFields() returns only PUBLIC fields (including inherited)
        Field[] publicFields = Person.class.getFields();
        System.out.println("    Public fields (including inherited): "
                + Arrays.toString(Arrays.stream(publicFields).map(Field::getName).toArray()));
        System.out.println();

        // --- Section C: Inspecting Methods ---
        System.out.println("  [C] Inspecting Methods");
        System.out.println("  ------------------------");

        Method[] methods = Person.class.getDeclaredMethods();
        System.out.println("    All declared methods of Person:");
        for (Method method : methods) {
            String modifiers = Modifier.toString(method.getModifiers());
            String params = Arrays.stream(method.getParameterTypes())
                    .map(Class::getSimpleName)
                    .reduce((a, b) -> a + ", " + b)
                    .orElse("");
            System.out.printf("      %-12s %-10s %s(%s)%n",
                    modifiers,
                    method.getReturnType().getSimpleName(),
                    method.getName(),
                    params);
        }
        System.out.println();

        // --- Section D: Inspecting Constructors ---
        System.out.println("  [D] Inspecting Constructors");
        System.out.println("  ----------------------------");

        Constructor<?>[] constructors = Person.class.getDeclaredConstructors();
        System.out.println("    Constructors of Person:");
        for (Constructor<?> ctor : constructors) {
            String params = Arrays.stream(ctor.getParameterTypes())
                    .map(Class::getSimpleName)
                    .reduce((a, b) -> a + ", " + b)
                    .orElse("(none)");
            System.out.println("      Person(" + params + ")");
        }
        System.out.println();

        // --- Section E: Creating instances dynamically ---
        System.out.println("  [E] Creating Instances Dynamically");
        System.out.println("  ------------------------------------");

        try {
            // Using no-arg constructor
            Constructor<Person> noArgCtor = Person.class.getDeclaredConstructor();
            Person p1 = noArgCtor.newInstance();
            System.out.println("    Created with no-arg constructor: " + p1);

            // Using parameterized constructor
            Constructor<Person> paramCtor = Person.class.getDeclaredConstructor(String.class, int.class);
            Person p2 = paramCtor.newInstance("Bob", 25);
            System.out.println("    Created with (String, int) constructor: " + p2);
        } catch (Exception e) {
            System.out.println("    Error: " + e.getMessage());
        }
        System.out.println();

        // --- Section F: Invoking methods dynamically ---
        System.out.println("  [F] Invoking Methods Dynamically");
        System.out.println("  ----------------------------------");

        try {
            Person p = new Person("Charlie", 28);

            // Invoke a public method with parameters
            Method greetMethod = Person.class.getDeclaredMethod("greet", String.class);
            String result = (String) greetMethod.invoke(p, "Hello");
            System.out.println("    greet('Hello'): " + result);

            // Invoke a method with no parameters
            Method getNameMethod = Person.class.getDeclaredMethod("getName");
            String name = (String) getNameMethod.invoke(p);
            System.out.println("    getName(): " + name);

            // Invoke a PRIVATE method (requires setAccessible)
            Method secretMethod = Person.class.getDeclaredMethod("getSecret");
            secretMethod.setAccessible(true); // Bypass access control!
            String secret = (String) secretMethod.invoke(p);
            System.out.println("    getSecret() [private]: " + secret);

            // Invoke a method with multiple parameters
            Method scoreMethod = Person.class.getDeclaredMethod(
                    "calculateScore", double.class, double.class);
            scoreMethod.setAccessible(true);
            double score = (double) scoreMethod.invoke(p, 100.0, 1.5);
            System.out.println("    calculateScore(100.0, 1.5): " + score);
        } catch (Exception e) {
            System.out.println("    Error: " + e.getMessage());
        }
        System.out.println();

        // --- Section G: Accessing and modifying fields ---
        System.out.println("  [G] Accessing and Modifying Fields");
        System.out.println("  ------------------------------------");

        try {
            Person p = new Person("Diana", 35);
            System.out.println("    Original: " + p);

            // Access a public field
            Field nameField = Person.class.getDeclaredField("name");
            System.out.println("    name field value: " + nameField.get(p));

            // Modify a public field
            nameField.set(p, "Diana Updated");
            System.out.println("    After setting name: " + p);

            // Access a PRIVATE field (requires setAccessible)
            Field ageField = Person.class.getDeclaredField("age");
            ageField.setAccessible(true); // Bypass private access
            System.out.println("    age field value [private]: " + ageField.get(p));

            // Modify a private field
            ageField.set(p, 36);
            System.out.println("    After setting age to 36: " + p);

            // Access a static field
            Field countField = Person.class.getDeclaredField("instanceCount");
            System.out.println("    instanceCount [static]: " + countField.get(null));
        } catch (Exception e) {
            System.out.println("    Error: " + e.getMessage());
        }
        System.out.println();

        // --- Section H: Inspecting class hierarchy and interfaces ---
        System.out.println("  [H] Class Hierarchy and Interfaces");
        System.out.println("  ------------------------------------");

        Class<?> empClass = Employee.class;
        System.out.println("    Employee class:");
        System.out.println("      Superclass: " + empClass.getSuperclass().getSimpleName());

        Class<?>[] interfaces = empClass.getInterfaces();
        System.out.print("      Interfaces: ");
        for (Class<?> iface : interfaces) {
            System.out.print(iface.getSimpleName() + " ");
        }
        System.out.println();

        // Check if an object is an instance of a class/interface
        Employee emp = new Employee("Eve", 29, "E001", "Engineering");
        System.out.println("      emp instanceof Person? " + (emp instanceof Person));
        System.out.println("      emp instanceof Identifiable? " + (emp instanceof Identifiable));
        System.out.println("      emp instanceof java.io.Serializable? "
                + (emp instanceof java.io.Serializable));
        System.out.println();

        // --- Section I: Practical use - simple object inspector ---
        System.out.println("  [I] Practical: Simple Object Inspector");
        System.out.println("  ----------------------------------------");

        inspectObject(new Person("Frank", 40));
        System.out.println();
        inspectObject(new Employee("Grace", 32, "E002", "Marketing"));
    }

    /**
     * A practical utility method that inspects any object using reflection.
     * This is similar to what debugging tools and serialization libraries do.
     */
    static void inspectObject(Object obj) {
        Class<?> clazz = obj.getClass();
        System.out.println("    Inspecting: " + clazz.getSimpleName());

        // Get all declared fields (including private)
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            try {
                Object value = field.get(obj);
                System.out.printf("      %-15s = %s%n", field.getName(), value);
            } catch (IllegalAccessException e) {
                System.out.printf("      %-15s = (inaccessible)%n", field.getName());
            }
        }
    }
}
