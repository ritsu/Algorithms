package net.qiguang.algorithms.C2_Sorting.S5_Applications;

import edu.princeton.cs.algs4.*;

import java.util.Arrays;

/**
 * 2.5.11 One way to describe the result of a sorting algorithm is to specify a permutation p[] of
 * the numbers 0 to a.length-1, such that p[i] specifies where the key originally in a[i] ends up.
 * Give the permutations that describe the results of insertion sort, selection sort, shellsort,
 * mergesort, quicksort, and heapsort for an array of seven equal keys.
 *
 * Answer:
 *   Insertion: 0 1 2 3 ... N
 *   Selection: 0 1 2 3 ... N
 *       Shell: 0 1 2 3 ... N
 *       Merge: 0 1 2 3 ... N
 *       Quick: Random shuffle
 *        Heap: 1 2 3 ... N 0    Building heap does not move anything.
 *                               First  "dequeue" exchanges 0   with N   and puts 0   at end.
 *                               Second "dequeue" exchanges N   with N-1 and puts N   at end.
 *                               Third  "dequeue" exchanges N-1 with N-2 and puts N-1 at end.
 *                               etc...
 */
public class Exercise_2_5_11 {
    static class Pair implements Comparable<Pair> {
        int key;
        int val;
        Pair(int k, int v) {
            key = k;
            val = v;
        }
        public int compareTo(Pair that) {
            return this.key - that.key;
        }
        public String toString() {
            return String.valueOf(val);
        }
    }
    public static void main(String[] args) {
        int n = 20;
        Pair[] p = new Pair[n];
        for (int i = 0; i < n; i++) {
            p[i] = new Pair(0, i);
        }
        Insertion.sort(p);
        System.out.printf("%12s: %s\n", "Insertion", Arrays.toString(p));

        for (int i = 0; i < n; i++) {
            p[i] = new Pair(0, i);
        }
        Selection.sort(p);
        System.out.printf("%12s: %s\n", "Selection", Arrays.toString(p));

        for (int i = 0; i < n; i++) {
            p[i] = new Pair(0, i);
        }
        Shell.sort(p);
        System.out.printf("%12s: %s\n", "Shell", Arrays.toString(p));

        for (int i = 0; i < n; i++) {
            p[i] = new Pair(0, i);
        }
        Merge.sort(p);
        System.out.printf("%12s: %s\n", "Merge", Arrays.toString(p));

        for (int i = 0; i < n; i++) {
            p[i] = new Pair(0, i);
        }
        Quick.sort(p);
        System.out.printf("%12s: %s\n", "Quick", Arrays.toString(p));

        for (int i = 0; i < n; i++) {
            p[i] = new Pair(0, i);
        }
        Heap.sort(p);
        System.out.printf("%12s: %s\n", "Heap", Arrays.toString(p));
    }
}
