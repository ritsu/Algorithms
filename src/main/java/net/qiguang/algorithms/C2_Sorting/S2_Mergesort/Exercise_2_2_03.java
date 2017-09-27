package net.qiguang.algorithms.C2_Sorting.S2_Mergesort;

/**
 * 2.2.3
 * Answer Exercise 2.2.2 for bottom-up mergesort.
 *
 * Answer:
 *                                0 1 2 3 4 5 6 7 8 9 10 11
 *                                E A S Y Q U E S T I O N
 *          merge(a,  0,  0,  1)  A E
 *          merge(a,  2,  2,  3)      S Y
 *          merge(a,  4,  4,  5)          Q U
 *          merge(a,  6,  6,  7)              E S
 *          merge(a,  8,  8,  9)                  I T
 *          merge(a, 10, 10, 11)                      N O
 *        merge(a,  0,  1,  3)    A E S Y
 *        merge(a,  4,  5,  7)            E Q S U
 *        merge(a,  8,  9, 11)                    I N O T
 *      merge(a,  0,  3,  7)      A E E Q S S U Y
 *    merge(a,  0,  7, 11)        A E E I N O Q S S T U Y
 */
public class Exercise_2_2_03 {
    public static class MergeBU {
        private static Comparable[] aux;      // auxiliary array for merges

        public static void sort(Comparable[] a) {
            // Do lg N passes of pairwise merges.
            int N = a.length;
            aux = new Comparable[N];
            for (int sz = 1; sz < N; sz = sz+sz) {               // sz: subarray size
                for (int lo = 0; lo < N - sz; lo += sz + sz) {   // lo: subarray index
                    merge(a, lo, lo + sz - 1, Math.min(lo + sz + sz - 1, N - 1));
                    // Print for exercise
                    System.out.printf("merge(a, %2d, %2d, %2d) ", lo, lo + sz - 1, Math.min(lo + sz + sz - 1, N - 1));
                    for (int i = lo; i <= Math.min(lo + sz + sz - 1, N - 1); i++) {
                        System.out.printf("%3s", a[i]);
                    }
                    System.out.println();

                }
            }
        }

        public static void merge(Comparable[] a, int lo, int mid, int hi) {
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
        String s = "E A S Y Q U E S T I O N";
        String[] a = s.split("\\s+");
        MergeBU.sort(a);
    }
}
