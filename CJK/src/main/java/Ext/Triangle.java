/* ----------------------------------------------------------------------------

   ---------------------------------------------------------------------------- 
 @author jkchang
 @date 30-Jun-2016
 */
package Ext;

public class Triangle extends TwoDShape {

    String style;

    Triangle(String style, double w, double h) {
        super(w,h);
        this.style = style;
    }

    double area() {
        return super.getWidth() * super.getHeight() / 2;
    }

    void showStyle() {
        System.out.println("Triangle is " + style);
    }

}
