import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

class CustomTemplate {

	public static void main(String[] args) { 
		try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {

		} catch(IOException ioe) {
			System.out.println("Err: " + ioe.printStackTrace());
		}
	}
}