package net.qiguang.algorithms.C1_Fundamentals.S2_DataAbstraction;

import edu.princeton.cs.algs4.StdOut;

/**
 * 1.2.17 Robust implementation of rational numbers.
 * Use assertions to develop an implementation of Rational (see Exercise 1.2.16) that is immune to overflow.
 *
 * Note: Enable assertions via one of the following vm arguments:
 *       java [ -enableassertions | -ea  ]
 */
public class Exercise_1_2_17 {
    public static class Rational {
        private final long numerator;
        private final long denominator;

        public Rational(int numerator, int denominator) {
            // Divide by largest common factor
            this.numerator = numerator / gcd(numerator, denominator);
            this.denominator = denominator / gcd(numerator, denominator);
        }

        public Rational plus(Rational b) {
            // Check for overflow
            assert checkOverflow(Math.toIntExact(numerator), b.denominator()) : "Integer overflow error";
            assert checkOverflow(Math.toIntExact(denominator), b.numerator()) : "Integer overflow error";
            assert checkOverflow(Math.toIntExact(denominator), b.denominator()) : "Integer overflow error";

            long common = gcd(denominator, b.denominator());
            long n = numerator * b.denominator() / common + b.numerator() * denominator() / common;
            long d = denominator * b.denominator() / common;
            int p = Math.toIntExact(n / gcd(n, d));
            int q = Math.toIntExact(d / gcd(n, d));
            return new Rational(p, q);
        }

        public Rational minus(Rational b) {
            // Check for overflow
            assert checkOverflow(Math.toIntExact(numerator), b.denominator()) : "Integer overflow error";
            assert checkOverflow(Math.toIntExact(denominator), b.numerator()) : "Integer overflow error";
            assert checkOverflow(Math.toIntExact(denominator), b.denominator()) : "Integer overflow error";

            long common = gcd(denominator, b.denominator());
            long n = numerator * b.denominator() / common - b.numerator() * denominator() / common;
            long d = denominator * b.denominator() / common;
            int p = Math.toIntExact(n / gcd(n, d));
            int q = Math.toIntExact(d / gcd(n, d));
            return new Rational(p, q);
        }
        public Rational times(Rational b) {
            // Check for overflow
            assert checkOverflow(Math.toIntExact(numerator), b.numerator()) : "Integer overflow error";
            assert checkOverflow(Math.toIntExact(denominator), b.denominator()) : "Integer overflow error";

            long n = numerator * b.numerator();
            long d = denominator * b.denominator();
            int p = Math.toIntExact(n / gcd(n, d));
            int q = Math.toIntExact(d / gcd(n, d));
            return new Rational(p, q);
        }
        public Rational divides(Rational b) {
            // Check for overflow
            assert checkOverflow(Math.toIntExact(numerator), b.denominator()) : "Integer overflow error";
            assert checkOverflow(Math.toIntExact(denominator), b.numerator()) : "Integer overflow error";

            long n = numerator * b.denominator();
            long d = denominator * b.numerator();
            int p = Math.toIntExact(n / gcd(n, d));
            int q = Math.toIntExact(d / gcd(n, d));
            return new Rational(p, q);
        }
        boolean equals(Rational that) {
            if (this.numerator() != that.numerator()) return false;
            if (this.denominator() != that.denominator()) return false;
            return true;
        }

        public String toString() {
            return numerator + "/" + denominator;
        }

        private long gcd(long p, long q) {
            if (q == 0) return p;
            long r = p % q;
            return gcd(q, r);
        }
        public int numerator() {  return Math.toIntExact(numerator);  }
        public int denominator() {  return Math.toIntExact(denominator);  }

        // Check that a * b does not overflow
        public boolean checkOverflow(int a, int b) {
            if (a > Math.ceil( 2147483647 / b)) return false;
            if (a < Math.ceil(-2147483648 / b)) return false;
            return true;
        }
    }

    public static void main(String[] args) {
        Rational a = new Rational(7, 3);
        Rational b = new Rational(-7, 11);
        Rational c = new Rational(8, 12);
        Rational d = new Rational(4, 6);
        Rational e = new Rational(1, 147483647);
        Rational f = new Rational(1, 147483648);

        StdOut.println("a = " + a);
        StdOut.println("b = " + b);
        StdOut.println("c = " + c);
        StdOut.println("d = " + d);
        StdOut.println("e = " + e);
        StdOut.println("f = " + f);

        StdOut.println("a == b = "  + (a.equals(b)));
        StdOut.println("c == d = "  + (c.equals(d)));
        StdOut.println("e == f = "  + (c.equals(d)));

        StdOut.println(a + " + " + b + " = " + a.plus(b));
        StdOut.println(c + " + " + d + " = " + c.plus(d));
        StdOut.println(e + " + " + f + " = " + e.plus(f));

        StdOut.println(a + " - " + b + " = " + a.minus(b));
        StdOut.println(c + " - " + d + " = " + c.minus(d));
        StdOut.println(e + " - " + f + " = " + e.minus(f));

        StdOut.println(a + " * " + b + " = " + a.times(b));
        StdOut.println(c + " * " + d + " = " + c.times(d));
        StdOut.println(e + " * " + f + " = " + e.times(f));

        StdOut.println(a + " / " + b + " = " + a.divides(b));
        StdOut.println(c + " / " + d + " = " + c.divides(d));
        StdOut.println(e + " / " + f + " = " + e.divides(f));
    }
}
