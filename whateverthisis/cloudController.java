import javax.swing.*;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.*;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class cloudController extends JFrame {
	private static Properties jobOwnerData = new Properties();
	private static Properties vehicleOwnerData = new Properties();

	public static void main(String args[]) {
		int port = 1234;
		serverLaunch(port);
	}

	public static void serverLaunch(int port) {
		try (ServerSocket serverSocket = new ServerSocket(port)) {
		    System.out.println("Server is running on port " + port);

		    while (true) {
		        Socket clientSocket = serverSocket.accept();
		        System.out.println("Client is connected");

		        DataInputStream inputStream = new DataInputStream(clientSocket.getInputStream());
		        DataOutputStream outputStream = new DataOutputStream(clientSocket.getOutputStream());
		        String typeOfInput = inputStream.readUTF();
		        final String[] result = new String[1];

		        if (typeOfInput.equals("Job")) {
		            
		        	String clientID = inputStream.readUTF();
		            String firstName = inputStream.readUTF();
		            String lastName = inputStream.readUTF();
		            String jobName = inputStream.readUTF();
		            String jobDuration = inputStream.readUTF();
		            String deadline = inputStream.readUTF();

		            jobOwnerData.setProperty(clientID,
		                    firstName + "," + lastName + "," + jobName + "," + jobDuration + "," + deadline);

		            try {
		               
		                SwingUtilities.invokeAndWait(() -> {
		                    JFrame approvalFrame = new JFrame("Approve Job?");
		                    approvalFrame.setSize(400, 250);
		                    approvalFrame.setLayout(null);
		                    approvalFrame.setLocationRelativeTo(null);

		                    JTextArea info = new JTextArea(
		                            "Client ID: " + clientID + "\n" + "Name: " + firstName + " " + lastName + "\n" + "Job: "
		                                    + jobName + " (" + jobDuration + " hours)\n" + "Deadline: " + deadline);
		                    info.setBounds(20, 20, 360, 100);
		                    info.setEditable(false);
		                    approvalFrame.add(info);

		                    JButton acceptButton = new JButton("Accept");
		                    acceptButton.setBounds(70, 140, 100, 30);
		                    approvalFrame.add(acceptButton);

		                    JButton rejectButton = new JButton("Reject");
		                    rejectButton.setBounds(220, 140, 100, 30);
		                    approvalFrame.add(rejectButton);

		                    acceptButton.addActionListener(e -> {
		                        result[0] = "ACCEPTED";  
		                        acceptJobOwnerData(clientID); 
		                        approvalFrame.dispose();
		                    });

		                    rejectButton.addActionListener(e -> {
		                        result[0] = "REJECTED";  
		                        rejectJobOwnerData(clientID); 
		                        approvalFrame.dispose();
		                    });

		                    approvalFrame.setVisible(true);
		                });

		                while (result[0] == null) {
		                    Thread.sleep(100);
		                }

		            } catch (InvocationTargetException | InterruptedException e) {
		                e.printStackTrace();
		            }

		          
		            System.out.println("Approval Status: " + result[0]);

		            outputStream.writeUTF(result[0]);

		        } else if (typeOfInput.equals("Vehicle")) {
		     
		            String ownerID = inputStream.readUTF();
		            String firstName = inputStream.readUTF();
		            String lastName = inputStream.readUTF();
		            String make = inputStream.readUTF();
		            String model = inputStream.readUTF();
		            String vin = inputStream.readUTF();
		            String residencyTime = inputStream.readUTF();

		            vehicleOwnerData.setProperty(ownerID, firstName + "," + lastName + "," + make + "," + model + "," + vin + "," + residencyTime);

		            try {
		                
		                SwingUtilities.invokeAndWait(() -> {
		                    JFrame approvalFrame = new JFrame("Approve Vehicle?");
		                    approvalFrame.setSize(400, 250);
		                    approvalFrame.setLayout(null);
		                    approvalFrame.setLocationRelativeTo(null);

		                    JTextArea info = new JTextArea("Owner ID: " + ownerID + "\n" + "Name: " + firstName + " " + lastName + "\n" + "Vehicle: " + make + " " + model + "\n" + "VIN: " + vin + ", Residency: " + residencyTime + " months");
		                    info.setBounds(20, 20, 360, 100);
		                    info.setEditable(false);
		                    approvalFrame.add(info);

		                    JButton acceptButton = new JButton("Accept");
		                    acceptButton.setBounds(70, 140, 100, 30);
		                    approvalFrame.add(acceptButton);

		                    JButton rejectButton = new JButton("Reject");
		                    rejectButton.setBounds(220, 140, 100, 30);
		                    approvalFrame.add(rejectButton);

		                    
		                    acceptButton.addActionListener(e -> {
		                        result[0] = "ACCEPTED"; 
		                        cloudController.acceptVehicleOwnerData(ownerID); 
		                        approvalFrame.dispose();
		                    });

		                    rejectButton.addActionListener(e -> {
		                        result[0] = "REJECTED"; 
		                        cloudController.rejectVehicleOwnerData(ownerID); 
		                        approvalFrame.dispose();
		                    });

		                    approvalFrame.setVisible(true);
		                });

		         
		                while (result[0] == null) {
		                    Thread.sleep(100);
		                }

		            } catch (InvocationTargetException | InterruptedException e) {
		                e.printStackTrace();
		            }

		            System.out.println("Approval Status: " + result[0]);

		            outputStream.writeUTF(result[0]);

		        }

		        clientSocket.close();
		    }
		} catch (IOException e) {
		    e.printStackTrace();
		    System.out.println("Error: " + e.getMessage());
		}

	
		
		
	}

	private static cloudController cloudController() {
		// TODO Auto-generated method stub
		return null;
	}

	JButton backButton, backToSelectionButton, backToLoginButton;
	JLabel titleLabel, welcomeLabel, roleLabel, messageLabel, titleHeading;
	JPanel loginPanel, selectionPanel;


	public cloudController() {
		setTitle("Cloud Controller");
		setSize(600, 400);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setLayout(null);

		JPanel backgroundPanel = new JPanel();
		backgroundPanel.setLayout(null);
		backgroundPanel.setBackground(new Color(82, 138, 174));
		backgroundPanel.setBounds(0, 0, getWidth(), getHeight());
		add(backgroundPanel);

		JLabel titleLabel = new JLabel("Cloud Controller", SwingConstants.CENTER);
		titleLabel.setFont(new Font("Arial", Font.BOLD, 30));
		titleLabel.setBounds(150, 20, 300, 50);
		backgroundPanel.add(titleLabel);
		
		JLabel ExplanationLabel1 = new JLabel("When the client inputs", SwingConstants.CENTER);
		ExplanationLabel1.setFont(new Font("Arial", Font.BOLD, 15));
		ExplanationLabel1.setBounds(200, 40, 200, 200);
		backgroundPanel.add(ExplanationLabel1);
		
		JLabel ExplanationLabel2 = new JLabel("some data, a popup will", SwingConstants.CENTER);
		ExplanationLabel2.setFont(new Font("Arial", Font.BOLD, 15));
		ExplanationLabel2.setBounds(200, 55, 200, 200);
		backgroundPanel.add(ExplanationLabel2);
		
		JLabel ExplanationLabel3 = new JLabel("appear.You can accept or", SwingConstants.CENTER);
		ExplanationLabel3.setFont(new Font("Arial", Font.BOLD, 15));
		ExplanationLabel3.setBounds(200, 70, 200, 200);
		backgroundPanel.add(ExplanationLabel3);
		
		JLabel ExplanationLabel4 = new JLabel("reject the job or vehicle", SwingConstants.CENTER);
		ExplanationLabel4.setFont(new Font("Arial", Font.BOLD, 15));
		ExplanationLabel4.setBounds(200, 85, 200, 200);
		backgroundPanel.add(ExplanationLabel4);
		
		JLabel ExplanationLabel5 = new JLabel("from the popup.", SwingConstants.CENTER);
		ExplanationLabel5.setFont(new Font("Arial", Font.BOLD, 15));
		ExplanationLabel5.setBounds(200, 100, 200, 200);
		backgroundPanel.add(ExplanationLabel5);

	
		JButton backButton = new JButton("Back");
		backButton.setBounds(50, 320, 100, 30);
		backButton.setBackground(Color.LIGHT_GRAY);
		backgroundPanel.add(backButton);

		backButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				new Launcher();
			}
		});

		setVisible(true);
	}

	public static void acceptJobOwnerData(String clientID) {
		System.out.println("acceptJobOwner Running");
		String data = jobOwnerData.getProperty(clientID);
		if (data != null) {
			String[] jobDetails = data.split(",");
			String firstName = jobDetails[0];
			String lastName = jobDetails[1];
			String jobName = jobDetails[2];
			String jobDuration = jobDetails[3];
			String deadline = jobDetails[4];

			String fileName = "VCRTS-DATA";
			String filePath = FileCreationFinal.createFolder(fileName);
			FileCreationFinal.jobOwnerFileCreate(filePath, clientID, firstName, lastName, jobName, jobDuration,
					deadline);
		}
	}

	public static void rejectJobOwnerData(String clientID) {
		jobOwnerData.remove(clientID);
		System.out.println("Data for Client ID " + clientID + " has been rejected and removed.");
	}

	public static void acceptVehicleOwnerData(String ownerID) {
		System.out.println("acceptVehicle Running");
		String data = vehicleOwnerData.getProperty(ownerID);
		if (data != null) {
			String[] vehicleDetails = data.split(",");
			String firstName = vehicleDetails[0];
			String lastName = vehicleDetails[1];
			String make = vehicleDetails[2];
			String model = vehicleDetails[3];
			String vin = vehicleDetails[4];
			String residencyTime = vehicleDetails[5];

			String fileName = "VCRTS-DATA";
			String filePath = FileCreationFinal.createFolder(fileName);
			FileCreationFinal.vehicleOwnerFileCreate(filePath, ownerID, firstName, lastName, make, model, vin,
					residencyTime);
		}

	}

	public static void rejectVehicleOwnerData(String ownerID) {
		vehicleOwnerData.remove(ownerID);
		System.out.println("Data for Owner ID " + ownerID + " has been rejected and removed.");
	}

	public Properties getJobOwnerData() {
		return jobOwnerData;
	}

	public Properties getVehicleOwnerData() {
		return vehicleOwnerData;
	}
	public void runCloudTask() {
        System.out.println("Cloud Controller Task is running...");
    }
}
