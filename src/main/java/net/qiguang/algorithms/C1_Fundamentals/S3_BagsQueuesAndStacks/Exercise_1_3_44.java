package net.qiguang.algorithms.C1_Fundamentals.S3_BagsQueuesAndStacks;

import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

/**
 * 1.3.44 Text editor buffer.
 * Develop a data type for a buffer in a text editor that implements the following API:
 *
 * public class Buffer
 * --------------------------------------------------------------------------
 *              Buffer()          create an empty buffer
 *         void insert(char c)    insert c at the cursor position
 *         char delete()          delete and return the character at the cursor
 *         void left(int k)       move the cursor k positions to the left
 *         void right(int k)      move the cursor k positions to the right
 *          int size()            number of characters in the buffer
 *
 *              API for a text buffer
 *
 * Hint: Use two stacks.
 */
public class Exercise_1_3_44 {
    public static class Buffer {
        Stack<Character> left;
        Stack<Character> right;

        public Buffer() {
            left = new Stack<>();
            right = new Stack<>();
        }
        public void insert(char c) {
            right.push(c);
        }
        public char delete() {
            if (right.isEmpty()) return ' ';
            return right.pop();
        }
        public void left(int k) {
            for (int i = 0; i < k; i++) {
                if (left.isEmpty()) return;
                right.push(left.pop());
            }
        }
        public void right(int k) {
            for (int i = 0; i < k; i++) {
                if (right.isEmpty()) return;
                left.push(right.pop());
            }
        }
        public int size() {
            return left.size() + right.size();
        }

        // String method with cursor position indicated by a | character
        public String toString() {
            String s = "";
            for (char c : left) {
                s += c;
            }
            s += "|";
            for (char c : right) {
                s += c;
            }
            return s;
        }
    }

    public static void main(String[] args) {
        // Create buffer
        Buffer b = new Buffer();

        // Generate random text
        for (int i = 0; i < 100; i++) {
            int r = StdRandom.uniform(100);
            if (r < 50) {
                // Insert character
                char c = (char) StdRandom.uniform(65, 123);
                b.insert(c);
                StdOut.printf("insert(%c):", c);
            }
            else if (r < 70) {
                // Move left
                int m = StdRandom.uniform(4);
                b.left(m);
                StdOut.printf("  left(%d):", m);
            }
            else if (r < 90 ) {
                // Move right
                int m = StdRandom.uniform(4);
                b.right(m);
                StdOut.printf(" right(%d):", m);
            }
            else {
                // Delete
                b.delete();
                StdOut.printf(" delete():");
            }
            StdOut.printf(" [%2d] %s\n", b.size(), b);
        }
    }
}
