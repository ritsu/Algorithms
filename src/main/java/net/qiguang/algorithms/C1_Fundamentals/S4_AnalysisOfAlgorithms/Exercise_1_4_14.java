package net.qiguang.algorithms.C1_Fundamentals.S4_AnalysisOfAlgorithms;

import edu.princeton.cs.algs4.BinarySearch;

import java.util.Arrays;

/**
 * 1.4.14  4-sum.
 * Develop an algorithm for the 4-sum problem.
 */
public class Exercise_1_4_14 {
    public static int FourSum(int[] a) {
        Arrays.sort(a);
        int N = a.length;
        int cnt = 0;
        for (int i = 0; i < N; i++)
            for (int j = i+1; j < N; j++)
                for (int k = j+1; k < N; k++)
                    if (BinarySearch.rank(-a[i]-a[j]-a[k], a) > k) {
                        //System.out.printf("%3d %3d %3d %3d\n", a[i], a[j], a[k], (-a[i]-a[j]-a[k]));
                        cnt++;
                    }
        return cnt;
    }

    public static void main(String[] args) {
        int[] a = new int[100];
        for (int i = 0; i < a.length; i++) {
            a[i] = i - a.length / 10;
        }
        System.out.println(FourSum(a));
    }
}
