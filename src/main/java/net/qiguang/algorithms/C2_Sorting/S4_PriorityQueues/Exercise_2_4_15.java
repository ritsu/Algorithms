package net.qiguang.algorithms.C2_Sorting.S4_PriorityQueues;

import java.util.NoSuchElementException;
import java.util.Random;

/**
 * 2.4.15 Design a linear-time certification algorithm to check whether an array pq[] is a min-oriented heap.
 */
public class Exercise_2_4_15 {
    private static class MinPQ<Key extends Comparable<Key>> {
        Key[] pq;
        int n;
        public MinPQ() {
            pq = (Key[]) new Comparable[2];
            n = 0;
        }
        public void insert(Key item) {
            if (n >= pq.length-1)
                resize(2*pq.length);
            pq[++n] = item;
            swim(n);
            if (!isMinHeap()) System.out.println("Error");
        }
        public Key delMin() {
            if (isEmpty())
                throw new NoSuchElementException("Priority queue underflow");
            Key min = pq[1];
            exch(1, n--);
            sink(1);
            pq[n+1] = null;
            if (n > 0 && n <= (pq.length-1)/4)
                resize(pq.length/2);
            if (!isMinHeap()) System.out.println("Error");
            return min;
        }
        public boolean isEmpty() {
            return n == 0;
        }
        public int size() {
            return n;
        }
        private void swim(int k) {
            while (k > 1 && less(k, k/2)) {
                exch(k/2, k);
                k /= 2;
            }
        }
        private void sink(int k) {
            while (2*k <= n) {
                int j = 2*k;
                if (j < n && less(j+1, j)) j++;
                if (less(k, j)) break;
                exch(k, j);
                k = j;
            }
        }
        private boolean isMinHeap() {
            return isMinHeap(1);
        }
        private boolean isMinHeap(int k) {
            if (k > n) return true;
            if (k*2   <= n && less(k*2,   k)) return false;
            if (k*2+1 <= n && less(k*2+1, k)) return false;
            return isMinHeap(k*2) && isMinHeap(k*2+1);
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
        MinPQ<Integer> pq = new MinPQ<Integer>();
        Random r = new Random();
        for (int t = 0; t < 1000; t++) {
            for (int i = 0; i < 64; i++)
                pq.insert(r.nextInt());
            while (!pq.isEmpty())
                pq.delMin();
        }
    }
}
