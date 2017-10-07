package net.qiguang.algorithms.C2_Sorting.S4_PriorityQueues;

/**
 * 2.4.20 Prove that sink-based heap construction uses fewer than 2N compares and fewer than N exchanges.
 *
 * Answer:
 * Let h = height of a binary tree
 * Nodes in the first row can have at most     h    exchanges
 * Nodes on the second row can have at most  2(h-1) exchanges
 * Thid row ...                              4(h-2)
 *                                            ...
 *                                         2^h(h-h)
 * Sum of exchanges = 2^(h+1) - h - 2
 *                  = N - (h - 1)
 *                  < N
 * Each exchange involves at most 2 compares, so compares < 2N
 */
public class Exercise_2_4_20 {
}
