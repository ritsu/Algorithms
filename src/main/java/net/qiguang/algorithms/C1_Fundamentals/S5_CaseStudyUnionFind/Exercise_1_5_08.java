package net.qiguang.algorithms.C1_Fundamentals.S5_CaseStudyUnionFind;

/**
 * 1.5.8
 * Give a counterexample that shows why this intuitive implementation of union() for quick-ﬁnd is not correct:
 *
 * public void union(int p, int q) {
 *     if (connected(p, q)) return;
 *     // Rename p’s component to q’s name.
 *     for (int i = 0; i < id.length; i++)
 *         if (id[i] == id[p]) id[i] = id[q];
 *         count--;
 *     }
 *
 * Answer:
 *     union(0, 1);   // 1 1 2 3 4 5 ...
 *     union(0, 2);   // 2 1 2 3 4 5 ...
 */
public class Exercise_1_5_08 {
    // Nothing to see here.
}
