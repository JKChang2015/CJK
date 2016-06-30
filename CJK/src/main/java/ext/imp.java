/* ----------------------------------------------------------------------------

   ---------------------------------------------------------------------------- 
 @author jkchang
 @date 30-Jun-2016
*/

package ext;


public class imp {
    public static void main(String[] args) {
        Triangle t1 = new Triangle();
        Triangle t2 = new Triangle();
        
        t1.width = 4d; //SUPER
        t1.height = 4d; //SUPER
        t1.style = "filled"; //SUB
        
        t2.width = 8d; //SUPER
        t2.height = 12d; //SUPER
        t2.style = "outlined"; //SUB
        
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
