package net.qiguang.algorithms.C1_Fundamentals.S4_AnalysisOfAlgorithms;

import edu.princeton.cs.algs4.Stopwatch;
import edu.princeton.cs.algs4.ThreeSum;
import edu.princeton.cs.algs4.ThreeSumFast;

import java.util.Arrays;
import java.util.Random;

/**
 * 1.4.42 Problem sizes.
 * Estimate the size of the largest value of P for which you can run TwoSumFast, TwoSum, ThreeSumFast, and ThreeSum
 * on your computer to solve the problems for a file of 2^P thousand numbers. Use DoublingRatio to do so.
 *
 * Quadratic:
 *       T(N) = c * N^r
 *     T(2^P) = c * (2^P)^r
 *            = c * 2^(r*P)
 *
 * Linearithmic:
 *       T(N) = c * N * lg(N)
 *     T(2^P) = c * 2^P * lg(2^P)
 *            = c * 2^P * P
 *
 */
public class Exercise_1_4_42 {
    public static class DoublingRatio {
        private static final int MAXIMUM_INTEGER = 1000000;
        private DoublingRatio() { }
        public static double timeTrial(int n, int r, String s) {
            /* This can create arrays with duplicates
            int[] a = new int[n];
            for (int i = 0; i < n; i++) {
                a[i] = StdRandom.uniform(-MAXIMUM_INTEGER, MAXIMUM_INTEGER);
            }
            */
            int[] a = randomArray(n, -MAXIMUM_INTEGER, MAXIMUM_INTEGER);
            Stopwatch timer = new Stopwatch();
            // TwoSumFast, TwoSum, ThreeSumFast and ThreeSum
            if (s == "TwoSum")
                while (--r >= 0)
                    twoSum(a);
            else if (s == "TwoSumFast")
                while (--r >= 0)
                    twoSumFast(a);
            else if (s == "ThreeSum")
                while (--r >= 0)
                    ThreeSum.count(a);
            else if (s == "ThreeSumFast")
                while (--r >= 0)
                    ThreeSumFast.count(a);
            return timer.elapsedTime();
        }
    }

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

    public static int twoSum(int[] a) {
        int count = 0;
        for (int i = 0; i < a.length; i++) {
            for (int j = i + 1; j < a.length; j++) {
                if (a[i] + a[j] == 0) count++;
            }
        }
        return count;
    }
    public static int twoSumFast(int[] a) {
        Arrays.sort(a);
        int count = 0;
        for (int i = 0; i < a.length; i++) {
            int j = Arrays.binarySearch(a, -a[i]);
            if (j > i) count++;
        }
        return count;
    }

    // Estimate maximum size of input (in form of 2^p) possible within time limit
    public static void printEstimate(double ratio, double time, int n, int limit) {
        int r = (int) (Math.log(ratio) / Math.log(2));
        if (r == 1) {
            // Linearithmic
            double c = time/(n * Math.log(n) / Math.log(2));
            System.out.printf("  %s: T(N) = %9.3E * N * log(N)\n", "Linearithmic", c);
            int p = 1;
            double t = c * Math.pow(2, p) * p;
            while (t < limit) {
                p++;
                t = c * Math.pow(2, p) * p;
            }
            System.out.printf("  T(2^%d) = %8.1f seconds\n", --p, c * Math.pow(2, p) * p);
            System.out.printf("  T(2^%d) = %8.1f seconds\n", ++p, c * Math.pow(2, p) * p);
        }
        else {
            // Quadratic
            double c = time/Math.pow(n, r);
            System.out.printf("  %s: T(N) = %9.3E * N^%d\n", "Quadratic", c, r);
            int p = 1;
            double t = c * Math.pow(Math.pow(2, p), r);
            while (t < limit) {
                p++;
                t = c * Math.pow(Math.pow(2, p), r);
            }
            System.out.printf("  T(2^%d) = %8.1f seconds\n", --p, c * Math.pow(Math.pow(2, p), r));
            System.out.printf("  T(2^%d) = %8.1f seconds\n", ++p, c * Math.pow(Math.pow(2, p), r));
        }
    }

    public static void main(String[] args) {
        int init = 1000;
        int target = 60 * 60 * 24;   // seconds in a day
        int timelimit = 10;  // stop trials after time taken exceeds this limit
        String[] types = {"TwoSum", "TwoSumFast", "ThreeSum", "ThreeSumFast"};
        int[] runs = {100, 100000, 1, 10};  // configure runs to get reasonable values from timetrials

        for (int i = 0; i < types.length; i++) {
            System.out.printf("%s (%d runs each)\n", types[i], runs[i]);
            System.out.printf("%7s %8s %6s\n", "size", "time", "ratio");
            double prev = 0;
            for (int n = init; true; n += n) {
                double time = DoublingRatio.timeTrial(n, runs[i], types[i]);
                if (prev > 0) System.out.printf("%7d %8.3f %6.1f\n", n, time, time/prev);
                else          System.out.printf("%7d %8.3f\n", n, time);
                if (time > timelimit) {
                    printEstimate(Math.round(time/prev), time/runs[i], n, target);
                    break;
                }
                prev = time;
            }
        }
    }
}
