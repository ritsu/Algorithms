package net.qiguang.algorithms.C1_Fundamentals.S3_BagsQueuesAndStacks;

import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;

/**
 * 1.3.12
 * Write an iterable Stack client that has a static method copy() that
 * takes a stack of strings as argument and returns a copy of the stack.
 *
 * Note : This ability is a prime example of the value of having an iterator,
 * because it allows development of such functionality without changing the basic API.
 */
public class Exercise_1_3_12 {
    public static Stack<String> copy(Stack<String> original) {
        Stack<String> copy = new Stack<String>();
        Stack<String> copy2 = new Stack<String>();
        for (String s : original) {
            copy.push(s);
        }
        for (String s : copy) {
            copy2.push(s);
        }
        return copy2;
    }

    public static void main(String[] args) {
        Stack<String> a = new Stack<String>();
        for (int i = 0; i < 10; i++) {
            a.push(String.valueOf(i));
        }
        StdOut.println("Stack A: " + a);

        Stack<String> b = copy(a);
        StdOut.println("Stack B = copy(A)");
        StdOut.println("Stack B: " + b);

        StdOut.println("pop() some values off Stack B");
        for (int i = 0; i < 5; i++) {
            b.pop();
        }
        StdOut.println("Stack A: " + a);
        StdOut.println("Stack B: " + b);
    }
}
