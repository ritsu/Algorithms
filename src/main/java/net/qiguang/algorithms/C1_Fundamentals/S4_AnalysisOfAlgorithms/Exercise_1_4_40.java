package net.qiguang.algorithms.C1_Fundamentals.S4_AnalysisOfAlgorithms;

import edu.princeton.cs.algs4.ThreeSumFast;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Random;

/**
 * 1.4.40 3-sum for random values.
 * Formulate and validate a hypothesis describing the number of triples of N random int values that sum to 0.
 * If you are skilled in mathematical analysis, develop an appropriate mathematical model for this problem,
 * where the values are uniformly distributed between –M and M, where M is not small.
 *
 * TODO:
 */
public class Exercise_1_4_40 {
    // Generate random array with values between min and max
    public static int[] randomArray(int n, int min, int max) {
        // Populate array with random ints
        int[] a = new int[n];
        Random r = new Random();
        for (int i = 0; i < n; i++) {
            a[i] = r.nextInt(max - min) - min;
        }
        // Remove duplicates
        Arrays.sort(a);
        int dupes = removeDuplicates(a);
        while (dupes > 0) {
            for (int i = a.length - dupes; i < a.length; i++) {
                a[i] = r.nextInt(max - min) + min;
            }
            Arrays.sort(a);
            dupes = removeDuplicates(a);
        }
        return a;
    }

    // Moves duplicate values to end of array and returns number of duplicates
    public static int removeDuplicates(int[] a) {
        int sent = 0;
        for (int i = 1; i < a.length; i++) {
            if (a[i] != a[sent]) a[++sent] = a[i];
        }
        return a.length - sent - 1;
    }


    public static BigInteger binomial(final int N, final int K) {
        BigInteger ret = BigInteger.ONE;
        for (int k = 0; k < K; k++) {
            ret = ret.multiply(BigInteger.valueOf(N-k))
                    .divide(BigInteger.valueOf(k+1));
        }
        return ret;
    }

    public static void main(String[] args) {
        // Number of runs for each set of inputs
        int n = 3;

        // Ranges of possible numbers in array
        int min = 100000;
        int max = 10000000;
        System.out.printf("%7s", "size");
        for (int i = min; i < max; i += i) {
            System.out.printf("%10d", i);
        }
        System.out.println("    M");

        // Get count of triplets
        for (int i = 100; true; i += i) {
            System.out.printf("%7d", i);
            // for each range of M
            for (int j = min; j < max; j += j) {
                double count = 0;
                // average multiple runs
                for (int k = 0; k < n; k++) {
                    int[] a = randomArray(i, -1 * j, j);
                    count += ThreeSumFast.count(a);
                }
                System.out.printf("%10.1f", count/n);

            }
            System.out.println();
        }
    }
}
