package net.qiguang.algorithms.C1_Fundamentals.S2_DataAbstraction;

import edu.princeton.cs.algs4.*;

/**
 * 1.2.3
 * Write an Interval2D client that takes command-line arguments N, min, and max and generates N random
 * 2D intervals whose width and height are uniformly distributed between min and max in the unit square.
 * Draw them on StdDraw and print the number of pairs of intervals that intersect and the number of
 * intervals that are contained in one another.
 */
public class Exercise_1_2_03 {
    public static void main(String[] args) {
        int n = args.length >= 1 ? Integer.parseInt(args[0]) : 10;
        double min = args.length >= 2 ? Double.parseDouble(args[1]) : 0;
        double max = args.length >= 3 ? Double.parseDouble(args[2]) : 1;
        StdDraw.setScale(min, max);

        // generate random 2D intervals
        Interval2D[] intervals = new Interval2D[n];
        for (int i = 0; i < n; i++) {
            double a = StdRandom.uniform(min, max);
            double b = StdRandom.uniform(min, max);
            double c = StdRandom.uniform(min, max);
            double d = StdRandom.uniform(min, max);
            Interval1D x = (a < b) ? new Interval1D(a, b) : new Interval1D(b, a);
            Interval1D y = (c < d) ? new Interval1D(c, d) : new Interval1D(d, c);
            intervals[i] = new Interval2D(x, y);
        }

        // intersecting pairs
        int count = 0;
        for (int i = 0; i < intervals.length; i++) {
            for (int j = i + 1; j < intervals.length; j++) {
                if (intervals[i].intersects(intervals[j])) count++;
            }
        }
        StdOut.println(count + " intersect");

        // containing pairs
        count = 0;
        for (int i = 0; i < intervals.length; i++) {
            intervals[i].draw();
            StdDraw.pause(600);
            for (int j = 0; j < intervals.length; j++) {
                if (i == j) continue;
                String[] s = intervals[j].toString().split("x");
                double x1 = Double.parseDouble(s[0].split(",")[0].replaceAll("[^0-9.]", ""));
                double x2 = Double.parseDouble(s[0].split(",")[1].replaceAll("[^0-9.]", ""));
                double y1 = Double.parseDouble(s[1].split(",")[0].replaceAll("[^0-9.]", ""));
                double y2 = Double.parseDouble(s[1].split(",")[1].replaceAll("[^0-9.]", ""));
                Point2D p1 = new Point2D(x1, y1);
                Point2D p2 = new Point2D(x2, y2);
                if (intervals[i].contains(p1) && intervals[i].contains(p2)) {
                    intervals[j].draw();
                    StdDraw.pause(1000);
                    count++;
                }
            }
            StdDraw.clear();
            StdDraw.pause(300);
        }
        StdOut.println(count + " contain");

        // draw
        for (int i = 0; i < intervals.length; i++) {
            intervals[i].draw();
        }
    }
}
