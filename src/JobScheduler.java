import java.io.*;
import java.util.*;

class JobCollect {
    String jobName;
    int jobID;
    int jobDuration;
    String deadline;
    String timestamp;
    int completionTime;

    public JobCollect(String jobName, int jobID, int jobDuration, String deadline, String timestamp) {
        this.jobName = jobName;
        this.jobID = jobID;
        this.jobDuration = jobDuration;
        this.deadline = deadline;
        this.timestamp = timestamp;
    }
}

public class JobScheduler {
    public static List<JobCollect> getJobs() {
        List<JobCollect> jobList = new ArrayList<>();
        File folder = new File(System.getProperty("user.home") + "/Desktop/VCRTS-DATA");
        
        if (!folder.exists() || !folder.isDirectory()) {
            System.out.println("Error: Folder not found: " + folder.getAbsolutePath());
            return jobList;
        }
        
        File[] files = folder.listFiles((dir, name) -> name.equals("JobOwner.txt"));
        if (files == null || files.length == 0) {
            System.out.println("Error: No job files found.");
            return jobList;
        }
        
        for (File file : files) {
            try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                String line;
                String jobName = "", timestamp = "";
                int jobID = 0, jobDuration = 0;
				String deadline = "";
                
                while ((line = br.readLine()) != null) {
                    if (line.startsWith("Job_Name=")) {
                        jobName = line.split("=")[1].trim();
                    } else if (line.startsWith("Client_ID=")) {
                        jobID = Integer.parseInt(line.split("=")[1].trim());
                    } else if (line.startsWith("Job_Duration=")) {
                        String durationStr = line.split("=")[1].trim().split(" ")[0];
                        jobDuration = Integer.parseInt(durationStr);
                    } else if (line.startsWith("Job_Deadline=")) {
                        deadline =  line.split("=")[1].trim();
                    } else if (line.startsWith("Timestamp=")) {
                        timestamp = line.split("=")[1].trim();
                    }
                    
                    if (!jobName.isEmpty() && jobDuration > 0 && !timestamp.isEmpty()) {
                        jobList.add(new JobCollect(jobName, jobID, jobDuration, deadline, timestamp));
                        jobName = ""; timestamp = ""; jobID = 0; jobDuration = 0; deadline = "";
                    }
                }
            } catch (IOException e) {
                System.out.println("Error reading file " + file.getName() + ": " + e.getMessage());
            }
        }
        
        jobList.sort(Comparator.comparing(j -> j.timestamp));
        
        int currentTime = 0;
        System.out.println("Job Completion Times:");
        for (JobCollect job : jobList) {
            currentTime += job.jobDuration;
            job.completionTime = currentTime;
            System.out.println("Job Name: " + job.jobName + " | Client ID: " + job.jobID + " | Completion Time: " + job.completionTime);
        }
		return jobList;
    }
}