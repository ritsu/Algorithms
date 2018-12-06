package net.qiguang.algorithms.C1_Fundamentals.S4_AnalysisOfAlgorithms;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.Stopwatch;

import java.util.Arrays;

/**
 * 1.4.8
 * Write a program to determine the number pairs of values in an input file that are equal.
 * If your first try is quadratic, think again and use Arrays.sort() to develop a linearithmic solution.
 *
 * Assume duplicate pairs are valid
 *   1, 1, 2, 3 = 2 pairs
 *   1, 1, 1, 2 = 3 pairs
 *   1, 1, 1, 1 = 6 pairs
 */
public class Exercise_1_4_08 {
    // Get first index of key in nums between lo and hi
    private static int findFirst(int[] nums, int key, int lo, int hi) {
        int first = -1;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if (key == nums[mid]) {
                first = mid;
                hi = mid - 1;
            }
            else if (key < nums[mid]) {
                hi = mid - 1;
            }
            else if (key > nums[mid]) {
                lo = mid + 1;
            }
        }
        return first;
    }

    // Get last index of key in nums between lo and hi
    private static int findLast(int[] nums, int key, int lo, int hi) {
        int last = -1;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if (key == nums[mid]) {
                last = mid;
                lo = mid + 1;
            }
            else if (key < nums[mid]) {
                hi = mid - 1;
            }
            else if (key > nums[mid]) {
                lo = mid + 1;
            }
        }
        return last;
    }

    // Get number of occurrences of key in nums between lo and hi
    private static int findCount(int[] nums, int key, int lo, int hi) {
        int first = findFirst(nums, key, lo, hi);
        if (first > -1)
            return findLast(nums, key, lo, hi) - first + 1;
        return 0;
    }

    public static int countEqualPairs(int[] nums) {
        Arrays.sort(nums);
        int count = 0;
        for (int i = 0; i < nums.length - 1; i++) {
            count += findCount(nums, nums[i], i + 1, nums.length - 1);
        }
        return count;
    }

    public static void main(String[] args) {
        // Read ints
        ClassLoader cl = Thread.currentThread().getContextClassLoader();
        In in = new In(cl.getResource("algs4-data/8Kints.txt").getFile());
        int[] nums = in.readAllInts();
        StdOut.println(countEqualPairs(nums));

        // Doubling test
        StdOut.printf("%8s %8s %10s\n", "Size", "Time", "Count");
        for (int i = 1; i <= 64; i *= 2) {
            int size = i * 200000;
            nums = new int[size];
            for (int j = 0; j < size; j++) {
                nums[j] = StdRandom.uniform(0, size);
            }
            Stopwatch timer = new Stopwatch();
            int count = countEqualPairs(nums);
            StdOut.printf("%8d %8.3f %10d\n", size, timer.elapsedTime(), count);
        }
    }
}
