package Ext;

/**
 *
 * @author jkchang
 * @date 14-Mar-2017
 * @Description:
 *
 */
public class Student extends Person {

    int id;
    String school;

    public void setName() {
        name = "David";
    }
    
    public void print(){
        System.out.println("This is also a Student");
          super.print();
          
    }
  
    
    public static void main(String[] args) {
        Student st = new Student();
        st.print();
    }

}
