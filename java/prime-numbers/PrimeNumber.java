import java.io.BufferedReader;
import java.io.InputStreamReader;

import java.io.IOException;

/**
 * Simple program to find prime numbers within a given range.
 * Functions for checking primality of a number using traditional and trial Division approaches are written.
 *
 * @author insp7
 */
class PrimeNumber {

	/**
	 * Function to check if the given number is prime.
	 * 
	 * @param  number Specifies the passed number
	 * @return        returns true if the given number is prime, else returns false.
	 */
	private static boolean isPrime(long number) {
		if(number == 2) return true; // Smallest prime number.
		if(number % 2 == 0) return false; // check if its an even number; if true, return false; since all even numbers are composite.

		for(long i = 3L; i <= number/2; i += 2) {
			if(number % i == 0) return false;
		}
		return true; 
	}

	/**
	 * Function to check if the given number is prime using Trial Division algorithm;
	 * If any prime number whose square does not exceed 'number' divides it without a remainder, then 'number' is not prime.
	 * 
	 * @param  number Specifies the passed number
	 * @return        returns true if the given number is prime, else returns false.
	 */
	private static boolean isPrimeUsingTrialDivision(long number) {
		if(number == 2) return true; // Smallest prime number.
		if(number % 2 == 0) return false; // check if its an even number; if true, return false; since all even numbers are composite.

		for(long i = 3L; (i * i) <= number; i += 2) {
			if(number % i == 0) return false;
		}
		return true; 
	}

	public static void main(String[] args) throws IOException, Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		System.out.println("Enter the Range : ");
		long lowerbound = Long.parseLong(br.readLine());
		long upperbound = Long.parseLong(br.readLine());

		System.out.println("The prime numbers between "+lowerbound+" & "+upperbound+" are :");

		if(lowerbound == 1) {
			lowerbound++; // since 1 is neither prime nor composite.
			System.out.println(lowerbound++); // print the smallest prime number i.e. 2 and then increment it 
		} else if(lowerbound == 2) {
			System.out.println(lowerbound++);
		}

		for(long i = lowerbound; i <= upperbound; i += 2) {
			if(isPrimeUsingTrialDivision(i))
				System.out.println(i);
		}
	}
}