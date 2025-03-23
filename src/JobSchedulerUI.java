import javax.swing.*;
import java.awt.*;
import java.util.List;

public class JobSchedulerUI {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Cloud Controller - Job Records");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(500, 500);

            JPanel panel = new JPanel();
            panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
            List<JobCollect> jobs = JobScheduler.getJobs();
            

            if (jobs.isEmpty()) {
                panel.add(new JLabel("No job data available."));
            } else {
                for (JobCollect job : jobs) {
                    JPanel jobPanel = new JPanel(new GridLayout(5, 1));
                    jobPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                    jobPanel.add(new JLabel("Job ID: " + job.jobID));  
                    jobPanel.add(new JLabel("Job Name: " + job.jobName));  
                   // jobPanel.add(new JLabel("Deadline: " + job.deadline));
                    jobPanel.add(new JLabel("Duration: " + job.jobDuration + " mins"));  
                    jobPanel.add(new JLabel("Timestamp: " + job.timestamp));
                    jobPanel.add(new JLabel("Completion Time: "+ job.completionTime));
                    panel.add(jobPanel);
                }
            }
            

            frame.add(new JScrollPane(panel));
            frame.setVisible(true);
        });
    }

	private static void add(JPanel backgroundPanel) {
		// TODO Auto-generated method stub
		
	}

	private static int getHeight() {
		// TODO Auto-generated method stub
		return 0;
	}

	private static int getWidth() {
		// TODO Auto-generated method stub
		return 0;
	}
}
