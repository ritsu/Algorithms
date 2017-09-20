package net.qiguang.algorithms.C1_Fundamentals.S5_CaseStudyUnionFind;

import java.util.Arrays;

/**
 * 1.5.2
 * Do Exercise 1.5.1, but use quick-union (page 224). In addition, draw the forest of trees
 * represented by the id[] array after each input pair is processed
 *
 * TODO: Draw
 */
public class Exercise_1_5_02 {
    public static class QuickUnionUF {
        private int[] parent;  // parent[i] = parent of i
        private int count;     // number of components
        private int access;

        public QuickUnionUF(int n) {
            parent = new int[n];
            count = n;
            for (int i = 0; i < n; i++) {
                parent[i] = i;
            }
        }

        public int count() {
            return count;
        }

        public int find(int p) {
            validate(p);
            access++;   // Initial while test
            while (p != parent[p]) {
                access++;   // update p
                p = parent[p];
                access++;   // while test
            }
            return p;
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
            access = 0;
            int rootP = find(p);
            int rootQ = find(q);
            if (rootP == rootQ) {
                System.out.printf("%s %d\n", Arrays.toString(parent), access);
                return;
            }
            access++;
            parent[rootP] = rootQ;
            count--;
            System.out.printf("%s %d\n", Arrays.toString(parent), access);
        }
    }

    public static void main(String[] args) {
        String[] input = {"9-0","3-4","5-8","7-2","2-1","5-7","0-3","4-2"};
        QuickUnionUF u = new QuickUnionUF(10);
        for (String s : input) {
            System.out.printf(s + " ");
            String[] a = s.split("-");
            int p = Integer.parseInt(a[0]);
            int q = Integer.parseInt(a[1]);
            u.union(p, q);
        }
        System.out.println();

        String[] worst = {"0-1", "0-2","0-3","0-4","0-5","0-6","0-7","0-8","0-9"};
        u = new QuickUnionUF(10);
        for (String s : worst) {
            System.out.printf(s + " ");
            String[] a = s.split("-");
            int p = Integer.parseInt(a[0]);
            int q = Integer.parseInt(a[1]);
            u.union(p, q);
        }

    }
}
