package net.qiguang.algorithms.C1_Fundamentals.S3_BagsQueuesAndStacks;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;

/**
 * 1.3.35 Random queue.
 * A random queue stores a collection of items and supports the following API:
 *
 * public class    RandomQueue<Item>
 * ----------------------------------------------
 *           RandomQueue()       create an empty random queue
 *   boolean isEmpty()           is the queue empty?
 *      void enqueue(Item item)  add an item
 *      Item dequeue()           remove and return a random item (sample without replacement)
 *      Item sample()            return a random item, but do not remove (sample with replacement)
 *
 *           API for a generic random queue
 *
 * Write a class RandomQueue that implements this API.
 *
 * Hint: Use an array representation (with resizing). To remove an item, swap one at a random position
 * (indexed 0 through N-1) with the one at the last position (index N-1). Then delete and return the
 * last object, as in ResizingArrayStack. Write a client that deals bridge hands (13 cards each)
 * using RandomQueue<Card>.
 */
public class Exercise_1_3_35 {
    public static class RandomQueue<Item> implements Iterable<Item> {
        Item[] items;
        int N;

        public RandomQueue() {
            items = (Item[]) new Object[4];
            N = 0;
        }
        public boolean isEmpty() { return N == 0; }
        public int size() { return N; }
        public void enqueue(Item item) {
            items[N++] = item;
            if (items.length == N) {
                resize(items.length * 2);
            }
        }
        public Item dequeue() {
            if (isEmpty()) throw new java.util.NoSuchElementException("Cannot dequeue from empty queue");
            int r = StdRandom.uniform(0, N);
            Item item = items[r];
            items[r] = items[--N];
            if (N < items.length / 4 && items.length > 4) {
                resize(items.length / 2);
            }
            return item;
        }
        public Item sample() {
            if (isEmpty()) throw new java.util.NoSuchElementException("Cannot sample from empty queue");
            return items[StdRandom.uniform(0, N)];
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
        public Iterator<Item> iterator() { return new RandomQueueIterator(); }
        public class RandomQueueIterator implements Iterator<Item> {
            int current;

            public RandomQueueIterator() {
                current = 0;
                for (int i = 0; i < N; i++) {
                    int random = StdRandom.uniform(0, N);
                    Item copy = items[random];
                    items[random] = items[i];
                    items[i] = copy;
                }
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

    public static void main(String[] args) {
        RandomQueue<Integer> q = new RandomQueue<Integer>();
        for (int i = 0; i < 10; i++) {
            q.enqueue(i);
        }
        StdOut.printf("%10s: %s\n", "Queue", q);
        for (int i = 0; i < 10; i++) {
            StdOut.printf("Iterator %d: ", i);
            for (int item : q) {
                StdOut.print(item + " ");
            }
            StdOut.println();
        }

        StdOut.printf("\nDequeue test:\n");
        q = new RandomQueue<Integer>();
        for (int i = 0; i < 10; i++) {
            q.enqueue(i);
        }
        while (!q.isEmpty()) {
            StdOut.printf("(%d) %s\n", q.dequeue(), q);
        }

        StdOut.printf("\nBridge hands:\n");
        RandomQueue<String> cards = new RandomQueue<String>();
        String[] suits = {"♠", "♥", "♦", "♣"};
        String[] ranks = {"A", "K", "Q", "J", "10", "9", "8", "7", "6", "5", "4", "3", "2"};
        for (String s : suits) {
            for (String r : ranks) {
                cards.enqueue(s + r);
            }
        }
        for (int i = 0; i < 4; i++) {
            StdOut.printf("%d: ", i);
            for (int j = 0; j < 13; j++) {
                StdOut.printf("%-4s", cards.dequeue());
            }
            StdOut.println();
        }

    }

}
