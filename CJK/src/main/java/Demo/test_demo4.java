package Demo;

/**
 *
 * @author jkchang
 * @date 30-Aug-2018
 * @Description:
 *
 */
interface A4 { //接口

    public static final String MSG = "Hello";  //全局常量

    public abstract void print(); //抽象方法
}

interface B4 {

    public abstract void get(); //抽象方法
}

abstract class C4 {

    public abstract void change();
}

class X extends C4 implements A4, B4 {  //实现 A B 两个接口

    @Override
    public void print() {
        System.out.println("override method from A");
    }

    @Override
    public void get() {
        System.out.println("overide method from B");
    }

    @Override
    public void change() {
        System.out.println("overide method from C");
    }

}

public class test_demo4 {

    public static void main(String[] args) {
        X x = new X();  //实例化子类对象
        A4 a = x; //向上转型
        B4 b = x;  //向上转型

        a.print();
        b.get();
        x.print();
        x.get();
    }
}
