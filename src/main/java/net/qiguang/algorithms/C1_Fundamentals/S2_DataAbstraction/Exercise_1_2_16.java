package net.qiguang.algorithms.C1_Fundamentals.S2_DataAbstraction;

import edu.princeton.cs.algs4.StdOut;

/**
 * 1.2.16 Rational numbers.
 * Implement an immutable data type Rational for rational numbers that supports
 * addition, subtraction, multiplication, and division.
 *
 * public class  Rational
 *            Rational(int numerator, int denominator)
 * Rational   plus(Rational b)        sum of this number and b
 * Rational   minus(Rational b)       difference of this number and b
 * Rational   times(Rational b)       product of this number and b
 * Rational   divides(Rational b)     quotient of this number and b
 * boolean    equals(Rational that)   is this number equal to that ?
 * String     toString()              string representation
 *
 * You do not have to worry about testing for overflow(see Exercise 1.2.17), but use as instance variables
 * two long values that represent the numerator and denominator to limit the possibility of overflow.
 * Use Euclid’s algorithm (see page 4) to ensure that the numerator and denominator never have any common factors.
 * Include a test client that exercises all of your methods.
 */
public class Exercise_1_2_16 {
    public static class Rational {
        private final long numerator;
        private final long denominator;

        public Rational(int numerator, int denominator) {
            // Divide by largest common factor
            this.numerator = numerator / gcd(numerator, denominator);
            this.denominator = denominator / gcd(numerator, denominator);
        }

        public Rational plus(Rational b) {
            long common = gcd(denominator, b.denominator());
            long n = numerator * b.denominator() / common + b.numerator() * denominator() / common;
            long d = denominator * b.denominator() / common;
            int p = Math.toIntExact(n / gcd(n, d));
            int q = Math.toIntExact(d / gcd(n, d));
            return new Rational(p, q);
        }

        public Rational minus(Rational b) {
            long common = gcd(denominator, b.denominator());
            long n = numerator * b.denominator() / common - b.numerator() * denominator() / common;
            long d = denominator * b.denominator() / common;
            int p = Math.toIntExact(n / gcd(n, d));
            int q = Math.toIntExact(d / gcd(n, d));
            return new Rational(p, q);
        }
        public Rational times(Rational b) {
            long n = numerator * b.numerator();
            long d = denominator * b.denominator();
            int p = Math.toIntExact(n / gcd(n, d));
            int q = Math.toIntExact(d / gcd(n, d));
            return new Rational(p, q);
        }
        public Rational divides(Rational b) {
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
    }

    public static void main(String[] args) {
        Rational a = new Rational(7, 3);
        Rational b = new Rational(-7, 11);
        Rational c = new Rational(8, 12);
        Rational d = new Rational(4, 6);
        Rational e = new Rational(1, 100);
        Rational f = new Rational(1, 1000);

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
