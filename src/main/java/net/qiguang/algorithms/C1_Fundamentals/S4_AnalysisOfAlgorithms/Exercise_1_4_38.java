package net.qiguang.algorithms.C1_Fundamentals.S4_AnalysisOfAlgorithms;

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.Stopwatch;

import java.util.Random;

/**
 * 1.4.38 Naive 3-sum implementation.
 * Run experiments to evaluate the following implementation of the inner loop of ThreeSum:
 *
 * for (int i = 0; i < N; i++)
 *     for (int j = 0; j < N; j++)
 *         for (int k = 0; k < N; k++)
 *             if (i < j && j < k)
 *                 if (a[i] + a[j] + a[k] == 0)
 *                     cnt++;
 *
 * Do so by developing a version of DoublingTest that computes the ratio of the running times
 * of this program and ThreeSum.
 */
public class Exercise_1_4_38 {
    public static class ThreeSum {

        public static int count(int[] a) {
            int count = 0;
            for (int i = 0; i < a.length; i++) {
                for (int j = i + 1; j < a.length; j++) {
                    for (int k = j + 1; k < a.length; k++) {
                        if (a[i] + a[j] + a[k] == 0) {
                            count++;
                        }
                    }
                }
            }
            return count;
        }

        public static int count2(int[] a) {
            int count = 0;
            for (int i = 0; i < a.length; i++)
                for (int j = 0; j < a.length; j++)
                    for (int k = 0; k < a.length; k++)
                        if (i < j && j < k)
                            if (a[i] + a[j] + a[k] == 0)
                                count++;
            return count;
        }
    }
    public static class DoublingTest {
        private static final int MAXIMUM_INTEGER = 1000000;

        // This class should not be instantiated.
        private DoublingTest() { }

        /**
         * Returns the amount of time to call {@code ThreeSum.count()} with <em>n</em>
         * random 6-digit integers.
         * @param n the number of integers
         * @return amount of time (in seconds) to call {@code ThreeSum.count()} with n random 6-digit integers
         */
        public static double timeTrial(int n, int v) {
            int[] a = new int[n];
            for (int i = 0; i < n; i++) {
                a[i] = StdRandom.uniform(-MAXIMUM_INTEGER, MAXIMUM_INTEGER);
            }
            Stopwatch timer = new Stopwatch();
            if (v == 1) ThreeSum.count(a);
            else        ThreeSum.count2(a);
            return timer.elapsedTime();
        }

    }

    public static void main(String[] args)  {
        System.out.printf("%7s %8s %8s %8s\n", "size", "v1", "v2", "v2/v1");
        for (int i = 250; true; i += i) {
            double t1 = DoublingTest.timeTrial(i, 1);
            double t2 = DoublingTest.timeTrial(i, 2);
            System.out.printf("%7d %8.3f %8.3f %8.3f\n", i, t1, t2, t2/t1);
        }
    }
}
