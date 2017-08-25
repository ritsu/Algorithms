package net.qiguang.algorithms.C1_Fundamentals.S3_BagsQueuesAndStacks;

import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * 1.3.50 Fail-fast iterator.
 * Modify the iterator code in Stack to immediately throw a
 *
 *     java.util.ConcurrentModificationException
 *
 * if the client modifies the collection (via push() or pop()) during iteration.
 *
 * Solution: Maintain a counter that counts the number of push() and pop() operations. When creating an
 * iterator, store this value as an Iterator instance variable. Before each call to hasNext() and next(),
 * check that this value has not changed since construction of the iterator; if it has, throw the exception.
 */
public class Exercise_1_3_50 {
    // Taken from edu.princeton.cs.algs4.Stack
    public static class FailFastStack<Item> implements Iterable<Item> {
        private Node<Item> first;
        private int n;
        private int ops;

        private static class Node<Item> {
            private Item item;
            private Node<Item> next;
        }

        public FailFastStack() {
            first = null;
            n = 0;
        }

        public boolean isEmpty() {
            return first == null;
        }

        public int size() {
            return n;
        }

        public void push(Item item) {
            Node<Item> oldfirst = first;
            first = new Node<Item>();
            first.item = item;
            first.next = oldfirst;
            n++;
            ops++;
        }

        public Item pop() {
            if (isEmpty()) throw new NoSuchElementException("Stack underflow");
            Item item = first.item;
            first = first.next;
            n--;
            ops++;
            return item;
        }

        public Item peek() {
            if (isEmpty()) throw new NoSuchElementException("Stack underflow");
            return first.item;
        }

        public String toString() {
            StringBuilder s = new StringBuilder();
            for (Item item : this) {
                s.append(item);
                s.append(' ');
            }
            return s.toString();
        }

        public Iterator<Item> iterator() {
            return new ListIterator<Item>(first, ops);
        }

        private class ListIterator<Item> implements Iterator<Item> {
            private Node<Item> current;
            int opscopy;

            public ListIterator(Node<Item> first, int ops) {
                current = first;
                opscopy = ops;
            }

            public boolean hasNext() {
                if (opscopy != ops) throw new java.util.ConcurrentModificationException();
                return current != null;
            }

            public void remove() {
                throw new UnsupportedOperationException();
            }

            public Item next() {
                if (opscopy != ops) throw new java.util.ConcurrentModificationException();
                if (!hasNext()) throw new NoSuchElementException();
                Item item = current.item;
                current = current.next;
                return item;
            }
        }
    }

    public static void main(String[] args) {
        FailFastStack<Integer> s = new FailFastStack<Integer>();

        for (int i = 0; i < 10; i++) {
            s.push(i);
        }
        for (int i : s) {
            StdOut.println(i);
            if (i < 5) s.pop();
        }
    }
}
