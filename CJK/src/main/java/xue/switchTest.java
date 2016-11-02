package xue;

/**
 * Test switch syn
 * note: break is necessary, if one case is satisfied, will not justify the case
 * anymore.
 * 
 * for example day = 5; output will be 
 * today is Friday
 * today is Saturday 
 * taday is Sunday
 *
 * @author jkchang
 */
public class switchTest {

    public static void main(String[] args) {
        String day = "8";
        switch (day) {
            case "1":
                System.out.println("today is Monday");
                break;
            case "2":
                System.out.println("today is Tuesday");
                break;
            case "3":
                System.out.println("today is Wednesday");
                break;
            case "4":
                System.out.println("today is Tuesday");
                break;
            case "5":
                System.out.println("today is Friday");
                break;
            case "6":
                System.out.println("today is Saturday");
                break;
            case "7":
                System.out.println("today is Sunday");
                break;
            default:
                throw new IllegalArgumentException("Invalid day of the week: " + day);
        }
    }

}
