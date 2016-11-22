package Demo;

/**
 *
 * @author JKChang
 * @date 22-Nov-2016
 * @Description: 
 *
 */
public enum enum_Light {
    RED(1), GREEN(3), YELLOW(2);

    private int nCode;

    private enum_Light(int _nCode) {
        this.nCode = _nCode;
    }

    @Override
    public String toString() { 
        //return the parameter value came from construction function
        return String.valueOf(this.nCode);

    }
}
