import java.util.HashMap;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigInteger;

import java.io.IOException;

/**
 * Simple Implementation of RSA public-key cryptosystem.
 * In such a cryptosystem, the encryption key is public and it is different from the decryption key which is kept secret (private).
 * RSA algorithm steps: key generation, encryption and decryption.
 * The process of Key distribution is also very important. 
 * For now, assuming that the keys will be distributed reliable and safe routes.
 *
 * @author insp7
 */
class RSA {

	private static BufferedReader reader;

	/**
	 * Function to check if the given number is prime using Trial Division algorithm;
	 * If any prime number whose square does not exceed 'number' divides it without a remainder, then 'number' is not prime.
	 * 
	 * @param  number Specifies the passed number
	 * @return        returns true if the given number is prime, else returns false.
	 */
	private static boolean isPrime(int number) {
		if(number == 2) return true; // Smallest prime number.
		if(number % 2 == 0) return false; // check if its an even number; if true, return false; since all even numbers are composite.

		for(int i = 3; (i * i) <= number; i += 2) {
			if(number % i == 0) return false;
		}
		return true; 
	}

	/**
	 * Function to get factors of the given number.
	 * 
	 * @param  number The specified number
	 * @return        returns an ArrayList consisting of factors of number
	 */
	private static ArrayList<Integer> getFactors(int number) {
		ArrayList<Integer> factorsFinal;// to store final factors
		
		// BASE CASE
		if(number == 1) {
			factorsFinal = new ArrayList<Integer>(1);
			factorsFinal.add(1);
			return factorsFinal;
		}

		int factors[] = new int[number]; 
        int j = 0, size = 0;

        for (int i = 1; (i * i) <= number; i++) { 
            if (number % i == 0) { 
                if ((number / i) == i) // check if divisor and quotient is equal 
              		factors[j++] = i; // Add only once.
                else { 
                	// Divisor and quotient is not equal; hence, add both.
                    factors[j++] = i;
                    factors[j++] = number / i;
                } 
            } 
        } 

       	for(int i = 0; i <= factors.length - 1 && factors[i] != 0; i++, size++); // Calculate number of elements stored in the factors array. 

       	factorsFinal = new ArrayList<Integer>(size); // initial capacity = size;
       	for(int i = 0; i < size; i++) {
       		factorsFinal.add(factors[i]);
       	}
       	return factorsFinal;
	}

	/**
	 * Function to generate RSA public-key('e' for encryption) and private-key('d' for decryption)
	 *  
	 * @param p the specified prime number.
	 * @param q the specified prime number.
	 * @return returns a HashMap<String, Integer> object which consists of all the required RSA variables.
	 */
	private static HashMap<String, Integer> generateRSAKeys(int p, int q) throws IOException {
		reader  = new BufferedReader(new InputStreamReader(System.in));
		
		/**
		 * rsaVariablesMap: 
		 * Contains public-key(aka Encryption variable 'e'), private-key(aka Decryption variable 'd'), 
		 * common-modulus 'n' & phi-of-n(equivalent to (p-1)*(q-1))
		 */
		HashMap<String, Integer> rsaVariablesMap = new HashMap<String, Integer>();
		rsaVariablesMap.put("common-modulus-n", p * q); // Calculate value of modulus N and map it to key.
		rsaVariablesMap.put("phi-of-n", (p - 1) * (q - 1)); // Calculate value of phi-of-N and map it to key.

		ArrayList<Integer> encryptionKeysList = new ArrayList<Integer>(); // List of encryption keys
		ArrayList<Integer> phiOfNFactors = getFactors(rsaVariablesMap.get("phi-of-n")); // get factors of phi-of-N
		ArrayList<Integer> currentNumberFactors;
		
		int tempD, tempE; // temporary private & public keys i.e. e & d
		boolean flag;

		// Choose 'e' such that 1 < e < phi-of-n; hence we check numbers (2, 3, 4, ... ,phi-of-n)
		for(int i = 2; i < rsaVariablesMap.get("phi-of-n"); i++) {
			flag = true; // SET 'true' AGAIN FOR CHECKING NEXT 'i'
			currentNumberFactors = getFactors(i); // get factors of 'i'

			for(int k = 1; k <= currentNumberFactors.size() - 1; k++) { // Start from index 1 i.e. 2nd element of the ArrayList; because we exclude 1.
				if(phiOfNFactors.contains(currentNumberFactors.get(k))) { // check if k is not coprime with phi-of-N
					flag = false; // k is not coprime with phi-of-N, so set 'false' & break i.e. now go to next 'i'.
					break;
				}
			}

			if(flag) {
				// this means that this 'i' is coprime with phi-of-N; 
				// hence, add to the encryptionKeysList as a candidate for selection of 'e'.
				encryptionKeysList.add(i); 			
			}
		}

		System.out.println("Choose a number for encryption(e) i.e. public-key from one of the following numbers: ");
		System.out.println(encryptionKeysList); // Show the list of public-keys 'e'.
		tempE = Integer.parseInt(reader.readLine()); // let the user select an 'e' from the given list.

		if(encryptionKeysList.contains(tempE)) 
			rsaVariablesMap.put("public-key", tempE);
		else {
			System.out.println("Valid key not selected!");
			System.exit(1);
		}

		// ******** NOW CHOOSE 'private-key' ******** 
		// SELECT 'd' aka decryption or private key, 
		// such that d*e (mod phi-of-N) = 1;
		tempD = 1;
		while(true) {
			int tempProduct = tempD * tempE;
			int tempModNOfProduct = tempProduct % rsaVariablesMap.get("phi-of-n");

			if(tempModNOfProduct == 1) {
				rsaVariablesMap.put("private-key", tempD);
				break;
			}
			tempD++;
		}

		return rsaVariablesMap;
	}

	/**
	 * Function to encrypt given 'message' using RSA encryption method
	 * 
	 * @param  message the plain-text to be encrypted
	 * @param  e       encryption variable 'e' aka public-key
	 * @param  n       the common modulus number 
	 * @return         encrypted message aka cipher-text
	 */
	private static BigInteger rsaEncryption(BigInteger message, int e, int n) {
		BigInteger commonModulus = BigInteger.valueOf(n);
		BigInteger powerCalculation = message.pow(e);
		powerCalculation = powerCalculation.mod(commonModulus);
		return powerCalculation;
	}

	/**
	 * Function to decrypt given 'ciphertext' using RSA decryption method
	 * 
	 * @param  cipherText the cipherText to be decrypted
	 * @param  d          decryption variable 'd' aka private-key
	 * @param  n          the common modulus number 
	 * @return            the original message aka plain-text
	 */
	private static BigInteger rsaDecryption(BigInteger cipherText, int d, int n) {
		BigInteger commonModulus = BigInteger.valueOf(n);
		BigInteger powerCalculation = cipherText.pow(d);
		powerCalculation = powerCalculation.mod(commonModulus);
		return powerCalculation;
	}

	public static void main(String[] args) throws IOException, Exception {
		reader = new BufferedReader(new InputStreamReader(System.in));

		System.out.println("Enter prime values for p & q:");
		int p = Integer.parseInt(reader.readLine());
		int q = Integer.parseInt(reader.readLine());

		if(isPrime(p) && isPrime(q)) {
			HashMap<String, Integer> rsaVariablesMap = generateRSAKeys(p, q);
			System.out.println(rsaVariablesMap); // Show the rsa variables

			System.out.println("Enter the message integer to be encrypted:");
			BigInteger message = new BigInteger(reader.readLine());

			BigInteger cipherText = rsaEncryption(message, rsaVariablesMap.get("public-key"), rsaVariablesMap.get("common-modulus-n"));
			System.out.println("CipherText = "+cipherText);

			System.out.println("Performing Decryption again to check if message and decrypted text match...");
			// compare the decrypted message with the original message; if both are equal 'compareTo' method returns 0;
			if(message.compareTo(rsaDecryption(cipherText, rsaVariablesMap.get("private-key"), rsaVariablesMap.get("common-modulus-n"))) == 0) {
				System.out.println("Decryption Successful! The original number is "+message);
			}
		} else {
			System.out.println("Values for p & q must be PRIME only!");
		}
	}
}


/*
	------------------------- SIGNIFICANCE OF LOAD FACTOR IN HASHMAP -------------------------

	Default initial capacity of the HashMap takes is 16 and load factor is 0.75f (i.e 75% of current map size). 
	The load factor represents at what level the HashMap capacity should be doubled.
	For example product of capacity and load factor as 16 * 0.75 = 12. 
	This represents that after storing the 12th key – value pair into the HashMap , its capacity becomes 32.
	NOTE: The above explanation is an answer taken from: https://stackoverflow.com/questions/10901752/what-is-the-significance-of-load-factor-in-hashmap
 */