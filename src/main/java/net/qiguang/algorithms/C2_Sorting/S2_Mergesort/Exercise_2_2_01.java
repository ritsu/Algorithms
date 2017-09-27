package net.qiguang.algorithms.C2_Sorting.S2_Mergesort;

/**
 * 2.2.1
 * Give a trace, in the style of the trace given at the beginning of this section, showing how the keys
 * A E Q S U Y E I N O S T are merged with the abstract in-place merge() method.
 *
 * Answer:
 *          k  0 1 2 3 4 5   6 7 8 9 10 11   i  j  0 1 2 3 4 5   6 7 8 9 10 11
 *   input     A E Q S U Y | E I N O S T
 *    copy     A E Q S U Y | E I N O S T           A E Q S U Y | E I N O S T
 *                                           0  5
 *          0  A                             1  5  A
 *          1  A E                                   E
 *          2  A E E                                             E
 *          3  A E E I                                             I
 *          4  A E E I N                                             N
 *          5  A E E I N O                                             O
 *          6  A E E I N O Q                           Q
 *          7  A E E I N O Q S                           S
 *          8  A E E I N O Q S S                                         S
 *          9  A E E I N O Q S S T                                         T
 *         10  A E E I N O Q S S T U                       U
 *         11  A E E I N O Q S S T U Y                       Y
 */
public class Exercise_2_2_01 {
    // Nothing to see here.
}
