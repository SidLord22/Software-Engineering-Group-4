import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Client extends JFrame{

    JButton loginButton;


    public Client() {
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

        JLabel titleLabel = new JLabel("Client", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 30));
        titleLabel.setBounds(150, 20, 300, 50);
        backgroundPanel.add(titleLabel);

        JLabel titleLabel2 = new JLabel("Select Your Role", SwingConstants.CENTER);
        titleLabel2.setFont(new Font("Arial", Font.BOLD, 15));
        titleLabel2.setBounds(150, 70, 300, 50);
        backgroundPanel.add(titleLabel2);


        JComboBox<String> roleDropdown = new JComboBox<>(new String[]{"Vehicle Owner", "Job Owner"});
        roleDropdown.setBounds(225, 130, 150, 40);
        roleDropdown.setFont(new Font("Arial", Font.PLAIN, 16));
        roleDropdown.setBackground(Color.WHITE);
        roleDropdown.setForeground(Color.BLACK);
        roleDropdown.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        backgroundPanel.add(roleDropdown);

        JButton loginButton = new JButton("Login");
        loginButton.setBounds(250, 200, 100, 40);
        backgroundPanel.add(loginButton);

        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                String selectedItem = (String) roleDropdown.getSelectedItem();
                if (selectedItem.equals("Vehicle Owner")) {
                    new vehicleOwner();
                } else if(selectedItem.equals("Job Owner")) {
                    new jobOwner();

                }
            }});


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

}
