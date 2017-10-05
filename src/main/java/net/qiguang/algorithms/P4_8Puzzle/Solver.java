package net.qiguang.algorithms.P4_8Puzzle;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.MinPQ;

public class Solver {
    private Stack<Board> solution;
    MinPQ<SearchNode> mpq;

    // Initialize
    public Solver(Board initial) {
        if (initial == null)
            throw new java.lang.IllegalArgumentException(
                    "Null argument passed to constructor");
        if (!initial.isSolvable())
            throw new java.lang.IllegalArgumentException(
                    "Unsolvable puzzle");
        mpq = new MinPQ<SearchNode>();
        mpq.insert(new SearchNode(initial, null, 0));
        solution = new Stack<Board>();
        solve();
    }

    // Find a solution to the initial board (using the A* algorithm)
    private void solve() {
        SearchNode minNode;
        while (true) {
            // Dequeue minimum node from mpq
            minNode = mpq.delMin();

            // If goal board was dequeued, save solution and return
            if (minNode.getBoard().isGoal()) {
                solution.push(minNode.getBoard());
                while (minNode.hasParent()) {
                    minNode = minNode.getParent();
                    solution.push(minNode.getBoard());
                }
                return;
            }

            // Add neighbors to tree
            for (Board neighbor : minNode.getBoard().neighbors()) {
                if (!isDuplicate(neighbor, minNode))
                    mpq.insert(new SearchNode(neighbor, minNode, minNode.getMoves()+1));
            }
        }
    }

    private boolean isDuplicate(Board board, SearchNode node) {
        /*
        while (node.hasParent()) {
            node = node.parent;
            if (node.getBoard().equals(board))
                return true;
        }
        */
        if (!node.hasParent()) return false;
        if (node.getParent().getBoard().equals(board)) return true;
        return false;
    }

    // Min number of moves to solve initial board
    public int moves() { return solution.size() - 1; }

    private class SearchNode implements Comparable<SearchNode> {
        private int moves;
        private int priority;
        private Board board;
        private SearchNode parent;
            
        public SearchNode(Board board, SearchNode parent, int moves) {
            this.board = board;
            this.parent = parent;
            this.moves = moves;
            this.priority = moves + 1 * board.manhattan();
        }
        
        public int compareTo(SearchNode that) {
            return this.priority - that.priority;
        }

        public SearchNode getParent() { return parent; }
        
        public boolean hasParent() {
            return parent != null;
        }
        
        public int getMoves() {
            return moves;
        }

        public Board getBoard() { return board; }
        
    }
    
    // Sequence of boards in a shortest solution
    public Iterable<Board> solution() { return solution; }
    
    // Solve a slider puzzle (given below)
    public static void main(String[] args) {
        // Environment specific input, delete when submitting ----------
        ClassLoader cl = Thread.currentThread().getContextClassLoader();
        args = new String[] {cl.getResource("algs4-data/P4_8Puzzle/puzzle14.txt").getFile()};
        // -------------------------------------------------------------

        In in = new In(args[0]);
        int n = in.readInt();

        // Create initial board from file
        int[][] tiles = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
            tiles[i][j] = in.readInt();
        Board initial = new Board(tiles);
        
        // Solve board and print solution
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