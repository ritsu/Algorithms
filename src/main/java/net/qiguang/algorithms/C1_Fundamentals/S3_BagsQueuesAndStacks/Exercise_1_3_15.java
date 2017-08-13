package net.qiguang.algorithms.C1_Fundamentals.S3_BagsQueuesAndStacks;

import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import static edu.princeton.cs.algs4.StdIn.isEmpty;

/**
 * 1.3.15
 * Write a Queue client that takes a command-line argument k and prints the kth from the last string
 * found on standard input (assuming that standard input has k or more strings).
 */
public class Exercise_1_3_15 {
    public static void main(String[] args) {
        // Get k from args. If no argument found, set to random number in [0, 100)
        int k = args.length >= 1 ? Integer.parseInt(args[0]) : StdRandom.uniform(100);

        // Get queue from StdIn, else set to [0, 100)
        Queue<String> q = new Queue<String>();
        String line;
        while (!(line = StdIn.readLine()).isEmpty()) {
            q.enqueue(line);
        }
        if (q.isEmpty()) {
            for (int i = 0; i < 100; i++) {
                q.enqueue(String.valueOf(i));
            }
        }
        StdOut.println(q);

        // Get k-th from last item
        int n = q.size() - k - 1;
        for (int i = 0; i < n; i++) {
            q.dequeue();
        }
        StdOut.println(k + "-th from last item is " + q.dequeue());

    }
}
