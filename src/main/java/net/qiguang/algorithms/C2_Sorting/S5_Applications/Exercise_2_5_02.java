package net.qiguang.algorithms.C2_Sorting.S5_Applications;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

/**
 * 2.5.2 Write a program that reads a list of words from standard input and prints
 * all twoword compound words in the list. For example, if after, thought, and
 * afterthought are in the list, then afterthought is a compound word.
 */
public class Exercise_2_5_02 {
    private static String[] compoundWords(String[] a) {
        Arrays.sort(a);
        String prev = a[0];
        ArrayDeque<String> q = new ArrayDeque<String>();
        for (int i = 1; i < a.length; i++) {
            if (a[i].length() > prev.length() &&
                    a[i].substring(0, prev.length()).equals(prev)) {
                q.add(a[i]);
            }
            prev = a[i];
        }
        String[] words = new String[q.size()];
        int i = 0;
        for (String s : q) {
            words[i++] = s;
        }
        return words;
    }
    public static void main(String[] args) {
        String[] s = {"as", "df", "asdf", "qw", "q", "w", "asdfqw"};
        System.out.println(Arrays.toString(compoundWords(s)));
    }
}
