package net.qiguang.algorithms.C2_Sorting.S5_Applications;

import java.util.Arrays;
import java.util.Date;

/**
 * 2.5.9 Develop a data type that allows you to write a client that can sort a ﬁle such as the one shown at right.
 *
 * input (DJI volumes for each day)
 *   1-Oct-28     3500000
 *   2-Oct-28     3850000
 *   3-Oct-28     4060000
 *   4-Oct-28     4330000
 *   5-Oct-28     4360000
 *   ...
 *   30-Dec-99   554680000
 *   31-Dec-99   374049984
 *    3-Jan-00   931800000
 *    4-Jan-00  1009000000
 *    5-Jan-00  1085500032
 *   ...
 * output
 *   19-Aug-40 130000
 *   26-Aug-40 160000
 *   24-Jul-40 200000
 *   10-Aug-42 210000
 *   23-Jun-42 210000
 *   ...
 *   23-Jul-02 2441019904
 *   17-Jul-02 2566500096
 *   15-Jul-02 2574799872
 *   19-Jul-02 2654099968
 *   24-Jul-02 2775559936
 */
public class Exercise_2_5_09 {
    static class Volume implements Comparable<Volume> {
        String date;   // Could change this to actual date class if you cared to
        long vol;
        Volume(String d, long v) {
            date = d;
            vol = v;
        }
        public int compareTo(Volume that) {
            return Long.compare(this.vol, that.vol);
        }
    }
    public static void main(String[] args) {
        Volume[] v = new Volume[20];
        v[0] = new Volume("1-Oct-28", 3500000);
        v[1] = new Volume("2-Oct-28", 3850000);
        v[2] = new Volume("3-Oct-28", 4060000);
        v[3] = new Volume("4-Oct-28", 4330000);
        v[4] = new Volume("5-Oct-28", 4360000);
        v[5] = new Volume("30-Dec-99", 554680000);
        v[6] = new Volume("31-Dec-99", 374049984);
        v[7] = new Volume("3-Jan-00", 931800000);
        v[8] = new Volume("4-Jan-00", 1009000000);
        v[9] = new Volume("5-Jan-00", 1085500032);
        v[10] = new Volume("19-Aug-40", 130000);
        v[11] = new Volume("26-Aug-40", 160000);
        v[12] = new Volume("24-Jul-40", 200000);
        v[13] = new Volume("10-Aug-42", 210000);
        v[14] = new Volume("23-Jun-42", 210000);
        v[15] = new Volume("23-Jul-02", 2441019904L);
        v[16] = new Volume("17-Jul-02", 2566500096L);
        v[17] = new Volume("15-Jul-02", 2574799872L);
        v[18] = new Volume("19-Jul-02", 2654099968L);
        v[19] = new Volume("24-Jul-02", 2775559936L);
        Arrays.sort(v);
        for (Volume item : v) {
            System.out.printf("%10s %12d\n", item.date, item.vol);
        }
    }
}
