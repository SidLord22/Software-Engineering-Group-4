import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class LoginPage extends JFrame{
    JTextField emailField;
    JPasswordField passwordField;
    JButton backButton, backToSelectionButton, backToLoginButton;
    JLabel titleLabel, welcomeLabel, roleLabel, messageLabel, titleHeading;
    JPanel loginPanel, registrationPanel, selectionPanel;
    
    
    public LoginPage() {
        setTitle("VCRTS");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        JPanel backgroundPanel = new JPanel();
        backgroundPanel.setLayout(null);
        backgroundPanel.setBackground(new Color(82, 138, 174));
        backgroundPanel.setBounds(0, 0, getWidth(), getHeight());
        add(backgroundPanel);

        JLabel titleLabel = new JLabel("Welcome", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 30));
        titleLabel.setBounds(150, 20, 300, 50);
        backgroundPanel.add(titleLabel);

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

        JButton loginButton = new JButton("Login");
        loginButton.setBounds(250, 180, 100, 40);
        backgroundPanel.add(loginButton);

        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                String selectedItem = (String) roleDropdown.getSelectedItem();
                if (selectedItem.equals("Vehicle Owner")) {
                    new vehicleOwner();
                } else (selectedItem.equals("Job Owner")) {
                    new jobOwner();
                } else if (selectedItem.equals("Cloud Controller")) {
                    new cloudController();
                }
            }
        });
        setVisible(true);
    }
    
   
    
}
