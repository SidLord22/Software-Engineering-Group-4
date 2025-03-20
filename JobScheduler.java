import java.io.*;
import java.util.*;

class Job {
    String name;
    int clientId;
    int duration;
    int deadline;
    String timestamp;
    int completionTime;

    public Job(String name, int clientId, int duration, int deadline, String timestamp) {
        this.name = name;
        this.clientId = clientId;
        this.duration = duration;
        this.deadline = deadline;
        this.timestamp = timestamp;
    }
}

public class JobScheduler {
    public static void main(String[] args) {
        List<Job> jobList = new ArrayList<>();
        File folder = new File(System.getProperty("user.home") + "/Desktop/VCRTS-DATA");
        
        if (!folder.exists() || !folder.isDirectory()) {
            System.out.println("Folder not found: " + folder.getAbsolutePath());
            return;
        }
        
        File[] files = folder.listFiles((dir, name) -> name.endsWith(".txt"));
        if (files == null || files.length == 0) {
            System.out.println("No job files found in folder.");
            return;
        }
        
        for (File file : files) {
            try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                String line;
                String name = "", timestamp = "";
                int clientId = 0, duration = 0, deadline = 0;
                
                while ((line = br.readLine()) != null) {
                    if (line.startsWith("Job_Name=")) {
                        name = line.split("=")[1].trim();
                    } else if (line.startsWith("Client_ID=")) {
                        clientId = Integer.parseInt(line.split("=")[1].trim());
                    } else if (line.startsWith("Job_Duration=")) {
                        String durationStr = line.split("=")[1].trim().split(" ")[0];
                        duration = Integer.parseInt(durationStr);
                    } else if (line.startsWith("Job_Deadline=")) {
                        deadline = Integer.parseInt(line.split("=")[1].trim());
                    } else if (line.startsWith("Timestamp=")) {
                        timestamp = line.split("=")[1].trim();
                    }
                    
                    if (!name.isEmpty() && duration > 0 && !timestamp.isEmpty()) {
                        jobList.add(new Job(name, clientId, duration, deadline, timestamp));
                        name = ""; timestamp = ""; clientId = 0; duration = 0; deadline = 0;
                    }
                }
            } catch (IOException e) {
                System.out.println("Error reading file " + file.getName() + ": " + e.getMessage());
            }
        }
        
        jobList.sort(Comparator.comparing(j -> j.timestamp));
        
        int currentTime = 0;
        System.out.println("Job Completion Times:");
        for (Job job : jobList) {
            currentTime += job.duration;
            job.completionTime = currentTime;
            System.out.println("Job Name: " + job.name + " | Client ID: " + job.clientId + " | Completion Time: " + job.completionTime);
        }
    }
}