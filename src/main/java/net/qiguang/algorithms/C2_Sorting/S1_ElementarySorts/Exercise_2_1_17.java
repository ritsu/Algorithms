package net.qiguang.algorithms.C2_Sorting.S1_ElementarySorts;

import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Arrays;
import java.util.Random;

/**
 * 2.1.17 Animation.
 * Add code to Insertion and Selection to make them draw the array contents as vertical bars like the
 * visual traces in this section, redrawing the bars after each pass, to produce an animated effect,
 * ending in a “sorted” picture where the bars appear in order of their height.
 *
 * Hint : Use a client like the one in the text that generates random Double values, insert calls to show()
 * as appropriate in the sort code, and implement a show() method that clears the canvas and draws the bars.
 */
public class Exercise_2_1_17 {
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
            boolean[] touched = new boolean[N];
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
        double dx = 1.0/a.length;
        double x = 0;
        StdDraw.clear();
        StdDraw.setPenColor(0, 0, 0);
        if (xindex == 0) StdDraw.text(1.0/2, pady/3, "Insertion");
        else             StdDraw.text(1.0/2, pady/3, "Selection");
        for (int i = 0; i < a.length; i++) {
            if (i == current)
                StdDraw.setPenColor(224, 0, 0);
            else if (!touched[i])
                StdDraw.setPenColor(192, 192, 192);
            else
                StdDraw.setPenColor(0, 0, 0);
            StdDraw.filledRectangle(x + dx/2, pady + (Double) a[i]/2, dx/4, (Double) a[i]/2);
            x += dx;
        }
        StdDraw.show();
        StdDraw.pause(100);
    }
    public static void main(String[] args) {
        // Setup draw
        int width = 800;
        int height = 600;
        StdDraw.setCanvasSize(width, height);
        StdDraw.setScale();
        StdDraw.enableDoubleBuffering();

        // Array params
        int n = 80;
        Double[] a = new Double[n];
        Double[] b = new Double[n];

        // Generate random array
        for (int i = 0; i < n; i++) {
            double d = StdRandom.uniform(0, 1 - pady);
            a[i] = d;
            b[i] = d;
        }
        Insertion.sort(a);
        Selection.sort(b);

        // Generate reverse sorted array
        for (int i = 0; i < n; i++) {
            double d = (1-pady) - (1-pady)/n * i;
            a[i] = d;
            b[i] = d;
        }
        Insertion.sort(a);
        Selection.sort(b);
    }
}
