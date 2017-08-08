package net.qiguang.algorithms.C1_Fundamentals.S1_BasicProgrammingModel;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;

/**
 * 1.1.32 Histogram.
 * Suppose that the standard input stream is a sequence of double values. Write a program that takes
 * an integer N and two double values l and r from the command line and uses StdDraw to plot a histogram
 * of the count of the numbers in the standard input stream that fall in each of the N intervals defined
 * by dividing (l , r) into N equal-sized intervals.
 */
public class Exercise_1_1_32 {
    // delay in milliseconds (controls animation speed)
    private static final int DELAY = 20;

    public static int rank(double key, double[] a) {
        return rank(key, a, 0, a.length - 1);
    }
    public static int rank(double key, double[] a, int lo, int hi) {
        if (lo > hi) return hi;
        int mid = lo + (hi - lo) / 2;
        if      (key < a[mid]) return rank(key, a, lo, mid - 1);
        else if (key > a[mid]) return rank(key, a, mid + 1, hi);
        else                   return mid;
    }

    public static void main(String[] args) {
        // Parameters
        int n = args.length >= 1 ? Integer.parseInt(args[0]) : 180;
        double l = args.length >= 2 ? Double.parseDouble(args[1]) : -90;
        double r = args.length >= 3 ? Double.parseDouble(args[2]) : 90;
        double padding = 0.1;
        int yscale = 10;

        // Get intervals
        int[] h = new int[n];
        double[] a = new double[n];
        double delta = (r - l) / n;
        for (int i = 0; i < n; i++) {
            h[i] = 0;
            a[i] = l + delta * i;
        }

        // Set initial scale
        StdDraw.setXscale(0, n);
        StdDraw.setYscale(0, yscale);

        // Read doubles from file
        ClassLoader cl = Thread.currentThread().getContextClassLoader();
        In in = new In(cl.getResource("custom-data/citiesLat.txt").getFile());
        if (args.length >= 4) in = new In(args[0]);
        while (in.hasNextLine()) {
            double d = in.readDouble();
            int i = rank(d, a);
            if (i < 0 || i > n) continue;

            // Update histogram
            h[i]++;
            if (h[i] >= yscale) {
                yscale *= 1.5;
                StdDraw.setYscale(0, yscale);
                StdDraw.clear();
                for (int j = 0; j < h.length; j++) {
                    double[] x = {j + padding, j + 1 - padding, j + 1 - padding, j + padding};
                    double[] y = {0, 0, h[j], h[j]};
                    StdDraw.filledPolygon(x, y);
                }
            }
            else {
                double[] x = {i + padding, i + 1 - padding, i + 1 - padding, i + padding};
                double[] y = {0, 0, h[i], h[i]};
                StdDraw.filledPolygon(x, y);
            }
            StdDraw.pause(DELAY);
        }
    }
}
