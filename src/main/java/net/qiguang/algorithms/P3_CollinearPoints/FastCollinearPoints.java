package net.qiguang.algorithms.P3_CollinearPoints;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import java.util.Arrays;

public class FastCollinearPoints 
{
    private int n;
    private Node first;

    private class Node {
        private LineSegment segment;
        private Node next;
    }

    // finds all line segments containing 4 or more points
    public FastCollinearPoints(Point[] points) {
        n = 0;
        first = null;

        // check for null input
        if (points == null)
            throw new IllegalArgumentException (
                "calling constructor with null");
        for (int i = 0; i < points.length; i++)
            if (points[i] == null)
                throw new IllegalArgumentException ("null point");

        // create copy of points
        Point[] pointsClone = points.clone();
        
        // check for duplicate and null points
        Arrays.sort(pointsClone);
        Point prev = null;
        for (int i = 0; i < pointsClone.length; i++) {
            if (i > 0 && pointsClone[i].compareTo(prev) == 0)
                throw new IllegalArgumentException("duplicate point");
            prev = pointsClone[i];
        }

        for (Point p : points) {
            Arrays.sort(pointsClone, p.slopeOrder());
            double slopeOld = p.slopeTo(pointsClone[pointsClone.length - 1]);
            int count = 1;
            for (int i = pointsClone.length - 2; i >= 0; i--) {
                double slopeNew = p.slopeTo(pointsClone[i]);
                if (doubleEquals(slopeNew, slopeOld)) {
                    count++;
                    continue;
                }
                // Check if segment was encountered
                if (count >= 3) {
                    // defer adding if base is not lowest point
                    boolean defer = false;
                    for (int j = 1; j <= count; j++) {
                        if (pointsClone[i+j].compareTo(p) < 0) {
                            defer = true;
                            break;
                        }
                    }
                    // add segment
                    if (!defer) {
                        Point high = pointsClone[i+1];
                        for (int j = 2; j <= count; j++) {
                            if (pointsClone[i+j].compareTo(high) > 0) {
                                high = pointsClone[i+j];
                            }
                        }
                        addNode(p, high);
                    }
                }
                count = 1;
                slopeOld = slopeNew;
            }
        }
    }

    private boolean doubleEquals(double a, double b) {
        double e = 0.0000001;
        if (a == Double.NEGATIVE_INFINITY && b == Double.NEGATIVE_INFINITY)
            return true;
        if (a == Double.POSITIVE_INFINITY && b == Double.POSITIVE_INFINITY)
            return true;
        if (a >= b && a - b < e) return true;
        if (b >= a && b - a < e) return true;
        return false;
    }

    // add node, takes two Points, stores LineSegment
    private void addNode(Point p, Point q) {
        Node oldFirst = first;
        first = new Node();
        first.segment = new LineSegment(p, q);
        if (n > 0) first.next = oldFirst;
        n++;
    }
    
    // the number of line segments
    public int numberOfSegments() {
        return n;
    }
    
    // the line segments
    public LineSegment[] segments() {
        LineSegment[] s = new LineSegment[n];
        Node current = first;
        for (int i = 0; i < n; i++) {
            s[i] = current.segment;
            current = current.next;
        }
        return s;
    }
    
    public static void main(String[] args) {
        // read the N points from a file
        ClassLoader cl = Thread.currentThread().getContextClassLoader();
        In in = new In(cl.getResource("algs4-data/P3_CollinearPoints/input100.txt").getFile());
        if (args.length > 0)
            in = new In(args[0]);
        int n = in.readInt();
        Point[] points = new Point[n];
        for (int i = 0; i < n; i++) {
            int x = in.readInt();
            int y = in.readInt();
            points[i] = new Point(x, y);
        }
        
        // draw the points
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        for (Point p : points) {
            p.draw();
        }
        
        // print and draw the line segments
        FastCollinearPoints collinear = new FastCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) {
            segment.draw();
            StdOut.println(segment);
        }
    }
}