package net.qiguang.algorithms.C1_Fundamentals.S3_BagsQueuesAndStacks;

import edu.princeton.cs.algs4.StdOut;

/**
 * 1.3.3
 * Suppose that a client performs an intermixed sequence of (stack) push and pop operations.
 * The push operations put the integers 0 through 9 in order onto the stack;
 * the pop operations print out the return values.
 * Which of the following sequence(s) could not occur?
 *    a. 4 3 2 1 0 9 8 7 6 5
 *    b. 4 6 8 7 5 3 2 9 0 1
 *    c. 2 5 6 7 4 8 9 3 1 0
 *    d. 4 3 2 1 0 5 6 7 8 9
 *    e. 1 2 3 4 5 6 9 8 7 0
 *    f. 0 4 6 5 3 8 1 7 2 9
 *    g. 1 4 7 9 8 6 5 3 0 2
 *    h. 2 1 4 3 6 5 8 7 9 0
 */
public class Exercise_1_3_03 {
    public static void main(String[] args) {
        // Order of input: 0 1 2 3 4 5 6 7 8 9
        // a. 0 1 2 3 4 - - - - - 5 6 7 8 9 - - - - -
        // b. 0 cannot be popped before 1
        // c. 0 1 2 - 3 4 5 - 6 - 7 - - 8 - 9 - - - -
        // d. 0 1 2 3 4 - - - - - 5 - 6 - 7 - 8 - 9 -
        // e. 0 1 - 2 - 3 - 4 - 5 - 6 - 7 8 9 - - - -
        // f. 1 cannot be popped before 7
        // g. 0 cannot be popped before 2
        // h. 0 1 2 - - 3 4 - - 5 6 - - 7 8 - - 9 - -
        StdOut.println("b, f, g");
    }
}
