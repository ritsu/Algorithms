package net.qiguang.algorithms.C2_Sorting.S4_PriorityQueues;

import edu.princeton.cs.algs4.MaxPQ;

/**
 * 2.4.1 Suppose that the sequence P R I O * R * * I * T * Y * * * Q U E * * * U * E (where a letter means
 * insert and an asterisk means remove the maximum) is applied to an initially empty priority queue. Give
 * the sequence of letters returned by the remove the maximum operations.
 *
 * Answer:
 * P R I O *   R
 * P I O R *   R
 * P I O *     P
 * I O I *     O
 * I I T *     T
 * I I Y *     Y
 * I I *       I
 * I *         I
 * Q U E *     U
 * Q E *       Q
 * E *         E
 * U *         U
 * E
 */
public class Exercise_2_4_01 {
    // Verify answer
    public static void main(String[] args) {
        MaxPQ<String> pq = new MaxPQ<String>();
        String[] input = "P R I O * R * * I * T * Y * * * Q U E * * * U * E".split("\\s+");
        StringBuilder sb = new StringBuilder();
        for (String s : input) {
            if (s.equals("*"))
                sb.append(pq.delMax()).append(" ");
            else
                pq.insert(s);
        }
        System.out.println(sb.toString());
    }
}
