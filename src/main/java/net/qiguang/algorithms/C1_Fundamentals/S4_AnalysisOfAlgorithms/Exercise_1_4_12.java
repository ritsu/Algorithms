package net.qiguang.algorithms.C1_Fundamentals.S4_AnalysisOfAlgorithms;

/**
 * 1.4.12
 * Write a program that, given two sorted arrays of N int values, prints all elements that appear in both arrays,
 * in sorted order. The running time of your program should be proportional to N in the worst case.
 */
public class Exercise_1_4_12 {
    public static String inBoth(int[] a, int[] b) {
        int ia = 0;
        int ib = 0;
        int lastmatch = -1;
        StringBuilder s = new StringBuilder();
        while (ia < a.length && ib < b.length) {
            if      (a[ia] < b[ib]) ia++;
            else if (a[ia] > b[ib]) ib++;
            else {
                if (lastmatch < 0 || a[ia] != a[lastmatch]) {
                    s.append(a[ia]).append(" ");
                    lastmatch = ia;
                }
                ia++;
                ib++;
            }
        }
        return s.toString();
    }
    public static void main(String[] args) {
        int[] a = {0, 1, 2, 3, 4, 5};
        int[] b = {4, 5, 6, 7, 8, 9};
        System.out.println(inBoth(a, b));
        System.out.println(inBoth(b, a));

        int[] c = {0, 1, 2, 2, 3, 4};
        int[] d = {0, 2, 2, 4, 8, 9};
        System.out.println(inBoth(c, d));
        System.out.println(inBoth(d, c));

        int[] e = {0, 1, 2, 3, 4, 5};
        int[] f = {5, 6, 7, 8, 9, 9};
        System.out.println(inBoth(e, f));
        System.out.println(inBoth(f, e));

        int[] g = {0, 1, 2, 3, 4, 5};
        int[] h = {6, 7, 8, 9, 9, 10};
        System.out.println(inBoth(g, h));
        System.out.println(inBoth(h, g));
    }
}
