package net.qiguang.algorithms.C1_Fundamentals.S3_BagsQueuesAndStacks;

import edu.princeton.cs.algs4.StdOut;

/**
 * 1.3.19
 * Give a code fragment that removes the last node in a linked list whose first node is first.
 */
public class Exercise_1_3_19 {

    public static class Stack<Item> {
        private Node first;
        private int N;

        public class Node {
            Item item;
            Node next;
        }

        public boolean isEmpty() {
            return first == null;
        }
        public int size() {
            return N;
        }
        public void push(Item item) {
            Node oldfirst = first;
            first = new Node();
            first.item = item;
            first.next = oldfirst;
            N++;
        }
        public Item pop() {
            Item item = first.item;
            first = first.next;
            N--;
            return item;
        }
        public void removeLast() {
            if (isEmpty()) {
                return;
            }
            if (first.next == null) {
                Item item = first.item;
                first = null;
                N--;
                return;
            }
            Node previous = first;
            Node current = first.next;
            while (current.next != null) {
                previous = current;
                current = current.next;
            }
            Item item = previous.item;
            previous.next = null;
            N--;
            return;
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
    }

    public static void main(String[] args) {
        Stack<Integer> s = new Stack<Integer>();
        for (int i = 0; i < 10; i++) {
            s.push(i);
        }
        StdOut.printf("%3d: %s\n", s.size(), s);
        s.removeLast();
        StdOut.printf("%3d: %s\n", s.size(), s);

    }


}
