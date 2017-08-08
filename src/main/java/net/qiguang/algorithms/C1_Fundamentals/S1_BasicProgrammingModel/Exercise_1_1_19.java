package net.qiguang.algorithms.C1_Fundamentals.S1_BasicProgrammingModel;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.Stopwatch;

/**
 * 1.1.19
 * Run the following program on your computer:
 *     ( See code for F() )
 *
 * What is the largest value of N for which this program takes less 1 hour to compute the value of F(N)?
 * Develop a better implementation of F(N) that saves computed values in an array.
 *     ( See code for F2() )
 */
public class Exercise_1_1_19 {
    public static long F(int N) {
        if (N == 0) return 0;
        if (N == 1) return 1;
        return F(N-1) + F(N-2);
    }

    public static long[] saved = {0, 1};

    public static long F2(int N) {
        // double array size when necessary
        if (N >= saved.length) {
            long[] copy = new long[N*2];
            for (int i = 0; i < saved.length; i++) {
                copy[i] = saved[i];
            }
            saved = copy;
        }

        if (N == 0) return 0;
        if (N == 1) return 1;
        if (saved[N] == 0) saved[N] = F2(N-1) + F2(N-2);
        return saved[N];
    }

    public static void main(String[] args) {
        for (int N = 0; N < 90; N++) {
            Stopwatch s = new Stopwatch();
            StdOut.printf(" F(%d): %d %f sec\n", N, F(N), s.elapsedTime());

            Stopwatch s2 = new Stopwatch();
            StdOut.printf("F2(%d): %d %f sec\n", N, F2(N), s2.elapsedTime());
        }
    }
}
