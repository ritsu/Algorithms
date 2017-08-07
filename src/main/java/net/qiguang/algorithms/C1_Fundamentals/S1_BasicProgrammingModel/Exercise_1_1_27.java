package net.qiguang.algorithms.C1_Fundamentals.S1_BasicProgrammingModel;

import edu.princeton.cs.algs4.StdOut;

/**
 * 1.1.27 Binomial distribution.
 * Estimate the number of recursive calls that would be used by the code
 *
 *     public static double binomial(int N, int k, double p) {
 *         if ((N == 0) || (k < 0)) return 1.0;
 *         return (1.0 - p)*binomial(N-1, k) + p*binomial(N-1, k-1);
 *     }
 *
 * to compute binomial(100, 50). Develop a better implementation that is
 * based on saving computed values in an array.
 */
public class Exercise_1_1_27 {
    public static long calls_binomial;
    public static long calls_binomial2;

    public static double[][] cache;

    public static double binomial(int N, int k, double p) {
        calls_binomial++;
        if ((N == 0) && (k == 0)) return 1.0;
        if ((N  < 0) || (k  < 0)) return 0.0;
        return (1 - p)*binomial(N-1, k, p) + p*binomial(N-1, k-1, p);
    }

    public static double binomial2(int N, int k, double p) {
        double[][] cache = new double[N+1][k+1];
        return binomial2(N, k, p, cache);
    }

    public static double binomial2(int N, int k, double p, double[][] cache) {
        calls_binomial2++;
        if ((N == 0) && (k == 0)) return 1.0;
        if ((N  < 0) || (k  < 0)) return 0.0;
        if (cache[N][k] > 0) return cache[N][k];
        cache[N][k] = (1 - p)*binomial2(N-1, k, p, cache) + p*binomial2(N-1, k-1, p, cache);
        return cache[N][k];
    }

    public static void main(String[] args) {
        double p = 0.25;
        for (int N = 10, k = 5; N <= 100; N+= 10, k+= 5) {
            StdOut.printf("Running binomial(%d, %d, %f)\n", N, k, p);
            calls_binomial = 0;
            StdOut.printf("  %f with %d calls\n", binomial(N, k, p), calls_binomial);
            calls_binomial2 = 0;
            StdOut.printf("  %f with %d calls\n", binomial2(N, k, p), calls_binomial2);
        }

    }

}
