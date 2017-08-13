package net.qiguang.algorithms.C1_Fundamentals.S3_BagsQueuesAndStacks;

import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * 1.3.30
 * Write a function that takes the first Node in a linked list as argument and
 * (destructively) reverses the list, returning the first Node in the result.
 */
public class Exercise_1_3_30 {
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
            Node current = first;
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

    public static Stack.Node reverseIterative(Stack.Node first) {
        Stack.Node sentinel = first;
        Stack.Node next = null;
        Stack.Node newfirst = null;
        while (sentinel != null) {
            next = sentinel.next;
            sentinel.next = newfirst;
            newfirst = sentinel;
            sentinel = next;
        }
        return newfirst;
    }

    public static Stack.Node reverseRecursive(Stack.Node first) {
        if (first == null) return null;
        if (first.next == null) return first;
        Stack.Node n = first.next;
        Stack.Node newfirst = reverseRecursive(first.next);
        n.next = first;
        first.next = null;
        return newfirst;
    }

    public static void main(String[] args) {
        // Test stacks
        Stack<Integer> a = new Stack<Integer>();
        Stack<Integer> b = new Stack<Integer>();
        b.push(0);
        Stack<Integer> c = new Stack<Integer>();
        for (int i = 0; i < 2; i++) {
            c.push(i);
        }
        Stack<Integer> d = new Stack<Integer>();
        for (int i = 0; i < 10; i++) {
            d.push(i);
        }
        Stack<Integer>[] stacks = new Stack[]{a, b, c, d};

        // Iterative test
        for (Stack<Integer> s : stacks) {
            StdOut.printf("%10s: %s\n", "Original", s);
            Stack.Node<Integer> n = reverseIterative(s.first);
            if (n == null) {
                StdOut.println();
                continue;
            }
            StdOut.printf("%10s: %d\n", "Return", n.item);
            StdOut.printf("%10s: ", "Reverse");
            while (n != null) {
                StdOut.printf("%d ", n.item);
                n = n.next;
            }
            StdOut.printf("\n%10s: %s\n\n", "Original", s);
        }

        // Rebuild test sacks
        a = new Stack<Integer>();
        b = new Stack<Integer>();
        b.push(0);
        c = new Stack<Integer>();
        for (int i = 0; i < 2; i++) {
            c.push(i);
        }
        d = new Stack<Integer>();
        for (int i = 0; i < 10; i++) {
            d.push(i);
        }
        stacks = new Stack[]{a, b, c, d};

        // Recursive test
        for (Stack<Integer> s : stacks) {
            StdOut.printf("%10s: %s\n", "Original", s);
            Stack.Node<Integer> n = reverseRecursive(s.first);
            if (n == null) {
                StdOut.println();
                continue;
            }
            StdOut.printf("%10s: %d\n", "Return", n.item);
            StdOut.printf("%10s: ", "Reverse");
            while (n != null) {
                StdOut.printf("%d ", n.item);
                n = n.next;
            }
            StdOut.printf("\n%10s: %s\n\n", "Original", s);
        }
    }
}
