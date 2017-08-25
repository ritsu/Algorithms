package net.qiguang.algorithms.C1_Fundamentals.S3_BagsQueuesAndStacks;

import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdOut;

import java.io.File;

/**
 * 1.3.43 Listing files.
 * A folder is a list of files and folders. Write a program that takes the name of a folder as a
 * command-line argument and prints out all of the files contained in that folder, with the contents
 * of each folder recursively listed (indented) under that folder’s name.
 *
 * Hint: Use a queue, and see java.io.File.
 */
public class Exercise_1_3_43 {
    public static void expand(Queue<String> q, File[] files, int depth) {
        String indent = "";
        for (int i = 0; i < depth; i++) {
            indent += "\t";
        }
        for (File f : files) {
            if (f.isDirectory()) {
                q.enqueue(indent + f.getName() + File.separator);
                expand(q, f.listFiles(), depth + 1);
            }
            else {
                q.enqueue(indent + f.getName());
            }
        }
    }

    public static void main(String[] args) {
        // Get folder name
        String folder = Thread.currentThread().getContextClassLoader().getResource("tinyT.txt").getFile();
        if (args.length >= 1) folder = args[0];

        // Get list of files
        File f = new File(folder);
        File[] files = f.listFiles();

        // Create queue
        Queue<String> q = new Queue<>();
        q.enqueue(folder);
        expand(q, files, 1);

        // Print result
        for (String s : q) {
            StdOut.println(s);
        }


    }

}
