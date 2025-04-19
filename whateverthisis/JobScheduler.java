import java.io.*;
import java.util.*;
import java.text.*;

class JobCollect {
    String jobName;
    int jobID;
    int jobDuration;
    String timestamp;
    Date deadline;  // Change to Date
    int completionTime;

    public JobCollect(String jobName, int jobID, int jobDuration, Date deadline, String timestamp) {
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

        File[] files = folder.listFiles((dir, name) -> name.endsWith(".txt"));
        if (files == null || files.length == 0) {
            System.out.println("Error: No job files found.");
            return jobList;
        }

        SimpleDateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy"); // Adjust date format as needed

        for (File file : files) {
            try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                String line;
                String jobName = "", timestamp = "";
                int jobID = 0, jobDuration = 0;
                Date deadline = null;

                while ((line = br.readLine()) != null) {
                    if (line.startsWith("Job_Name=")) {
                        jobName = line.split("=")[1].trim();
                    } else if (line.startsWith("Client_ID=")) {
                        try {
                            jobID = Integer.parseInt(line.split("=")[1].trim());
                        } catch (NumberFormatException e) {
                            System.out.println("Invalid job ID format in file " + file.getName());
                        }
                    } else if (line.startsWith("Job_Duration=")) {
                        String durationStr = line.split("=")[1].trim().split(" ")[0];
                        try {
                            jobDuration = Integer.parseInt(durationStr);
                        } catch (NumberFormatException e) {
                            System.out.println("Invalid job duration format in file " + file.getName());
                        }
                    } else if (line.startsWith("Job_Deadline=")) {
                        String deadlineStr = line.split("=")[1].trim();
                        try {
                            deadline = dateFormat.parse(deadlineStr);  // Parse as a Date if it's a date
                        } catch (ParseException e) {
                            System.out.println("Invalid deadline format in file " + file.getName());
                        }
                    } else if (line.startsWith("Timestamp=")) {
                        timestamp = line.split("=")[1].trim();
                    }

                    if (!jobName.isEmpty() && jobDuration > 0 && deadline != null && !timestamp.isEmpty()) {
                        jobList.add(new JobCollect(jobName, jobID, jobDuration, deadline, timestamp));
                        jobName = ""; timestamp = ""; jobID = 0; jobDuration = 0; deadline = null;
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
