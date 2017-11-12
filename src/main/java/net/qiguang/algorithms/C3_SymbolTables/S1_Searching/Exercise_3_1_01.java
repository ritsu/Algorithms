package net.qiguang.algorithms.C3_SymbolTables.S1_Searching;

import edu.princeton.cs.algs4.BinarySearchST;

import java.util.Arrays;
import java.util.Random;

/**
 * 3.1.1 Write a client that creates a symbol table mapping letter grades to numerical scores, as in the table below,
 * then reads from standard input a list of letter grades and computes and prints the GPA
 * (the average of the numbers corresponding to the grades).
 *
 * A+   A    A-   B+   B    B-   C+   C    C-   D    F
 * 4.33 4.00 3.67 3.33 3.00 2.67 2.33 2.00 1.67 1.00 0.00
 */
public class Exercise_3_1_01 {
    public static void main(String[] args) {
        // Create symbol table
        BinarySearchST<String, Double> st = new BinarySearchST<>();
        String[] grades = {"A+", "A", "A-", "B+", "B", "B-", "C+", "C", "C-", "D", "F"};
        Double[] gpas = {4.33, 4.00, 3.67, 3.33, 3.00, 2.67, 2.33, 2.00, 1.67, 1.00, 0.00};
        for (int i = 0; i < grades.length; i++) {
            st.put(grades[i], gpas[i]);
        }

        // Generate random input
        int n = 10;
        String[] input = new String[n];
        Random r = new Random();
        for (int i = 0; i < n; i++) {
            input[i] = grades[r.nextInt(grades.length)];
        }

        // Calculate average gpa
        double sum = 0;
        for (String s : input) {
            sum += st.get(s);
        }
        double gpa = sum / input.length;
        System.out.println(Arrays.toString(input));
        System.out.printf("%4.2f", gpa);
    }
}
