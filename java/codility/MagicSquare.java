package codility;

/*
QUESTION:
A magic square of size K is a K x K square grid filled with integers
such that the sum of the integers in each row, column and diagonal of the grid is equal.

 */

public class MagicSquare {

    /**
     * Function to check whether the given sub matrix is a magic square or not.
     * @param a Represents the input matrix.
     * @param x1 This co-ordinate represents the row from which the square starts.
     * @param y1 This co-ordinate represents the column from which the square starts.
     * @param x2 This co-ordinate represents the row at which the square ends.
     * @param y2 This co-ordinate represents the column at which the square ends.
     * @return Size of this sub matrix if it is a magic square, else  -1.
     */
    public static int isMagicSquare(int[][] a, int x1, int y1, int x2, int y2) {
        int size = x2 - x1 + 1; // get size of the current sub matrix (y2-y1+1 or x2-x1+1)
        int[][] rowSum = new int[size][size];
        int[][] colSum = new int[size][size];
        int diagonalSum = a[x1][y1];
        int secondDiagonalSum = a[x1][y2];
        int magicSquareSize = -1;

        for (int row = 0, col = 0, xIndex = x1, yIndex = y1; row < size && col < size; row++, col++, xIndex++, yIndex++) {
            rowSum[row][0] = a[xIndex][y1]; // (0, 0) (1, 0), (2, 0) ... (N-1, 0)
            colSum[0][col] = a[x1][yIndex]; // (0, 0) (0, 1), (0, 2) ... (0, N-1)
        }

        // The below 2 nested for loops can be combined to 1, but keeping different for better readability
        for (int xIndex = 0, row = x1, column = y1, end = y2 - 1; xIndex < size; row++, column++, end--, xIndex++) {
            if(row != x2) { // to prevent out of bounds indexing
                diagonalSum = diagonalSum + a[row + 1][column + 1]; // calculate sum for each diagonal
                secondDiagonalSum = secondDiagonalSum + a[row + 1][end]; // when row = x2, end = 0
            }

            for (int yIndex = 1, col = y1 + 1; yIndex < size; col++, yIndex++) {
                rowSum[xIndex][yIndex] = rowSum[xIndex][yIndex-1] + a[row][col]; // calculate sum for each row
            }
        }

        for(int yIndex = 0, col = y1; yIndex < size; yIndex++, col++) {
            for (int xIndex = 1, row = x1 + 1; xIndex < size; row++, xIndex++) {
                colSum[xIndex][yIndex] = colSum[xIndex-1][yIndex] + a[row][col]; // calculate sum for each column
            }
        }

        if(diagonalSum == secondDiagonalSum) { // if both diagonals have equal sum
            int column = 0;
            for (int row = size - 1; column < size; column++) {
                // check if sum of rows is equal to the sum of columns
                if(colSum[row][column] == rowSum[column][row] && colSum[row][column] == diagonalSum)
                    continue;
                break;
            }

            if(column == size) { // this means the square is magic
                magicSquareSize = size;
                // print the coordinates of this magic square matrix
                System.out.println("(x1,y1) => (" + x1 + "," + y1 + ") (x2,y2) => (" + x2 + "," + y2 + ")");
            }
        }
        return magicSquareSize;
    }

    public static void main(String[] args) {
        int[][] a = new int[][] {
                {11, 1, 2, 3, 4},
                {11, 7, 3, 8, 10},
                {11, 7, 6, 5, 10},
                {11, 4, 9, 5, 10},
                {11, 1, 2, 3, 4}
        };

        // if the input matrix is not empty, then the minimum possible magic square size is 1
        int maxMagicSquareSize = a.length == 0 ? -1 : 1;
        int rowSize = a.length;
        int colSize = a[0].length;

        for(int row = 0; row < rowSize - 1 ; row++) {
            for(int col = 0; col < colSize; col++) {
                for(int xOffset = row + 1, yOffset = col + 1; xOffset < rowSize && yOffset < colSize; xOffset++, yOffset++) {
                    int currentSize = isMagicSquare(a, row, col, xOffset, yOffset);
                    maxMagicSquareSize = Math.max(currentSize, maxMagicSquareSize);
                }
            }
        }
        System.out.println(maxMagicSquareSize);
    }
}










