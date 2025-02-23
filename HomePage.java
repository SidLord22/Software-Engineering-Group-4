import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;

public class HomePage extends JFrame {
    private JTextField userField, firstNameField, lastNameField, emailField;
    private JPasswordField passField;
    
    private JButton loginButton, jobOwnerButton, vehicleOwnerButton, 
    adminButton, forgotPasswordButton, createAccountButton, backButton, registerButton, 
    backToSelectionButton, backToLoginButton;
    
    private JLabel titleLabel, welcomeLabel, roleLabel, messageLabel, titleHeading;
    private JPanel loginPanel, registrationPanel, selectionPanel;
    private JComboBox<String> roleComboBox;
    private HashMap<String, String> users;

    public HomePage() {
        setTitle("Vehicular Cloud Real Time System (VCRTS)");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new CardLayout());
        
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setSize(screenSize.width / 2, screenSize.height / 2);
        setLocationRelativeTo(null);

        /* dummy user login to test
         * 
        users = new HashMap<>();
        users.put("user1", "pass1");
        users.put("user2", "pass2");
        users.put("user3", "pass3");
        
        */

        /*Andrea: 2/23/2025
         * 
         * I got rid of the buttons that contained each role, and instead created a drop down menu.
         * This allows the user to chose their role, then proceed to login, or create a new account
         * 
         * Also made a few changes to formatting to make it look more appealing
         */
        
        
        // Login Panel
        loginPanel = new JPanel(new GridBagLayout());
        loginPanel.setBackground(new Color(82, 138, 174));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        welcomeLabel = new JLabel("Welcome!", SwingConstants.CENTER);
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 30));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        loginPanel.add(welcomeLabel, gbc);

        titleLabel = new JLabel("Login to Your Account", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 22));
        gbc.gridy = 1;
        loginPanel.add(titleLabel, gbc);

        roleLabel = new JLabel("Select Role:");
        gbc.gridy = 2;
        gbc.gridx = 0;
        gbc.gridwidth = 1;
        loginPanel.add(roleLabel, gbc);
        
        roleComboBox = new JComboBox<>(new String[]{"Job Owner", "Vehicle Owner", "Admin"});
        gbc.gridx = 1;
        loginPanel.add(roleComboBox, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        loginPanel.add(new JLabel("Username:"), gbc);
        gbc.gridx = 1;
        userField = new JTextField(15);
        loginPanel.add(userField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        loginPanel.add(new JLabel("Password:"), gbc);
        gbc.gridx = 1;
        passField = new JPasswordField(15);
        loginPanel.add(passField, gbc);

        gbc.gridx = 1;
        gbc.gridy = 5;
        loginButton = new JButton("Login");
        loginButton.addActionListener(e -> handleLogin());
        loginPanel.add(loginButton, gbc);

       /* Andrea: I commented this out because I am not sure if we should add this just yet
        gbc.gridy = 6;
        forgotPasswordButton = new JButton("Forgot Password?");
        styleSecondaryButton(forgotPasswordButton);
        loginPanel.add(forgotPasswordButton, gbc);
        
        */

        gbc.gridy = 7;
        createAccountButton = new JButton("Create an Account");
        createAccountButton.addActionListener(e -> switchToRegistration());
        styleSecondaryButton(createAccountButton);
        loginPanel.add(createAccountButton, gbc);

        // Create an Account Page
        registrationPanel = new JPanel(new GridBagLayout());
        registrationPanel.setBackground(new Color(82, 138, 174));

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
        backToLoginButton.addActionListener(e -> switchToLogin());
        registrationPanel.add(backToLoginButton, gbc);
        
        add(loginPanel);
        
        setVisible(true);
    }

    
/*Andrea: ( handleLogin() ) ... 
 * This allows the user to chose which role they are, enter their name and password,
 * then after if their credentials are valid, they will be taken to the VehicleOwner Frame and
 * the JobOwner frame. 
 * 
 * You may want to replace the "containsKey" or what not depending on how you constructed your back-end
 * But this is the main method of taking them into the next frame
 * back-end takes care of making sure login credentials are valid or not, so what I wrote is not
 * entirely correct, please change accordingly. 
 * 
 */
    private void handleLogin() {
        String username = userField.getText();
        String password = new String(passField.getPassword());
        String selectedRole = (String) roleComboBox.getSelectedItem();
        
        if (users.containsKey(username) && users.get(username).equals(password)) {
            if ("Vehicle Owner".equals(selectedRole)) {
                new VehicleOwner();
            } else if ("Job Owner".equals(selectedRole)) {
                new JobOwner();
            }
            dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Invalid username or password", "Login Failed", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void switchToRegistration() {
        getContentPane().removeAll();
        add(registrationPanel);
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
        button.setForeground(Color.BLACK);
        button.setBackground(new Color(82, 138, 174));
        button.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        button.setFocusPainted(false);
    }

    public static void main(String[] args) {
        new HomePage();
    }
}
