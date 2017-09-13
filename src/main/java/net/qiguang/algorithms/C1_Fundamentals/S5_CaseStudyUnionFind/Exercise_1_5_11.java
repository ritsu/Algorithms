package net.qiguang.algorithms.C1_Fundamentals.S5_CaseStudyUnionFind;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Stopwatch;

import java.util.Arrays;
import java.util.Random;

/**
 * 1.5.11
 * Implement weighted quick-find, where you always change the id[] entries of the smaller component to the
 * identifier of the larger component. How does this change affect performance?
 *
 * Answer: No significant change, since each union() is still O(N).
 * Only difference is slightly fewer array entries changed for each union().
 */
public class Exercise_1_5_11 {
    public static class WeightedQuickFindUF {
        private int[] id;
        private int[] size;
        private int count;
        private int access;

        public WeightedQuickFindUF(int n) {
            count = n;
            id = new int[n];
            size = new int[n];
            for (int i = 0; i < n; i++) {
                id[i] = i;
                size[i] = 1;
            }
        }

        public int count() {
            return count;
        }

        public int find(int p) {
            validate(p);
            access ++;
            return id[p];
        }

        // validate that p is a valid index
        private void validate(int p) {
            int n = id.length;
            if (p < 0 || p >= n) {
                throw new IndexOutOfBoundsException("index " + p + " is not between 0 and " + (n - 1));
            }
        }

        public boolean connected(int p, int q) {
            validate(p);
            validate(q);
            access += 2;
            return id[p] == id[q];
        }

        public void union(int p, int q) {
            access = 0;
            validate(p);
            validate(q);
            access++;
            int pID = id[p];   // needed for correctness
            access++;
            int qID = id[q];   // to reduce the number of array accesses

            // p and q are already in the same component
            if (pID == qID) {
                return;
            }

            access += 2;
            if (size[p] > size[q]) {
                for (int i = 0; i < id.length; i++) {
                    access++;
                    if (id[i] == qID) {
                        access++;
                        id[i] = pID;
                    }
                }
                access += 2;
                size[p] += size[q];
            }
            else {
                for (int i = 0; i < id.length; i++) {
                    access++;
                    if (id[i] == pID) {
                        access++;
                        id[i] = qID;
                    }
                }
                access += 2;
                size[q] += size[p];
            }
            count--;
        }
    }

    public static class QuickFindUF {
        private int[] id;    // id[i] = component identifier of i
        private int count;   // number of components
        private int access;

        public QuickFindUF(int n) {
            count = n;
            id = new int[n];
            for (int i = 0; i < n; i++)
                id[i] = i;
        }

        public int count() {
            return count;
        }

        public int find(int p) {
            validate(p);
            access ++;
            return id[p];
        }

        // validate that p is a valid index
        private void validate(int p) {
            int n = id.length;
            if (p < 0 || p >= n) {
                throw new IndexOutOfBoundsException("index " + p + " is not between 0 and " + (n - 1));
            }
        }

        public boolean connected(int p, int q) {
            validate(p);
            validate(q);
            access += 2;
            return id[p] == id[q];
        }

        public void union(int p, int q) {
            access = 0;
            validate(p);
            validate(q);
            access++;
            int pID = id[p];   // needed for correctness
            access++;
            int qID = id[q];   // to reduce the number of array accesses

            // p and q are already in the same component
            if (pID == qID) {
                return;
            }

            for (int i = 0; i < id.length; i++) {
                access++;
                if (id[i] == pID) {
                    access++;
                    id[i] = qID;
                }
            }
            count--;
        }
    }

    public static void main(String[] args) {
        // Timetrials for random input
        int init = 20000;
        Random r = new Random();
        for (int n = init; true; n += n) {
            System.out.printf("n = %d:\n", n);
            QuickFindUF qf = new QuickFindUF(n);
            WeightedQuickFindUF wqf = new WeightedQuickFindUF(n);
            double tqf = 0;
            double twqf = 0;
            long aqf = 0;
            long awqf = 0;
            for (int i = 0; i < n; i++) {
                int p = r.nextInt(n);
                int q = r.nextInt(n);
                // QuickFind
                Stopwatch s = new Stopwatch();
                if (!qf.connected(p, q)) {
                    qf.union(p, q);
                    aqf += qf.access;
                }
                tqf += s.elapsedTime();
                // WeightedQuickFind
                s = new Stopwatch();
                if (!wqf.connected(p, q)) {
                    wqf.union(p, q);
                    awqf += wqf.access;
                }
                twqf += s.elapsedTime();
            }
            System.out.printf("          QuickFind: %6.1f seconds, %10d array accesses\n", tqf, aqf);
            System.out.printf("  WeightedQuickFind: %6.1f seconds, %10d array accesses\n", twqf, awqf);
        }
    }
}
