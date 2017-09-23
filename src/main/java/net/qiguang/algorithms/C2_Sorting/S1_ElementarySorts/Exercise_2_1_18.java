package net.qiguang.algorithms.C2_Sorting.S1_ElementarySorts;

import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Random;

/**
 * 2.1.18 Visual trace.
 * Modify your solution to the previous exercise to make Insertion and Selection produce visual traces
 * such as those depicted in this section.
 *
 * Hint : Judicious use of setYscale() makes this problem easy.
 * Extra credit : Add the code necessary to produce red and gray color accents such as those in our figures.
 */
public class Exercise_2_1_18 {
    private static double padx = 0.05;      // Padding between insertion and selction graphs
    private static double pady = 0.04;      // Padding at bottom for text label

    public static class Insertion {
        private static boolean less(Comparable v, Comparable w) {
            return v.compareTo(w) < 0;
        }
        private static void exch(Comparable[] a, int i, int j) {
            Comparable t = a[i];
            a[i] = a[j];
            a[j] = t;
        }
        public static void sort(Comparable[] a) {
            // Sort a[] into increasing order.
            int N = a.length;
            int layer = 0;
            boolean[] touched = new boolean[N];
            // Draw initial bars for i = 0
            show(a, 0, touched, 0);
            for (int i = 1; i < N; i++) {
                // Reset touched array
                for (int t = 0; t < N; t++) touched[t] = false;
                // Insert a[i] among a[i-1], a[i-2], a[i-3]... ..
                for (int j = i; j > 0 && less(a[j], a[j-1]); j--) {
                    exch(a, j, j - 1);
                    touched[j] = true;
                    touched[j-1] = true;
                }
                // Draw bars
                show(a, i, touched, 0);
            }
        }
    }
    public static class Selection {
        private static boolean less(Comparable v, Comparable w) {
            return v.compareTo(w) < 0;
        }
        private static void exch(Comparable[] a, int i, int j) {
            Comparable t = a[i];
            a[i] = a[j];
            a[j] = t;
        }
        public static void sort(Comparable[] a) {
            // Sort a[] into increasing order.
            int N = a.length;               // array length
            int layer = 0;
            boolean[] touched = new boolean[N];
            for (int i = 0; i < N; i++) {
                // Reset touched array
                for (int t = 0; t < N; t++) touched[t] = false;
                // Exchange a[i] with smallest entry in a[i+1...N).
                int min = i;                 // index of minimal entry
                for (int j = i+1; j < N; j++) {
                    if (less(a[j], a[min])) min = j;
                    touched[j] = true;
                }
                exch(a, i, min);
                // Draw bars
                show(a, i, touched, 1);
            }
        }
    }
    // Draw bars
    public static void show(Comparable[] a, int current, boolean[] touched, int xindex) {
        double yfloor = 1 - (1-pady)/a.length * (current+1);
        double dx = (1-padx)/a.length/2;
        double x = ((1-padx)/2 + padx) * xindex;
        for (int i = 0; i < a.length; i++) {
            if (i == current)
                StdDraw.setPenColor(224, 0, 0);
            else if (!touched[i])
                StdDraw.setPenColor(192, 192, 192);
            else
                StdDraw.setPenColor(0, 0, 0);
            double y = (Double) a[i]/a.length;
            StdDraw.filledRectangle(x + dx/2, yfloor + y/2, dx/4, y/2);
            x += dx;
        }
        StdDraw.show();
        StdDraw.pause(30);
    }
    public static void main(String[] args) {
        // Setup draw
        int n = 18;
        int width = 800;
        int height = n * 50;
        StdDraw.setCanvasSize(width, height);
        StdDraw.setScale();
        StdDraw.enableDoubleBuffering();
        StdDraw.text(1.0/4, pady/3, "Insertion");
        StdDraw.text(3.0/4, pady/3, "Selection");

        // Make two identical arrays for sake of comparison
        Double[] a = new Double[n];
        Double[] b = new Double[n];

        // Generate random array
        for (int i = 0; i < n; i++) {
            // Make d at least 0.1 and less than 0.9 for easier visualization
            Double d = StdRandom.uniform(0.1, 0.9);
            a[i] = d;
            b[i] = d;
        }
        Insertion.sort(a);
        Selection.sort(b);
    }
}
