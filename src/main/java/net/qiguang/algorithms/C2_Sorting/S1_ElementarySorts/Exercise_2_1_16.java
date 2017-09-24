package net.qiguang.algorithms.C2_Sorting.S1_ElementarySorts;

import java.util.Arrays;
import java.util.Random;

/**
 * 2.1.16 Certification.
 * Write a check() method that calls sort() for a given array and returns true if sort() puts the array
 * in order and leaves the same set of objects in the array as were there initially, false otherwise.
 * Do not assume that sort() is restricted to move data only with exch(). You may use Arrays.sort() and
 * assume that it is correct.
 */
public class Exercise_2_1_16 {
    public static void sortGood(Comparable[] a) {
        Arrays.sort(a);
    }
    public static void sortBad(Comparable[] a) {
        Comparable c = a[0];
        for (int i = 0; i < a.length; i++) {
            a[i] = c;
        }
    }
    public static boolean checkGood(Comparable[] a) {
        Comparable[] b = new Comparable[a.length];
        for (int i = 0; i < a.length; i++) {
            b[i] = a[i];
        }
        Arrays.sort(b);
        sortGood(a);
        for (int i = 0; i < a.length; i++) {
            if (a[i] != b[i]) return false;
        }
        return true;
    }
    public static boolean checkBad(Comparable[] a) {
        Comparable[] b = new Comparable[a.length];
        for (int i = 0; i < a.length; i++) {
            b[i] = a[i];
        }
        Arrays.sort(b);
        sortBad(a);
        for (int i = 0; i < a.length; i++) {
            if (a[i] != b[i]) return false;
        }
        return true;
    }
    public static void main(String[] args) {
        Integer[] a = new Integer[100];
        Random r = new Random();
        for (int i = 0; i < a.length; i++) {
            a[i] = r.nextInt();
        }
        System.out.println(checkGood(a));
        System.out.println(checkBad(a));
    }
}
