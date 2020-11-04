package codility;

import java.util.Date;

public class BinaryGap {

    private static int[] decimalToBinary(int n) {
        int[] binaryNum = new int[31];
        int[] orderedBinNum = new int[31];
        int i, j;
        i = j = 0;

        while (n > 0) {
            binaryNum[i] = n % 2;
            n = n / 2;
            i++;
        }

        for (i = binaryNum.length - 1; i >= 0; i--, j++) {
            orderedBinNum[j] = binaryNum[i];
        }
        return orderedBinNum;
    }

    public static void main(String[] args) {
        long startTime = new Date().getTime();

        int number = 2047483647; 
        int[] binaryNum = decimalToBinary(number);

        System.out.println("The binary number is: ");
        for (int j : binaryNum) { System.out.print(j); }
        System.out.println();

        boolean startCounting = false;
        int count, maxCount;
        count = maxCount = 0;

        // main code
        for (int i = 0; i < binaryNum.length; i++) {
            if(binaryNum[i] == 1) {
                startCounting = true;

                if(count > maxCount) maxCount = count;

                if(i-1 >= 0 && binaryNum[i-1] == 0) count = 0;

            } else if(binaryNum[i] == 0 && startCounting) {
                count++;
            }
//            System.out.println("binaryNum[" + i + "] = " + binaryNum[i] + " Count = " + count + " MaxCount = " + maxCount);
        }

        System.out.println("Binary Gap: " + maxCount);

        long endTime = new Date().getTime();
        long executionTime = endTime - startTime;
        System.out.println("Time Elapsed in milliseconds: " + executionTime);
    }
}
