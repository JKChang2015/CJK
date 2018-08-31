package Demo;

class FixedQueue implements ICharQ {

    private char q[];
    private int putloc, getloc;

    // constructor
    public FixedQueue(int size) {
        q = new char[size];
        putloc = getloc = 0;
    }

    @Override
    public void put(char ch) {
        if (putloc == q.length) {
            System.out.println("- Queue is full.");
            return;
        }
        q[putloc++] = ch;
    }

    @Override
    public char get() {
        if (getloc == putloc) {
            System.out.println(" - Queue is empty.");
            return (char) 0;
        }
        return q[getloc++];
    }
}

class CircularQueue implements ICharQ {

    private char q[];
    private int getloc, putloc;

    public CircularQueue(int size) {
        q = new char[size + 1];
        getloc = putloc = 0;
    }

    @Override
    public void put(char ch) {
        /* Queue is full if either putloc is one less than getloc, or if putloc is at the end of the array and getloc is at the
        beginning. */
        if (putloc + 1 == getloc | ((putloc == q.length - 1) & (getloc == 0))) {
            System.out.println(" - Queue is full");
            return;
        }
        q[putloc++] = ch;
        if (putloc == q.length) {
            putloc = 0;
        }
    }

    @Override
    public char get() {
        if (getloc == putloc) {
            System.out.println(" - Queue is empty.");
            return (char) 0;
        }

        char ch = q[getloc++];
        if (getloc == q.length) {
            getloc = 0; // loop back 
        }
        return ch;
    }

}

// A dynamic queue.
class DynQueue implements ICharQ {

    private char q[]; // this array holds the queue 
    private int putloc, getloc; // the put and get indices

    public DynQueue(int size) {
        q = new char[size]; // allocate memory for queue
        putloc = getloc = 0;
    }

    public void put(char ch) {

        if (putloc == q.length) { // increase queue size 
            char t[] = new char[q.length * 2];

            // copy elements into new queue 
            for (int i = 0; i < q.length; i++) {
                t[i] = q[i];
            }
            q = t;
        }
        q[putloc++] = ch;
    }

// Get a character from the queue. 
    public char get() {
        if (getloc == putloc) {
            System.out.println(" - Queue is empty.");
            return (char) 0;
        }
        return q[getloc++];
    }

}

public class IQDemo {

    public static void main(String[] args) {

        FixedQueue q1 = new FixedQueue(10);
        DynQueue q2 = new DynQueue(5);
        CircularQueue q3 = new CircularQueue(10);

        ICharQ iQ;

        char ch;
        int i;

        // ============================
        iQ = q1;
        // put something to q1
        for (i = 0; i < 10; i++) {
            iQ.put((char) ('A' + i));
        }
        System.out.print("Contents of fixed queue: ");
        for (i = 0; i < 10; i++) {
            ch = iQ.get();
            System.out.print(ch);
        }
        System.out.println();

        // ============================
        iQ = q2;
        for (i = 0; i < 10; i++) {
            iQ.put((char) ('Z' - i));
        }
        // Show the queue.

        System.out.print("Contents of dynamic queue: ");
        for (i = 0; i < 10; i++) {
            ch = iQ.get();
            System.out.print(ch);
        }
        System.out.println();

        // ============================
        iQ = q3;
        for (i = 0; i < 10; i++) {
            iQ.put((char) ('A' + i));
        }

        System.out.print("Contents of circular queue: ");
        for (i = 0; i < 10; i++) {
            ch = iQ.get();
            System.out.print(ch);
        }
        System.out.println();

        // Put more characters into circular queue. 
        for (i = 10; i < 20; i++) {
            iQ.put((char) ('A' + i));
        }
        System.out.print("Contents of circular queue: ");
        for (i = 0; i < 10; i++) {
            ch = iQ.get();
            System.out.print(ch);
        }

        System.out.println("\nStore and consume from" + " circular queue.");

        // Store in and consume from circular queue. 
        for (i = 0; i < 20; i++) {
            iQ.put((char) ('A' + i));
            ch = iQ.get();
            System.out.print(ch);
        }
    }
}
