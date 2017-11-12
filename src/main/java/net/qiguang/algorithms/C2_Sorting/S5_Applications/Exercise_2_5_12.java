package net.qiguang.algorithms.C2_Sorting.S5_Applications;

import edu.princeton.cs.algs4.MinPQ;

import java.util.Random;

/**
 * 2.5.12 Scheduling.
 * Write a program SPT.java that reads job names and processing times from standard input and prints a
 * schedule that minimizes average completion time using the shortest processing time first rule,
 * as described on page 349.
 */
public class Exercise_2_5_12 {
    public static class SPT {
        private MinPQ<Job> mpq;

        public SPT() {
            mpq = new MinPQ<Job>();
        }

        public void add(Job j) {
            mpq.insert(j);
        }
        public void printSchedule() {
            StringBuilder sb = new StringBuilder();
            for (Job j : mpq) {
                sb.append(j).append("\n");
            }
            System.out.printf(sb.toString());
        }
    }
    public static class Job implements Comparable<Job> {
        String name;
        int time;
        public Job(String name, int time) {
            this.name = name;
            this.time = time;
        }
        public int compareTo(Job that) {
            return this.time - that.time;
        }
        public String toString() {
            return String.format("%12s %12d", name, time);
        }
    }

    public static void main(String[] args) {
        SPT s = new SPT();
        Random r = new Random();
        for (int i = 0; i < 100; i++) {
            s.add(new Job("Job_" + String.format("%02d", i), r.nextInt(100)));
        }
        s.printSchedule();
    }
}
