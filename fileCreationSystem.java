import java.util.*;
import java.time.*;
import java.io.*;


public class fileCreationSystem {
	
	public static void main(String args[]) {
		Scanner txtInput = new Scanner(System.in);  
		System.out.println("Please input some text.");
		String userInput = txtInput.nextLine();
		String timeStamp = Instant.now().toString();
		System.out.println("your input"+ userInput+ " timestamp"+ timeStamp);
		
				try {
					FileWriter myWriter = new FileWriter("Test.txt");
				     
				      myWriter.write(userInput);
				      myWriter.write("\n");
				      myWriter.write(Instant.now().toString());
				      myWriter.close();
				      System.out.println("Successfully wrote to the file.");
				    } catch (IOException e) {
				      System.out.println("An error occurred.");
				      e.printStackTrace();
				    }
	
	}
}
