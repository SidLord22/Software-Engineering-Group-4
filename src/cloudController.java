import javax.swing.*;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.*;
import java.util.ArrayList;

public class cloudController extends JFrame {
	
	JButton backButton, backToSelectionButton, backToLoginButton;
    JLabel titleLabel, welcomeLabel, roleLabel, messageLabel, titleHeading;
    JPanel loginPanel,selectionPanel;
    
	public cloudController() {
		
	setTitle("Cloud Controller");
    
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
	
}
