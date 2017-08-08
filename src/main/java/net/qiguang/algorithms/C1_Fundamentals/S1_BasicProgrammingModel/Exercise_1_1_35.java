package net.qiguang.algorithms.C1_Fundamentals.S1_BasicProgrammingModel;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Arrays;

/**
 * 1.1.35 Dice simulation.
 * The following code computes the exact probability distribution for the sum of two dice:
 *
 * int SIDES = 6;
 * double[] dist = new double[2*SIDES+1];
 * for (int i = 1; i <= SIDES; i++)
 *    for (int j = 1; j <= SIDES; j++)
 *       dist[i+j] += 1.0;
 * for (int k = 2; k <= 2*SIDES; k++)
 *    dist[k] /= 36.0;
 *
 * The value dist[i] is the probability that the dice sum to k.
 * Run experiments to validate this calculation simulating N dice throws, keeping track of the
 * frequencies of occurrence of each value when you compute the sum of two random integers between 1 and 6.
 * How large does N have to be before your empirical results match the exact results to three decimal places?
 */

public class Exercise_1_1_35 {
    public static int SIDES = 6;

    public static double[] getDist() {
        double[] dist = new double[2*SIDES+1];
        for (int i = 1; i <= SIDES; i++)
            for (int j = 1; j <= SIDES; j++)
                dist[i+j] += 1.0;
        for (int k = 2; k <= 2*SIDES; k++)
            dist[k] /= 36.0;
        return dist;
    }

    public static boolean checkSim(double[] dist, double[] sim, double diff) {
        for (int i = 0; i < dist.length; i++)
            if (Math.abs(dist[i] - sim[i]) > diff) return false;
        return true;
    }

    public static void main(String[] args) {
        double[] dist = getDist();
        double[] sim = new double[2*SIDES+1];
        double accuracy = 0.001;

        double[] rolls = new double[2*SIDES+1];
        int count = 0;
        while (!checkSim(dist, sim, accuracy)) {
            int a = StdRandom.uniform(1, 7);
            int b = StdRandom.uniform(1, 7);
            rolls[a+b] += 1.0;
            count++;
            for (int k = 2; k <= 2*SIDES; k++)
                sim[k] = rolls[k] / count;
        }
        StdOut.println(count);

    }
}
