import java.util.Iterator;
import edu.princeton.cs.algs4.StdOut;
public class Deque<Item> implements Iterable<Item> {
    private class Node {
        private Item data;
        private Node pre,next;
        private Node(Item item) {
            data = item;
            pre = null;
            next = null;
        }
    }
    private Node first,last;
    private int size;
    // construct an empty deque
    public Deque() {
        first = null;
        last = null;
        size = 0;
    }
    // is the deque empty?
    public boolean isEmpty() { return size == 0; }

    // return the number of items on the deque
    public int size() { return size; }

    // add the item to the front
    public void addFirst(Item item) {
        if(item == null) { throw new IllegalArgumentException(); }
        Node oldfirst = first;
        first = new Node(item);
        first.next = oldfirst;
        if(isEmpty()) last = first;
        else first.next.pre = first;
        size++;
    }

    // add the item to the back
    public void addLast(Item item) {
        if(item == null) { throw new IllegalArgumentException(); }
        Node oldlast = last;
        last = new Node(item);
        last.pre = oldlast;
        if(isEmpty()) first = last;
        else last.pre.next = last;
        size++;
    }

    // remove and return the item from the front
    public Item removeFirst() {
        if(isEmpty()) { throw new java.util.NoSuchElementException(); }
        Item retItem = first.data;
        first = first.next;
        size--;
        if(isEmpty()) last = null;
        else first.pre = null;

        return retItem;
    }

    // remove and return the item from the back
    public Item removeLast() {
        if(isEmpty()) { throw new java.util.NoSuchElementException(); }
        Item retItem = last.data;
        last = last.pre;
        size--;
        if(isEmpty()) first = null;
        else last.next = null;
        return retItem;
    }
    private class DequeIterator implements Iterator<Item> {
        private Deque<Item>.Node current = first;

        @Override
        public boolean hasNext() {
            return current != null;
        }
        @Override
        public Item next() {
            if(!hasNext()) throw new java.util.NoSuchElementException();
            Item retItem = current.data;
            current = current.next;
            return retItem;
        }
        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }
    // return an iterator over items in order from front to back
    public Iterator<Item> iterator() {
        return new DequeIterator();
    }
    // unit testing (required)
    public static void main(String[] args){
        Deque<Integer> deque = new Deque<Integer>() ;
        deque.addFirst(2);
        deque.addFirst(3);
        deque.addFirst(4);
        deque.addLast(1);
        for(Integer i : deque) {
            StdOut.println(i);
        }
        deque.removeFirst();
        deque.removeLast();
        deque.removeFirst();
        StdOut.println();
        for(Integer i : deque) {
            StdOut.println(i);
        }
        StdOut.println(deque.isEmpty());
        deque.removeLast();
        StdOut.println(deque.isEmpty());
    }

}