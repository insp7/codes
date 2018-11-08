import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

/* To find prime numbers within a given range */
class PrimeNumber {

	/**
	 * Function to check if the given number is prime.
	 * 
	 * @param  number Specifies the passed number
	 * @return        returns true if the given number is prime, else returns false.
	 */
	private static boolean isPrime(int number) {
		if(number == 2) return true; // Smallest prime number.
		if(number % 2 == 0) return false; // check if its an even number; if true, return false; since all even numbers are composite.

		for(int i = 3; i < number; i+=2) {
			if(number % i == 0) return false;
		}
		return true; 
	}

	public static void main(String[] args) throws IOException, Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		System.out.println("Enter the Range : ");
		int lowerbound = Integer.parseInt(br.readLine());
		int upperbound = Integer.parseInt(br.readLine());

		System.out.println("The prime numbers between "+lowerbound+" & "+upperbound+" are :");

		if(lowerbound == 1) lowerbound++; // since 1 is neither prime nor composite.

		for(int i = lowerbound; i <= upperbound; i++) {
			if(isPrime(i))
				System.out.println(i);
		}
	}
}