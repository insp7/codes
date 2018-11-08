import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Random;

class OneTimePad {
	private static int[] randomShift; // Array to store the random-shift values of each character. 

	/**
	 * Function to Encrypt the plain-text using Vernam Cipher(aka One-time pad).
	 * 
	 * @param {String} plainTxt Specifies the plain-text 
	 * @return {String} returns encrypted text.
	 */
	private static String encrypt(String plainTxt) {
		Random random = new Random();
		char[] shiftedChars = new char[plainTxt.length()];
		randomShift = new int[plainTxt.length()];

		for(int i=0; i<plainTxt.length(); i++){
			int currentCharAsciiValue = (int)plainTxt.charAt(i), temp;
			
			randomShift[i] = random.nextInt(26) + 1; // Generates a random number between 1 to 26
			temp = currentCharAsciiValue + randomShift[i]; 

			if(temp > 90 && currentCharAsciiValue <= 90) {
				temp = 64 + (temp - 90); // temp-90 is the difference, added to the start of capital characters' ascii values
			} else if(temp > 122 && currentCharAsciiValue <= 122 && currentCharAsciiValue >= 97) { 
				temp = 96 + (temp - 122); // temp-122 is the difference, added to the start of lowercase characters' ascii values
			}
			shiftedChars[i] = (char)temp;
		}

		return new String(shiftedChars);
	}

	/**
	 * Function to Decrypt the cipher-text using Vernam Cipher(aka One-time pad).
	 * 
	 * @param {String} cipherTxt Specifies the cipher-text
	 * @param {int[]} randomShift Specifies the array consisting of random Shifts for each character in the cipher-text
	 * @return {String} returns decrypted text.
	 */
	private static String decrypt(String cipherTxt, int[] randomShift) {
		char[] shiftedChars = new char[cipherTxt.length()];

		for(int i=0; i<cipherTxt.length(); i++){
			int currentCharAsciiValue = (int)cipherTxt.charAt(i), temp;
			
			temp = currentCharAsciiValue - randomShift[i];
			if(temp < 65 && currentCharAsciiValue >= 65 && currentCharAsciiValue <=90) {
				temp = 91 - (65 - temp); // 65-temp is the difference, subtracted from the end of capital characters' ascii values
			} else if(temp < 97 && currentCharAsciiValue <= 122 && currentCharAsciiValue >= 97) {
				temp = 123 - (97 - temp); // 97-temp is the difference, subtracted from the end of lowercase characters' ascii values
			}
			shiftedChars[i] = (char)temp;
		}

		return new String(shiftedChars);
	} 
	
	// Function to print the randomShift values for all the characters.
	private static void printRandomShiftKeys() {
		System.out.println("Random Key Shifts : ");
		for(int i=0; i<randomShift.length; i++){
			System.out.print(randomShift[i]+" ");
		}
		System.out.println();
	}
	
	public static void main(String[] args) throws IOException {
		try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
			while(true) {
				String plainTxt, cipherTxt;

				System.out.println("1.Encrypt\n2.Decrypt\n3.Exit\n");
				switch(Integer.parseInt(br.readLine())) {
					case 1: System.out.println("Enter plaintext:");
							plainTxt = br.readLine();

							cipherTxt = encrypt(plainTxt);
							printRandomShiftKeys();

							System.out.println("Cipher Text: "+cipherTxt);
							break;

					case 2: System.out.println("Enter ciphertext:");
							cipherTxt = br.readLine();

							randomShift = new int[cipherTxt.length()];
							System.out.println("enter the keys");
							for(int i=0; i<cipherTxt.length(); i++) {
								randomShift[i] = Integer.parseInt(br.readLine());
								if(randomShift[i] > 26) {
									System.out.println("Err! Key cannot be greater than 26");
									return; 
								}
							}

							plainTxt = decrypt(cipherTxt, randomShift);
							System.out.println("Plain Text: "+plainTxt);
							break;

					case 3: default: System.exit(1);
				}
			}
		}
	}
}