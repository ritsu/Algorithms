package net.qiguang.algorithms.C3_SymbolTables.S1_Searching;

import java.util.ArrayDeque;
import java.util.Random;

/**
 * 3.1.2  Develop a symbol-table implementation ArrayST that uses an (unordered) array as the
 * underlying data structure to implement our basic symbol-table API.
 */
public class Exercise_3_1_02 {
    private static class ArrayST<Key, Value> {
        private static final int INIT_CAPACITY = 4;
        Key[] keys;
        Value[] values;
        private int size;

        public ArrayST() {
            this(INIT_CAPACITY);
        }
        public ArrayST(int capacity) {
            keys = (Key[]) new Object[capacity];
            values = (Value[]) new Object[capacity];
            size = 0;
        }
        private void resize(int n) {
            Key[] keys_copy = (Key[]) new Object[n];
            Value[] values_copy = (Value[]) new Object[n];
            for (int i = 0; i < size; i++) {
                keys_copy[i] = keys[i];
                values_copy[i] = values[i];
            }
            keys = keys_copy;
            values = values_copy;
        }
        public void put(Key key, Value val) {
            for (int i = 0; i < size; i++) {
                if (keys[i].equals(key)) {
                    values[i] = val;
                    return;
                }
            }
            if (size == keys.length) {
                resize(size * 2);
            }
            keys[size] = key;
            values[size] = val;
            size++;
        }
        public Value get(Key key) {
            for (int i = 0; i < size; i++) {
                if (keys[i].equals(key)) {
                    return values[i];
                }
            }
            return null;
        }
        public void delete(Key key) {
            for (int i = 0; i < size; i++) {
                if (keys[i].equals(key)) {
                    for (int j = i; j < size - 1; j++) {
                        keys[j] = keys[j+1];
                        values[j] = values[j+1];
                    }
                    size--;
                    keys[size] = null;
                    values[size] = null;
                    if (size / 2 >= INIT_CAPACITY && size * 4 < keys.length) {
                        resize(size / 2);
                    }
                    return;
                }
            }
        }
        public boolean contains(Key key) {
            for (int i = 0; i < size; i++) {
                if (keys[i].equals(key)) {
                    return true;
                }
            }
            return false;
        }
        public boolean isEmpty() {
            return size == 0;
        }
        public int size() {
            return size;
        }
        public Iterable<Key> keys() {
            ArrayDeque<Key> deque = new ArrayDeque<>();
            for (int i = 0; i < size; i++) {
                deque.add(keys[i]);
            }
            return deque;
        }
    }
    public static void main(String[] args) {
        ArrayST<Integer, Integer> st = new ArrayST();
        int n = 20;
        Random r = new Random();
        for (int i = 0; i < n; i++) {
            st.put(i, r.nextInt());
        }
        for (int i : st.keys()) {
            System.out.printf("%4d %12d\n", i, st.get(i));
        }
        System.out.printf("Contains %d: %s\n", n-1, st.contains(n-1));
        System.out.printf("Contains %d: %s\n", n, st.contains(n));
        System.out.printf("IsEmpty: %s\n", st.isEmpty());
        for (int i = 0; i < n; i++) {
            st.delete(i);
            for (int j : st.keys()) {
                System.out.printf("%3d", j);
            }
            System.out.println();
        }
        System.out.printf("IsEmpty: %s\n", st.isEmpty());

    }
}
