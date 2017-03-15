/* ----------------------------------------------------------------------------

   ---------------------------------------------------------------------------- 
 @author jkchang
 @date 30-Jun-2016
*/

package Ext;


public class ColorTriangle extends Triangle{
    private String color;
    
    ColorTriangle(String color, String style, double width, double height){
      // super(style, width, height);
        this.color = color;
    }
    
    double getArea(){
        return super.area();
    }
    
    String getStyle(){
        return super.style;
    }

}
