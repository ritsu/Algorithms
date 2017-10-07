package net.qiguang.algorithms.P4_8Puzzle;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;

public class Solver {
    private Stack<Board> solution;
    private MinPQ<SearchNode> mpqA;
    private MinPQ<SearchNode> mpqB;

    // Initialize
    public Solver(Board initial) {
        if (initial == null)
            throw new java.lang.IllegalArgumentException(
                    "Null argument passed to constructor");
        // Incompatible with Coursera version
        /*
        if (!initial.isSolvable())
            throw new java.lang.IllegalArgumentException(
                    "Unsolvable puzzle");
        */
        mpqA = new MinPQ<SearchNode>();
        mpqB = new MinPQ<SearchNode>();
        mpqA.insert(new SearchNode(initial, null, 0));
        mpqB.insert(new SearchNode(initial.twin(), null, 0));
        solve();
        mpqA = null;
        mpqB = null;
    }

    // is this board solvable?
    public boolean isSolvable() {
        return solution != null;
    }

    // Min number of moves to solve initial board
    public int moves() {
        return isSolvable() ? solution.size() - 1 : -1;
    }

    // Find a solution to the initial board (using the A* algorithm)
    private void solve() {
        for (int i = 0; solution == null; i++) {
            // Dequeue minimum node from mpq
            MinPQ<SearchNode> mpq = i % 2 == 0 ? mpqA : mpqB;
            SearchNode minNode = mpq.delMin();

            // If goal board was dequeued, save solution and return
            if (minNode.getBoard().isGoal()) {
                if (i % 2 > 0) return;
                solution = new Stack<Board>();
                solution.push(minNode.getBoard());
                while (minNode.hasParent()) {
                    minNode = minNode.getParent();
                    solution.push(minNode.getBoard());
                }
            }

            // Add neighbors to tree
            for (Board neighbor : minNode.getBoard().neighbors()) {
                if (!isDuplicate(neighbor, minNode))
                    mpq.insert(new SearchNode(
                            neighbor, minNode, minNode.getMoves()+1));
            }
        }
    }

    private boolean isDuplicate(Board board, SearchNode node) {
        return node.hasParent() && node.getParent().getBoard().equals(board);
    }

    private class SearchNode implements Comparable<SearchNode> {
        private int moves;
        private Board board;
        private SearchNode parent;
        private int priority;

        public SearchNode(Board board, SearchNode parent, int moves) {
            this.board = board;
            this.parent = parent;
            this.moves = moves;
            this.priority = moves + board.manhattan();
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
    
    // Solve a slider puzzle (given below)e
    public static void main(String[] args) {
        // Environment specific input, delete when submitting ----------
        ClassLoader cl = Thread.currentThread().getContextClassLoader();
        args = new String[] {cl.getResource("algs4-data/P4_8Puzzle/puzzle2x2-06.txt").getFile()};
        // -------------------------------------------------------------

        // create initial board from file
        In in = new In(args[0]);
        int n = in.readInt();
        int[][] blocks = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                blocks[i][j] = in.readInt();
        Board initial = new Board(blocks);

        // solve the puzzle
        Solver solver = new Solver(initial);

        // print solution to standard output
        if (!solver.isSolvable())
            StdOut.println("No solution possible");
        else {
            StdOut.println("Minimum number of moves = " + solver.moves());
            for (Board board : solver.solution())
                StdOut.println(board);
        }
    }
}