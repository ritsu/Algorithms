package net.qiguang.algorithms.C2_Sorting.S5_Applications;

import java.util.Arrays;
import java.util.Random;

/**
 * 2.5.4 Implement a method String[] dedup(String[] a) that returns the objects in a[] in sorted order,
 * with duplicates removed.
 */
public class Exercise_2_5_04 {
    private static String[] dedup(String[] a) {
        if (a.length < 2)
            return a;
        Arrays.sort(a);
        int i = 1;
        int j = 1;
        while (j < a.length - 1) {
            if (a[i].equals(a[i-1])) {
                a[i] = a[++j];
            }
            else {
                a[++i] = a[++j];
            }
        }
        String[] b = new String[i];
        for (int k = 0; k < i; k++) {
            b[k] = a[k];
        }
        return b;
    }
    public static void main(String[] args) {
        int n = 30;
        String[] a = new String[n];
        Random r = new Random();
        for (int i = 0; i < n; i++) {
            a[i] = String.valueOf(r.nextInt(10));
        }
        System.out.println(Arrays.toString(a));
        System.out.println(Arrays.toString(dedup(a)));
    }
}
