package net.qiguang.algorithms.C1_Fundamentals.S3_BagsQueuesAndStacks;

import edu.princeton.cs.algs4.StdOut;
import java.util.Iterator;

/**
 * 1.3.7
 * Add a method peek() to Stack that returns the most recently inserted item on the stack (without popping it).
 */
public class Exercise_1_3_07 {
    public static class Stack<Item> implements Iterable<Item> {
        private Node first;
        private int N;
        private  class Node {
            Item item;
            Node next;
        }
        public boolean isEmpty() {  return first == null; }  // Or: N == 0.
        public int size()        {  return N; }
        public void push(Item item)   {
            // Add item to top of stack.
            Node oldfirst = first;
            first = new Node();
            first.item = item;
            first.next = oldfirst;
            N++;
        }
        public Item pop() {
            // Remove item from top of stack.
            Item item = first.item;
            first = first.next;
            N--;
            return item;
        }
        public Iterator<Item> iterator()   {  return new ListIterator();  }
        private class ListIterator implements Iterator<Item>   {
            private Node current = first;
            public boolean hasNext() {
                return current != null;
            }
            public void remove() {}
            public Item next() {
                Item item = current.item;
                current = current.next;
                return item;
            }
        }
        public Item peek() {
            return first.item;
        }
    }
    public static void main(String[] args) {
        Stack<Integer> s = new Stack<Integer>();
        for (int i = 0; i < 10; i++) {
            s.push(i);
        }
        StdOut.println(s.peek().toString());
    }
}
