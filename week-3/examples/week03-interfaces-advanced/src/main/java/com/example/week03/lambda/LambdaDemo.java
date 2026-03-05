package com.example.week03.lambda;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;
import java.util.function.BinaryOperator;

/**
 * ============================================================================
 * DEMO 11: Lambda Expressions
 * ============================================================================
 *
 * A LAMBDA EXPRESSION is a concise way to represent an anonymous function
 * (an implementation of a functional interface). Introduced in Java 8.
 *
 * Syntax:
 *   (parameters) -> expression
 *   (parameters) -> { statements; }
 *
 * Variations:
 *   () -> expression                          // no parameters
 *   x -> expression                           // single parameter (parens optional)
 *   (x) -> expression                         // single parameter (with parens)
 *   (x, y) -> expression                      // multiple parameters
 *   (int x, int y) -> expression              // explicit parameter types
 *   (x, y) -> { statement1; return expr; }    // block body
 *
 * Lambda expressions can ONLY be used with FUNCTIONAL INTERFACES
 * (interfaces with exactly ONE abstract method).
 *
 * Built-in functional interfaces in java.util.function:
 *   - Predicate<T>     : T -> boolean     (test a condition)
 *   - Function<T,R>    : T -> R           (transform input to output)
 *   - Consumer<T>      : T -> void        (perform action, no return)
 *   - Supplier<T>      : () -> T          (produce a value)
 *   - UnaryOperator<T> : T -> T           (same type in and out)
 *   - BinaryOperator<T>: (T, T) -> T      (combine two values)
 *   - Comparator<T>    : (T, T) -> int    (compare two values)
 *   - Runnable         : () -> void        (execute an action)
 *
 * ============================================================================
 */
public class LambdaDemo {

    // =======================================================================
    // DEMO METHOD
    // =======================================================================

    public static void demo() {
        // --- Section A: Lambda syntax variations ---
        System.out.println("  [A] Lambda Syntax Variations");
        System.out.println("  ------------------------------");

        // 1. No parameters
        Runnable noParams = () -> System.out.println("    Hello from a no-parameter lambda!");
        noParams.run();

        // 2. Single parameter (parentheses optional)
        Consumer<String> singleParam = name -> System.out.println("    Hello, " + name + "!");
        singleParam.accept("Lambda World");

        // 3. Multiple parameters
        Comparator<String> multipleParams = (a, b) -> a.length() - b.length();
        System.out.println("    Compare 'abc' vs 'de' by length: " + multipleParams.compare("abc", "de"));

        // 4. Explicit parameter types
        BinaryOperator<Integer> explicitTypes = (Integer a, Integer b) -> a + b;
        System.out.println("    Sum with explicit types: " + explicitTypes.apply(10, 20));

        // 5. Block body (multiple statements)
        Function<String, String> blockBody = (input) -> {
            String upper = input.toUpperCase();
            String trimmed = upper.trim();
            return "[" + trimmed + "]";
        };
        System.out.println("    Block body result: " + blockBody.apply("  hello  "));
        System.out.println();

        // --- Section B: Runnable ---
        System.out.println("  [B] Runnable (Lambda vs Anonymous Class)");
        System.out.println("  ------------------------------------------");

        // Old way: anonymous class
        Runnable oldWay = new Runnable() {
            @Override
            public void run() {
                System.out.println("    Old way (anonymous class): Running!");
            }
        };

        // New way: lambda
        Runnable newWay = () -> System.out.println("    New way (lambda): Running!");

        oldWay.run();
        newWay.run();
        System.out.println();

        // --- Section C: Comparator ---
        System.out.println("  [C] Comparator with Lambdas");
        System.out.println("  ----------------------------");

        List<String> names = new ArrayList<>(Arrays.asList(
                "Charlie", "Alice", "Bob", "Diana", "Eve"));
        System.out.println("    Original: " + names);

        // Sort alphabetically
        Collections.sort(names, (a, b) -> a.compareTo(b));
        System.out.println("    Alphabetical: " + names);

        // Sort by length
        names.sort((a, b) -> Integer.compare(a.length(), b.length()));
        System.out.println("    By length: " + names);

        // Sort by last character
        names.sort((a, b) -> Character.compare(
                a.charAt(a.length() - 1),
                b.charAt(b.length() - 1)));
        System.out.println("    By last char: " + names);

        // Reverse sort using Comparator.reversed()
        names.sort(Comparator.comparingInt(String::length).reversed());
        System.out.println("    By length (reversed): " + names);
        System.out.println();

        // --- Section D: Predicate ---
        System.out.println("  [D] Predicate<T> (T -> boolean)");
        System.out.println("  ---------------------------------");

        // Predicate takes one argument and returns boolean
        Predicate<Integer> isEven = n -> n % 2 == 0;
        Predicate<Integer> isPositive = n -> n > 0;
        Predicate<String> isLong = s -> s.length() > 5;

        System.out.println("    isEven(4): " + isEven.test(4));
        System.out.println("    isEven(7): " + isEven.test(7));
        System.out.println("    isPositive(-3): " + isPositive.test(-3));
        System.out.println("    isLong(\"Hello\"): " + isLong.test("Hello"));
        System.out.println("    isLong(\"Hello World\"): " + isLong.test("Hello World"));

        // Combining predicates with and(), or(), negate()
        Predicate<Integer> isEvenAndPositive = isEven.and(isPositive);
        Predicate<Integer> isEvenOrPositive = isEven.or(isPositive);
        Predicate<Integer> isOdd = isEven.negate();

        System.out.println("    isEvenAndPositive(4): " + isEvenAndPositive.test(4));
        System.out.println("    isEvenAndPositive(-4): " + isEvenAndPositive.test(-4));
        System.out.println("    isEvenOrPositive(-4): " + isEvenOrPositive.test(-4));
        System.out.println("    isOdd(7): " + isOdd.test(7));

        // Using Predicate to filter a list
        List<Integer> numbers = Arrays.asList(1, -2, 3, -4, 5, 6, -7, 8);
        System.out.println("    Numbers: " + numbers);
        System.out.print("    Even and positive: ");
        numbers.stream().filter(isEvenAndPositive).forEach(n -> System.out.print(n + " "));
        System.out.println();
        System.out.println();

        // --- Section E: Function ---
        System.out.println("  [E] Function<T,R> (T -> R)");
        System.out.println("  ----------------------------");

        // Function takes one argument and returns a result of possibly different type
        Function<String, Integer> stringLength = String::length;
        Function<Integer, String> intToString = n -> "Number: " + n;
        Function<String, String> toUpperCase = String::toUpperCase;

        System.out.println("    stringLength('Hello'): " + stringLength.apply("Hello"));
        System.out.println("    intToString(42): " + intToString.apply(42));
        System.out.println("    toUpperCase('hello'): " + toUpperCase.apply("hello"));

        // Chaining Functions with andThen() and compose()
        Function<String, Integer> lengthOfUpper = toUpperCase.andThen(stringLength);
        System.out.println("    toUpperCase then length of 'hello': " + lengthOfUpper.apply("hello"));

        Function<Integer, String> doubleAndFormat =
                ((Function<Integer, Integer>) n -> n * 2).andThen(intToString);
        System.out.println("    double(21) then format: " + doubleAndFormat.apply(21));

        // Function.identity()
        Function<String, String> identity = Function.identity();
        System.out.println("    identity('same'): " + identity.apply("same"));
        System.out.println();

        // --- Section F: Consumer ---
        System.out.println("  [F] Consumer<T> (T -> void)");
        System.out.println("  ----------------------------");

        // Consumer performs an action on a value, returns nothing
        Consumer<String> printer = s -> System.out.println("    Consuming: " + s);
        Consumer<String> shouter = s -> System.out.println("    SHOUTING: " + s.toUpperCase());

        printer.accept("Hello");
        shouter.accept("quiet message");

        // Chaining consumers with andThen()
        Consumer<String> printThenShout = printer.andThen(shouter);
        printThenShout.accept("chained");

        // Using Consumer with forEach
        List<String> fruits = Arrays.asList("Apple", "Banana", "Cherry");
        System.out.print("    forEach: ");
        fruits.forEach(f -> System.out.print(f + " "));
        System.out.println();
        System.out.println();

        // --- Section G: Supplier ---
        System.out.println("  [G] Supplier<T> (() -> T)");
        System.out.println("  ---------------------------");

        // Supplier takes no arguments and returns a value (factory pattern)
        Supplier<String> greeting = () -> "Hello from Supplier!";
        Supplier<Double> randomNumber = Math::random;
        Supplier<List<String>> emptyList = ArrayList::new;

        System.out.println("    greeting: " + greeting.get());
        System.out.println("    randomNumber: " + String.format("%.4f", randomNumber.get()));
        System.out.println("    emptyList: " + emptyList.get());

        // Practical: Lazy evaluation
        Supplier<String> expensiveComputation = () -> {
            // Imagine this is an expensive operation
            return "Computed value: " + (42 * 42);
        };
        System.out.println("    Lazy computation: " + expensiveComputation.get());
        System.out.println();

        // --- Section H: UnaryOperator and BinaryOperator ---
        System.out.println("  [H] UnaryOperator<T> and BinaryOperator<T>");
        System.out.println("  ---------------------------------------------");

        // UnaryOperator<T> is Function<T,T> - same input/output type
        UnaryOperator<String> exclaim = s -> s + "!";
        UnaryOperator<Integer> doubleIt = n -> n * 2;

        System.out.println("    exclaim('Hello'): " + exclaim.apply("Hello"));
        System.out.println("    doubleIt(21): " + doubleIt.apply(21));

        // Chaining UnaryOperators
        UnaryOperator<String> upperExclaim = ((UnaryOperator<String>) String::toUpperCase).andThen(exclaim)::apply;
        System.out.println("    upper then exclaim: " + upperExclaim.apply("wow"));

        // BinaryOperator<T> is BiFunction<T,T,T> - two inputs, same type output
        BinaryOperator<Integer> add = Integer::sum;
        BinaryOperator<Integer> max = Integer::max;
        BinaryOperator<String> concat = String::concat;

        System.out.println("    add(10, 20): " + add.apply(10, 20));
        System.out.println("    max(10, 20): " + max.apply(10, 20));
        System.out.println("    concat('Hello', ' World'): " + concat.apply("Hello", " World"));

        // Using with reduce
        List<Integer> nums = Arrays.asList(1, 2, 3, 4, 5);
        int total = nums.stream().reduce(0, Integer::sum);
        System.out.println("    reduce sum [1,2,3,4,5]: " + total);
        System.out.println();

        // --- Section I: Method References ---
        System.out.println("  [I] Method References");
        System.out.println("  ----------------------");

        System.out.println("    Method references are shorthand for lambdas:");
        System.out.println();

        // 1. Reference to a static method: ClassName::staticMethod
        Function<String, Integer> parseInt = Integer::parseInt;
        System.out.println("    Static method ref - Integer::parseInt(\"42\"): "
                + parseInt.apply("42"));

        // 2. Reference to an instance method of a specific object
        String sample = "Hello World";
        Supplier<String> toUpper = sample::toUpperCase;
        System.out.println("    Instance method ref - \"Hello World\"::toUpperCase(): "
                + toUpper.get());

        // 3. Reference to an instance method of an arbitrary object
        Function<String, String> toLowerCase = String::toLowerCase;
        System.out.println("    Arbitrary object ref - String::toLowerCase(\"HELLO\"): "
                + toLowerCase.apply("HELLO"));

        // 4. Reference to a constructor
        Supplier<ArrayList<String>> listFactory = ArrayList::new;
        ArrayList<String> newList = listFactory.get();
        newList.add("Created via constructor reference!");
        System.out.println("    Constructor ref - ArrayList::new: " + newList);
        System.out.println();

        // --- Section J: Lambdas with effectively final variables ---
        System.out.println("  [J] Lambdas and Effectively Final Variables");
        System.out.println("  ---------------------------------------------");

        String prefix = ">>>";  // effectively final
        int multiplier = 3;     // effectively final

        Consumer<String> prefixedPrint = s -> System.out.println("    " + prefix + " " + s);
        Function<Integer, Integer> multiply = n -> n * multiplier;

        prefixedPrint.accept("This uses an effectively final variable");
        System.out.println("    multiply(7) with captured multiplier=3: " + multiply.apply(7));

        // Note: Uncommenting the next line would make 'multiplier' NOT effectively final,
        // causing a compile error in the lambda above.
        // multiplier = 5;  // COMPILE ERROR if uncommented

        System.out.println("    Lambdas can capture local variables only if they are");
        System.out.println("    'effectively final' (never reassigned after initialization).");
    }
}
