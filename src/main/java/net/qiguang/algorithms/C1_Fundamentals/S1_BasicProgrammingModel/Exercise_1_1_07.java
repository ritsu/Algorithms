package net.qiguang.algorithms.C1_Fundamentals.S1_BasicProgrammingModel;

import edu.princeton.cs.algs4.StdOut;

/**
 * 1.1.7
 * Give the value printed by each of the following code fragments:
 *
 * a.  double t = 9.0;
 *     while (Math.abs(t - 9.0/t) > .001)
 *         t = (9.0/t + t) / 2.0;
 *     StdOut.printf("%.5f\n", t);
 *
 * b.  int sum = 0;
 *     for (int i = 1; i < 1000; i++)
 *         for (int j = 0; j < i; j++)
 *             sum++;
 *     StdOut.println(sum);
 *
 * c.  int sum = 0;
 *     for (int i = 1; i < 1000; i *= 2)
 *         for (int j = 0; j < N; j++)
 *             sum++;
 *     StdOut.println(sum);
 */

public class Exercise_1_1_07 {
    public static void main(String[] args) {
        // t => sqrt(9)
        double t = 9.0;
        while (Math.abs(t - 9.0/t) > .001)
            t = (9.0/t + t) / 2.0;
        StdOut.printf("%.5f\n", t);

        // Sum(x) for x = {1...999}
        // = 1000^2 / 2 - 1000/2
        // = 500000 - 500
        // = 499500
        int sum = 0;
        for (int i = 1; i < 1000; i++) {
            for (int j = 0; j < i; j++) {
                sum++;
            }
        }
        StdOut.println(sum);

        // 1000 * (1 + lg(1000))
        // = 1000 * 10
        // = 10000
        sum = 0;
        for (int i = 1; i < 1000; i *= 2)
            for (int j = 0; j < 1000; j++)
                sum++;
        StdOut.println(sum);
    }
}
