package net.qiguang.algorithms.C1_Fundamentals.S3_BagsQueuesAndStacks;

import edu.princeton.cs.algs4.StdOut;

/**
 * 1.3.2
 * Give the output printed by java Stack for the input
 *    it was - the best - of times - - - it  was - the -
 */
public class Exercise_1_3_02 {
    public static void main(String[] args) {
        // Stack is LIFO
        // Input | Stack           | Output
        // -----------------------------------------------------------
        // it    | it              |
        // was   | it was          |
        // -     | it              | was
        // the   | it the          | was
        // best  | it the best     | was
        // -     | it the          | was best
        // of    | it the of       | was best
        // times | it the of times | was best
        // -     | it the of       | was best times
        // -     | it the          | was best times of
        // -     | it              | was best times of the
        // it    | it it           | was best times of the
        // was   | it it was       | was best times of the
        // -     | it it           | was best times of the was
        // the   | it it the       | was best times of the was
        // -     | it it           | was best times of the was the
        StdOut.println("was best times of the was the");
    }
}
