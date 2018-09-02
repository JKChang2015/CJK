package Demo;

// 标准可以链接不同层的操作
interface USB {
    
    public void start();
    
    public void stop();
}

class Computer {
    
    public void plugin(USB usb) { //插入
        // 不管有多少设备，只要是实现了USB的子类，都可以运行 
        usb.start();  //固定操作
        usb.stop();
    }
    
}

class FlashDrive implements USB {
    
    @Override
    public void start() {
        System.out.println("FlashDrive Start..");
    }
    
    @Override
    public void stop() {
        System.out.println("FlashDrive Stop..");
    }
    
}

class Printer implements USB {
    
    @Override
    public void start() {
        System.out.println("Printer Start...");
    }
    
    @Override
    public void stop() {
        System.out.println("Printer Stop...");
    }
    
}

class MP3 implements USB {
    
    @Override
    public void start() {
        System.out.println("Mp3 Start...");
    }
    
    @Override
    public void stop() {
        System.out.println("MP3 Stop... ");
    }
    
}

public class test_demo5 {
    
    public static void main(String[] args) {
        Computer com = new Computer();
        com.plugin(new FlashDrive());
        com.plugin(new Printer());
    }
    
}
