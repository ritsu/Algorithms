package net.qiguang.algorithms.C1_Fundamentals.S5_CaseStudyUnionFind;

import edu.princeton.cs.algs4.StdDraw;

import java.util.Random;

/**
 * 1.5.16 Amortized costs plots.
 * Instrument your implementations from Exercise 1.5.7 to make amortized costs plots like those in the text.
 */
public class Exercise_1_5_16 {
    public static class QuickUnionUF {
        private int[] id;
        private int count;
        private int access;

        public QuickUnionUF(int n) {
            id = new int[n];
            for (int i = 0; i < n; i++) {
                id[i] = i;
            }
            count = n;
        }
        public int find(int p) {
            access++;         // initial while condition
            while (p != id[p]) {
                access++;     // assignment
                p = id[p];
                access++;     // loop while condition
            }
            return p;
        }
        public boolean connected(int p, int q) {
            return find(p) == find(q);
        }
        public void union(int p, int q) {
            access = 0;
            p = find(p);
            q = find(q);
            if (p == q) return;
            access++;     // assignment
            id[q] = p;
            count--;
        }
    }
    public static class QuickFindUF {
        private int[] id;
        private int count;
        private int access;

        public QuickFindUF(int n) {
            id = new int[n];
            for (int i = 0; i < n; i++) {
                id[i] = i;
            }
            count = n;
        }

        public int find(int p) {
            return id[p];
        }

        public boolean connected(int p, int q) {
            return id[p] == id[q];
        }

        public void union(int p, int q) {
            access = 0;
            access++;
            int idp = id[p];
            access++;
            int idq = id[q];
            if (idp == idq) return;
            for (int i = 0; i < id.length; i++) {
                access++;
                if (id[i] == idp) {
                    access++;
                    id[i] = idq;
                }
            }
            count--;
        }
    }
    public static void main(String[] args) {
        int n = 1000;
        //StdDraw.setCanvasSize();
        StdDraw.setXscale(0, n);
        StdDraw.setYscale(0, n/2);

        Random r = new Random();
        QuickUnionUF qu = new QuickUnionUF(n);
        QuickFindUF qf = new QuickFindUF(n);
        for (int i = 0; i < n; i++) {
            int a = r.nextInt(n);
            int b = r.nextInt(n);
            qu.union(a, b);
            qf.union(a, b);
            StdDraw.setPenColor(0, 0, 200);
            StdDraw.point(i, qu.access);
            StdDraw.setPenColor(200, 0, 0);
            StdDraw.point(i, qf.access);
        }


    }
}
