package net.qiguang.algorithms.C1_Fundamentals.S2_DataAbstraction;

import edu.princeton.cs.algs4.StdOut;

/**
 * 1.2.19 Parsing.
 * Develop the parse constructors for your Date and Transaction implementations of Exercise 1.2.13 that take
 * a single String argument to specify the initialization values, using the formats given in the table below.
 *
 * Partial solution:
 *    public Date(String date) {
 *       String[] fields = date.split("/");
 *       month = Integer.parseInt(fields[0]);
 *       day   = Integer.parseInt(fields[1]);
 *       year  = Integer.parseInt(fields[2]);
 *    }
 */
public class Exercise_1_2_19 {
    public static class Date {
        private final int month;
        private final int day;
        private final int year;
        public Date(String date) {
            String[] fields = date.split("/");
            month = Integer.parseInt(fields[0]);
            day   = Integer.parseInt(fields[1]);
            year  = Integer.parseInt(fields[2]);
        }
        public Date(int m, int d, int y) {  month = m; day = d; year = y;  }
        public int month() {  return month;  }
        public int day() {  return day;  }
        public int year() {  return day;  }
        public String toString() {  return month() + "/" + day() + "/" + year();  }
    }
    public static class Transaction {
        private final String who;
        private final Date when;
        private final double amount;
        public Transaction(String who, String when, double amount) {
            this.who = who;
            this.when = new Date(when);
            this.amount = amount;
        }
        public Transaction(String who, Date when, double amount) {
            this.who = who;
            this.when = when;
            this.amount = amount;
        }
        public String who() {  return who;  }
        public Date when() {  return when;  }
        public double amount() {  return amount;  }
        public String toString() {  return who() + ", " + when() + ", " + amount();  }
    }
    public static void main(String[] args) {
        Transaction t = new Transaction("Sedgewick", new Date(6, 29, 2017), 67.49);
        StdOut.println(t);
        t = new Transaction("Sedgewick", new Date("6/29/2017"), 67.49);
        StdOut.println(t);
    }
}
