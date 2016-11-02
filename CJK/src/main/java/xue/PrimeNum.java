package xue;

/**
 * List all the prime numbers in a period
 * @author jkchang
 */
public class PrimeNum {
    
    public static void main(String[] args) {
        int endNum = 100;
        boolean isPrime;
        
        for (int i = 2; i < endNum; i++) {
            isPrime = true;
            
            for (int j = 2; j < i; j++) {
                if (i % j == 0) {
                    isPrime = false;
                    break;
                }
            }
            
            if (isPrime) {
                System.out.println(i + "\t");
            }
        }
    }
    
}
