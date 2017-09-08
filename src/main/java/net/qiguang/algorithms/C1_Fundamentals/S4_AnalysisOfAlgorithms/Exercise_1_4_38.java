package net.qiguang.algorithms.C1_Fundamentals.S4_AnalysisOfAlgorithms;

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
    public static int count(int[] a, int n) {
        int count = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i+1; j < n; j++) {
                for (int k = j+1; k < n; k++) {
                    if (a[i] + a[j] + a[k] == 0) {
                        count++;
                    }
                }
            }
        }
        return count;
    }
    public static int count2(int[] a, int n) {
        int count = 0;
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                for (int k = 0; k < n; k++)
                    if (i < j && j < k)
                        if (a[i] + a[j] + a[k] == 0)
                            count++;
        return count;
    }

    public static void main(String[] args)  {
        int n = 1000000;
        int[] a = new int[n];
        Random r = new Random();
        for (int i = 0; i < a.length; i++) {
            a[i] = r.nextInt(n) - n/2;
        }
        Stopwatch s;
        double prev1 = 1;
        double prev2 = 1;
        System.out.printf("%13s %6s %6s %12s %6s %6s\n", "", "Time", "Ratio", "", "Time", "Ratio");
        for (int i = 400; i < n; i += i) {
            s = new Stopwatch();
            count(a, i);
            System.out.printf("%12s: %6.3f %6.3f", "ThreeSum", s.elapsedTime(), s.elapsedTime()/prev1);
            prev1 = s.elapsedTime();
            s = new Stopwatch();
            count2(a, i);
            System.out.printf("%12s: %6.3f %6.3f\n", "ThreeSum2", s.elapsedTime(), s.elapsedTime()/prev2);
            prev2 = s.elapsedTime();
        }
    }
}
