package net.qiguang.algorithms.C2_Sorting.S2_Mergesort;

/**
 * 2.2.5
 * Give the sequence of subarray sizes in the merges performed by both the
 * topdown and the bottom-up mergesort algorithms, for N = 39.
 *
 * Answer:
 * Merge:
 *   2  3  2  5  2  3  2  5 10  2  3  2  5  2  3  2  5 10 20  2  3  2  5  2  3  2  5 10  2  3  2  5  2  2  4  9 19 39
 * MergeBU:
 *   2  2  2  2  2  2  2  2  2  2  2  2  2  2  2  2  2  2  2  4  4  4  4  4  4  4  4  4  3  8  8  8  8  7 16 16 32 39
 */
public class Exercise_2_2_05 {
    public static class Merge {
        private static Comparable[] aux;      // auxiliary array for merges

        public static void sort(Comparable[] a) {
            aux = new Comparable[a.length];    // Allocate space just once.
            sort(a, 0, a.length - 1);
        }

        private static void sort(Comparable[] a, int lo, int hi) {
            // Sort a[lo..hi].
            if (hi <= lo) return;
            int mid = lo + (hi - lo) / 2;
            sort(a, lo, mid);          // Sort left half.
            sort(a, mid + 1, hi);     // Sort right half.
            merge(a, lo, mid, hi);     // Merge results (code on page 271).
        }

        public static void merge(Comparable[] a, int lo, int mid, int hi) {
            System.out.printf("%3d", hi-lo+1);
            // Merge a[lo..mid] with a[mid+1..hi].
            int i = lo, j = mid + 1;
            for (int k = lo; k <= hi; k++)  // Copy a[lo..hi] to aux[lo..hi].
                aux[k] = a[k];
            for (int k = lo; k <= hi; k++)  // Merge back to a[lo..hi].
                if (i > mid) a[k] = aux[j++];
                else if (j > hi) a[k] = aux[i++];
                else if (less(aux[j], aux[i])) a[k] = aux[j++];
                else a[k] = aux[i++];
        }

        private static boolean less(Comparable v, Comparable w) {
            return v.compareTo(w) < 0;
        }
    }

    public static class MergeBU {
        private static Comparable[] aux;      // auxiliary array for merges

        public static void sort(Comparable[] a) {
            // Do lg N passes of pairwise merges.
            int N = a.length;
            aux = new Comparable[N];
            for (int sz = 1; sz < N; sz = sz+sz) {               // sz: subarray size
                for (int lo = 0; lo < N - sz; lo += sz + sz) {   // lo: subarray index
                    merge(a, lo, lo + sz - 1, Math.min(lo + sz + sz - 1, N - 1));
                }
            }
        }

        public static void merge(Comparable[] a, int lo, int mid, int hi) {
            System.out.printf("%3d", hi-lo+1);
            // Merge a[lo..mid] with a[mid+1..hi].
            int i = lo, j = mid + 1;
            for (int k = lo; k <= hi; k++)  // Copy a[lo..hi] to aux[lo..hi].
                aux[k] = a[k];
            for (int k = lo; k <= hi; k++)  // Merge back to a[lo..hi].
                if (i > mid) a[k] = aux[j++];
                else if (j > hi) a[k] = aux[i++];
                else if (less(aux[j], aux[i])) a[k] = aux[j++];
                else a[k] = aux[i++];
        }

        private static boolean less(Comparable v, Comparable w) {
            return v.compareTo(w) < 0;
        }
    }

    public static void main(String[] args) {
        int n = 39;
        Integer[] a = new Integer[n];
        for (int i = 0; i < n; i++) {
            a[i] = i;
        }
        System.out.println("Merge:");
        Merge.sort(a);
        System.out.println();
        System.out.println("MergeBU:");
        MergeBU.sort(a);
    }

}
