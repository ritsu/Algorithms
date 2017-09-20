package net.qiguang.algorithms.C1_Fundamentals.S5_CaseStudyUnionFind;

/**
 * 1.5.5
 * Estimate the minimum amount of time (in days) that would be required for quick-ﬁnd to solve a
 * dynamic connectivity problem with 10^9 sites and 10^6 input pairs, on a computer capable of
 * executing 10^9 instructions per second. Assume that each iteration of the inner for loop
 * requires 10 machine instructions.
 *
 *    Number of iterations per union = 10^9
 *                  Number of unions ~ 10^6        (assume most input pairs result in a union)
 *        Number of union iterations ~ 10^9 * 10^6   = 10^15
 *      Number of union instructions ~ 10 * 10^15    = 10^16
 *
 *           Total number of seconds ~ 10^16 / 10^9  = 10&7 seconds
 *                                                   = 116 days
 */
public class Exercise_1_5_05 {
    // Nothing to see here.
}
