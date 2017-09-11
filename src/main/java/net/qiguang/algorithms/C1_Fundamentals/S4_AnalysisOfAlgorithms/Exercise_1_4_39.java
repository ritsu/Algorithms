package net.qiguang.algorithms.C1_Fundamentals.S4_AnalysisOfAlgorithms;

import edu.princeton.cs.algs4.DoublingTest;

/**
 * 1.4.39 Improved accuracy for doubling test.
 * Modify DoublingRatio to take a second command-line argument that specifies the number of calls to make to
 * timeTrial() for each value of N. Run your program for 10, 100, and 1,000 trials and comment on the precision
 * of the results.
 */
public class Exercise_1_4_39 {
    public static void main(String[] args) {
        int n = 1000;
        System.out.printf("%7s %8s %8s %8s %8s\n", "size", "1x", "10x", "100x", "1000x");
        for (int i = 100; true; i += i) {
            double time = 0;
            System.out.printf("%7d", i);
            for (int j = 0; j < n; j++) {
                time += DoublingTest.timeTrial(i);
                // Print single run for comparison
                if      (j == 0)    System.out.printf("%9.3f", time);
                else if (j == 10)   System.out.printf("%9.3f", time / 10);
                else if (j == 100)  System.out.printf("%9.3f", time / 100);
            }
            System.out.printf("%9.3f\n", time / n);
        }
    }
}

