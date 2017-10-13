package net.qiguang.algorithms.C2_Sorting.S5_Applications;

import edu.princeton.cs.algs4.StdRandom;

import java.util.Arrays;
import java.util.Random;

/**
 * 2.5.6 Implement a recursive version of select()
 */
public class Exercise_2_5_06 {
    private static Comparable select(Comparable[] a, int k) {
        // Disable shuffle for the sake of comparison
        // StdRandom.shuffle(a);
        int lo = 0, hi = a.length - 1;
        while (hi > lo) {
            int j = partition(a, lo, hi);
            if     (j == k)  return a[k];
            else if (j > k)  hi = j - 1;
            else if (j < k)  lo = j + 1;
        }
        return a[k];
    }
    private static Comparable selectRecursive(Comparable[] a, int k) {
        // Disable shuffle for the sake of comparison
        // StdRandom.shuffle(a);
        return selectRecursive(a, k, 0, a.length - 1);
    }
    private static Comparable selectRecursive(Comparable[] a, int k, int lo, int hi) {
        int j = partition(a, lo, hi);
        if     (j == k) return a[k];
        else if (j > k) return selectRecursive(a, k, lo, j - 1);
        else            return selectRecursive(a, k, j + 1, hi);
    }
    private static int partition(Comparable[] a, int lo, int hi) {
        int i = lo;
        int j = hi + 1;
        Comparable v = a[lo];
        while (true) {
            while (less(a[++i], v))
                if (i == hi) break;
            while (less(v, a[--j]))
                if (j == lo) break;      // redundant since a[lo] acts as sentinel
            if (i >= j) break;
            exch(a, i, j);
        }
        exch(a, lo, j);
        return j;
    }
    private static boolean less(Comparable a, Comparable b) {
        return a.compareTo(b) < 0;
    }
    private static void exch(Object[] a, int i, int j) {
        Object tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
    }
    public static void main(String[] args) {
        int n = 20;
        Integer[] a = new Integer[n];
        Integer[] b = new Integer[n];
        Random r = new Random();
        for (int t = 0; t < 10; t++) {
            for (int i = 0; i < n; i++) {
                a[i] = r.nextInt(n);
                b[i] = a[i];
            }
            int k = r.nextInt(20);
            System.out.printf("%3d %3d %s\n", k, select(a, k), Arrays.toString(a));
            System.out.printf("%3d %3d %s\n", k, selectRecursive(b, k), Arrays.toString(b));
        }
    }
}
