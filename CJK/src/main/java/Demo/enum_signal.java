package Demo;

/**
 *
 * @author JKChang
 * @date 22-Nov-2016
 * @Description: demonstration of 'enum' using in switch-case
 *
 */
public class enum_signal {

    

    public enum Signal {
        GREEN, RED, YELLOW;
    }
    
    Signal color;

    public void change() {
        switch (color) {
            case RED:
                color = Signal.GREEN;
                break;
            case YELLOW:
                color = Signal.RED;
                break;
            case GREEN:
                color = Signal.YELLOW;
                break;
        }
    }

}
