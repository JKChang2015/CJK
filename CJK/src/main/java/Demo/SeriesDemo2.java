package Demo;

/**
 *
 * @author jkchang
 * @date 31-Aug-2018
 * @Description:
 *
 */
public class SeriesDemo2 {

    public static void main(String[] args) {
        ByTwo twoOb = new ByTwo();
        ByThree threeOb = new ByThree();
        Series ob;

        for (int i = 0; i < 3; i++) {
            ob = twoOb;
            System.out.println("Next byTwo value is " + ob.getNext());

            ob = threeOb;
            threeOb.print();
            System.out.println("Next byThree value is " + ob.getNext());
            
        }
    }

}

interface Series {

    public abstract int getNext();

    public abstract void reset();

    public abstract void setStart(int x);
}

class ByTwo implements Series {

    int start;
    int val;

    public ByTwo() {
        start = 0;
        val = 0;
    }

    @Override
    public int getNext() {
        val += 2;
        return val;
    }

    @Override
    public void reset() {
        val = start;

    }

    @Override
    public void setStart(int x) {
        start = x;
        val = x;
    }

}

class ByThree implements Series {

    int start;
    int val;

    public ByThree() {
        start = 0;
        val = 0;
    }

    @Override
    public int getNext() {
        val += 3;
        return val;
    }

    @Override
    public void reset() {
        val = start;

    }

    @Override
    public void setStart(int x) {
        start = x;
        val = x;
    }
    
    public void print(){
        System.out.println("Some message from byThree");
    }

}
