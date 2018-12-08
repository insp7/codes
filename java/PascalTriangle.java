import java.io.BufferedReader;
import java.io.InputStreamReader;

import java.io.IOException;

/**
 * Program to print Pascal triangle for specified numRows.
 *
 * @author insp7
 */
class PascalTriangle {

	/**
	 * Function to display Pascal triangle pattern 'numRows' number of rows.
	 * 
	 * @param numRows Specifies the number of rows for which the pattern will be displayed.
	 */
	private static void printPascalTriangle(int numRows) {
		int[] output = new int[numRows+1];
		int[] tempOutput = new int[numRows+1];
		output[0] = output[1] = 1;

		//Main code
		if(numRows == 1)
			System.out.println(output[0]);
		else if(numRows == 2) {
			System.out.println(output[0]);
			System.out.println(output[0]+" "+output[1]);
		} else if(numRows > 2) {
			//Print first 2 rows 
			System.out.println(output[0]);
			System.out.println(output[0]+" "+output[1]);

			/* Main Code For printing rest of the rows */
			for(int i = 3; i <= numRows; i++) { 
				for(int j = 0; j < i; j++) {
					// for first element of every row, store 1.
					if(j == 0) { 
						tempOutput[j] = output[j]; 
					} else { 
						// adds the previous plus the current number and store it in the current index
						tempOutput[j] = output[j-1] + output[j];
					}
				}

				// Copies the tempOutput array to the output Array
				for(int x = 0; x < tempOutput.length && tempOutput[x] != 0; x++) { 
					output[x] = tempOutput[x];
				}

				// Prints the tempOutput Array i.e. prints the ith row of the pascal triangle
				for(int k = 0; k < tempOutput.length && tempOutput[k] != 0; k++) { 
					System.out.print(tempOutput[k]+" ");
				}
				System.out.println();	
			}
		}
	}

	public static void main(String[] args) throws IOException, Exception {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

		System.out.println("Enter the number of rows: ");
		int numRows = Integer.parseInt(reader.readLine());

		printPascalTriangle(numRows);
	}
}