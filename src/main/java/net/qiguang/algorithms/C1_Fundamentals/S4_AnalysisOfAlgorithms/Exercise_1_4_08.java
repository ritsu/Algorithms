package net.qiguang.algorithms.C1_Fundamentals.S4_AnalysisOfAlgorithms;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Arrays;

/**
 * 1.4.8
 * Write a program to determine the number pairs of values in an input file that are equal.
 * If your first try is quadratic, think again and use Arrays.sort() to develop a linearithmic solution.
 */
public class Exercise_1_4_08 {
    public static int countEqualPairs(int[] nums) {
        Arrays.sort(nums);
        int count = 0;
        int prev = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == prev) {
                //System.out.format("%d, %d\n", prev, nums[i]);
                count++;
            }
            prev = nums[i];
        }
        return count;
    }
    public static void main(String[] args) {
        // Read ints
        ClassLoader cl = Thread.currentThread().getContextClassLoader();
        In in = new In(cl.getResource("algs4-data/8Kints.txt").getFile());
        int[] nums = in.readAllInts();
        StdOut.println(countEqualPairs(nums));

        // Generate random ints
        for (int i = 0; i < 1000; i++) {
            nums[i] = StdRandom.uniform(0, 10000);
        }
        StdOut.println(countEqualPairs(nums));
    }
}
