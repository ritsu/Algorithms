package net.qiguang.algorithms.C1_Fundamentals.S3_BagsQueuesAndStacks;

import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * 1.3.33 Deque.
 * A double-ended queue or deque (pronounced “deck”) is like a stack or a queue but
 * supports adding and removing items at both ends. A deque stores a collection of
 * items and supports the following API:
 *
 * public class Deque<Item> implements Iterable<Item>
 * ------------------------------------------------------------------------------
 *         Deque()               create an empty deque
 * boolean isEmpty()             is the deque empty?
 *     int size()                number of items in the deque
 *    void pushLeft(Item item)   add an item to the left end
 *    void pushRight(Item item)  add an item to the right end
 *    Item popLeft()             remove an item from the left end
 *    Item popRight()            remove an item from the right end
 *
 *    API for a generic double-ended queue
 *
 * Write a class Deque that uses a doubly-linked list to implement this API and
 * a class ResizingArrayDeque that uses a resizing array.
 */
public class Exercise_1_3_33 {
    public static class Deque<Item> implements Iterable<Item> {
        protected Node first;
        protected Node last;
        private int N;

        // construct an empty deque
        public Deque() {
            first = null;
            last = null;
            N = 0;
        }

        protected class Node {
            protected Item item;
            protected Node next;
            protected Node prev;
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
        public void pushLeft(Item item) {
            if (item == null) throw new NullPointerException("Cannot add null item");
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
        public void pushRight(Item item) {
            if (item == null) throw new NullPointerException("Cannot add null item");
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
        public Item popLeft() {
            if (isEmpty()) throw new NoSuchElementException("Cannot remove from empty deque");
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
        public Item popRight() {
            if (isEmpty()) throw new NoSuchElementException("Cannot remove from empty deque");
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

        public String toString() {
            String s = "";
            for (Item item : this) {
                s += item + " ";
            }
            return s;
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
                throw new UnsupportedOperationException("Cannot remove in iterator");
            }

            public Item next() {
                if (current == null)
                    throw new NoSuchElementException("Cannot call next() on last item in deque");
                Item item = current.item;
                current = current.next;
                return item;
            }
        }
    }

    public static class ResizingArrayDeque<Item> implements Iterable<Item> {
        private Item[] deque;
        // Indices with items are [start...end)
        private int start;
        private int end;

        public ResizingArrayDeque() {
            start = 1;
            end = 1;
            deque = (Item[]) new Object[3];
        }

        public void resize(int n) {
            Item[] copy = (Item[]) new Object[n];
            int newstart = (n - size()) / 2;
            for (int i = 0; i < size(); i++) {
                copy[newstart + i] = deque[start + i];
            }
            end = newstart + size();
            start = newstart;
            deque = copy;
        }

        // is the deque empty?
        public boolean isEmpty() { return (end - start) == 0; }

        // return the number of items on the deque
        public int size() { return (end - start); }

        public int sizeOfArray() { return deque.length; }

        // add the item to the front
        public void pushLeft(Item item) {
            if (item == null) throw new NullPointerException("Cannot add null item");
            if (start == 0) resize(deque.length * 2);
            deque[--start] = item;
        }

        // add the item to the end
        public void pushRight(Item item) {
            if (item == null) throw new NullPointerException("Cannot add null item");
            if (end == deque.length) resize(deque.length * 2);
            deque[end++] = item;
        }

        // remove and return the item from the front
        public Item popLeft() {
            if (isEmpty()) throw new NoSuchElementException("Cannot remove from empty deque");
            Item item = deque[start++];
            if (!isEmpty() && size() < deque.length / 4) {
                resize(deque.length / 2);
            }
            return item;
        }

        // remove and return the item from the end
        public Item popRight() {
            if (isEmpty()) throw new NoSuchElementException("Cannot remove from empty deque");
            Item item = deque[--end];
            if (!isEmpty() && size() < deque.length / 4) {
                resize(deque.length / 2);
            }
            return item;
        }

        public String toString() {
            String s = "";
            for (Item item : this) {
                s += item + " ";
            }
            return s;
        }

        // return an iterator over items in order from front to end
        public Iterator<Item> iterator() {
            return new DequeIterator();
        }

        private class DequeIterator implements Iterator<Item> {
            private int current = start;

            public boolean hasNext() {
                return current != end;
            }

            public void remove() {
                throw new UnsupportedOperationException("Cannot remove in iterator");
            }

            public Item next() {
                if (current == end)
                    throw new NoSuchElementException("Cannot call next() on last item in deque");
                return deque[current++];
            }
        }
    }


    public static void main(String[] args) {
        // Test Deque
        Deque<Integer> d = new Deque<Integer>();
        StdOut.println("Empty Deque: " + d);
        StdOut.printf("isEmpty(): %s; size(): %d\n\n", d.isEmpty(), d.size());

        StdOut.println("pushLeft([0...9]):");
        StdOut.println("[Size] Items");
        for (int i = 0; i < 10; i++) {
            d.pushLeft(i);
            StdOut.printf("  [%2d] %s\n", d.size(), d);
        }
        StdOut.println();

        StdOut.println("pushRight([10...19])");
        StdOut.println("[Size] Items");
        for (int i = 10; i < 20; i++) {
            d.pushRight(i);
            StdOut.printf("  [%2d] %s\n", d.size(), d);
        }
        StdOut.println();

        StdOut.println("popLeft() 8 times:");
        StdOut.println("(Ret) [Size] Items");
        for (int i = 0; i < 8; i++) {
            StdOut.printf(" (%2d)   [%2d] %s\n", d.popLeft(), d.size(), d);
        }
        StdOut.println();

        StdOut.println("popRight() 8 times:");
        StdOut.println("(Ret) [Size] Items");
        for (int i = 0; i < 8; i++) {
            StdOut.printf(" (%2d)   [%2d] %s\n", d.popRight(), d.size(), d);
        }
        StdOut.println();

        // Test ResizingArrayDeque
        ResizingArrayDeque<Integer> rad = new ResizingArrayDeque<Integer>();
        StdOut.println("Empty ResizingArrayDeque: " + rad);
        StdOut.printf("isEmpty(): %s; sizeOfArray(): %d; size(): %d\n\n",
                rad.isEmpty(), rad.sizeOfArray(), rad.size());

        StdOut.println("pushLeft([0...9]):");
        StdOut.println("[ArraySize] [Size] Items");
        for (int i = 0; i < 10; i++) {
            rad.pushLeft(i);
            StdOut.printf("       [%2d]   [%2d] %s\n", rad.sizeOfArray(), rad.size(), rad);
        }
        StdOut.println();

        StdOut.println("pushRight([10...19])");
        StdOut.println("[ArraySize] [Size] Items");
        for (int i = 10; i < 20; i++) {
            rad.pushRight(i);
            StdOut.printf("       [%2d]   [%2d] %s\n", rad.sizeOfArray(), rad.size(), rad);
        }
        StdOut.println();

        StdOut.println("popLeft() 8 times:");
        StdOut.println("(Ret) [ArraySize] [Size] Items");
        for (int i = 0; i < 8; i++) {
            StdOut.printf(" (%2d)        [%2d]   [%2d] %s\n", rad.popLeft(), rad.sizeOfArray(), rad.size(), rad);
        }
        StdOut.println();

        StdOut.println("popRight() 8 times:");
        StdOut.println("(Ret) [ArraySize] [Size] Items");
        for (int i = 0; i < 8; i++) {
            StdOut.printf(" (%2d)        [%2d]   [%2d] %s\n", rad.popRight(), rad.sizeOfArray(), rad.size(), rad);
        }
        StdOut.println();
    }
}
