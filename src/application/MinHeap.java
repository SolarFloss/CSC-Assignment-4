package application;

import java.util.Arrays;

/**
 * Created by nicholas on 7/18/17.
 */
public class MinHeap {
    private int size;
    double[] items;
    int pos;
    int capacity;


    public MinHeap(int capacity){
        this.pos = 1;
        this.items = new double[capacity];
        this.size = 0;
        this.capacity = capacity;
    }

    public void insert(double data){
        items[pos++] = data;
        int child = pos - 1;
        int parent = child / 2;
        while(items[parent] > items[child] && parent > 0){
            double tmp = items[parent];
            items[parent] = items[child];
            items[child] = tmp;
            child = parent;
            parent = child/2;
        }
    }



    public String toString(){
        StringBuilder output = new StringBuilder();
        for(int i=1; i< items.length; i++){
            if(items[i] > 0)
                output.append(items[i] + ", ");
        }
        return output.toString();
    }

    public static void main(String[] args){
        MinHeap heap = new MinHeap(10);
        heap.insert(12);
        heap.insert(7);
        heap.insert(6);
        heap.insert(10);
        heap.insert(8);
        heap.insert(20);
        System.out.println(heap.toString());
    }
}

