package net.qiguang.algorithms.C1_Fundamentals.S3_BagsQueuesAndStacks;

import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * 1.3.14
 * Develop a class ResizingArrayQueueOfStrings that implements the queue abstraction with a fixed-size array,
 * and then extend your implementation to use array resizing to remove the size restriction.
 */
public class Exercise_1_3_14 {
    public static class ResizingArrayQueueOfStrings implements Iterable<String>{
        int start;
        int end;
        String[] queue;

        public ResizingArrayQueueOfStrings() {
            start = 0;
            end = 0;
            queue = new String[2];
        }

        public void resize(int n) {
            String[] copy = new String[n];
            for (int i = start; i < end; i++) {
                copy[i - start] = queue[i];
            }
            queue = copy;
            end = end - start;
            start = 0;
        }

        public void enqueue(String s) {
            if (end == queue.length) {
                resize(queue.length * 2);
            }
            queue[end++] = s;
        }

        public String dequeue() {
            String s = queue[start++];
            if ( !isEmpty() && (end - start) < queue.length / 4 ) {
                resize(queue.length / 2);
            }
            return s;
        }

        public int size() {
            return end - start;
        }

        public boolean isEmpty() {
            return end - start > 0;
        }

        public String toString() {
            String s = "";
            for (int i = start; i < end; i++) {
                s += queue[i] + " ";
            }
            return s;
        }

        public Iterator<String> iterator() {
            return new QueueIterator();
        }
        private class QueueIterator implements Iterator<String> {
            private int current;
            public QueueIterator() {
                current = start;
            }
            public boolean hasNext() {
                return current < end;
            }
            public void remove() {
                throw new UnsupportedOperationException();
            }
            public String next() {
                if (!hasNext()) throw new NoSuchElementException();
                return queue[current++];
            }
        }
    }

    public static void main(String[] args) {
        ResizingArrayQueueOfStrings a = new ResizingArrayQueueOfStrings();

        // Add 0...9 to queue
        for (int i = 0; i < 10; i++) {
            a.enqueue(String.valueOf(i));
        }
        StdOut.print("Queue: ");
        for (String s : a) {
            StdOut.print(s + " ");
        }
        StdOut.printf("\nSize: %d\n", a.size());

        // Dequeue 5 items
        StdOut.print("Dequeue: ");
        for (int i = 0; i < 5; i++) {
            StdOut.print(a.dequeue() + " ");
        }
        StdOut.printf("\nQueue: %s\nSize: %d\n\n", a, a.size());

        // Add 0...99 to queue
        for (int i = 0; i < 100; i++) {
            a.enqueue(String.valueOf(i));
        }
        StdOut.print("Queue: ");
        for (String s : a) {
            StdOut.print(s + " ");
        }
        StdOut.printf("\nSize: %d\n", a.size());

        // Dequeue 90 items
        StdOut.print("Dequeue: ");
        for (int i = 0; i < 90; i++) {
            StdOut.print(a.dequeue() + " ");
        }
        StdOut.printf("\nQueue: %s\nSize: %d\n\n", a, a.size());

    }
}
