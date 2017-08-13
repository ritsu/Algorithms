package net.qiguang.algorithms.C1_Fundamentals.S3_BagsQueuesAndStacks;

import java.util.Iterator;

/**
 * 1.3.8
 * Give the contents and size of the array for DoublingStackOfStrings with the input
 *    it was - the best - of times - - - it was - the -
 *
 * Note: DoublingStackOfStrings not found in text. Assuming they meant ResizingArrayStack.
 *       Regardless, answer is the same, shown below. Code is just for reference.
 *
 * Input    Array                 Size
 * ---------------------------------------
 * it       [it]                  1
 * was      [it, was]             2
 * -        [it, ]                2
 * the      [it, the]             2
 * best     [it, the, best, ]     4
 * -        [it, the, , ]         4
 * of       [it, the, of, ]       4
 * times    [it, the, of, times]  4
 * -        [it, the, of, ]       4
 * -        [it, the, , ]         4
 * -        [it, ]                2
 * it       [it, it]              2
 * was      [it, it, was, ]       4
 * -        [it, it, , ]          4
 * the      [it, it, the, ]       4
 * -        [it, it, , ]          4
 */
public class Exercise_1_3_08 {
    public static class ResizingArrayStack<Item> implements Iterable<Item> {
        private Item[] a = (Item[]) new Object[1];  // stack items
        private int N = 0;                          // number of items
        public boolean isEmpty()  {  return N == 0; }
        public int size()         {  return N;      }
        private void resize(int max)   {
            // Move stack to a new array of size max.
            Item[] temp = (Item[]) new Object[max];
            for (int i = 0; i < N; i++)
                temp[i] = a[i];
            a = temp;
        }
        public void push(Item item) {
            // Add item to top of stack.
            if (N == a.length)
                resize(2*a.length);
            a[N++] = item;
        }
        public Item pop() {
            // Remove item from top of stack.
            Item item = a[--N];
            a[N] = null;  // Avoid loitering (see text).
            if (N > 0 && N == a.length/4)
                resize(a.length/2);
            return item;
        }
        public Iterator<Item> iterator()    {  return new ReverseArrayIterator();  }
        private class ReverseArrayIterator implements Iterator<Item>   {
            // Support LIFO iteration.
            private int i = N;
            public boolean hasNext() {  return i > 0;   }
            public    Item next()    {  return a[--i];  }
            public    void remove()  {                  }
        }
    }
}
