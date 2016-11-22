package Demo;

import Demo.Colora;

/**
 *
 * @author JKChang
 * @date 22-Nov-2016
 * @Description:
 *
 */
public interface Behaviour {

    void print();

    String getInfo();
}

public enum Colora implements Behaviour {
    RED("红色", 1), GREEN("绿色", 2), BLANK("白色", 3), YELLO("黄色", 4);
    // 成员变量  
    private String name;
    private int index;

    // 构造方法  
    private Colora(String name, int index) {
        this.name = name;
        this.index = index;
    }

    //接口方法  
    @Override
    public String getInfo() {
        return this.name;
    }

    //接口方法  
    @Override
    public void print() {
        System.out.println(this.index + ":" + this.name);
    }
}
