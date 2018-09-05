package Demo;

/**
 *
 * @author jkchang
 * @date 05-Sep-2018
 * @Description:
 *
 */
public class linked_demo {
    
    public static void main(String[] args) {
        Link link = new Link();
        link.add("hello");
        link.add("world");
        link.add("this");
        link.add("is");
        link.add("the");
        link.add("end");
        
        link.print();
    } 
    
}


class Link { //负责数据的设置和输出

    public void add(String data) {
        
    }

    public void print() {
        
    }
    
}

class Node {
    
    private String data;
    private Node next;
    
    public Node(String data) {
        this.data = data;
    }
    
    public void setNext(Node next) {
        this.next = next;
    }
    
    public Node getNext() {
        return this.next;
    }
    
    public String getData() {
        return this.data;
    }
}
