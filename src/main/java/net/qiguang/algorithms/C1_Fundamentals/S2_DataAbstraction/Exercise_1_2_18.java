package net.qiguang.algorithms.C1_Fundamentals.S2_DataAbstraction;

import edu.princeton.cs.algs4.StdOut;

/**
 * 1.2.18 Variance for accumulator.
 * Validate that the following code, which adds the methods var() and stddev() to Accumulator,
 * computes both the mean and variance of the numbers presented as arguments to addDataValue():
 *
 * This implementation is less susceptible to round-off error than the straightforward implementation
 * based on saving the sum of the squares of the numbers.
 */
public class Exercise_1_2_18 {
    public static class Accumulator {
        private double m;
        private double s;
        private int N;

        public void addDataValue(double x) {
            N++;
            s = s + 1.0 * (N-1) / N * (x - m) * (x - m);
            m = m + (x - m) / N;
        }
        public double mean() {
            return m;
        }
        public double var() {
            return s/(N - 1);
        }
        public double stddev() {
            return Math.sqrt(this.var());
        }
    }

    public static void main(String[] args) {
        Accumulator a = new Accumulator();
        double[] values = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        for (double d : values) {
            a.addDataValue(d);
        }

        // 5
        StdOut.printf("%8s %f\n", "mean", a.mean());

        // 2 * (16 + 9 + 4 + 1) / 8 = 7.5
        StdOut.printf("%8s %f\n", "var", a.var());

        // sqrt(7.5) ~ 2.739
        StdOut.printf("%8s %f\n", "stddev", a.stddev());
    }

}
