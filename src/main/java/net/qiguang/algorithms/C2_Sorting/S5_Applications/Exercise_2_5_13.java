package net.qiguang.algorithms.C2_Sorting.S5_Applications;

import edu.princeton.cs.algs4.MaxPQ;
import edu.princeton.cs.algs4.MinPQ;

import java.util.ArrayDeque;
import java.util.Random;

/**
 * 2.5.13 Load balancing.
 * Write a program LPT.java that takes an integer M as a command-line argument, reads job names and
 * processing times from standard input and prints a schedule assigning the jobs to M processors that
 * approximately minimizes the time when the last job completes using the longest processing time first
 * rule, as described on page 349.
 */
public class Exercise_2_5_13 {
    private static class Processor implements Comparable<Processor> {
        ArrayDeque<Job> jobs;
        String name;

        public Processor(String name) {
            jobs = new ArrayDeque<>();
            this.name = name;
        }
        public void add(Job j) {
            jobs.addLast(j);
        }
        public void process() {
            if (jobs.isEmpty()) return;
            Job j = jobs.peekFirst();
            j.time--;
            if (j.time <= 0) {
                jobs.removeFirst();
            }
        }
        public int getTime() {
            int t = 0;
            for (Job j : jobs) {
                t += j.time;
            }
            return t;
        }
        public boolean isAvailable() {
            return getTime() == 0;
        }
        public int compareTo(Processor that) {
            return this.getTime() - that.getTime();
        }
    }
    public static class LPT {
        private MaxPQ<Job> jobs;
        private MinPQ<Processor> procs;

        public LPT() {
            jobs = new MaxPQ<>();
            procs = new MinPQ<>();
        }

        public void addJob(Job j) {
            jobs.insert(j);
        }
        public void addProc(Processor p) {
            procs.insert(p);
        }
        public void schedule() {
            if (jobs.isEmpty()) return;
            Job j = jobs.delMax();
            Processor minp = procs.min();
            while (!minp.isAvailable()) {
                for (Processor p : procs) {
                    p.process();
                }
                minp = procs.min();
            }
            minp = procs.delMin();
            minp.add(j);
            procs.insert(minp);
        }
        public boolean isEmpty() {
            return jobs.isEmpty();
        }
        public void simulateProcs() {
            for (Processor p : procs) {
                p.process();
            }
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
        // Setup
        LPT lpt = new LPT();
        Processor[] procs = {
                new Processor("A"),
                new Processor("B"),
                new Processor("C"),
                new Processor("D")};
        for (Processor p : procs) {
            lpt.addProc(p);
        }

        // Add jobs
        Random r = new Random();
        for (int i = 0; i < 100; i++) {
            lpt.addJob(new Job("Job_" + String.format("%02d", i), r.nextInt(99)+1));
        }

        // Perform scheduling
        while (!lpt.isEmpty()) {
            lpt.schedule();
            StringBuilder sb = new StringBuilder();
            for (Processor p : procs) {
                String status = p.isAvailable() ? "" : "Busy";
                String s = String.format("%s: %4s %2d", p.name, status, p.getTime());
                sb.append(s).append(" | ");
            }
            System.out.println(sb.toString());
        }
    }
}
