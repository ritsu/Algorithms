package net.qiguang.algorithms.C1_Fundamentals.S2_DataAbstraction;

import edu.princeton.cs.algs4.StdOut;

/**
 * 1.2.11
 * Develop an implementation SmartDate of our Date API that raises an exception if the date is not legal.
 */
public class Exercise_1_2_11 {
    public static class SmartDate {
        private final int value;

        public SmartDate(int m, int d, int y) {
            if (!isValidYear(y)) throw new java.lang.IllegalArgumentException("Invalid year");
            if (!isValidMonth(m)) throw new java.lang.IllegalArgumentException("Invalid month");
            if (!isValidDay(m, d, y)) throw new java.lang.IllegalArgumentException("Invalid day");
            value = y*512 + m*32 + d;
        }
        public boolean isValidYear(int y) {
            return y > 0;
        }
        public boolean isValidMonth(int m) {
            return (m > 0) && (m < 13);
        }
        public boolean isValidDay(int m, int d, int y) {
            if (d <= 0) return false;
            if (d > 31) return false;
            switch (m) {
                case 4:
                case 6:
                case 9:
                case 11:
                    if (d > 30) return false;
                case 2:
                    if (d > 29) return false;
                    if (!isLeapYear(y) && d > 28) return false;
            }
            return true;
        }
        public boolean isLeapYear(int y) {
            return (y % 4 == 0 && y % 100 != 0) || (y % 400 == 0);
        }
        public int month() {
            return (value / 32) % 16;
        }
        public int day() {
            return value % 32;
        }
        public int year() {
            return value / 512;
        }
        public String toString() {
            return month() + "/" + day() + "/" + year();
        }
    }

    public static void main(String[] args) {
        SmartDate sd;

        // Valid dates
        sd = new SmartDate(6, 23, 2017);
        StdOut.println(sd);
        sd = new SmartDate(2, 29, 2016);
        StdOut.println(sd);
        sd = new SmartDate(2, 28, 2015);
        StdOut.println(sd);

        // Invalid dates
        try {
            sd = new SmartDate(2, 29, 2015);
            StdOut.println(sd);
        } catch (java.lang.IllegalArgumentException e) {
            StdOut.println(e);
        }
        try {
            sd = new SmartDate(1, 32, 2017);
            StdOut.println(sd);
        } catch (java.lang.IllegalArgumentException e) {
            StdOut.println(e);
        }
        try {
            sd = new SmartDate(4, 31, 2017);
            StdOut.println(sd);
        } catch (java.lang.IllegalArgumentException e) {
            StdOut.println(e);
        }
        try {
            sd = new SmartDate(1, 0, 2017);
            StdOut.println(sd);
        } catch (java.lang.IllegalArgumentException e) {
            StdOut.println(e);
        }
        try {
            sd = new SmartDate(13, 1, 2017);
            StdOut.println(sd);
        } catch (java.lang.IllegalArgumentException e) {
            StdOut.println(e);
        }
        try {
            sd = new SmartDate(0, 1, 2017);
            StdOut.println(sd);
        } catch (java.lang.IllegalArgumentException e) {
            StdOut.println(e);
        }
        try {
            sd = new SmartDate(1, 1, 0);
            StdOut.println(sd);
        } catch (java.lang.IllegalArgumentException e) {
            StdOut.println(e);
        }

    }

}
