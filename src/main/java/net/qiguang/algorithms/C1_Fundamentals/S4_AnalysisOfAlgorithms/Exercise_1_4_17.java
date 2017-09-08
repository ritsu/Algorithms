package net.qiguang.algorithms.C1_Fundamentals.S4_AnalysisOfAlgorithms;

import java.util.Arrays;
import java.util.Random;

/**
 * 1.4.17 Farthest pair (in one dimension).
 * Write a program that, given an array a[] of N double values, finds a farthest pair: two values whose
 * difference is no smaller than the the difference of any other pair (in absolute value). The running time
 * of your program should be linear in the worst case.
 */
public class Exercise_1_4_17 {
    public static Double[] farthestPair(Double[] d) {
        int max = d[0] > d[1] ? 0 : 1;
        int min = d[0] > d[1] ? 1 : 0;
        for (int i = 2; i < d.length; i++) {         // ~2N
            if      (d[i] > max) max = i;
            else if (d[i] < min) min = i;
        }
        return new Double[] {d[min], d[max]};
    }

    public static void main(String[] args) {
        Random r = new Random();
        Double[] d = new Double[100];
        for (int i = 0; i < 100; i++) {
            d[i] = r.nextDouble();
        }
        System.out.println(Arrays.toString(farthestPair(d)));
    }
}
