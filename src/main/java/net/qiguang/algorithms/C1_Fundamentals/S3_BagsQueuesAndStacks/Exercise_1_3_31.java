package net.qiguang.algorithms.C1_Fundamentals.S3_BagsQueuesAndStacks;

import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * 1.3.31
 * Implement a nested class DoubleNode for building doubly-linked lists, where each node
 * contains a reference to the item preceding it and the item following it in the list
 * (null if there is no such item).
 *
 * Then implement static methods for the following tasks:
 *     insert at the beginning,
 *     insert at the end,
 *     remove from the beginning,
 *     remove from the end,
 *     insert before a given node,
 *     insert after a given node, and
 *     remove a given node.
 */
public class Exercise_1_3_31 {
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
                if (current.item == item) return current;
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
        DoublyLinkedList<Integer> d = new DoublyLinkedList<Integer>();

        // Empty list and single item list
        StdOut.printf("%26s: %s\n", "D = new DoubleLinkedList()", d);
        d.insertBeginning(0);
        StdOut.printf("%26s: %s\n", "D.insertBeginning(0)", d);
        StdOut.printf("%26s: [%d] %s\n\n", "D.removeBeginning()", d.removeBeginning(), d);

        d.insertBeginning(0);
        StdOut.printf("%26s: %s\n", "D.insertBeginning(0)", d);
        StdOut.printf("%26s: [%d] %s\n\n", "D.removeEnd()", d.removeEnd(), d);

        d.insertEnd(0);
        StdOut.printf("%26s: %s\n", "D.insertEnd(0)", d);
        StdOut.printf("%26s: [%d] %s\n\n", "D.removeEnd()", d.removeEnd(), d);

        d.insertEnd(0);
        StdOut.printf("%26s: %s\n", "D.insertEnd(0)", d);
        StdOut.printf("%26s: [%d] %s\n\n", "D.removeBeginning()", d.removeBeginning(), d);

        // insertBeginning and insertEnd
        for (int i = 0; i < 10; i++) {
            d.insertBeginning(i);
        }
        StdOut.printf("%26s: %s\n", "D.insertBeginning(0...9)", d);
        d = new DoublyLinkedList<Integer>();
        StdOut.printf("%26s: %s\n", "D = new DoubleLinkedList()", d);
        for (int i = 0; i < 10; i++) {
            d.insertEnd(i);
        }
        StdOut.printf("%26s: %s\n\n", "D.insertEnd(0...9)", d);

        // insertBefore and insertAfter
        DoublyLinkedList.DoubleNode<Integer> a = d.findNode(0);
        DoublyLinkedList.DoubleNode<Integer> b = d.findNode(5);
        DoublyLinkedList.DoubleNode<Integer> c = d.findNode(9);
        d.insertBeforeNode(a, 11);
        StdOut.printf("%26s: %s\n", "D.insertBeforeNode(0, 11)", d);
        d.insertAfterNode(a, 12);
        StdOut.printf("%26s: %s\n", "D.insertAfterNode(0, 12)", d);
        d.insertBeforeNode(b, 21);
        StdOut.printf("%26s: %s\n", "D.insertBeforeNode(5, 21)", d);
        d.insertAfterNode(b, 22);
        StdOut.printf("%26s: %s\n", "D.insertAfterNode(5, 22)", d);
        d.insertBeforeNode(c, 31);
        StdOut.printf("%26s: %s\n", "D.insertBeforeNode(9, 31)", d);
        d.insertAfterNode(c, 32);
        StdOut.printf("%26s: %s\n\n", "D.insertAfterNode(9, 32)", d);

        // removeNode and iterator
        d.removeNode(d.findNode(11));
        StdOut.printf("%26s: %s\n", "D.removeNode(11)", d);
        d.removeNode(d.findNode(32));
        StdOut.printf("%26s: %s\n", "D.removeNode(32)", d);
        d.removeNode(d.findNode(5));
        StdOut.printf("%26s: %s\n", "D.removeNode(5)", d);
        d.removeNode(d.findNode(99));
        StdOut.printf("%26s: %s\n\n", "D.removeNode(99)", d);

        StdOut.printf("%26s: ", "Iterator test");
        for (int i : d) {
            StdOut.printf("%d ", i);
        }
        StdOut.println();


    }
}
