package net.qiguang.algorithms.C2_Sorting.S3_Quicksort;

import edu.princeton.cs.algs4.StdRandom;

import java.util.Arrays;
import java.util.Random;

/**
 * 2.3.9 Explain what happens when Quick.sort() is run on an array having items with just two distinct keys,
 * and then explain what happens when it is run on an array having just three distinct keys.
 *
 * TODO
 */
public class Exercise_2_3_09 {
    public static class Quick {
        static int ecount;

        public static int sort(Comparable[] a) {
            StdRandom.shuffle(a);
            ecount = 0;
            sort(a, 0, a.length - 1);
            return ecount;
        }
        private static void sort(Comparable[] a, int lo, int hi) {
            if (hi <= lo) {
                //System.out.printf("%4d %4s %4d %s\n", lo, "-", hi, Arrays.toString(a));
                return;
            }
            int j = partition(a, lo, hi);
            //System.out.printf("%4d %4d %4d %s\n", lo, j, hi, Arrays.toString(a));
            sort(a, lo, j - 1);
            sort(a, j + 1, hi);
        }
        private static int partition(Comparable[] a, int lo, int hi) {
            // Partition into a[lo..i-1], a[i], a[i+1..hi].
            int i = lo, j = hi+1;
            Comparable v = a[lo];
            while (true) {
                while (less(a[++i], v)) if (i == hi) break;
                while (less(v, a[--j])) if (j == lo) break;
                if (i >= j) break;
                exch(a, i, j);
            }
            exch(a, lo, j);
            return j;             // with a[lo..j-1] <= a[j] <= a[j+1..hi].
        }
        private static boolean less(Comparable a, Comparable b) {
            return a.compareTo(b) < 0;
        }
        private static void exch(Comparable[] a, int i, int j) {
            ecount++;
            Comparable copy = a[i];
            a[i] = a[j];
            a[j] = copy;
        }
    }
    public static void main(String[] args) {
        int trials = 100;
        Random r = new Random();
        System.out.printf("%8s %8s %8s %8s %8s %8s\n", "n", "1", "2", "3", "4", "n");
        for (int n = 100; n < 10000; n += n) {
            System.out.printf("%8s", n);
            Integer[] a = new Integer[n];
            for (int j = 1; j <= 4; j++) {
                for (int i = 0; i < n; i++) {
                    a[i] = r.nextInt(j);
                }
                int count = 0;
                for (int t = 0; t < trials; t++) {
                    count += Quick.sort(a);
                }
                System.out.printf("%9d", count/trials);
            }
            for (int i = 0; i < n; i++) {
                a[i] = i;
            }
            int count = 0;
            for (int t = 0; t < trials; t++) {
                count += Quick.sort(a);
            }
            System.out.printf("%9d", count/trials);
            System.out.println();
        }
    }
}
