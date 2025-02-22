import java.io.*;
import java.util.*;
import java.time.*;
import java.time.format.DateTimeFormatter;
// Sidney: This Java Project is made to create and save a folder with data files recording the login information, seperated between
// Job information and Vechicle information, which are both put into two different files, along with the date and time.
public class FileCreationFinal {
    
	// Matthew: Returns a string of the current date and time with format dd-mm-yyHH-mm-ss. This format is filename friendly
    public static String stringDateTime() {
        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy_HH-mm-ss");
        return currentDateTime.format(formatter);
    }

    // Sidney: This pulls in the user's directory
    public static String getDesktopPath() {
        return System.getProperty("user.home") + File.separator + "Desktop";
    }

    // Sidney: This creates the folder if it doesn't already exist
    public static String createFolder(String folderName) {
        String folderPath = getDesktopPath() + File.separator + folderName;
        File folder = new File(folderPath);
        if (!folder.exists() && folder.mkdir()) {
            System.out.println("Folder '" + folderName + "' created successfully.");
        }
        return folderPath;
    }

    // Sidney: This is to that the filename continues to increase incrementally so there can be more than one report file
    public static String generateIncrementalFilename(String folderPath, String baseName, String extension) {
        int fileNumber = 1;
        File file;
        do {
            file = new File(folderPath + File.separator + baseName + "_" + fileNumber + extension);
            fileNumber++;
        } while (file.exists());
        return file.getAbsolutePath();
    }

 // Matthew: Creates a file with the properties of the vehicle owner with filename format 
 // VehicleOwner with a current timestamp concatenated to the end of it Its a text file, 
 // and it contains owner id, make, model, vin of the car and the duration of stay for the car	
    public static void vehicleOwnerFileCreate(String folderPath, String ownerID, String make, 
    		String model, String vin, String durationOfStay) {
        String filePath = generateIncrementalFilename(folderPath, "VehicleOwner", ".txt");
        Properties properties = new Properties();
        String timeStamp = stringDateTime();

        properties.setProperty("Owner_ID", ownerID);
        properties.setProperty("Manufacturer", make);
        properties.setProperty("Model", model);
        properties.setProperty("VIN", vin);
        properties.setProperty("Job Duration", durationOfStay);
        properties.setProperty("Timestamp", timeStamp);

        try (FileWriter writer = new FileWriter(filePath)) {
            properties.store(writer, "Vehicle Owner Information - " + timeStamp);
            System.out.println("Vehicle owner file saved: " + filePath);
        } catch (IOException e) {
            System.out.println("Error writing vehicle owner file.");
            e.printStackTrace();
        }
    }

    // Matthew: Creates a file with the properties of the job with  filename format JobOwner with a current 
    // timestamp concatenated to the end of it Its a text file, and it containts owner id, make, 
    // model,vin of the car and the duration of stay for the car
    public static void jobOwnerFileCreate(String folderPath, String clientID, String jobName, 
    		String jobDuration, String jobDeadline) {
        String filePath = generateIncrementalFilename(folderPath, "JobOwner", ".txt");
        Properties properties = new Properties();
        String timeStamp = stringDateTime();

        properties.setProperty("Client_ID", clientID);
        properties.setProperty("Job_Name", jobName);
        properties.setProperty("Job_Duration", jobDuration);
        properties.setProperty("Job_Deadline", jobDeadline);
        properties.setProperty("Timestamp", timeStamp);

        try (FileWriter writer = new FileWriter(filePath)) {
            properties.store(writer, "Job Owner Information - " + timeStamp);
            System.out.println("Job owner file saved: " + filePath);
        } catch (IOException e) {
            System.out.println("Error writing job owner file.");
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Sidney: This collects all of the vehicle owner data
        System.out.println("Enter Vehicle Owner ID:");
        String vehicleOwnerID = scanner.nextLine();
        System.out.println("Enter Vehicle Maker:");
        String make = scanner.nextLine();
        System.out.println("Enter Vehicle Model:");
        String model = scanner.nextLine();
        System.out.println("Enter Vehicle VIN:");
        String vin = scanner.nextLine();
        System.out.println("Enter Duration of Stay:");
        String stayLength = scanner.nextLine();

        // Sidney: And this collects all of the job owner data
        System.out.println("Enter Client ID:");
        String clientID = scanner.nextLine();
        System.out.println("Enter Job Name:");
        String jobName = scanner.nextLine();
        System.out.println("Enter Job Duration:");
        String jobDuration = scanner.nextLine();
        System.out.println("Enter Job Deadline:");
        String deadline = scanner.nextLine();
        scanner.close();

        // Sidney: Creates folder on desktop
        String folderPath = createFolder("Reports");

        // Sidney: Saves details to files
        vehicleOwnerFileCreate(folderPath, vehicleOwnerID, make, model, vin, stayLength);
        jobOwnerFileCreate(folderPath, clientID, jobName, jobDuration, deadline);
    }
}
