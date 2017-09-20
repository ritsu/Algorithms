package net.qiguang.algorithms.C1_Fundamentals.S5_CaseStudyUnionFind;

import net.qiguang.algorithms.C1_Fundamentals.S3_BagsQueuesAndStacks.Exercise_1_3_34.RandomBag;

import java.util.Random;

/**
 * 1.5.18 Random grid generator.
 * Write a program RandomGrid that takes an int value N from the command line, generates
 * all the connections in an N-by-N grid, puts them in random order, randomly orients them
 * (so that p q and q p are equally likely to occur), and prints the result to standard output.
 * To randomly order the connections, use a RandomBag (see Exercise 1.3.34 on page 167).
 * To encapsulate p and q in a single object, use the Connection nested class shown below.
 * Package your program as two static methods: generate(), which takes N as argument and returns
 * an array of connections, and main(), which takes N from the command line, calls generate(),
 * and iterates through the returned array to print the connections.
 */
public class Exercise_1_5_18 {
    public static class RandomGrid {
        private class Connection {
            int p;
            int q;
            public Connection(int p, int q) {
                this.p = p;
                this.q = q;
            }
        }

        // Generate connections between neighbors in an n x n grid.
        // Indices start at 1.
        private Connection[] generate(int n) {
            RandomBag<Connection> b = new RandomBag();
            Random r = new Random();
            int m = n * n;
            for (int i = 1; i <= m; i++) {
                // Try to connect to right neighbor
                if (i % n > 0) {
                    if (r.nextBoolean()) b.add(new Connection(i, i + 1));
                    else                 b.add(new Connection(i + 1, i));
                }
                // Try to connect to bottom neighbor
                if (m - i >= n) {
                    if (r.nextBoolean()) b.add(new Connection(i, i+n));
                    else                 b.add(new Connection(i+n, i));
                }
            }
            Connection[] cons = new Connection[b.size()];
            int i = 0;
            for (Connection con : b) {
                cons[i++] = con;
            }
            return cons;
        }
        public static void main(int n) {
            RandomGrid grid = new RandomGrid();
            Connection[] cons = grid.generate(n);
            for (Connection con : cons)
                System.out.printf("%d %d\n", con.p, con.q);
        }
    }
    public static void main(String[] args) {
        RandomGrid grid = new RandomGrid();
        grid.main(10);
    }
}
