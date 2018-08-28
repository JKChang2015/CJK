package Demo;

/**
 * Created by jkchang 23/08/2018 Tag: Description:
 */
import java.util.Collections;
import java.util.List;
import java.util.ArrayList;
import java.util.Comparator;

public class Java8 {

    public static void main(String[] args) {

        Java8 tester = new Java8();

        // with type declaration
        MathOperation addition = (int a, int b) -> a + b;

        //without type declaration
        MathOperation substraction = (a, b) -> a - b;

        // with return statement along with curly braces
        MathOperation multiplication = (int a, int b) -> {
            return a * b;
        };

        // without return statement and without curly braces
        MathOperation division = (int a, int b) -> a / b;

        System.out.println("10 + 5 = " + tester.operation(10, 5, addition));
        System.out.println("10 - 5 = " + tester.operation(10, 5, substraction));
        System.out.println("10 * 5 = " + tester.operation(10, 5, multiplication));
        System.out.println("10 / 5 = " + tester.operation(10, 5, division));

        //without parenthesis
        GreetingService greetService1 = message
                -> System.out.println("Hello " + message);

        //with parenthesis
        GreetingService greetService2 = (message)
                -> System.out.println("Hello " + message);

        greetService1.sayMessage("Mahesh");
        greetService2.sayMessage("Suresh");

    }

    interface MathOperation {

        int operatrion(int a, int b);
    }

    interface GreetingService {

        void sayMessage(String message);
    }

    private int operation(int a, int b, MathOperation mathOperation) {
        return mathOperation.operatrion(a, b);
    }
}
