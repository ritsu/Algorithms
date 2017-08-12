package net.qiguang.algorithms.C1_Fundamentals.S3_BagsQueuesAndStacks;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

/**
 * 1.3.20
 * Write a method delete() that takes an int argument k and deletes the kth element in a linked list, if it exists.
 */
public class Exercise_1_3_20 {
    public static class Stack<Item> {
        private Node first;
        private int N;

        public class Node {
            Item item;
            Node next;
        }

        public boolean isEmpty() { return first == null; }
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
        public void delete(int k) {
            if (isEmpty()) {
                return;
            }
            if (k == 1) {
                first = first.next;
                N--;
                return;
            }
            Node current = first;
            for (int i = 2; i < k; i++) {
                if (current == null) return;
                current = current.next;
            }
            current.next = current.next.next;
            N--;
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
    }
    public static void main(String[] args) {
        // Create stack
        Stack<Integer> s = new Stack<Integer>();
        for (int i = 10; i > 0; i--) {
            s.push(i);
        }
        StdOut.printf("Stack     %3d: %s\n", s.size(), s);

        // Delete items
        while (!s.isEmpty()) {
            int k = StdRandom.uniform(1, s.size() + 1);
            s.delete(k);
            StdOut.printf("delete(%d) %3d: %s\n", k, s.size(), s);
        }
    }
}
