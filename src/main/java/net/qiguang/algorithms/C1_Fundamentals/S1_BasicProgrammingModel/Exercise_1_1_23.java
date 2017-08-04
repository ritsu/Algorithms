package net.qiguang.algorithms.C1_Fundamentals.S1_BasicProgrammingModel;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;

/**
 * 1.1.23
 * Add to the BinarySearch test client the ability to respond to a second argument:
 * + to print numbers from standard input that are not in the whitelist,
 * - to print numbers that are in the whitelist
 */
public class Exercise_1_1_23 {
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

        // Whitelist
        In in = new In(Thread.currentThread().getContextClassLoader().getResource(w).getFile());
        int[] whitelist = in.readAllInts();
        Arrays.sort(whitelist);
        StdOut.println("Whitelist: " + Arrays.toString(whitelist));

        // Read input
        in = new In(Thread.currentThread().getContextClassLoader().getResource(t).getFile());
        int[] input = in.readAllInts();
        StdOut.println("Input: " + Arrays.toString(input));

        // Get +/- argument
        StdOut.println("\nEnter + to print numbers that are not in the whitelist, " +
                "- to print numbers that are in the whitelist:");
        String s = StdIn.readLine();

        // + to print numbers that are not in the whitelist
        // - to print numbers that are in the whitelist
        for(int key : input) {
            if (rank(key, whitelist) == -1) {
                if (s.equals("+")) StdOut.printf(key + " ");
            }
            else {
                if (s.equals("-")) StdOut.printf(key + " ");
            }
        }

    }
}
