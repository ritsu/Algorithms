package net.qiguang.algorithms.C1_Fundamentals.S2_DataAbstraction;

import edu.princeton.cs.algs4.*;
import java.util.Arrays;

/**
 * 1.2.2
 * Write an Interval1D client that takes an int value N as command-line argument, reads N intervals
 * (each defined by a pair of double values) from standard input, and prints all pairs that intersect.
 */

public class Exercise_1_2_02 {
    public static void main(String[] args) {
        int n = args.length >= 1 ? Integer.parseInt(args[0]) : 50000;
        Interval1D[] intervals = new Interval1D[n];

        // generate random intervals
        for (int i = 0; i < n; i++) {
            double a = StdRandom.uniform();
            double b = StdRandom.uniform();
            intervals[i] = (a < b) ? new Interval1D(a, b) : new Interval1D(b, a);
        }

        // brute force method
        Stopwatch s = new Stopwatch();
        long count = 0;
        for (int i = 0; i < intervals.length; i++) {
            for (int j = i + 1; j < intervals.length; j++) {
                if (intervals[i].intersects(intervals[j])) {
                    //StdOut.println(intervals[i] + ", " + intervals[j]);
                    count++;
                }
            }
        }
        StdOut.println("Intersect count: " + count);
        StdOut.println("Elapsed time   : " + s.elapsedTime());

        // slightly less brute force method
        s = new Stopwatch();
        Arrays.sort(intervals, Interval1D.MIN_ENDPOINT_ORDER);
        count = 0;
        for (int i = 0; i < intervals.length; i++) {
            for (int j = i + 1; j < intervals.length; j++) {
                if (intervals[j].min() > intervals[i].max()) break;
                //StdOut.println(intervals[i] + ", " + intervals[j]);
                count++;
            }
        }
        StdOut.println("Intersect count: " + count);
        StdOut.println("Elapsed time   : " + s.elapsedTime());

    }
}
