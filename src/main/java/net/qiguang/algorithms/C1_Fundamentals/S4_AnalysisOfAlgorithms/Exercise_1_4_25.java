package net.qiguang.algorithms.C1_Fundamentals.S4_AnalysisOfAlgorithms;

/**
 * 1.4.25 Throwing two eggs from a building.
 * Consider the previous question, but now suppose you only have two eggs, and your cost model is the number of throws.
 * Devise a strategy to determine F such that the number of throws is at most 2√N, then ﬁnd a way to reduce the cost
 * to ~c√F. This is analogous to a situation where search hits (egg intact) are much cheaper than misses (egg broken).
 */
public class Exercise_1_4_25 {
    public static class EggToss {
        private long N;
        private long F;
        private long broken = 0;
        private long thrown = 0;

        public EggToss(long n) {
            N = n;
        }
        public void setFloor(long f) {
            F = f;
        }
        // Returns true if egg does not break, false if egg breaks
        private boolean toss(long floor) {
            thrown++;
            if (floor >= F) broken++;
            return (floor < F);
        }
        // Assume you do not know F
        public long findFloorN() {
            long step = (long) Math.sqrt(N);
            long floor = step;
            while (toss(floor)) floor += step;
            floor -= step;
            while (toss(floor)) floor++;
            if (floor != F) throw new java.lang.IllegalStateException("Incorrect value");
            return floor;
        }
        // Assume you do not know F
        public long findFloorF() {
            long floor = 2;
            while (toss(floor)) floor *= floor;
            floor = (long) Math.sqrt(floor) - 1;
            while (toss(floor)) floor++;
            if (floor != F) throw new java.lang.IllegalStateException("Incorrect value");
            return floor;
        }
        public void resetBroken() {
            broken = 0;
        }
        public void resetThrown() {
            thrown = 0;
        }

    }
    public static void main(String[] args) {
        long n = 2000000000;
        EggToss e = new EggToss(n);
        int runs = 1000;

        System.out.println("Performing " + runs + " runs:");
        double brokenN = 0;
        double brokenF = 0;
        double thrownN = 0;
        double thrownF = 0;
        int fsum = 0;
        for (int i = 0; i < runs; i++) {
            int f = (int) (Math.random() * 100);
            fsum += f;
            e.setFloor(f);
            e.resetBroken();
            e.resetThrown();
            e.findFloorN();
            brokenN += e.broken;
            thrownN += e.thrown;
            e.resetBroken();
            e.resetThrown();
            e.findFloorF();
            brokenF += e.broken;
            thrownF += e.thrown;
        }
        System.out.printf("findFloorN: avg %6.3f thrown, %6.3f broken\n", thrownN / runs, brokenN / runs);
        System.out.printf("sqrt(N) = %6.3f\n", Math.sqrt(n));
        System.out.printf("findFloorF: avg %6.3f thrown, %6.3f broken\n", thrownF / runs, brokenF / runs);
        System.out.printf("sqrt(f) = %6.3f\n", Math.sqrt(fsum / runs));


    }
}
