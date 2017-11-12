package net.qiguang.algorithms.C2_Sorting.S5_Applications;

import java.util.Arrays;

/**
 * 2.5.14 Sort by reverse domain.
 * Write a data type Domain that represents domain names, including an appropriate compareTo() method
 * where the natural order is in order of the reverse domain name. For example, the reverse domain of
 * cs.princeton.edu is edu.princeton.cs. This is useful for web log analysis.
 *
 * Hint: Use s.split("\\.") to split the string s into tokens, delimited by dots. Write a client that
 * reads domain names from standard input and prints the reverse domains in sorted order.
 */
public class Exercise_2_5_14 {
    private static class Domain implements Comparable<Domain> {
        private String tld;
        private String domain;
        private String prefix;

        public Domain(String s) {
            String[] parts = s.split("\\.");
            if (parts.length < 2) {
                throw new IllegalArgumentException("Invalid domain name");
            }
            tld = parts[parts.length - 1];
            domain = parts[parts.length - 2];
            prefix = String.join(".", Arrays.copyOfRange(parts, 0, parts.length - 2));
        }
        public int compareTo(Domain that) {
            if      (this.tld.compareTo(that.tld) < 0) return -1;
            else if (this.tld.compareTo(that.tld) > 0) return 1;
            else if (this.domain.compareTo(that.domain) < 0) return -1;
            else if (this.domain.compareTo(that.domain) > 0) return 1;
            else if (this.prefix.compareTo(that.prefix) < 0) return -1;
            else if (this.prefix.compareTo(that.prefix) > 0) return 1;
            else return 0;
        }
        public String toString() {
            return String.join(".", tld, domain, prefix);
        }
    }
    public static void main(String[] args) {
        String[] test = {"b.com", "a.edu", "b.asdf.com", "a.asdf.com", "asdf.com"};
        Domain[] domains = new Domain[test.length];
        for (int i = 0; i < test.length; i++) {
            domains[i] = new Domain(test[i]);
        }
        System.out.println(Arrays.toString(domains));
        Arrays.sort(domains);
        System.out.println(Arrays.toString(domains));
    }
}
