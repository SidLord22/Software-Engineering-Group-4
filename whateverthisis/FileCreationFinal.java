import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Properties;
import java.util.Scanner;

public class FileCreationFinal {
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
    public static void vehicleOwnerFileCreate(String folderPath, String ownerID, String firstName, String lastName, String make,
                                              String model, String vin, String durationOfStay) {
        // Set a fixed filename for all vehicle owner data (appended to the same file)
        String filePath = folderPath + File.separator + "VehicleOwner.txt";  // Same file for all data

        Properties properties = new Properties();
        String timeStamp = stringDateTime();

        properties.setProperty("Owner_ID", ownerID);
        properties.setProperty("First_Name", firstName);
        properties.setProperty("Last_Name", lastName);
        properties.setProperty("Make", make);
        properties.setProperty("Model", model);
        properties.setProperty("VIN", vin);
        properties.setProperty("Duration_Of_Stay", durationOfStay);
        properties.setProperty("Timestamp", timeStamp);

        try (FileWriter writer = new FileWriter(filePath, true)) {  // 'true' for appending
            // Add vehicle data to the file in a structured format
            writer.write("Vehicle Owner Information - " + timeStamp + "\n");
            properties.store(writer, "");  // Appends to the file
            writer.write("\n");  // Add a newline after each entry
            System.out.println("Vehicle owner data appended to: " + filePath);
        } catch (IOException e) {
            System.out.println("Error writing vehicle owner file.");
            e.printStackTrace();
        }
    }

    // Matthew: Creates a file with the properties of the job with  filename format JobOwner with a current
    // timestamp concatenated to the end of it Its a text file, and it containts owner id, make,
    // model,vin of the car and the duration of stay for the car
    public static void jobOwnerFileCreate(String folderPath, String clientID, String firstName, String lastName, String jobName,
                                          String jobDuration, String jobDeadline) {
        // Set a fixed filename for all job owner data (appended to the same file)
        String filePath = folderPath + File.separator + "JobOwner.txt";  // Same file for all data

        Properties properties = new Properties();
        String timeStamp = stringDateTime();

        properties.setProperty("Client_ID", clientID);
        properties.setProperty("Job_Name", jobName);
        properties.setProperty("First_Name", firstName);
        properties.setProperty("Last_Name", lastName);
        properties.setProperty("Job_Duration", jobDuration);
        properties.setProperty("Job_Deadline", jobDeadline);
        properties.setProperty("Timestamp", timeStamp);

        try (FileWriter writer = new FileWriter(filePath, true)) {  // 'true' for appending
            // Add job data to the file in a structured format
            writer.write("Job Owner Information - " + timeStamp + "\n");
            properties.store(writer, "");  // Appends to the file
            writer.write("\n");  // Add a newline after each entry
            System.out.println("Job owner data appended to: " + filePath);
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
        System.out.println("Enter First Name:");
        String firstName = scanner.nextLine();
        System.out.println("Enter Last Name:");
        String lastName = scanner.nextLine();
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
        System.out.println("Enter First Name:");
        String firstName1 = scanner.nextLine();
        System.out.println("Enter Last Name:");
        String lastName1 = scanner.nextLine();
        System.out.println("Enter Job Duration:");
        String jobDuration = scanner.nextLine();
        System.out.println("Enter Job Deadline:");
        String deadline = scanner.nextLine();
        scanner.close();

        // Sidney: Creates folder on desktop
        String folderPath = createFolder("Reports");

        // Sidney: Saves details to files
        vehicleOwnerFileCreate(folderPath, vehicleOwnerID, firstName,lastName, make, model, vin, stayLength);
        jobOwnerFileCreate(folderPath, clientID, firstName1, lastName1, jobName, jobDuration, deadline);
    }
}
