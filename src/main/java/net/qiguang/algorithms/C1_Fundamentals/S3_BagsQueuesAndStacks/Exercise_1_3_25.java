package net.qiguang.algorithms.C1_Fundamentals.S3_BagsQueuesAndStacks;

import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * 1.3.25
 * Write a method insertAfter() that takes two linked-list Node arguments and inserts the
 * second after the first on its list (and does nothing if either argument is null).
 */
public class Exercise_1_3_25 {
    public static class Stack<Item> implements Iterable<Item> {
        private Node<Item> first;
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

        // Remove the node following the given node
        // Does nothing if the argument or the next field in the argument node is null
        public void removeAfter(Node<Item> node) {
            Node<Item> current = first;
            while (current.next != null) {
                if (current == node) {
                    current.next = current.next.next;
                    break;
                }
                current = current.next;
            }
        }

        public void insertAfter(Node<Item> search, Node<Item> insert) {
            Node<Item> current = first;
            while (current != null) {
                if (current == search) {
                    insert.next = current.next;
                    current.next = insert;
                    return;
                }
                current = current.next;
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

    public static void main(String[] args) {
        Stack<Integer> s = new Stack<Integer>();
        for (int i = 0; i < 10; i++) {
            s.push(i);
        }
        Stack.Node<Integer> a = new Stack.Node<Integer>();
        a.item = 10;
        Stack.Node<Integer> b = new Stack.Node<Integer>();
        b.item = 11;
        Stack.Node<Integer> c = new Stack.Node<Integer>();
        c.item = 12;

        StdOut.printf("%22s: %s\n", "Stack S", s);
        StdOut.printf("%22s: %s\n", "Item  A", a.item);
        s.insertAfter(s.findNodeWithItem(5), a);
        StdOut.printf("%22s: %s\n", "S.insertAfter(5, A)", s);

        StdOut.printf("%22s: %s\n", "Item  B", b.item);
        s.insertAfter(s.findNodeWithItem(0), b);
        StdOut.printf("%22s: %s\n", "S.insertAfter(0, B)", s);

        StdOut.printf("%22s: %s\n", "Item  C", c.item);
        s.insertAfter(s.findNodeWithItem(-1), c);
        StdOut.printf("%22s: %s\n", "S.insertAfter(-1, C)", s);
        s.insertAfter(s.findNodeWithItem(11), c);
        StdOut.printf("%22s: %s\n", "S.insertAfter(11, C)", s);

    }
}
