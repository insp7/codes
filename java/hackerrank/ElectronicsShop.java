package hackerrank;

import java.util.Scanner;
import java.io.IOException;

/**
 * HACKER RANK QUESTION : https://www.hackerrank.com/challenges/electronics-shop/problem
 *
 * @author AniketKonkar
 */
public class ElectronicsShop {
    private static final Scanner scanner = new Scanner(System.in);

    /**
     * Function which returns the maximum amount of money she can spend on a keyboard and USB drive, 
     * or -1 if she can't purchase both items.
     * 
     * @param  keyboards Specifies the cost array of available keyboards
     * @param  drives    Specifies the cost array of available drives
     * @param  budget    Specifies the budget
     * @return           returns the maximum amount of money she can spend on a keyboard and USB drive, or -1 if she can't purchase both items 
     */
    static int getMoneySpent(int[] keyboards, int[] drives, int budget) {
        boolean constraintsSatisfied = true; // Set default to true
        
        // Check first 2 constraints
        if(drives.length >= 1 && drives.length <= 1000 && keyboards.length >= 1 && keyboards.length <= 1000 && budget >= 1 && budget <= 1000000) {
            // Check if each keyboard item's price is in the given constraint range.
            for(int i = 0; i < keyboards.length; i++) {
                if(!(keyboards[i] >= 1 && keyboards[i] <= 1000000)) {
                    constraintsSatisfied = false;
                    break;
                }
            }

            // Check if each drives item's price is in the given constraint range.
            for(int i = 0; i < drives.length; i++) {
                if(!(drives[i] >= 1 && drives[i] <= 1000000)) {
                    constraintsSatisfied = false;
                    break;
                }
            }

            if(constraintsSatisfied) {
                // Main code
                int sum = -1;

                for(int i = 0; i < keyboards.length; i++) {
                    for(int j = 0; j < drives.length; j++) {
                        if((keyboards[i] + drives[j]) <= budget && (keyboards[i] + drives[j]) > sum) {
                            sum = keyboards[i] + drives[j];
                        }
                    }    
                }
                return sum;
            }
        }
        return 0;
    }

    public static void main(String[] args) throws IOException, Exception {
        int[] keyboards = new int[2];
        int[] drives = new int[3];

        // STATIC TESTING
        keyboards[0] = 3; keyboards[1] = 1;
        drives[0] = 5; drives[1] = 2; drives[2] = 8;

        int moneySpent = getMoneySpent(keyboards, drives, 10);
        System.out.println("Money Spent = "+moneySpent);
        scanner.close();
    }
}