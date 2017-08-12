package net.qiguang.algorithms.C1_Fundamentals.S3_BagsQueuesAndStacks;

import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdOut;

/**
 * 1.3.13
 * Suppose that a client performs an intermixed sequence of (queue) enqueue and dequeue operations.
 * The enqueue operations put the integers 0 through 9 in order onto the queue; the dequeue operations
 * print out the return value. Which of the following sequence(s) could not occur?
 *
 * a. 0 1 2 3 4 5 6 7 8 9
 * b. 4 6 8 7 5 3 2 9 0 1
 * c. 2 5 6 7 4 8 9 3 1 0
 * d. 4 3 2 1 0 5 6 7 8 9
 *
 * Answer: Since queues are FIFO, dequeued items are always in order of when they were enqueued.
 * It doesn't matter what order the enqueue and dequeue operations are performed in; if the numbers are
 * enqueued in order (0 - 9), then they will always be dequeued in order (0 - 9).
 *
 */
public class Exercise_1_3_13 {
    public static void main(String[] args) {
        StdOut.println("a. 0 1 2 3 4 5 6 7 8 9");
        Queue<Integer> a = new Queue<Integer>();
        for (int i = 0; i < 10; i++) {
            a.enqueue(i);
        }
        while (!a.isEmpty()) {
            StdOut.print(a.dequeue() + " ");
        }
        StdOut.println();

        StdOut.println("b. 4 6 8 7 5 3 2 9 0 1");
        StdOut.println("Impossible");

        StdOut.println("c. 2 5 6 7 4 8 9 3 1 0");
        StdOut.println("Impossible");

        StdOut.println("d. 4 3 2 1 0 5 6 7 8 9");
        StdOut.println("Impossible");

    }
}
