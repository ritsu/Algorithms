package net.qiguang.algorithms.C1_Fundamentals.S3_BagsQueuesAndStacks;

import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;

/**
 * 1.3.37 Josephus problem.
 * In the Josephus problem from antiquity, N people are in dire straits and agree to the following
 * strategy to reduce the population. They arrange themselves in a circle (at positions numbered
 * from 0 to N–1) and proceed around the circle, eliminating every Mth person until only one person
 * is left. Legend has it that Josephus ﬁgured out where to sit to avoid being eliminated. Write a
 * Queue client Josephus that takes N and M from the command line and prints out the order in which
 * people are eliminated (and thus would show Josephus where to sit in the circle).
 *
 *     % java Josephus 7 2
 *     1 3 5 0 4 2 6
 */
public class Exercise_1_3_37 {
    public static String Josephus(int n, int m) {
        // Create queue
        Queue<Integer> q = new Queue<Integer>();
        for (int i = 0; i < n; i++) {
            q.enqueue(i);
        }
        // Determine order of dequeue
        String result = "";
        for (int i = 1; !q.isEmpty(); i++) {
            int j = q.dequeue();
            if (i % m > 0) q.enqueue(j);
            else           result += j + " ";
        }
        return result;
    }

    public static void main (String[] args) {
        int n = args.length >= 1 ? Integer.parseInt(args[0]) : 7;
        int m = args.length >= 2 ? Integer.parseInt(args[1]) : 2;

        StdOut.println(Josephus(n, m));
    }
}
