import java.io.BufferedReader;
import java.io.InputStreamReader;

import java.io.IOException;

/**
 * The action of a Caesar cipher is to replace each plaintext letter with a different one a fixed number of places down the alphabet. 
 * It is a type of substitution cipher in which each letter in the plaintext is replaced by a letter some fixed number of positions down the alphabet. 
 * For example, with a left shift of 3, D would be replaced by A, E would become B, and so on. 
 * 
 * @author insp7
 */
class CaesarCipher {

	/**
	 * This method performs caesar cipher encryption on 'plainTxt' using 'keyShift';
	 * The 'keyShift' value will be added to each characters of the 'plainTxt' to get the 'cipherTxt'.
	 * 
	 * @param {String} plainTxt Specifies the plain-text string
	 * @param {int} keyShift Specifies the 'Shift' value for the 'plainTxt'
	 * @return {String} returns the Encrypted String.
	 */
	private static String caesarCipherEncrypt(String plainTxt, int keyShift) {
		char[] shiftedChars = new char[plainTxt.length()];

		for(int i=0; i<plainTxt.length(); i++) { 
			int currentCharAsciiValue = (int)plainTxt.charAt(i);
			int temp = currentCharAsciiValue + keyShift;

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
	 * This method performs caesar cipher decryption on 'cipherTxt' using 'keyShift';
	 * The 'keyShift' value will be subtracted from each characters of the 'cipherTxt' to get the 'plainTxt'.
	 * 
	 * @param {String} cipherTxt Specifies the cipher-text string
	 * @param {int} keyShift Specifies the 'Shift' value for the 'cipherTxt'
	 * @return {String} returns the Decrypted String.
	 */
	private static String caesarCipherDecrypt(String cipherTxt, int keyShift) {
		char[] shiftedChars = new char[cipherTxt.length()];

		for(int i=0; i<cipherTxt.length(); i++) { 
			int currentCharAsciiValue = (int)cipherTxt.charAt(i);
			int temp = currentCharAsciiValue - keyShift;

			if(temp < 65 && currentCharAsciiValue >= 65 && currentCharAsciiValue <=90) {
				temp = 91 - (65 - temp); // 65-temp is the difference, subtracted from the end of capital characters' ascii values
			} else if(temp < 97 && currentCharAsciiValue <= 122 && currentCharAsciiValue >= 97) { 
				temp = 123 - (97 - temp); // 97-temp is the difference, subtracted from the end of lowercase characters' ascii values
			}
			shiftedChars[i] = (char)temp;
		}

		return new String(shiftedChars);
	}

	public static void main(String[] args) throws IOException {
		try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in))){
			while(true) {
				int keyShift;
				String message; 

				System.out.println("1.Encrypt\n2.Decrypt\n3.Exit");
				switch(Integer.parseInt(br.readLine())) {
					case 1: System.out.println("Enter the plain-text message:");
							message = br.readLine();

							System.out.println("Enter the Key(Shifting value):");
							keyShift = Integer.parseInt(br.readLine());

							String cipherTxt = caesarCipherEncrypt(message, keyShift); 
							System.out.println("Cipher text: "+cipherTxt);
							break;

					case 2: System.out.println("Enter the encrypted text message:");
							message = br.readLine();

							System.out.println("Enter the Key(Shifting value):");
							keyShift = Integer.parseInt(br.readLine());

							String plainTxt = caesarCipherDecrypt(message, keyShift); 
							System.out.println("Plain text: "+plainTxt);
							break;

					case 3: default: System.exit(1);
				}
			}
		}
	}
}
