/* Program to count vowels and consonants */
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

class VowelConsonant {

	/**
	 * Function to check if a given character is a vowel or not.
	 * 
	 * @param {char} x Specifies a character
	 * @return {boolean} returns true if the character 'x' is a vowel, else 'false'.
	 */
	private static boolean isVowel(char x) {
		switch(x){
			case 'a':
			case 'A':
			case 'e':
			case 'E':
			case 'i':
			case 'I':
			case 'o':
			case 'O':
			case 'u':
			case 'U':
				return true;
			default : return false;
		}
	}

	public static void main(String[] args) throws IOException, Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int vowels, consonants;
		vowels = consonants = 0;

		System.out.println("Enter the Data");
		String s = br.readLine();

		for(int i = 0; i <= s.length()-1; i++) {
			if(isVowel(s.charAt(i)))
				vowels++;
			else
				consonants++;
		}

		System.out.println("Vowels = "+vowels);
		System.out.println("Consonants = "+consonants);
	}
}


