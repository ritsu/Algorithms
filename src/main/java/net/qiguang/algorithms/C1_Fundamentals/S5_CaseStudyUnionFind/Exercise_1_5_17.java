package net.qiguang.algorithms.C1_Fundamentals.S5_CaseStudyUnionFind;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

import java.util.Random;

/**
 * 1.5.17 Random connections.
 * Develop a UF client ErdosRenyi that takes an integer value N from the command line, generates
 * random pairs of integers between 0 and N-1, calling connected() to determine if they are connected
 * and then union() if not (as in our development client), looping until all sites are connected, and
 * printing the number of connections generated. Package your program as a static method count() that
 * takes N as argument and returns the number of connections and a main() that takes N from the
 * command line, calls count(), and prints the returned value.
 */
public class Exercise_1_5_17 {
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
    }
    public static void main(String[] args) {
        for (int n = 100; n < 100000; n += n) {
            int count = 0;
            for (int i = 0; i < 10; i++)
                count += ErdosRenyi.count(n);
            System.out.printf("%5d %8d %8d\n", n, count / 10, (int) (n*Math.log(n)/Math.log(Math.E)/2));
        }
    }
}
