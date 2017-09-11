package net.qiguang.algorithms.C1_Fundamentals.S4_AnalysisOfAlgorithms;

import edu.princeton.cs.algs4.StdRandom;

import java.util.HashMap;

/**
 * 1.4.44 Birthday problem.
 * Write a program that takes an integer N from the command line and uses StdRandom.uniform() to generate a
 * random sequence of integers between 0 and N – 1. Run experiments to validate the hypothesis that the number
 * of integers generated before the first repeated value is found is ~√(pi*N/2).
 */
public class Exercise_1_4_44 {
    public static int untilRepeat(int n) {
        int count = 0;
        HashMap<Integer, Integer> h = new HashMap<Integer, Integer>();
        int r = StdRandom.uniform(n);
        while (!h.containsKey(r)) {
            h.put(r, 1);
            r = StdRandom.uniform(n);
            count++;
        }
        return count;
    }
    public static void main(String[] args) {
        int runs = 100;
        for (int n = 100; n < 100000000; n += n) {
            double hypo = Math.sqrt(Math.PI * n / 2);
            double count = 0;
            for (int i = 0; i < runs; i++) {
                count += untilRepeat(n);
            }
            System.out.printf("%8d %7.1f %7.1f\n", n, count/runs, hypo);
        }
    }
}
