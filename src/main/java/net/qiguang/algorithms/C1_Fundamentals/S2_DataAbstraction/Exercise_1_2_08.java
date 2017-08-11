package net.qiguang.algorithms.C1_Fundamentals.S2_DataAbstraction;

/**
 * 1.2.8
 * Suppose that a[] and b[] are each integer arrays consisting of millions of integers.
 * What does the follow code do? Is it reasonably efficient?
 *
 *     int[] t = a; a = b; b = t;
 * Answer. It swaps them. It could hardly be more efficient because it does so by copying
 * references, so that it is not necessary to copy millions of elements.
 */
public class Exercise_1_2_08 {
    // Nothing to see here
}
