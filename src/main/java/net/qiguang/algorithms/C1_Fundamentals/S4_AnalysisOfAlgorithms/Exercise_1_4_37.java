package net.qiguang.algorithms.C1_Fundamentals.S4_AnalysisOfAlgorithms;

import edu.princeton.cs.algs4.Stopwatch;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * 1.4.37 Autoboxing performance penalty.
 * Run experiments to determine the performance penalty on your machine for using autoboxing and auto-unboxing.
 * Develop an implementation FixedCapacityStackOfInts and use a client such as DoublingRatio to compare its
 * performance with the generic FixedCapacityStack<Integer>, for a large number of push() and pop() operations.
 */
public class Exercise_1_4_37 {
    public static class FixedCapacityStackOfInts implements Iterable<Integer> {
        private int[] a;  // holds the items
        private int N;    // number of items in stack

        // create an empty stack with given capacity
        public FixedCapacityStackOfInts(int capacity) {
            a = new int[capacity];
            N = 0;
        }

        public boolean isEmpty()            {  return N == 0;                    }
        public boolean isFull()             {  return N == a.length;             }
        public void push(int item)          {  a[N++] = item;                    }
        public int pop()                    {  return a[--N];                    }
        public int peek()                   {  return a[N-1];                    }
        public Iterator<Integer> iterator() { return new ReverseArrayIterator(); }


        public class ReverseArrayIterator implements Iterator<Integer> {
            private int i = N-1;

            public boolean hasNext() {
                return i >= 0;
            }

            public Integer next() {
                if (!hasNext()) throw new NoSuchElementException();
                return a[i--];
            }

            public void remove() {
                throw new UnsupportedOperationException();
            }
        }
    }
    public static class FixedCapacityStack<Item> implements Iterable<Item> {
        private Item[] a;    // holds the items
        private int N;       // number of items in stack

        // create an empty stack with given capacity
        public FixedCapacityStack(int capacity) {
            a = (Item[]) new Object[capacity];   // no generic array creation
            N = 0;
        }

        public boolean isEmpty() {
            return N == 0;
        }

        public void push(Item item) {
            a[N++] = item;
        }

        public Item pop() {
            return a[--N];
        }

        public Iterator<Item> iterator() {
            return new ReverseArrayIterator();
        }


        public class ReverseArrayIterator implements Iterator<Item> {
            private int i = N - 1;

            public boolean hasNext() {
                return i >= 0;
            }

            public Item next() {
                if (!hasNext()) throw new NoSuchElementException();
                return a[i--];
            }

            public void remove() {
                throw new UnsupportedOperationException();
            }
        }
    }
    public static void main(String[] args) {
        int size = 100000;
        int runs = 10000;
        FixedCapacityStackOfInts s1 = new FixedCapacityStackOfInts(size);
        FixedCapacityStack<Integer> s2 = new FixedCapacityStack<Integer>(size);

        Stopwatch s = new Stopwatch();
        for (int i = 0; i < runs; i++) {
            for (int j = 0; j < size; j++) {
                s1.push(j);
            }
            while (!s1.isEmpty()) {
                s1.pop();
            }
        }
        System.out.printf("%26s: %6.3f\n", "FixedCapacityStackOfInts", s.elapsedTime());

        s = new Stopwatch();
        for (int i = 0; i < runs; i++) {
            for (int j = 0; j < size; j++) {
                s2.push(j);
            }
            while (!s2.isEmpty()) {
                s2.pop();
            }
        }
        System.out.printf("%26s: %6.3f\n", "FixedCapacityStack", s.elapsedTime());

    }

}
