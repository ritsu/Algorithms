package net.qiguang.algorithms.C1_Fundamentals.S3_BagsQueuesAndStacks;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * 1.3.21
 * Write a method find() that takes a linked list and a string key as arguments and
 * returns true if some node in the list has key as its item field, false otherwise.
 */
public class Exercise_1_3_21 {
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
    }

    public static boolean find(Stack<String> stack, String key) {
        for (String s : stack) {
            if (s.equals(key)) return true;
        }
        return false;
    }

    public static void main(String[] args) {
        // Parameters
        int size = StdRandom.uniform(1, 100);

        // Create Stack
        Stack<String> s = new Stack<String>();
        for (int i = 0; i < size; i++) {
            s.push(String.valueOf(i));
        }
        StdOut.println("Stack: " + s);

        // Some random searches
        for (int i = 0; i < 4; i++) {
            String key = String.valueOf(StdRandom.uniform(1, s.size() * 2));
            StdOut.printf("find(%3s) %s\n", key, find(s, key));
        }

        // Specific searches
        StdOut.printf("find(%3d) %s\n", s.size() - 1, find(s, String.valueOf(s.size() - 1)));
        StdOut.printf("find(%3d) %s\n", s.size(), find(s, String.valueOf(s.size())));

    }
}
