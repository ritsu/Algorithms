package net.qiguang.algorithms.C1_Fundamentals.S4_AnalysisOfAlgorithms;

import edu.princeton.cs.algs4.Queue;

/**
 * 1.4.28 Stack with a queue.
 * Implement a stack with a single queue so that each stack operations takes a linear number of queue operations.
 *
 * Hint: To delete an item, get all of the elements on the queue one at a time, and put them at the end,
 * except for the last one which you should delete and return. (This solution is admittedly very inefficient.)
 */
public class Exercise_1_4_28 {
    public static class QueueStack<Item> {
        private Queue<Item> q;

        public QueueStack() {
            q = new Queue<Item>();
        }
        public void push(Item item) {
            q.enqueue(item);
        }
        public Item pop() {
            if (q.isEmpty()) return null;
            int end = q.size();
            for (int i = 1; i < end; i++) {
                q.enqueue(q.dequeue());
            }
            return q.dequeue();
        }
        public int size() {
            return q.size();
        }
        public boolean isEmpty() {
            return q.isEmpty();
        }
    }
    public static void main(String[] args) {
        QueueStack<Integer> qs = new QueueStack<>();
        for (int i = 0; i < 10; i++) {
            qs.push(i);
        }
        System.out.println(qs.size());
        while (!qs.isEmpty()) {
            System.out.printf(qs.pop() + " ");
        }
        System.out.println();

        for (int i = 0; i < 10; i++) {
            qs.push(i);
        }
        for (int i = 0; i < 5; i++) {
            qs.pop();
        }
        for (int i = 10; i < 15; i++) {
            qs.push(i);
        }
        while (!qs.isEmpty()) {
            System.out.printf(qs.pop() + " ");
        }
        System.out.println();

    }
}
