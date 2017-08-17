package net.qiguang.algorithms.C1_Fundamentals.S3_BagsQueuesAndStacks;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * 1.3.38 Delete kth element.
 * Implement a class that supports the following API:
 *
 * public class GeneralizedQueue<Item>
 * ----------------------------------------------------
 *         GeneralizedQueue() create an empty queue
 * boolean isEmpty()          is the queue empty?
 *    void insert(Item x)     add an item
 *    Item delete(int k)      delete and return the kth least recently inserted item
 *
 *         API for a generic generalized queue
 *
 * First, develop an implementation that uses an array implementation, and then develop one that uses
 * a linked-list implementation. Note: the algorithms and data structures that we introduce in Chapter 3
 * make it possible to develop an implementation that can guarantee that both insert() and delete() take
 * time proportional to the logarithm of the number of items in the queue—see Exercise 3.5.27.
 */
public class Exercise_1_3_38 {
    public static class GeneralizedQueue<Item> implements Iterable<Item> {
        private Item[] items;
        private int N;

        public GeneralizedQueue() {
            items = (Item[]) new Object[4];
            N = 0;
        }

        public boolean isEmpty() { return N == 0; }
        public int size() { return N; }
        public void insert(Item item) {
            items[N++] = item;
            if (items.length == N) {
                resize(items.length * 2);
            }
        }
        // delete(0)    delete most recently inserted item
        // delete(N-1)  delete first inserted item
        public Item delete(int k) {
            if (isEmpty()) throw new java.util.NoSuchElementException("Cannot delete from empty queue");
            if (k < 0 || k >= N) throw new java.util.NoSuchElementException("Index out of range");
            Item item = items[N - 1 - k];
            for (int i = N - 1 - k; i < N; i++) {
                items[i] = items[i + 1];
            }
            N--;
            if (N < items.length / 4 && items.length > 4) {
                resize(items.length / 2);
            }
            return item;
        }
        public void resize(int size) {
            Item[] copy = (Item[]) new Object[size];
            for (int i = 0; i < N; i++) {
                copy[i] = items[i];
            }
            items = copy;
        }
        public String toString() {
            String s = "";
            for (int i = 0; i < N; i++) {
                s += items[i] + " ";
            }
            return s;
        }

        @Override
        public Iterator<Item> iterator() { return new GeneralizedQueueIterator(); }
        public class GeneralizedQueueIterator implements Iterator<Item> {
            int current;

            public GeneralizedQueueIterator() {
                current = 0;
            }
            @Override
            public boolean hasNext() {
                return current < N;
            }
            @Override
            public Item next() {
                return items[current++];
            }
            @Override
            public void remove() {
                throw new UnsupportedOperationException("Cannot remove in iterator");
            }
        }
    }

    public static class GeneralizedQueueLinkedList<Item> implements Iterable<Item> {
        private Node first;
        private int N;

        // construct an empty queue
        public GeneralizedQueueLinkedList() {
            first = null;
            N = 0;
        }

        private class Node {
            private Item item;
            private Node next;
        }

        public boolean isEmpty() { return N == 0; }
        public int size() { return N; }
        public void insert(Item item) {
            if (item == null) throw new NullPointerException("Cannot add null item");
            Node oldfirst = first;
            first = new Node();
            first.item = item;
            first.next = oldfirst;
            N++;
        }
        // delete(0)    delete most recently inserted item
        // delete(N-1)  delete first inserted item
        public Item delete(int k) {
            if (isEmpty()) throw new java.util.NoSuchElementException("Cannot delete from empty queue");
            if (k < 0 || k >= N) throw new java.util.NoSuchElementException("Index out of range");
            Node current = first;
            Item item;
            if (k == 0) {
                item = first.item;
                first = first.next;
            }
            else {
                for (int i = 0; i < k - 1; i++) {
                    current = current.next;
                }
                item = current.next.item;
                current.next = current.next.next;
            }
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

        @Override
        public Iterator<Item> iterator() { return new GeneralizedQueueIterator(); }
        public class GeneralizedQueueIterator implements Iterator<Item> {
            private Node current = first;

            public boolean hasNext() {
                return current != null;
            }

            public void remove() {
                throw new UnsupportedOperationException("Cannot remove in iterator");
            }

            public Item next() {
                if (current == null)
                    throw new NoSuchElementException("Cannot call next() on last item in queue");
                Item item = current.item;
                current = current.next;
                return item;
            }
        }
    }

    public static void main(String[] args) {
        // Test GeneralizedQueue
        StdOut.println("GeneralizedQueue:");
        GeneralizedQueue<Integer> q = new GeneralizedQueue<Integer>();
        for (int i = 0; i < 10; i++) {
            q.insert(i);
        }
        StdOut.println(q);

        // Delete first and last items
        StdOut.printf("\nDelete first and last iteme:\n");
        StdOut.printf("[%d] (%d) %s\n", q.size() - 1, q.delete(q.size() - 1), q);
        StdOut.printf("[%d] (%d) %s\n", 0, q.delete(0), q);

        // Delete random items
        StdOut.printf("\nDelete random items:\n");
        while (!q.isEmpty()) {
            int r = StdRandom.uniform(q.size());
            StdOut.printf("[%d] (%d) %s\n", r, q.delete(r), q);
        }
        StdOut.println();

        // Test GeneralizedQueueLinkedList
        StdOut.println("GeneralizedQueueLinkedList:");
        GeneralizedQueueLinkedList<Integer> q2 = new GeneralizedQueueLinkedList<Integer>();
        for (int i = 0; i < 10; i++) {
            q2.insert(i);
        }
        StdOut.println(q2);

        // Delete first and last items
        StdOut.printf("\nDelete first and last iteme:\n");
        StdOut.printf("[%d] (%d) %s\n", q2.size() - 1, q2.delete(q2.size() - 1), q2);
        StdOut.printf("[%d] (%d) %s\n", 0, q2.delete(0), q2);

        // Delete random items
        StdOut.printf("\nDelete random items:\n");
        while (!q2.isEmpty()) {
            int r = StdRandom.uniform(q2.size());
            StdOut.printf("[%d] (%d) %s\n", r, q2.delete(r), q2);
        }
    }
}
