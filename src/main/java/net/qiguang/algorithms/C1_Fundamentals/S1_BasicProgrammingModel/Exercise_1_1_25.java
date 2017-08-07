package net.qiguang.algorithms.C1_Fundamentals.S1_BasicProgrammingModel;

/**
 * 1.1.25
 * Use mathematical induction to prove that Euclid’s algorithm computes the
 * greatest common divisor of any pair of nonnegative integers p and q.
 *
 * Answer:
 * 1. Assume p > q
 * 2. Observe that if q is a factor of p, then gcd(p, q) = q
 * 3. Let there be r such that p is congruent to r mod q
 *         p ≡ r (mod q)
 * 4. By definition, q is a multiple of p - r, or there exists a y such that
 *         y * q = p - r
 *    or       r = p - y * q
 * 5. Observe that any common denominator of (p, q) is also a
 *    common denominator of (p - y * q) and therefore (r, q)
 * 6. Observe that any common denominator of (r, q) is also a
 *    common denominator of (r + y * q) and therefore (p, q)
 * 7. From (2), (5) and (6), conclude that:
 *         gcd(p, q) = gcd(r, q)        where r = p - q
 * 8. Now, given p and q with p > q, gcd(p, q) can be computed as follows:
 *         gcd(p, q)   = gcd(q, r1)     where r1 = p - q
 *         gcd(q, r1)  = gcd(r1, r2)    where r2 = q - r1
 *         gcd(r1, r2) = gcd(r2, r3)    where r3 = r1 - r2
 *         ...
 *         until r[n] reaches 0, leaving r[n-1] as gcd(p, q)
 */
public class Exercise_1_1_25 {
    // Nothing to see here
}
