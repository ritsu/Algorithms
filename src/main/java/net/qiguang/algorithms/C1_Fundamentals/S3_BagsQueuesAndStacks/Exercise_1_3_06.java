package net.qiguang.algorithms.C1_Fundamentals.S3_BagsQueuesAndStacks;

import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;

/**
 * 1.3.6
 * What does the following code fragment do to the queue q?
 *
 * Answer: Reverses the queue
 */
public class Exercise_1_3_06 {
    // Reverse queue items
    public static void main(String[] args) {
        Stack<String> stack = new Stack<String>();

        // Test data
        Queue<String> q = new Queue<String>();
        for (int i = 0; i < 10; i++) {
            q.enqueue(Integer.toString(i));
        }

        while (!q.isEmpty())
            stack.push(q.dequeue());
        while (!stack.isEmpty())
            q.enqueue(stack.pop());

        StdOut.println(q);

    }
}
