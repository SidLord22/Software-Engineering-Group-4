import java.util.*;
import java.time.*;
import java.io.*;
/*Written by: Matthew Ahamad 2/19/2025
 * Concept for file creation system which takes some text input, saves it to a text file, and adds a timestam
 * at the bottom of the file
 */
public class FileSystemWitProperties {
    public static void main(String[] args) {
        Scanner txtInput = new Scanner(System.in);
        System.out.println("Please input some text:");
        String userInput = txtInput.nextLine();
        txtInput.close();

        String timeStamp = Instant.now().toString();
        
        Properties properties = new Properties();
        properties.setProperty("UserInput", userInput);
        properties.setProperty("Timestamp", timeStamp);

        try (FileWriter writer = new FileWriter("Test.txt")) {
            properties.store(writer, "User Input with Timestamp");
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
