package Demo;

/**
 *
 * @author jkchang
 * @date 02-Sep-2018
 * @Description:
 *
 */
public class test_demo6 {

    public static void main(String[] args) {
        Fruit f = Factory.getInstance("apple");
        f.eat();
        f = Factory.getInstance("orange");
        f.eat();
    }
}

interface Fruit {

    public void eat();
}

class Apple implements Fruit {

    @Override
    public void eat() {
        System.out.println("eating Apple");
    }
}

class Orange implements Fruit {

    @Override
    public void eat() {
        System.out.println("eating Orange..");
    }
}

class Factory {

    public static Fruit getInstance(String className) {
        if ("apple".equals(className)) {
            return new Apple();
        } else if ("orange".equals(className)) {
            return new Orange();
        } else {
            return null;
        }

    }

}
