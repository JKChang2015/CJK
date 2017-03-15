/* ----------------------------------------------------------------------------

   ---------------------------------------------------------------------------- 
 @author jkchang
 @date 30-Jun-2016
 */
package Ext;

public class Triangle extends TwoDShape {

    String style;

    Triangle() {
        super();
        style = "none";
    }

    Triangle(double x) {
        super(x, "Triangle");
        style = "filled";
    }

    Triangle(String style, double width, double height) {
        super(width, height, "Triangle");
        this.style = style;
    }

    Triangle(Triangle ob) {
        super(ob);
        this.style = ob.style;
    }

    public double area(){
        return super.getHeight() * super.getWidth() /2.0;
    }
    
    void showStyle(){
        System.out.println("Triangle is " + style);
    }
    
}
