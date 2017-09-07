package net.qiguang.algorithms.C1_Fundamentals.S4_AnalysisOfAlgorithms;

import java.util.Arrays;

/**
 * 1.4.21 Binary search on distinct values.
 *
 * Develop an implementation of binary search for StaticSETofInts (see page 98) where the running time of
 * contains() is guaranteed to be ~ lg(R), where R is the number of different integers in the array given
 * as argument to the constructor.
 */
public class Exercise_1_4_21 {
    public class StaticSETofInts {
        private int[] a;
        public StaticSETofInts(int[] keys)   {
            a = new int[keys.length];
            for (int i = 0; i < keys.length; i++)
                a[i] = keys[i]; // defensive copy
            Arrays.sort(a);
        }
        public boolean contains(int key)   {
            return rank(key) != -1;
        }
        private int rank(int key)   {
            // Binary search.
            int lo  = 0;
            int hi = a.length - 1;
            while (lo <= hi)      {
                // Key is in a[lo..hi] or not present.
                int mid = lo + (hi - lo) / 2;
                if      (key < a[mid]) hi = mid - 1;
                else if (key > a[mid]) lo = mid + 1;
                else                   return mid;
            }
            return -1;
        }
    }


}
