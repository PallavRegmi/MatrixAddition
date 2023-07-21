class ThreadOperation implements Runnable {
    private int[][] matrixA; // First input matrix
    private int[][] matrixB; // Second input matrix
    private int[][] matrixC; // Result matrix
    private String quadrant; // Quadrant identifier

    public ThreadOperation(int[][] matrixA, int[][] matrixB, int[][] matrixC, String quadrant) {
        this.matrixA = matrixA;
        this.matrixB = matrixB;
        this.matrixC = matrixC;
        this.quadrant = quadrant;
    }

    // Get the quadrant indexes based on matrix dimensions and quadrant identifier
    public static int[] getQuadrantIndexes(int rows, int columns, String quadrant) {
        int[] indexes = new int[4];

        if (quadrant.equals("upper left")) {
            indexes[0] = 0;
            indexes[1] = rows / 2 - 1;
            indexes[2] = 0;
            indexes[3] = columns / 2 - 1;
        } else if (quadrant.equals("upper right")) {
            indexes[0] = 0;
            indexes[1] = rows / 2 - 1;
            indexes[2] = columns / 2;
            indexes[3] = columns - 1;
        } else if (quadrant.equals("lower left")) {
            indexes[0] = rows / 2;
            indexes[1] = rows - 1;
            indexes[2] = 0;
            indexes[3] = columns / 2 - 1;
        } else {
            indexes[0] = rows / 2;
            indexes[1] = rows - 1;
            indexes[2] = columns / 2;
            indexes[3] = columns - 1;
        }

        return indexes;
    }

    @Override
    public void run() {
        // Get the quadrant indexes for the current thread
        int[] indexes = getQuadrantIndexes(matrixA.length, matrixA[0].length, quadrant);

        // Iterate over the elements in the specified quadrant
        for (int i = indexes[0]; i <= indexes[1]; i++) {
            for (int j = indexes[2]; j <= indexes[3]; j++) {
                // Perform matrix addition and store the result in the result matrix
                matrixC[i][j] = matrixA[i][j] + matrixB[i][j];
            }
        }
    }
}
