import javax.swing.*;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.*;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Properties;

public class cloudController extends JFrame {
    private static Properties jobOwnerData = new Properties();
    private static Properties vehicleOwnerData = new Properties();
    
	public static void main(String args[]) {
		int port = 1234;
		serverLaunch(port);
		
	}
	
public static void serverLaunch(int port) {
		
		String typeOfInput= "Job";
		try (ServerSocket serverSocket = new ServerSocket(port)) {
			System.out.println("Server is running on port " + port);
					
			while (true) {
				Socket clientSocket = serverSocket.accept();
				System.out.println("Client is connected");

				DataInputStream inputStream = new DataInputStream(clientSocket.getInputStream());
				
				if (typeOfInput.equals("Vehicle")) {
					String ownerID = inputStream.readUTF();
					String firstName = inputStream.readUTF();
					String lastName = inputStream.readUTF();
					String make = inputStream.readUTF();
					String model = inputStream.readUTF();
					String vin = inputStream.readUTF();
					String residencyTime = inputStream.readUTF();
					String fileName = "VCRTS-DATA";
					String filePath = FileCreationFinal.createFolder(fileName);
					FileCreationFinal.vehicleOwnerFileCreate(filePath, ownerID, firstName, lastName, make, model, vin,
							residencyTime);

				}
				else if (typeOfInput.equals("Job")) {
					// Receiving job owner information
					String clientID = inputStream.readUTF();
					String firstName = inputStream.readUTF();
					String lastName = inputStream.readUTF();
					String jobName = inputStream.readUTF();
					String jobDuration = inputStream.readUTF();
					String deadline = inputStream.readUTF();

					System.out.println("Received Vehicle Owner Information:");
					System.out.println("Client ID: " + clientID);
					System.out.println("First Name: " + firstName);
					System.out.println("Last Name: " + lastName);
					System.out.println("Job Name: " + jobName);
					System.out.println("Job Duration: " + jobDuration);
					System.out.println("Deadline: " + deadline);

					String fileName = "VCRTS-DATA";
					String filePath = FileCreationFinal.createFolder(fileName);
					FileCreationFinal.jobOwnerFileCreate(filePath, clientID, firstName, lastName, jobName, jobDuration,
							deadline);
					
				}

				clientSocket.close();
			}
		}

		catch (IOException e) {
			e.printStackTrace();
			System.out.println("Error: " + e.getMessage());
		}
	}
	
	JButton backButton, backToSelectionButton, backToLoginButton;
    JLabel titleLabel, welcomeLabel, roleLabel, messageLabel, titleHeading;
    JPanel loginPanel,selectionPanel;
    
    public static void jobOwnerInfo(int port) {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Server is running on port " + port);

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Client is connected");

                DataInputStream inputStream = new DataInputStream(clientSocket.getInputStream());

                // Receiving job owner information
                String clientID = inputStream.readUTF();
                String firstName = inputStream.readUTF();
                String lastName = inputStream.readUTF();
                String jobName = inputStream.readUTF();
                String jobDuration = inputStream.readUTF();
                String deadline = inputStream.readUTF();

                // Save the information to properties object
                jobOwnerData.setProperty(clientID, firstName + "," + lastName + "," + jobName + "," + jobDuration + "," + deadline);

                // For now, just print the data
                System.out.println("Received Job Owner Information:");
                System.out.println("Client ID: " + clientID);
                System.out.println("First Name: " + firstName);
                System.out.println("Last Name: " + lastName);
                System.out.println("Job Name: " + jobName);
                System.out.println("Job Duration: " + jobDuration);
                System.out.println("Deadline: " + deadline);

                clientSocket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error: " + e.getMessage());
        }
    }

    
    public static void vehicleInfo(int port) {
		  try (ServerSocket serverSocket = new ServerSocket(port)) {
	            System.out.println("Server is running on port " + port);

	            while (true) {
	                Socket clientSocket = serverSocket.accept();
	                System.out.println("Client is connected");

	                DataInputStream inputStream = new DataInputStream(clientSocket.getInputStream());

	                // Receiving vehicle owner information
	                String ownerID = inputStream.readUTF();
	                String firstName = inputStream.readUTF();
	                String lastName = inputStream.readUTF();
	                String make = inputStream.readUTF();
	                String model = inputStream.readUTF();
	                String vin = inputStream.readUTF();
	                String residencyTime = inputStream.readUTF();
	                
	                // Save the information to properties object
	                vehicleOwnerData.setProperty(ownerID, firstName + "," + lastName + "," + make + "," + model + "," + vin + "," + residencyTime);

	                System.out.println("Received Vehicle Owner Information:");
	                System.out.println("Owner ID: " + ownerID);
	                System.out.println("First Name: " + firstName);
	                System.out.println("Last Name: " + lastName);
	                System.out.println("Make: " + make);
	                System.out.println("Model: " + model);
	                System.out.println("VIN: " + vin);
	                System.out.println("Residency Time: " + residencyTime);
	                
	                String fileName = "VCRTS-DATA";
	                String filePath = FileCreationFinal.createFolder(fileName);
	                FileCreationFinal.vehicleOwnerFileCreate(filePath,ownerID, firstName, lastName, make, model, vin, residencyTime);

	                clientSocket.close();
	            }
	        } catch (IOException e) {
	            e.printStackTrace();
	            System.out.println("Error: " + e.getMessage());
	        }
		
		
	}
    
	public cloudController() {
		
	setTitle("Cloud Controller");
    
    // Sizing and Operations of the frame
    setSize(600, 400);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setLocationRelativeTo(null);
    setLayout(null);
    
    JPanel backgroundPanel = new JPanel();
    backgroundPanel.setLayout(null);
    backgroundPanel.setBackground(new Color(82, 138, 174));
    backgroundPanel.setBounds(0, 0, getWidth(), getHeight());
    add(backgroundPanel);
    
    JLabel titleLabel = new JLabel("Click Me For Data!", SwingConstants.CENTER);
    titleLabel.setFont(new Font("Arial", Font.BOLD, 30));
    titleLabel.setBounds(150, 20, 300, 50);
    backgroundPanel.add(titleLabel);
    
    
    
    
    JButton accessButton = new JButton("Access Jobs");
    accessButton.setBounds(215, 180, 180, 40);
    backgroundPanel.add(accessButton);
    accessButton.addActionListener(e -> {
        JobSchedulerUI.main(null); 
    });
    
    // Back Button
    JButton backButton = new JButton("Back");
    backButton.setBounds(50, 320, 100, 30);
    backButton.setBackground(Color.LIGHT_GRAY);
    backgroundPanel.add(backButton);

    backButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            dispose(); // Close the current window
            new LoginPage(); 
        }
    });
  
    setVisible(true);

	}
	// This accepts the JobOwner data if accepted 
	public void acceptJobOwnerData(String clientID) {
	    String data = jobOwnerData.getProperty(clientID);
	    if (data != null) {
	        // This is for saving the data to a file
	        String[] jobDetails = data.split(",");
	        String firstName = jobDetails[0];
	        String lastName = jobDetails[1];
	        String jobName = jobDetails[2];
	        String jobDuration = jobDetails[3];
	        String deadline = jobDetails[4];

	        // This tells where to write to the file
	        String fileName = "VCRTS-DATA";
	        String filePath = FileCreationFinal.createFolder(fileName);
	        FileCreationFinal.jobOwnerFileCreate(filePath, clientID, firstName, lastName, jobName, jobDuration, deadline);
	    }
	}

	// This deletes the JobOwner data if rejected
	public void rejectJobOwnerData(String clientID) {
	    jobOwnerData.remove(clientID); // Simply remove the data from properties
	    System.out.println("Data for Client ID " + clientID + " has been rejected and removed.");
	}
	// This accepts the VehicleOwner data if accepted 
	public void acceptVehicleOwnerData(String ownerID) {
	    String data = vehicleOwnerData.getProperty(ownerID);
	    if (data != null) {
	        // Parse and save data to a file or another structure
	        String[] vehicleDetails = data.split(",");
	        String firstName = vehicleDetails[0];
	        String lastName = vehicleDetails[1];
	        String make = vehicleDetails[2];
	        String model = vehicleDetails[3];
	        String vin = vehicleDetails[4];
	        String residencyTime = vehicleDetails[5];

	        // This tells where to write to the file
	        String fileName = "VCRTS-DATA";
	        String filePath = FileCreationFinal.createFolder(fileName);
	        FileCreationFinal.vehicleOwnerFileCreate(filePath, ownerID, firstName, lastName, make, model, vin, residencyTime);
	    }
	}

	// This deletes the VehicleOwner data if rejected
	public void rejectVehicleOwnerData(String ownerID) {
	    vehicleOwnerData.remove(ownerID); // Simply remove the data from properties
	    System.out.println("Data for Owner ID " + ownerID + " has been rejected and removed.");
	}
	// Getters for front-end access
    public Properties getJobOwnerData() {
        return jobOwnerData;
    }

    public Properties getVehicleOwnerData() {
        return vehicleOwnerData;
    }
}