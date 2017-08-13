package net.qiguang.algorithms.C1_Fundamentals.S3_BagsQueuesAndStacks;

import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * 1.3.32 Steque.
 * A stack-ended queue or steque is a data type that supports push, pop, and enqueue.
 * Articulate an API for this ADT. Develop a linked-list-based implementation.
 *
 * public class Steque<Item> implements Iterable<Item>
 * --------------------------------------------------------------------
 *         Steque()            create an empty steque
 * boolean isEmpty()           is the steque empty?
 *     int size()              number of items in the steque
 *    void push(Item item)     add an item to the top / beginning
 *    void enqueue(Item item)  add an item to the bottom / end
 *    Item pop()               remove an item from the top / beginning
 *
 */
public class Exercise_1_3_32 {
    // Stack-ended queue
    public static class Steque<Item> implements Iterable<Item> {
        private Node<Item> first;
        private Node<Item> last;
        private int N;

        public static class Node<Item> {
            private Item item;
            private Node<Item> next;
        }

        // create an empty steque
        public Steque() {
            first = null;
            last = null;
            N = 0;
        }

        // add an item to the top / beginning
        public void push(Item item) {
            Node<Item> oldfirst = first;
            first = new Node<Item>();
            first.item = item;
            first.next = oldfirst;
            if (last == null) last = first;
            N++;

        }

        // add an item to the bottom / end
        public void enqueue(Item item) {
            Node<Item> oldlast = last;
            last = new Node<Item>();
            last.item = item;
            if (oldlast == null) {
                first = last;
            }
            else {
                oldlast.next = last;
            }
            N++;
        }

        // remove an item from the top / beginning
        public Item pop() {
            if (isEmpty()) throw new NoSuchElementException("Stack underflow");
            Item item = first.item;
            first = first.next;
            N--;
            if (isEmpty()) last = null;
            return item;
        }

        // is the steque empty?
        public boolean isEmpty() { return first == null; }

        // number of items in the steque
        public int size() { return N; }

        public String toString() {
            Node<Item> current = first;
            String s = "";
            while (current != null) {
                s += current.item + " ";
                current = current.next;
            }
            return s;
        }

        public Iterator<Item> iterator() { return new StequeIterator<Item>(first); }
        public class StequeIterator<Item> implements Iterator<Item> {
            private Node<Item> current;
            public StequeIterator(Node<Item> first) { current = first; }
            public boolean hasNext() { return current != last; }
            public Item next() {
                if (!hasNext()) throw new NoSuchElementException();
                Item item = current.item;
                current = current.next;
                return item;
            }
        }
    }

    public static void main(String[] args) {
        Steque<Integer> s = new Steque<Integer>();
        StdOut.println("Empty steque: " + s);
        StdOut.printf("isEmpty(): %s; size(): %d\n\n", s.isEmpty(), s.size());
        for (int i = 0; i < 10; i++) {
            s.push(i);
        }
        StdOut.println("push([0,9]): " + s);
        StdOut.printf("isEmpty(): %s; size(): %d\n\n", s.isEmpty(), s.size());
        s = new Steque<Integer>();
        StdOut.println("Empty steque: " + s);
        StdOut.printf("isEmpty(): %s; size(): %d\n\n", s.isEmpty(), s.size());
        for (int i = 0; i < 10; i++) {
            s.enqueue(i);
        }
        StdOut.println("enqueue([0,9]): " + s);
        StdOut.printf("isEmpty(): %s; size(): %d\n\n", s.isEmpty(), s.size());
        for (int i = 10; i < 20; i++) {
            s.push(i);
        }
        StdOut.println("push([10,19]): " + s);
        StdOut.printf("isEmpty(): %s; size(): %d\n\n", s.isEmpty(), s.size());
        for (int i = 20; i < 30; i++) {
            s.enqueue(i);
        }
        StdOut.println("enqueue([0,9]): " + s);
        StdOut.printf("isEmpty(): %s; size(): %d\n\n", s.isEmpty(), s.size());
        StdOut.println("pop() 10 times:");
        for (int i = 0; i < 10; i++) {
            StdOut.printf("[%d] %s\n", s.pop(), s);
            StdOut.printf("isEmpty(): %s; size(): %d\n", s.isEmpty(), s.size());
        }
    }
}
