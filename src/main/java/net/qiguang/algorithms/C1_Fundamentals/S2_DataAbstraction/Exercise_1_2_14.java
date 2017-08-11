package net.qiguang.algorithms.C1_Fundamentals.S2_DataAbstraction;

import edu.princeton.cs.algs4.StdOut;

/**
 * 1.2.14
 * Using our implementation of equals() in Date as a model (page 103),
 * develop implementations of equals() for Transaction.
 */
public class Exercise_1_2_14 {
    public static class Date {
        private final int month;
        private final int day;
        private final int year;
        public Date(int m, int d, int y) {  month = m; day = d; year = y;  }
        public int month() {  return month;  }
        public int day() {  return day;  }
        public int year() {  return day;  }
        public String toString() {  return month() + "/" + day() + "/" + year();  }
        public boolean equals(Object x) {
            if (this == x) return true;
            if (x == null) return false;
            if (this.getClass() != x.getClass()) return false;
            Date that = (Date) x;
            if (this.day != that.day)            return false;
            if (this.month != that.month)        return false;
            if (this.year != that.year)          return false;
            return true;
        }
    }
    public static class Transaction {
        private final String who;
        private final Date when;
        private final double amount;
        public Transaction(String who, Date when, double amount) {
            this.who = who;
            this.when = when;
            this.amount = amount;
        }
        public String who() {  return who;  }
        public Date when() {  return when;  }
        public double amount() {  return amount;  }
        public String toString() {  return who() + ", " + when() + ", " + amount();  }
        public boolean equals(Object x) {
            if (this == x) return true;
            if (x == null) return false;
            if (this.getClass() != x.getClass()) return false;
            Transaction that = (Transaction) x;
            if (!this.who.equals(that.who))      return false;
            if (!this.when.equals(that.when))    return false;
            if (this.amount != that.amount)      return false;
            return true;
        }
    }
    public static void main(String[] args) {
        Transaction t1 = new Transaction("Sedgewick", new Date(6, 29, 2017), 67.49);
        StdOut.println(t1);
        Transaction t2 = new Transaction("Sedgewick", new Date(6, 29, 2017), 67.49);
        StdOut.println(t2);
        StdOut.println(t1.equals(t2));

        t1 = new Transaction("Sedgewick", new Date(6, 29, 2017), 67.49);
        StdOut.println(t1);
        t2 = new Transaction("Wayne", new Date(6, 29, 2017), 67.49);
        StdOut.println(t2);
        StdOut.println(t1.equals(t2));
        t1 = new Transaction("Sedgewick", new Date(6, 29, 2017), 67.49);
        StdOut.println(t1);
        t2 = new Transaction("Sedgewick", new Date(6, 29, 2016), 67.49);
        StdOut.println(t2);
        StdOut.println(t1.equals(t2));
        t1 = new Transaction("Sedgewick", new Date(6, 29, 2017), 67.49);
        StdOut.println(t1);
        t2 = new Transaction("Sedgewick", new Date(6, 29, 2017), 67.48);
        StdOut.println(t2);
        StdOut.println(t1.equals(t2));
    }
}
