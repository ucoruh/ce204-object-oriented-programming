package com.example.week03.wrappers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * ============================================================================
 * DEMO 10: Wrapper Classes
 * ============================================================================
 *
 * Java has 8 PRIMITIVE types: byte, short, int, long, float, double, char, boolean.
 * Each primitive has a corresponding WRAPPER CLASS in java.lang:
 *
 *   Primitive   Wrapper Class
 *   --------    -------------
 *   byte        Byte
 *   short       Short
 *   int         Integer
 *   long        Long
 *   float       Float
 *   double      Double
 *   char        Character
 *   boolean     Boolean
 *
 * Why do we need wrapper classes?
 *   1. Collections (ArrayList, HashMap, etc.) can only hold OBJECTS, not primitives.
 *   2. Generics require object types: List<Integer>, not List<int>.
 *   3. Wrapper classes provide utility methods (parsing, comparison, conversion).
 *   4. They allow null values (primitives cannot be null).
 *
 * AUTOBOXING: Automatic conversion from primitive to wrapper (Java 5+).
 *   int -> Integer (automatically)
 *
 * UNBOXING: Automatic conversion from wrapper to primitive.
 *   Integer -> int (automatically)
 *
 * ============================================================================
 */
public class WrapperClassDemo {

    // =======================================================================
    // DEMO METHOD
    // =======================================================================

    public static void demo() {
        // --- Section A: Basic Autoboxing and Unboxing ---
        System.out.println("  [A] Autoboxing and Unboxing");
        System.out.println("  ----------------------------");

        // AUTOBOXING: primitive -> wrapper (automatic)
        int primitiveInt = 42;
        Integer wrappedInt = primitiveInt;  // autoboxing: int -> Integer
        System.out.println("    Autoboxing: int " + primitiveInt + " -> Integer " + wrappedInt);

        double primitiveDouble = 3.14;
        Double wrappedDouble = primitiveDouble;  // autoboxing: double -> Double
        System.out.println("    Autoboxing: double " + primitiveDouble + " -> Double " + wrappedDouble);

        boolean primitiveBool = true;
        Boolean wrappedBool = primitiveBool;  // autoboxing: boolean -> Boolean
        System.out.println("    Autoboxing: boolean " + primitiveBool + " -> Boolean " + wrappedBool);

        char primitiveChar = 'A';
        Character wrappedChar = primitiveChar;  // autoboxing: char -> Character
        System.out.println("    Autoboxing: char '" + primitiveChar + "' -> Character '" + wrappedChar + "'");
        System.out.println();

        // UNBOXING: wrapper -> primitive (automatic)
        Integer boxedValue = Integer.valueOf(100);
        int unboxedValue = boxedValue;  // unboxing: Integer -> int
        System.out.println("    Unboxing: Integer " + boxedValue + " -> int " + unboxedValue);

        Double boxedPI = Double.valueOf(3.14159);
        double unboxedPI = boxedPI;  // unboxing: Double -> double
        System.out.println("    Unboxing: Double " + boxedPI + " -> double " + unboxedPI);
        System.out.println();

        // --- Section B: Wrapper classes in Collections ---
        System.out.println("  [B] Wrapper Classes in Collections");
        System.out.println("  ------------------------------------");

        // Collections require objects, not primitives
        // List<int> list = new ArrayList<>();  // COMPILE ERROR!
        List<Integer> numbers = new ArrayList<>();

        // Autoboxing happens automatically when adding primitives
        numbers.add(5);       // autoboxing: 5 -> Integer.valueOf(5)
        numbers.add(3);
        numbers.add(8);
        numbers.add(1);
        numbers.add(9);
        System.out.println("    List: " + numbers);

        // Sorting works because Integer implements Comparable
        Collections.sort(numbers);
        System.out.println("    Sorted: " + numbers);

        // Unboxing happens automatically in arithmetic
        int sum = 0;
        for (Integer n : numbers) {
            sum += n;  // unboxing: Integer -> int for addition
        }
        System.out.println("    Sum: " + sum);
        System.out.println();

        // --- Section C: Parsing Strings to Primitives ---
        System.out.println("  [C] Parsing Strings to Primitives");
        System.out.println("  -----------------------------------");

        // Integer parsing
        String intStr = "123";
        int parsedInt = Integer.parseInt(intStr);
        System.out.println("    Integer.parseInt(\"" + intStr + "\") = " + parsedInt);

        // Double parsing
        String doubleStr = "3.14159";
        double parsedDouble = Double.parseDouble(doubleStr);
        System.out.println("    Double.parseDouble(\"" + doubleStr + "\") = " + parsedDouble);

        // Boolean parsing
        String boolStr = "true";
        boolean parsedBool = Boolean.parseBoolean(boolStr);
        System.out.println("    Boolean.parseBoolean(\"" + boolStr + "\") = " + parsedBool);

        // Long parsing
        String longStr = "9876543210";
        long parsedLong = Long.parseLong(longStr);
        System.out.println("    Long.parseLong(\"" + longStr + "\") = " + parsedLong);

        // Parsing with radix (base)
        int binary = Integer.parseInt("1010", 2);    // binary
        int octal = Integer.parseInt("17", 8);        // octal
        int hex = Integer.parseInt("1F", 16);          // hexadecimal
        System.out.println("    parseInt(\"1010\", 2) [binary] = " + binary);
        System.out.println("    parseInt(\"17\", 8) [octal] = " + octal);
        System.out.println("    parseInt(\"1F\", 16) [hex] = " + hex);
        System.out.println();

        // --- Section D: Converting Primitives to Strings ---
        System.out.println("  [D] Converting Primitives to Strings");
        System.out.println("  --------------------------------------");

        // Using wrapper class methods
        String fromInt = Integer.toString(42);
        String fromDouble = Double.toString(3.14);
        String fromBool = Boolean.toString(true);
        System.out.println("    Integer.toString(42) = \"" + fromInt + "\"");
        System.out.println("    Double.toString(3.14) = \"" + fromDouble + "\"");
        System.out.println("    Boolean.toString(true) = \"" + fromBool + "\"");

        // Using String.valueOf() - works for all types
        String sv1 = String.valueOf(42);
        String sv2 = String.valueOf(3.14);
        String sv3 = String.valueOf('A');
        System.out.println("    String.valueOf(42) = \"" + sv1 + "\"");
        System.out.println("    String.valueOf(3.14) = \"" + sv2 + "\"");
        System.out.println("    String.valueOf('A') = \"" + sv3 + "\"");

        // Integer to different bases
        System.out.println("    Integer.toBinaryString(42) = " + Integer.toBinaryString(42));
        System.out.println("    Integer.toOctalString(42) = " + Integer.toOctalString(42));
        System.out.println("    Integer.toHexString(42) = " + Integer.toHexString(42));
        System.out.println();

        // --- Section E: Wrapper Class Constants and Utility Methods ---
        System.out.println("  [E] Constants and Utility Methods");
        System.out.println("  -----------------------------------");

        // Integer constants
        System.out.println("    Integer.MAX_VALUE = " + Integer.MAX_VALUE);
        System.out.println("    Integer.MIN_VALUE = " + Integer.MIN_VALUE);
        System.out.println("    Integer.SIZE = " + Integer.SIZE + " bits");
        System.out.println("    Integer.BYTES = " + Integer.BYTES + " bytes");

        // Double constants
        System.out.println("    Double.MAX_VALUE = " + Double.MAX_VALUE);
        System.out.println("    Double.MIN_VALUE = " + Double.MIN_VALUE);
        System.out.println("    Double.POSITIVE_INFINITY = " + Double.POSITIVE_INFINITY);
        System.out.println("    Double.NEGATIVE_INFINITY = " + Double.NEGATIVE_INFINITY);
        System.out.println("    Double.NaN = " + Double.NaN);
        System.out.println("    Double.isNaN(Double.NaN) = " + Double.isNaN(Double.NaN));
        System.out.println("    Double.isInfinite(1.0/0) = " + Double.isInfinite(1.0 / 0));

        // Character utility methods
        System.out.println("    Character.isLetter('A') = " + Character.isLetter('A'));
        System.out.println("    Character.isDigit('5') = " + Character.isDigit('5'));
        System.out.println("    Character.isWhitespace(' ') = " + Character.isWhitespace(' '));
        System.out.println("    Character.toUpperCase('a') = " + Character.toUpperCase('a'));
        System.out.println("    Character.toLowerCase('Z') = " + Character.toLowerCase('Z'));
        System.out.println();

        // --- Section F: Comparing Wrapper Objects ---
        System.out.println("  [F] Comparing Wrapper Objects (IMPORTANT!)");
        System.out.println("  ---------------------------------------------");

        // WARNING: == on wrapper objects checks REFERENCE equality, not value!
        Integer a = 127;
        Integer b = 127;
        System.out.println("    a = 127, b = 127");
        System.out.println("    a == b:      " + (a == b));       // true (cached range -128 to 127)
        System.out.println("    a.equals(b): " + a.equals(b));     // true

        Integer c = 128;
        Integer d = 128;
        System.out.println("    c = 128, d = 128");
        System.out.println("    c == d:      " + (c == d));       // false! (outside cache range)
        System.out.println("    c.equals(d): " + c.equals(d));     // true

        System.out.println();
        System.out.println("    LESSON: ALWAYS use .equals() to compare wrapper objects!");
        System.out.println("    The == operator checks reference identity, not value.");
        System.out.println("    Java caches Integer values from -128 to 127, so ==");
        System.out.println("    appears to work for small values but fails for larger ones.");
        System.out.println();

        // Integer.compare() and Integer.compareTo()
        System.out.println("    Integer.compare(10, 20) = " + Integer.compare(10, 20));  // -1
        System.out.println("    Integer.compare(20, 10) = " + Integer.compare(20, 10));  //  1
        System.out.println("    Integer.compare(10, 10) = " + Integer.compare(10, 10));  //  0
        System.out.println();

        // --- Section G: Null handling with wrappers ---
        System.out.println("  [G] Null Handling with Wrappers");
        System.out.println("  ---------------------------------");

        // Wrappers can be null (primitives cannot)
        Integer nullableAge = null;
        System.out.println("    nullableAge = null (primitives can't be null, wrappers can)");

        // DANGER: Unboxing null throws NullPointerException!
        try {
            // int dangerousUnbox = nullableAge; // This would throw NPE!
            System.out.println("    Unboxing null would throw NullPointerException");
            System.out.println("    Always check for null before unboxing!");
        } catch (NullPointerException e) {
            System.out.println("    Caught NPE: " + e.getMessage());
        }

        // Safe pattern
        Integer safeValue = nullableAge != null ? nullableAge : 0;
        System.out.println("    Safe default: " + safeValue);
        System.out.println();

        // --- Section H: Number class hierarchy ---
        System.out.println("  [H] Number Class Hierarchy");
        System.out.println("  ----------------------------");
        System.out.println("    All numeric wrappers extend java.lang.Number:");
        System.out.println("    Number <- Integer, Long, Double, Float, Short, Byte");
        System.out.println();

        Number num = Integer.valueOf(42);
        System.out.println("    Number num = Integer.valueOf(42)");
        System.out.println("    num.intValue() = " + num.intValue());
        System.out.println("    num.doubleValue() = " + num.doubleValue());
        System.out.println("    num.longValue() = " + num.longValue());
        System.out.println("    num.floatValue() = " + num.floatValue());

        // Polymorphism with Number
        Number[] mixedNumbers = {
            Integer.valueOf(42),
            Double.valueOf(3.14),
            Long.valueOf(999999999L),
            Float.valueOf(2.5f)
        };
        System.out.println("    Mixed Number array, all as doubles:");
        for (Number n : mixedNumbers) {
            System.out.println("      " + n.getClass().getSimpleName()
                    + ": " + n + " -> double: " + n.doubleValue());
        }
    }
}
