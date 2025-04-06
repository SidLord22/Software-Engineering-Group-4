import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginPage extends JFrame{
    JTextField emailField;
    JPasswordField passwordField;
    JButton ServerButton, ClientButton;
    JLabel titleLabel,titleLabel2, welcomeLabel, roleLabel, messageLabel, titleHeading;
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

        JLabel titleLabel2 = new JLabel("Select Your Role", SwingConstants.CENTER);
        titleLabel2.setFont(new Font("Arial", Font.BOLD, 15));
        titleLabel2.setBounds(150, 70, 300, 50);
        backgroundPanel.add(titleLabel2);


        JButton ServerButton = new JButton("Server");
        ServerButton.setBounds(180, 140, 100, 40);
        backgroundPanel.add(ServerButton);

        ServerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new cloudController();
            }
        });

        JButton ClientButton = new JButton("Client");
        ClientButton.setBounds(320, 140, 100, 40);
        backgroundPanel.add(ClientButton);

        ClientButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Client();
            }
        });

        setVisible(true);
    }

}
