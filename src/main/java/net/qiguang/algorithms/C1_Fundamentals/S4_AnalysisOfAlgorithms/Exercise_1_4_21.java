package net.qiguang.algorithms.C1_Fundamentals.S4_AnalysisOfAlgorithms;

import edu.princeton.cs.algs4.Stopwatch;

import java.util.Arrays;

/**
 * 1.4.21 Binary search on distinct values.
 *
 * Develop an implementation of binary search for StaticSETofInts (see page 98) where the running time of
 * contains() is guaranteed to be ~ lg(R), where R is the number of different integers in the array given
 * as argument to the constructor.
 */
public class Exercise_1_4_21 {
    public static class StaticSETofInts {
        private int[] a;
        private int[] b;

        public StaticSETofInts(int[] keys, int r) {
            a = new int[keys.length];
            for (int i = 0; i < keys.length; i++)
                a[i] = keys[i]; // defensive copy
            Arrays.sort(a);
            // Create distinct copy
            b = new int[r];
            b[0] = a[0];
            int j = 1;
            for (int i = 1; i < a.length; i++)
                if (a[i] != b[j - 1]) b[j++] = a[i];
        }
        public boolean contains(int key) {
            return rank(key) != -1;
        }
        private int rank(int key) {
            // Binary search.
            int lo  = 0;
            int hi = b.length - 1;
            while (lo <= hi) {
                // Key is in a[lo..hi] or not present.
                int mid = lo + (hi - lo) / 2;
                if      (key < b[mid]) hi = mid - 1;
                else if (key > b[mid]) lo = mid + 1;
                else                   return mid;
            }
            return -1;
        }
        public boolean containsSlow(int key) {
            return rankSlow(key) != -1;
        }
        private int rankSlow(int key) {
            // Binary search.
            int lo  = 0;
            int hi = a.length - 1;
            while (lo <= hi) {
                // Key is in a[lo..hi] or not present.
                int mid = lo + (hi - lo) / 2;
                if      (key < a[mid]) hi = mid - 1;
                else if (key > a[mid]) lo = mid + 1;
                else                   return mid;
            }
            return -1;
        }
    }
    public static void main(String[] args) {
        // Create array
        int r = 100;
        int[] a = new int[r * 100000];
        // Distinct values
        for (int i = 0; i < r; i++) {
            a[i] = i;
        }
        // Duplicates
        for (int i = r; i < a.length; i++) {
            a[i] = 0;
        }

        // Test search
        StaticSETofInts s = new StaticSETofInts(a, r);
        System.out.printf(" contains(0): %s\n", s.contains(0));
        System.out.printf("contains(%d): %s\n", r - 1, s.contains(r - 1));

        // Compare times
        int n = 10000000;
        Stopwatch st = new Stopwatch();
        for (int i = 0; i < n; i++) {
            s.contains(i % r);
        }
        System.out.println("Distinct time: " + st.elapsedTime());
        st = new Stopwatch();
        for (int i = 0; i < n; i++) {
            s.containsSlow(i % r);
        }
        System.out.println("  Normal time: " + st.elapsedTime());
    }
}
