package net.qiguang.algorithms.C2_Sorting.S2_Mergesort;

import java.util.Arrays;
import java.util.Random;

/**
 * 2.2.12 Sublinear extra space.
 * Develop a merge implementation that reduces the extra space requirement to max(M, N/M),
 * based on the following idea:
 * Divide the array into N/M blocks of size M (for simplicity in this description, assume that
 * N is a multiple of M). Then,
 * (i) considering the blocks as items with their first key as the sort key, sort them using selection sort; and
 * (ii) run through the array merging the first block with the second, then the second block with the third,
 * and so forth.
 *
 * TODO
 */
public class Exercise_2_2_12 {
    public static class Merge {
        private static int blockSize = 10;
        public static void sort(Comparable[] a) {
            // For this problem, assume a.length is a multiple of blockSize
            Comparable[] aux = new Comparable[blockSize];
            sort(a, aux);
        }

        private static void sort(Comparable[] a, Comparable[] aux) {
            // Selection sort
            for (int i = 0; i < a.length; i += blockSize) {
                for (int j = i; (j - i) < blockSize; j++) {
                    int min = j;
                    for (int k = j + 1; (k - i) < blockSize; k++)
                        if (less(a[k], a[min])) min = k;
                    exch(a, j, min);
                }
            }
            // Need to merge N/M times because max item may be 1st block, or min item in N/M-th block
            for (int k = 0; k < a.length / blockSize; k++)
                for (int i = 0; (i + blockSize) < a.length; i += blockSize)
                    merge(a, aux, i, i + blockSize - 1, i + 2 * blockSize - 1);
        }

        public static void merge(Comparable[] a, Comparable[] aux, int lo, int mid, int hi) {
            for (int k = 0; k < blockSize; k ++)
                aux[k] = a[lo + k];
            int i = 0;             // Left index
            int j = mid + 1;       // Right index
            for (int k = lo; k <= hi && i < blockSize; k++) {
                if (j > hi) {
                    a[k] = aux[i++];
                } else if (less(a[j], aux[i])) {
                    a[k] = a[j++];
                } else {
                    a[k] = aux[i++];
                }
            }
        }

        private static boolean less(Comparable v, Comparable w) {
            return v.compareTo(w) < 0;
        }

        private static void exch(Comparable[] a, int i, int j) {
            Comparable copy = a[i];
            a[i] = a[j];
            a[j] = copy;
        }
    }

    public static boolean isSorted(Comparable[] a) {
        for (int i = 1; i < a.length; i++)
            if (a[i].compareTo(a[i-1]) < 0) return false;
        return true;
    }

    public static void main(String[] args) {
        // Test a reverse sorted array
        Integer[] nums = new Integer[30];
        for (int i = 0; i < nums.length; i++)
            nums[i] = nums.length - i;
        Merge.sort(nums);
        System.out.println(Arrays.toString(nums));

        // Test random array
        Random r = new Random();
        for (int i = 0; i < nums.length; i++) {
            int j = r.nextInt(nums.length);
            int n = nums[i];
            nums[i] = nums[j];
            nums[j] = n;
        }
        Merge.sort(nums);
        System.out.println(Arrays.toString(nums));

        // Test multiple random arrays
        for (int n = 10; n < 2000; n += 10) {
            Integer[] a = new Integer[n];
            for (int i = 0; i < n; i++)
                a[i] = r.nextInt(n);
            Merge.sort(a);
            if (!isSorted(a)) System.out.println("We have a problem.");
        }
    }
}
