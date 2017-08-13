package net.qiguang.algorithms.C1_Fundamentals.S3_BagsQueuesAndStacks;

import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * 1.3.29
 * Write a Queue implementation that uses a circular linked list, which is the same as a linked list
 * except that no links are null and the value of last.next is first whenever the list is not empty.
 * Keep only one Node instance variable (last).
 */
public class Exercise_1_3_29 {
    public static class Queue<Item> implements Iterable<Item> {
        private Node<Item> last;
        private int N;

        public class Node<Item> {
            private Item item;
            private Node<Item> next;
        }

        public Queue() {
            last = null;
            N = 0;
        }
        public boolean isEmpty() {
            return last == null;
        }
        public int size() {
            return N;
        }
        public void enqueue(Item item) {
            Node<Item> oldlast = last;
            last = new Node<Item>();
            last.item = item;
            if (oldlast == null) {
                last.next = last;
            }
            else {
                last.next = oldlast.next;
                oldlast.next = last;
            }
            N++;
        }
        public Item dequeue() {
            if (isEmpty()) throw new NoSuchElementException("Stack underflow");
            Item item = last.next.item;
            if (last.next == last) last = null;
            else                   last.next = last.next.next;
            N--;
            return item;
        }
        public Item peek() {
            if (isEmpty()) throw new NoSuchElementException("Stack underflow");
            return last.item;
        }

        public String toString() {
            if (last == null) return "";
            Node current = last.next;
            String s = "";
            while (current != last) {
                s += current.item + " ";
                current = current.next;
            }
            s += current.item;
            return s;
        }

        public Iterator<Item> iterator() {
            return new QueueIterator<Item>(last);
        }
        private class QueueIterator<Item> implements Iterator<Item> {
            private Node<Item> current;
            private int count;
            public QueueIterator(Node<Item> last) {
                current = last;
                count = 0;
            }
            public boolean hasNext() { return count < N; }
            public void remove()     { throw new UnsupportedOperationException(); }
            public Item next() {
                if (!hasNext()) throw new NoSuchElementException();
                Item item = current.next.item;
                current = current.next;
                count++;
                return item;
            }
        }
    }

    public static void main(String[] args) {
        Queue<Integer> q = new Queue<Integer>();

        // Test empty queue operations
        StdOut.println(q);
        for (int i : q) {
            StdOut.println(i);
        }
        // Test enqueue() and iterator()
        for (int i = 0; i < 10; i++) {
            q.enqueue(i);
            for (int j : q) {
                StdOut.printf("%d ", j);
            }
            StdOut.println();
        }
        // Test dequeue() and toString()
        while (!q.isEmpty()) {
            StdOut.printf("[%d] %s\n", q.dequeue(), q);
        }
    }
}
