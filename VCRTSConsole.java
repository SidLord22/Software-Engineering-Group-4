import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.util.*;
import java.time.*;
import java.time.format.DateTimeFormatter;

import javax.swing.border.EmptyBorder;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

//main class
public class VCRTSConsole {
    public static void main(String[] args) {
        new login();
    }
}
//Frontend Done by : Andrea Dass and Jinqing Mei
//The login frame
class login extends JFrame {
    JTextField emailField;
    JPasswordField passwordField;
    JButton backButton, backToSelectionButton,backToLoginButton;
    JLabel titleLabel, welcomeLabel, roleLabel, messageLabel, titleHeading;
    JPanel loginPanel, registrationPanel, selectionPanel;
    
    
    
   login() {
	// Title of the frame/window
        setTitle("Login"); 
        
        // Sizing and Operations of the frame
        setSize(600,400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); //Centers the frame
        setLayout(null);
        
        
        // Creating background panel with color
        JPanel backgroundPanel = new JPanel();
        backgroundPanel.setLayout(null);
        backgroundPanel.setBackground(new Color(82,138,174));
        backgroundPanel.setBounds(0,0,getWidth(),getHeight());
        add(backgroundPanel);
        
        // Welcome Title
        JLabel titleLabel = new JLabel("Welcome", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial",Font.BOLD,30));
        titleLabel.setBounds(150,20,300,50);
        backgroundPanel.add(titleLabel);
        
        // Dropdown Menu Role Selection & Designing
        JLabel roleLabel = new JLabel("Select Role:");
        roleLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        roleLabel.setBounds(225, 80, 150, 30);
        backgroundPanel.add(roleLabel);
        
        JComboBox<String> roleDropdown = new JComboBox<>(new String[]{"Vehicle Owner", "Job Owner"});
        roleDropdown.setBounds(225, 110, 150, 40);
        roleDropdown.setFont(new Font("Arial", Font.PLAIN, 16));
        roleDropdown.setBackground(Color.WHITE);
        roleDropdown.setForeground(Color.BLACK);
        roleDropdown.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        backgroundPanel.add(roleDropdown);
        
        // Login Button
        JButton loginButton = new JButton("Login");
        loginButton.setBounds(250, 180, 100, 40);
        backgroundPanel.add(loginButton);

        //When user presses button, change frame to VehicleInfo frame
        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                String selectedItem = (String) roleDropdown.getSelectedItem(); 
               if (selectedItem.equals("Vehicle Owner")) {
                	new VehicleInfo();
                }
                if (selectedItem.equals("Job Owner")) {
                	new JobOwnerInfo();
                };
               
              
            }
        });
        setVisible(true);
    }
}
// End of login frame



//Vehicle Info frame, where user enters the info of Vehicle
class VehicleInfo extends JFrame {
    JTextField ownerIDField, makeField, modelField, vinField, residencyTimeField;

    VehicleInfo() {
	// Title of the frame/window
        setTitle("Vehicle Information");
        
        // Sizing and Operations of the frame
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        // Owner ID label and Text Field
        JLabel ownerIDLabel = new JLabel("Owner ID:");
        ownerIDLabel.setBounds(150, 50, 100, 30);
        add(ownerIDLabel);

        ownerIDField = new JTextField();
        ownerIDField.setBounds(250, 50, 200, 30);
        add(ownerIDField);

        // Make Label and Text Field
        JLabel makeLabel = new JLabel("Make:");
        makeLabel.setBounds(150, 90, 100, 30);
        add(makeLabel);

        makeField = new JTextField();
        makeField.setBounds(250, 90, 200, 30);
        add(makeField);

        // Model Label and Text Field
        JLabel modelLabel = new JLabel("Model:");
        modelLabel.setBounds(150, 130, 100, 30);
        add(modelLabel);

        modelField = new JTextField();
        modelField.setBounds(250, 130, 200, 30);
        add(modelField);

        // VIN Label and Text Field
        JLabel vinLabel = new JLabel("VIN:");
        vinLabel.setBounds(150, 170, 100, 30);
        add(vinLabel);

        vinField = new JTextField();
        vinField.setBounds(250, 170, 200, 30);
        add(vinField);

        // Residency Time Label and Text Field
        JLabel residencyTimeLabel = new JLabel("Residency Time:");
        residencyTimeLabel.setBounds(150, 210, 120, 30);
        add(residencyTimeLabel);

        residencyTimeField = new JTextField();
        residencyTimeField.setBounds(250, 210, 200, 30);
        add(residencyTimeField);

        // Submit Button
        JButton submitButton = new JButton("Submit");
        submitButton.setBounds(250, 260, 100, 40);
        add(submitButton);

        submitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                saveVehicleInfo();
            }
        });
        setVisible(true);
    }
     

	
    /*
    Jinqiing Mei + Matthew Ahamad
    Takes the info that the user submit and make a txt file of that information
    For VehicleOwner users
    Contain all info that user submit regarding the vehicle and shows timestamp of when
    the info was inputted
    
     */
    private void saveVehicleInfo() {
        String ownerID = ownerIDField.getText().trim();
        String make = makeField.getText().trim();
        String model = modelField.getText().trim();
        String vin = vinField.getText().trim();
        String residencyTime = residencyTimeField.getText().trim();
        String fileName = "VCRTS-DATA";
        

        if (ownerID.isEmpty() || make.isEmpty() || model.isEmpty() || vin.isEmpty() || residencyTime.isEmpty()) {
            JOptionPane.showMessageDialog(this, "All fields must be filled correctly!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        String filePath = FileCreationFinal.createFolder(fileName);
        FileCreationFinal.vehicleOwnerFileCreate(filePath,ownerID, make, model, vin, residencyTime);
        JOptionPane.showMessageDialog(this, "Vehicle information saved successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
        ownerIDField.setText("");
        makeField.setText("");
        modelField.setText("");
        vinField.setText("");
        residencyTimeField.setText("");
        //new JobList(); // Open JobList frame
        dispose(); // Close VehicleInfo frame
        new login();
    }
}


//Job Owner input Frame
class JobOwnerInfo extends JFrame {
    JTextField clientIDField,jobNameField, jobDurationField, deadlineField;

    JobOwnerInfo() {
    	// Title of the frame/window
        setTitle("Job Information");
        
        // Sizing and Operations of the frame
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        // Client ID Label and Text Field
        JLabel clientIDLabel = new JLabel("Client ID:");
        clientIDLabel.setBounds(150, 50, 100, 30);
        add(clientIDLabel);

        clientIDField = new JTextField();
        clientIDField.setBounds(250, 50, 200, 30);
        add(clientIDField);
        
        // Job Name Label and Text Field
        JLabel jobNameLabel = new JLabel("Job Name:");
        jobNameLabel.setBounds(150, 90, 100, 30);
        add(jobNameLabel);

        jobNameField = new JTextField();
        jobNameField.setBounds(250, 90, 200, 30);
        add(jobNameField);
        
        //Job Duration Label and Text Field
        JLabel jobDurationLabel = new JLabel("Job Duration:");
        jobDurationLabel.setBounds(150, 130, 100, 30);
        add(jobDurationLabel);

        jobDurationField = new JTextField();
        jobDurationField.setBounds(250, 130, 200, 30);
        add(jobDurationField);
        
        // Deadline Label and Text Field
        JLabel deadlineLabel = new JLabel("Deadline:");
        deadlineLabel.setBounds(150, 170, 100, 30);
        add(deadlineLabel);

        deadlineField = new JTextField();
        deadlineField.setBounds(250, 170, 200, 30);
        add(deadlineField);

        // Submit Button
        JButton submitButton = new JButton("Submit");
        submitButton.setBounds(250, 260, 100, 40);
        add(submitButton);

        submitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                saveClientInfo();
            }
        });
        setVisible(true);
    }
    

    /*
    Jinqing Mei + Matthew Ahamad
    Takes the info that the user submit and make a txt file of that information
    For jobOwner users
    Contain all info that user submit regarding the vehicle and shows timestamp of when
    the info was inputted
    
     */
    private void saveClientInfo() {
        String clientID = clientIDField.getText().trim();
        String jobName = jobNameField.getText().trim();
        String jobDuration = jobDurationField.getText().trim();
        String deadline = deadlineField.getText().trim();
        
        String fileName = "VCRTS-DATA";
        

        if (clientID.isEmpty() || jobDuration.isEmpty() || deadline.isEmpty() ) {
            JOptionPane.showMessageDialog(this, "All fields must be filled correctly!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        String filePath = FileCreationFinal.createFolder(fileName);
        FileCreationFinal.jobOwnerFileCreate(filePath,jobName,clientID,jobDuration,deadline);
        JOptionPane.showMessageDialog(this, "Job information saved successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
        clientIDField.setText("");
        jobDurationField.setText("");
        deadlineField.setText("");
       
        dispose(); // Close VehicleInfo frame
        new login();// Sends user back to login screen
    }
}

class FileCreationFinal {
    
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
