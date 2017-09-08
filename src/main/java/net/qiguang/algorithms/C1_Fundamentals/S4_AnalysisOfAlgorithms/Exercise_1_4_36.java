package net.qiguang.algorithms.C1_Fundamentals.S4_AnalysisOfAlgorithms;

/**
 * 1.4.36 Space usage for pushdown stacks.
 * Justify the entries in the table below, which shows typical space usage for various pushdown stack implementations.
 * Use a static nested class for linked-list nodes to avoid the non-static nested class overhead.
 *
 * data structure   item type   space usage for N int values (bytes)
 * ---------------------------------------------------------------------
 * linked list      int         ~ 32 N
 *                              16 (obj) + 8 (item ref) + 8 (node ref)
 *                  Integer     ~ 64 N
 *                              32 (above) + 16 (Integer obj) + 8 (ref) + 8 (instance variable)
 * resizing array   int         between ~4 N and ~16 N
 *                              4 (int), and 1/4 full when resizing down
 *                  Integer     between ~32 N and ~56 N
 *                              16 (Integer obj) + 8 (ref) + 8 (instance variable) for each Integer
 *                              when resizing down, additional 3/4 * N * 24 (above, without instance variable)
 */
public class Exercise_1_4_36 {
    // Nothing to see here.
}
