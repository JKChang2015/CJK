/* ----------------------------------------------------------------------------

   ---------------------------------------------------------------------------- 
 @author jkchang
 @date 30-Jun-2016
 */
package Ext;

public class TwoDShape {

    private double width;
    private double height;
    private String name;

    //default
    TwoDShape() {
        width = height = 0.0;
        name = "none";
    }

    // 2 arguments
    TwoDShape(double x, String name) {
        width = height = x;
        this.name = name;
    }

    //3 arguments
    TwoDShape(double width, double height, String name) {
        this.width = width;
        this.height = height;
        this.name = name;
    }

    TwoDShape(TwoDShape ob) {
        this.width = ob.width;
        this.height = ob.height;
        this.name = ob.name;
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }

    public String getName() {
        return name;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    void showDim() {
        System.out.println(name + " Width and height are " + width + "+" + height);
    }

    double area() {
        System.out.println("area() must be overrided ");
        return 0;
    }
}
