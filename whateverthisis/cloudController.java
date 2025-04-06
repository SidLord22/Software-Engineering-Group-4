import javax.swing.*;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.*;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Properties;

public class cloudController extends JFrame {
    private static Properties jobOwnerData = new Properties();
    private static Properties vehicleOwnerData = new Properties();

    public static void main(String args[]) {
        int port = 1234;
        serverLaunch(port);
    }

    public static void serverLaunch(int port) {
        String typeOfInput = "Job"; // You may want to make this dynamic later.

        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Server is running on port " + port);

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Client is connected");

                DataInputStream inputStream = new DataInputStream(clientSocket.getInputStream());
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);

                if (typeOfInput.equals("Job")) {
                    // Handle Job Owner data
                    String clientID = inputStream.readUTF();
                    String firstName = inputStream.readUTF();
                    String lastName = inputStream.readUTF();
                    String jobName = inputStream.readUTF();
                    String jobDuration = inputStream.readUTF();
                    String deadline = inputStream.readUTF();

                    jobOwnerData.setProperty(clientID, firstName + "," + lastName + "," + jobName + "," + jobDuration + "," + deadline);

                    SwingUtilities.invokeAndWait(() -> {
                        JFrame approvalFrame = new JFrame("Approve Job?");
                        approvalFrame.setSize(400, 250);
                        approvalFrame.setLayout(null);
                        approvalFrame.setLocationRelativeTo(null);

                        JTextArea info = new JTextArea(
                                "Client ID: " + clientID + "\n" +
                                        "Name: " + firstName + " " + lastName + "\n" +
                                        "Job: " + jobName + " (" + jobDuration + " hours)\n" +
                                        "Deadline: " + deadline
                        );
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
                            out.println("APPROVED");
                            new cloudController().acceptJobOwnerData(clientID);  // Process job owner data
                            approvalFrame.dispose();
                        });

                        rejectButton.addActionListener(e -> {
                            out.println("REJECTED");
                            new cloudController().rejectJobOwnerData(clientID);  // Reject job owner data
                            approvalFrame.dispose();
                        });

                        approvalFrame.setVisible(true);
                    });

                } else if (typeOfInput.equals("Vehicle")) {
                    // Handle Vehicle Owner data
                    String ownerID = inputStream.readUTF();
                    String firstName = inputStream.readUTF();
                    String lastName = inputStream.readUTF();
                    String make = inputStream.readUTF();
                    String model = inputStream.readUTF();
                    String vin = inputStream.readUTF();
                    String residencyTime = inputStream.readUTF();

                    vehicleOwnerData.setProperty(ownerID, firstName + "," + lastName + "," + make + "," + model + "," + vin + "," + residencyTime);

                    SwingUtilities.invokeAndWait(() -> {
                        JFrame approvalFrame = new JFrame("Approve Vehicle?");
                        approvalFrame.setSize(400, 250);
                        approvalFrame.setLayout(null);
                        approvalFrame.setLocationRelativeTo(null);

                        JTextArea info = new JTextArea(
                                "Owner ID: " + ownerID + "\n" +
                                        "Name: " + firstName + " " + lastName + "\n" +
                                        "Vehicle: " + make + " " + model + "\n" +
                                        "VIN: " + vin + ", Residency: " + residencyTime + " months"
                        );
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
                            out.println("APPROVED");
                            new cloudController().acceptVehicleOwnerData(ownerID);  // Process vehicle owner data
                            approvalFrame.dispose();
                        });

                        rejectButton.addActionListener(e -> {
                            out.println("REJECTED");
                            new cloudController().rejectVehicleOwnerData(ownerID);  // Reject vehicle owner data
                            approvalFrame.dispose();
                        });

                        approvalFrame.setVisible(true);
                    });
                }
                clientSocket.close();
            }
        } catch (IOException | InvocationTargetException | InterruptedException e) {
            e.printStackTrace();
            System.out.println("Error: " + e.getMessage());
        }
    }

    JButton backButton, backToSelectionButton, backToLoginButton;
    JLabel titleLabel, welcomeLabel, roleLabel, messageLabel, titleHeading;
    JPanel loginPanel, selectionPanel;

    public static void vehicleInfo(int port) {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Server is running on port " + port);

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Client is connected");

                DataInputStream inputStream = new DataInputStream(clientSocket.getInputStream());
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);

                String ownerID = inputStream.readUTF();
                String firstName = inputStream.readUTF();
                String lastName = inputStream.readUTF();
                String make = inputStream.readUTF();
                String model = inputStream.readUTF();
                String vin = inputStream.readUTF();
                String residencyTime = inputStream.readUTF();

                vehicleOwnerData.setProperty(ownerID, firstName + "," + lastName + "," + make + "," + model + "," + vin + "," + residencyTime);

                SwingUtilities.invokeAndWait(() -> {
                    JFrame approvalFrame = new JFrame("Approve Vehicle?");
                    approvalFrame.setSize(400, 250);
                    approvalFrame.setLayout(null);
                    approvalFrame.setLocationRelativeTo(null);

                    JTextArea info = new JTextArea(
                            "Owner ID: " + ownerID + "\n" +
                                    "Name: " + firstName + " " + lastName + "\n" +
                                    "Vehicle: " + make + " " + model + "\n" +
                                    "VIN: " + vin + ", Residency: " + residencyTime + " months"
                    );
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
                        out.println("APPROVED");
                        new cloudController().acceptVehicleOwnerData(ownerID);
                        approvalFrame.dispose();
                    });

                    rejectButton.addActionListener(e -> {
                        out.println("REJECTED");
                        new cloudController().rejectVehicleOwnerData(ownerID);
                        approvalFrame.dispose();
                    });

                    approvalFrame.setVisible(true);
                });

                clientSocket.close();
            }
        } catch (IOException | InvocationTargetException | InterruptedException e) {
            e.printStackTrace();
            System.out.println("Error: " + e.getMessage());
        }
    }

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

        JButton backButton = new JButton("Back");
        backButton.setBounds(50, 320, 100, 30);
        backButton.setBackground(Color.LIGHT_GRAY);
        backgroundPanel.add(backButton);

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new LoginPage();
            }
        });

        setVisible(true);
    }

    public void acceptJobOwnerData(String clientID) {
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
            FileCreationFinal.jobOwnerFileCreate(filePath, clientID, firstName, lastName, jobName, jobDuration, deadline);
        }
    }

    public void rejectJobOwnerData(String clientID) {
        jobOwnerData.remove(clientID);
        System.out.println("Data for Client ID " + clientID + " has been rejected and removed.");
    }

    public void acceptVehicleOwnerData(String ownerID) {
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
            FileCreationFinal.vehicleOwnerFileCreate(filePath, ownerID, firstName, lastName, make, model, vin, residencyTime);
        }
    }

    public void rejectVehicleOwnerData(String ownerID) {
        vehicleOwnerData.remove(ownerID);
        System.out.println("Data for Owner ID " + ownerID + " has been rejected and removed.");
    }

    public Properties getJobOwnerData() {
        return jobOwnerData;
    }

    public Properties getVehicleOwnerData() {
        return vehicleOwnerData;
    }
}
