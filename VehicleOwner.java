import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;

public class VehicleOwner {
    public static void main(String[] args) {
        new VehicleLogin();
    }
}

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

        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose(); // Close current frame
                new VehicleInfo(); // Open next frame
            }
        });

        setVisible(true);
    }
}

class VehicleInfo extends JFrame {
    JTextField ownerIDField, vehicleInfoField, residencyTimeField;

    VehicleInfo() {
        setTitle("Vehicle Information");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        JLabel ownerIDLabel = new JLabel("Owner ID:");
        ownerIDLabel.setBounds(150, 80, 100, 30);
        add(ownerIDLabel);

        ownerIDField = new JTextField();
        ownerIDField.setBounds(250, 80, 200, 30);
        add(ownerIDField);

        JLabel vehicleInfoLabel = new JLabel("Vehicle Info:");
        vehicleInfoLabel.setBounds(150, 130, 100, 30);
        add(vehicleInfoLabel);

        vehicleInfoField = new JTextField();
        vehicleInfoField.setBounds(250, 130, 200, 30);
        add(vehicleInfoField);

        JLabel residencyTimeLabel = new JLabel("Residency Time:");
        residencyTimeLabel.setBounds(150, 180, 120, 30);
        add(residencyTimeLabel);

        residencyTimeField = new JTextField();
        residencyTimeField.setBounds(250, 180, 200, 30);
        add(residencyTimeField);

        JButton submitButton = new JButton("Submit");
        submitButton.setBounds(250, 240, 100, 40);
        add(submitButton);

        submitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                saveVehicleInfo();
            }
        });

        setVisible(true);
    }

    private void saveVehicleInfo() {
        String ownerID = ownerIDField.getText().trim();
        String vehicleInfo = vehicleInfoField.getText().trim();
        String residencyTime = residencyTimeField.getText().trim();

        if (ownerID.isEmpty() || vehicleInfo.isEmpty() || residencyTime.isEmpty()) {
            JOptionPane.showMessageDialog(this, "All fields must be filled!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try (FileWriter fw = new FileWriter("vehicle_database.txt", true);
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter out = new PrintWriter(bw)) {
            out.println(ownerID + "," + vehicleInfo + "," + residencyTime);
            JOptionPane.showMessageDialog(this, "Vehicle information saved successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            ownerIDField.setText("");
            vehicleInfoField.setText("");
            residencyTimeField.setText("");
            new JobList(); // Open JobList frame
            dispose(); // Close VehicleInfo frame
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Error saving vehicle information!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}

class JobList extends JFrame {
    JobList() {
        setTitle("Available Jobs");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        JLabel message = new JLabel("No jobs available yet.");
        add(message);

        setVisible(true);
    }
}
