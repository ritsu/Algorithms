package net.qiguang.algorithms.C1_Fundamentals.S4_AnalysisOfAlgorithms;

import java.util.Arrays;

/**
 * 1.4.11
 * Add an instance method howMany() to StaticSETofInts (page 99) that finds the number of occurrences
 * of a given key in time proportional to log N in the worst case.
 */
public class Exercise_1_4_11 {
    public static class StaticSETofInts {
        private int[] a;

        public StaticSETofInts(int[] keys) {
            a = new int[keys.length];
            for (int i = 0; i < keys.length; i++)
                a[i] = keys[i]; // defensive copy
            Arrays.sort(a);
        }

        public boolean contains(int key) {
            return rank(key) != -1;
        }

        private int rank(int key) {
            // Binary search.
            int lo = 0;
            int hi = a.length - 1;
            while (lo <= hi) {
                // Key is in a[lo..hi] or not present.
                int mid = lo + (hi - lo) / 2;
                if (key < a[mid]) hi = mid - 1;
                else if (key > a[mid]) lo = mid + 1;
                else return mid;
            }
            return -1;
        }

        public int howMany(int key) {
            int lo = 0;
            int hi = a.length - 1;
            while (lo <= hi) {
                int mid = lo + (hi - lo) / 2;
                if (key < a[mid]) hi = mid - 1;
                else if (key > a[mid]) lo = mid + 1;
                else {
                    int m = 1;
                    for (int i = mid + 1; i < a.length && a[i] == key; i++) {
                        m++;
                    }
                    for (int i = mid - 1; i >= 0 && a[i] == key; i--) {
                        m++;
                    }
                    return m;
                }
            }
            return 0;
        }
    }
    public static void main(String[] args) {
        int[] a = {0, 1, 2, 3, 3, 3, 4, 5};
        StaticSETofInts s = new StaticSETofInts(a);
        System.out.println(s.howMany(2));
        System.out.println(s.howMany(3));
        System.out.println(s.howMany(6));
    }
}
