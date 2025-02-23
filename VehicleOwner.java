import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.util.*;
import java.time.*;
import java.time.format.DateTimeFormatter;

//main class
public class VehicleOwner {
    public static void main(String[] args) {
        new VehicleLogin();
    }
}

//The login frame
class VehicleLogin extends JFrame {
    JTextField emailField;
    JPasswordField passwordField;

    VehicleLogin() {
        setTitle("Login");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        JLabel loginTitle = new JLabel("Vehicle Owner Login");
        loginTitle.setBounds(200, 20, 200, 30);
        add(loginTitle);

        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setBounds(150, 80, 100, 30);
        add(emailLabel);

        emailField = new JTextField();
        emailField.setBounds(250, 80, 200, 30);
        add(emailField);

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(150, 130, 100, 30);
        add(passwordLabel);

        passwordField = new JPasswordField();
        passwordField.setBounds(250, 130, 200, 30);
        add(passwordField);

        JButton loginButton = new JButton("Login");
        loginButton.setBounds(250, 180, 100, 40);
        add(loginButton);

        JButton createAccountButton = new JButton("Create an Account");
        createAccountButton.setBounds(230, 240, 150, 40);
        add(createAccountButton);

        //When user presses button, change frame to VehicleInfo frame
        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                new VehicleInfo();
            }
        });
        setVisible(true);
    }
}

//Vehicle Info frame, where user enters the info of Vehicle
class VehicleInfo extends JFrame {
    JTextField ownerIDField, makeField, modelField, vinField, residencyTimeField;

    VehicleInfo() {
        setTitle("Vehicle Information");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        JLabel ownerIDLabel = new JLabel("Owner ID:");
        ownerIDLabel.setBounds(150, 50, 100, 30);
        add(ownerIDLabel);

        ownerIDField = new JTextField();
        ownerIDField.setBounds(250, 50, 200, 30);
        add(ownerIDField);

        JLabel makeLabel = new JLabel("Make:");
        makeLabel.setBounds(150, 90, 100, 30);
        add(makeLabel);

        makeField = new JTextField();
        makeField.setBounds(250, 90, 200, 30);
        add(makeField);

        JLabel modelLabel = new JLabel("Model:");
        modelLabel.setBounds(150, 130, 100, 30);
        add(modelLabel);

        modelField = new JTextField();
        modelField.setBounds(250, 130, 200, 30);
        add(modelField);

        JLabel vinLabel = new JLabel("VIN:");
        vinLabel.setBounds(150, 170, 100, 30);
        add(vinLabel);

        vinField = new JTextField();
        vinField.setBounds(250, 170, 200, 30);
        add(vinField);

        JLabel residencyTimeLabel = new JLabel("Residency Time:");
        residencyTimeLabel.setBounds(150, 210, 120, 30);
        add(residencyTimeLabel);

        residencyTimeField = new JTextField();
        residencyTimeField.setBounds(250, 210, 200, 30);
        add(residencyTimeField);

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
    Takes the info that the user submit and make a txt file of that information
    Contain all info that user submit regarding the vehicle and shows timestamp of when
    the info was inputted
    Txt file uses fileCreationFinal
     */
    private void saveVehicleInfo() {
        String ownerID = ownerIDField.getText().trim();
        String make = makeField.getText().trim();
        String model = modelField.getText().trim();
        String vin = vinField.getText().trim();
        String residencyTime = residencyTimeField.getText().trim();

        if (ownerID.isEmpty() || make.isEmpty() || model.isEmpty() || vin.isEmpty() || residencyTime.isEmpty()) {
            JOptionPane.showMessageDialog(this, "All fields must be filled correctly!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        fileCreationFinal.vehicleOwnerFileCreate(ownerID, make, model, vin, residencyTime);
        JOptionPane.showMessageDialog(this, "Vehicle information saved successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
        ownerIDField.setText("");
        makeField.setText("");
        modelField.setText("");
        vinField.setText("");
        residencyTimeField.setText("");
        new JobList(); // Open JobList frame
        dispose(); // Close VehicleInfo frame
    }
}

//After user submits the vehicle info, bring them to next frame, which shows list of jobs
class JobList extends JFrame {
    JobList() {
        setTitle("Available Jobs");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        //Currently empty, because we dont have job database yet
        JLabel message = new JLabel("No jobs available yet.");
        add(message);

        setVisible(true);
    }
}

/*
fileCreationVehicle is pretty much the same as fileCreationFinal, except it's in
this class instead of an outside class.
Currently not in use, but if you want to use this class instead of fileCreationFinal,
change line 145 to fileCreationVehicle instead of fileCreationFinal
 */
class fileCreationVehicle {
    public static String stringDateTime() {
        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyyHH-mm-ss");
        return currentDateTime.format(formatter);
    }

    public static void vehicleOwnerFileCreate(String ownerID, String make, String model, String vin, String durationOfStay) {
        String timeStamp = stringDateTime();
        String vehicleOwnerFileName = "VehicleOwner" + timeStamp + ".txt";

        Properties properties = new Properties();
        properties.setProperty("Owner ID", ownerID);
        properties.setProperty("Manufacturer", make);
        properties.setProperty("Model", model);
        properties.setProperty("VIN", vin);
        properties.setProperty("Job Duration", durationOfStay);
        properties.setProperty("Timestamp", timeStamp);

        try (FileWriter writer = new FileWriter(vehicleOwnerFileName)) {
            properties.store(writer, "Vehicle Owner Information entered at " + timeStamp);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
