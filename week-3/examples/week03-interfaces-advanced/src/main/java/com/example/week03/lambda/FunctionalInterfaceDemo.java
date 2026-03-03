package com.example.week03.lambda;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * ============================================================================
 * DEMO 12: Functional Interfaces and Chaining
 * ============================================================================
 *
 * A FUNCTIONAL INTERFACE is an interface with exactly ONE abstract method.
 * It can have any number of default or static methods.
 *
 * The @FunctionalInterface annotation:
 *   - Is OPTIONAL but recommended.
 *   - Tells the compiler to enforce the single-abstract-method rule.
 *   - If you accidentally add a second abstract method, the compiler reports an error.
 *
 * Functional interfaces are the TARGET TYPES for lambda expressions and
 * method references.
 *
 * This demo covers:
 *   1. Creating custom functional interfaces with @FunctionalInterface
 *   2. Generic functional interfaces
 *   3. Chaining and composing functional interfaces
 *   4. Higher-order functions (functions that take/return functions)
 *   5. Practical pipeline processing patterns
 *
 * ============================================================================
 */
public class FunctionalInterfaceDemo {

    // =======================================================================
    // PART 1: Custom Functional Interfaces
    // =======================================================================

    /**
     * A simple custom functional interface.
     * The @FunctionalInterface annotation ensures it has exactly one abstract method.
     */
    @FunctionalInterface
    interface Greeting {
        String greet(String name);

        // Default methods are allowed in functional interfaces
        default String greetFormal(String name) {
            return "Dear " + greet(name);
        }

        // Static methods are also allowed
        static Greeting withPrefix(String prefix) {
            return name -> prefix + " " + name;
        }
    }

    /**
     * A functional interface that takes two parameters.
     */
    @FunctionalInterface
    interface BiConverter<A, B, R> {
        R convert(A first, B second);
    }

    /**
     * A functional interface for validation with a reason.
     */
    @FunctionalInterface
    interface Validator<T> {
        ValidationResult validate(T value);
    }

    /**
     * A simple validation result record-like class.
     */
    static class ValidationResult {
        final boolean valid;
        final String message;

        ValidationResult(boolean valid, String message) {
            this.valid = valid;
            this.message = message;
        }

        @Override
        public String toString() {
            return valid ? "VALID" : "INVALID: " + message;
        }
    }

    /**
     * A functional interface for transforming data with error handling.
     */
    @FunctionalInterface
    interface SafeTransformer<T, R> {
        R transform(T input) throws Exception;

        // Default method that wraps the transform with error handling
        default Function<T, R> withDefault(R defaultValue) {
            return input -> {
                try {
                    return transform(input);
                } catch (Exception e) {
                    return defaultValue;
                }
            };
        }
    }

    // =======================================================================
    // PART 2: Chaining and Composition
    // =======================================================================

    /**
     * A functional interface for text processing, designed for chaining.
     */
    @FunctionalInterface
    interface TextProcessor {
        String process(String input);

        // Chain this processor with another (this runs first, then other)
        default TextProcessor andThen(TextProcessor other) {
            return input -> other.process(this.process(input));
        }

        // Chain another processor before this one
        default TextProcessor compose(TextProcessor before) {
            return input -> this.process(before.process(input));
        }

        // Static factory: identity processor (no-op)
        static TextProcessor identity() {
            return input -> input;
        }

        // Static factory: combine multiple processors
        static TextProcessor pipeline(TextProcessor... processors) {
            TextProcessor combined = identity();
            for (TextProcessor p : processors) {
                combined = combined.andThen(p);
            }
            return combined;
        }
    }

    // =======================================================================
    // PART 3: Higher-Order Functions
    // =======================================================================

    /**
     * A higher-order function that creates a repeater function.
     * It takes an int and returns a Function that repeats strings.
     */
    static Function<String, String> createRepeater(int times) {
        return s -> {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < times; i++) {
                if (i > 0) sb.append(" ");
                sb.append(s);
            }
            return sb.toString();
        };
    }

    /**
     * A higher-order function that creates a filter-and-transform pipeline.
     */
    static <T, R> Function<List<T>, List<R>> createPipeline(
            Predicate<T> filter, Function<T, R> transform) {
        return list -> list.stream()
                .filter(filter)
                .map(transform)
                .collect(Collectors.toList());
    }

    /**
     * A method that takes a Function as parameter and applies it.
     */
    static <T, R> List<R> applyToAll(List<T> items, Function<T, R> fn) {
        List<R> result = new ArrayList<>();
        for (T item : items) {
            result.add(fn.apply(item));
        }
        return result;
    }

    // =======================================================================
    // PART 4: Practical Data Processing
    // =======================================================================

    static class Student {
        String name;
        int grade;
        String department;

        Student(String name, int grade, String department) {
            this.name = name;
            this.grade = grade;
            this.department = department;
        }

        @Override
        public String toString() {
            return name + "(" + grade + ", " + department + ")";
        }
    }

    // =======================================================================
    // DEMO METHOD
    // =======================================================================

    public static void demo() {
        // --- Section A: Custom functional interfaces ---
        System.out.println("  [A] Custom @FunctionalInterface");
        System.out.println("  ---------------------------------");

        // Using the custom Greeting functional interface
        Greeting hello = name -> "Hello, " + name + "!";
        Greeting goodbye = name -> "Goodbye, " + name + "!";

        System.out.println("    " + hello.greet("Alice"));
        System.out.println("    " + goodbye.greet("Bob"));

        // Using default method on functional interface
        System.out.println("    " + hello.greetFormal("Professor"));

        // Using static factory method
        Greeting mrGreeting = Greeting.withPrefix("Mr.");
        System.out.println("    " + mrGreeting.greet("Smith"));
        System.out.println();

        // --- Section B: Generic functional interface ---
        System.out.println("  [B] Generic Functional Interface (BiConverter)");
        System.out.println("  ------------------------------------------------");

        BiConverter<String, Integer, String> repeat =
                (s, n) -> s.repeat(n);
        BiConverter<Double, Double, Double> power =
                Math::pow;
        BiConverter<String, String, String> join =
                (a, b) -> a + " + " + b;

        System.out.println("    repeat('Ha', 3): " + repeat.convert("Ha", 3));
        System.out.println("    power(2.0, 10.0): " + power.convert(2.0, 10.0));
        System.out.println("    join('Java', 'Lambdas'): " + join.convert("Java", "Lambdas"));
        System.out.println();

        // --- Section C: Validator with result ---
        System.out.println("  [C] Validator Functional Interface");
        System.out.println("  ------------------------------------");

        Validator<String> emailValidator = email -> {
            if (email == null || email.isEmpty()) {
                return new ValidationResult(false, "Email is empty");
            }
            if (!email.contains("@")) {
                return new ValidationResult(false, "Email must contain @");
            }
            if (!email.contains(".")) {
                return new ValidationResult(false, "Email must contain a domain");
            }
            return new ValidationResult(true, "OK");
        };

        Validator<Integer> ageValidator = age -> {
            if (age < 0) return new ValidationResult(false, "Age cannot be negative");
            if (age > 150) return new ValidationResult(false, "Age is unrealistic");
            return new ValidationResult(true, "OK");
        };

        System.out.println("    email 'user@mail.com': " + emailValidator.validate("user@mail.com"));
        System.out.println("    email 'invalid': " + emailValidator.validate("invalid"));
        System.out.println("    email '': " + emailValidator.validate(""));
        System.out.println("    age 25: " + ageValidator.validate(25));
        System.out.println("    age -5: " + ageValidator.validate(-5));
        System.out.println("    age 200: " + ageValidator.validate(200));
        System.out.println();

        // --- Section D: SafeTransformer with error handling ---
        System.out.println("  [D] SafeTransformer (Error Handling in Functional Style)");
        System.out.println("  ----------------------------------------------------------");

        SafeTransformer<String, Integer> safeParseInt = Integer::parseInt;
        Function<String, Integer> safeParse = safeParseInt.withDefault(-1);

        System.out.println("    safeParse('42'): " + safeParse.apply("42"));
        System.out.println("    safeParse('abc'): " + safeParse.apply("abc"));
        System.out.println("    safeParse('999'): " + safeParse.apply("999"));
        System.out.println();

        // --- Section E: TextProcessor chaining ---
        System.out.println("  [E] TextProcessor Chaining (Composition)");
        System.out.println("  -------------------------------------------");

        TextProcessor trim = String::trim;
        TextProcessor upper = String::toUpperCase;
        TextProcessor addBrackets = s -> "[" + s + "]";
        TextProcessor addExclaim = s -> s + "!";

        // Chain processors: trim -> uppercase -> brackets -> exclaim
        TextProcessor pipeline = trim
                .andThen(upper)
                .andThen(addBrackets)
                .andThen(addExclaim);

        String result = pipeline.process("  hello world  ");
        System.out.println("    Input: '  hello world  '");
        System.out.println("    Pipeline (trim -> upper -> brackets -> exclaim): " + result);

        // Using the static pipeline factory
        TextProcessor pipeline2 = TextProcessor.pipeline(
                trim,
                s -> s.replace(" ", "_"),
                upper,
                s -> "PREFIX_" + s
        );
        System.out.println("    Pipeline2 result: " + pipeline2.process("  hello world  "));
        System.out.println();

        // --- Section F: Higher-order functions ---
        System.out.println("  [F] Higher-Order Functions");
        System.out.println("  ---------------------------");

        // Function that creates functions
        Function<String, String> repeat3 = createRepeater(3);
        Function<String, String> repeat5 = createRepeater(5);
        System.out.println("    repeat3('Ha'): " + repeat3.apply("Ha"));
        System.out.println("    repeat5('Go'): " + repeat5.apply("Go"));

        // applyToAll - takes a function as parameter
        List<String> words = Arrays.asList("hello", "world", "java", "lambda");
        List<String> upperedWords = applyToAll(words, String::toUpperCase);
        List<Integer> lengths = applyToAll(words, String::length);
        System.out.println("    Words: " + words);
        System.out.println("    Upper: " + upperedWords);
        System.out.println("    Lengths: " + lengths);

        // Pipeline creator - takes predicate + function, returns a function
        Function<List<Integer>, List<String>> numberPipeline =
                createPipeline(
                        n -> n > 0,                        // filter: positive only
                        n -> "+" + n                       // transform: add prefix
                );
        List<Integer> mixedNumbers = Arrays.asList(-3, -1, 0, 1, 5, -2, 8);
        System.out.println("    Input: " + mixedNumbers);
        System.out.println("    Pipeline (filter positive, add '+'): " + numberPipeline.apply(mixedNumbers));
        System.out.println();

        // --- Section G: Predicate chaining ---
        System.out.println("  [G] Advanced Predicate Chaining");
        System.out.println("  ---------------------------------");

        Predicate<String> notEmpty = s -> !s.isEmpty();
        Predicate<String> startsWithA = s -> s.startsWith("A");
        Predicate<String> endsWithE = s -> s.endsWith("e");
        Predicate<String> lengthOver3 = s -> s.length() > 3;

        // Complex predicate: not empty AND (starts with A OR ends with e) AND length > 3
        Predicate<String> complexFilter = notEmpty
                .and(startsWithA.or(endsWithE))
                .and(lengthOver3);

        List<String> testWords = Arrays.asList(
                "Alice", "Bob", "Apple", "Eve", "Andre", "Jane", "Abe", "Charlotte");
        System.out.println("    Words: " + testWords);
        System.out.println("    Filter: notEmpty AND (startsWithA OR endsWithE) AND length > 3");
        List<String> filtered = testWords.stream()
                .filter(complexFilter)
                .collect(Collectors.toList());
        System.out.println("    Result: " + filtered);
        System.out.println();

        // --- Section H: Function chaining ---
        System.out.println("  [H] Advanced Function Chaining");
        System.out.println("  --------------------------------");

        Function<Integer, Integer> doubleIt = n -> n * 2;
        Function<Integer, Integer> addTen = n -> n + 10;
        Function<Integer, String> toStr = n -> "Result: " + n;

        // andThen: doubleIt -> addTen -> toStr
        Function<Integer, String> chain1 = doubleIt.andThen(addTen).andThen(toStr);
        System.out.println("    chain1(5) = double -> addTen -> toStr = " + chain1.apply(5));

        // compose: toStr <- addTen <- doubleIt (reverse order)
        Function<Integer, String> chain2 = toStr.compose(addTen).compose(doubleIt);
        System.out.println("    chain2(5) = same result via compose = " + chain2.apply(5));
        System.out.println();

        // --- Section I: Consumer chaining ---
        System.out.println("  [I] Consumer Chaining");
        System.out.println("  -----------------------");

        Consumer<String> log = s -> System.out.println("    [LOG] " + s);
        Consumer<String> save = s -> System.out.println("    [SAVE] " + s);
        Consumer<String> notify = s -> System.out.println("    [NOTIFY] " + s);

        // Chain consumers: log -> save -> notify
        Consumer<String> fullProcess = log.andThen(save).andThen(notify);
        fullProcess.accept("New user registered: Charlie");
        System.out.println();

        // --- Section J: Practical example with Student data ---
        System.out.println("  [J] Practical: Student Data Processing");
        System.out.println("  ----------------------------------------");

        List<Student> students = Arrays.asList(
                new Student("Alice", 92, "CS"),
                new Student("Bob", 78, "Math"),
                new Student("Charlie", 95, "CS"),
                new Student("Diana", 88, "Physics"),
                new Student("Eve", 71, "CS"),
                new Student("Frank", 85, "Math"),
                new Student("Grace", 97, "CS")
        );

        System.out.println("    All students: " + students);

        // Build a processing pipeline using functional interfaces
        Predicate<Student> isCS = s -> s.department.equals("CS");
        Predicate<Student> highGrade = s -> s.grade >= 90;
        Function<Student, String> toHonorRoll = s -> s.name + " (" + s.grade + ")";

        // CS students with grade >= 90
        List<String> csHonors = students.stream()
                .filter(isCS.and(highGrade))
                .map(toHonorRoll)
                .collect(Collectors.toList());
        System.out.println("    CS Honor Roll (grade >= 90): " + csHonors);

        // Average grade per department using lambdas
        System.out.println("    Average grades by department:");
        students.stream()
                .collect(Collectors.groupingBy(
                        s -> s.department,
                        Collectors.averagingInt(s -> s.grade)))
                .forEach((dept, avg) ->
                        System.out.printf("      %s: %.1f%n", dept, avg));

        // Top student using Comparator
        students.stream()
                .max((s1, s2) -> Integer.compare(s1.grade, s2.grade))
                .ifPresent(s ->
                        System.out.println("    Top student: " + s.name + " (" + s.grade + ")"));
    }
}
