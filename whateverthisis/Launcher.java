import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class Launcher extends JFrame {
    public Launcher() {
        setTitle("Launcher");
        setSize(800, 600);
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
        titleLabel.setBounds(250, 20, 300, 50);
        backgroundPanel.add(titleLabel);

        JLabel selectRole = new JLabel("Select Client or Server:");
        selectRole.setFont(new Font("Arial", Font.PLAIN, 16));
        selectRole.setBounds(315, 80, 300, 30);
        backgroundPanel.add(selectRole);

        JButton clientButton = new JButton("Client");
        clientButton.setBounds(100, 150, 175, 80);
        backgroundPanel.add(clientButton);

        JButton serverButton = new JButton("Server");
        serverButton.setBounds(500, 150, 175, 80);
        backgroundPanel.add(serverButton);

        clientButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new LoginPage(); // Opens the LoginPage frame
            }
        });

        serverButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new cloudController(); // Opens the CloudController frame
            }
        });
    }
}
