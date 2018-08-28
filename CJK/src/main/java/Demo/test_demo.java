package Demo;

/**
 *
 * @author jkchang
 * @date 28-Aug-2018
 * @Description:
 *
 */
public class test_demo {


    public static void main(String[] args) {
        A a = new B();
        B b = (B) a;
        b.print();
    }}


    class A {

        public void print() {
            System.out.println("A output");
        }
    }

    class B extends A {

        public void print() {
            System.out.println("B output");
        }
    }
