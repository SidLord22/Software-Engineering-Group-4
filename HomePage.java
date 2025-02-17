import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;

public class HomePage extends JFrame {
    private JTextField userField, firstNameField, lastNameField, emailField;
    private JPasswordField passField;
    private JButton loginButton, jobOwnerButton, vehicleOwnerButton, adminButton, forgotPasswordButton, createAccountButton, backButton, registerButton, backToSelectionButton, backToLoginButton;
    private JLabel messageLabel, titleLabel, titleHeading;
    private JPanel selectionPanel, loginPanel, registrationPanel;
    private JComboBox<String> roleComboBox;
    private HashMap<String, String> users;

    public HomePage() {
        setTitle("Vehicular Cloud Real Time System (VCRTS)");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new CardLayout());
        
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setSize(screenSize.width / 2, screenSize.height / 2);
        setLocationRelativeTo(null);
        
        users = new HashMap<>();
        users.put("user1", "pass1");
        users.put("user2", "pass2");
        users.put("user3", "pass3");
        
        // Home Page Panel
        selectionPanel = new JPanel(new BorderLayout());
        selectionPanel.setBackground(new Color(135, 206, 235));
        
        titleLabel = new JLabel("Welcome!", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 30));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(20, 10, 20, 10));
        selectionPanel.add(titleLabel, BorderLayout.NORTH);
        
        titleHeading = new JLabel ("Please Select Your Role", SwingConstants.CENTER);
        titleHeading.setFont(new Font("Arial", Font.BOLD, 15));
        titleHeading.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        selectionPanel.add(titleHeading, BorderLayout.SOUTH);
        
        
        JPanel buttonPanel = new JPanel(new GridLayout(5, 1, 10, 10));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(80, 50, 10, 50));
        buttonPanel.setBackground(new Color(135, 206, 235));
        
        jobOwnerButton = new JButton("Job Owner");
        vehicleOwnerButton = new JButton("Vehicle Owner");
        adminButton = new JButton("Administration");
        createAccountButton = new JButton("Create an Account");
        styleSecondaryButton(createAccountButton);
        
        buttonPanel.add(jobOwnerButton);
        buttonPanel.add(vehicleOwnerButton);
        buttonPanel.add(adminButton);
        buttonPanel.add(createAccountButton);
        selectionPanel.add(buttonPanel, BorderLayout.CENTER);
        
        
        //Where each button takes you
        jobOwnerButton.addActionListener(e -> switchToLogin());
        vehicleOwnerButton.addActionListener(e -> switchToLogin());
        adminButton.addActionListener(e -> switchToLogin());
        createAccountButton.addActionListener(e -> switchToRegistration());
        
        // Login Panel
        loginPanel = new JPanel(new GridBagLayout());
        loginPanel.setBackground(new Color(135, 206, 235));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        
        titleLabel = new JLabel("Login to Your Account", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 22));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        loginPanel.add(titleLabel, gbc);
        
        gbc.gridwidth = 1;
        gbc.gridy = 1;
        loginPanel.add(new JLabel("Username:"), gbc);
        gbc.gridx = 1;
        userField = new JTextField(15);
        loginPanel.add(userField, gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 2;
        loginPanel.add(new JLabel("Password:"), gbc);
        gbc.gridx = 1;
        passField = new JPasswordField(15);
        loginPanel.add(passField, gbc);
        
        gbc.gridx = 1;
        gbc.gridy = 3;
        loginButton = new JButton("Login");
        loginPanel.add(loginButton, gbc);
        
        gbc.gridx = 1;
        gbc.gridy = 8;
        forgotPasswordButton = new JButton("Forgot Password?");
        styleSecondaryButton(forgotPasswordButton);
        loginPanel.add(forgotPasswordButton, gbc);
        
        gbc.gridx = 1;
        gbc.gridy = 4;
        backToSelectionButton = new JButton("Back");
        backToSelectionButton.addActionListener(e -> switchToSelection());
        loginPanel.add(backToSelectionButton, gbc);
        
        // Registration Panel
        registrationPanel = new JPanel(new GridBagLayout());
        registrationPanel.setBackground(new Color(135, 206, 235));
        
        gbc.gridx = 0;
        gbc.gridy = 0;
        registrationPanel.add(new JLabel("First Name:"), gbc);
        gbc.gridx = 1;
        firstNameField = new JTextField(15);
        registrationPanel.add(firstNameField, gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 1;
        registrationPanel.add(new JLabel("Last Name:"), gbc);
        gbc.gridx = 1;
        lastNameField = new JTextField(15);
        registrationPanel.add(lastNameField, gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 2;
        registrationPanel.add(new JLabel("Email:"), gbc);
        gbc.gridx = 1;
        emailField = new JTextField(15);
        registrationPanel.add(emailField, gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 3;
        registrationPanel.add(new JLabel("Job Role:"), gbc);
        gbc.gridx = 1;
        roleComboBox = new JComboBox<>(new String[]{"Job Owner", "Vehicle Owner", "Admin"});
        registrationPanel.add(roleComboBox, gbc);
        
        gbc.gridx = 1;
        gbc.gridy = 4;
        registerButton = new JButton("Register");
        registrationPanel.add(registerButton, gbc);
        
        gbc.gridx = 0;
        backToLoginButton = new JButton("Back");
        backToLoginButton.addActionListener(e -> switchToSelection());
        registrationPanel.add(backToLoginButton, gbc);
        
        add(selectionPanel);
        setVisible(true);
    }
    
    private void switchToRegistration() {
        getContentPane().removeAll();
        add(registrationPanel);
        revalidate();
        repaint();
    }
    
    private void switchToSelection() {
        getContentPane().removeAll();
        add(selectionPanel);
        revalidate();
        repaint();
    }
    
    private void switchToLogin() {
        getContentPane().removeAll();
        add(loginPanel);
        revalidate();
        repaint();
    }
    
    private void styleSecondaryButton(JButton button) {
        button.setFont(new Font("Arial", Font.PLAIN, 14));
        button.setForeground(new Color(0, 0, 0));
        button.setBackground(new Color (135, 206, 235));
        button.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        button.setFocusPainted(false);
    }
    
// Running your Page
    public static void main(String[] args) {
        new HomePage(); 
    }
}
