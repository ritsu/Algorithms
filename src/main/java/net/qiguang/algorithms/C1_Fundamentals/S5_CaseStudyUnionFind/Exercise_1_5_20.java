package net.qiguang.algorithms.C1_Fundamentals.S5_CaseStudyUnionFind;

import java.util.Random;

/**
 * 1.5.20 Dynamic growth.
 * Using linked lists or a resizing array, develop a weighted quick-union implementation that
 * removes the restriction on needing the number of objects ahead of time. Add a method newSite()
 * to the API, which returns an int identifier
 */
public class Exercise_1_5_20 {
    public static class WeightedQuickUnionUF {
        private int[] parent;   // parent[i] = parent of i
        private int[] size;     // size[i] = number of sites in subtree rooted at i
        private int count;      // number of components
        int N;                  // number of items

        public WeightedQuickUnionUF() {
            N = 0;
            count = 0;
            parent = new int[4];
            size = new int[4];
        }

        private void resize(int n) {
            int[] parentCopy = new int[n];
            int[] sizeCopy = new int[n];
            for (int i = 0; i < count; i++) {
                parentCopy[i] = parent[i];
                sizeCopy[i] = size[i];
            }
            parent = parentCopy;
            size = sizeCopy;
        }

        public int newSite() {
            N++;
            if (N == parent.length) resize(N * 2);
            parent[N - 1] = N - 1;
            size[N - 1] = 1;
            return N - 1;
        }

        public int count() {
            return count;
        }

        public int find(int p) {
            // Now with path compression
            validate(p);
            int root = p;
            while (root  != parent[root]) {
                root = parent[root];
            }
            while (p != root) {
                int next = parent[p];
                parent[p] = root;
                p = next;
            }
            return p;
        }

        // validate that p is a valid index
        private void validate(int p) {
            if (p < 0 || p >= N) {
                throw new IndexOutOfBoundsException("index " + p + " is not between 0 and " + (N - 1));
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
            if (size[rootP] < size[rootQ]) {
                parent[rootP] = rootQ;
                size[rootQ] += size[rootP];
            } else {
                parent[rootQ] = rootP;
                size[rootP] += size[rootQ];
            }
            count--;
        }
    }
    public static void main(String[] args) {
        WeightedQuickUnionUF uf = new WeightedQuickUnionUF();
        Random r = new Random();
        for (int i = 0; i < 20; i++) {
            System.out.printf("\n%2d", uf.newSite());
            int p = r.nextInt(i+1);
            int q = r.nextInt(i+1);
            if (uf.connected(p, q)) continue;
            uf.union(p, q);
            System.out.printf("%5d-%d", p, q);
            uf.union(r.nextInt(i+1), r.nextInt(i+1));
        }

    }
}
