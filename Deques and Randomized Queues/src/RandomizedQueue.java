import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private Item[] array;
    private int size;

    private class RandomizedQueueIterator implements Iterator<Item> {
        int cur = 0;
        Item[] toDequeue = array.clone();

        public boolean hasNext(){
            return (cur < size);
        }
        public Item next(){
            if (!hasNext()){
                throw new NoSuchElementException();
            }
            int random = (int)(Math.random() * (size-cur));
            Item last = toDequeue[size-cur-1];
            Item ret = toDequeue[random];
            toDequeue[random] = last;
            cur++;
            return ret;
        }
        public void remove(){
            throw new UnsupportedOperationException();
        }
    }

    // construct an empty randomized queue
    public RandomizedQueue(){
        array = (Item[]) new Object[1];
        size = 0;
    }

    private Item[] resize(Item[] array, int size) {
        Item[] newArray = (Item[]) new Object[size];
        for (int i = 0; i < size && i < array.length; i++){
            newArray[i] = array[i];
        }
        return newArray;
    }

    // is the randomized queue empty?
    public boolean isEmpty(){
        return (size == 0);
    }

    // return the number of items on the randomized queue
    public int size(){
        return size;
    }

    // add the item
    public void enqueue(Item item){
        if (item == null){
            throw new IllegalArgumentException();
        }
        if (size >= array.length-1){
            array = resize(array, array.length*2);
        }
        array[size] = item;
        size ++;
    }

    // remove and return a random item
    public Item dequeue(){
        if (isEmpty()){
            throw new NoSuchElementException();
        }
        int random = (int)(Math.random() * size);
        Item last = array[size-1];
        Item toRemove = array[random];
        array[random] = last;
        size --;
        array[size] = null;
        if (size <= array.length/4){
            array = resize(array, array.length/2);
        }
        return toRemove;
    }

    // return a random item (but do not remove it)
    public Item sample(){
        if (isEmpty()){
            throw new NoSuchElementException();
        }
        int random = (int)(Math.random() * size);
        return array[random];
    }


    // return an independent iterator over items in random order
    public Iterator<Item> iterator(){ return new RandomizedQueueIterator();}

    // unit testing (required)
    public static void main(String[] args) {
        RandomizedQueue<Integer> queue = new RandomizedQueue<>();
            for (int i = 0; i < 10; i++){
                queue.enqueue(i);
            }
            System.out.print("5 random numbers 0 - 9: ");
            for (int i = 0; i < 5; i++){
                System.out.print( queue.dequeue() + " ");
            }
        System.out.print("\nThe other 5 numbers in random order: ");
        for (int i : queue){
            System.out.print(i + " ");
        }
        System.out.println("\nExpected: false     Actual: " + queue.isEmpty());
    }
}
