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
        
        public int compare(Term a, Term b) {
            int max = a.query.length() < b.query.length() ? 
                a.query.length() : b.query.length();
            for (int i = 0; i < r && i < max; i++) {
                if (a.query.charAt(i) > b.query.charAt(i)) return 1;
                else if (a.query.charAt(i) < b.query.charAt(i)) return -1;
            }
            return 0;
        }
    }
    
    // Compares the two terms in lexicographic order by query.
    public int compareTo(Term that) {
        return this.query.compareTo(that.query);
    }
    
    // Returns a string representation of this term in the following format:
    // the weight, followed by a tab, followed by the query.
    public String toString() {
        return Long.toString(this.weight) + "\t" + this.query;
    }
    
    // unit testing (required)
    public static void main(String[] args) {
        // process args
        In in = new In(args[0]);
        int c = args.length > 1 ? Integer.parseInt(args[1]) : -1;

        // store all terms to array
        int N = in.readInt();        
        Term[] all = new Term[N];        
        for (int i = 0; i < N; i++) {
            long w = in.readLong();
            String q = in.readLine().trim();
            all[i] = new Term(q, w);
        }
        
        // time sort for multiples of 2
        for (int i = 10; i < N; i = i * 2) {
            Term[] t = Arrays.copyOfRange(all, 0, i-1);
            double start = System.currentTimeMillis();
            Arrays.sort(t, byPrefixOrder(4));
            double time = System.currentTimeMillis() - start;
            // sanity check
            if (i == c) {
                for (Term tmp : t) {
                    StdOut.println(tmp);
                }
            }
            StdOut.printf("% 10d %4.3f\n", i, time);
        }
        

    }
}