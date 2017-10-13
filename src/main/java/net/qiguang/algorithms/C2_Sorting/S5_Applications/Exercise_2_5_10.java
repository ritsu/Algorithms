package net.qiguang.algorithms.C2_Sorting.S5_Applications;

import java.util.Arrays;

/**
 * 2.5.10 Create a data type Version that represents a software version number,
 * such as 115.1.1, 115.10.1, 115.10.2. Implement the Comparable interface so that
 * 115.1.1 is less than 115.10.1, and so forth.
 */
public class Exercise_2_5_10 {
    static class Version implements Comparable<Version> {
        int[] nums;
        public Version(String s) {
            String[] tmp = s.split("\\.");
            nums = new int[tmp.length];
            for (int i = 0; i < tmp.length; i++)
                nums[i] = Integer.valueOf(tmp[i]);

        }
        public int compareTo(Version that) {
            int max = Math.min(this.nums.length, that.nums.length);
            for (int i = 0; i < max; i++) {
                if      (nums[i] < that.nums[i]) return -1;
                else if (nums[i] > that.nums[i]) return 1;
            }
            return nums.length - that.nums.length;
        }
        public String toString() {
            if (nums.length == 0) return "";
            StringBuilder sb = new StringBuilder();
            sb.append(nums[0]);
            for (int i = 1; i < nums.length; i++) {
                sb.append(".").append(nums[i]);
            }
            return sb.toString();
        }
    }
    public static void main(String[] args) {
        String[] s = {"1.1.1", "1.1.2", "1.1.1.0", "1.1.1.1", "1.1.2.3", "1.1.4", "1.1.11", "1.1.2.10"};
        Version[] v = new Version[s.length];
        for (int i = 0; i < s.length; i++) {
            v[i] = new Version(s[i]);
        }
        System.out.println(Arrays.toString(v));
        Arrays.sort(v);
        System.out.println(Arrays.toString(v));
    }
}
