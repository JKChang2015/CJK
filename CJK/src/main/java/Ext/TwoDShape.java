/* ----------------------------------------------------------------------------

   ---------------------------------------------------------------------------- 
 @author jkchang
 @date 30-Jun-2016
 */
package Ext;

public class TwoDShape {

    private double width;
    private double height;

    TwoDShape() {
        width = height = 0.0;
    }

    TwoDShape(double width, double height) {
        this.width = width;
        this.height = height;
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    void showDim() {
        System.out.println("Width and height are " + width + " and " + height);
    }

}
