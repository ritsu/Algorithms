package net.qiguang.algorithms.C1_Fundamentals.S3_BagsQueuesAndStacks;

import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

/**
 * 1.3.45 Stack generability.
 * Suppose that we have a sequence of intermixed push and pop operations as with our test stack client,
 * where the integers 0, 1, ..., N-1 in that order (push directives) are intermixed with N minus signs
 * (pop directives). Devise an algorithm that determines whether the intermixed sequence causes the stack
 * to underflow. (You may use only an amount of space independent of N—you cannot store the integers in a
 * data structure.) Devise a linear-time algorithm that determines whether a given permutation can be
 * generated as output by our test client (depending on where the pop directives occur).
 *
 * Solution: The stack does not underflow unless there exists an integer k such that the first k pop operations
 * occur before the first k push operations. If a given permutation can be generated, it is uniquely generated
 * as follows: if the next integer in the output permutation is in the top of the stack, pop it; otherwise,
 * push it onto the stack.
 */
public class Exercise_1_3_45 {
    public static boolean willUnderflow(String s) {
        String[] items = s.split("\\s+");
        int pushes = 0;
        int pops = 0;
        for (int i = 0; i < items.length; i++) {
            if (items[i].equals("-")) pops++;
            else                      pushes++;
            if (pops > pushes) return true;
        }
        return false;
    }

    public static boolean isValidOutput(String s) {
        // Get output into queue
        String[] items = s.split("\\s+");
        Queue<Integer> output = new Queue<>();
        for (String item : items) {
            output.enqueue(Integer.parseInt(item));
        }
        // Generate queue of possible inputs
        Queue<Integer> input = new Queue<>();
        for (int i = 0; i < output.size(); i++) {
            input.enqueue(i);
        }
        // Parse output
        String debug = "";
        Stack<Integer> buffer = new Stack<>();
        while (!output.isEmpty()) {
            while (!input.isEmpty() && output.peek() >= input.peek()) {
                // StdOut.println("  push " + input.peek());
                debug += input.peek() + " ";
                buffer.push(input.dequeue());
            }
            if (output.peek() == buffer.peek()) {
                int tmp = buffer.pop();
                // StdOut.println("  pop  " + tmp);
                debug += "- ";
            }
            output.dequeue();
        }
        // Output is valid if buffer is empty
        if (buffer.isEmpty()) {
            // Sanity check
            StdOut.printf("%12s: \"%s\" from \"%s\"\n", "Verify", testClient(debug), debug);
            return true;
        }
        else {
            return false;
        }
    }

    public static String testClient(String input) {
        String[] items = input.split("\\s+");
        Stack<String> s = new Stack<>();
        String output = "";
        for (String item : items) {
            if (!item.equals("-"))
                s.push(item);
            else if (!s.isEmpty())
                output += s.pop() + " ";
        }
        return output;
    }

    public static void main(String[] args) {
        // Test underflow
        String in = "0 - 1 2 - - 3 4 5 - - - - 6 7 8 9";
        StdOut.printf("willUnderflow(\"%s\") = %s\n", in, willUnderflow(in));
        in = "0 - 1 2 - - 3 4 5 - - - 6 7 8 9 - - - -";
        StdOut.printf("willUnderflow(\"%s\") = %s\n", in, willUnderflow(in));
        StdOut.println();

        // Valid outputs
        String[] outs = new String[10];
        int n = 10;
        for (int i = 0; i < 6; i++) {
            Stack<Integer> s = new Stack<>();
            String out = "";
            for (int j = 0; j < n; j++) {
                if (!s.isEmpty() && StdRandom.uniform() > 0.5) {
                    out += s.pop() + " ";
                    j--;
                }
                else {
                    s.push(j);
                }
            }
            while (!s.isEmpty()) {
                out += s.pop() + " ";
            }
            outs[i] = out;
        }
        // Invalid outputs
        outs[6] = "2 0 1 3 4 5 6 7 8 9";
        outs[7] = "0 1 2 3 4 5 6 9 7 8";
        outs[8] = "9 7 8 6 5 4 3 2 1 0";
        outs[9] = "9 8 7 6 5 4 3 2 0 1";

        // Check if outputs are valid
        for (String s : outs) {
            StdOut.printf("isValidOutput(\"%s\") = %s\n", s, isValidOutput(s));
        }
    }
}
