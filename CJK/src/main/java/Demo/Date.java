package Demo;

/**
 *
 * @author JKChang
 * @date 23-Nov-2016
 * @Description:
 *
 */

public class Date {
    
   public enum weekDayEnum{
       Mon, Tue, Wed, Thu, Fri, Sat, Sun
   }
   
   private String description;
   private int i=4;
   
   private  weekDayEnum(){
       
   }
   
    public static void main(String[] args) {
        Boolean res;
        String test = "Mon";
        for (int i = 0; i < weekDayEnum.values().length; i++) {
            System.out.println(Integer.toString(weekDayEnum.values()[i].ordinal()) + ".  " +weekDayEnum.values()[i]);
        }
    }
   
}
