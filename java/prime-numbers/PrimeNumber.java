import java.io.BufferedReader;
import java.io.InputStreamReader;

import java.io.IOException;

/**
 * Simple program to find prime numbers within a given range.
 * Functions for checking primality of a number using traditional and trial Division approaches are written.
 *
 * @author AniketKonkar
 */
class PrimeNumber {

	/**
	 * Function to check if the given number is prime.
	 * 
	 * @param  n Specifies the passed number
	 * @return        true if the given number is prime, else false.
	 */
	private static boolean isPrime(long n) {
		if(n == 2) return true; // Smallest prime number.
		if(n % 2 == 0) return false; // check if its an even number; if true, return false; since all even numbers are composite.

		for(long i = 3L; i <= n/2; i += 2) {
			if(n % i == 0) return false;
		}
		return true; 
	}

	/**
	 * Function to check if the given number is prime using Trial Division algorithm;
	 * If any prime number whose square does not exceed 'n' divides it without a remainder, then 'n' is not prime.
	 * 
	 * @param  n Specifies the passed number
	 * @return        true if the given number is prime, else false.
	 */
	private static boolean isPrimeUsingTrialDivision(long n) {
		if(n == 2) return true; // Smallest prime number.
		if(n % 2 == 0) return false; // check if its an even number; if true, return false; since all even numbers are composite.

		for(long i = 3L; (i * i) <= n; i += 2) {
			if(n % i == 0) return false;
		}
		return true; 
	}

	/**
	 * Optimized approach for trial division.
	 * @param  n Specifies the passed number
	 * @return   true if the given number is prime, else false.
	 */
	public static boolean isPrimeUsingOptimizedTrialDivision(long n) {
	     if(n ≤ 3)
	        return n > 1;
	     else if(n % 2 == 0 or n % 3 == 0)
	        return false;

	     long i = 5;
	     while(i * i ≤ n)
	        if(n % i == 0 || n % (i + 2) == 0)
	            return false;
	        i = i + 6;
	     return true;
	}

	public static void main(String[] args) throws IOException, Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		System.out.println("Enter the Range : ");
		long lowerbound = Long.parseLong(br.readLine());
		long upperbound = Long.parseLong(br.readLine());

		System.out.println("The prime numbers between " + lowerbound + " & " + upperbound + " are :");

		if(lowerbound == 1) {
			lowerbound++; // since 1 is neither prime nor composite.
			System.out.println(lowerbound++); // print the smallest prime number i.e. 2 and then increment it 
		} else if(lowerbound % 2 == 0) {
			System.out.println(lowerbound++);
		}

		for(long i = lowerbound; i <= upperbound; i += 2) {
			if(isPrimeUsingTrialDivision(i))
				System.out.println(i);
		}
	}
}