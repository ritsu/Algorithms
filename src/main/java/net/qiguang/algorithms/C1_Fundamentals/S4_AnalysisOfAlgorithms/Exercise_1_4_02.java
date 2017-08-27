package net.qiguang.algorithms.C1_Fundamentals.S4_AnalysisOfAlgorithms;

import edu.princeton.cs.algs4.StdOut;

/**
 * 1.4.2
 * Modify ThreeSum to work properly even when the int values are so large that
 * adding two of them might cause overflow.
 */
public class Exercise_1_4_02 {
    // Brute force implementation taken from text
    public static class ThreeSum {
        public static int count(int[] a) {
            // Count triples that sum to 0.
            int N = a.length;
            int cnt = 0;
            for (int i = 0; i < N; i++)
                for (int j = i + 1; j < N; j++)
                    for (int k = j + 1; k < N; k++) {
                        if (a[i] + a[j] + a[k] == 0) {
                            StdOut.printf("(%11d, %11d, %11d)\n", a[i], a[j], a[k]);
                            cnt++;
                        }
                    }
            return cnt;
        }
        public static int countWithOverflow(int[] a) {
            // Count triples that sum to 0.
            int N = a.length;
            int cnt = 0;
            for (int i = 0; i < N; i++)
                for (int j = i + 1; j < N; j++)
                    for (int k = j + 1; k < N; k++) {
                        int x = a[i];
                        int y = a[j];
                        int z = a[k];
                        int carry = 0;
                        boolean zero = true;
                        while (zero && (x != 0 || y != 0 || z != 0)) {
                            int lsd = x % 10 + y % 10 + z % 10;   // Sum least significant digits
                            if (lsd % 10 + carry != 0) zero = false;
                            carry = lsd / 10;
                            x /= 10;
                            y /= 10;
                            z /= 10;
                        }
                        if (zero) {
                            StdOut.printf("(%11d, %11d, %11d)\n", a[i], a[j], a[k]);
                            cnt++;
                        }
                    }
            return cnt;
        }
    }
    public static void main(String[] args) {
        // −2,147,483,648 and 2,147,483,647 are smallest and largest values for int
        int a = -2147483648;
        int b = 2147483647;

        // Setup array
        int[] items = new int[7];
        items[0] = a;          // -2147483648   (-2147483648 + -10 = 2147483638)
        items[1] = -10;        // -10
        items[2] = a + 10;     // -2147483638
        items[3] = b;          //  2147483647   (2147483647 + 11 = -2147483638)
        items[4] = 11;         //  11
        items[5] = b - 9;      //  2147483638
        items[6] = 1;

        // Without handling overflow
        StdOut.println("No overflow handling:");
        StdOut.println(ThreeSum.count(items));

        // With handling overflow
        StdOut.println("With overflow handling:");
        StdOut.println(ThreeSum.countWithOverflow(items));
    }
}
