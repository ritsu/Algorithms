package net.qiguang.algorithms.C1_Fundamentals.S3_BagsQueuesAndStacks;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * 1.3.24
 * Write a method removeAfter() that takes a linked-list Node as argument and removes the node following
 * the given one (and does nothing if the argument or the next field in the argument node is null).
 */
public class Exercise_1_3_24 {
    public static class Stack<Item> implements Iterable<Item> {
        private Node<Item> first;
        private int N;

        public class Node<Item> {
            private Item item;
            private Node<Item> next;
        }

        public Stack() {
            first = null;
            N = 0;
        }
        public boolean isEmpty() {
            return first == null;
        }
        public int size() {
            return N;
        }
        public void push(Item item) {
            Node<Item> oldfirst = first;
            first = new Node<Item>();
            first.item = item;
            first.next = oldfirst;
            N++;
        }
        public Item pop() {
            if (isEmpty()) throw new NoSuchElementException("Stack underflow");
            Item item = first.item;
            first = first.next;
            N--;
            return item;
        }
        public Item peek() {
            if (isEmpty()) throw new NoSuchElementException("Stack underflow");
            return first.item;
        }

        public String toString() {
            if (isEmpty()) return "";
            Node current = first;
            String s = "";
            while (current.next != null) {
                s += current.item + " ";
                current = current.next;
            }
            s += current.item;
            return s;
        }

        public Iterator<Item> iterator() {
            return new StackIterator<Item>(first);
        }
        private class StackIterator<Item> implements Iterator<Item> {
            private Node<Item> current;
            public StackIterator(Node<Item> first) {
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

        // Remove the node following the given node
        // Does nothing if the argument or the next field in the argument node is null
        public void removeAfter(Node<Item> node) {
            Node<Item> current = first;
            while (current.next != null) {
                if (current == node) {
                    if (current.next == null) return;
                    current.next = current.next.next;
                    N--;
                    break;
                }
                current = current.next;
            }
        }

        // Get node corresponding to n-th index (0 based)
        public Node<Item> getNode(int n) {
            Node<Item> current = first;
            for (int i = 0; i < n; i++) {
                current = current.next;
            }
            return current;
        }
    }

    public static void main(String[] args) {
        // Generate stack
        Stack<Integer> s = new Stack<Integer>();
        for (int i = 9; i >= 0; i--) {
            s.push(i);
        }
        StdOut.printf("%15s %s\n", "Stack:", s);

        // Remove random items
        while (s.size() >= 2) {
            int n = StdRandom.uniform(0, s.size());
            s.removeAfter(s.getNode(n));
            StdOut.printf("removeAfter(%d): %s\n", n, s);
        }
    }

}
