package net.qiguang.algorithms.C2_Sorting.S2_Mergesort;

import java.util.Random;

/**
 * 2.2.18 Shuffling a linked list.
 * Develop and implement a divide-and-conquer algorithm that randomly shuffles a linked list
 * in linearithmic time and logarithmic extra space.
 */
public class Exercise_2_2_18 {
    public static class Node {
        Comparable val;
        Node next;
        Node(Comparable x) { val = x; }
        public String toString() {
            Node current = this;
            StringBuilder sb = new StringBuilder();
            int i = 0;
            while (current != null) {
                sb.append(current.val + " ");
                current = current.next;
                if (i++ > 20) break;
            }
            return sb.toString();
        }
    }

    // Return n-th next node
    public static Node next(Node node, int n) {
        if (n == 0) return node;
        while (n-- > 0)
            node = node.next;
        return node;
    }

    public static Node shuffle(Node head) {
        if (head.next == null) return head;

        // Split into two lists
        Node a = head;
        Node b = head.next;
        Node curA = a;
        Node curB = b;
        Node current = b.next;
        while (current != null) {
            curA.next = current;
            current = current.next;
            curA = curA.next;
            if (current != null) {
                curB.next = current;
                current = current.next;
                curB = curB.next;
            }
        }
        curA.next = null;
        curB.next = null;

        a = shuffle(a);
        b = shuffle(b);

        // Merge
        Node newHead;
        curA = a;
        curB = b;
        Random r = new Random();
        if (r.nextBoolean()) {
            newHead = curA;
            curA = curA.next;
        } else {
            newHead = curB;
            curB = curB.next;
        }
        current = newHead;
        while (curA != null && curB != null) {
            if (r.nextBoolean()) {
                current.next = curA;
                curA = curA.next;
            } else {
                current.next = curB;
                curB = curB.next;
            }
            current = current.next;
        }
        if (curA != null) current.next = curA;
        if (curB != null) current.next = curB;

        return newHead;
    }

    public static void checkDist(int n, int trials) {
        int[][] dist = new int[n][n];
        Node head, current;
        for (int t = 0; t < trials; t++) {
            head = new Node(0);
            current = head;
            for (int i = 1; i < n; i++) {
                Node next = new Node(i);
                current.next = next;
                current = current.next;
            }
            head = shuffle(head);
            current = head;
            int i = 0;
            while (current != null) {
                dist[(int) current.val][i++]++;
                current = current.next;
            }
        }

        // Print dist
        System.out.printf("%4s", "");
        for (int i = 0; i < n; i++) {
            System.out.printf("%6d", i);
        }
        System.out.println();
        for (int i = 0; i < n; i++) {
            System.out.printf("%4d", i);
            for (int j = 0; j < n; j++) {
                double pct = 100.0 * dist[i][j] / trials;
                System.out.printf("%6.1f", pct);
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        int n = 20;
        Node head = new Node(0);
        Node current = head;
        for (int i = 1; i < n; i++) {
            current.next = new Node(i);
            current = current.next;
        }
        System.out.println(head);
        head = shuffle(head);
        System.out.println(head);

        checkDist(20, 1000);
    }
}
