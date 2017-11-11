package net.qiguang.algorithms.C2_Sorting.S5_Applications;

/**
 * 2.5.3 Criticize the following implementation of a class intended to represent account balances.
 * Why is compareTo() a flawed implementation of the Comparable interface? Describe a way to ﬁx this problem.
 *
 * public class Balance implements Comparable<Balance> {
 *   ...
 *   private double amount;
 *   public int compareTo(Balance that)   {
 *     if (this.amount < that.amount - 0.005) return -1;
 *     if (this.amount > that.amount + 0.005) return +1;
 *     return 0;
 *   }
 *   ...
 * }
 *
 * Answer: It is not transitive for a, b, c if
 *   abs(a - b) < 0.005
 *   abs(b - c) < 0.005
 *   abs(a - c) > 0.005
 * This results in a == b, b == c, a != c
 *
 *   // Note this doesn't handle NaN and -0.0 values
 *   if (this < that) return -1;
 *   if (this > that) return 1;
 *   return 0;
 *
 */
public class Exercise_2_5_03 {
    public static void main(String[] args) {
        Double a = 0.0;
        Double b = 0.0;
        a.compareTo(b);
    }
}
