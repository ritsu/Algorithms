package net.qiguang.algorithms.C1_Fundamentals.S4_AnalysisOfAlgorithms;

import edu.princeton.cs.algs4.StdRandom;

import java.util.HashMap;

/**
 * 1.4.45 Coupon collector problem.
 * Generating random integers as in the previous exercise, run experiments to validate the hypothesis
 * that the number of integers generated before all possible values are generated is ~N*H_N
 *
 * Note: H_N is the Nth harmonic number: 1/1 + 1/2 + 1/3 + ... + 1/N
 */
public class Exercise_1_4_45 {
    public static int untilAll(int n) {
        HashMap<Integer, Integer> h = new HashMap<Integer, Integer>();
        int count = 0;
        while (h.size() < n) {
            h.put(StdRandom.uniform(n), 1);
            count++;
        }
        return count;
    }
    public static double harmonic(int n) {
        double h = 0.0;
        while (n >= 1) {
            h += 1.0/n;
            n--;
        }
        return h;
    }
    public static void main(String[] args) {
        int runs = 10;
        for (int n = 1; n < 1000000; n += n) {
            double hypo = n * harmonic(n);
            double count = 0;
            for (int i = 0; i < runs; i++) {
                count += untilAll(n);
            }
            System.out.printf("%8d %10.1f %10.1f\n", n, count/runs, hypo);
        }
    }
}
