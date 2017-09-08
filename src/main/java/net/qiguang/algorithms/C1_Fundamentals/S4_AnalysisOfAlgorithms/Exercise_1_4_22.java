package net.qiguang.algorithms.C1_Fundamentals.S4_AnalysisOfAlgorithms;

import edu.princeton.cs.algs4.Stopwatch;

/**
 * 1.4.22 Binary search with only addition and subtraction.
 * [Mihai Patrascu] Write a program that, given an array of N distinct int values in ascending order, determines
 * whether a given integer is in the array. You may use only additions and subtractions and a constant amount of
 * extra memory. The running time of your program should be proportional to log N in the worst case.
 */
public class Exercise_1_4_22 {
    public static int fibonacciSearch(int[] a, int key) {
        int lo = 0;
        int hi = a.length - 1;
        int fi = 0;
        while (f(fi) + f(fi) < hi) {
            fi++;
        }
        while (hi >= lo) {
            int mid = lo + f(--fi);
            if (mid > hi) mid = hi;
            if      (a[mid] > key) hi = mid - 1;
            else if (a[mid] < key) lo = mid + 1;
            else                   return mid;
        }
        return -1;
    }
    public static int f(int n) {
        int a = 0;
        int b = 1;
        for (int i = 0; i < n; i++) {
            a = a + b;
            b = a - b;
        }
        return a;
    }
    public static int binarySearch(int[] a, int key) {
        int lo = 0;
        int hi = a.length - 1;
        while (hi >= lo) {
            int mid = lo + (hi - lo) / 2;
            if      (a[mid] > key) hi = mid - 1;
            else if (a[mid] < key) lo = mid + 1;
            else                   return mid;
        }
        return -1;
    }
    public static int bruteSearch(int[] a, int key) {
        for (int i = 0; i < a.length; i++) {
            if (a[i] == key) return i;
        }
        return -1;
    }
    public static void main(String[] args) {
        int[] a = new int[200000];
        for (int i = 0; i < a.length; i++) {
            a[i] = i;
        }
        for (int i = 0; i < a.length; i += a.length - 1) {
            System.out.printf("fibonacciSearch(%d): %s\n", i, fibonacciSearch(a, i));
            System.out.printf("   binarySearch(%d): %s\n", i, binarySearch(a, i));
            System.out.printf("    bruteSearch(%d): %s\n", i, bruteSearch(a, i));
        }

        // Time
        Stopwatch s = new Stopwatch();
        for (int i = 0; i < a.length; i++) {
            fibonacciSearch(a, i);
        }
        System.out.printf("%14s: %6.3f\n", "fibonacci time", s.elapsedTime());
        s = new Stopwatch();
        for (int i = 0; i < a.length; i++) {
            binarySearch(a, i);
        }
        System.out.printf("%14s: %6.3f\n", "binary time", s.elapsedTime());
        s = new Stopwatch();
        for (int i = 0; i < a.length; i++) {
            bruteSearch(a, i);
        }
        System.out.printf("%14s: %6.3f\n", "brute time", s.elapsedTime());

    }
}
