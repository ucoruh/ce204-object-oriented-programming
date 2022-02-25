---
marp: true
theme: default
style: |
    img[alt~="center"] {
      display: block;
      margin: 0 auto;
    }
_class: lead
paginate: true
backgroundColor: #fff
backgroundImage: url('https://marp.app/assets/hero-background.svg')
header: 'CE204 Object-Oriented Programming'
footer: '![height:50px](http://erdogan.edu.tr/Images/Uploads/MyContents/L_379-20170718142719217230.jpg) RTEU CE204 Week-2'
title: "CE204 Object-Oriented Programming"
author: "Author: Asst. Prof. Dr. Uğur CORUH"
date:
subtitle: "OOP with Java-II"
geometry: "left=2.54cm,right=2.54cm,top=1.91cm,bottom=1.91cm"
titlepage: true
titlepage-color: "FFFFFF"
titlepage-text-color: "000000"
titlepage-rule-color: "CCCCCC"
titlepage-rule-height: 4
logo: "assets/2021-10-19-15-01-36-image.png"
logo-width: 100 
page-background:
page-background-opacity:
links-as-notes: true
lot: true
lof: true
listings-disable-line-numbers: true
listings-no-page-break: false
disable-header-and-footer: false
header-left:
header-center:
header-right:
footer-left: "© Dr. Uğur CORUH"
footer-center: "License: WTFPL"
footer-right:
subparagraph: true
lang: en-US 

math: katex
---

<!-- _backgroundColor: aquq -->

<!-- _color: orange -->

<!-- paginate: false -->

## CE204 Object-Oriented Programming

## Week-2 (OOP with Java-II)

#### Spring Semester, 2021-2022

Download [DOC](ce204-week-2.md_doc.pdf), [SLIDE](ce204-week-2.md_slide.pdf), [PPTX](ce204-week-2.md_slide.pptx)

<iframe width=700, height=500 frameBorder=0 src="../ce204-week-2.md_slide.html"></iframe>

---

<!-- paginate: true -->

## **OOP with Java-II**

--- 

## Outline (1)

 - Java super Keyword
 - Java final Keyword
 - Java Polymorphism / Encapsulation
 - Java Method Overriding
 - Java Nested Inner Class
 - Java Static Class
 - Java Anonymous Class

--- 

## Outline (2)

 - Java Enums / Enum-Constructor / Enum-String
 - Java Abstract Class
 - Java Object Class
 - Java Forms of Inheritance
 - Java Benefits and Costs of Inheritance
 - Java Packages
 - Java Access Protection in Packages

--- 

## **Java super keyword**

---

## Java super keyword

- In java, `super` is a keyword used to refers to the **parent class object**. 
- The `super` keyword came into existence to solve the *naming conflicts* in the inheritance. 
- When both parent class and child class have members with the same name, 
  - then the super keyword is used to refer to the parent class version.

---

## Java super keyword

- In another word, The super keyword in Java is used in subclasses to access superclass members (attributes, constructors and methods).

---

## Java super keyword

- In java, the super keyword is used for the following purposes.
  - To refer parent class **data members**
  - To refer parent class **methods**
  - To call parent class **constructor**

---

## Java super keyword

- To call methods of the superclass that is overridden in the subclass.
- To access attributes (fields) of the superclass if both superclass and subclass have attributes with the same name.
- To explicitly call superclass no-arg (default) or parameterized constructor from the subclass constructor.

---

## Java super keyword

- The super keyword is used inside the child class only.

---

## super to refer parent class **data members**

- When both parent class and child class have data members with the same name, 
  - then the super keyword is used to refer to the parent class data member from child class.

---

## super to refer parent class **data members**

``` Java
class ParentClass{
	
	int num = 10;
	
}
```

``` Java
class ChildClass extends ParentClass{
	
	int num = 20;
	
	void showData() {
		System.out.println("Inside the ChildClass");
		System.out.println("ChildClass num = " + num);
		System.out.println("ParentClass num = " + super.num);		
	}
}
```

---

## super to refer parent class **data members**

``` Java
public class SuperKeywordExample {

	public static void main(String[] args) {
		ChildClass obj = new ChildClass();
		
		obj.showData();
		
		System.out.println("\nInside the non-child class");
		System.out.println("ChildClass num = " + obj.num);
		//System.out.println("ParentClass num = " + super.num); //super can't be used here
	}
}
```

---

## super to refer parent class **method**

- When both parent class and child class have method with the same name, 
  - then the super keyword is used to refer to the parent class method from child class.

---

## super to refer parent class **method**

```Java
class ParentClass{
	
	int num1 = 10;
	
	void showData() {
		System.out.println("\nInside the ParentClass showData method");
		System.out.println("ChildClass num = " + num1);		
	}	
}
```

---

## super to refer parent class **method**

```Java
class ChildClass extends ParentClass{
	
	int num2 = 20;
	
	void showData() {
		System.out.println("\nInside the ChildClass showData method");
		System.out.println("ChildClass num = " + num2);	

		super.showData();
		
	}
}
```

---

## super to refer parent class **method**

```Java
public class SuperKeywordExample {

	public static void main(String[] args) {
		ChildClass obj = new ChildClass();
		
		obj.showData();
		//super.showData();	// super can't be used here
		
	}
}
```

--- 

## super to call parent class **constructor**

- When an object of child class is created, it automatically calls the parent class default-constructor before it's own. 
- But, the parameterized constructor of parent class must be called explicitly using the super keyword inside the child class constructor.

--- 

## super to call parent class **constructor**

``` Java
class ParentClass{
	
	int num1;
	
	ParentClass(){
		System.out.println("\nInside the ParentClass default constructor");
		num1 = 10;
	}
	
	ParentClass(int value){
		System.out.println("\nInside the ParentClass parameterized constructor");
		num1 = value;
	}	
}
```

--- 

## super to call parent class **constructor**

``` Java
class ChildClass extends ParentClass{
	
	int num2;
	
	ChildClass(){
		super(100);
		System.out.println("\nInside the ChildClass constructor");
		num2 = 200;		
	}
}
```

--- 

## super to call parent class **constructor**

``` Java
public class SuperKeywordExample {

	public static void main(String[] args) {
		
		ChildClass obj = new ChildClass();
		
	}
}
```

--- 

## super to call parent class **constructor**

- To call the parameterized constructor of the parent class, 
- the super keyword must be the first statement inside the child class constructor, 
- and we must pass the parameter values.

---

## Access Overridden Methods of the superclass

- If methods with the same name are defined in both superclass and subclass, the method in the subclass overrides the method in the superclass. This is called method overriding.

---

## Example 1: Method overriding

``` Java
class Animal {

  // overridden method
  public void display(){
    System.out.println("I am an animal");
  }
}
```

---

## Example 1: Method overriding

``` Java
class Dog extends Animal {

  // overriding method
  @Override
  public void display(){
    System.out.println("I am a dog");
  }

  public void printMessage(){
    display();
  }
}
```

---

## Example 1: Method overriding

``` Java
class Main {
  public static void main(String[] args) {
    Dog dog1 = new Dog();
    dog1.printMessage();
  }
}

```

---

## Example 1: Method overriding

In this example, by making an object dog1 of Dog class, we can call its method printMessage() which then executes the display() statement.

Since display() is defined in both the classes, the method of subclass Dog overrides the method of superclass Animal. Hence, the display() of the subclass is called.

---

## Example 1: Method overriding

![center h:500px](assets/java-overriding-example.png)

---

## What if the overridden method of the superclass has to be called?
- We use super.display() if the overridden method display() of superclass Animal needs to be called.

---

## Example 2: super to Call Superclass Method

``` Java
class Animal {

  // overridden method
  public void display(){
    System.out.println("I am an animal");
  }
}
```

---

## Example 2: super to Call Superclass Method

``` Java
class Dog extends Animal {

  // overriding method
  @Override
  public void display(){
    System.out.println("I am a dog");
  }

  public void printMessage(){

    // this calls overriding method
    display();

    // this calls overridden method
    super.display();
  }
}
```
---

## Example 2: super to Call Superclass Method

``` Java
class Main {
  public static void main(String[] args) {
    Dog dog1 = new Dog();
    dog1.printMessage();
  }
}
```
---

## Example 2: super to Call Superclass Method

![center h:500px](assets/call-superclass-method.png)

---

## Access Attributes of the Superclass

- The superclass and subclass can have attributes with the same name. 
  - We use the super keyword to access the attribute of the superclass.

---

## Example 3: Access superclass attribute

``` Java
class Animal {
  protected String type="animal";
}
```

``` Java
class Dog extends Animal {
  public String type="mammal";

  public void printType() {
    System.out.println("I am a " + type);
    System.out.println("I am an " + super.type);
  }
}
```

---

## Example 3: Access superclass attribute

``` Java
class Main {
  public static void main(String[] args) {
    Dog dog1 = new Dog();
    dog1.printType();
  }
}

```

---

## Example 3: Access superclass attribute

- In this example, we have defined the same instance field `type` in both the superclass `Animal` and the subclass `Dog`.
- We then created an object `dog1` of the Dog class. Then, the `printType()` method is called using this object.
  - Inside the `printType()` function,
    - `type` refers to the attribute of the subclass `Dog`.
    - `super.type` refers to the attribute of the superclass Animal.

---

## Use of super() to access superclass constructor

- As we know, when an object of a class is created, its default constructor is automatically called.
- To explicitly call the superclass constructor from the subclass constructor, we use `super()`. It's a special form of the super keyword.
- `super()` can be used only inside the subclass constructor and must be the first statement.

---

## Example 4: Use of super()

``` Java
class Animal {

  // default or no-arg constructor of class Animal
  Animal() {
    System.out.println("I am an animal");
  }
}
```

---

## Example 4: Use of super()

``` Java
class Dog extends Animal {

  // default or no-arg constructor of class Dog
  Dog() {

    // calling default constructor of the superclass
    super();

    System.out.println("I am a dog");
  }
}
```

---

## Example 4: Use of super()

``` Java
class Main {
  public static void main(String[] args) {
    Dog dog1 = new Dog();
  }
}
```

---

## Example 4: Use of super()

- when an object dog1 of Dog class is created, it automatically calls the default or no-arg constructor of that class.

- Inside the subclass constructor, the super() statement calls the constructor of the superclass and executes the statements inside it. Hence, we get the output I am an animal.

---

## Example 4: Use of super()

![bg right:50% h:400px](assets/super()-example.png)

The flow of the program then returns back to the subclass constructor and executes the remaining statements. Thus, I am a dog will be printed.

However, using super() is not compulsory. Even if super() is not used in the subclass constructor, the compiler implicitly calls the default constructor of the superclass.

---

## Example 4: Use of super()

- **So, why use redundant code if the compiler automatically invokes super()?**
  -  It is required if the parameterized constructor (a constructor that takes arguments) of the superclass has to be called from the subclass constructor.

- The parameterized super() must always be the first statement
  - in the body of the constructor of the subclass, 
  - otherwise, we get a compilation error.

---

## Example 5: Call Parameterized Constructor Using super()

``` Java
class Animal {

  // default or no-arg constructor
  Animal() {
    System.out.println("I am an animal");
  }

  // parameterized constructor
  Animal(String type) {
    System.out.println("Type: "+type);
  }
}
```

---

## Example 5: Call Parameterized Constructor Using super()

``` Java
class Dog extends Animal {

  // default constructor
  Dog() {

    // calling parameterized constructor of the superclass
    super("Animal");

    System.out.println("I am a dog");
  }
}
```

---

## Example 5: Call Parameterized Constructor Using super()

``` Java
class Main {
  public static void main(String[] args) {
    Dog dog1 = new Dog();
  }
}
```

---

## Example 5: Call Parameterized Constructor Using super()

If a parameterized constructor has to be called, we need to explicitly define it in the subclass constructor.

![bg right:50% h:500px](assets/parameterized-super-example.png)

---

## Example 5: Call Parameterized Constructor Using super()

Note that in the above example, we explicitly called the parameterized constructor super("Animal"). The compiler does not call the default constructor of the superclass in this case.

---

## References

[BtechSmartClass-super Keyword](http://www.btechsmartclass.com/java/java-super-keyword.html)
[Programiz-super Keyword](https://www.programiz.com/java-programming/super-keyword)
[BtechSmartClass-Java final Keyword](http://www.btechsmartclass.com/java/java-final-keyword.html)
[Programiz-final Keyword](https://www.programiz.com/java-programming/final-keyword)
[BtechSmartClass-java Polymorphism](http://www.btechsmartclass.com/java/java-polymorphism.html)
[Programiz-Polymorphism](https://www.programiz.com/java-programming/polymorphism)
[Programiz-Encapsulation](https://www.programiz.com/java-programming/encapsulation)
[BtechSmartClass-Java Method Overriding](http://www.btechsmartclass.com/java/java-method-overriding.html)

---

## References

[Programiz-Method Overriding](https://www.programiz.com/java-programming/method-overriding)
[Programiz-Nested Inner Class](https://www.programiz.com/java-programming/nested-inner-class)
[Programiz-Static Class](https://www.programiz.com/java-programming/static-class)
[Programiz-Anonymous Class](https://www.programiz.com/java-programming/anonymous-class)
[Programiz-enums](https://www.programiz.com/java-programming/enums)
[Programiz-enum constructor](https://www.programiz.com/java-programming/enum-constructor)
[Programiz-enum string](https://www.programiz.com/java-programming/enum-string)
[BtechSmartClass-Java Abstract Class](http://www.btechsmartclass.com/java/java-abstract-class.html)
[Programiz-Abstract Classes Methods](https://www.programiz.com/java-programming/abstract-classes-methods)

---

## References

[BtechSmartClass-Java Object class](http://www.btechsmartclass.com/java/java-Object-class.html)
[BtechSmartClass-Java Forms of Inheritance](http://www.btechsmartclass.com/java/java-forms-of-inheritance.html)
[Programiz-Interfaces](https://www.programiz.com/java-programming/interfaces)
[BtechSmartClass-Java Benefits and Costs of Inheritance](http://www.btechsmartclass.com/java/java-benefits-and-costs-of-inheritance.html)
[BtechSmartClass-Java Defining Packages](http://www.btechsmartclass.com/java/java-defining-packages.html)
[BtechSmartClass-Java Access Protection in Packages](http://www.btechsmartclass.com/java/java-access-protection-in-packages.html)
[BtechSmartClass-Java Importing Packages](http://www.btechsmartclass.com/java/java-importing-packages.html)

---

$End-Of-Week-2-Module$