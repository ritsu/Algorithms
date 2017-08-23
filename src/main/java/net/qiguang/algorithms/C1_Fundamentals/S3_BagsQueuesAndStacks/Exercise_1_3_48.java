package net.qiguang.algorithms.C1_Fundamentals.S3_BagsQueuesAndStacks;

import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * 1.3.48 Two stacks with a deque.
 * Implement two stacks with a single deque so that each operation takes a constant number of
 * deque operations (see Exercise 1.3.33).
 */
public class Exercise_1_3_48 {
    public static class TwoStack<Item> {
        private Exercise_1_3_33.Deque<Item> d;
        private int sizeA;
        private int sizeB;
        public StackA<Item> a = new StackA<Item>();
        public StackB<Item> b = new StackB<Item>();

        public TwoStack() {
            d = new Exercise_1_3_33.Deque<Item>();
            sizeA = 0;
            sizeB = 0;
        }

        public class StackA<T> implements Iterable<T> {
            public void push(T item) {
                d.pushLeft((Item) item);
                sizeA++;
            }
            public T pop() {
                sizeA--;
                return (T) d.popLeft();
            }
            public int size() {
                return sizeA;
            }
            public boolean isEmpty() {
                return sizeA == 0;
            }
            public String toString() {
                String s = "";
                for (T item : this) {
                    s += item + " ";
                }
                return s;
            }
            public Iterator<T> iterator() {
                return new StackAIterator();
            }
            public class StackAIterator<T> implements Iterator<T> {
                Exercise_1_3_33.Deque.Node current;
                int n;

                public StackAIterator() {
                    current = d.first;
                    n = 0;
                }
                public boolean hasNext() {
                    return n < sizeA;
                }
                public T next() {
                    if (current == null)
                        throw new NoSuchElementException("Cannot call next() on last item in deque");
                    T item = (T) current.item;
                    current = current.next;
                    n++;
                    return item;
                }
                public void remove() {
                    throw new UnsupportedOperationException("Cannot remove in iterator");
                }
            }
        }

        public class StackB<T> implements Iterable<T> {
            public void push(T item) {
                d.pushRight((Item) item);
                sizeB++;
            }
            public T pop() {
                sizeB--;
                return (T) d.popRight();
            }
            public int size() {
                return sizeB;
            }
            public boolean isEmpty() {
                return sizeB == 0;
            }
            public String toString() {
                String s = "";
                for (T item : this) {
                    s += item + " ";
                }
                return s;
            }
            public Iterator<T> iterator() {
                return new StackBIterator();
            }
            public class StackBIterator<T> implements Iterator<T> {
                Exercise_1_3_33.Deque.Node current;
                int n;

                public StackBIterator() {
                    current = d.last;
                    n = 0;
                }
                public boolean hasNext() {
                    return n < sizeB;
                }
                public T next() {
                    if (current == null)
                        throw new NoSuchElementException("Cannot call next() on last item in deque");
                    T item = (T) current.item;
                    current = current.prev;
                    n++;
                    return item;
                }
                public void remove() {
                    throw new UnsupportedOperationException("Cannot remove in iterator");
                }
            }
        }
    }

    public static void main(String[] args) {
        StdOut.println("Create new TwoStack.");
        TwoStack<Integer> t = new TwoStack<Integer>();
        StdOut.println("StackA: " + t.a);
        StdOut.println("StackB: " + t.b);
        StdOut.println("A.isEmpty() = " + t.a.isEmpty() + ", A.size() = " + t.a.size());
        StdOut.println("B.isEmpty() = " + t.b.isEmpty() + ", B.size() = " + t.b.size());

        StdOut.println("\nPush [0..9] to A, [10..19] to B.");
        for (int i = 0; i < 10; i++) {
            t.a.push(i);
            t.b.push(i + 10);
        }
        StdOut.println("StackA: " + t.a);
        StdOut.println("StackB: " + t.b);
        StdOut.println("A.isEmpty() = " + t.a.isEmpty() + ", A.size() = " + t.a.size());
        StdOut.println("B.isEmpty() = " + t.b.isEmpty() + ", B.size() = " + t.b.size());

        StdOut.println("\nPop 4 items from A, 6 items from B.");
        for (int i = 0; i < 10; i++) {
            if (i < 4) StdOut.printf("StackA: (%d) %s\n", t.a.pop(), t.a);
            else       StdOut.printf("StackB: (%d) %s\n", t.b.pop(), t.b);
        }
        StdOut.println();

        StdOut.println("StackA: " + t.a);
        StdOut.println("StackB: " + t.b);
        StdOut.println("A.isEmpty() = " + t.a.isEmpty() + ", A.size() = " + t.a.size());
        StdOut.println("B.isEmpty() = " + t.b.isEmpty() + ", B.size() = " + t.b.size());
    }
}
