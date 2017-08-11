package net.qiguang.algorithms.C1_Fundamentals.S2_DataAbstraction;

import edu.princeton.cs.algs4.StdOut;

/**
 * 1.2.13
 * Using our implementation of Date as a model (page 91), develop an implementation of Transaction.
 *
 * public class  Transaction implements Comparable<Transaction>
 * ----------------------------------------------------------------------------------------
 *               Transaction(String who, Date when, double amount)
 *               Transaction(String transaction) create a transaction (parse constructor)
 * String        who()                           customer name
 * Date          when()                          date
 * double        amount()                        amount
 * String        toString()                      string representation
 * boolean       equals(Object that)             is this the same transaction as that?
 * int           compareTo(Transaction that)     compare this transaction to that
 * int           hashCode()                      hash code
 */
public class Exercise_1_2_13 {
    public static class Date {
        private final int month;
        private final int day;
        private final int year;
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
    }
    
}
