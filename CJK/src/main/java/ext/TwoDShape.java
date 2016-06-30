/* ----------------------------------------------------------------------------

   ---------------------------------------------------------------------------- 
 @author jkchang
 @date 30-Jun-2016
 */
package ext;

public class TwoDShape {

    private double width;
    private double height;

    TwoDShape() {
        System.out.println("dont have parameter");
    }
        TwoDShape(int d) {
            System.out.println("have parameter");
    }

    void showDim() {
        System.out.println("Width and height are " + width + "and" + height);
    }

    double area() {
        return width * height;
    }

}
