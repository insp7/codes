package codility;

/*
QUESTION:
A magic square of size K is a K x K square grid filled with integers
such that the sum of the integers in each row, column and diagonal of the grid is equal.
Note that integers in the magic square do not have to be distinct.
Also note that every square of size 1 is magic.

Given a matrix consisting of N rows and M columns,
return the size of the largest magic square that can be found within this matrix.

More about magic square: https://en.wikipedia.org/wiki/Magic_square
 */

public class MagicSquare {

    /**
     *
     * @param a Represents the input matrix.
     * @param x1 This co-ordinate represents the row from which the square starts.
     * @param y1 This co-ordinate represents the column from which the square starts.
     * @param x2 This co-ordinate represents the row at which the square ends.
     * @param y2 This co-ordinate represents the column at which the square ends.
     * @return Size of this sub matrix if it is a magic square, else  -1.
     */
    public static int isMagicSquare(int[][] a, int x1, int y1, int x2, int y2) {
        int size = x2 - x1 + 1; // get size of the current sub matrix (y2-y1+1 or x2-x1+1)
        int diagonalSum = a[x1][y1];
        int secondDiagonalSum = a[x1][y2];
        int magicSquareSize = -1;

        for (int index = 0, row = x1, column = y1, end = y2 - 1; index < size; row++, column++, end--, index++) {
            if (row != x2) { // to prevent out of bounds indexing
                diagonalSum = diagonalSum + a[row + 1][column + 1]; // calculate sum for each diagonal
                secondDiagonalSum = secondDiagonalSum + a[row + 1][end]; // when row = x2, end = 0
            }
        }

        if(diagonalSum == secondDiagonalSum) { // perform further operations only if both diagonals have equal sum
            int[][] rowSum = new int[size][size];
            int[][] colSum = new int[size][size];

            for (int row = 0, col = 0, xIndex = x1, yIndex = y1; row < size && col < size; row++, col++, xIndex++, yIndex++) {
                rowSum[row][0] = a[xIndex][y1]; // (0, 0) (1, 0), (2, 0) ... (N-1, 0)
                colSum[0][col] = a[x1][yIndex]; // (0, 0) (0, 1), (0, 2) ... (0, N-1)
            }

            for (int xIndex = 0, row = x1; xIndex < size; row++, xIndex++) {
                for (int yIndex = 1, col = y1 + 1; yIndex < size; col++, yIndex++) {
                    rowSum[xIndex][yIndex] = rowSum[xIndex][yIndex-1] + a[row][col]; // calculate sum for each row
                }
                if(diagonalSum != rowSum[xIndex][size-1]) return -1;
            }

            for(int yIndex = 0, col = y1; yIndex < size; yIndex++, col++) {
                for (int xIndex = 1, row = x1 + 1; xIndex < size; row++, xIndex++) {
                    colSum[xIndex][yIndex] = colSum[xIndex-1][yIndex] + a[row][col]; // calculate sum for each column
                }
                if(diagonalSum != colSum[size-1][yIndex]) return -1;
            }

            System.out.println("(x1,y1) => (" + x1 + "," + y1 + ") (x2,y2) => (" + x2 + "," + y2 + ")"); // print the coordinates of this magic square matrix
            magicSquareSize = size;
        }
        return magicSquareSize;
    }

    public static void main(String[] args) {
        int[][] a = new int[][] {
                {16, 16, 16, 16, 16},
                {1, 23, 16, 4, 21},
                {15, 14, 7, 18, 11},
                {24, 17, 13, 9, 2},
                {20, 8, 19, 12, 6},
                {5, 3, 10, 22, 25},
                {7, 7, 7, 7, 7}
        };
        /*
            From the above input, the following matrix is a magic square:
            {1, 23, 16, 4, 21},
            {15, 14, 7, 18, 11},
            {24, 17, 13, 9, 2},
            {20, 8, 19, 12, 6},
            {5, 3, 10, 22, 25}
            Coordinates of this magic square [(x1, y1) to (x2, y2)]: start => (1, 0) end => (5, 4)
         */

        int maxMagicSquareSize = -1;
        int rowSize = a.length;
        int colSize = a[0].length;

        for(int row = 0; row < rowSize - 1; row++) {
            for(int col = 0; col < colSize; col++) {
                for(int xOffset = row + 1, yOffset = col + 1; xOffset < rowSize && yOffset < colSize; xOffset++, yOffset++) {
                    // int currentSubMatrixSize = xOffset - row + 1
                    // if maxMagicSquareSize >= currentSubMatrixSize, then skip checking of this subMatrix
                    if(maxMagicSquareSize >= (xOffset - row + 1))
                        continue;
                    maxMagicSquareSize = Math.max(isMagicSquare(a, row, col, xOffset, yOffset), maxMagicSquareSize);
                }
            }
        }
        System.out.println(maxMagicSquareSize);
    }
}