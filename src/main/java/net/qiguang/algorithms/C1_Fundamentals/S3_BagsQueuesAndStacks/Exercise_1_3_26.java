package net.qiguang.algorithms.C1_Fundamentals.S3_BagsQueuesAndStacks;

import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * 1.3.26
 * Write a method remove() that takes a linked list and a string key as arguments and
 * removes all of the nodes in the list that have key as its item field.
 */
public class Exercise_1_3_26 {
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

        public Node<Item> findNodeWithItem(Item item) {
            Node<Item> current = first;
            while (current != null) {
                if (current.item.equals(item)) return current;
                current = current.next;
            }
            return null;
        }
    }

    public static void remove(Stack<String> stack, String key) {
        Stack.Node<String> current = stack.first;
        Stack.Node<String> previous = current;
        while (current != null) {
            if (current.item.equals(key)) {
                if (current == stack.first) stack.first = current.next;
                else                        previous.next = current.next;
                current = current.next;
            }
            else {
                previous = current;
                current = current.next;
            }
        }
    }

    public static void main(String[] args) {
        Stack<String> s = new Stack<String>();
        for (int i = 0; i < 20; i++) {
            s.push(String.valueOf(i));
            if (i % 2 == 0) s.push("2");
        }
        StdOut.printf("%16s: %s\n", "Stack S", s);
        remove(s, "10");
        StdOut.printf("%16s: %s\n", "remove(S, 10)", s);
        remove(s, "0");
        StdOut.printf("%16s: %s\n", "remove(S,  0)", s);
        remove(s, "19");
        StdOut.printf("%16s: %s\n", "remove(S, 19)", s);
        remove(s, "99");
        StdOut.printf("%16s: %s\n", "remove(S, 99)", s);
    }
}
