
public class LinkedList{
     Node head;
     Node current;
     Node last;

    public LinkedList(){
        head = current = last = null;
    }

    public boolean isEmpty(){
        return head == null;
    }

    public Object getNext(){
        if(current != last){
            current = current.next;
            return current;
        }
        else{
            return null;
        }
    }

    public Object getFirst(){
        if(isEmpty()){
            return null;
        }
        else{
            current = head;
            return current;
        }
    }

    public Object getLast(){
        if(isEmpty()){
            return null;
        }
        else{
            return last.data;
        }
    }

    public void insertAtFront(Object data){
        if(isEmpty()){
            head = last = new Node(data);
        }
        else{
            head = new Node(data,head);
        }
    }

    public void insertAtBack(Object data){
        if(isEmpty()){
            head = last = new Node(data);
        }
        else{
            last = last.next = new Node(data);
        }
    }

    public void removeAtFront() throws EmptyListException{
        if(isEmpty()){
            throw new EmptyListException();
        }
        else{
            if(head.equals(last)){
                head = last = null;
            }
            else{
                head = head.next;
            }
        }
    }

    public void removeAtBack() throws EmptyListException{
        if(isEmpty()){
            throw new EmptyListException();
        }
        else{
            if(head.equals(last)){
                head = last = null;
            }
            else{
                current = head;
                while(current.next != last){
                    current = current.next;
                }
                last = current;
                last.next = null;
            }  
        }
    }

    public Node sortedMerge(Node a, Node b){ 

        Node result = null; 
        if (a == null) 
            return b; 
        if (b == null) 
            return a; 
  
        Volunteer tempA = (Volunteer) a.data;
        Volunteer tempB = (Volunteer) b.data;
        if (tempA.getHour() >= tempB.getHour()){ 
            result = a; 
            result.next = sortedMerge(a.next, b); 
        } 
        else{ 
            result = b; 
            result.next = sortedMerge(a, b.next); 
        } 
        return result; 
    } 

    public Node mergeSort(Node h){ 
        
        if (h == null || h.next == null){ 
            return h; 
        } 
  
        // get the middle of the list 
        Node middle = getMiddle(h); 
        Node nextofmiddle = middle.next; 
  
        // set the next of middle node to null 
        middle.next = null; 
  
        // Apply mergeSort on left list 
        Node left = mergeSort(h); 
  
        // Apply mergeSort on right list 
        Node right = mergeSort(nextofmiddle); 
  
        // Merge the left and right lists 
        Node sortedlist = sortedMerge(left, right); 
        return sortedlist; 
    } 

    public Node getMiddle(Node h){ 
        // Base case 
        if (h == null) 
            return h; 
        Node fastptr = h.next; 
        Node slowptr = h; 
  
        // Move fastptr by two and slow ptr by one 
        // Finally slowptr will point to middle node 
        while (fastptr != null) { 
            fastptr = fastptr.next; 
            if (fastptr != null) { 
                slowptr = slowptr.next; 
                fastptr = fastptr.next; 
            } 
        } 
        return slowptr; 
    } 

    public void push(Volunteer new_data){ 
        /* allocate node */
        Node new_node = new Node(new_data); 
  
        /* link the old list off the new node */
        new_node.next = head; 
  
        /* move the head to point to the new node */
        head = new_node; 
    } 

    public void printList(Node head){
        while(head != null){
            Volunteer data = (Volunteer) head.data;
            System.out.println("\n");
            System.out.println(data.toString());
            head = head.next;
        }
    }

    /*private Node mergeSortLinkList(Node startNode){
   
        //Break the list until list is null or only 1 element is present in List.
        if(startNode==null || startNode.getNext()==null){
         return startNode;
        }
       
        //Break the linklist into 2 list.
        //Finding Middle node and then breaking the Linled list in 2 parts.
        //Now 2 list are, 1st list from start to middle and 2nd list from middle+1 to last.
         
        Node middle = getMiddle(startNode);
        Node nextOfMiddle = middle.getNext();
        middle.setNext(null);
       
        //Again breaking the List until there is only 1 element in each list.
        Node left = mergeSortLinkList(startNode);
        Node right = mergeSortLinkList(nextOfMiddle);
       
        //Once complete list is divided and contains only single element, 
        //Start merging left and right half by sorting them and passing Sorted list further. 
        Node sortedList = mergeTwoListRecursive(left, right);
         
        return sortedList;
       }
       
       //Recursive Approach for Merging Two Sorted List
       private Node mergeTwoListRecursive(Node leftStart, Node rightStart){
        if(leftStart==null)
         return rightStart;
         
        if(rightStart==null)
         return leftStart;
         
         Node temp=null;
   
  if(leftStart.getData()<rightStart.getData()){
   temp=leftStart;
   temp.setNext(mergeTwoListRecursive(leftStart.getNext(), rightStart));
  }else{
   temp=rightStart;
   temp.setNext(mergeTwoListRecursive(leftStart, rightStart.getNext()));
  }
  return temp;
 }
 
 private Node getMiddle(Node startNode) {
  if(startNode==null){
   return startNode;
  }
 
  Node pointer1=startNode;
  Node pointer2=startNode;
   
  while(pointer2!=null && pointer2.getNext()!=null && pointer2.getNext().getNext()!=null){
   pointer1 = pointer1.getNext();
   pointer2 = pointer2.getNext().getNext();
 
  }
  return pointer1;
 }
 
 private void printLinkList(Node startNode) {
  Node temp = startNode;
  while(temp!=null){
   System.out.print(temp.getData() + " ");
   temp = temp.getNext();
  }
 }
  
}
         */
 }