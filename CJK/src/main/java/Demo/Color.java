package Demo;

/**
 *
 * @author JKChang
 * @date 22-Nov-2016
 * @Description:
 *
 */
public enum Color {

    RED("Hong Se", 1), GREEN("Lv Se", 2), BLANK("Bai Se", 3), YELLO("Huang Se", 4);

    private String name;
    private int index;

    // Construction function
    private Color(String name, int index) {
        this.name = name;
        this.index = index;
    }

    public static String getName(int index) {
        for (Color c : Color.values()) {
            if (c.getIndex() == index) { // 1<=index<=4
                return c.name;
            }
        }
        return null;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }
    
    
    
    


    

}
