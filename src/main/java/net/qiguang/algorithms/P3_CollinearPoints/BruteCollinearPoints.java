package net.qiguang.algorithms.P3_CollinearPoints;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import java.util.Arrays;
     
public class BruteCollinearPoints
{
    private int n;
    private Node first;

    private class Node {
        private LineSegment segment;
        private Node next;
    }

    // finds all line segments containing 4 points
    public BruteCollinearPoints(Point[] points) {
        n = 0;
        first = null;

        // check for null input
        if (points == null)
            throw new java.lang.IllegalArgumentException (
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

        for (int a = 0; a < pointsClone.length; a++) {
            for (int b = a+1; b < pointsClone.length; b++) {
                double s = pointsClone[a].slopeTo(pointsClone[b]);
                for (int c = b+1; c < pointsClone.length; c++) {
                    if (doubleEquals(pointsClone[b].slopeTo(pointsClone[c]), s)) {
                        for (int d = c+1; d < pointsClone.length; d++) {
                            if (doubleEquals(pointsClone[c].slopeTo(pointsClone[d]), s)) {
                                addNode(pointsClone[a], pointsClone[d]);
                            }
                        }
                    }
                }
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
        In in = new In(cl.getResource("algs4-data/P3_CollinearPoints/input48.txt").getFile());
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
        BruteCollinearPoints collinear = new BruteCollinearPoints(points);        
        for (LineSegment segment : collinear.segments()) {
            segment.draw();
            StdOut.println(segment);
        }
    }
}

