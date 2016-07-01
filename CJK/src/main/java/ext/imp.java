/* ----------------------------------------------------------------------------

   ---------------------------------------------------------------------------- 
 @author jkchang
 @date 30-Jun-2016
 */
package ext;

public class imp {

    public static void main(String[] args) {
        double t1_width = 4d; //SUPER
        double t1_height = 4d; //SUPER
        String t1_style = "filled"; //SUB

        double t2_width = 8d; //SUPER
        double t2_height = 12d; //SUPER
        String t2_style = "outlined"; //SUB

        Triangle t1 = new Triangle(t1_style, t1_width, t1_height);
        Triangle t2 = new Triangle(t2_style, t2_width, t2_height);

        System.out.println("Info for Triangle 1: ");
        t1.showDim();  //SUPER
        t1.showStyle();//SUB
        System.out.println("The area is " + t1.area()); //SUB

        System.out.println("");

        System.out.println("Info for Triangle 2: ");
        t2.showDim();//SUPER
        t2.showStyle(); //SUB
        System.out.println("The are is " + t2.area()); //SUB
    }
}
