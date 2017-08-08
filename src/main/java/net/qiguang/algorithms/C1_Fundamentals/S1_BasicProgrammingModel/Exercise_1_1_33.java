package net.qiguang.algorithms.C1_Fundamentals.S1_BasicProgrammingModel;

import edu.princeton.cs.algs4.StdOut;

/**
 * Matrix library. Write a library  Matrix that implements the following API:
 *
 * public class  Matrix
 *   static     double dot(double[] x, double[] y)       vector dot product
 *   static double[][] mult(double[][] a, double[][] b)  matrix-matrix product
 *   static double[][] transpose(double[][] a)           transpose
 *   static   double[] mult(double[][] a, double[] x)    matrix-vector product
 *   static   double[] mult(double[] y, double[][] a)    vector-matrix product
 *
 * Develop a test client that reads values from standard input and tests all the methods.
 */
public class Exercise_1_1_33 {
    public static class Matrix {
        // Print methods
        public static void print(double[][] a, double[][] b) {
            int max_length = a.length > b.length ? a.length : b.length;
            for (int i = 0; i < max_length; i++) {
                if (i < a.length) {
                    StdOut.print("[");
                    for (int j = 0; j < a[i].length; j++) {
                        StdOut.printf("%6.2f ", a[i][j]);
                    }
                    StdOut.print("]");
                } else {
                    int padding = 2 + 7 * a[0].length;
                    String fmt = "%" + padding + "s";
                    StdOut.printf(fmt, "");
                }
                if (i < b.length) {
                    StdOut.print("[");
                    for (int j = 0; j < b[i].length; j++) {
                        StdOut.printf("%6.2f ", b[i][j]);
                    }
                    StdOut.print("]");
                }
                StdOut.println();
            }
        }

        public static void print(double[] a, double[] b) {
            double[][] x = new double[1][a.length];
            double[][] y = new double[b.length][1];
            x[0] = a;
            for (int i = 0; i < b.length; i++) {
                y[i][0] = b[i];
            }
            print(x, y);
        }

        public static void print(double[][] a, double[] b) {
            double[][] y = new double[b.length][1];
            for (int i = 0; i < b.length; i++) {
                y[i][0] = b[i];
            }
            print(a, y);
        }

        public static void print(double[] a, double[][] b) {
            double[][] x = new double[1][a.length];
            x[0] = a;
            print(x, b);
        }

        public static void print(double[][] a) {
            for (int i = 0; i < a.length; i++) {
                StdOut.print("[");
                for (int j = 0; j < a[i].length; j++) {
                    StdOut.printf("%6.2f ", a[i][j]);
                }
                StdOut.println("]");
            }
        }

        public static void print(double[] a, boolean transpose) {
            double[][] x;
            if (transpose) {
                x = new double[a.length][1];
                for (int i = 0; i < a.length; i++) {
                    x[i][0] = a[i];
                }
            } else {
                x = new double[1][a.length];
                x[0] = a;
            }
            print(x);
        }

        // vector dot product
        public static double dot(double[] x, double[] y) {
            double d = 0;
            for (int i = 0; i < x.length; i++) {
                d += x[i] * y[i];
            }
            return d;
        }

        // matrix-matrix product
        public static double[][] mult(double[][] a, double[][] b) {
            double[][] m = new double[a.length][b[0].length];
            for (int i = 0; i < a.length; i++) {
                for (int j = 0; j < b[0].length; j++) {
                    double d = 0.0;
                    for (int k = 0; k < b.length; k++) {
                        d += a[i][k] * b[k][j];
                    }
                    m[i][j] = d;
                }
            }
            return m;
        }

        // transpose
        public static double[][] transpose(double[][] a) {
            double[][] m = new double[a[0].length][a.length];
            for (int i = 0; i < a.length; i++) {
                for (int j = 0; j < a[i].length; j++) {
                    m[j][i] = a[i][j];
                }
            }
            return m;
        }

        public static double[][] transpose(double[] a) {
            double[][] m = new double[1][a.length];
            return transpose(m);
        }

        // matrix-vector product
        public static double[] mult(double[][] a, double[] x) {
            double[] v = new double[x.length];
            for (int i = 0; i < a.length; i++) {
                double d = 0;
                for (int j = 0; j < x.length; j++) {
                    d += a[i][j] * x[j];
                }
                v[i] = d;
            }
            return v;

        }

        // vector-matrix product
        public static double[] mult(double[] y, double[][] a) {
            double[] v = new double[y.length];
            for (int i = 0; i < a[0].length; i++) {
                double d = 0;
                for (int j = 0; j < y.length; j++) {
                    d += y[j] * a[j][i];
                }
                v[i] = d;
            }
            return v;

        }
    }

    public static void main(String[] args) {
        // Test vector dot product
        double[] x = {1.0, 2.0, 3.0};
        double[] y = {4.0, 5.0, 6.0};
        StdOut.println("vector dot product:");
        Matrix.print(x, y);
        StdOut.println(Matrix.dot(x, y));
        StdOut.println();

        // Test matrix-vector product
        double[][] x2 = {{1.0, 2.0, 3.0}, {2.0, 3.0, 4.0}, {3.0, 4.0, 5.0}};
        double[] y2 = {1.0, 2.0, 3.0};
        StdOut.println("matrix-vector product:");
        Matrix.print(x2, y2);
        Matrix.print(Matrix.mult(x2, y2), true);
        StdOut.println();

        // Test vector-matrix product
        StdOut.println("vector-matrix product:");
        Matrix.print(y2, x2);
        Matrix.print(Matrix.mult(y2, x2), false);
        StdOut.println();

        // Test matrix-matrix product
        double[][] x3 = {{1.0, 2.0, 3.0}, {2.0, 3.0, 4.0}, {3.0, 4.0, 5.0}};
        double[][] y3 = {{4.0, 5.0, 6.0}, {5.0, 6.0, 7.0}, {6.0, 7.0, 8.0}};
        StdOut.println("matrix-matrix product:");
        Matrix.print(x3, y3);
        Matrix.print(Matrix.mult(x3, y3));
        StdOut.println();

        // Test transpose
        double[][] x4 = {{1.0, 2.0, 3.0}};
        double[][] y4 = {{1.0, 2.0}, {2.0, 3.0}, {3.0, 4.0}};
        StdOut.println("transpose:");
        Matrix.print(x4, Matrix.transpose(x4));
        Matrix.print(y4, Matrix.transpose(y4));
        StdOut.println();
    }
}
