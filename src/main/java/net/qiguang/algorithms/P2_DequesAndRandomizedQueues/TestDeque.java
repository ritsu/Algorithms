package net.qiguang.algorithms.P2_DequesAndRandomizedQueues;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import net.qiguang.algorithms.P2_DequesAndRandomizedQueues.Deque;

public class TestDeque {
    public TestDeque() {
    }
    
    // Enqueue then dequeue in order
    public void echo() {
        StdOut.println("Echo - addFirst, removeLast:");
        Deque<Integer> d = new Deque<Integer>();
        for (int i = 0; i < 10; i++) {
            d.addFirst(i);
        }
        while (!d.isEmpty()) {
            StdOut.print(d.removeLast());
        }
        StdOut.println();

        StdOut.println("Echo - addLast, removeFirst:");
        for (int i = 0; i < 10; i++) {
            d.addLast(i);
        }
        while (!d.isEmpty()) {
            StdOut.print(d.removeFirst());
        }
        StdOut.println();
    }
    
    // Enqueue then dequeue in reverse order
    public void reverse() {
        StdOut.println("Reverse - addFirst, removeFirst:");
        Deque<Integer> d = new Deque<Integer>();
        for (int i = 0; i < 10; i++) {
            d.addFirst(i);
        }
        while (!d.isEmpty()) {
            StdOut.print(d.removeFirst());
        }
        StdOut.println();

        StdOut.println("Reverse - addLast, removeLast:");
        for (int i = 0; i < 10; i++) {
            d.addLast(i);
        }
        while (!d.isEmpty()) {
            StdOut.print(d.removeLast());
        }
        StdOut.println();
    }
    
    // Iterator test
    public void iterate() {
        Deque<String> d = new Deque<String>();
        d.addLast("I");
        d.addLast("hope");
        d.addLast("this");
        d.addLast("works");
        
        StdOut.println("Iterator:");
        for (String s : d) {
            StdOut.print(s + " ");
        }        
        StdOut.println();
    }
    
    /**
     * Catch the following errors:
     *    Add null item
     *    Remove from empty queue
     **/
    public void errors() {
        Deque<Integer> d = new Deque<Integer>();
        
        StdOut.println("addFirst(null):");
        try {
            d.addFirst(null);
        }
        catch (java.lang.NullPointerException e) {
            StdOut.println(e);
        }
        StdOut.println("addLast(null):");
        try {
            d.addLast(null);
        }
        catch (java.lang.NullPointerException e) {
            StdOut.println(e);
        }
        
        StdOut.println("removeFirst from empty deque:");
        try {
            d.removeFirst();
        }
        catch (java.util.NoSuchElementException e) {
            StdOut.println(e);
        }
        StdOut.println("removeLast from empty deque:");
        try {
            d.removeLast();
        }
        catch (java.util.NoSuchElementException e) {
            StdOut.println(e);
        }
    }
        
    public static void main(String[] args) {
        TestDeque t = new TestDeque();
        t.echo();
        t.reverse();
        t.iterate();
        t.errors();
    }
    
}