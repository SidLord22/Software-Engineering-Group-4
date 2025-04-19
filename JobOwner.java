import javax.swing.*;

import java.awt.Color;
import java.awt.event.*;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;

public class jobOwner extends JFrame{
    private String jobOwnerID;
    private String firstName;
    private String lastName;
    private ArrayList<String> ownJobList;
    JTextField clientIDField, firstNameField, lastNameField, jobNameField, jobDurationField, deadlineField;
    private JButton submitButton;
	
	
	public jobOwner(String jobOwnerID, String firstName, String lastName) {
        this.jobOwnerID = jobOwnerID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.ownJobList = new ArrayList<>();
    }

    public String getJobOwnerID() {
        return jobOwnerID;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public ArrayList<String> getOwnJobList() {
        return ownJobList;
    }

    public void addJob(String jobID) {
        ownJobList.add(jobID);
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
        JLabel clientIDLabel = new JLabel("Client ID:");
        clientIDLabel.setBounds(50, 30, 150, 25);
        backgroundPanel.add(clientIDLabel);

        clientIDField = new JTextField();
        clientIDField.setBounds(200, 30, 300, 25);
        backgroundPanel.add(clientIDField);
        
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
        
        //Job Duration Label and Text Field
        JLabel jobDurationLabel = new JLabel("Job Duration:");
        jobDurationLabel.setBounds(50, 190, 150, 25);
        backgroundPanel.add(jobDurationLabel);
        JLabel jobDuration2Label = new JLabel("(hh-mm-ss)");
        jobDuration2Label.setBounds(50, 200, 100, 30);
        backgroundPanel.add(jobDuration2Label);

        jobDurationField = new JTextField();
        jobDurationField.setBounds(200, 190, 300, 25);
        backgroundPanel.add(jobDurationField);
        
        // Deadline Label and Text Field
        JLabel deadlineLabel = new JLabel("Deadline:");
        deadlineLabel.setBounds(50, 230, 150, 25);
        backgroundPanel.add(deadlineLabel);
        JLabel deadline2Label = new JLabel("(mm-dd-yyyy)");
        deadline2Label.setBounds(50, 240, 150, 25);
        backgroundPanel.add(deadline2Label);

        deadlineField = new JTextField();
        deadlineField.setBounds(200, 230, 300, 25);
        backgroundPanel.add(deadlineField);

        // Submit Button
        submitButton = new JButton("Submit");
        submitButton.setBounds(250, 320, 100, 30);
        backgroundPanel.add(submitButton);

        submitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                saveClientInfo();
            }
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
	
	

	private void saveClientInfo() {
        String clientID = clientIDField.getText().trim();
        String firstName = firstNameField.getText().trim();
        String lastName = lastNameField.getText().trim();
        String jobName = jobNameField.getText().trim();
        String jobDuration = jobDurationField.getText().trim();
        String deadline = deadlineField.getText().trim();
        

        if (clientID.isEmpty() || firstName.isEmpty() || lastName.isEmpty() || jobName.isEmpty() || jobDuration.isEmpty() || deadline.isEmpty() ) {
            JOptionPane.showMessageDialog(this, "All fields must be filled correctly!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
       
        JOptionPane waitingPopup = new JOptionPane("Waiting for approval from Cloud Controller...",JOptionPane.INFORMATION_MESSAGE,JOptionPane.DEFAULT_OPTION,null, new Object[]{}, null);  
        JDialog dialog = waitingPopup.createDialog("Waiting...");
        dialog.setModal(false); 
        dialog.setVisible(true);
        new Thread(() -> {
        	try (Socket socket = new Socket("localhost", 1234);
        			DataOutputStream out = new DataOutputStream(socket.getOutputStream());
        			DataInputStream in = new DataInputStream(socket.getInputStream())) 
        	{
        		out.writeUTF(clientIDField.getText());
        		out.writeUTF(firstNameField.getText());
        		out.writeUTF(lastNameField.getText());
        		out.writeUTF(jobNameField.getText());
        		out.writeUTF(jobDurationField.getText());
        		out.writeUTF(deadlineField.getText());
        		out.flush();
        		
        		boolean isApproved = in.readBoolean();
        		
        		dialog.dispose();
        		
        		SwingUtilities.invokeLater(() -> {
        			if (isApproved) {
        				JOptionPane.showMessageDialog(null, "Job Approved!", "Approval Result",JOptionPane.INFORMATION_MESSAGE);
        				} else {
        					JOptionPane.showMessageDialog(null, "Job Rejected.", "Approval Result",JOptionPane.ERROR_MESSAGE);
                        }
                    });

                }  catch (EOFException ex) {
                    SwingUtilities.invokeLater(() -> JOptionPane.showMessageDialog(null, "Server unexpectedly closed the connection.", "Connection Error", JOptionPane.ERROR_MESSAGE));
                } catch (IOException ex) {
                    SwingUtilities.invokeLater(() -> JOptionPane.showMessageDialog(null, "Failed to submit job.\nError: " + ex.getMessage(), "Connection Error", JOptionPane.ERROR_MESSAGE));
                    ex.printStackTrace();
                }
            }).start();
        
	
        
        clientIDField.setText("");
        firstNameField.setText("");
        lastNameField.setText("");
        jobNameField.setText("");
        jobDurationField.setText("");
        deadlineField.setText("");
        
        
        dispose(); 
        new Client(); 
        
    
    }
	
	
}
