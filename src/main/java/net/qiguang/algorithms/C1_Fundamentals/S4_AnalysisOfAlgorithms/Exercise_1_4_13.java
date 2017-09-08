package net.qiguang.algorithms.C1_Fundamentals.S4_AnalysisOfAlgorithms;

import edu.princeton.cs.algs4.Date;

import java.util.Comparator;

/**
 * 1.4.13 Using the assumptions developed in the text, give the amount of memory needed to represent
 * an object of each of the following types:
 *
 * a. Accumulator
 *        Object      16
 *        double   m   8
 *        double   s   8
 *        int      N   4
 *        padding      4
 *        Total       40
 *
 * b. Transaction
 *        Object          16
 *        String  who      8
 *        Date    when     8
 *        double  amount   8
 *        padding          0
 *        Total           40
 *
 * c. FixedCapacityStackOfStrings with capacity C and N entries
 *        Object          16
 *        String[] a       8  (only count reference)
 *        int      N       4
 *        padding          4
 *        Total           24
 *
 * d. Point2D
 *        Object               16
 *        Comparator  X_ORDER   8
 *        Comparator  Y_ORDER   8
 *        Comparator  R_ORDER   8
 *        double      x         8
 *        double      y         8
 *        padding               0
 *        Total                56
 *
 * e. Interval1D
 *        Object                          16
 *        Comparator  MIN_ENDPOINT_ORDER   8
 *        Comparator  MAX_ENDPOINT_ORDER   8
 *        Comparator  LENGTH_ORDER         8
 *        double      min                  8
 *        double      max                  8
 *        padding                          0
 *        Total                           56

 * f. Interval2D
 *        Object         16
 *        Interval1D  x   8
 *        Interval1D  y   8
 *        padding         0
 *        Total          32
 *
 * g. Double
 *        Object   16
 *        double    8 x 6
 *        int       4 x 4
 *        padding   0
 *        Total    80
 */
public class Exercise_1_4_13 {
    // For reference only
    public class Accumulator {
        private double m;
        private double s;
        private int N;
    }
    public class Transaction {
        private String who;
        private Date when;
        private double amount;
    }
    public class FixedCapacityStackOfStrings {
        private String[] a;  // holds the items
        private int N;       // number of items in stack
    }
    public class Point2D {
        public Comparator<Point2D> X_ORDER;
        public Comparator<Point2D> Y_ORDER;
        public Comparator<Point2D> R_ORDER;
        private double x;    // x coordinate
        private double y;    // y coordinate
    }
    public class Double {
        public static final double POSITIVE_INFINITY = 1.0 / 0.0;
        public static final double NEGATIVE_INFINITY = -1.0 / 0.0;
        public static final double NaN = 0.0d / 0.0;
        public static final double MAX_VALUE = 0x1.fffffffffffffP+1023; // 1.7976931348623157e+308
        public static final double MIN_NORMAL = 0x1.0p-1022; // 2.2250738585072014E-308
        public static final double MIN_VALUE = 0x0.0000000000001P-1022; // 4.9e-324
        public static final int MAX_EXPONENT = 1023;
        public static final int MIN_EXPONENT = -1022;
        public static final int SIZE = 64;
        public static final int BYTES = SIZE / Byte.SIZE;
    }
    public class Interval1D {
        public Comparator<Interval1D> MIN_ENDPOINT_ORDER;
        public Comparator<Interval1D> MAX_ENDPOINT_ORDER;
        public Comparator<Interval1D> LENGTH_ORDER;
        private double min;
        private double max;
    }
    public class Interval2D {
        private Interval1D x;
        private Interval1D y;
    }
}
