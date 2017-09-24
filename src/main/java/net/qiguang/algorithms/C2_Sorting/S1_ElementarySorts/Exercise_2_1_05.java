package net.qiguang.algorithms.C2_Sorting.S1_ElementarySorts;

/**
 * 2.1.5
 * For each of the two conditions in the inner for loop in insertion sort (Algorithm 2.2),
 * describe an array of N items where that condition is always false when the loop terminates.
 *
 * Answer:
 * 1) j > 0 : The item being examined is always the smallest item encountered thus far.
 *            e.g. 9 8 7 6 5 4 3 2 1 0
 *
 * 2) less(a[j], a[j-1]) : The item being exampled is never the smallest item encountered thus far.
 *                         e.g. 0 [any arrangement of remaining elements]
 */
public class Exercise_2_1_05 {
    // Nothing to see here.
}
