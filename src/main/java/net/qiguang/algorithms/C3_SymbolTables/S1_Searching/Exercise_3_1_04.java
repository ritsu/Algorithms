package net.qiguang.algorithms.C3_SymbolTables.S1_Searching;

/**
 * 3.1.4 Develop Time and Event ADTs that allow processing of data as in the example illustrated on page 367.
 */
public class Exercise_3_1_04 {
    private static class Time implements Comparable<Time> {
        int hour;
        int min;
        int sec;
        public Time(int h, int m, int s) {
            hour = h;
            min = m;
            sec = s;
        }
        public int compareTo(Time that) {
            if (this == that) return 0;
            if (this.hour > that.hour) return 1;
            if (this.hour < that.hour) return -1;
            if (this.min > that.min) return 1;
            if (this.min < that.min) return -1;
            if (this.sec > that.sec) return 1;
            if (this.sec < that.sec) return -1;
            return 0;
        }
        public String toString() {
            return String.format("%02d:%02d:%02d", hour, min, sec);
        }
    }
    private static class Event implements Comparable<Event>{
        String name;
        public Event(String s) {
            name = s;
        }
        public int compareTo(Event that) {
            return this.name.compareTo(that.name);
        }
        public String toString() {
            return name;
        }
    }
    public static void main(String[] args) {
        Event[] events = {
                new Event("aaaa"),
                new Event("asdf"),
                new Event("xyz"),
                new Event("asdf")
        };
        Time[] times = {
                new Time(1, 2, 3),
                new Time(1, 1, 1),
                new Time(2, 2, 2),
                new Time(1, 2, 3)
        };
        for (int i = 0; i < events.length; i++) {
            for (int j = 0; j < events.length; j++) {
                if (events[i].compareTo(events[j]) < 0)
                    System.out.printf("%8s < %s\n", events[i], events[j]);
                else if (events[i].compareTo(events[j]) > 0)
                    System.out.printf("%8s > %s\n", events[i], events[j]);
                else
                    System.out.printf("%8s = %s\n", events[i], events[j]);
            }
        }
        for (int i = 0; i < times.length; i++) {
            for (int j = 0; j < times.length; j++) {
                if (times[i].compareTo(times[j]) < 0)
                    System.out.printf("%8s < %s\n", times[i], times[j]);
                else if (times[i].compareTo(times[j]) > 0)
                    System.out.printf("%8s > %s\n", times[i], times[j]);
                else
                    System.out.printf("%8s = %s\n", times[i], times[j]);
            }
        }
    }
}
