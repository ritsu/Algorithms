package net.qiguang.algorithms.C2_Sorting.S4_PriorityQueues;

import java.util.NoSuchElementException;
import java.util.Random;

/**
 * 2.4.13 Describe a way to avoid the j < N test in sink().
 *
 * Answer: See code
 */
public class Exercise_2_4_13 {
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
            while (2*k <= n - 1) {
                int j = 2*k;
                if (less(j, j+1)) j++;
                if (!less(k, j)) break;
                exch(k, j);
                k = j;
            }
            if (2*k == n && less(k, 2*k))
                exch(k, 2*k);
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
        MaxPQ<Integer> pq = new MaxPQ<Integer>();
        Random r = new Random();
        for (int t = 0; t < 1000; t++) {
            for (int i = 0; i < 64; i++)
                pq.insert(r.nextInt());
            Integer prev = null;
            while (!pq.isEmpty()) {
                int cur = pq.delMax();
                if (prev != null && cur > prev)
                    System.out.println("Error");
                //System.out.printf("%d ", cur);
                prev = cur;
            }
            //System.out.println();
        }
    }
}
