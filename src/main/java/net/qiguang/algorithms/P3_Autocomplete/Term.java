package net.qiguang.algorithms.P3_Autocomplete;

import java.util.Comparator;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.In;
import java.util.Arrays;

public class Term implements Comparable<Term> {
    private final String query;
    private final long weight;
    
    // Initializes a term with the given query string and weight.
    public Term(String query, long weight) {
        if (query == null)
            throw new java.lang.IllegalArgumentException("Query cannot be null");
        if (weight < 0)
            throw new java.lang.IllegalArgumentException("Weight cannot be negative");
        this.query = query;
        this.weight = weight;
    }
    
    // Compares the two terms in descending order by weight.
    public static Comparator<Term> byReverseWeightOrder() {
        return new ReverseWeightOrder();
    }
    private static class ReverseWeightOrder implements Comparator<Term> {
        public int compare(Term a, Term b) {
            if (a.weight < b.weight) return 1;
            else if (a.weight > b.weight) return -1;
            else return 0;            
        }
    }
    
    // Compares the two terms in lexicographic order but using only the 
    // first r characters of each query.
    public static Comparator<Term> byPrefixOrder(int r) {
        return new PrefixOrder(r);
    }
    private static class PrefixOrder implements Comparator<Term> {
        private final int r;
        public PrefixOrder(int r) {
            this.r = r;
        }
        // Consider case insensitive...
        public int compare(Term a, Term b) {
            int max = a.query.length() < b.query.length() ? 
                a.query.length() : b.query.length();
            max = max < r ? max : r;
            for (int i = 0; i < max; i++) {
                if (a.query.charAt(i) > b.query.charAt(i)) return 1;
                else if (a.query.charAt(i) < b.query.charAt(i)) return -1;
            }
            if (r > a.query.length() && b.query.length() > a.query.length())
                return -1;
            else if (r > b.query.length() && a.query.length() > b.query.length())
                return 1;
            return 0;
        }
    }
    
    // Compares the two terms in lexicographic order by query.
    // Consider case insensitive...
    public int compareTo(Term that) {
        return query.compareTo(that.query);
    }
    
    // Returns a string representation of this term in the following format:
    // the weight, followed by a tab, followed by the query.
    public String toString() {
        return Long.toString(weight) + "\t" + query;
    }
    
    // unit testing (required)
    public static void main(String[] args) {
        // Environment specific input, delete when submitting ----------
        ClassLoader cl = Thread.currentThread().getContextClassLoader();
        args = new String[] {cl.getResource("algs4-data/P3_Autocomplete/5grams.txt").getFile()};
        // -------------------------------------------------------------

        // process args
        In in = new In(args[0]);

        // store all terms to array
        int n = in.readInt();
        Term[] all = new Term[n];
        for (int i = 0; i < n; i++) {
            long w = in.readLong();
            String q = in.readLine().trim();
            all[i] = new Term(q, w);
        }
        
        // time sort for multiples of 2
        for (int i = 10; i < n; i = i * 2) {
            Term[] t = Arrays.copyOfRange(all, 0, i-1);
            long start = System.currentTimeMillis();
            Arrays.sort(t, byPrefixOrder(4));
            long time = System.currentTimeMillis() - start;
            StdOut.printf("%10d %4d\n", i, time);
        }
        

    }
}