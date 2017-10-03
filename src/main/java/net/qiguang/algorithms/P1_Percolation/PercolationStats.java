package net.qiguang.algorithms.P1_Percolation;

/**
 * Runs monte carlo simulations to determine percolation statistics
 */

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.StdOut;

public class PercolationStats {
    private double[] thresholds;
    
    // Perform T independent experiments on an N-by-N grid
    public PercolationStats(int N, int T)
    {
        // Check valid N
        if (N <= 0)
            throw new IllegalArgumentException("Parameter N <= 0");

        // Check valid T
        if (T <= 0)
            throw new IllegalArgumentException("Parameter T <= 0");
        
        // Create array to hold threshold test results
        thresholds = new double[T];
        
        // Run tests
        for (int i = 0; i < T; i++) {
            Percolation perc = new Percolation(N);
            double x = 0.0;
            while (!perc.percolates()) {
                int j = StdRandom.uniform(N)+1;
                int k = StdRandom.uniform(N)+1;
                if (!perc.isOpen(j, k)) {
                    perc.open(j, k);
                    x++;
                }
            }
            thresholds[i] = x/(N*N);
        }        
    }
    
    // Sample mean of percolation threshold
    public double mean()
    {
        return StdStats.mean(thresholds);     
    }
    
    // Sample standard deviation of percolation threshold
    public double stddev()
    {
        return StdStats.stddev(thresholds);
    }
    
    // Low  endpoint of 95% confidence interval
    public double confidenceLo()
    {
        return mean() - (1.96 * stddev()) / java.lang.Math.sqrt(thresholds.length);
    }
    
    // High endpoint of 95% confidence interval
    public double confidenceHi()
    {
        return mean() + (1.96 * stddev()) / java.lang.Math.sqrt(thresholds.length);
    }
    
    // Test client
    public static void main(String[] args)
    {
        // Dimension N of the N-by-N grid
        int N = Integer.parseInt(args[0]);

        // Number of independent computational experiments to run 
        int T = Integer.parseInt(args[1]);

        // Run experiments
        PercolationStats stats = new PercolationStats(N, T);
        
        // Print results
        StdOut.printf("%-23s = %f\n", "mean", stats.mean());
        StdOut.printf("%-23s = %f\n", "stddev", stats.stddev());
        StdOut.printf("%-23s = %f, %f\n", "95% confidence interval", 
                      stats.confidenceLo(), stats.confidenceHi());
        
    }
}