package net.qiguang.algorithms.C1_Fundamentals.S1_BasicProgrammingModel;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import java.util.Arrays;

/**
 * 1.1.22
 * Write a version of BinarySearch that uses the recursive rank() given on page 25 and
 * traces the method calls. Each time the recursive method is called, print the argument
 * values lo and hi, indented by the depth of the recursion.
 *
 * Hint: Add an argument to the recursive method that keeps track of the depth.
 */
public class Exercise_1_1_22 {
    public static int rank(int key, int[] a) {
        return rank(key, a, 0, 0, a.length - 1);
    }
    public static int rank(int key, int[] a, int depth, int lo, int hi) {
        StdOut.printf("%4d: %4d %4d\n", depth, lo, hi);
        if (lo > hi) return -1;
        int mid = lo + (hi - lo) / 2;
        if      (key < a[mid]) return rank(key, a, ++depth, lo, mid - 1);
        else if (key > a[mid]) return rank(key, a, ++depth, mid + 1, hi);
        else                   return mid;
    }

    public static void main(String[] args) {
        // Data parameters
        int size = 100;
        int min = 0;
        int max = 100;
        int search = StdRandom.uniform(min, max);

        // Generate data
        int[] a = new int[size];
        for (int i = 0; i < size; i++) {
            a[i] = StdRandom.uniform(min, max);
        }
        Arrays.sort(a);

        // Perform search
        StdOut.println("Data: " + Arrays.toString(a));
        StdOut.println("Search: " + search);
        StdOut.printf("%4s %4s %4s\n", "Depth", "Lo", "Hi");
        StdOut.println("Result: " + rank(search, a));

    }
}
