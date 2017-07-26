package application;

/**
 * Created by nicholas on 7/24/17.
 */
public class OrderedList {
    ListNode head;
    ListNode tail;
    int size;

    public void add(double element,int id){
        size++;
        ListNode newNode = new ListNode(element,id);
        if(head == null) {
            head = newNode;
            tail = newNode;
        }else if(element < head.getValue()){
            newNode.setRight(head);
            head = newNode;
            return;
        }else{
            ListNode current = head;
            ListNode previous = head;
            while(current != null){
                if(element > current.getValue()){
                    previous = current;
                    current = current.getRight();
                }else{
                    previous.setRight(newNode);
                    newNode.setRight(current);
                    return;
                }
            }
            if(tail.getValue() < element){
                tail.setRight(newNode);
                tail = newNode;
            }
        }
    }


    public ListNode get(int index){
        ListNode current = head;
        int count = 0;
        while(count < index){
            current = current.getRight();
            count++;
        }

        return current;
    }

    public int size(){
        return this.size;
    }

    public String toString(){
        ListNode current = head;
        StringBuilder output = new StringBuilder();
        while(current != null){
            output.append(current.getValue() + ",");
            current = current.getRight();
        }
        return String.valueOf(output);
    }
}
