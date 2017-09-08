package net.qiguang.algorithms.C1_Fundamentals.S4_AnalysisOfAlgorithms;

import edu.princeton.cs.algs4.Stack;

/**
 * 1.4.31 Deque with three stacks.
 * Implement a deque with three stacks so that each deque operation
 * takes a constant amortized number of stack operations.
 */
public class Exercise_1_4_31 {
    public static class StackDeque<Item> {
        Stack<Item> left;
        Stack<Item> right;
        Stack<Item> buffer;

        public StackDeque() {
            left = new Stack<Item>();
            right = new Stack<Item>();
            buffer = new Stack<Item>();
        }
        public void pushLeft(Item item) {
            left.push(item);
        }
        public void pushRight(Item item) {
            right.push(item);
        }
        public Item popLeft() {
            if (isEmpty()) return null;
            if (left.isEmpty()) fillLeft();
            return left.pop();
        }
        public Item popRight() {
            if (isEmpty()) return null;
            if (right.isEmpty()) fillRight();
            return right.pop();
        }
        public int size() {
            return left.size() + right.size();
        }
        public boolean isEmpty() {
            return left.isEmpty() && right.isEmpty();
        }
        // Move half of right onto left
        private void fillLeft() {
            int limit = right.size() / 2;
            for (int i = right.size() / 2; i > 0; i--)
                buffer.push(right.pop());
            while (!right.isEmpty())
                left.push(right.pop());
            while (!buffer.isEmpty())
                right.push(buffer.pop());
        }
        // Move half of left onto right
        private void fillRight() {
            int limit = left.size() / 2;
            for (int i = left.size() / 2; i > 0; i--)
                buffer.push(left.pop());
            while (!left.isEmpty())
                right.push(left.pop());
            while (!buffer.isEmpty())
                left.push(buffer.pop());
        }
    }
    public static void main(String[] args) {
        StackDeque<Integer> sd = new StackDeque<>();

        // Push left, pop left
        for (int i = 0; i < 10; i++) {
            sd.pushLeft(i);
        }
        while (!sd.isEmpty()) {
            System.out.printf(sd.popLeft() + " ");
        }
        System.out.println();

        // Push left, pop right
        for (int i = 0; i < 10; i++) {
            sd.pushLeft(i);
        }
        while (!sd.isEmpty()) {
            System.out.printf(sd.popRight() + " ");
        }
        System.out.println();

        // Push right, pop right
        for (int i = 0; i < 10; i++) {
            sd.pushRight(i);
        }
        while (!sd.isEmpty()) {
            System.out.printf(sd.popRight() + " ");
        }
        System.out.println();

        // Push right, pop left
        for (int i = 0; i < 10; i++) {
            sd.pushRight(i);
        }
        while (!sd.isEmpty()) {
            System.out.printf(sd.popLeft() + " ");
        }
        System.out.println();

        // 9 8 7 6 5 4 3 2 1 0 10 11 12 13 14 15 16 17 18 19
        for (int i = 0; i < 10; i++) {
            sd.pushLeft(i);
        }
        for (int i = 10; i < 20; i++) {
            sd.pushRight(i);
        }
        while (!sd.isEmpty()) {
            System.out.printf(sd.popLeft() + " ");
        }
        System.out.println();

        for (int i = 0; i < 10; i++) {
            sd.pushLeft(i);
        }
        for (int i = 10; i < 20; i++) {
            sd.pushRight(i);
        }
        while (!sd.isEmpty()) {
            System.out.printf(sd.popRight() + " ");
        }
        System.out.println();
    }
}
