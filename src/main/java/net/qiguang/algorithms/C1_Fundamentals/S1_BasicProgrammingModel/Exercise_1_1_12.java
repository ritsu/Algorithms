package net.qiguang.algorithms.C1_Fundamentals.S1_BasicProgrammingModel;

/**
 * 1.1.12
 * What does the following code fragment print?
 */
public class Exercise_1_1_12 {
    public static void main(String[] args) {
        // 9 8 7 6 5 4 3 2 1 0
        // 0 1 2 3 4 4 3 2 1 0
        int[] a = new int[10];
        for (int i = 0; i < 10; i++)
            a[i] = 9 - i;
        for (int i = 0; i < 10; i++)
            a[i] = a[a[i]];
        for (int i = 0; i < 10; i++)
            System.out.println(a[i]);
    }
}
