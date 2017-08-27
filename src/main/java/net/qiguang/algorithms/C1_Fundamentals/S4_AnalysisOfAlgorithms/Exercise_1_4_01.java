package net.qiguang.algorithms.C1_Fundamentals.S4_AnalysisOfAlgorithms;

/**
 * 1.4.1
 * Show that the number of different triples that can be chosen from N items is precisely N(N-1)(N-2)/6.
 * Hint: Use mathematical induction.
 *
 * Answer:
 * Given N items, for each item, there are (N-1) pairs that can be formed without reusing an item.
 * Of the N(N-1) pairs, 1/2 will be repeats, e.g. (a,b) and (b,a), so 1/2 will be unique.
 * Thus, there are N(N-1)/2 unique pairs.
 * For each of the unique N(N-1)/2 pairs, there are (N-2) triplets that can be formed without reusing an item.
 * Of the N(N-1)(N-2)/2 triplets, 2/3 will be repeats, e.g. ((a,b),c), ((a,c),b), and ((c,b),a), so 1/3 will be unique.
 * Thus, there are N(N-1)(N-2)/6 unique triplets.
 */
public class Exercise_1_4_01 {
    // Nothing to see here
}
