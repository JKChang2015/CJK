/* ----------------------------------------------------------------------------

   ---------------------------------------------------------------------------- 
 @author jkchang
 @date 30-Jun-2016
 */
package ext;

public class Triangle extends TwoDShape {

    String style;

    Triangle(int data) {
        System.out.println("triangle have parameter");
    }

    double area() {
        return width * height / 2;
    }

    void showStyle() {
        System.out.println("Triangle is " + style);
    }

}
