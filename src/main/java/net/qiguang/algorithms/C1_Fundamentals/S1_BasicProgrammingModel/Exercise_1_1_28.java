package net.qiguang.algorithms.C1_Fundamentals.S1_BasicProgrammingModel;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import java.util.Arrays;

/**
 * 1.1.28 Remove duplicates.
 * Modify the test client in BinarySearch to remove any duplicate keys
 * in the whitelist after the sort.
 */
public class Exercise_1_1_28 {
    public static int rank(int key, int[] a) {
        return rank(key, a, 0, a.length - 1);
    }
    public static int rank(int key, int[] a, int lo, int hi) {
        if (lo > hi) return -1;
        int mid = lo + (hi - lo) / 2;
        if      (key < a[mid]) return rank(key, a, lo, mid - 1);
        else if (key > a[mid]) return rank(key, a, mid + 1, hi);
        else                   return mid;
    }

    public static void main(String[] args) {
        // Resources
        String w = "algs4-data/tinyW.txt";
        String t = "algs4-data/tinyT.txt";

        // Read whitelist
        In in;
        if (args.length >= 1) {
            in = new In(args[0]);
        }
        else {
            in = new In(Thread.currentThread().getContextClassLoader().getResource(w).getFile());
        }
        int[] whitelist = in.readAllInts();
        Arrays.sort(whitelist);

        // Remove duplicate keys from whitelist
        StdOut.printf("%11s: %s\n", "Whitelist", Arrays.toString(whitelist));
        int i = 0;
        int j = 1;
        while (j < whitelist.length) {
            if (whitelist[i] == whitelist[j]) {
                j++;
            }
            else {
                whitelist[++i] = whitelist[j];
            }
        }
        int[] whitelist_clean = Arrays.copyOf(whitelist, i + 1);
        StdOut.printf("%11s: %s\n", "Cleaned", Arrays.toString(whitelist_clean));

        // Read input
        if (args.length >= 2) {
            in = new In(args[1]);
        }
        else {
            in = new In(Thread.currentThread().getContextClassLoader().getResource(t).getFile());
        }
        int[] input = in.readAllInts();

        // Print numbers that are not in the whitelist
        StdOut.printf("%11s: %s\n", "Input", Arrays.toString(input));
        StdOut.printf("%11s: ", "Result");
        for (int key : input) {
            if (rank(key, whitelist_clean) == -1) {
                StdOut.printf(key + " ");
            }
        }
    }
}
