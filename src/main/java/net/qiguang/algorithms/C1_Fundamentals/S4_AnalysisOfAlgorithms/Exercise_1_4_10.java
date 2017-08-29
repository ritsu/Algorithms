package net.qiguang.algorithms.C1_Fundamentals.S4_AnalysisOfAlgorithms;

import java.util.Arrays;

/**
 * 1.4.10
 * Modify binary search so that it always returns the element with the smallest index that matches the
 * search element (and still guarantees logarithmic running time).
 */
public class Exercise_1_4_10 {
    public static int indexOf(int[] a, int key) {
        int lo = 0;
        int hi = a.length - 1;
        while (lo <= hi) {
            // Key is in a[lo..hi] or not present.
            int mid = lo + (hi - lo) / 2;
            if      (key < a[mid]) hi = mid - 1;
            else if (key > a[mid]) lo = mid + 1;
            else return mid;
        }
        return -1;
    }

    public static int smallestIndexOf(int[] a, int key) {
        int lo = 0;
        int hi = a.length - 1;
        while (lo <= hi) {
            // Key is in a[lo..hi] or not present.
            int mid = lo + (hi - lo) / 2;
            if      (key < a[mid]) hi = mid - 1;
            else if (key > a[mid]) lo = mid + 1;
            else {
                while (mid >= 0 && key == a[mid]) {
                    mid--;
                }
                return ++mid;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        // Generate an int array with duplicates
        int[] nums = new int[100];
        for (int i = 0; i < 100; i++) {
            if      (i ==  1) nums[i] = 0;
            else if (i ==  2) nums[i] = 0;
            else if (i == 52) nums[i] = i - 1;
            else         nums[i] = i;
        }

        // Not necessary given input array, but it's here on principle
        Arrays.sort(nums);

        // Compare original and modified binary search
        System.out.println("Original  Smallest");
        for (int i = 0; i < 5; i++) {
            System.out.format("%8d %9d\n", indexOf(nums, i), smallestIndexOf(nums, i));
        }
        for (int i = 50; i < 55; i++) {
            System.out.format("%8d %9d\n", indexOf(nums, i), smallestIndexOf(nums, i));
        }
    }
}
