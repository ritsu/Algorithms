package net.qiguang.algorithms.C1_Fundamentals.S4_AnalysisOfAlgorithms;

import edu.princeton.cs.algs4.ResizingArrayStack;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.Stopwatch;

/**
 * 1.4.43 Resizing arrays versus linked lists.
 * Run experiments to validate the hypothesis that resizing arrays are faster than linked lists for stacks
 * (see Exercise 1.4.35 and Exercise 1.4.36). Do so by developing a version of DoublingRatio that computes the ratio
 * of the running times of the two programs.
 */
public class Exercise_1_4_43 {
    public static class DoublingRatio {
        private static final int MAXIMUM_INTEGER = 1000000;
        private DoublingRatio() { }
        public static double timeTrial(int n, int runs, String type) {
            Stopwatch timer = new Stopwatch();
            if (type == "list") {
                Stack<Integer> s = new Stack();
                while (--runs >= 0) {
                    for (int i = 0; i < n; i++)
                        s.push(i);
                    while (!s.isEmpty())
                        s.pop();
                }
            }
            else {
                ResizingArrayStack s = new ResizingArrayStack();
                while (--runs >= 0) {
                    for (int i = 0; i < n; i++)
                        s.push(i);
                    while (!s.isEmpty())
                        s.pop();
                }
            }
            return timer.elapsedTime();
        }
    }

    public static void main(String[] args) {
        int init = 10000;
        int runs = 1000;
        System.out.printf("%7s %5s %5s %10s\n", "size", "list", "array", "list/array");
        for (int n = init; true; n += n) {
            double time_list = DoublingRatio.timeTrial(n, runs, "list");
            double time_array = DoublingRatio.timeTrial(n, runs, "array");
            System.out.printf("%7d %5.1f %5.1f %10.1f\n", n, time_list, time_array, time_list/time_array);
        }
    }
}
