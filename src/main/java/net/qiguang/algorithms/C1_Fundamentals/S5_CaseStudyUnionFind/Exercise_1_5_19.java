package net.qiguang.algorithms.C1_Fundamentals.S5_CaseStudyUnionFind;

import edu.princeton.cs.algs4.StdDraw;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

/**
 * 1.5.19 Animation.
 * Write a RandomGrid client (see Exercise 1.5.18) that uses UnionFind as in our development client
 * to check connectivity and uses StdDraw to draw the connections as they are processed.
 */
public class Exercise_1_5_19 {
    private static void animateLine(double px, double py, double qx, double qy) {
        StdDraw.setPenRadius(0.001);
        int frames = 60;
        double dx = (qx - px) / frames;
        double dy = (qy - py) / frames;
        StdDraw.point(px, py);
        for (int i = 0; i < frames; i++) {
            px += dx;
            py += dy;
            StdDraw.point(px, py);
        }
        int t = 1000 / 60;
        StdDraw.pause(t);
    }
    public static void main(String[] args) {
        // Grid size
        int n = 10;

        // Draw grid
        StdDraw.setCanvasSize(720, 720);
        StdDraw.setXscale(0.0, 1.0*n + 1);
        StdDraw.setYscale(0.0, 1.0*n + 1);
        StdDraw.setPenRadius(0.01);
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                StdDraw.point(i, j);
            }
        }

        // Capture output stream
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(baos);
        PrintStream old = System.out;
        System.setOut(ps);

        // Generate connections
        Exercise_1_5_18.RandomGrid.main(n);
        System.out.flush();
        System.setOut(old);

        // Draw connections
        String[] pairs = baos.toString().split("\\n+");
        for (String pair : pairs) {
            String[] nums = pair.split("\\s+");
            int p = Integer.parseInt(nums[0]);
            int q = Integer.parseInt(nums[1]);
            int px = ((p - 1) % n) + 1;
            int py = ((p - 1) / n) + 1;
            int qx = ((q - 1) % n) + 1;
            int qy = ((q - 1) / n) + 1;
            animateLine(px, py, qx, qy);
        }
    }
}
