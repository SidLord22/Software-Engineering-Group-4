import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Properties;
import java.util.Random;

public class Job {
    private String jobID;
    private String jobName;
    private String description;
    private String deadline;
    private String jobOwnerID;
    private int jobDuration;
    private int redundancyLevel;

    //Constructor for a Job class
    public Job(String jobID, String jobName, String description, String deadline, String jobOwnerID) {
        this.jobID = jobID;
        this.jobName = jobName;
        this.description = description;
        this.deadline = deadline;
        this.jobOwnerID = jobOwnerID;
        this.jobDuration = generateRandomDuration();
        this.redundancyLevel = generateRandomRedundancy();
    }

    /* Generate a random Int for the duration of the Job, can be replaced when we have a
        better way of getting the job duration
     */
    private int generateRandomDuration() {
        return new Random().nextInt(30) + 1; // Random value between 1 and 30
    }

    /*
    Same with the Redundancy level, generate random Int for the redundancy,
    can be replaced when we have a better way of getting it
     */
    private int generateRandomRedundancy() {
        return new Random().nextInt(5) + 1; // Random value between 1 and 5
    }

    //Saves the Job info to a file
    public void saveToFileJob() {
        String filePath = createUpdateFile("JobRecords.txt");
        Properties properties = new Properties();
        String timeStamp = timeStampJob();
        properties.setProperty("Job_ID", jobID);
        properties.setProperty("Job_Name", jobName);
        properties.setProperty("Description", description);
        properties.setProperty("Deadline", deadline);
        properties.setProperty("Job_Owner_ID", jobOwnerID);
        properties.setProperty("Job_Duration", String.valueOf(jobDuration));
        properties.setProperty("Redundancy_Level", String.valueOf(redundancyLevel));
        properties.setProperty("Timestamp", timeStamp);

        try (FileWriter writer = new FileWriter(filePath, true)) { // Append mode
            properties.store(writer, "Job Information - " + timeStamp);
            System.out.println("Job file updated: " + filePath);
        } catch (IOException e) {
            System.out.println("Error writing job file.");
            e.printStackTrace();
        }
    }

    //Create a JobRecord file to store the data, or add the data to an existing file
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

    //Creates timestamp for when the info was registered
    public static String timeStampJob() {
        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy_HH-mm-ss");
        return currentDateTime.format(formatter);
    }
}
