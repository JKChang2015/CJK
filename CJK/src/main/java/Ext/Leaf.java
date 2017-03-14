package Ext;

/**
 *
 * @author jkchang
 * @date 14-Mar-2017
 * @Description: java exercise
 *
 */
public class Leaf {

    public static int num ;
    int i = 0;

    Leaf(int i) {
        this.i = i;
    }
    
    Leaf increament(){
        i ++;
        num ++;
        return this;
    }
    
    void print(){
        System.out.println("i = " + i);
    }
    
    public static void main(String[] args) {
        Leaf.num = 0;
        Leaf leaf = new Leaf(100);
        System.out.println("num is: " + leaf.num);
        leaf.print();
        
        leaf.increament().print();
        System.out.println("num is: " + leaf.num);
        leaf.increament().increament().print();
        System.out.println("num is: " + leaf.num);
    }
}
