package net.qiguang.algorithms.C1_Fundamentals.S4_AnalysisOfAlgorithms;

/**
 * 1.4.32 Amortized analysis.
 * Prove that, starting from an empty stack, the number of array accesses used by any sequence of
 * M operations in the resizing array implementation of Stack is proportional to M.
 *
 * Answer:
 * Constant number of array accesses when array is not being resized
 * When array is resizing, number of array accesses is proportional to number of items in array, which is
 * proportional to number of operations done on stack.
 */
public class Exercise_1_4_32 {
    // Nothing to see here
}
