package net.qiguang.algorithms.P2_DequesAndRandomizedQueues;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import net.qiguang.algorithms.P2_DequesAndRandomizedQueues.RandomizedQueue;

public class TestRandomizedQueue {
    public TestRandomizedQueue() {
    }
    
    // Dequeu in random order
    public void dequeue() {
        StdOut.println("Dequeue in random order:");
        RandomizedQueue<Integer> r = new RandomizedQueue<Integer>();
        for (int i = 0; i < 10; i++) {
            r.enqueue(i);
        }
        while (!r.isEmpty()) {
            StdOut.print(r.dequeue());
        }
        StdOut.println();
        StdOut.println("Size of queue: " + r.size());
    }
    
    // Sample but do not dequeue
    public void sample() {
        StdOut.println("Sample items 3 x 10 times");
        RandomizedQueue<Integer> r = new RandomizedQueue<Integer>();
        for (int i = 0; i < 10; i++) {
            r.enqueue(i);
        }
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 10; j++) {
                StdOut.print(r.sample());
            }
            StdOut.println();
        }
        StdOut.println("Size of queue: " + r.size());
    }
    
    // Iterator test
    public void iterate() {
        StdOut.println("Iterator:");
        RandomizedQueue<Integer> r = new RandomizedQueue<Integer>();
        for (int i = 0; i < 10; i++) {
            r.enqueue(i);
        }
        for (Integer i : r) {
            StdOut.println("Outer: " + i);
            StdOut.print("Inner: ");
            for (Integer j : r) {
                StdOut.print(j + " ");
            }
            StdOut.println();
        }
    }

    // Catch errors
    public void errors() {
        RandomizedQueue<Integer> r = new RandomizedQueue<Integer>();
        
        StdOut.println("enqueue(null):");
        try {
            r.enqueue(null);
        }
        catch (java.lang.NullPointerException e) {
            StdOut.println(e);
        }

        StdOut.println("dequeue from empty queue:");
        try {
            r.dequeue();
        }
        catch (java.util.NoSuchElementException e) {
            StdOut.println(e);
        }
        StdOut.println("sample from empty queue:");
        try {
            r.sample();
        }
        catch (java.util.NoSuchElementException e) {
            StdOut.println(e);
        }
    }
        
    public static void main(String[] args) {
        TestRandomizedQueue t = new TestRandomizedQueue();
        t.dequeue();
        t.sample();
        t.iterate();
        t.errors();
    }
    
}