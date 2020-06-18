import java.util.Iterator;
import java.util.NoSuchElementException;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Deque<Item> implements Iterable<Item> {
    private Node first;
    private Node last;
    private int size;

    private class DequeIterator implements Iterator<Item> {
        private Node cur = first;

        public boolean hasNext(){
            return (cur != null);
        }

        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            Item ret = cur.item;
            cur = cur.next;
            return ret;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    private class Node{
        Item item = null;
        Node next = null;
        Node prev = null;
    }


    // construct an empty deque
    public Deque(){
        size = 0;
        first = null;
        last = null;
    }

    // is the deque empty?
    public boolean isEmpty(){
        return (size == 0);
    }


    // return the number of items on the deque
    public int size(){
        return size;
    }

    // add the item to the front
    public void addFirst(Item item){
        if (item == null){
            throw new IllegalArgumentException();
        }
        Node oldFirst  = first;
        first = new Node();
        first.item = item;
        first.next  = oldFirst; //null if empty
        first.prev = null;
        if (isEmpty()) {
            last = first;
        }
        else{
            oldFirst.prev = first;
        }
        size++;
    }

    // add the item to the back
    public void addLast(Item item){
        if (item == null){
            throw new IllegalArgumentException();
        }
        Node oldLast = last;
        last = new Node();
        last.item = item;
        last.next = null;
        last.prev = oldLast; //null if empty
        if(isEmpty()){
            first = last;
        }
        else{
            oldLast.next = last;
        }
        size++;
    }

    // remove and return the item from the front
    public Item removeFirst(){
        if (!isEmpty()){
            Item ret = first.item;
            size --;
            if (isEmpty()){
                first = null;
                last = null;
            }
            else {
                first = first.next;
                first.prev = null;
            }
            return ret;
        }
        throw new NoSuchElementException();
    }

    // remove and return the item from the back
    public Item removeLast(){
        if (!isEmpty()){
            Item ret = last.item;
            size --;
            if(isEmpty()){
                first = null;
                last = null;
            }
            else {
                last = last.prev;
                last.next = null;
            }
            return ret;
        }
        throw new NoSuchElementException();
    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator(){
        return new DequeIterator();
    }

    // unit testing (required)
    public static void main(String[] args) {
        Deque<Integer> deque = new Deque<Integer>();
        deque.addFirst(1);
        deque.addLast(2);
        deque.addFirst(-1);
        deque.addLast(-2);
        deque.removeFirst();
        deque.removeLast();
        System.out.print("expected: 1 false 2 false\n  actual:");
        for (int i : deque){
            System.out.print(" " + i + " " + deque.isEmpty());
        }

    }

}
