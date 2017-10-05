package net.qiguang.algorithms.C2_Sorting.S4_PriorityQueues;

import net.qiguang.algorithms.P3_CollinearPoints.Point;

/**
 * 2.4.3 Provide priority-queue implementations that support insert and remove the maximum, one for each of the
 * following underlying data structures: unordered array, ordered array, unordered linked list, and linked list.
 * Give a table of the worst-case bounds for each operation for each of your four implementations.
 *
 * Worst case:             insert() delMax()
 *         Unordered Array       1        N
 *           Ordered Array       N        1
 *   Unordered Linked List       1        N
 *     Ordered Linked List       N        1
 */
public class Exercise_2_4_03 {
    // Unordered array
    private static class MaxPQUA<Key extends Comparable<Key>> {
        Key[] pq;
        int n;
        public MaxPQUA() {
            pq = (Key[]) new Comparable[2];
            n = 0;
        }
        public Key delMax() {
            int max = 0;
            for (int i = 1; i < n; i++) {
                if (less(max, i))
                    max = i;
            }
            exch(max, --n);
            Key item = pq[n];
            if (n > 1 && pq.length == n*4)
                resize(n/2);
            return item;
        }
        public void insert(Key item) {
            if (pq.length == n)
                resize(n*2);
            pq[n++] = item;
        }
        private void resize(int size) {
            Key[] copy = (Key[]) new Comparable[size];
            for (int i = 0; i < n; i++) {
                copy[i] = pq[i];
            }
            pq = copy;
        }
        private boolean less(int i, int j) {
            return pq[i].compareTo(pq[j]) < 0;
        }
        private void exch(int i, int j) {
            Key swap = pq[i];
            pq[i] = pq[j];
            pq[j] = swap;
        }
    }
    // Ordered array
    private static class MaxPQOA<Key extends Comparable<Key>> {
        Key[] pq;
        int n;
        public MaxPQOA() {
            pq = (Key[]) new Comparable[2];
            n = 0;
        }
        public void insert(Key item) {
            if (pq.length == n)
                resize(n*2);
            pq[n++] = item;
            for (int i = n-1; i > 0 && less(i, i-1); i--) {
                exch(i, i-1);
            }
        }
        public Key delMax() {
            Key item = pq[--n];
            if (n > 1 && pq.length == n*4)
                resize(n/2);
            return item;
        }
        private void resize(int size) {
            Key[] copy = (Key[]) new Comparable[size];
            for (int i = 0; i < n; i++) {
                copy[i] = pq[i];
            }
            pq = copy;
        }
        private boolean less(int i, int j) {
            return pq[i].compareTo(pq[j]) < 0;
        }
        private void exch(int i, int j) {
            Key swap = pq[i];
            pq[i] = pq[j];
            pq[j] = swap;
        }
    }
    // Unordered linked list
    private static class MaxPQULL<Key extends Comparable<Key>> {
        Node<Key> first;
        Node<Key> last;
        int n;
        class Node<Key> {
            Key val;
            Node next;
        }
        public MaxPQULL() {
            n = 0;
        }
        public void insert(Key item) {
            Node oldLast = last;
            last = new Node<Key>();
            last.val = item;
            if (oldLast != null)
                oldLast.next = last;
            if (first == null)
                first = last;
            n++;
        }
        public Key delMax() {
            if (first == null) return null;
            Node<Key> max = first;
            Node<Key> prev = null;
            Node current = first;
            while (current.next != null) {
                if (less(max, current.next)) {
                    max = current.next;
                    prev = current;
                }
                current = current.next;
            }
            Key item = max.val;
            if (max == last)
                last = prev;
            if (prev == null)
                first = max.next;
            else
                prev.next = prev.next.next;
            n--;
            return item;
        }
        private boolean less(Node<Key> a, Node<Key> b) {
            return a.val.compareTo(b.val) < 0;
        }
    }
    private static class MaxPQLL<Key extends Comparable<Key>> {
        Node<Key> first;
        Node<Key> last;
        int n;
        class Node<Key> {
            Key val;
            Node next;
        }
        public MaxPQLL() {
            n = 0;
        }
        public void insert(Key item) {
            Node<Key> newNode = new Node<Key>();
            newNode.val = item;
            if (first == null) {
                first = newNode;
                last = first;
                return;
            }
            if (less(first, newNode)) {
                newNode.next = first;
                first = newNode;
                return;
            }
            Node<Key> current = first;
            while(current.next != null && less(newNode, current.next))
                current = current.next;
            newNode.next = current.next;
            current.next = newNode;
            if (current == last)
                last = newNode;
        }
        public Key delMax() {
            if (first == null) return null;
            Key item = first.val;
            first = first.next;
            return item;
        }
        private boolean less(Node<Key> a, Node<Key> b) {
            return a.val.compareTo(b.val) < 0;
        }
    }
    public static void main(String[] args) {
        MaxPQUA<String> pqua = new MaxPQUA<String>();
        MaxPQOA<String> pqoa = new MaxPQOA<String>();
        MaxPQULL<String> pqull = new MaxPQULL<String>();
        MaxPQLL<String> pqll = new MaxPQLL<String>();
        StringBuilder sbua = new StringBuilder();
        StringBuilder sboa = new StringBuilder();
        StringBuilder sbull = new StringBuilder();
        StringBuilder sbll = new StringBuilder();
        String[] input = "P R I O * R * * I * T * Y * * * Q U E * * * U * E".split("\\s+");
        for (String s : input) {
            if (s.equals("*")) {
                sbua.append(pqua.delMax()).append(" ");
                sboa.append(pqoa.delMax()).append(" ");
                sbull.append(pqull.delMax()).append(" ");
                sbll.append(pqll.delMax()).append(" ");
            } else {
                pqua.insert(s);
                pqoa.insert(s);
                pqull.insert(s);
                pqll.insert(s);
            }
        }
        System.out.println(sbua.toString());
        System.out.println(sboa.toString());
        System.out.println(sbull.toString());
        System.out.println(sbll.toString());
    }
}
