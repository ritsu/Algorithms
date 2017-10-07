package net.qiguang.algorithms.C2_Sorting.S4_PriorityQueues;

import java.util.NoSuchElementException;
import java.util.Random;

/**
 * 2.4.19 Implement the constructor for MaxPQ that takes an array of items as argument,
 * using the bottom-up heap construction method described on page 323 in the text.
 */
public class Exercise_2_4_19 {
    private static class MaxPQ<Key extends Comparable<Key>> {
        Key[] pq;
        int n;
        public MaxPQ() {
            pq = (Key[]) new Comparable[2];
            n = 0;
        }
        public MaxPQ(Key[] keys) {
            n = keys.length;
            pq = (Key[]) new Comparable[n+1];
            for (int i = 0; i < n; i++) {
                pq[i+1] = keys[i];
            }
            for (int k = n/2; k >= 1; k--) {
                sink(k);
            }
            if (!isMaxHeap()) System.out.println("error");
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
        private boolean isMaxHeap() {
            return isMaxHeap(1);
        }
        private boolean isMaxHeap(int k) {
            if (k > n) return true;
            if (k*2   <= n && less(k, k*2))   return false;
            if (k*2+1 <= n && less(k, k*2+1)) return false;
            return isMaxHeap(k*2) && isMaxHeap(k*2+1);
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
        public String arrayString() {
            StringBuilder sb = new StringBuilder();
            for (Key k : pq) {
                if (k != null)
                    sb.append(k).append(" ");
            }
            return sb.toString();
        }
    }
    public static void main(String[] args) {
        int max = 100;
        Random r = new Random();
        for (int t = 0; t < 10; t++) {
            int len = 10 + r.nextInt(10);
            Integer[] a = new Integer[len];
            for (int i = 0; i < len; i++) {
                a[i] = r.nextInt(max);
            }
            MaxPQ<Integer> pq = new MaxPQ<Integer>(a);
            StringBuilder sb = new StringBuilder();
            while (!pq.isEmpty())
                sb.append(pq.delMax()).append(" ");
            System.out.println(sb);
        }
    }
}
