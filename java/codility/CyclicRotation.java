package codility;

public class CyclicRotation {

    // BASE assumption is that array size & number of rotations is between 0-100 inclusive.
    /**
     * Method to perform cyclic rotation on an array N number of times.
     * @param a The Specified Array to rotate.
     * @param numOfRotations Specifies the total number of rotations to be applied on the array.
     * @return An integer array with respective number of rotations applied is returned.
     */
    public static int[] rotate(int[] a, int numOfRotations) {
        /* We return the array as it is, if
            1. The array is empty
            2. The array has only 1 element
            3. The array's length is equal to the number of rotations
            4. The array's length is a multiple of 5
         */
        if(a.length == 0 || a.length == 1 || a.length == numOfRotations || numOfRotations % a.length == 0)
            return a;

        int i, j;
        int[] rotatedArray = new int[a.length];

        // mod the number of rotation to avoid multiple unnecessary rotations.
        // ex. if its 93, then 93 % 5 = 3, therefore only rotate 3 times.
        numOfRotations = numOfRotations % a.length;

        // traverse some elements of array and shift them to the start of the 'rotatedArray' array
        for(i = a.length - numOfRotations, j = 0; i < a.length; i++)
            rotatedArray[j++] = a[i];

        // remaining elements are traversed and are added to the end of 'rotatedArray' array
        i = 0; // reset i index to point to 0
        while(j < a.length)
            rotatedArray[j++] = a[i++];
        return rotatedArray;
    }

    public static void main(String[] args) {
        int[] rotatedArray = rotate(new int[]{1, 2, 3, 4, 5}, 93);
        for (int j : rotatedArray) System.out.print(j + " ");
    }
}
