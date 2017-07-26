package application;

/**
 * Created by nicholas on 7/24/17.
 */
public class ListNode {
    ListNode right;
    double value;
    int id;

    public ListNode(double value, int id){
        this.id = id;
        this.value = value;
    }

    public ListNode getRight(){
        return this.right;
    }


    public double getValue(){
        return this.value;
    }


    public void setRight(ListNode node){
        this.right = node;
    }

    public int getID(){
        return id;
    }


}
