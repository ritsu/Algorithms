package net.qiguang.algorithms.C1_Fundamentals.S3_BagsQueuesAndStacks;

import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * 1.3.47 Catenable queues, stacks, or steques.
 * Add an extra operation catenation that (destructively) concatenates two queues, stacks, or steques
 * (see Exercise 1.3.32). Hint: Use a circular linked list, maintaining a pointer to the last item.
 */
public class Exercise_1_3_47 {
    // Stack-ended queue
    public static class Steque<Item> implements Iterable<Item> {
        private Node<Item> last;
        private int N;

        public static class Node<Item> {
            private Item item;
            private Node<Item> next;
        }

        // create an empty steque
        public Steque() {
            last = null;
            N = 0;
        }

        // add an item to the top / beginning
        public void push(Item item) {
            Node<Item> node = new Node<Item>();
            node.item = item;
            if (last == null) {
                last = node;
                last.next = last;
            }
            else {
                node.next = last.next;
                last.next = node;
            }
            N++;
        }

        // add an item to the bottom / end
        public void enqueue(Item item) {
            Node<Item> node = new Node<Item>();
            node.item = item;
            if (last == null) {
                node.next = node;
            }
            else {
                node.next = last.next;
                last.next = node;
            }
            last = node;
            N++;
        }

        // remove an item from the top / beginning
        public Item pop() {
            if (isEmpty()) throw new NoSuchElementException("Stack underflow");
            Item item = last.next.item;
            last.next = last.next.next;
            N--;
            if (isEmpty()) last = null;
            return item;
        }

        // Concatenate steque s with current steque
        public void cat(Steque<Item> s) {
            for (Item item : s) {
                enqueue(item);
            }
        }

        // is the steque empty?
        public boolean isEmpty() { return N == 0; }

        // number of items in the steque
        public int size() { return N; }

        public String toString() {
            String s = "";
            for (Item item : this) {
                s += item + " ";
            }
            return s;
        }

        public Iterator<Item> iterator() { return new StequeIterator<Item>(last); }
        public class StequeIterator<Item> implements Iterator<Item> {
            private Node<Item> current;
            private boolean initial;
            public StequeIterator(Node<Item> last) {
                current = isEmpty() ? null : last.next;
                initial = true;
            }
            public boolean hasNext() {
                return !isEmpty() && (current != last.next || initial);
            }
            public Item next() {
                if (!hasNext()) throw new NoSuchElementException();
                Item item = current.item;
                current = current.next;
                initial = false;
                return item;
            }
        }
    }

    public static void main(String[] args) {
        Steque<Integer> s1 = new Steque<>();
        Steque<Integer> s2 = new Steque<>();
        for (int i = 0; i < 10; i++) {
            s1.enqueue(i);
            s2.enqueue(i + 10);
        }
        StdOut.println("Steque A: " + s1);
        StdOut.println("Steque B: " + s2);
        s1.cat(s2);
        StdOut.println("Steque A + Steque B: " + s1);
        StdOut.println();

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
