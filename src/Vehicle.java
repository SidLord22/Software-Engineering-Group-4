import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Properties;
import java.util.Random;
public class Vehicle {

    private String vehicleID;
    private String manufacturer;
    private String model;
    private int duration;
    private String VIN;
    private String vehicleOwnerID;

    //Constructor for the Vehicle class
    public Vehicle(String vehicleID, String manufacturer, String model, String VIN, String vehicleOwnerID) {
        this.vehicleID = vehicleID;
        this.manufacturer = manufacturer;
        this.model = model;
        this.duration = generateRandomDuration();
        this.VIN = VIN;
        this.vehicleOwnerID = vehicleOwnerID;
    }

    /*
    Generates random duration until we have a better way of getting it
     */
    private int generateRandomDuration() {
        return new Random().nextInt(30) + 1; // Random value between 1 and 30
    }

    //Saves the info into a Vehicle text file
    public void saveToFileVehicle() {
        String filePath = createUpdateFile("Vehicle.txt");
        Properties properties = new Properties();
        String timeStamp = timeStampVehicle();
        properties.setProperty("Vehicle_ID" , vehicleID);
        properties.setProperty("Manufacturer" , manufacturer);
        properties.setProperty("Model" , model);
        properties.setProperty("Duration" , String.valueOf(duration));
        properties.setProperty("VIN" , VIN);
        properties.setProperty("Vehicle_Owner_ID" , vehicleOwnerID);
        properties.setProperty("TimeStamp" , timeStamp);

        try(FileWriter writer = new FileWriter(filePath , true)) {
            properties.store(writer , "Vehicle Information - " + timeStamp);
            System.out.println("Job file updated: " + filePath);
        } catch (IOException e) {
            System.out.println("Error writing job file.");
            e.printStackTrace();
        }
    }

    //Creates a Vehicle file to store data, or add onto an existing file
    public static String createUpdateFile(String fileName) {
        String filePath = System.getProperty("user.home") + File.separator + "Desktop" + File.separator + fileName;
        File file = new File(filePath);
        try {
            if (file.createNewFile()) {
                System.out.println("File created: " + filePath);
            }
            else {
                System.out.println("File exists, appending data.");
            }
        } catch (IOException e) {
            System.out.println("Error creating or accessing file.");
            e.printStackTrace();
        }
        return filePath;
    }

    //Creates timestamp
    public static String timeStampVehicle() {
        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy_HH-mm-ss");
        return currentDateTime.format(formatter);
    }
}
