package Demo;

abstract class Action {
    
    public static final int EAT = 1;
    public static final int SLEEP = 5;
    public static final int WORK = 7;
    
    public void command(int flag) {
        switch (flag) {
            case EAT:
                this.eat();
                break;
            case SLEEP:
                this.sleep();
                break;
            case WORK:
                this.work();
                break;
            case EAT + WORK:
                this.eat();
                this.work();
                break;
        }
        
    }

    //不确定子类是如何实现的
    public abstract void eat();
    
    public abstract void sleep();
    
    public abstract void work();
    
}

class Robot extends Action {
    
    @Override
    public void eat() {
        System.out.println("Robot is charging");
    }
    
    @Override
    public void sleep() {
    }
    
    @Override
    public void work() {
        System.out.println("Robot is working");
    }
    
}

class Human extends Action {
    
    @Override
    public void eat() {
        System.out.println("Human is eating");
    }
    
    @Override
    public void sleep() {
        System.out.println("Human is sleeping");
    }
    
    @Override
    public void work() {
        System.out.println("Human is working hard");
    }
    
}

class Pig extends Action {
    
    @Override
    public void eat() {
        System.out.println("Pig eat");
    }
    
    @Override
    public void sleep() {
        System.out.println("Pig sleep");
    }
    
    @Override
    public void work() {
    }
    
}

public class abstractEx {
    
    public static void main(String[] args) {
        fun(new Robot());
        fun(new Human());
        fun(new Pig());
    }
    
    public static void fun(Action action) {
        action.command(Action.EAT);
        action.command(Action.SLEEP);
        action.command(Action.WORK);
    }
    
}
