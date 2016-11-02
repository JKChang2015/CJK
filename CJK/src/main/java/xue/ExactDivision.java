package xue;

/**
 * List all the numbers in a interval that can be exact divided by a certain
 * number
 *
 * @author jkchang
 */
public class ExactDivision {

    public static void main(String[] args) {
        int srtNum = 1;
        int endNum = 100;
        int dividend = 7;

        for (int i = srtNum; i < endNum; i++) {
            
            if (i % dividend == 0) {
                System.out.print(i + "\t");              
            }
        }
    }
}
