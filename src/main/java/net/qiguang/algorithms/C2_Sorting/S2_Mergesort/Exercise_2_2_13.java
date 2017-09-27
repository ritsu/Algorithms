package net.qiguang.algorithms.C2_Sorting.S2_Mergesort;

/**
 * 2.2.13 Lower bound for average case.
 * Prove that the expected number of compares used by any compare-based sorting algorithm must be at least
 * ~N lg N (assuming that all possible orderings of the input are equally likely). Hint: The expected number of
 * compares is at least the external path length of the compare tree (the sum of the lengths of the paths from
 * the root to all leaves), which is minimized when it is balanced.
 *
 * Answer:
 * Let C(N) be the number of compares needed to sort an array of N items
 * Let M(N) be the number of compares needed to merge two sorted arrays of N items*
 *    C(N) = C(N/2) + C(N/2) + M(N)
 *
 * If all items in one half of the array are less than (or greater than) all items, in the other half, then
 *    C(N) = C(N/2) + C(N/2) + N/2
 *
 * If N is a power of two, N = 2^n, then
 *        C(2^n) = 2 C(2^(n-1)) + 2^(n-1)
 *    C(2^n)/2^n = C(2^(n-1))/2^(n-1) + 1/2
 *               = C(2^(n-2))/2^(n-2) + 1/2 + 1/2
 *               ...
 *               = C(2^0)/2^0 + 1/2 * n
 *        C(2^n) = C(1) + 1/2 * n * 2^n
 *          C(N) = 1/2 * n * 2^n
 *               = 1/2 * lg(N) * N                      ( since lg(2^n) = n )
 */
public class Exercise_2_2_13 {
    // Nothing to see here
}
