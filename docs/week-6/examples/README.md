# CEN206 - Week 6: UMPLE Part-1 Examples

This folder contains UMPLE (`.ump`) files demonstrating Model-Driven Development concepts including classes, attributes, associations, inheritance, traits, and constraints.

## Files

| File | Topic | Description |
|------|-------|-------------|
| `basic-class.ump` | Classes & Attributes | Simple classes with typed attributes, default values, immutable and derived attributes |
| `associations.ump` | Associations | 1-to-1, 1-to-many, many-to-many, optional, and self-associations |
| `inheritance.ump` | Inheritance | Class hierarchies, abstract classes, interfaces, and `isA` keyword |
| `traits.ump` | Traits | Reusable cross-cutting concerns mixed into multiple classes |
| `constraints.ump` | Constraints | Attribute validation constraints (range, enum, non-negative) |

## What is UMPLE?

UMPLE is a model-oriented programming language that adds UML constructs (associations, state machines, etc.) directly to programming languages like Java, C++, and PHP. Instead of drawing UML diagrams and manually translating them to code, you write UMPLE and let the compiler generate the target language.

## How to Compile

### Option 1: Online (Recommended for Quick Testing)

1. Visit **[https://try.umple.org](https://try.umple.org)**.
2. Paste the contents of any `.ump` file into the editor.
3. The class diagram appears automatically on the right.
4. Click **Generate Code** and select **Java** to see the generated Java source.

### Option 2: Command Line with `umple.jar`

1. **Prerequisites:** Java 8+ installed.

2. **Download UMPLE:**
   ```bash
   # Download the latest umple.jar
   curl -L -o umple.jar https://cruise.umple.org/umple/download/umple.jar
   ```

3. **Generate Java code from an UMPLE file:**
   ```bash
   java -jar umple.jar basic-class.ump
   ```
   This creates `.java` files in the current directory.

4. **Compile the generated Java code:**
   ```bash
   javac *.java
   ```

5. **Generate to a specific output directory:**
   ```bash
   java -jar umple.jar -g Java --output ./generated basic-class.ump
   ```

### Option 3: UMPLE Eclipse Plugin

1. Install the UMPLE plugin from the Eclipse Marketplace.
2. Create an UMPLE project and add `.ump` files.
3. Code generation happens automatically on save.

## UMPLE Quick Reference

### Attributes
```umple
class Person {
    String name;                  // required attribute
    Integer age;                  // required attribute
    Double salary = 50000.0;     // attribute with default
    Boolean active = true;       // boolean with default
    immutable String id;         // cannot be changed after construction
    lazy String nickname;        // not required in constructor
}
```

### Associations
```umple
class A {
    1 -- 1 B;           // one-to-one
    1 -- 0..* C;        // one-to-many
    * -- * D;            // many-to-many
    0..1 -- 0..* E;     // optional to many
}
```

### Inheritance
```umple
class Animal { String name; }
class Dog { isA Animal; String breed; }
class Cat { isA Animal; Boolean indoor = true; }
```

### Traits
```umple
trait Timestamped { String createdAt; String updatedAt; }
class Order { isA Timestamped; Double total; }
```

### Constraints
```umple
class Product {
    Double price;
    [price > 0]           // price must be positive
    Integer qty;
    [qty >= 0]            // quantity non-negative
}
```

## Further Reading

- [UMPLE User Manual](https://cruise.umple.org/umple/)
- [UMPLE Language Reference](https://cruise.umple.org/umple/UmpleLanguage.html)
- [UMPLE Examples](https://cruise.umple.org/umple/examples.html)
- [Try UMPLE Online](https://try.umple.org)
