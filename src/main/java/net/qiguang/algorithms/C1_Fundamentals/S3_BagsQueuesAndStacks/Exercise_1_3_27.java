package net.qiguang.algorithms.C1_Fundamentals.S3_BagsQueuesAndStacks;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * 1.3.27
 * Write a method max() that takes a reference to the first node in a linked list as argument and
 * returns the value of the maximum key in the list. Assume that all keys are positive integers,
 * and return 0 if the list is empty.
 */
public class Exercise_1_3_27 {
    public static class Stack<Item> implements Iterable<Item> {
        // Making first public for this exercise
        public Node<Item> first;
        private int N;

        // Making Node static for this exercise
        public static class Node<Item> {
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
    }

    public static int max(Stack.Node<Integer> n) {
        int max = 0;
        Stack.Node<Integer> current = n;
        while (current != null) {
            if (current.item > max) max = current.item;
            current = current.next;
        }
        return max;
    }

    public static void main(String[] args) {
        Stack<Integer> s = new Stack<Integer>();
        StdOut.printf("%16s: %s\n", "Stack S", s);
        StdOut.printf("%16s: %s\n", "max(S.first)", max(s.first));

        for (int i = 0; i < 10; i++) {
            s.push(StdRandom.uniform(100));
        }
        StdOut.printf("%16s: %s\n", "Stack S", s);
        StdOut.printf("%16s: %s\n", "max(S.first)", max(s.first));
    }
}
