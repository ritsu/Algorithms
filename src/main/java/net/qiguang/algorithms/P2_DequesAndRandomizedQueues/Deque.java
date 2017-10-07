package net.qiguang.algorithms.P2_DequesAndRandomizedQueues;

import java.util.Iterator;

public class Deque<Item> implements Iterable<Item> {
    private Node first;
    private Node last;
    private int N;
    
    // construct an empty deque
    public Deque() {
        first = null;
        last = null;
        N = 0;
    }
    
    private class Node {
        private Item item;
        private Node next;
        private Node prev;
    }
    
    // is the deque empty?
    public boolean isEmpty() {
        return N == 0;
    }
    
    // return the number of items on the deque
    public int size() {
        return N;
    }
    
    // add the item to the front
    public void addFirst(Item item) {
        if (item == null)
            throw new java.lang.NullPointerException("Cannot add null item");
        Node oldfirst = first;
        first = new Node();
        first.item = item;
        if (isEmpty()) {
            last = first;
        }
        else {
            first.next = oldfirst;
            oldfirst.prev = first;
        }
        N++;
    }
    
    // add the item to the end
    public void addLast(Item item) {
        if (item == null)
            throw new java.lang.NullPointerException("Cannot add null item");
        Node oldlast = last;
        last = new Node();
        last.item = item;
        if (isEmpty()) {
            first = last;
        }
        else {
            oldlast.next = last;
            last.prev = oldlast;
        }
        N++;
    }
    
    // remove and return the item from the front
    public Item removeFirst() {
        if (isEmpty())
            throw new java.util.NoSuchElementException(
                "Cannot remove from empty deque");
        Item item = first.item;
        first = first.next;
        N--;
        if (isEmpty()) {
            last = null;
        }
        else {
            first.prev = null;
        }
        return item;
    }
    
    // remove and return the item from the end
    public Item removeLast() {
        if (isEmpty())
            throw new java.util.NoSuchElementException(
                "Cannot remove from empty deque");
        Item item = last.item;
        last = last.prev;
        N--;
        if (isEmpty()) {
            first = null;
        }
        else {
            last.next = null;
        }
        return item;
    }
    
    // return an iterator over items in order from front to end
    public Iterator<Item> iterator() {
        return new DequeIterator();
    }
    
    private class DequeIterator implements Iterator<Item> {
        private Node current = first;
        
        public boolean hasNext() {
            return current != null;
        }
        
        public void remove() {
            throw new java.lang.UnsupportedOperationException(
                "Cannot remove in iterator");
        }
        
        public Item next() {
            if (current == null)
                throw new java.util.NoSuchElementException(
                    "Cannot call next() on last item in deque");
            Item item = current.item;
            current = current.next;
            return item;
        }
    } 
    
    public static void main(String[] args) {
    }
}