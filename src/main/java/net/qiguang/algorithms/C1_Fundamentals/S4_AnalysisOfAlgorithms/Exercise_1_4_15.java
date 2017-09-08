package net.qiguang.algorithms.C1_Fundamentals.S4_AnalysisOfAlgorithms;

import edu.princeton.cs.algs4.BinarySearch;
import edu.princeton.cs.algs4.Stopwatch;

import java.util.Arrays;
import java.util.HashMap;

/**
 * 1.4.15  Faster 3-sum.
 * As a warmup, develop an implementation TwoSumFaster that uses a linear algorithm to count the pairs that
 * sum to zero after the array is sorted (instead of the binary-search-based linearithmic algorithm).
 * Then apply a similar idea to develop a quadratic algorithm for the 3-sum problem.
 */
public class Exercise_1_4_15 {
    public static int ThreeSumHash(int[] a) {
        Arrays.sort(a);
        int N = a.length;

        HashMap<Integer, Integer> h = new HashMap<Integer, Integer>();
        for (int i = 0; i < a.length; i++) {
            h.put(a[i], i);
        }

        int cnt = 0;
        for (int i = 0; i < N; i++) {
            for (int j = i + 1; j < N; j++) {
                if (h.get(-a[i]-a[j]) != null && h.get(-a[i]-a[j]) > j) {
                    //System.out.printf("%3d %3d %3d\n", a[i], a[j], (-a[i]-a[j]));
                    cnt++;
                }
            }
        }
        return cnt;
    }

    public static int ThreeSum(int[] a) {
        Arrays.sort(a);
        int N = a.length;
        int cnt = 0;
        for (int i = 0; i < N; i++)
            for (int j = i+1; j < N; j++)
                if (BinarySearch.rank(-a[i]-a[j], a) > j) {
                    //System.out.printf("%3d %3d %3d\n", a[i], a[j], (-a[i]-a[j]));
                    cnt++;
                }
        return cnt;
    }

    public static void main(String[] args) {
        Stopwatch s;
        for (int n = 10; n < 100000 ; n *= 2) {
            int[] a = new int[n];
            for (int i = 0; i < a.length; i++) {
                a[i] = i - a.length / 10;
            }
            s = new Stopwatch();
            System.out.printf("%9d %9.4f", ThreeSum(a), s.elapsedTime());
            s = new Stopwatch();
            System.out.printf("%9d %9.4f\n", ThreeSumHash(a), s.elapsedTime());
        }
    }

}
