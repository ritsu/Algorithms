package net.qiguang.algorithms.P2_DequesAndRandomizedQueues;

import edu.princeton.cs.algs4.StdRandom;
import java.util.Iterator;

/**
 * A randomized queue is similar to a stack or queue, except that the item 
 * removed is chosen uniformly at random from items in the data structure.
 * 
 * The order of two or more iterators to the same randomized queue must be 
 * mutually independent; each iterator must maintain its own random order. 
 * 
 * Throw java.lang.NullPointerException if client attempts to add a null item; 
 * 
 * Throw java.util.NoSuchElementException if client attempts to sample or 
 * dequeue an item from an empty randomized queue; 
 * 
 * Throw java.lang.UnsupportedOperationException if client calls the remove() 
 * method in the iterator;
 * 
 * Throw a java.util.NoSuchElementException if the client calls the next()
 * method in the iterator and there are no more items to return.
 * 
 * Performance requirements. Your randomized queue implementation must support 
 * each randomized queue operation (besides creating an iterator) in constant 
 * amortized time. That is, any sequence of M randomized queue operations 
 * (starting from an empty queue) should take at most cM steps in the worst 
 * case, for some constant c. 
 * 
 * A randomized queue containing N items must use at most 48N + 192 bytes of 
 * memory. Additionally, your iterator implementation must support operations 
 * next() and hasNext() in constant worst-case time; and construction in linear 
 * time; you may (and will need to) use a linear amount of extra memory per 
 * iterator.
 */
public class RandomizedQueue<Item> implements Iterable<Item> {        
    private Item[] a;
    private int N;
    
    // construct an empty randomized queue
    public RandomizedQueue() {
        a = (Item[]) new Object[2];
        N = 0;
    }
    
    // is the queue empty?
    public boolean isEmpty() {
        return N == 0;
    }
    
    // return the number of items on the queue
    public int size() {
        return N;
    }

    // Resize queue array
    private void resize(int len) {
        Item[] tmp = (Item[]) new Object[len];
        for (int i = 0; i < N; i++) {
            tmp[i] = a[i];
        }
        a = tmp;
    }
    
    // add the item
    public void enqueue(Item item) {
        if (item == null)
            throw new java.lang.NullPointerException("Cannot add null item");
        if (N == a.length) {
            resize(2*a.length);
        }
        a[N++] = item;
    }
    
    // remove and return a random item
    public Item dequeue() {
        if (isEmpty())
            throw new java.util.NoSuchElementException(
                "Cannot remove from empty queue");        
        int r = StdRandom.uniform(N);
        Item item = a[r];
        // move last item in queue in place of removed item
        if (r < --N) {
            a[r] = a[N];
        }
        a[N] = null;
        // resize if less than 1/4 capacity
        if (N > 0 && N == a.length/4) {
            resize(a.length/2);
        }
        return item;    
    }
    
    // return (but do not remove) a random item
    public Item sample() {
        if (isEmpty())
            throw new java.util.NoSuchElementException(
                "Cannot sample from empty queue");        
        return a[StdRandom.uniform(N)];
    }
    
    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new RandomizedQueueIterator();
    }
    
    private class RandomizedQueueIterator implements Iterator<Item> {
        private Item[] acopy;
        private int M;
        
        public RandomizedQueueIterator() {
            M = N;
            acopy = (Item[]) new Object[N];
            for (int i = 0; i < N; i++) {
                acopy[i] = a[i];
            }
        }
        
        public boolean hasNext() {
            return M > 0;
        }
        
        public void remove() {
            throw new java.lang.UnsupportedOperationException(
                "Cannot remove in iterator");
        }
        
        public Item next() {
            if (M == 0)
                throw new java.util.NoSuchElementException(
                    "Cannot call next() on last item in queue");
            int r = StdRandom.uniform(M);
            Item item = acopy[r];
            if (r < --M) {
                acopy[r] = acopy[M];
            }
            acopy[M] = null;
            return item;
        }
    }
    
    // unit testing
    public static void main(String[] args)
    {
    }
}