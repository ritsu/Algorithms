package net.qiguang.algorithms.C1_Fundamentals.S2_DataAbstraction;

import edu.princeton.cs.algs4.*;
import java.util.Arrays;

/**
 * 1.2.1
 * Write a Point2D client that takes an integer value N from the command line, generates N random points
 * in the unit square, and computes the distance separating the closest pair of points.
 */

public class Exercise_1_2_01 {
    private static class Pair {
        public Point2D a;
        public Point2D b;
        public double dist;
        public Pair(Point2D a, Point2D b, double dist) {
            this.a = a;
            this.b = b;
            this.dist = dist;
        }
    }

    // Find closest pair
    private static Pair closestPair(Point2D[] p, int lo, int hi) {
        if (hi - lo <= 0) {
            return new Pair(p[hi], p[lo], 1);
        }
        else if (hi - lo == 1) {
            return new Pair(p[hi], p[lo], p[hi].distanceTo(p[lo]));
        }
        else {
            int mid = lo + (hi - lo) / 2;
            Pair delta1 = closestPair(p, lo, mid);
            Pair delta2 = closestPair(p, mid + 1, hi);
            Pair delta = delta1.dist < delta2.dist ? delta1 : delta2;

            // Create arrays of points within delta of mid
            Point2D[] bot = new Point2D[mid - lo + 1];
            Point2D[] top = new Point2D[hi - mid];
            for (int i = lo; i <= mid; i++) {
                bot[i - lo] = p[i];
            }
            for (int i = mid + 1; i <= hi; i++) {
                top[i - mid - 1] = p[i];
            }
            Arrays.sort(bot, Point2D.X_ORDER);
            Arrays.sort(top, Point2D.X_ORDER);

            // Find closest pair between top and bottom
            int start = 0;
            for (int i = 0; i < top.length; i++) {
                int count = 0;
                for (int j = start; j < bot.length; j++) {
                    if (bot[j].x() - top[i].x() > delta.dist) break;
                    if (top[i].x() - bot[j].x() > delta.dist) continue;
                    if (count == 0) start = j;
                    if (++count > 6) break;
                    if (top[i].distanceTo(bot[j]) < delta.dist)
                        delta = new Pair(top[i], bot[j], top[i].distanceTo(bot[j]));
                }
            }

            return delta;
        }
    }

    public static void main(String[] args) {
        // Generate array of points
        int n = args.length == 1 ? Integer.parseInt(args[0]) : 20000;
        Point2D[] p = new Point2D[n];
        for (int i = 0; i < n; i++) {
            p[i] = new Point2D(StdRandom.uniform(), StdRandom.uniform());
        }

        // Brute force method
        StdOut.println("Brute force method:");
        Stopwatch s = new Stopwatch();
        double min_dist = p[0].distanceTo(p[1]);
        Point2D min_a = p[0];
        Point2D min_b = p[1];
        for (int i = 0; i < n ; i++) {
            for (int j = i + 1; j < n; j++) {
                double dist = p[i].distanceTo(p[j]);
                if (dist < min_dist) {
                    min_dist = dist;
                    min_a = p[i];
                    min_b = p[j];
                }
            }
        }
        StdOut.printf("A = %s\nB = %s\nDist = %f\nTime = %f\n\n",
                min_a, min_b, min_a.distanceTo(min_b), s.elapsedTime());

        // Divide and conquer method
        StdOut.println("Divide and conquer method:");
        s = new Stopwatch();
        Arrays.sort(p);
        Pair d = closestPair(p, 0, p.length - 1);
        StdOut.printf("A = %s\nB = %s\nDist = %f\nTime = %f\n",
                d.a, d.b, d.a.distanceTo(d.b), s.elapsedTime());

    }
}
