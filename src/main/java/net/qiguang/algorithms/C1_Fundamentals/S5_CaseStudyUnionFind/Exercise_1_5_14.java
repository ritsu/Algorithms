package net.qiguang.algorithms.C1_Fundamentals.S5_CaseStudyUnionFind;

import java.util.Random;

/**
 * 1.5.14 Weighted quick-union by height.
 * Develop a UF implementation that uses the same basic strategy as weighted quick-union but keeps track of
 * tree height and always links the shorter tree to the taller one. Prove a logarithmic upper bound on the
 * height of the trees for N sites with your algorithm.
 *
 * Answer: Max height of N sites is max height of N/2 sites + 1.
 *         Max height of N/2 sites is max height of N/4 sites + 1.
 *         Repeat until N = 1, with max height of 1.
 *         Max height of original N sites is 1 + 1 + ... + 1 a total of lg(N) times
 */
public class Exercise_1_5_14 {
    public static class WeightedQuickUnionUF {
        private int[] parent;   // parent[i] = parent of i
        private int[] height;     // size[i] = number of sites in subtree rooted at i
        private int count;      // number of components

        public WeightedQuickUnionUF(int n) {
            count = n;
            parent = new int[n];
            height = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
                height[i] = 1;
            }
        }

        public int count() {
            return count;
        }

        public int find(int p) {
            validate(p);
            while (p != parent[p]) {
                p = parent[p];
            }
            return p;
        }
        public int getHeight(int p) {
            int length = 1;
            while (p != parent[p]) {
                p = parent[p];
                length++;
            }
            return length;
        }

        // validate that p is a valid index
        private void validate(int p) {
            int n = parent.length;
            if (p < 0 || p >= n) {
                throw new IndexOutOfBoundsException("index " + p + " is not between 0 and " + (n - 1));
            }
        }

        public boolean connected(int p, int q) {
            return find(p) == find(q);
        }

        public void union(int p, int q) {
            int rootP = find(p);
            int rootQ = find(q);
            if (rootP == rootQ) {
                return;
            }

            // make smaller root point to larger one
            if (height[rootP] < height[rootQ]) {
                parent[rootP] = rootQ;
            } else if (height[rootP] > height[rootQ]) {
                parent[rootQ] = rootP;
            } else {
                // if equal, arbitrarily make p the parent
                parent[rootQ] = rootP;
                height[rootP]++;
            }
            count--;
        }
    }

    public static void main(String[] args) {
        Random r = new Random();
        for (int n = 100; n < 10000000; n += n) {
            WeightedQuickUnionUF wqu = new WeightedQuickUnionUF(n);
            for (int i = 0; i < n; i++) {
                wqu.union(r.nextInt(n), r.nextInt(n));
            }
            int max = 1;
            for (int i = 0; i < n; i++) {
                int len = wqu.getHeight(i);
                if (len > max)
                    max = len;
            }
            System.out.printf("%8d: max = %3d, lg(n) = %5.1f\n", n, max, Math.log(n)/Math.log(2));
        }
    }
}
