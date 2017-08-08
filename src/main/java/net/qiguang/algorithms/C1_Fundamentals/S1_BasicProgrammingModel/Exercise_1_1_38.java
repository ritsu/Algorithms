package net.qiguang.algorithms.C1_Fundamentals.S1_BasicProgrammingModel;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.Stopwatch;

import java.util.NoSuchElementException;

/**
 * 1.1.38 Binary search versus brute-force search.
 * Write a program BruteForceSearch that uses the brute-force search method given on page 48
 * and compare its running time on your computer with that of BinarySearch for largeW.txt and largeT.txt.
 */
public class Exercise_1_1_38 {
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

    public static int bruteRank(int key, int[] a) {
        for (int i = 0; i < a.length; i++)
            if (a[i] == key) return i;
        return -1;
    }

    public static void main(String[] args) {
        // Whitelist
        ClassLoader cl = Thread.currentThread().getContextClassLoader();
        In in = new In(cl.getResource("algs4-data/largeW.txt").getFile());
        int[] whitelist = in.readAllInts();

        // Binary Search
        Stopwatch s = new Stopwatch();
        in = new In(cl.getResource("algs4-data/largeT.txt").getFile());
        int count = 0;
        StdOut.printf("Binary Search:");
        while (in.hasNextLine()) {
            try {
                int i = in.readInt();
                // if (rank(i, whitelist) == -1) StdOut.println(i);
                if (rank(i, whitelist) == -1){
                    if (++count % 10000 == 0) StdOut.print(".");
                }
            } catch(NoSuchElementException ignored) {}
        }
        StdOut.printf(" %f sec, %d found\n", s.elapsedTime(), count);

        // Brute Force Search
        s = new Stopwatch();
        in = new In(cl.getResource("algs4-data/largeT.txt").getFile());
        count = 0;
        StdOut.printf("Brute Force  :");
        while (in.hasNextLine()) {
            try {
                int i = in.readInt();
                //if (bruteRank(i, whitelist) == -1) StdOut.println(i);
                if (bruteRank(i, whitelist) == -1) {
                    if (++count % 10000 == 0) StdOut.print(".");
                }
            } catch(NoSuchElementException ignored) {}
        }
        StdOut.printf(" %f sec, %d found\n", s.elapsedTime(), count);
    }
}
