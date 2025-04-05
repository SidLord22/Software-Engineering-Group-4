import javax.swing.*;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.*;
import java.util.ArrayList;

public class vehicleOwner extends JFrame{
	    private String vehicleOwnerID;
	    private String firstName;
	    private String lastName;
	    private ArrayList<String> ownVehicleList;
		JTextField ownerIDField, firstNameField, lastNameField, makeField, modelField, vinField, residencyTimeField;

	    public vehicleOwner(String vehicleOwnerID, String firstName, String lastName) {
	        this.vehicleOwnerID = vehicleOwnerID;
	        this.firstName = firstName;
	        this.lastName = lastName;
	        this.ownVehicleList = new ArrayList<>();
	    }

	    public String getVehicleOwnerID() {
	        return vehicleOwnerID;
	    }

	    public String getFirstName() {
	        return firstName;
	    }

	    public String getLastName() {
	        return lastName;
	    }

	    public ArrayList<String> getOwnVehicleList() {
	        return ownVehicleList;
	    }

	    public void addVehicle(String vehicleID) {
	        ownVehicleList.add(vehicleID);
	    }
	
	
	
	public vehicleOwner (){
		setTitle("Vehicle Information");
        
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
   

        // Owner ID label and Text Field
        JLabel ownerIDLabel = new JLabel("Vehicle Owner ID:");
        ownerIDLabel.setBounds(50, 30, 150, 25);
        backgroundPanel.add(ownerIDLabel);

        ownerIDField = new JTextField();
        ownerIDField.setBounds(200, 30, 300, 25);
        backgroundPanel.add(ownerIDField);

        JLabel firstNameLabel = new JLabel("First Name:");
        firstNameLabel.setBounds(50, 70, 150, 25);
        backgroundPanel.add(firstNameLabel);

        firstNameField = new JTextField();
        firstNameField.setBounds(200, 70, 300, 25);
        backgroundPanel.add(firstNameField);

        JLabel lastNameLabel = new JLabel("Last Name:");
        lastNameLabel.setBounds(50, 110, 150, 25);
        backgroundPanel.add(lastNameLabel);

        lastNameField = new JTextField();
        lastNameField.setBounds(200, 110, 300, 25);
        backgroundPanel.add(lastNameField);

        JLabel makeLabel = new JLabel("Make:");
        makeLabel.setBounds(50, 150, 150, 25);
        backgroundPanel.add(makeLabel);

        makeField = new JTextField();
        makeField.setBounds(200, 150, 300, 25);
        backgroundPanel.add(makeField);

        JLabel modelLabel = new JLabel("Model:");
        modelLabel.setBounds(50, 190, 150, 25);
        backgroundPanel.add(modelLabel);

        modelField = new JTextField();
        modelField.setBounds(200, 190, 300, 25);
        backgroundPanel.add(modelField);

        JLabel vinLabel = new JLabel("VIN:");
        vinLabel.setBounds(50, 230, 150, 25);
        backgroundPanel.add(vinLabel);

        vinField = new JTextField();
        vinField.setBounds(200, 230, 300, 25);
        backgroundPanel.add(vinField);

        JLabel residencyTimeLabel = new JLabel("Residency Time:");
        residencyTimeLabel.setBounds(50, 270, 150, 25);
        JLabel residencyTime2Label = new JLabel("(mm-dd-yyyy)");
        residencyTime2Label.setBounds(50, 285, 150, 25);
        backgroundPanel.add(residencyTimeLabel);
        backgroundPanel.add(residencyTime2Label);

        residencyTimeField = new JTextField();
        residencyTimeField.setBounds(200, 270, 300, 25);
        backgroundPanel.add(residencyTimeField);

        // Submit Button
        JButton submitButton = new JButton("Submit");
        submitButton.setBounds(250, 320, 100, 30);
        backgroundPanel.add(submitButton);

        submitButton.addActionListener(e -> saveVehicleInfo());
        
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
	private void saveVehicleInfo() {
        String ownerID = ownerIDField.getText().trim();
        String firstName = firstNameField.getText().trim();
        String lastName = lastNameField.getText().trim();
        String make = makeField.getText().trim();
        String model = modelField.getText().trim();
        String vin = vinField.getText().trim();
        String residencyTime = residencyTimeField.getText().trim();
        

        if (ownerID.isEmpty() || firstName.isEmpty() || lastName.isEmpty() ||  make.isEmpty() || model.isEmpty() || vin.isEmpty() || residencyTime.isEmpty()) {
            JOptionPane.showMessageDialog(this, "All fields must be filled correctly!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        JOptionPane.showMessageDialog(this, "Submitting your information...\nPlease wait for the Cloud Controller to respond.", "Submitting", JOptionPane.INFORMATION_MESSAGE);
        
        boolean isApproved = true; 
        if (isApproved) {
            JOptionPane.showMessageDialog(this, "Your job has been APPROVED by the Cloud Controller!", "Approved", JOptionPane.INFORMATION_MESSAGE);
            String fileName = "VCRTS-DATA";
            String filePath = FileCreationFinal.createFolder(fileName);
            FileCreationFinal.vehicleOwnerFileCreate(filePath, ownerID,firstName,lastName,make,model,vin,residencyTime);
        } else {
            JOptionPane.showMessageDialog(this, "Your job has been REJECTED by the Cloud Controller.", "Rejected", JOptionPane.ERROR_MESSAGE);
            
        }
        
        
        ownerIDField.setText("");
        firstNameField.setText("");
        lastNameField.setText("");
        makeField.setText("");
        modelField.setText("");
        vinField.setText("");
        residencyTimeField.setText("");
        
        
        dispose(); 
        new Client();
    }
	
	
}
