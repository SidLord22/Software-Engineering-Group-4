import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.util.*;
import java.time.*;
import java.time.format.DateTimeFormatter;

//main class
public class JobOwner2 {
    public static void main(String[] args) {
        new JobLogin();
    }
}

//The login frame
class JobLogin extends JFrame {
    JTextField emailField;
    JPasswordField passwordField;

    JobLogin() {
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
                new JobInfo();
            }
        });
        setVisible(true);
    }
}

//Vehicle Info frame, where user enters the info of Vehicle
class JobInfo extends JFrame {
    JTextField clientIDField, jobNameField, jobDurationField, jobDeadlineField;

    JobInfo() {
        setTitle("Job Information");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        JLabel clientIDLabel = new JLabel("Client ID:");
        clientIDLabel.setBounds(150, 50, 100, 30);
        add(clientIDLabel);

        clientIDField = new JTextField();
        clientIDField.setBounds(250, 50, 200, 30);
        add(clientIDField);

        JLabel jobNameLabel = new JLabel("Job Name:");
        jobNameLabel.setBounds(150, 90, 100, 30);
        add(jobNameLabel);

        jobNameField = new JTextField();
        jobNameField.setBounds(250, 90, 200, 30);
        add(jobNameField);

        JLabel jobDurationLabel = new JLabel("Job Duration:");
        jobDurationLabel.setBounds(150, 130, 100, 30);
        add(jobDurationLabel);

        jobDurationField = new JTextField();
        jobDurationField.setBounds(250, 130, 200, 30);
        add(jobDurationField);

        JLabel jobDeadlineLabel = new JLabel("Job Deadline:");
        jobDeadlineLabel.setBounds(150, 170, 100, 30);
        add(jobDeadlineLabel);

        jobDeadlineField = new JTextField();
        jobDeadlineField.setBounds(250, 170, 200, 30);
        add(jobDeadlineField);

        JButton submitButton = new JButton("Submit");
        submitButton.setBounds(250, 260, 100, 40);
        add(submitButton);

        submitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                saveJobInfo();
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
    private void saveJobInfo() {
        String clientID = clientIDField.getText().trim();
        String jobName = jobNameField.getText().trim();
        String jobDuration = jobDurationField.getText().trim();
        String jobDeadline = jobDeadlineField.getText().trim();

        if (clientID.isEmpty() || jobName.isEmpty() || jobDuration.isEmpty() || jobDeadline.isEmpty()) {
            JOptionPane.showMessageDialog(this, "All fields must be filled correctly!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        fileCreationFinal.jobOwnerFileCreate(clientID, jobName, jobDuration, jobDeadline);
        JOptionPane.showMessageDialog(this, "Vehicle information saved successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
        clientIDField.setText("");
        jobNameField.setText("");
        jobDurationField.setText("");
        jobDeadlineField.setText("");
        dispose(); // Close VehicleInfo frame
    }
}
