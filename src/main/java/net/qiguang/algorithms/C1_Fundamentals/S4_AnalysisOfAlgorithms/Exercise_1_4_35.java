package net.qiguang.algorithms.C1_Fundamentals.S4_AnalysisOfAlgorithms;

/**
 * 1.4.35 Time costs for pushdown stacks.
 * Justify the entries in the table below, which shows typical time costs for various pushdown stack implementations,
 * using a cost model that counts both data references (references to data pushed onto the stack, either an array
 * reference or a reference to an object’s instance variable) and objects created.
 *
 *                              cost to push N int values
 * data structure   item type   data references   objects created
 * ---------------------------------------------------------------------
 * linked list      int         2 N                 N
 *                              item                new node
 *                              last node next
 *                  Integer     3 N               2 N
 *                              additional obj from Integer autoboxing
 * resizing array   int        ~5 N              lg N
 *                              item                new array when resizing
 *                              array
 *                              array length
 *                              stack size
 *                              next array index
 *                  Integer    ~5 N                ~N
 *                                                  new Integer objects when resizing
 */
public class Exercise_1_4_35 {
    // Nothing to see here.
}
