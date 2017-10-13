package net.qiguang.algorithms.C2_Sorting.S5_Applications;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.IndexMaxPQ;
import edu.princeton.cs.algs4.MaxPQ;

import java.util.Arrays;

/**
 * 2.5.8 Write a program Frequency that reads strings from standard input and
 * prints the number of times each string occurs, in descending order of frequency.
 */
public class Exercise_2_5_08 {
    private static class Pair implements Comparable<Pair> {
        int count;
        String item;
        Pair(int n, String s) {
            count = n;
            item = s;
        }
        public int compareTo(Pair other) {
            return this.count - other.count;
        }
    }
    public static void main(String[] args) {
        ClassLoader cl = Thread.currentThread().getContextClassLoader();
        args = new String[] {cl.getResource("algs4-data/tinyTale.txt").getFile()};
        In in = new In(args[0]);

        String[] s = in.readAllStrings();
        Arrays.sort(s);
        MaxPQ<Pair> q = new MaxPQ<>();
        for (int i = 0, count = 1; i < s.length; i++) {
            if (i == s.length - 1) {
                q.insert(new Pair(count, s[i]));
            }
            else if (s[i].equals(s[i+1])) {
                count++;
            }
            else {
                q.insert(new Pair(count, s[i]));
                count = 1;
            }
        }
        while (!q.isEmpty()) {
            Pair p = q.delMax();
            System.out.printf("%3d %s\n", p.count, p.item);
        }

    }
}
