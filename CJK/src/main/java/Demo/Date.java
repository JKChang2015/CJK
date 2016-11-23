package Demo;

/**
 *
 * @author JKChang
 * @date 23-Nov-2016
 * @Description:
 *
 */
public class Date {

    enum weekDayEnum {
        Mon ("hola"), Tue, Wed, Thu, Fri, Sat, Sun;

        private String description;
        private int i = 4;

        private weekDayEnum() {
        }

        private weekDayEnum(String description) {
            this.description = description;
        }

        private weekDayEnum(int i) {
            this.i = this.i + i;
        }

        public String getDescription() {
            return description;
        }

        public int getindex() {
            return i;
        }
    }

    public static void main(String[] args) {
        Boolean res;
        String test = "Mon";
        for (int i = 0; i < weekDayEnum.values().length; i++) {
            System.out.println(Integer.toString(weekDayEnum.values()[i].ordinal()) + ".  " + weekDayEnum.values()[i]);
            System.out.println(weekDayEnum.values()[i] + "   " +weekDayEnum.values()[i].getDescription());
        }
    }

}
