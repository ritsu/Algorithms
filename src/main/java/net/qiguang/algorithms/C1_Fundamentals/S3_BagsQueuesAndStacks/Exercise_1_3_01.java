package net.qiguang.algorithms.C1_Fundamentals.S3_BagsQueuesAndStacks;

import edu.princeton.cs.algs4.StdOut;

/**
 * 1.3.1
 * Add a method isFull() to FixedCapacityStackOfStrings.
 */
public class Exercise_1_3_01 {
    public static class FixedCapacityStackOfStrings {
        private String[] a;
        private int N;
        public FixedCapacityStackOfStrings(int cap) {
            a = new String[cap];
        }
        public boolean isEmpty() {
            return N == 0;
        }
        public int size() {
            return N;
        }
        public void push(String item) {
            a[N++] = item;
        }
        public String pop() {
            return a[--N];
        }
        public boolean isFull() {
            return N == a.length;
        }
    }
    public static void main(String[] args) {
        FixedCapacityStackOfStrings s;
        s = new FixedCapacityStackOfStrings(100);
        String[] items = {"to", "be", "or", "not", "to", "-", "be", "-", "-", "that", "-", "-", "-", "is"};
        for (String item : items) {
            if (!item.equals("-")) {
                if (!s.isFull()) s.push(item);
            }
            else if (!s.isEmpty()) {
                StdOut.print(s.pop() + " ");
            }
        }
        StdOut.println("(" + s.size() + " left on stack)");
    }
}
