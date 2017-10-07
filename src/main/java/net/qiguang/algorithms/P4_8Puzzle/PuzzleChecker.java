package net.qiguang.algorithms.P4_8Puzzle;

/******************************************************************************
 *  Compilation:  javac PuzzleChecker.java
 *  Execution:    java  PuzzleChecker filename1.txt filename2.txt ...
 *  Dependencies: Board.java Solver.java
 *
 *  This program creates an initial board from each filename specified
 *  on the command line and finds the minimum number of moves to
 *  reach the goal state.
 *
 *  % java PuzzleChecker puzzle[0-9][0-9].txt
 *  filename                    moves     time
 *  ------------------------------------------
 *  puzzle00.txt                    0     0.09
 *  puzzle01.txt                    1     0.01
 *  puzzle02.txt                    2     0.00
 *  puzzle03.txt                    3     0.00
 *  puzzle04.txt                    4     0.00
 *  puzzle05.txt                    5     0.00
 *  puzzle06.txt                    6     0.00
 *  ............                   ..     ....
 *  puzzle46.txt                   46     0.56
 *  puzzle47.txt                   47    15.52
 *  puzzle48.txt                   48     1.07
 *  puzzle49.txt                   49    33.65
 *  puzzle50.txt                   50     9.18
 * 
 ******************************************************************************/

/* Incompatible with Coursera version :(

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.Stopwatch;

public class PuzzleChecker {

    public static void main(String[] args) {
        // Environment specific input, delete when submitting ----------
        ClassLoader cl = Thread.currentThread().getContextClassLoader();
        // puzzle[0-9][0-9].txt
        args = new String[51];
        for (int i = 0; i <= 50; i++) {
            String num = i < 10 ? "0" + i : String.valueOf(i);
            String file = "algs4-data/P4_8Puzzle/puzzle" + num + ".txt";
            args[i] = cl.getResource(file).getFile();
        }
        // -------------------------------------------------------------

        // header
        StdOut.printf("%-25s %7s %8s\n", "filename", "moves", "time");
        StdOut.println("------------------------------------------");

        // for each command-line argument
        for (String filename : args) {
            // read in the board specified in the filename
            In in = new In(filename);
            int N = in.readInt();
            int[][] blocks = new int[N][N];
            for (int i = 0; i < N; i++)
                for (int j = 0; j < N; j++)
                    blocks[i][j] = in.readInt();
            Board initial = new Board(blocks);

            // check if puzzle is solvable; if so, solve it print out number of moves
            if (initial.isSolvable()) {
                Stopwatch timer = new Stopwatch();
                Solver solver = new Solver(initial);
                int moves = solver.moves();
                double time = timer.elapsedTime();
                StdOut.printf("%-25s %7d %8.2f\n", filename, moves, time);
            }

            // if not, print that it is unsolvable
            else {
                StdOut.printf("%-25s   unsolvable\n", filename);
            }
        }
    }
}

*/
