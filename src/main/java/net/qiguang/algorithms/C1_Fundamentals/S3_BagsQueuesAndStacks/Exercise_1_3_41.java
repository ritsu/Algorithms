package net.qiguang.algorithms.C1_Fundamentals.S3_BagsQueuesAndStacks;

import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdOut;

/**
 * 1.3.41 Copy a queue.
 * Create a new constructor so that
 *
 *     Queue<Item> r = new Queue<Item>(q);
 *
 * makes r a reference to a new and independent copy of the queue q. You should be able to push and pop
 * from either q or r without influencing the other.
 *
 * Hint: Delete all of the elements from q and add these elements to both q and r.
 */
public class Exercise_1_3_41 {
    public static class MyQueue<Item> extends Queue<Item> {
        public MyQueue() { }
        public MyQueue(Queue<Item> q) {
            Queue<Item> copy = new Queue<Item>();
            while (!q.isEmpty()) {
                Item item = q.dequeue();
                copy.enqueue(item);
                this.enqueue(item);
            }
            while (!copy.isEmpty()) {
                q.enqueue(copy.dequeue());
            }
        }
    }

    public static void main (String[] args) {
        MyQueue<String> q1 = new MyQueue<>();

        StdOut.println("Create Queue 1.");
        for (int i = 0; i < 10; i++) {
            q1.enqueue(String.valueOf(i));
        }
        StdOut.println("Queue 1: " + q1);

        StdOut.println("\nCreate Queue 2.");
        MyQueue<String> q2 = new MyQueue<>(q1);
        StdOut.println("Queue 1: " + q1);
        StdOut.println("Queue 2: " + q2);

        StdOut.println("\nDequeue 5 items from Queue 1.");
        for (int i = 0; i < 5; i++) {
            StdOut.printf("Queue 1: (%s) %s\n", q1.dequeue(), q1);
            StdOut.printf("Queue 2:     %s\n", q2);
        }
        StdOut.println("\nDequeue 3 items from Queue 2.");
        for (int i = 0; i < 3; i++) {
            StdOut.printf("Queue 1:     %s\n", q1);
            StdOut.printf("Queue 2: (%s) %s\n", q2.dequeue(), q2);
        }

        StdOut.println("\nEnqueue 5 items to Queue 1.");
        for (int i = 10; i < 15; i++) {
            q1.enqueue(String.valueOf(i));
            StdOut.printf("Queue 1: (%d) %s\n", i, q1);
            StdOut.printf("Queue 2:      %s\n", q2);
        }
        StdOut.println("\nEnqueue 3 items to Queue 2.");
        for (int i = 10; i < 13; i++) {
            q2.enqueue(String.valueOf(i));
            StdOut.printf("Queue 1:      %s\n", q1);
            StdOut.printf("Queue 2: (%d) %s\n", i, q2);
        }
    }
}
