import javax.swing.*;
import java.awt.Color;
import java.awt.event.*;
import java.io.*;
import java.net.Socket;

public class jobOwner extends JFrame {
    private String jobOwnerID;
    private String firstName;
    private String lastName;
    private JTextField ownerIDField, firstNameField, lastNameField, jobNameField, jobDurationField, deadlineField;

    public jobOwner(String jobOwnerID, String firstName, String lastName) {
        this.jobOwnerID = jobOwnerID;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public jobOwner() {
        setTitle("Job Information");

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

        // Client ID Label and Text Field
        JLabel ownerIDLabel = new JLabel("Owner ID:");
        ownerIDLabel.setBounds(50, 30, 150, 25);
        backgroundPanel.add(ownerIDLabel);

        ownerIDField = new JTextField();
        ownerIDField.setBounds(200, 30, 300, 25);
        backgroundPanel.add(ownerIDField);

        // Job Name Label and Text Field
        JLabel jobNameLabel = new JLabel("Job Name:");
        jobNameLabel.setBounds(50, 70, 150, 25);
        backgroundPanel.add(jobNameLabel);

        jobNameField = new JTextField();
        jobNameField.setBounds(200, 70, 300, 25);
        backgroundPanel.add(jobNameField);

        // First Name Label and Text Field
        JLabel firstNameLabel = new JLabel("First Name:");
        firstNameLabel.setBounds(50, 110, 150, 25);
        backgroundPanel.add(firstNameLabel);

        firstNameField = new JTextField();
        firstNameField.setBounds(200, 110, 300, 25);
        backgroundPanel.add(firstNameField);

        // Last Name Label and Text Field
        JLabel lastNameLabel = new JLabel("Last Name:");
        lastNameLabel.setBounds(50, 150, 150, 25);
        backgroundPanel.add(lastNameLabel);

        lastNameField = new JTextField();
        lastNameField.setBounds(200, 150, 300, 25);
        backgroundPanel.add(lastNameField);

        // Job Duration Label and Text Field
        JLabel jobDurationLabel = new JLabel("Job Duration:");
        jobDurationLabel.setBounds(50, 190, 150, 25);
        backgroundPanel.add(jobDurationLabel);

        jobDurationField = new JTextField();
        jobDurationField.setBounds(200, 190, 300, 25);
        backgroundPanel.add(jobDurationField);

        // Deadline Label and Text Field
        JLabel deadlineLabel = new JLabel("Deadline:");
        deadlineLabel.setBounds(50, 230, 150, 25);
        backgroundPanel.add(deadlineLabel);

        deadlineField = new JTextField();
        deadlineField.setBounds(200, 230, 300, 25);
        backgroundPanel.add(deadlineField);

        // Submit Button
        JButton submitButton = new JButton("Submit");
        submitButton.setBounds(250, 320, 100, 30);
        backgroundPanel.add(submitButton);

        submitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                saveClientInfo();
            }
        });

        setVisible(true);
    }

    private void saveClientInfo() {
        String ownerID = ownerIDField.getText().trim();
        String firstName = firstNameField.getText().trim();
        String lastName = lastNameField.getText().trim();
        String jobName = jobNameField.getText().trim();
        String jobDuration = jobDurationField.getText().trim();
        String deadline = deadlineField.getText().trim();

        if (ownerID.isEmpty() || firstName.isEmpty() || lastName.isEmpty() || jobName.isEmpty() || jobDuration.isEmpty() || deadline.isEmpty()) {
            JOptionPane.showMessageDialog(this, "All fields must be filled correctly!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        JOptionPane.showMessageDialog(this, "Submitting your information...\nPlease wait for the Cloud Controller to respond.", "Submitting", JOptionPane.INFORMATION_MESSAGE);

        try (
                Socket socket = new Socket("localhost", 1234);
                DataOutputStream out = new DataOutputStream(socket.getOutputStream())
        ) {
            // Send job details in the same format as TestClient
            out.writeUTF(ownerID);     // clientID
            out.writeUTF(firstName);     // firstName
            out.writeUTF(lastName);      // lastName
            out.writeUTF(jobName);      // jobName
            out.writeUTF(jobDuration);  // jobDuration
            out.writeUTF(deadline);     // deadline

            out.flush();
            System.out.println("Job data sent to server.");

        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Error connecting to server: " + ex.getMessage(), "Connection Error", JOptionPane.ERROR_MESSAGE);
        }

        // Clear the input fields after submission
        ownerIDField.setText("");
        firstNameField.setText("");
        lastNameField.setText("");
        jobNameField.setText("");
        jobDurationField.setText("");
        deadlineField.setText("");

        dispose();  // Close the current window
        new Client(); // Open a new client window
    }
}
