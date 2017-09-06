package net.qiguang.algorithms.C1_Fundamentals.S4_AnalysisOfAlgorithms;

import java.util.Arrays;
import java.util.Random;

/**
 * 1.4.18 Local minimum of an array.
 * Write a program that, given an array a[] of N distinct integers, finds a local minimum: an index i such that
 * a[i-1] < a[i] < a[i+1]. Your program should use ~2 lg N compares in the worst case.
 *
 * Note: Elements at the end of an array qualify as a local minimum if it is smaller than its only neighbor.
 *
 * Answer: Examine the middle value a[N/2] and its two neighbors a[N/2 - 1] and a[N/2 + 1].
 * If a[N/2] is a local minimum, stop; otherwise search in the half with the smaller neighbor.
 */
public class Exercise_1_4_18 {
    public static int localMinimum(int[] nums) {
        return localMinimum(nums, 0, nums.length - 1);
    }
    public static int localMinimum(int[] nums, int lo, int hi) {
        int mid = (lo + hi) / 2;
        if (mid == 0)               return nums[mid] < nums[mid + 1] ? mid : -1;
        if (mid == nums.length - 1) return nums[mid] < nums[mid - 1] ? mid : -1;
        if (nums[mid] < nums[mid - 1] && nums[mid] < nums[mid + 1]) return mid;
        if (nums[mid - 1] < nums[mid + 1]) return localMinimum(nums, lo, mid - 1);
        else                               return localMinimum(nums, mid + 1, hi);
    }
    public static void main(String[] args) {
        int[][] a = new int[10][100];
        Random r = new Random();
        for (int i = 0; i < 100; i++) {
            a[0][i] = i;                     // 0...100
            a[1][i] = 100 - i;               // 100...0
            if (i == 25) a[2][i] = 23;       // 0...24,23,26...100
            else         a[2][i] = i;
            if (i == 75) a[3][i] = 73;       // 0...74,73,76...100
            else         a[3][i] = i;
            if (i == 25) a[4][i] = 73;       // 100...76,73,74...0
            else         a[4][i] = 100 - i;
            if (i == 74) a[5][i] = 24;       // 100...27,24,25...0
            else         a[5][i] = 100 - i;
            for (int j = 6; j < 10; j++) a[j][i] = r.nextInt(100);
        }
        for (int[] nums : a) {
            System.out.println(Arrays.toString(nums));
            int i = localMinimum(nums);
            if      (i == 0)               System.out.printf("%d: [ %d, %d\n", i, nums[i], nums[i+1]);
            else if (i == nums.length - 1) System.out.printf("%d: %d, %d ]\n", i, nums[i-1], nums[i]);
            else                           System.out.printf("%d: %d, %d, %d\n", i, nums[i-1], nums[i], nums[i+1]);
        }
    }
}
