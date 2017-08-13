package net.qiguang.algorithms.C1_Fundamentals.S3_BagsQueuesAndStacks;

import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;

/**
 * 1.3.4
 * Write a stack client Parentheses that reads in a text stream from standard input and uses a stack to
 * determine whether its parentheses are properly balanced.
 * For example, your program should print
 *    true  for [()]{}{[()()]()} and
 *    false for [(]).
 */
public class Exercise_1_3_04 {
    public static void main(String[] args) {
        // Test cases
        Stack<String> inputs = new Stack<String>();
        inputs.push("[()]{}{[()()]()}");
        inputs.push("[(])");
        inputs.push("((({{{[[[]]]}}})))[]{}()");
        inputs.push("((({{{[[[]]]}}}))][]{}()");
        inputs.push("((({{{[[[]]]}}}))))[]{}()");

        // Simulate "read text stream from standard input"
        readInputs:
        for (String input : inputs) {
            StdOut.println("Evaluating " + input);
            Stack<Character> s = new Stack<Character>();
            for (int i = 0; i < input.length(); i++) {
                switch (input.charAt(i)) {
                    case '[':
                    case '(':
                    case '{':
                        s.push(input.charAt(i));
                        break;
                    case ']':
                        if (s.isEmpty() || s.pop() != '[') {
                            StdOut.printf("false - mismatch at %s\n", input.charAt(i));
                            continue readInputs;
                        }
                        break;
                    case ')':
                        if (s.isEmpty() || s.pop() != '(') {
                            StdOut.printf("false - mismatch at %s\n", input.charAt(i));
                            continue readInputs;
                        }
                        break;
                    case '}':
                        if (s.isEmpty() || s.pop() != '{') {
                            StdOut.printf("false - mismatch at %s\n", input.charAt(i));
                            continue readInputs;
                        }
                        break;
                    default:
                        StdOut.println("Invalid character");
                        break;
                }
            }
            StdOut.println("true");
        }
    }
}
