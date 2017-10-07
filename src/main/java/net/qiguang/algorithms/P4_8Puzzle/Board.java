package net.qiguang.algorithms.P4_8Puzzle;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Queue;

/**
 * Note: Coursera version of this problem differs from COS226 version.
 * There are some remnants of COS226 code in here that are not necessary,
 * but do not result in any lower of a grader score.
 */
public class Board {
    private final int dim;
    private final int[][] tiles;
    private int dh;                    // Hamming distance
    private int dm;                    // Manhattan distance
    private int zi;                    // row of zero value
    private int zj;                    // col of zero value
    
    // construct a board from an N-by-N array of tiles
    // (where tiles[i][j] = tile at row i, column j)
    public Board(int[][] tiles) {
        dim = tiles.length;
        this.tiles = new int[dim][dim];
        // calculate hamming and manhattan distances in constructor
        // so we don't have to iterate through tiles again
        dh = 0;
        dm = 0;
        zi = 0;
        zj = 0;
        for (int i = 0; i < dim; i++) {
            for (int j = 0; j < dim; j++) {
                int num = tiles[i][j];
                this.tiles[i][j] = num;
                if (num == 0) {
                    zi = i;
                    zj = j;
                }
                else {
                    // hamming distance
                    if (num != i*dim + (j+1)) dh++;
                    // manhattan horizontal distance
                    dm += Math.abs((num-1) % dim - j);
                    // vertical distance
                    dm += Math.abs((num-1) / dim - i);
                }
            }
        }
    }

    public int dimension() {
        return dim;
    }

    // return tile at row i, column j (or 0 if blank)
    private int tileAt(int i, int j) {
        if (i < 0 || i >= dim || j < 0 || j >= dim)
            throw new java.lang.IllegalArgumentException("Index out of bounds");
        return tiles[i][j];
    }
    
    // number of tiles out of place
    public int hamming() {
        return dh;
    }

    // sum of Manhattan distances between tiles and goal
    public int manhattan() {
        return dm;
    }

    // is this board the goal board?
    public boolean isGoal() {
        return dh == 0;
    }

    // is this board solvable? (Coursera version does not allow this)
    private boolean isSolvable() {
        int inv = 0;
        for (int i = 0; i < dimension(); i++) {
            for (int j = 0; j < dimension(); j++) {
                if (i == zi && j == zj) continue;
                int num = tileAt(i, j);
                for (int ci = i; ci < dimension(); ci++) {
                    for (int cj = 0; cj < dimension(); cj++) {
                        if (ci == i && cj <= j) continue;
                        if (ci == zi && cj == zj) continue;
                        if (num > tileAt(ci, cj)) inv++;
                    }
                }
            }
        }
        if (dimension() % 2 == 0)
            return (inv + zi) % 2 == 1;
        else
            return inv % 2 == 0;
    }
    
    // does this board equal y?
    public boolean equals(Object y) {
        if (y == this) return true;
        if (y == null) return false;
        if (y.getClass() != this.getClass()) return false;

        Board tmp = (Board) y;
        //return tmp.hash == this.hash;
        if (tmp.hamming() != this.hamming()) return false;
        if (tmp.manhattan() != this.manhattan()) return false;
        if (tmp.dimension() != this.dimension()) return false;
        for (int i = 0; i < this.dimension(); i++) {
            for (int j = 0; j < this.dimension(); j++) {
                if (tmp.tileAt(i, j) != this.tileAt(i, j)) return false;
            }
        }
        return true;
    }

    // all neighboring boards
    public Iterable<Board> neighbors() {
        // Create board for each degree of freedom of 0 tile
        Queue<Board> q = new Queue<Board>();
        if (zi > 0) {
            int tmp = tiles[zi-1][zj];
            tiles[zi-1][zj] = 0;
            tiles[zi][zj] = tmp;
            q.enqueue(new Board(tiles));
            tiles[zi-1][zj] = tmp;
            tiles[zi][zj] = 0;
        }
        if (zi < (dim-1)) {
            int tmp = tiles[zi+1][zj];
            tiles[zi+1][zj] = 0;
            tiles[zi][zj] = tmp;
            q.enqueue(new Board(tiles));
            tiles[zi+1][zj] = tmp;
            tiles[zi][zj] = 0;
        }
        if (zj > 0) {
            int tmp = tiles[zi][zj-1];
            tiles[zi][zj-1] = 0;
            tiles[zi][zj] = tmp;
            q.enqueue(new Board(tiles));
            tiles[zi][zj-1] = tmp;
            tiles[zi][zj] = 0;
        }
        if (zj < (dim-1)) {
            int tmp = tiles[zi][zj+1];
            tiles[zi][zj+1] = 0;
            tiles[zi][zj] = tmp;
            q.enqueue(new Board(tiles));
            tiles[zi][zj+1] = tmp;
            tiles[zi][zj] = 0;
        }
        return q;
    }

    // string representation of this board (in the output format specified below)
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(dim).append("\n");
        for (int i = 0; i < dim; i++) {
            for (int j = 0; j < dim; j++) {
                s.append(String.format("%2d ", tileAt(i, j)));
            }
            s.append("\n");
        }
        return s.toString();
    }

    // a board that is obtained by exchanging any pair of blocks
    public Board twin() {
        // Get (non-zero) blocks to switch
        int ai = 0;
        int aj = 0;
        int bi = 1;
        int bj = 0;
        if (zi == 0 && zj == 0) {
            ai = 1;
            aj = 1;
        }
        else if (zi == 1 && zj == 0) {
            bi = 0;
            bj = 1;
        }
        // Create board
        int[][] tcopy = new int[dim][dim];
        for (int i = 0; i < dim; i++) {
            for (int j = 0; j < dim; j++) {
                tcopy[i][j] = tiles[i][j];
            }
        }
        int tmp = tcopy[ai][aj];
        tcopy[ai][aj] = tcopy[bi][bj];
        tcopy[bi][bj] = tmp;
        return new Board(tcopy);
    }

    // unit testing (required)
    public static void main(String[] args) {
        // Environment specific input, delete when submitting ----------
        ClassLoader cl = Thread.currentThread().getContextClassLoader();
        args = new String[] {cl.getResource("algs4-data/P4_8Puzzle/puzzle02.txt").getFile()};
        // -------------------------------------------------------------

        String file = args[0];
        In in = new In(file);

        int n = in.readInt();
        // we assume valid input file...
        int[][] tiles = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                tiles[i][j] = in.readInt();
            }
        }
        
        Board b = new Board(tiles);
        StdOut.println(b); 
        StdOut.printf("hamming = %d\n", b.hamming());
        StdOut.printf("manhattan = %d\n", b.manhattan());
        StdOut.printf("isGoal = %s\n", b.isGoal());
        StdOut.printf("isSolvable = %s\n", b.isSolvable());
        Board c = new Board(tiles);
        StdOut.printf("equals copy = %s\n", b.equals(c));
        for (Board neighbor : b.neighbors()) {
            StdOut.printf("neighbor:\n%s", neighbor);
            StdOut.printf("equals neighbor = %s\n", b.equals(neighbor));
            StdOut.printf("twin:\n%s\n", neighbor.twin());
        }
    }
}