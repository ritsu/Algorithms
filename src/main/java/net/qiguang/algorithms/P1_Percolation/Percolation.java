package net.qiguang.algorithms.P1_Percolation;

/**
 * Percolation model using WeightedQuickUnionUF to represent sites
 * 
 * sites[0] defined as common source 
 * sites[1] ... sites[N*N] correspond to sites in N-by-N grid
 * 
 * Status array keeps track of site status
 *   - 0b001 = site open
 *   - 0b010 = site connected to bottom
 *   - 0b100 = site connected to top
 * 
 * Percolation(N):
 *   - Create WeightedQuickUnionUF sites[N*N+1]
 *   - Create int[N*N+1] status
 *   - Set status[n] = 0b100 for common source and sites in top row
 *   - Set status[n] = 0b010 for sites in bottom row
 *   - Set status[n] = 0b000 for all other sites
 *   - Union sites[0] with sites[1] ... sites[N]
 * 
 * open(n):
 *   - For each open neighbor of sites[n]:
 *     - Find root of neighbor (r)
 *     - Union sites[r] with sites[n]
 *     - Merge status[r] with status[n]
 *   - Find new root (nr) of sites[n]
 *   - Set status[nr] = status[n] (inherits status from open neighbors)
 * 
 * isOpen(n):
 *   - Return true if status[n] >> 0
 * 
 * isFull(n):
 *   - Find root (r) of sites[n]
 *   - Return true if status[r] >> 2
 * 
 * percolates():
 *   - Find root (r0) of sites[0]
 *   - Return true if status[r0] >> 1
 *
 */

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    // Defines which nodes are connected
    private WeightedQuickUnionUF sites;
    
    // Status of each site
    private byte[] status;
    
    // Dimension (N) of the N-by-N grid
    private int dim;
    
    // Create N-by-N grid, with all sites blocked
    public Percolation(int N)
    {
        // Check if N is valid
        if (N <= 0)
            throw new IllegalArgumentException("Dimension N <= 0");
        
        // Save dimension of grid
        dim = N;
        
        // Create status array (includes common source)
        status = new byte[N*N+1];
        for (int i = 0; i <= N; i++) status[i] = 0b100;
        for (int i = N+1; i <= N*(N-1); i++) status[i] = 0b000;
        for (int i = N*(N-1)+1; i <= N*N; i++) status[i] = 0b010;
        
        // Create sites
        sites = new WeightedQuickUnionUF(N*N+1);
        for (int i = 1; i <= N; i++) sites.union(0, i);
    }
    
    // Check if (x,y) is a valid index
    private void checkValid(int x, int y)
    {
        if (x <= 0 || x > dim || y <= 0 || y > dim)
            throw new IndexOutOfBoundsException(
               String.format("Indices (%d, %d) out of bounds", x, y));
    }
    
    // Convert (i, j) to n
    private int xyTo1D(int i, int j)
    {
        checkValid(i, j);
        return (i-1) * dim + j;
    }
    
    // Open site (row i, column j) if it is not open already
    public void open(int i, int j)
    {
        // Get n value of (i, j)
        int n = xyTo1D(i, j);
                
        // Return if site already open, otherwise open site
        if (isOpen(i, j))
            return;
        else
            status[n] = (byte) (status[n] | 0b001);
        
        // Union and merge status with open neighbor sites
        if (i > 1 && isOpen(i-1, j)) {            
            int r = sites.find(xyTo1D(i-1, j));
            status[n] = (byte) (status[n] | status[r]);
            sites.union(n, r);
        }
        if (i < dim && isOpen(i+1, j)) {
            int r = sites.find(xyTo1D(i+1, j));
            status[n] = (byte) (status[n] | status[r]);
            sites.union(n, r);
        }
        if (j > 1 && isOpen(i, j-1)) {
            int r = sites.find(xyTo1D(i, j-1));
            status[n] = (byte) (status[n] | status[r]);
            sites.union(n, r);
        }
        if (j < dim && isOpen(i, j+1)) {
            int r = sites.find(xyTo1D(i, j+1));
            status[n] = (byte) (status[n] | status[r]);
            sites.union(n, r);
        }
        
        // Update status of root
        int r = sites.find(n);
        status[r] = (byte) (status[r] | status[n]);
    }
    
    // Is site (row i, column j) open?
    public boolean isOpen(int i, int j)
    {
        return (status[xyTo1D(i, j)] & 1) > 0; 
    }
    
    // Is site (row i, column j) full?
    public boolean isFull(int i, int j)
    {
        int r = sites.find(xyTo1D(i, j));
        return isOpen(i, j) && (status[r] >> 2 & 1) > 0;
    }
    
    // Does the system percolate?
    public boolean percolates()
    {
        int r = sites.find(0);
        return (status[r] >> 1 & 1) > 0;
    }
    
    // Test client (optional)
    public static void main(String[] args)
    {
    }
    
}


