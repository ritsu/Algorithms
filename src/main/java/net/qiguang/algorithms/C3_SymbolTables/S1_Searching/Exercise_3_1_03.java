package net.qiguang.algorithms.C3_SymbolTables.S1_Searching;

import java.util.ArrayDeque;
import java.util.Random;

/**
 * 3.1.3 Develop a symbol-table implementation OrderedSequentialSearchST that uses an ordered linked list
 * as the underlying data structure to implement our ordered symbol-table API.
 */
public class Exercise_3_1_03 {
    private static class OrderedSequentialSearchST<Key extends Comparable<Key>, Value> {
        Node first;
        int size;

        private class Node {
            Key key;
            Value value;
            Node next;
            public Node(Key key, Value value) {
                this.key = key;
                this.value = value;
                next = null;
            }
        }

        public OrderedSequentialSearchST() {
            first = null;
            size = 0;
        }
        public void put(Key key, Value value) {
            if (value == null) {
                delete(key);
            }
            if (first == null) {
                first = new Node(key, value);
                size++;
                return;
            }
            Node current = first;
            while (current.next != null && current.next.key.compareTo(key) < 0) {
                current = current.next;
            }
            if (current.key.compareTo(key) == 0) {
                current.value = value;
                return;
            }
            Node node = new Node(key, value);
            node.next = current.next;
            current.next = node;
            size++;
        }
        public Value get(Key key) {
            Node current = first;
            while (current != null) {
                if (current.key.equals(key)) {
                    return current.value;
                }
                current = current.next;
            }
            return null;
        }
        public void delete(Key key) {
            if (first == null) {
                return;
            }
            if (first.key.compareTo(key) == 0) {
                first = first.next;
                size--;
                return;
            }
            Node current = first;
            while (current.next != null) {
                if (current.key.compareTo(key) == 0) {
                    current.next = current.next.next;
                    size--;
                    return;
                }
                current = current.next;
            }
        }
        public boolean contains(Key key) {
            Node current = first;
            while (current != null && current.key.compareTo(key) <= 0) {
                if (current.key.compareTo(key) == 0)
                    return true;
                current = current.next;
            }
            return false;
        }
        public boolean isEmpty() {
            return size == 0;
        }
        public int size() {
            return size;
        }
        public Key min() {
            if (first == null)
                return null;
            return first.key;
        }
        public Key max() {
            if (first == null)
                return null;
            Node current = first;
            while (current.next != null) {
                current = current.next;
            }
            return current.key;
        }
        // Largest key less than or equal to key
        public Key floor(Key key) {
            if (first == null || first.key.compareTo(key) > 0)
                return null;
            Node current = first;
            while (current.next != null && current.next.key.compareTo(key) <= 0) {
                current = current.next;
            }
            return current.key;
        }
        // Smallest key greater than or equal to key
        public Key ceiling(Key key) {
            if (first == null)
                return null;
            Node current = first;
            while (current != null && current.key.compareTo(key) < 0) {
                current = current.next;
            }
            if (current == null)
                return null;
            return current.key;
        }
        // Number of keys less than key
        public int rank(Key key) {
            if (first == null)
                return 0;
            int r = 0;
            Node current = first;
            while (current != null && current.key.compareTo(key) < 0) {
                r++;
                current = current.next;
            }
            return r;
        }
        public Key select(int k) {
            if (first == null)
                return null;
            Node current = first;
            while (k > 0) {
                current = current.next;
                if (current == null)
                    return null;
                k--;
            }
            return current.key;
        }
        public void deleteMin() {
            if (first == null)
                return;
            first = first.next;
            size--;
        }
        public void deleteMax() {
            if (first == null)
                return;
            Node current = first;
            while (current.next != null) {
                current = current.next;
            }
            current.next = null;
            size--;
        }
        public int size(Key lo, Key hi) {
            if (first == null)
                return 0;
            Node current = first;
            while (current != null && current.key.compareTo(lo) < 0) {
                current = current.next;
            }
            if (current == null)
                return 0;
            int s = 0;
            while (current != null && current.key.compareTo(hi) <= 0) {
                s++;
                current = current.next;
            }
            return s;
        }
        public Iterable<Key> keys(Key lo, Key hi) {
            ArrayDeque<Key> deque = new ArrayDeque<>();
            if (first == null)
                return deque;
            Node current = first;
            while (current != null && current.key.compareTo(lo) < 0) {
                current = current.next;
            }
            while (current != null && current.key.compareTo(hi) <= 0) {
                deque.add(current.key);
                current = current.next;
            }
            return deque;
        }
        public Iterable<Key> keys() {
            return keys(select(0), select(this.size - 1));
        }

    }
    public static void main(String[] args) {
        OrderedSequentialSearchST<Integer, Integer> st = new OrderedSequentialSearchST<>();
        int n = 20;
        Random r = new Random();
        for (int i = 0; i < n; i++) {
            st.put(i, 100 + i);
        }
        for (int i : st.keys()) {
            System.out.printf("%4d %12d\n", i, st.get(i));
        }
        System.out.printf("Contains %d: %s\n", n-1, st.contains(n-1));
        System.out.printf("Contains %d: %s\n", n, st.contains(n));

        System.out.printf("IsEmpty: %s\n", st.isEmpty());
        System.out.printf("Size: %d\n", st.size());
        System.out.printf("Min: %d\n", st.min());
        System.out.printf("Max: %d\n", st.max());

        // Edge cases
        int[] nums = new int[4];
        nums[0] = -1;
        nums[1] = 0;
        nums[2] = n - 1;
        nums[3] = n;
        for (int num : nums) {
            System.out.printf("Floor(%d): %d\n", num, st.floor(num));
            System.out.printf("Ceiling(%d): %d\n", num, st.ceiling(num));
            System.out.printf("Rank(%d): %d\n", num, st.rank(num));
        }
        int i = 0;
        while (!st.isEmpty()) {
            if (i++ % 2 == 0)
                st.deleteMax();
            else
                st.deleteMin();
            for (int j : st.keys()) {
                System.out.printf("%3d", j);
            }
            System.out.println();
        }
        System.out.printf("IsEmpty: %s\n", st.isEmpty());
    }
}
