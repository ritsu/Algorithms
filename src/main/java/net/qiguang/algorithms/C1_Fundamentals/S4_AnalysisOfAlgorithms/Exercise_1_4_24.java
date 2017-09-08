package net.qiguang.algorithms.C1_Fundamentals.S4_AnalysisOfAlgorithms;

/**
 * 1.4.24 Throwing eggs from a building.
 * Suppose that you have an N-story building and plenty of eggs. Suppose also that an egg is broken if it is
 * thrown off floor F or higher, and unhurt otherwise. First, devise a strategy to determine the value of F such
 * that the number of broken eggs is ~lg(N) when using ~lg(N) throws, then ﬁnd a way to reduce the cost to ~2*lg(F).
 */
public class Exercise_1_4_24 {
    public static class EggToss {
        private long N;
        private long F;
        private long broken = 0;

        public EggToss(long n) {
            N = n;
        }
        public void setFloor(long f) {
            F = f;
        }
        // Returns true if egg does not break, false if egg breaks
        private boolean toss(long floor) {
            if (floor >= F) broken++;
            return (floor < F);
        }
        // Assume you do not know F
        public long findFloorN() {
            long lo = 0;
            long hi = N;
            while (hi >= lo) {
                long mid = lo + (hi - lo) / 2;
                boolean safe = toss(mid);
                if (safe) lo = mid + 1;
                else      hi = mid - 1;
            }
            while (toss(lo)) lo++;
            if (lo != F) throw new java.lang.IllegalStateException("Incorrect value");
            return lo;
        }
        // Assume you do not know F
        public long findFloorF() {
            long lo = 0;
            long hi = 1;
            while (toss(hi)) hi *= 2;
            while (hi >= lo) {
                long mid = lo + (hi - lo) / 2;
                boolean safe = toss(mid);
                if (safe) lo = mid + 1;
                else      hi = mid - 1;
            }
            while (toss(lo)) lo++;
            if (lo != F) throw new java.lang.IllegalStateException("Incorrect value");
            return lo;
        }
        public void resetBroken() {
            broken = 0;
        }

    }
    public static void main(String[] args) {
        long n = 2000000000;
        EggToss e = new EggToss(n);
        int runs = 100000;

        System.out.println("Performing " + runs + " runs:");
        double brokenN = 0;
        double brokenF = 0;
        int fsum = 0;
        for (int i = 0; i < runs; i++) {
            int f = (int) (Math.random() * n / 10000000);
            fsum += f;
            e.setFloor(f);
            e.resetBroken();
            e.findFloorN();
            brokenN += e.broken;
            e.resetBroken();
            e.findFloorF();
            brokenF += e.broken;
        }
        System.out.printf("findFloorN: avg %6.3f broken eggs\n", brokenN / runs);
        System.out.printf("log(N) = %6.3f\n", Math.log(n) / Math.log(2));
        System.out.printf("findFloorF: avg %6.3f broken eggs\n", brokenF / runs);
        System.out.printf("log(f) = %6.3f\n", Math.log(fsum / runs) / Math.log(2));


    }

}
