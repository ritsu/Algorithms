package net.qiguang.algorithms.C1_Fundamentals.S4_AnalysisOfAlgorithms;

/**
 * 1.4.33 Memory requirements on a 32-bit machine.
 * Give the memory requirements for
 *
 *              Answers:
 * Integer,     8 (object overhead) + 4 (instance variable) + 4 (padding) = 16
 * Date,        8 (object overhead) + 12 (3 instance variables) + 4 (padding) = 24
 * Counter,     8 (object overhead) + 4 (String reference) + 4 (instance variable) = 16
 * int[],       8 (obj) + 4 (length) + 4 (padding) = 16 + 4*N
 * double[],    8 (obj) + 4 (length) + 4 (padding) = 16 + 8*N
 * double[][],  8 (obj) + 4 (length) + 4 (padding) = 16 + 4*N
 * String,      8 (obj) + 12 (3 instance variables) + 4 (array reference) = 24
 * Node,        8 (obj) + 4 (ref enclosing instance) + 8 (item and node ref) + 4 (padding) = 24
 * Stack (linked-list representation)
 *              8 (obj) + 4 (ref instance) + 4 (int instance) = 16 + (24 + X)*N where X depends on Item type
 *
 * for a 32-bit machine. Assume that references are 4 bytes, object overhead is 8 bytes,
 * and padding is to a multiple of 4 bytes.
 *
 * Note: Book uses 64-bit machine model, which includes 16 bytes for object overhead and 8 bytes for references,
 * so numbers don't match those in this question.
 */
public class Exercise_1_4_33 {
    // Nothing to see here.
}
