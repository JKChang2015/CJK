package Ext;

/**
 *
 * @author jkchang
 * @date 15-Mar-2017
 * @Description:
 *
 */
public class Rectangle extends TwoDShape {

    Rectangle() {
        super();
    }

    Rectangle(double width) {
        super(width, "Square");
    }

    Rectangle(double width, double height) {
        super(width, height, "rectangle");
    }

    Rectangle(Rectangle ob) {
        super(ob);
    }

    boolean isSquare() {
        if ((super.getHeight()) == (super.getWidth())) {
            return true;
        }
        return false;
    }

    double area() {
        return getWidth() * getHeight();
    }

}
