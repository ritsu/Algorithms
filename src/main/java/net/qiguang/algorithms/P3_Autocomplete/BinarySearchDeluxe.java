package net.qiguang.algorithms.P3_Autocomplete;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Arrays;
import java.util.Comparator;

public class BinarySearchDeluxe {
    // Returns the index of the first key in a[] that equals the search key, or -1 if no such key.
    public static <Key> int firstIndexOf(Key[] a, Key key, Comparator<Key> comparator) {
        int lo = 0;
        int hi = a.length - 1;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if      (comparator.compare(a[mid], key) > 0) hi = mid - 1;
            else if (comparator.compare(a[mid], key) < 0) lo = mid + 1;
            else {
                while (mid >= 0 && comparator.compare(a[mid], key) == 0) {
                    mid--;
                }
                return mid + 1;
            }
        }
        return -1;
    }

    // Returns the index of the last key in a[] that equals the search key, or -1 if no such key.
    public static <Key> int lastIndexOf(Key[] a, Key key, Comparator<Key> comparator) {
        int lo = 0;
        int hi = a.length - 1;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if      (comparator.compare(a[mid], key) > 0) hi = mid - 1;
            else if (comparator.compare(a[mid], key) < 0) lo = mid + 1;
            else {
                while (mid < a.length && comparator.compare(a[mid], key) == 0) {
                    mid++;
                }
                return mid - 1;
            }
        }
        return -1;
    }

    // unit testing (required)
    public static void main(String[] args) {
        // Environment specific input, delete when submitting ----------
        ClassLoader cl = Thread.currentThread().getContextClassLoader();
        args = new String[] {cl.getResource("algs4-data/P3_Autocomplete/2grams.txt").getFile()};
        // -------------------------------------------------------------

        // Process args
        In in = new In(args[0]);

        // Store all terms to array
        int n = in.readInt();
        Term[] all = new Term[n];
        for (int i = 0; i < n; i++) {
            long w = in.readLong();
            String q = in.readLine().trim();
            all[i] = new Term(q, w);
        }

        // Get random item
        Term t = all[StdRandom.uniform(n)];
        System.out.println(t);

        // Compare
        System.out.println("\nDefault:");
        Arrays.sort(all, Term::compareTo);
        int first = BinarySearchDeluxe.firstIndexOf(all, t, Term::compareTo);
        int last = BinarySearchDeluxe.lastIndexOf(all, t, Term::compareTo);
        if (first < 0 || last < 0) {
            System.out.println("  not found");
        }
        else {
            if (first > 0)
                System.out.printf("   %s\n", all[first - 1]);
            for (int i = first; i <= last; i++)
                System.out.printf(" * %s\n", all[i]);
            if (last < n - 2)
                System.out.printf("   %s\n", all[last + 1]);
        }

        // Weight
        System.out.println("\nReverse Weight:");
        Arrays.sort(all, Term.byReverseWeightOrder());
        first = BinarySearchDeluxe.firstIndexOf(all, t, Term.byReverseWeightOrder());
        last = BinarySearchDeluxe.lastIndexOf(all, t, Term.byReverseWeightOrder());
        if (first < 0 || last < 0) {
            System.out.println("  not found");
        }
        else {
            if (first > 0)
                System.out.printf("   %s\n", all[first - 1]);
            for (int i = first; i <= last; i++)
                System.out.printf(" * %s\n", all[i]);
            if (last < n - 2)
                System.out.printf("   %s\n", all[last + 1]);
        }

        // Prefix
        int r = 5 + StdRandom.uniform(5);
        Arrays.sort(all, Term.byPrefixOrder(r));
        System.out.printf("\nPrefix (%d):\n", r);
        first = BinarySearchDeluxe.firstIndexOf(all, t, Term.byPrefixOrder(r));
        last = BinarySearchDeluxe.lastIndexOf(all, t, Term.byPrefixOrder(r));
        if (first < 0 || last < 0) {
            System.out.println("  not found");
        }
        else {
            if (first > 0)
                System.out.printf("   %s\n", all[first - 1]);
            for (int i = first; i <= last; i++)
                System.out.printf(" * %s\n", all[i]);
            if (last < n - 2)
                System.out.printf("   %s\n", all[last + 1]);
        }
    }
}
