import java.util.LinkedList;
public class Queue{
    
    protected LinkedList list;

    public Queue(){
        list = new LinkedList();
    }//default constructor
    
    public boolean isEmpty(){
        return list.isEmpty();
    }//method isEmpty

    public int size(){
        return list.size();
    }//method size

    public void enqueue(Object element){
        list.addLast(element);
    }//method enqueue

    public Object dequeue(){
        return list.removeFirst();
    }//method dequeue

    public Object front(){
        return list.getFirst();
    }//method front 

    public Object rear(){
        return list.getLast();
    }
}