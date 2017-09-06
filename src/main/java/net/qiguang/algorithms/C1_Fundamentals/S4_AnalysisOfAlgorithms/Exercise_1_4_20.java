package net.qiguang.algorithms.C1_Fundamentals.S4_AnalysisOfAlgorithms;

import java.util.Arrays;
import java.util.Random;

/**
 * 1.4.20 Bitonic search.
 * An array is bitonic if it is comprised of an increasing sequence of integers followed immediately by a
 * decreasing sequence of integers. Write a program that, given a bitonic array of N distinct int values,
 * determines whether a given integer is in the array. Your program should use ~3*lg(N) compares in the worst case.
 */
public class Exercise_1_4_20 {
    public static int indexOfBitonic(int[] a, int key) {
        // Find mid
        int lo = 0;
        int hi = a.length - 1;
        int midc = (lo + hi) / 2;
        while (hi >= lo) {
            if (midc == 0) break;
            if (midc == a.length - 1) break;
            if (a[midc] > a[midc - 1] && a[midc] > a[midc + 1]) break;
            if (a[midc] > a[midc + 1]) hi = midc - 1;
            else                       lo = midc + 1;
            midc = (lo + hi) / 2;
        }
        // Search left
        lo = 0;
        hi = midc;
        int mid = (lo + hi) / 2;
        while (hi > lo) {
            if (a[mid] == key) return mid;
            if (a[mid] > key) hi = mid - 1;
            else              lo = mid + 1;
            mid = (lo + hi) / 2;
        }
        // Search right
        lo = midc;
        hi = a.length - 1;
        mid = (lo + hi) / 2;
        while (hi > lo) {
            if (a[mid] == key) return mid;
            if (a[mid] > key) hi = mid - 1;
            else              lo = mid + 1;
            mid = (lo + hi) / 2;
        }
        return -1;
    }
    public static void main(String[] args) {
        int n = 100;
        int[] a = new int[n];
        double mid = Math.ceil(Math.random() * n);
        int value = 0;
        for (int i = 0; i < n; i++) {
            if (i <= mid) value += Math.ceil(Math.random() * 10);
            else          value -= Math.ceil(Math.random() * 10);
            a[i] = value;
        }
        // Large key
        System.out.printf("%5d: %d\n", 10 * n, indexOfBitonic(a, 10 * n));
        // Small key
        System.out.printf("%5d: %d\n", -10 * n, indexOfBitonic(a, -10 * n));
        // Random keys
        System.out.println(Arrays.toString(a));
        Random r = new Random();
        for (int i = 0; i < 10; i++) {
            int key = r.nextInt(n);
            System.out.printf("%3d: %3d\n", key, indexOfBitonic(a, key));
        }
    }
}
