package net.qiguang.algorithms.C1_Fundamentals.S4_AnalysisOfAlgorithms;

import java.util.Arrays;
import java.util.Random;

/**
 * 1.4.16 Closest pair (in one dimension).
 * Write a program that, given an array a[] of N double values, finds a closest pair: two values whose
 * difference is no greater than the the difference of any other pair (in absolute value). The running time
 * of your program should be linearithmic in the worst case.
 */
public class Exercise_1_4_16 {
    public static Double[] closestPair(Double[] d) {
        Arrays.sort(d);                              // ~N*log(N)
        int a = 0;
        int b = 1;
        double closest = Math.abs(d[a] - d[b]);
        for (int i = 2; i < d.length; i++) {         // ~2*N
            double dist = Math.abs(d[i] - d[i-1]);
            if (dist < closest) {
                a = i;
                b = i - 1;
                closest = dist;
            }
        }
        return new Double[] {d[a], d[b]};
    }

    public static void main(String[] args) {
        Random r = new Random();
        Double[] d = new Double[100];
        for (int i = 0; i < 100; i++) {
            d[i] = r.nextDouble();
        }
        System.out.println(Arrays.toString(closestPair(d)));
    }
}
