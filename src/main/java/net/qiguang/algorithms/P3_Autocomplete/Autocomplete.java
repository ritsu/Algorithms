package net.qiguang.algorithms.P3_Autocomplete;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Arrays;

public class Autocomplete {
    private final Term[] terms;

    // Initializes the data structure from the given array of terms.
    public Autocomplete(Term[] terms) {
        if (terms == null)
            throw new java.lang.IllegalArgumentException("terms is null");
        this.terms = new Term[terms.length];
        for (int i = 0; i < terms.length; i++) {
            if (terms[i] == null)
                throw new java.lang.IllegalArgumentException("null term");
            this.terms[i] = terms[i];
        }
        Arrays.sort(this.terms);
    }

    // Returns all terms that start with the given prefix, in descending order of weight.
    public Term[] allMatches(String prefix) {
        if (prefix == null)
            throw new java.lang.IllegalArgumentException("prefix is null");
        if (prefix.length() == 0) {
            Term[] matches = new Term[terms.length];
            for (int i = 0; i < terms.length; i++) {
                matches[i] = terms[i];
            }
            Arrays.sort(matches, Term.byReverseWeightOrder());
            return matches;
        }
        Term p = new Term(prefix, 0);
        int first = BinarySearchDeluxe.firstIndexOf(
                terms, p, Term.byPrefixOrder(prefix.length()));
        if (first < 0)
            return null;
        int last = BinarySearchDeluxe.lastIndexOf(
                terms, p, Term.byPrefixOrder(prefix.length()));
        Term[] matches = new Term[last - first + 1];
        for (int i = first; i <= last; i++) {
            matches[i - first] = terms[i];
        }
        Arrays.sort(matches, Term.byReverseWeightOrder());
        return matches;
    }

    // Returns the number of terms that start with the given prefix.
    public int numberOfMatches(String prefix) {
        if (prefix == null)
            throw new java.lang.IllegalArgumentException("prefix is null");
        if (prefix.length() == 0) {
            return terms.length;
        }
        Term p = new Term(prefix, 0);
        int first = BinarySearchDeluxe.firstIndexOf(
                terms, p, Term.byPrefixOrder(prefix.length()));
        if (first < 0)
            return 0;
        int last = BinarySearchDeluxe.lastIndexOf(
                terms, p, Term.byPrefixOrder(prefix.length()));
        return last - first + 1;
    }

    // unit testing (required)
    public static void main(String[] args) {
        // Environment specific input, delete when submitting ----------
        ClassLoader cl = Thread.currentThread().getContextClassLoader();
        args = new String[] {cl.getResource("algs4-data/P3_Autocomplete/cities.txt").getFile()};
        // -------------------------------------------------------------

        // Process args
        In in = new In(args[0]);

        // Store all terms to array
        int n = in.readInt();
        Term[] all = new Term[n];
        for (int i = 0; i < n; i++) {
            long w = in.readLong();
            String q = in.readLine().trim();
            all[i] = new Term(q, w);
        }

        // Test Autocomplete
        Autocomplete a = new Autocomplete(all);
        Term t = all[StdRandom.uniform(n)];
        String p = t.toString().split("\\t")[1];
        p = p.substring(0, Math.min(p.length(), 2 + StdRandom.uniform(4)));
        System.out.println(p);
        System.out.println(a.numberOfMatches(p));
        Term[] m = a.allMatches(p);
        for (Term s : m) {
            System.out.println(s);
        }
    }
}