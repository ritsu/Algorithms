package net.qiguang.algorithms.C1_Fundamentals.S3_BagsQueuesAndStacks;

import edu.princeton.cs.algs4.*;

/**
 * 1.3.16
 * Using readInts() on page 126 as a model, write a static method readDates() for Date that reads dates
 * from standard input in the format specified in the table on page 119 and returns an array containing them.
 */
public class Exercise_1_3_16 {
    public static Date[] readDates() {
        Queue<Date> q = new Queue<Date>();
        String line;
        while (!(line = StdIn.readLine()).isEmpty()) {
            q.enqueue(new Date(line));
        }
        int N = q.size();
        Date[] d = new Date[N];
        for (int i = 0; i < N; i++) {
            d[i] = q.dequeue();
        }
        return d;
    }

    public static void main(String[] args) {
        StdOut.println("Enter dates (e.g. 1/2/2003):");
        Date[] dates = readDates();
        for (Date d : dates)
            StdOut.println(d);
    }
}
