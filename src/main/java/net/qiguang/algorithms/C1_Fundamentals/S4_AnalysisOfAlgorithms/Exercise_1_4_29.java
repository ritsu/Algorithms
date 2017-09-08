package net.qiguang.algorithms.C1_Fundamentals.S4_AnalysisOfAlgorithms;

import edu.princeton.cs.algs4.Stack;

/**
 * 1.4.29 Steque with two stacks.
 * Implement a steque with two stacks so that each steque operation (see Exercise 1.3.32)
 * takes a constant amortized number of stack operations.
 */
public class Exercise_1_4_29 {
    public static class StackSteque<Item> {
        private Stack<Item> s;
        private Stack<Item> q;

        public StackSteque() {
            s = new Stack<Item>();
            q = new Stack<Item>();
        }
        public void push(Item item) {
            s.push(item);
        }
        public void enqueue(Item item) {
            q.push(item);
        }
        public Item pop() {
            if (isEmpty()) return null;
            if (s.isEmpty()) update();
            Item item = s.pop();
            return item;
        }
        private void update() {
            while (!q.isEmpty()) {
                s.push(q.pop());
            }
        }
        public int size() {
            return s.size() + q.size();
        }
        public boolean isEmpty() {
            return s.isEmpty() && q.isEmpty();
        }
    }
    public static void main(String[] args) {
        StackSteque<Integer> ss = new StackSteque<Integer>();
        for (int i = 0; i < 10; i++) {
            ss.push(i);
        }
        while (!ss.isEmpty()) {
            System.out.printf(ss.pop() + " ");
        }
        System.out.println();

        for (int i = 0; i < 10; i++) {
            ss.enqueue(i);
        }
        while (!ss.isEmpty()) {
            System.out.printf(ss.pop() + " ");
        }
        System.out.println();

        for (int i = 0; i < 10; i++) {
            ss.push(i);
        }
        for (int i = 10; i < 20; i++) {
            ss.enqueue(i);
        }
        while (!ss.isEmpty()) {
            System.out.printf(ss.pop() + " ");
        }
        System.out.println();
    }
}
