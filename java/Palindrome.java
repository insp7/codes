import java.io.BufferedReader;
import java.io.InputStreamReader;

import java.io.IOException;

/**
 * Program to check if a given string is a palindrome.
 * Ex. 'radar' is a palindrome; 'hello' is not a palindrome.
 * 
 * @author insp7
 */
class Palindrome { 

	/**
	 * Function to check whether the given string is a palindrome.
	 * 
	 * @param  inputString Specifies the input string 
	 * @return             returns true if the given input string is a palindrome, else returns false
	 */
	private static boolean isPalindrome(String inputString) {
		for(int i = 0, j = inputString.length() - 1; i <= inputString.length() - 1 && i <= j; i++, j--) {
			if(inputString.charAt(i) == inputString.charAt(j))
				continue;
			else {
				return false;
			}
		}
		return true;
	}

	public static void main(String[] args) throws IOException, Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		System.out.println("Enter the string :");
		String s = br.readLine();

		if(isPalindrome(s))
			System.out.println(s+" is a palindrome");
		else
			System.out.println(s+" is not a palindrome");
	}
}