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

        MathOperation addition = (int a, int b) -> a + b;

        MathOperation substraction = (a, b) -> a - b;

        MathOperation multiplication = (int a, int b) -> {
            return a * b;
        };
        
        MathOperation division = (int a, int b) -> a/b;
        
        System.out.println("10 + 5 = " + tester.operation(10, 5, addition));
        System.out.println("10 - 5 = " + tester.operation(10, 5, substraction));
        System.out.println("10 * 5 = " + tester.operation(10, 5, multiplication));
        System.out.println("10 / 5 = " + tester.operation(10, 5, division));
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
