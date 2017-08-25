package net.qiguang.algorithms.C1_Fundamentals.S3_BagsQueuesAndStacks;

import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;

/**
 * 1.3.49 Queue with three stacks.
 * Implement a queue with three stacks so that each queue operation takes a constant (worst-case)
 * number of stack operations. Warning: high degree of difficulty.
 */
public class Exercise_1_3_49 {
    /**
     * Stack<Stack> node: Acts like a linked list node and contains one of the following:
     *
     *                    Stack of size 2:
     *                        Stack<Stack> link to next node
     *                        Stack<Item>  containing a single item
     *                    Stack of size 1:
     *                        Stack<Item>  containing a single item
     *
     * Stack<Stack> first: Always contains the first (oldest) item enqueued. When an item is dequeued,
     *                     the item contained in first is returned, and first is updated to the node
     *                     containing the next oldest item.
     *
     * Stack<Item> stackitem: A container stack for simply storing a single item.
     */
    public static class ThreeStackQueue<Item> implements Iterable<Item> {
        Stack<Stack> node;
        Stack<Stack> first;
        Stack<Item>  stackitem;
        int n;

        public ThreeStackQueue() {
            node = new Stack<Stack>();
            first = new Stack<Stack>();
        }
        public void enqueue(Item item) {
            stackitem = new Stack<Item>();
            stackitem.push(item);
            node.push(new Stack<Stack>());
            node = node.peek();
            node.push(stackitem);
            if (isEmpty()) first = node;
            n++;
        }
        public Item dequeue() {
            if (first.size() == 2) {
                node.push(first.pop());
                stackitem = first.pop();
                first = node.pop();
            }
            else {
                stackitem = first.pop();
            }
            n--;
            return stackitem.pop();
        }
        public boolean isEmpty() {
            return first.isEmpty();
        }
        public int size() {
            return n;
        }
        public String toString() {
            String s = "";
            if (isEmpty()) return s;
            for (Item item : this) {
                s += item + " ";
            }
            return s;
        }

        public Iterator iterator() {
            return new ThreeStackQueueIterator();
        }
        public class ThreeStackQueueIterator implements Iterator {
            Stack<Stack> current;

            public ThreeStackQueueIterator() {
                current = first;
            }
            public boolean hasNext() {
                return current != null;
            }
            public Item next() {
                if (current.size() == 2) {
                    Stack<Stack> s = current.pop();
                    stackitem = current.peek();
                    current.push(s);
                    current = s;
                }
                else {
                    stackitem = current.peek();
                    current = null;
                }
                return stackitem.peek();
            }
            public void remove() {
                throw new UnsupportedOperationException("Cannot remove in iterator");
            }
        }
    }

    public static void main(String[] args) {
        ThreeStackQueue<Integer> q = new ThreeStackQueue<Integer>();

        StdOut.println("enqueue([0..9]):");
        for (int i = 0; i < 10; i++) {
            q.enqueue(i);
            StdOut.println(q);
        }
        StdOut.printf("size() = %d, isEmpty() = %s\n\n", q.size(), q.isEmpty());

        StdOut.println("dequeue():");
        while (!q.isEmpty()) {
            StdOut.printf("(%d) %s\n", q.dequeue(), q);
        }
        StdOut.printf("size() = %d, isEmpty() = %s\n", q.size(), q.isEmpty());
    }
}
