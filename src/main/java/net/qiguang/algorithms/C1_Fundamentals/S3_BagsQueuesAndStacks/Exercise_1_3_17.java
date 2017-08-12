package net.qiguang.algorithms.C1_Fundamentals.S3_BagsQueuesAndStacks;

import edu.princeton.cs.algs4.*;

/**
 * 1.3.17
 * Do Exercise 1.3.16 for Transaction.
 */
public class Exercise_1_3_17 {
    public static Transaction[] readTransactions() {
        Queue<Transaction> q = new Queue<Transaction>();
        String line;
        while (!(line = StdIn.readLine()).isEmpty()) {
            q.enqueue(new Transaction(line));
        }
        int N = q.size();
        Transaction[] t = new Transaction[N];
        for (int i = 0; i < N; i++) {
            t[i] = q.dequeue();
        }
        return t;
    }

    public static void main(String[] args) {
        StdOut.println("Enter transactions (e.g. Dollar 1/2/2003 1.00):");
        Transaction[] transactions = readTransactions();
        for (Transaction t : transactions)
            StdOut.println(t);
    }
}
