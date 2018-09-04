package Demo;

/**
 *
 * @author jkchang
 * @date 02-Sep-2018
 * @Description:
 *
 */
interface Subject {

    public void make();
}

class RealSubject implements Subject {

    @Override
    public void make() {
        System.out.println("making");
    }
}

class ProxySubject implements Subject {

    private Subject subject;

    public ProxySubject(Subject subject) {
        this.subject = subject;
    }

    public void prepare() {
        System.out.println("preparing for making...");
    }

    public void make() {
        this.prepare();
        this.subject.make();
        this.finish();
    }

    public void finish() {
        System.out.println("Done...");
    }
}

public class test_demo7 {

    public static void main(String[] args) {
        Subject sub = new ProxySubject(new RealSubject());
        // 用户请求操作， 代理服务 帮助用户完成其余操作
        // 一个主题subject，但是是两个操作
        sub.make();  //调用代理主题操作， 完成比真实更多的操作

    }
}
