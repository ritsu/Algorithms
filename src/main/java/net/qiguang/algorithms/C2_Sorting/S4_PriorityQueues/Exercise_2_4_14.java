package net.qiguang.algorithms.C2_Sorting.S4_PriorityQueues;

/**
 * 2.4.14 What is the minimum number of items that must be exchanged during a remove the maximum operation
 * in a heap of size N with no duplicate keys? Give a heap of size 15 for which the minimum is achieved.
 * Answer the same questions for two and three successive remove the maximum operations.
 *
 * Answer:
 * 1) 2
 *
 *     0  1  2  3  4  5  6  7  8  9 10 11 12 13 14 15
 * 2a)   15 14 13  6  5 11 12  4  3  2  1  8  7  9 10
 *       10 14 13  6  5 11 12  4  3  2  1  8  7  9     exch, remove 15
 *       14 10 13  6  5 11 12  4  3  2  1  8  7  9     exch
 * 2b)   15 14 13  6  5 11 12  4  3  2  1  8  7  9 10
 *       10 14 13  6  5 11 12  4  3  2  1  8  7  9     exch, remove 15
 *       14 10 13  6  5 11 12  4  3  2  1  8  7  9     exch
 *        9 10 13  6  5 11 12  4  3  2  1  8  7        exch, remove 14
 *       13 10  9  6  5 11 12  4  3  2  1  8  7        exch
 *       13 10 12  6  5 11  9  4  3  2  1  8  7        exch
 * 2c)   15 14 13  6  5 11 12  4  3  2  1  8  7  9 10
 *       10 14 13  6  5 11 12  4  3  2  1  8  7  9     exch, remove 15
 *       14 10 13  6  5 11 12  4  3  2  1  8  7  9     exch
 *        9 10 13  6  5 11 12  4  3  2  1  8  7        exch, remove 14
 *       13 10  9  6  5 11 12  4  3  2  1  8  7        exch
 *       13 10 12  6  5 11  9  4  3  2  1  8  7        exch
 *        7 10 12  6  5 11  9  4  3  2  1  8           exch, remove 13
 *       12 10  7  6  5 11  9  4  3  2  1  8           exch
 *       12 10 11  6  5  7  9  4  3  2  1  8           exch
 */
public class Exercise_2_4_14 {
}
