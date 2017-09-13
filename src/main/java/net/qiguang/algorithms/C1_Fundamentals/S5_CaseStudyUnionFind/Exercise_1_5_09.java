package net.qiguang.algorithms.C1_Fundamentals.S5_CaseStudyUnionFind;

/**
 * 1.5.9
 * Draw the tree corresponding to the id[] array depicted at right (below).
 * Can this be the result of running weighted quick-union?
 * Explain why this is impossible or give a sequence of operations that results in this array.
 *
 *   i    0 1 2 3 4 5 6 7 8 9
 * ---------------------------
 * id[i]  1 1 3 1 5 6 1 3 4 5
 *
 * Answer:
 *     Assume union assigns id[q] = id[p] when weights are equal
 *     id[8] is a child of id[4] -> union(4, 8);
 *     id[4] is a child of id[5] -> id[5] must have at least 1 child before union(5, 4) [union(5, 9)]
 *     id[5] is a child of id[6] -> id[6] must have at least 2 child before union(6, 5) [union(6, ?)] -> Impossible
 */
public class Exercise_1_5_09 {
    // Nothing to see here.
}
