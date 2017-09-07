package net.qiguang.algorithms.C1_Fundamentals.S4_AnalysisOfAlgorithms;

/**
 * 1.4.26 3-collinearity.
 * Suppose that you have an algorithm that takes as input N distinct points in the plane and can return the number
 * of triples that fall on the same line. Show that you can use this algorithm to solve the 3-sum problem.
 *
 * Strong hint: Use algebra to show that (a, a^3), (b, b^3), and (c, c^3) are collinear if and only if a + b + c = 0.
 *
 * Answer: TODO ...
 * Assume points A, B, C, denoted by (x1, y1), (x2, y2), (x3, y3), are collinear
 *
 *          C/| y3
 *         /  |
 *      B/_ __| y2
 *     / |    |
 *  A/___|____| y1
 *  x1   x2   x3
 *
 * The slope between A and B must equal the slope between B and C:
 *     (y2 - y1) / (x2 - x1) = (y3 - y2) / (x3 - x2)
 *
 * Let y1 = x1^3, y2 = x2^3, y3 = x3^3:
 *              (x2^3 - x1^3) / (x2 - x1) = (x3^3 - x2^3) / (x3 - x2)
 *                (x2^3 - x1^3) (x3 - x2) = (x3^3 - x2^3) (x2 - x1)
 *     x3 x2^3 - x2^4 - x3 x1^3 + x2 x1^3 = x2 x3^3 - x1 x3^3 - x2^4 + x1 x2^3
 *            x3 x2^3 - x3 x1^3 + x2 x1^3 = x2 x3^3 - x1 x3^3 + x1 x2^3
 *                                      0 = x3^3(x2 - x1) + x2^3(x1 - x3) + x1^3(x3 - x2)
 *                                        ...
 *
 * The distance AC must equal the sum of the distances AB and BC:
 *     √((x3 - x1)^2 + (y3 - y1)^2) = √((x2 - x1)^2 + (y2 - y1)^2) + √((x3 - x2)^2 + (y3 - y2)^2)
 *                                  ...
 *
 */
public class Exercise_1_4_26 {
    // Nothing to see here.
}
