package net.qiguang.algorithms.C1_Fundamentals.S4_AnalysisOfAlgorithms;

import edu.princeton.cs.algs4.Stack;

/**
 * 1.4.27 Queue with two stacks.
 * Implement a queue with two stacks so that each queue operation takes a constant amortized
 * number of stack operations.
 *
 * Hint: If you push elements onto a stack and then pop them all, they appear in reverse order.
 * If you repeat this process, they’re now back in order.
 */
public class Exercise_1_4_27 {
    public static class StackQueue<Item> {
        Stack<Item> items;
        Stack<Item> backup;

        public StackQueue() {
            items = new Stack<Item>();
            backup = new Stack<Item>();
        }
        public void enqueue(Item item) {
            backup.push(item);
        }
        public Item dequeue() {
            if (isEmpty()) return null;
            if (items.isEmpty()) update();
            Item item = items.pop();
            return item;
        }
        private void update() {
            while (!backup.isEmpty()) {
                items.push(backup.pop());
            }
        }
        public int size() {
            return backup.size() + items.size();
        }
        public boolean isEmpty() {
            return backup.isEmpty() && items.isEmpty();
        }
    }
    public static void main(String[] args) {
        StackQueue<Integer> sq = new StackQueue<Integer>();
        for (int i = 0; i < 10; i++) {
            sq.enqueue(i);
        }
        System.out.println(sq.size());
        while (!sq.isEmpty()) {
            System.out.printf(sq.dequeue() + " ");
        }
        System.out.println();

        for (int i = 0; i < 10; i++) {
            sq.enqueue(i);
        }
        for (int i = 0; i < 5; i++) {
            sq.dequeue();
        }
        for (int i = 10; i < 15; i++) {
            sq.enqueue(i);
        }
        while (!sq.isEmpty()) {
            System.out.printf(sq.dequeue() + " ");
        }
        System.out.println();
    }
}
