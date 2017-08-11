package net.qiguang.algorithms.C1_Fundamentals.S2_DataAbstraction;

import edu.princeton.cs.algs4.*;
import java.util.Arrays;

/**
 * 1.2.9
 * Instrument BinarySearch (page 47) to use a Counter to count the total number of keys examined
 * during all searches and then print the total after all searches are complete.
 *
 * Hint: Create a Counter in main() and pass it as an argument to rank().
 */
public class Exercise_1_2_09 {
    public static int rank(int key, int[] a, Counter c) {
        // Array must be sorted.
        int lo = 0;
        int hi = a.length - 1;
        while (lo <= hi) {
            // Key is in a[lo..hi] or not present.
            c.increment();
            int mid = lo + (hi - lo) / 2;
            if (key < a[mid]) hi = mid - 1;
            else if (key > a[mid]) lo = mid + 1;
            else return mid;
        }
        return -1;
    }

    public static void main(String[] args) {
        // Resources
        ClassLoader cl = Thread.currentThread().getContextClassLoader();
        In inw = new In(cl.getResource("algs4-data/tinyW.txt").getFile());
        if (args.length >= 1) inw = new In(args[0]);

        int[] whitelist = inw.readAllInts();
        Arrays.sort(whitelist);

        // Print whitelist
        StdOut.printf("Whitelist: %s\n", Arrays.toString(whitelist));

        // Generate keys
        int[] input = new int[10];
        for (int i = 0; i < input.length; i++) {
            input[i] = StdRandom.uniform(whitelist[0], whitelist[whitelist.length - 1]);
        }

        // Examine keys
        Counter c = new Counter("Keys examined");
        StdOut.printf("Search misses: ");
        for (int key : input) {
            // Read key, print if not in whitelist.
            if (rank(key, whitelist, c) < 0)
                StdOut.print(key + " ");
        }
        StdOut.printf("\n%s\n", c);
    }

}
