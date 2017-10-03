package net.qiguang.algorithms.P4_8Puzzle;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.MinPQ;

public class Solver {
    private Stack<Board> s;

    // find a solution to the initial board (using the A* algorithm)
    public Solver(Board initial) {
        if (initial == null)
            throw new java.lang.IllegalArgumentException(
                    "Null argument passed to constructor");
        if (!initial.isSolvable())
            throw new java.lang.IllegalArgumentException(
                    "Unsolvable puzzle");

        SearchNode si = new SearchNode(initial, null, 0);
        MinPQ<SearchNode> mpq = new MinPQ<SearchNode>();
        mpq.insert(si);
        
        s = new Stack<Board>();
        while (true) {
            // dequeue minimum node from mpq
            SearchNode m = mpq.delMin();
            Board b = m.getBoard();
            
            // Debug
            String id = "";
            for (int i = 0; i < b.size(); i++) {
                int tmp = 0;
                for (int j = 0; j < b.size(); j++) {
                    id += (b.tileAt(i, j) < 10) ? 0 : "";
                    id += (j == b.size() - 1) ? b.tileAt(i, j) : b.tileAt(i, j) + "-";
                }
                id += (i == b.size() - 1) ? "" : "..";
            }
            //StdOut.printf("Checking %s | m=%d, h=%d\n", id, b.manhattan(), b.hamming());
            //StdOut.printf("Checking m%d-h%d\n", b.manhattan(), b.hamming());
            //StdOut.println(b);

            //StdOut.printf("* Checking m%d-h%d-p%d\n", b.manhattan(), b.hamming(), m.priority);
            //StdOut.println(b);
            
            // check of solution was dequeued
            if (b.isGoal()) {
                // save solution
                s.push(b);
                while (m.hasParent()) {
                    m = m.getParent();
                    s.push(m.getBoard());
                }
                break;
            }
            // add neighbors to tree
            for (Board n : b.neighbors()) {
                //if (m.hasParent() && m.parent.getBoard().equals(n)) continue;
                //mpq.insert(new SearchNode(n, m, moves));
                SearchNode tmp = m;
                boolean dup = false;
                while (tmp.hasParent()) {
                    tmp = tmp.parent;
                    //StdOut.println("Checking parent: ***");
                    //StdOut.println(tmp.getBoard());
                    if (tmp.getBoard().equals(n)) {
                        dup = true;
                        break;
                    }
                }
                if (!dup) {
                    //StdOut.printf("+++ Inserting new node: m%d-h%d-p%d\n", n.manhattan(), n.hamming(), n.manhattan() + moves);
                    //StdOut.println(n);
                    mpq.insert(new SearchNode(n, m, m.getMoves()+1));
                }
            }
        }
    }
    
    private class SearchNode implements Comparable<SearchNode> {
        private int moves;
        private int priority;
        private Board board;
        private SearchNode parent;
            
        public SearchNode(Board board, SearchNode parent, int moves) {
            /*
            int[][] tiles = new int[board.size()][board.size()];
            for (int i = 0; i < board.size(); i++) {
                for (int j = 0; j < board.size(); j++) {
                    tiles[i][j] = board.tileAt(i, j);
                }
            }
            this.board = new Board(tiles);
            */
            this.board = board;
            this.parent = parent;
            this.moves = moves;
            this.priority = moves + board.manhattan();
        }
        
        public int compareTo(SearchNode that) {
            return this.priority - that.priority;
        }
        
        public Board getBoard() {
            return board;
        }
        
        public SearchNode getParent() {
            SearchNode p = parent;
            return p;        
        }
        
        public boolean hasParent() {
            return parent != null;
        }
        
        public int getMoves() {
            return moves;
        }
        
    }
    
    // min number of moves to solve initial board
    public int moves() {
        return s.size() - 1;
    }
    
    // sequence of boards in a shortest solution
    public Iterable<Board> solution() {
        Queue<Board> q = new Queue<Board>();
        for (Board b : s) {
            q.enqueue(b);
        }
        return q;            
    }
    
    // solve a slider puzzle (given below)
    public static void main(String[] args) {
        // Environment specific input, delete when submitting ----------
        ClassLoader cl = Thread.currentThread().getContextClassLoader();
        args = new String[] {cl.getResource("algs4-data/P4_8Puzzle/puzzle14.txt").getFile()};
        // -------------------------------------------------------------

        In in = new In(args[0]);
        int n = in.readInt();

        // create initial board from file
        int[][] tiles = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
            tiles[i][j] = in.readInt();
        Board initial = new Board(tiles);
        
        // solve board and print solution
        try {
            Solver solver = new Solver(initial);
            StdOut.println("Minimum number of moves = " + solver.moves());
            for (Board board : solver.solution())
                StdOut.println(board);
        }
        catch (IllegalArgumentException e) {
            StdOut.println(e.getMessage());
        }
    }
}