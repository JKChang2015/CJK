package Demo;

/**
 * Created by jkchang 30/08/2018 Tag: Description:
 */
public class test_demo2 {

    public static void main(String[] args) {
        A2 a = new B2();
        a.print();
        a.fun();
    }

}

abstract class A2 {

    // general method
    public void fun() {
        System.out.println("include a method block");
    }

    // abstract method, not include any method body block
    public abstract void print();

}

class B2 extends A2 {

    public void print() {
        System.out.println("This is B2");
    }

}
