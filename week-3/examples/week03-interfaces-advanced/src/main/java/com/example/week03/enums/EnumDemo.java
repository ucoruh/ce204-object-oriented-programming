package com.example.week03.enums;

/**
 * ============================================================================
 * DEMO 8: Enums
 * ============================================================================
 *
 * An ENUM (enumeration) in Java is a special class type that represents a
 * fixed set of constants. Enums were introduced in Java 5.
 *
 * Key features of Java enums:
 *   1. Each enum constant is an instance of the enum type.
 *   2. Enums can have fields, methods, and constructors.
 *   3. Enum constructors are always private (implicitly or explicitly).
 *   4. Enums can implement interfaces.
 *   5. Enums cannot extend other classes (they implicitly extend java.lang.Enum).
 *   6. Enums are inherently serializable and thread-safe (great for singletons).
 *   7. Built-in methods: values(), valueOf(), name(), ordinal().
 *
 * Enums are MUCH better than using integer constants or strings because:
 *   - Type safety: compiler catches invalid values.
 *   - Self-documenting: the code is clearer.
 *   - Can have behavior attached to each constant.
 *
 * ============================================================================
 */
public class EnumDemo {

    // =======================================================================
    // PART 1: Basic Enum
    // =======================================================================

    /**
     * A simple enum representing days of the week.
     * Each constant (MONDAY, TUESDAY, ...) is an instance of Day.
     */
    enum Day {
        MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY
    }

    // =======================================================================
    // PART 2: Enum with Fields, Constructor, and Methods
    // =======================================================================

    /**
     * A more advanced enum with fields and methods.
     * Each planet has a mass, radius, and can calculate surface gravity.
     */
    enum Planet {
        // Each constant calls the constructor with (mass, radius)
        MERCURY(3.303e+23, 2.4397e6),
        VENUS(4.869e+24, 6.0518e6),
        EARTH(5.976e+24, 6.37814e6),
        MARS(6.421e+23, 3.3972e6),
        JUPITER(1.9e+27, 7.1492e7),
        SATURN(5.688e+26, 6.0268e7),
        URANUS(8.686e+25, 2.5559e7),
        NEPTUNE(1.024e+26, 2.4746e7);

        // Fields
        private final double mass;    // in kilograms
        private final double radius;  // in meters

        // Gravitational constant
        static final double G = 6.67300E-11;

        // Constructor (always private for enums)
        Planet(double mass, double radius) {
            this.mass = mass;
            this.radius = radius;
        }

        // Methods
        double surfaceGravity() {
            return G * mass / (radius * radius);
        }

        double surfaceWeight(double otherMass) {
            return otherMass * surfaceGravity();
        }
    }

    // =======================================================================
    // PART 3: Enum with Abstract Methods
    // =======================================================================

    /**
     * Each enum constant provides its own implementation of calculate().
     * This is a powerful pattern for attaching different behavior to each constant.
     */
    enum Operation {
        ADD("+") {
            @Override
            public double calculate(double a, double b) { return a + b; }
        },
        SUBTRACT("-") {
            @Override
            public double calculate(double a, double b) { return a - b; }
        },
        MULTIPLY("*") {
            @Override
            public double calculate(double a, double b) { return a * b; }
        },
        DIVIDE("/") {
            @Override
            public double calculate(double a, double b) {
                if (b == 0) throw new ArithmeticException("Division by zero");
                return a / b;
            }
        };

        private final String symbol;

        Operation(String symbol) {
            this.symbol = symbol;
        }

        // Abstract method - each constant must implement this
        public abstract double calculate(double a, double b);

        public String getSymbol() { return symbol; }

        @Override
        public String toString() {
            return symbol;
        }
    }

    // =======================================================================
    // PART 4: Enum Implementing an Interface
    // =======================================================================

    /**
     * An interface that our enum will implement.
     */
    interface Describable {
        String getDescription();
        int getCode();
    }

    /**
     * HTTP status codes as an enum implementing an interface.
     */
    enum HttpStatus implements Describable {
        OK(200, "Success"),
        CREATED(201, "Resource Created"),
        BAD_REQUEST(400, "Bad Request"),
        UNAUTHORIZED(401, "Unauthorized"),
        NOT_FOUND(404, "Not Found"),
        INTERNAL_ERROR(500, "Internal Server Error");

        private final int code;
        private final String description;

        HttpStatus(int code, String description) {
            this.code = code;
            this.description = description;
        }

        // Implementing the Describable interface
        @Override
        public String getDescription() { return description; }

        @Override
        public int getCode() { return code; }

        // Custom method
        public boolean isSuccess() {
            return code >= 200 && code < 300;
        }

        public boolean isClientError() {
            return code >= 400 && code < 500;
        }

        public boolean isServerError() {
            return code >= 500 && code < 600;
        }

        // Static lookup method
        public static HttpStatus fromCode(int code) {
            for (HttpStatus status : values()) {
                if (status.code == code) return status;
            }
            throw new IllegalArgumentException("Unknown HTTP status code: " + code);
        }
    }

    // =======================================================================
    // PART 5: Enum in switch statements
    // =======================================================================

    /**
     * Seasons enum for switch statement demonstration.
     */
    enum Season {
        SPRING("March - May", "Warm"),
        SUMMER("June - August", "Hot"),
        AUTUMN("September - November", "Cool"),
        WINTER("December - February", "Cold");

        private final String months;
        private final String weather;

        Season(String months, String weather) {
            this.months = months;
            this.weather = weather;
        }

        public String getMonths() { return months; }
        public String getWeather() { return weather; }
    }

    static String getSeasonActivity(Season season) {
        // Traditional switch with enum
        switch (season) {
            case SPRING: return "Planting flowers";
            case SUMMER: return "Going to the beach";
            case AUTUMN: return "Raking leaves";
            case WINTER: return "Building snowmen";
            default: return "Unknown";
        }
    }

    // =======================================================================
    // PART 6: Enum as Singleton Pattern
    // =======================================================================

    /**
     * Using enum for the Singleton pattern.
     * This is considered the BEST way to implement Singleton in Java
     * (recommended by Joshua Bloch in "Effective Java").
     */
    enum DatabaseConnection {
        INSTANCE; // Only one instance ever exists

        private String url = "jdbc:mysql://localhost:3306/mydb";
        private boolean connected = false;

        public void connect() {
            if (!connected) {
                connected = true;
                System.out.println("    Connected to database: " + url);
            } else {
                System.out.println("    Already connected to: " + url);
            }
        }

        public void disconnect() {
            connected = false;
            System.out.println("    Disconnected from database.");
        }

        public boolean isConnected() { return connected; }
    }

    // =======================================================================
    // DEMO METHOD
    // =======================================================================

    public static void demo() {
        // --- Section A: Basic enum ---
        System.out.println("  [A] Basic Enum");
        System.out.println("  ---------------");

        Day today = Day.WEDNESDAY;
        System.out.println("    Today is: " + today);
        System.out.println("    Ordinal (position): " + today.ordinal());
        System.out.println("    Name: " + today.name());

        // Iterating over all enum values using values()
        System.out.print("    All days: ");
        for (Day d : Day.values()) {
            System.out.print(d + " ");
        }
        System.out.println();

        // Converting string to enum using valueOf()
        Day parsed = Day.valueOf("FRIDAY");
        System.out.println("    valueOf('FRIDAY'): " + parsed);

        // Comparing enums (use == for enums, not .equals())
        System.out.println("    today == WEDNESDAY? " + (today == Day.WEDNESDAY));
        System.out.println("    today == FRIDAY? " + (today == Day.FRIDAY));
        System.out.println();

        // --- Section B: Enum with fields and methods ---
        System.out.println("  [B] Enum with Fields, Constructor, and Methods");
        System.out.println("  ------------------------------------------------");

        double earthWeight = 75.0; // kg
        double mass = earthWeight / Planet.EARTH.surfaceGravity();

        System.out.println("    Your weight on different planets (Earth weight = " + earthWeight + " kg):");
        for (Planet p : Planet.values()) {
            System.out.printf("      %-8s: %.2f kg%n", p, p.surfaceWeight(mass));
        }
        System.out.println();

        // --- Section C: Enum with abstract methods ---
        System.out.println("  [C] Enum with Abstract Methods (Calculator)");
        System.out.println("  ----------------------------------------------");

        double a = 10.0, b = 3.0;
        for (Operation op : Operation.values()) {
            double result = op.calculate(a, b);
            System.out.printf("    %.1f %s %.1f = %.2f%n", a, op.getSymbol(), b, result);
        }
        System.out.println();

        // --- Section D: Enum implementing interface ---
        System.out.println("  [D] Enum Implementing an Interface");
        System.out.println("  ------------------------------------");

        for (HttpStatus status : HttpStatus.values()) {
            System.out.printf("    %d %s - %s (Success: %b)%n",
                    status.getCode(),
                    status.name(),
                    status.getDescription(),
                    status.isSuccess());
        }

        // Static lookup
        HttpStatus found = HttpStatus.fromCode(404);
        System.out.println("    Lookup code 404: " + found + " -> " + found.getDescription());

        // Using as Describable interface type
        Describable desc = HttpStatus.OK;
        System.out.println("    As Describable: code=" + desc.getCode()
                + ", description=" + desc.getDescription());
        System.out.println();

        // --- Section E: Enum in switch ---
        System.out.println("  [E] Enum in Switch Statements");
        System.out.println("  -------------------------------");

        for (Season s : Season.values()) {
            System.out.println("    " + s + " (" + s.getMonths() + ", " + s.getWeather()
                    + "): " + getSeasonActivity(s));
        }
        System.out.println();

        // --- Section F: Enum as Singleton ---
        System.out.println("  [F] Enum as Singleton Pattern");
        System.out.println("  -------------------------------");

        DatabaseConnection db1 = DatabaseConnection.INSTANCE;
        DatabaseConnection db2 = DatabaseConnection.INSTANCE;
        System.out.println("    Same instance? " + (db1 == db2)); // Always true

        db1.connect();
        db2.connect(); // Same instance, already connected
        System.out.println("    Is connected? " + db1.isConnected());
        db1.disconnect();
        System.out.println();

        // --- Section G: EnumSet and EnumMap (bonus) ---
        System.out.println("  [G] Bonus: EnumSet and EnumMap");
        System.out.println("  --------------------------------");

        // EnumSet - efficient Set implementation for enums
        java.util.EnumSet<Day> weekend = java.util.EnumSet.of(Day.SATURDAY, Day.SUNDAY);
        java.util.EnumSet<Day> weekdays = java.util.EnumSet.range(Day.MONDAY, Day.FRIDAY);
        System.out.println("    Weekend: " + weekend);
        System.out.println("    Weekdays: " + weekdays);

        // EnumMap - efficient Map with enum keys
        java.util.EnumMap<Season, String> holidays = new java.util.EnumMap<>(Season.class);
        holidays.put(Season.SPRING, "Easter");
        holidays.put(Season.SUMMER, "Independence Day");
        holidays.put(Season.AUTUMN, "Thanksgiving");
        holidays.put(Season.WINTER, "Christmas");
        System.out.println("    Season holidays: " + holidays);
    }
}
