import edu.princeton.cs.algs4.StdRandom;
import java.util.Iterator;
import edu.princeton.cs.algs4.StdOut;
/**
 * @author DiamondUser
 * </>
 * Programming project for Princeton Algorithm 1
 */
public class RandomizedQueue<Item> implements Iterable<Item> {
    private Item[] items;
    private int size;
    // construct an empty randomized queue
    public RandomizedQueue() {
        items = (Item[]) new Object[1];
        size = 0;
    }

    /**
     *
     * @return if the RandomizedQueue is empty.
     */
    public boolean isEmpty() { return size == 0; }

    // return the number of items on the randomized queue
    public int size() { return size; }

    // add the item
    public void enqueue(Item item) {
        if(item == null) throw new  IllegalArgumentException();
        items[size++] = item;
        if(size == items.length) resize(items.length * 2);
    }
    // remove and return a random item
    public Item dequeue() {
        if(isEmpty()) throw new java.util.NoSuchElementException();
        int randomIndex = StdRandom.uniformInt(size);
        Item retItem = items[randomIndex];
        items[randomIndex] = items[--size];
        return retItem;
    }
    // return a random item (but do not remove it)
    public Item sample() {
        if(isEmpty()) throw new java.util.NoSuchElementException();
        return items[StdRandom.uniformInt(size)];
    }

    /**
     * @return an independent iterator over items in random order
     */
    public Iterator<Item> iterator() { return new RandomizedQueueIterator(); }
    private class RandomizedQueueIterator implements Iterator<Item> {
        private int[] randomOrder;
        private int index = 0;
        public RandomizedQueueIterator() {
            randomOrder = new int[size];
            for( int i = 0;i<size;i++) {
                randomOrder[i] = i;
            }
            StdRandom.shuffle(randomOrder);
        }
        @Override
        public boolean hasNext() {
            return index < size;
        }
        @Override
        public Item next() {
            if(!hasNext()) throw new java.util.NoSuchElementException();
            return items[randomOrder[index++]];
        }
        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }
    private void resize(int new_capacity) {
        Item[] copy = (Item[]) new Object[new_capacity];
        for(int i = 0; i < size ; i++ ) {
            copy[i] = items[i];
        }
        items = copy;
    }
    // unit testing (required)
    public static void main(String[] args) {
        RandomizedQueue<Integer> randQueue = new RandomizedQueue<>();
        for(int i = 1;i != 5 ;i++) {
            randQueue.enqueue(i);
        }
        for(int i = 0;i<3;i++) {
            StdOut.println(randQueue.sample());
        }
        StdOut.println();
        StdOut.println(randQueue.dequeue());
        StdOut.println();
        for(int num : randQueue) {
            StdOut.println(num);
        }
        if(!randQueue.isEmpty()) StdOut.println("size of the RandomizedDeque is " + randQueue.size());
    }

}