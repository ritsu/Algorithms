package net.qiguang.algorithms.C2_Sorting.S4_PriorityQueues;

/**
 * 2.4.10 Suppose that we wish to avoid wasting one position in a heap-ordered array pq[],
 * putting the largest value in pq[0], its children in pq[1] and pq[2], and so forth,
 * proceeding in level order. Where are the parents and children of pq[k]?
 *
 * Answer:
 * 0 1 2 3 4 5 6 7 8 9 10
 * 0 1 2
 *   1   3 4
 *     2     5 6
 *       3       7 8
 *         4         9 10
 *
 * Parent of k is (k-1)/2
 * Children of k are 2*k+1 and 2*k+2
 */
public class Exercise_2_4_10 {
}
