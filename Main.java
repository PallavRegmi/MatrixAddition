import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        int rows=0,columns=0;
        String fileName = args[0];
        Scanner scanner = null;
        int[][] matrix1FromFile;
        int [][]matrix2FromFile;


        try {
            // Open and connect to the file using a Scanner
            File file = new File(fileName);
            scanner = new Scanner(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        // Read the number of rows and columns
        rows = scanner.nextInt();
        columns = scanner.nextInt();
        int[][] matrixC = new int[rows][columns];

        // Read the first matrix
        matrix1FromFile = readMatrixFromFile(rows, columns, scanner);
        System.out.println("Matrix 1 from file:");
        print2dArray(matrix1FromFile);

        // Read the second matrix
        matrix2FromFile = readMatrixFromFile(rows, columns, scanner);
        System.out.println("Matrix 2 from file:");
        print2dArray(matrix2FromFile);
        System.out.println();

        scanner.close();


        ThreadOperation operation1 = new ThreadOperation(matrix1FromFile, matrix2FromFile, matrixC,"upper left");
        ThreadOperation operation2 = new ThreadOperation(matrix1FromFile, matrix2FromFile,matrixC, "upper right");
        ThreadOperation operation3 = new ThreadOperation(matrix1FromFile, matrix2FromFile, matrixC,"lower left");
        ThreadOperation operation4 = new ThreadOperation(matrix1FromFile, matrix2FromFile, matrixC,"lower right");

        // Create four threads and start them
        Thread thread1 = new Thread(operation1);
        Thread thread2 = new Thread(operation2);
        Thread thread3 = new Thread(operation3);
        Thread thread4 = new Thread(operation4);

        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();

        // Join the threads to wait for their completion
        try{
            thread1.join();
            thread2.join();
            thread3.join();
            thread4.join();
        }
        catch(InterruptedException e){
            e.printStackTrace();
        }
        System.out.println("Matrix-Addition.");
        print2dArray(matrixC);
    }

    // Read a matrix from the file
    public static int[][] readMatrixFromFile(int rows, int columns, Scanner scanner) {
        int[][] matrix = new int[rows][columns];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                matrix[i][j] = scanner.nextInt();
            }
        }
        return matrix;
    }

    // Print a 2D array with proper alignment using System.out.printf
    public static void print2dArray(int[][] array) {
        for (int[] row : array) {
            for (int value : row) {
                System.out.printf("%4d", value);
            }
            System.out.println();
        }
    }
}
