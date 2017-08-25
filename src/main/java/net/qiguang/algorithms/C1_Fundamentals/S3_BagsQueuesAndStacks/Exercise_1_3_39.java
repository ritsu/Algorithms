package net.qiguang.algorithms.C1_Fundamentals.S3_BagsQueuesAndStacks;

import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * 1.3.39 Ring buffer.
 * A ring buffer, or circular queue, is a FIFO data structure of a fixed size N. It is useful for
 * transferring data between asynchronous processes or for storing log files. When the buffer is
 * empty, the consumer waits until data is deposited; when the buffer is full, the producer waits
 * to deposit data. Develop an API for a RingBuffer and an implementation that uses an array
 * representation (with circular wrap-around).
 *
 * public class CircularQueue<Item> implements CircularQueue<Item>
 * ------------------------------------------------------------------------------
 *         CircularQueue(int n)  create an empty CircularQueue with maximum size n
 * boolean isEmpty()             is the CircularQueue empty?
 * boolean isFull()              is the CircularQueue full?
 *     int size()                number of items in the CircularQueue
 *     int maxSize()             maximum size of CircularQueue
 *    void enqueue(Item item)    add an item to the CircularQueue; throw exception if queue is full
 *    void dequeue(Item item)    remove an item from the CircularQueue (FIFO)
 */
public class Exercise_1_3_39 {
    public static class CircularQueue<Item> implements Iterable<Item> {
        private Item[] items;
        private int max;
        private int start;
        private int N;

        public CircularQueue(int m) {
            max = m;
            items = (Item[]) new Object[max];
            start = 0;
            N = 0;
        }
        public boolean isEmpty() {
            return N == 0;
        }
        public boolean isFull() {
            return N == max;
        }
        public int size() {
            return N;
        }
        public int maxSize() {
            return max;
        }
        public void enqueue(Item item) {
            if (isFull()) {
                throw new UnsupportedOperationException("Queue is full");
            }
            items[(start + N) % max] = item;
            N++;
        }
        public Item dequeue() {
            if (isEmpty()) {
                throw new UnsupportedOperationException("Queue is empty");
            }
            Item item = items[start++];
            if (start == max) start = 0;
            N--;
            return item;
        }
        public String toString() {
            String s = "";
            for (Item item : this) {
                s += item + " ";
            }
            return s;
        }

        public Iterator<Item> iterator() {
            return new CircularQueueIterator();
        }
        private class CircularQueueIterator implements Iterator<Item> {
            int current;
            public CircularQueueIterator() {
                current = 0;
            }
            public boolean hasNext() {
                return current < N;
            }
            public Item next() {
                if (current == N)
                    throw new NoSuchElementException("Cannot call next() on last item in deque");
                return items[(start + current++) % max];
            }
            public void remove() {
                throw new UnsupportedOperationException("Cannot remove in iterator");
            }
        }
    }
    public static void main(String[] args) {
        // Create queue
        CircularQueue<Integer> c = new CircularQueue<>(10);
        for (int i = 0; i < 10; i++) {
            c.enqueue(i);
        }
        StdOut.println(c);

        StdOut.println("\nTest dequeue():");
        for (int i = 0; i < 5; i++) {
            StdOut.printf("(%d) %s\n", c.dequeue(), c);
        }

        StdOut.printf("\nSize: %d, MaxSize: %d\n", c.size(), c.maxSize());

        StdOut.println("\nTest enqueue():");
        for (int i = 10; i < 15; i++) {
            c.enqueue(i);
            StdOut.printf("(%d) %s\n", i, c);
        }

    }
}
