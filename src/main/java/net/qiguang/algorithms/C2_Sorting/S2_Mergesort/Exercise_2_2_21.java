package net.qiguang.algorithms.C2_Sorting.S2_Mergesort;

import java.util.Arrays;
import java.util.Random;

/**
 * 2.2.21 Triplicates.
 * Given three lists of N names each, devise a linearithmic algorithm to determine if there is any
 * name common to all three lists, and if so, return the first such name.
 */
public class Exercise_2_2_21 {
    // Not allowed to use hashmap
    public static String triplicate(String[] a, String[] b, String[] c) {
        Arrays.sort(a);
        Arrays.sort(b);
        Arrays.sort(c);
        int ia = 0;
        int ib = 0;
        int ic = 0;
        while (ia < a.length && ib < b.length) {
            if (a[ia].compareTo(b[ib]) > 0) {
                ib++;
            } else if (a[ia].compareTo(b[ib]) < 0) {
                ia++;
            } else {
                while (ic < c.length && c[ic].compareTo(a[ia]) < 0) ic++;
                if (ic == c.length) return null;
                if (c[ic].compareTo(a[ia]) == 0) return c[ic];
                ia++;
            }
        }
        return null;
    }
    public static String triplicateBrute(String[] a, String[] b, String[] c) {
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < b.length; j++) {
                for (int k = 0; k < c.length; k++) {
                    if (a[i].compareTo(b[j]) == 0 && a[i].compareTo(c[k]) == 0) return a[i];
                }
            }
        }
        return null;
    }
    public static void main(String[] args) {
        int n = 20;
        String[] a = new String[n];
        String[] b = new String[n];
        String[] c = new String[n];
        Random r = new Random();
        for (int i = 0; i < n; i++) {
            a[i] = String.valueOf(r.nextInt(n*1));
            b[i] = String.valueOf(r.nextInt(n*1));
            c[i] = String.valueOf(r.nextInt(n*10));
        }
        System.out.println(Arrays.toString(a));
        System.out.println(Arrays.toString(b));
        System.out.println(Arrays.toString(c));
        System.out.println(triplicateBrute(a, b, c));
        System.out.println(triplicate(a, b, c));
    }
}
