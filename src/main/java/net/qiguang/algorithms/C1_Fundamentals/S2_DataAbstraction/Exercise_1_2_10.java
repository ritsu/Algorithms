package net.qiguang.algorithms.C1_Fundamentals.S2_DataAbstraction;

import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdRandom;

/**
 * 1.2.10
 * Develop a class VisualCounter that allows both increment and decrement operations.
 * Take two arguments N and max in the constructor, where N specifies the maximum number of operations and
 * max specifies the maximum absolute value for the counter. As a side effect, create a plot showing the
 * value of the counter each time its tally changes.
 *
 */
public class Exercise_1_2_10 {

    public static class VisualCounter {
        int N;
        int max;
        int operations = 0;
        int counter = 0;

        public VisualCounter(int N, int max) {
            if (N <= 0) {
                throw new java.lang.IllegalArgumentException("Invalid N argument");
            }
            if (max <= 0) {
                throw new java.lang.IllegalArgumentException("Invalid max argument");
            }
            this.N = N;
            this.max = max;
            StdDraw.setYscale(0, max);
            StdDraw.setXscale(0, N);
        }

        public void increment() {
            if (++operations > N) {
                throw new java.lang.UnsupportedOperationException("Maximum number of operations exceeded");
            }
            if (++counter > max) {
                throw new java.lang.UnsupportedOperationException("Maximum counter value exceeded");
            }
            StdDraw.line(operations - 1, counter - 1, operations, counter);
        }
        public void decrement() {
            if (++operations > N) {
                throw new java.lang.UnsupportedOperationException("Maximum number of operations exceeded");
            }
            if (--counter < 0) {
                counter = 0;
                StdDraw.line(operations - 1, 0, operations, 0);
            }
            else {
                StdDraw.line(operations - 1, counter + 1, operations, counter);
            }
        }

    }

    /**
     * VisualCounter test
     * args[0]   int      Maximum number of operations for VisualCounter
     * args[1]   int      Maximum counter value for VisualCounter
     * args[2]   int      Number of operations to send to VisualCounter
     * args[3]   double   Weighting for operations
     *                    0.5          = equal probability of increment and decrement operations
     *                    higher value = more likely to increment
     *                    lower value  = more likely to decrement
     */
    public static void main(String[] args) {
        int n = args.length > 0 ? Integer.parseInt(args[0]) : 100;
        int max = args.length > 1 ? Integer.parseInt(args[1]) : 100;
        int ops = args.length > 2 ? Integer.parseInt(args[2]) : 100;
        double weight = args.length > 3 ? Double.parseDouble(args[3]) : 0.5;

        VisualCounter vc = new VisualCounter(n, max);
        for (int i = 0; i < ops; i++) {
            if (StdRandom.uniform() < weight) {
                vc.increment();
            }
            else {
                vc.decrement();
            }
        }

    }
}
