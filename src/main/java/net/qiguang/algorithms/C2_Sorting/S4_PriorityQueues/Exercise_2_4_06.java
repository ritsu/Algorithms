package net.qiguang.algorithms.C2_Sorting.S4_PriorityQueues;

import java.util.Arrays;
import java.util.NoSuchElementException;

/**
 * 2.4.6 Using the conventions of Exercise 2.4.1, give the sequence of heaps produced when the operations
 * P R I O * R * * I * T * Y * * * Q U E * * * U * E are performed on an initially empty max-oriented heap.
 *
 * Answer:
 *  In 0 1 2 3 4 5 6 7 8 9
 *   p   P
 *   R   R P
 *   I   R P I
 *   O   R P I O
 *   *   P O I
 *   R   R P I O
 *   *   P O I
 *   *   O I
 *   I   O I I
 *   *   I I
 *   T   T I I
 *   *   I I
 *   Y   Y I I
 *   *   I I
 *   *   I
 *   *
 *   Q   Q
 *   U   U Q
 *   E   U Q E
 *   *   Q E
 *   *   E
 *   *
 *   U   U
 *   *
 *   E   E
 */
public class Exercise_2_4_06 {
    // For verification
    private static class MaxPQ<Key extends Comparable<Key>> {
        Key[] pq;
        int n;
        public MaxPQ() {
            pq = (Key[]) new Comparable[2];
            n = 0;
        }
        public void insert(Key item) {
            if (n >= pq.length-1)
                resize(2*pq.length);
            pq[++n] = item;
            swim(n);
        }
        public Key delMax() {
            if (isEmpty())
                throw new NoSuchElementException("Priority queue underflow");
            Key max = pq[1];
            exch(1, n--);
            sink(1);
            pq[n+1] = null;
            if (n > 0 && n <= (pq.length-1)/4)
                resize(pq.length/2);
            return max;
        }
        public boolean isEmpty() {
            return n == 0;
        }
        public int size() {
            return n;
        }
        private void swim(int k) {
            while (k > 1 && less(k/2, k)) {
                exch(k/2, k);
                k /= 2;
            }
        }
        private void sink(int k) {
            while (2*k <= n) {
                int j = 2*k;
                if (j < n && less(j, j+1)) j++;
                if (!less(k, j)) break;
                exch(k, j);
                k = j;
            }
        }
        private void exch(int a, int b) {
            Key swap = pq[a];
            pq[a] = pq[b];
            pq[b] = swap;
        }
        private boolean less(int a, int b) {
            return pq[a].compareTo(pq[b]) < 0;
        }
        private void resize(int size) {
            Key[] newpq = (Key[]) new Comparable[size];
            for (int i = 1; i <= n; i++)
                newpq[i] = pq[i];
            pq = newpq;
        }
    }
    public static void main(String[] args) {
        MaxPQ<String> pq = new MaxPQ<String>();
        String[] input = "P R I O * R * * I * T * Y * * * Q U E * * * U * E".split("\\s+");
        StringBuilder sb = new StringBuilder();
        for (String s : input) {
            if (s.equals("*"))
                sb.append(pq.delMax()).append(" ");
            else
                pq.insert(s);
            System.out.println(Arrays.toString(pq.pq));
        }
        System.out.println(sb.toString());
    }
}
