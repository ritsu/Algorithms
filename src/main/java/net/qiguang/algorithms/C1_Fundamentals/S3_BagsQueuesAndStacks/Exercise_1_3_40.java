package net.qiguang.algorithms.C1_Fundamentals.S3_BagsQueuesAndStacks;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * 1.3.40 Move-to-front.
 * Read in a sequence of characters from standard input and maintain the characters in a linked list with
 * no duplicates. When you read in a previously unseen character, insert it at the front of the list.
 * When you read in a duplicate character, delete it from the list and reinsert it at the beginning.
 * Name your program MoveToFront: it implements the well-known move-to-front strategy, which is useful
 * for caching, data compression, and many other applications where items that have been recently accessed
 * are more likely to be reaccessed.
 */
public class Exercise_1_3_40 {
    // Taken from Exercise_1_3_31
    public static class DoublyLinkedList<Item> implements Iterable<Item> {
        private DoubleNode<Item> first;
        private DoubleNode<Item> last;
        private int N;

        public static class DoubleNode<Item> {
            private Item item;
            private DoubleNode<Item> next;
            private DoubleNode<Item> previous;
        }

        public DoublyLinkedList() {
            first = null;
            last = null;
            N = 0;
        }
        public boolean isEmpty() {
            return first == null;
        }
        public int size() {
            return N;
        }
        public void insertBeginning(Item item) {
            DoubleNode<Item> oldfirst = first;
            first = new DoubleNode<Item>();
            first.item = item;
            first.next = oldfirst;
            first.previous = null;
            if (oldfirst != null) oldfirst.previous = first;
            if (last == null) last = first;
            N++;
        }
        public void insertEnd(Item item) {
            DoubleNode<Item> oldlast = last;
            last = new DoubleNode<Item>();
            last.item = item;
            last.next = null;
            last.previous = oldlast;
            if (oldlast != null) oldlast.next = last;
            if (first == null) first = last;
            N++;
        }

        public Item removeBeginning() {
            if (isEmpty()) throw new NoSuchElementException("Stack underflow");
            Item item = first.item;
            first = first.next;
            if (first == null) last = null;
            else               first.previous = null;
            N--;
            return item;
        }
        public Item removeEnd() {
            if (isEmpty()) throw new NoSuchElementException("Stack underflow");
            Item item = last.item;
            last = last.previous;
            if (last == null) first= null;
            else              last.next = null;
            N--;
            return item;
        }

        public void insertBeforeNode(DoubleNode<Item> node, Item item) {
            DoubleNode<Item> current = first;
            while (current != null) {
                if (current == node) {
                    DoubleNode<Item> n = new DoubleNode<Item>();
                    n.item = item;
                    n.next = current;
                    n.previous = current.previous;
                    if (current == first) {
                        first = n;
                    }
                    else {
                        current.previous.next = n;
                    }
                    current.previous = n;
                }
                current = current.next;
            }
        }
        public void insertAfterNode(DoubleNode<Item> node, Item item) {
            DoubleNode<Item> current = first;
            while (current != null) {
                if (current == node) {
                    DoubleNode<Item> n = new DoubleNode<Item>();
                    n.item = item;
                    n.next = current.next;
                    n.previous = current;
                    if (current == last) {
                        last = n;
                    }
                    else {
                        current.next.previous = n;
                    }
                    current.next = n;
                }
                current = current.next;
            }
        }
        public Item removeNode(DoubleNode<Item> node) {
            DoubleNode<Item> current = first;
            while (current != null) {
                if (current == node) {
                    Item item = current.item;
                    if (current == first) first = current.next;
                    if (current == last)  last  = current.previous;
                    if (current.previous != null)  current.previous.next = current.next;
                    if (current.next     != null)  current.next.previous = current.previous;
                    return item;
                }
                current = current.next;
            }
            return null;
        }

        // Find first node with given item. Used for testing other methods.
        public DoubleNode findNode(Item item) {
            if (isEmpty()) throw new NoSuchElementException("Stack underflow");
            DoubleNode current = first;
            while (current != null) {
                if (current.item.equals(item)) return current;
                current = current.next;
            }
            return null;
        }

        public String toString() {
            DoubleNode current = first;
            String s = "";
            while (current != null) {
                s += current.item + " ";
                current = current.next;
            }
            return s;
        }

        public Iterator<Item> iterator() {
            return new StackIterator<Item>(first);
        }
        private class StackIterator<Item> implements Iterator<Item> {
            private DoubleNode<Item> current;
            public StackIterator(DoubleNode<Item> first) {
                current = first;
            }
            public boolean hasNext() { return current != null; }
            public void remove()     { throw new UnsupportedOperationException(); }
            public Item next() {
                if (!hasNext()) throw new NoSuchElementException();
                Item item = current.item;
                current = current.next;
                return item;
            }
        }
    }

    public static void main(String[] args) {
        // Create linked list
        DoublyLinkedList<String> d = new DoublyLinkedList<>();

        // For convenience, read from file instead of stdin
        ClassLoader cl = Thread.currentThread().getContextClassLoader();
        In in = new In(cl.getResource("algs4-data/tinyTale.txt").getFile());

        // Perform move-to-front for items in file
        while (!in.isEmpty()) {
            String s = in.readString();
            DoublyLinkedList.DoubleNode n = d.isEmpty() ? null : d.findNode(s);
            if (n != null) d.removeNode(n);
            d.insertBeginning(s);
            StdOut.printf("%s: %s\n", s, d);
        }

    }
}
