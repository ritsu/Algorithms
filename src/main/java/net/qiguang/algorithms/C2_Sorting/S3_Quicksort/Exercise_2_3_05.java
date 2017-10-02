package net.qiguang.algorithms.C2_Sorting.S3_Quicksort;

import java.util.Arrays;
import java.util.Random;

/**
 * 2.3.5 Give a code fragment that sorts an array that is known to consist of items having
 * just two distinct keys.
 */
public class Exercise_2_3_05 {
    public static void sort(Comparable[] a) {
        Comparable pivot = a[0];
        int lt = 0;
        int gt = a.length - 1;
        int i = lt + 1;
        while (i <= gt) {
            int cmp = a[i].compareTo(pivot);
            if      (cmp > 0) exch(a, i, gt--);
            else if (cmp < 0) exch(a, i++, lt++);
            else              i++;
        }
    }
    public static void exch(Comparable[] a, int i, int j) {
        Comparable c = a[i];
        a[i] = a[j];
        a[j] = c;
    }
    public static void main(String[] args) {
        int n = 20;
        Random r = new Random();
        for (int t = 0; t < 10; t++) {
            Integer[] a = new Integer[n];
            for (int i = 0; i < n; i++) {
                a[i] = r.nextInt(2);
            }
            System.out.printf("%s ", Arrays.toString(a));
            sort(a);
            System.out.printf("%s\n", Arrays.toString(a));
        }
    }
}
