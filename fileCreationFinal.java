import java.util.*;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.io.*;

/*Written by: Matthew Ahamad 2/22/2025
 * This is based on the concept of my previous file creation system, 
 * I used the same basic logic of that project, to create three methods.
 * The first just returns the current system time in a more human readable format
 * The second is vehicleOwnerFileCreate void return type, takes in arguements for the ownerID, make, model, vin and duration of stay (all strings
 * Each of these strings are added as a property of a Properties class
 * than this properties class is copied to a txt file with the timestamp at the top and bottom which is then saved
 * The jobOwnerFileCreate works the same way as the vehicleOwnerFileCreate, it just has string inputs for clientID, jobName, jobDuration
 * and jobDeadline instead of the vehicle owner inputs
 */
public class fileCreationFinal {
//Returns a string of the current date and time with format dd-mm-yyHH-mm-ss. This format is filename friendly
	public static String stringDateTime() {
		LocalDateTime currentDateTime = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyyHH-mm-ss");
		String formattedDate = currentDateTime.format(formatter);
		return formattedDate;
	}
// Creates a file with the properties of the vehicle owner with filename format VehicleOwner with a current timestamp concatenated to the end of it
// Its a text file, and it containts owner id, make, model,vin of the car and the duration of stay for the car	
	public static void vehicleOwnerFileCreate(String ownerID, String make, String model, String vin,
			String durationOfStay) {
		String timeStamp = stringDateTime();
		String vehicleOwnerFileName = "VehicleOwner" + timeStamp + ".txt";

		Properties properties = new Properties();
		properties.setProperty("Owner ID", ownerID);
		properties.setProperty("Manufacturer", make);
		properties.setProperty("Model", model);
		properties.setProperty("VIN", vin);
		properties.setProperty("Job Duration", durationOfStay);
		properties.setProperty("Timestamp", timeStamp);

		try (FileWriter writer = new FileWriter(vehicleOwnerFileName)) {
			properties.store(writer, "Vehicle Owner Information entered at"+ timeStamp);
			System.out.println("Successfully wrote to the file.");
		} catch (IOException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}

	}

	// Creates a file with the properties of the job with 
	//filename format JobOwner with a current timestamp concatenated to the end of it
	// Its a text file, and it containts owner id, make, model,vin of the car and the duration 
	//of stay for the car
	
	public static void jobOwnerFileCreate(String clientID,String jobName, String jobDuration, String jobDeadline) {
		String timeStamp = stringDateTime();
		String jobOwnerFileName = "JobOwner" + timeStamp + ".txt";

		Properties properties = new Properties();
		properties.setProperty("Client ID", clientID);
		properties.setProperty("Job Name", jobName);
		properties.setProperty("Job Duration", jobDuration);
		properties.setProperty("Job Deadline", jobDeadline);
		properties.setProperty("Timestamp", timeStamp);

		try (FileWriter writer = new FileWriter(jobOwnerFileName)) {
			properties.store(writer, "Job Owner Information entered at" + timeStamp);
			System.out.println("Successfully wrote to the file.");
		} catch (IOException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {

		Scanner txtInput = new Scanner(System.in);
		System.out.println("Please input vehicle owner ID:");
		String vehicleOwnerID = txtInput.nextLine();
		System.out.println("Please input make:");
		String make = txtInput.nextLine();
		System.out.println("Please input the vehicle model:");
		String model = txtInput.nextLine();
		System.out.println("Please input the vehicle vin:");
		String vin = txtInput.nextLine();
		System.out.println("Please input job duration");
		String stayLength = txtInput.nextLine();

		System.out.println("Please input the client ID:");
		String clientID = txtInput.nextLine();
		System.out.println("Please input the job Name:");
		String jobName = txtInput.nextLine();
		System.out.println("Please input the job duration:");
		String jobDuration = txtInput.nextLine();
		System.out.println("Please input deadline");
		String deadline = txtInput.nextLine();
		txtInput.close();

		vehicleOwnerFileCreate(vehicleOwnerID, make, model, vin, stayLength);
		jobOwnerFileCreate(clientID,jobName, jobDuration, deadline);

	}
}
