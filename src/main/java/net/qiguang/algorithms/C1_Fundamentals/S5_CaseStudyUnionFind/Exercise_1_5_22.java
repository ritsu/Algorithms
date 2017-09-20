package net.qiguang.algorithms.C1_Fundamentals.S5_CaseStudyUnionFind;

import edu.princeton.cs.algs4.QuickFindUF;
import edu.princeton.cs.algs4.QuickUnionUF;
import edu.princeton.cs.algs4.Stopwatch;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

import java.util.Random;

/**
 * 1.5.22 Doubling test for Erdös-Renyi model.
 * Develop a performance-testing client that takes an int value T from the command line and performs
 * T trials of the following experiment: Use your client from Exercise 1.5.17 to generate random connections,
 * using UnionFind to determine connectivity as in our development client, looping until all sites are
 * connected. For each N, print the value of N, the average number of connections processed, and the ratio
 * of the running time to the previous. Use your program to validate the hypotheses in the text that the running
 * times for quick-ﬁnd and quick-union are quadratic and weighted quick-union is near-linear.
 */
public class Exercise_1_5_22 {
    public static class ErdosRenyi {
        private ErdosRenyi() {}
        public static int count(int n) {
            int result = 0;
            WeightedQuickUnionUF uf = new WeightedQuickUnionUF(n);
            Random r = new Random();
            while (uf.count() > 1) {
                result++;
                int a = r.nextInt(n);
                int b = r.nextInt(n);
                if (uf.connected(a, b)) continue;
                uf.union(a, b);
            }
            return result;
        }
        public static int countQF(int n) {
            int result = 0;
            QuickFindUF uf = new QuickFindUF(n);
            Random r = new Random();
            while (uf.count() > 1) {
                result++;
                int a = r.nextInt(n);
                int b = r.nextInt(n);
                if (uf.connected(a, b)) continue;
                uf.union(a, b);
            }
            return result;
        }
        public static int countQU(int n) {
            int result = 0;
            QuickUnionUF uf = new QuickUnionUF(n);
            Random r = new Random();
            while (uf.count() > 1) {
                result++;
                int a = r.nextInt(n);
                int b = r.nextInt(n);
                if (uf.connected(a, b)) continue;
                uf.union(a, b);
            }
            return result;
        }
    }
    public static void main(String[] args) {
        int runs = 10;

        // QuickUnion
        System.out.println("QuickUnion");
        System.out.printf("%8s %8s %8s\n", "N", "Cons", "Ratio");
        double prev = 0;
        for (int n = 500; n < 50000; n += n) {
            int count = 0;
            Stopwatch s = new Stopwatch();
            for (int i = 0; i < runs; i++)
                count += ErdosRenyi.countQU(n);
            double ratio = prev > 0 ? s.elapsedTime() / prev : 0;
            prev = s.elapsedTime();
            System.out.printf("%8d %8d %6.1f\n", n, count / runs, ratio);
        }

        // QuickFind
        System.out.println("QuickFind");
        System.out.printf("%8s %8s %8s\n", "N", "Cons", "Ratio");
        prev = 0;
        for (int n = 500; n < 50000; n += n) {
            int count = 0;
            Stopwatch s = new Stopwatch();
            for (int i = 0; i < runs; i++)
                count += ErdosRenyi.countQF(n);
            double ratio = prev > 0 ? s.elapsedTime() / prev : 0;
            prev = s.elapsedTime();
            System.out.printf("%8d %8d %6.1f\n", n, count / runs, ratio);
        }

        // WeightedQuickUnion
        System.out.println("WeightedQuickUnion");
        System.out.printf("%8s %8s %8s\n", "N", "Cons", "Ratio");
        prev = 0;
        for (int n = 5000; n < 1000000; n += n) {
            int count = 0;
            Stopwatch s = new Stopwatch();
            for (int i = 0; i < runs; i++)
                count += ErdosRenyi.count(n);
            double ratio = prev > 0 ? s.elapsedTime() / prev : 0;
            prev = s.elapsedTime();
            System.out.printf("%8d %8d %6.1f\n", n, count / runs, ratio);
        }
    }
}
