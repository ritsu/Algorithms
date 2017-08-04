package net.qiguang.algorithms.C1_Fundamentals.S1_BasicProgrammingModel;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/**
 * 1.1.21
 * Write a program that reads in lines from standard input with each line containing a name and
 * two integers and then uses printf() to print a table with a column of the names, the integers,
 * and the result of dividing the first by the second, accurate to three decimal places. You could
 * use a program like this to tabulate batting averages for baseball players or grades for students.
 */
public class Exercise_1_1_21 {
    private static String[][] resize(String[][] a, int n) {
        String[][] copy = new String[n][4];
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < 4; j++) {
                copy[i][j] = a[i][j];
            }
        }
        return copy;
    }

    public static void main(String[] args) {
        String[][] a = new String[2][4];
        int n = 0;
        String line;

        // Get data
        StdOut.println("Enter: name number number");
        while (!(line = StdIn.readLine()).isEmpty()) {
            String[] items = line.split(" ");
            a[n][0] = items[0];
            a[n][1] = items[1];
            a[n][2] = items[2];
            Double d = Math.round(1000.0 * Double.parseDouble(items[1]) / Double.parseDouble(items[2])) / 1000.0;
            a[n][3] = String.format("%8.3f", d);
            if (++n == a.length) {
                a = resize(a, a.length * 2);
            }
        }

        // Print rows
        StdOut.printf("%12s %4s %4s %8s\n", "Name", "Num1", "Num2", "Average");
        for (int i = 0; i < n; i++) {
            StdOut.printf("%12s %4s %4s %8s\n", a[i][0], a[i][1], a[i][2], a[i][3]);
        }
    }
}
