package net.qiguang.algorithms.C1_Fundamentals.S3_BagsQueuesAndStacks;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;

/**
 * 1.3.34 Random bag.
 * A random bag stores a collection of items and supports the following API:
 *
 * public class RandomBag<Item> implements Iterable<Item>
 * ------------------------------------------------------------------------------------
 *         RandomBag()     create an empty random bag
 * boolean isEmpty()       is the bag empty?
 *     int size()          number of items in the bag
 *    void add(Item item)  add an item
 *
 *    API for a generic random bag
 *
 * Write a class RandomBag that implements this API. Note that this API is the same as for Bag,
 * except for the adjective random, which indicates that the iteration should provide the items
 * in random order (all N! permutations equally likely, for each iterator).
 *
 * Hint: Put the items in an array and randomize their order in the iterator’s constructor.
 */
public class Exercise_1_3_34 {
    public static class RandomBag<Item> implements Iterable<Item> {
        Item[] items;
        int N;

        public RandomBag() {
            items = (Item[]) new Object[4];
            N = 0;
        }
        public boolean isEmpty() { return N == 0; }
        public int size() { return N; }
        public void add(Item item) {
            items[N++] = item;
            if (items.length == N) {
                resize(items.length * 2);
            }
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
        public Iterator<Item> iterator() { return new RandomBagIterator(); }
        public class RandomBagIterator implements Iterator<Item> {
            int current;

            public RandomBagIterator() {
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
        RandomBag<Integer> b = new RandomBag<Integer>();
        for (int i = 0; i < 10; i++) {
            b.add(i);
        }
        StdOut.printf("%10s: %s\n", "Bag", b);
        for (int i = 0; i < 10; i++) {
            StdOut.printf("Iterator %d: ", i);
            for (int item : b) {
                StdOut.print(item + " ");
            }
            StdOut.println();
        }
    }
}
