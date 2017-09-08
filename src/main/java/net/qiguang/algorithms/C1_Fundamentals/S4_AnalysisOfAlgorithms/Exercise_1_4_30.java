package net.qiguang.algorithms.C1_Fundamentals.S4_AnalysisOfAlgorithms;

import edu.princeton.cs.algs4.Stack;
import net.qiguang.algorithms.C1_Fundamentals.S3_BagsQueuesAndStacks.Exercise_1_3_32;

/**
 * 1.4.30 Deque with a stack and a steque.
 * Implement a deque with a stack and a steque (see Exercise 1.3.32) so that each deque operation
 * takes a constant amortized number of stack and steque operations.
 */
public class Exercise_1_4_30 {
    // Left is steque, Right is stack
    public static class StackStequeDeque<Item> {
        private Stack<Item> s;
        private Exercise_1_3_32.Steque<Item> q;

        public StackStequeDeque() {
            s = new Stack<Item>();
            q = new Exercise_1_3_32.Steque<Item>();
        }
        // Left is top of steque, so push to steque
        public void pushLeft(Item item) {
            q.push(item);
        }
        // Right is top of stack, so push to stack
        public void pushRight(Item item) {
            s.push(item);
        }
        // Left is top of steque, so pop from steque
        public Item popLeft() {
            if (isEmpty()) return null;
            if (q.isEmpty()) fillSteque();
            return q.pop();
        }
        // Right is top of stack, so pop from stack
        public Item popRight() {
            if (isEmpty()) return null;
            if (s.isEmpty()) fillStack();
            return s.pop();
        }
        public int size() {
            return q.size() + s.size();
        }
        public boolean isEmpty() {
            return q.isEmpty() && s.isEmpty();
        }
        private void fillSteque() {
            while (!s.isEmpty())
                q.push(s.pop());
        }
        private void fillStack() {
            while (!q.isEmpty())
                s.push(q.pop());
        }
    }
    public static void main(String[] args) {
        StackStequeDeque<Integer> ssd = new StackStequeDeque<>();

        // Push left, pop left
        for (int i = 0; i < 10; i++) {
            ssd.pushLeft(i);
        }
        while (!ssd.isEmpty()) {
            System.out.printf(ssd.popLeft() + " ");
        }
        System.out.println();

        // Push left, pop right
        for (int i = 0; i < 10; i++) {
            ssd.pushLeft(i);
        }
        while (!ssd.isEmpty()) {
            System.out.printf(ssd.popRight() + " ");
        }
        System.out.println();

        // Push right, pop right
        for (int i = 0; i < 10; i++) {
            ssd.pushRight(i);
        }
        while (!ssd.isEmpty()) {
            System.out.printf(ssd.popRight() + " ");
        }
        System.out.println();

        // Push right, pop left
        for (int i = 0; i < 10; i++) {
            ssd.pushRight(i);
        }
        while (!ssd.isEmpty()) {
            System.out.printf(ssd.popLeft() + " ");
        }
        System.out.println();

        // 9 8 7 6 5 4 3 2 1 0 10 11 12 13 14 15 16 17 18 19
        for (int i = 0; i < 10; i++) {
            ssd.pushLeft(i);
        }
        for (int i = 10; i < 20; i++) {
            ssd.pushRight(i);
        }
        while (!ssd.isEmpty()) {
            System.out.printf(ssd.popLeft() + " ");
        }
        System.out.println();

        for (int i = 0; i < 10; i++) {
            ssd.pushLeft(i);
        }
        for (int i = 10; i < 20; i++) {
            ssd.pushRight(i);
        }
        while (!ssd.isEmpty()) {
            System.out.printf(ssd.popRight() + " ");
        }
        System.out.println();
    }
}
