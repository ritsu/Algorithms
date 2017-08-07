package net.qiguang.algorithms.C1_Fundamentals.S1_BasicProgrammingModel;

import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdRandom;

/**
 * 1.1.31 Random connections.
 * Write a program that takes as command-line arguments an integer N and a double value p
 * (between 0 and 1), plots N equally spaced dots of size .05 on the circumference of a circle,
 * and then, with probability p for each pair of points, draws a gray line connecting them.
 */
public class Exercise_1_1_31 {
    // Get array of n points on circle
    public static double[][] getPoints(int n) {
        double[][] a = new double[n][2];
        double delta = 2 * Math.PI / n;
        for (int i = 0; i < n; i++) {
            double x = Math.sin(i * delta);
            double y = Math.cos(i * delta);
            a[i][0] = x;
            a[i][1] = y;
        }
        return a;
    }

    // Draw points
    public static void drawPoints(double[][] a) {
        for (int i = 0; i < a.length; i++) {
            StdDraw.point(a[i][0], a[i][1]);
        }
    }

    // Draw lines between points with probability p
    public static void drawLines(double[][]a, double p) {
        for (int i = 0; i < a.length; i++) {
            for (int j = i + 1; j < a.length; j++) {
                if (StdRandom.uniform() < p) {
                    StdDraw.line(a[i][0], a[i][1], a[j][0], a[j][1]);
                }
            }
        }
    }

    public static void main(String[] args) {
        int n = args.length >= 1 ? Integer.parseInt(args[0]) : 10;
        double p = args.length >= 2 ? Double.parseDouble(args[1]) : 0.5;

        // Draw points
        StdDraw.setScale(-1.2, 1.2);
        StdDraw.setPenRadius(0.05);
        StdDraw.setPenColor(StdDraw.BLUE);
        double[][] points = getPoints(n);
        drawPoints(points);

        // Draw lines
        StdDraw.setPenColor(StdDraw.GRAY);
        StdDraw.setPenRadius(0.01);
        drawLines(points, p);
    }
}
