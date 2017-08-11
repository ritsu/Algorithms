package net.qiguang.algorithms.C1_Fundamentals.S2_DataAbstraction;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;

/**
 * 1.2.15 File input.
 * Develop a possible implementation of the static readInts() method from In
 * (which we use for various test clients, such as binary search on page 47)
 * that is based on the split() method in String.
 */
public class Exercise_1_2_15 {
    public static int[] readInts(String name) {
        In in = new In(name);
        String input = in.readAll();
        String[] words = input.split("\\s+");
        int[] ints = new int[words.length];
        for (int i = 0; i < words.length; i++) {
            ints[i] = Integer.parseInt(words[i]);
        }
        return ints;
    }
    public static void main(String[] args) {
        // Read ints
        ClassLoader cl = Thread.currentThread().getContextClassLoader();
        int[] ints = readInts(cl.getResource("algs4-data/tinyW.txt").getFile());
        if (args.length >= 1) ints = readInts(cl.getResource(args[0]).getFile());

        // Print ints
        StdOut.println(Arrays.toString(ints));
    }
}
