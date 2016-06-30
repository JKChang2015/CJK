/* ----------------------------------------------------------------------------

   ---------------------------------------------------------------------------- 
 @author jkchang
 @date 30-Jun-2016
 */
package ext;

public class TwoDShape {

    double width;
    double height;

    void showDim() {
        System.out.println("Width and height are " + width + "and" + height);
    }
    
    double area(){
        return width * height;
    }

}
