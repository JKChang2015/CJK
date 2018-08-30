package Demo;

abstract class A3 {

    public abstract void print();

    private static class B3 extends A3 {  //内部抽象类子类

        public void print() {  //覆写抽象类方法
            System.out.println("Hello this is B3");
        }

    }

//这个方法不受实例化控制
    public static A3 getInstance() {
        return new B3();
    }

}

public class test_demo3 {

    public static void main(String args[]) {
        // 此时调用抽象类的方法，子类被隐藏。
        A3 a = A3.getInstance();
        a.print();
    }
}
