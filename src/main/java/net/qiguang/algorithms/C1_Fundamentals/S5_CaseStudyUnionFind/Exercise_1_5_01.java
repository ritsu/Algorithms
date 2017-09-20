package net.qiguang.algorithms.C1_Fundamentals.S5_CaseStudyUnionFind;

import java.util.Arrays;

/**
 * 1.5.1
 * Show the contents of the id[] array and the number of times the array is accessed for each input pair
 * when you use quick-ﬁnd for the sequence 9-0 3-4 5-8 7-2 2-1 5-7 0-3 4-2
 */
public class Exercise_1_5_01 {
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
                System.out.printf("%s %3d\n", Arrays.toString(id), access);
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
            System.out.printf("%s %3d\n", Arrays.toString(id), access);
        }
    }

    public static void main(String[] args) {
        String[] input = {"9-0","3-4","5-8","7-2","2-1","5-7","0-3","4-2"};
        QuickFindUF u = new QuickFindUF(10);
        for (String s : input) {
            System.out.printf(s + " ");
            String[] a = s.split("-");
            int p = Integer.parseInt(a[0]);
            int q = Integer.parseInt(a[1]);
            u.union(p, q);
        }
    }
}
