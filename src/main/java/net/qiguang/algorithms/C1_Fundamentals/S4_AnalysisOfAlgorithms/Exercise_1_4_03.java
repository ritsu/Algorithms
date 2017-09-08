package net.qiguang.algorithms.C1_Fundamentals.S4_AnalysisOfAlgorithms;

import edu.princeton.cs.algs4.*;

import java.util.Arrays;

/**
 * 1.4.3
 * Modify DoublingTest to use StdDraw to produce plots like the standard and log-log plots in the text,
 * rescaling as necessary so that the plot always fills a substantial portion of the window.
 */
public class Exercise_1_4_03 {
    public static class ThreeSum {
        public static int countSlow(int[] a) {
            // Count triples that sum to 0.
            int N = a.length;
            int cnt = 0;
            for (int i = 0; i < N; i++)
                for (int j = i + 1; j < N; j++)
                    for (int k = j + 1; k < N; k++) {
                        if (a[i] + a[j] + a[k] == 0) {
                            cnt++;
                        }
                    }
            return cnt;
        }

        public static int countFast(int[] a) {
            // Count triples that sum to 0.
            Arrays.sort(a);
            int N = a.length;
            int cnt = 0;
            for (int i = 0; i < N; i++)
                for (int j = i + 1; j < N; j++)
                    if (BinarySearch.rank(-a[i] - a[j], a) > j)
                        cnt++;
            return cnt;
        }
    }
    public static double timeTrial(int N, boolean slow) {
        // Time ThreeSum.count() for N random 6-digit ints.
        int MAX = 1000000;
        int[] a = new int[N];
        for (int i = 0; i < N; i++)
            a[i] = StdRandom.uniform(-MAX, MAX);
        Stopwatch timer = new Stopwatch();
        if (slow) ThreeSum.countSlow(a);
        else      ThreeSum.countFast(a);
        return timer.elapsedTime();
    }
    public static void main(String[] args) {
        // We have to save values to queues and redraw each time we call setScale() because
        // setScale() does not rescale items already drawn on the canvas
        Queue<Double> y1 = new Queue<Double>();
        Queue<Double> y2 = new Queue<Double>();
        Queue<Integer> x = new Queue<Integer>();
        y1.enqueue(0.0);
        y2.enqueue(0.0);
        x.enqueue(0);
        for (int N = 100; true; N += N) {
            // Print time for problem size N.
            double t1 = timeTrial(N, false);
            double t2 = timeTrial(N, true);
            StdOut.printf("%7d %5.1f %5.1f\n", N, t1, t2);

            // Store data
            y1.enqueue(t1);
            y2.enqueue(t2);
            x.enqueue(N);

            // Update scale
            StdDraw.setXscale(0, N);
            StdDraw.setYscale(0, Math.max(1, Math.max(t1, t2)));

            // Draw lines
            StdDraw.clear();
            Queue<Double> y1copy = new Queue<Double>();
            Queue<Double> y2copy = new Queue<Double>();
            Queue<Integer> xcopy = new Queue<Integer>();
            t1 = y1.dequeue();
            t2 = y2.dequeue();
            int n = x.dequeue();
            y1copy.enqueue(t1);
            y2copy.enqueue(t2);
            xcopy.enqueue(n);
            while (!x.isEmpty()) {
                StdDraw.setPenColor(255, 64, 64);
                StdDraw.line(n, t1, x.peek(), y1.peek());
                StdDraw.setPenColor(64, 64, 255);
                StdDraw.line(n, t2, x.peek(), y2.peek());
                t1 = y1.dequeue();
                t2 = y2.dequeue();
                n = x.dequeue();
                y1copy.enqueue(t1);
                y2copy.enqueue(t2);
                xcopy.enqueue(n);
            }
            y1 = y1copy;
            y2 = y2copy;
            x = xcopy;
        }
    }
}
