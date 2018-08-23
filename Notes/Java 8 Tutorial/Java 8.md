# Java 8 Tutorial

Java 8 is a major feature release of Java programming language development. It released on 18 March 2014. 

-------

## New features
* **Lambda expression:** Adds functional processing capability to Java
* **Method references:** Referencing functions by their names instead of invoking them directly. Using function as parameter.
* **Default method:** Interface to have default method implementation
* **New tools:** New compiler tools and utilities are added like 'jdeps' to figure out dependencies.
* **Stream API:** New stream API to facilitate pipeline processing
* **Date Time API:** Improved date time API
* **Optinal:** Emphasis on best practices to handle null values properly.
* **Nashorn, JavaScript Engine:** A Java-based engine to execute JavaScript code. 

```java 
import java.util.Collections;
import java.util.List;
import java.util.ArrayList;
import java.util.Comparator;

public class Java8 {

    public static void main(String[] args) {

        List<String> array1 = new ArrayList<String>();
        array1.add("Mahesh ");
        array1.add("Suresh ");
        array1.add("Ramesh ");
        array1.add("Naresh ");
        array1.add("Kalpesh ");

        List<String> array2 = new ArrayList<String>(array1);
        Java8 tester = new Java8();

        System.out.println("Sort using Java 7 syntax: ");
        tester.sortUsingJava7(array1);
        System.out.println(array1);

        System.out.println("Sort using Java 8 syntax: ");
        tester.sortUsingJava8(array2);
        System.out.println(array2);
    }

    //sort using java 7
    private void sortUsingJava7(List<String> names) {
        Collections.sort(names, new Comparator<String>() {
            @Override
            public int compare(String s1, String s2) {
                return s1.compareTo(s2);
            }
        });
    }

    //sort using java 8
    private void sortUsingJava8(List<String> names) {
        Collections.sort(names, (s1, s2) -> s1.compareTo(s2));
  }
}
```

#### output
```
Sort using Java 7 syntax: 
[Kalpesh , Mahesh , Naresh , Ramesh , Suresh ]
Sort using Java 8 syntax: 
[Kalpesh , Mahesh , Naresh , Ramesh , Suresh ]
```

Here the `sortUsingJava8()` method uses sort function with a lambda expression as parameter to get the sorting criteria.

-------
## Lambda Expressions

###Syntax
A lambda expression is characterised by the following syntax.
```java
parameter -> expression body
```
Some important characteristics of a lambda expression:
* **Optional type declaration:** No need to declare the type of a parameter. the compiler can inference the same from the value of the parameter.
* **Optional parenthesis around parameter:**  No need to declare a single parameter in parenthesis. For multiple parameters, parentheses are required. 
* **Optional curly braces:** No need to use curly braces in expression body if the body contains a single statement.
* **Optional return keyword:** The compiler automatically returns the value if the body has a single expression to return the value. Curly braces are required to indicate that expression returns a value. 

### Lambda Expressions Example
