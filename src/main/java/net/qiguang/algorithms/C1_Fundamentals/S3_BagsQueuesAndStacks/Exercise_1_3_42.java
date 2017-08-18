package net.qiguang.algorithms.C1_Fundamentals.S3_BagsQueuesAndStacks;

import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;

/**
 * 1.3.42 Copy a stack.
 * Create a new constructor for the linked-list implementation of Stack so that
 *
 *     Stack<Item> t = new Stack<Item>(s);
 *
 * makes t a reference to a new and independent copy of the stack s.
 */
public class Exercise_1_3_42 {
    public static class MyStack<Item> extends Stack<Item> {
        public MyStack() { }
        public MyStack(Stack<Item> s) {
            Stack<Item> copy = new Stack<Item>();
            while (!s.isEmpty()) {
                Item item = s.pop();
                copy.push(item);
            }
            while (!copy.isEmpty()) {
                Item item = copy.pop();
                this.push(item);
                s.push(item);
            }
        }
    }

    public static void main(String[] args) {
        MyStack<String> s1 = new MyStack<>();

        StdOut.println("Create Stack 1.");
        for (int i = 0; i < 10; i++) {
            s1.push(String.valueOf(i));
        }
        StdOut.println("Stack 1: " + s1);

        StdOut.println("\nCreate Stack 2.");
        MyStack<String> s2 = new MyStack<>(s1);
        StdOut.println("Stack 1: " + s1);
        StdOut.println("Stack 2: " + s2);

        StdOut.println("\nPop 5 items from Stack 1.");
        for (int i = 0; i < 5; i++) {
            StdOut.printf("Stack 1: (%s) %s\n", s1.pop(), s1);
            StdOut.printf("Stack 2:     %s\n", s2);
        }
        StdOut.println("\nPop 3 items from Stack 2.");
        for (int i = 0; i < 3; i++) {
            StdOut.printf("Stack 1:     %s\n", s1);
            StdOut.printf("Stack 2: (%s) %s\n", s2.pop(), s2);
        }

        StdOut.println("\nPush 5 items to Stack 1.");
        for (int i = 10; i < 15; i++) {
            s1.push(String.valueOf(i));
            StdOut.printf("Stack 1: (%d) %s\n", i, s1);
            StdOut.printf("Stack 2:      %s\n", s2);
        }
        StdOut.println("\nPush 3 items to Stack 2.");
        for (int i = 10; i < 13; i++) {
            s2.push(String.valueOf(i));
            StdOut.printf("Stack 1:      %s\n", s1);
            StdOut.printf("Stack 2: (%d) %s\n", i, s2);
        }
    }
}
