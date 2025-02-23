import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JEditorPane;
import javax.swing.JCheckBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import java.awt.Color;

public class JobOwner extends JPanel {

//Andrea: I deleted the main because it was not necessary since we are connecting
// the homepage to each login, instead of it being a "frame", it became a 
// "panel", where it basically all just stays in one window instead of making
// multiple windows
	
	public JobOwner (HomePage parentFrame ){
		setLayout(null);
		setBackground(new Color(82, 138, 174));

		// Andrea: Adding Username Field
		JLabel lblNewLabel = new JLabel("Username:");
		lblNewLabel.setBounds(48, 83, 78, 19);
		add(lblNewLabel);
		
		
		JEditorPane usernameField = new JEditorPane();
        usernameField.setBounds(166, 83, 190, 19);
        add(usernameField);
        
        // Andrea: Adding Password Field
        JLabel lblPassword = new JLabel("Password:");
		lblPassword.setBounds(48, 139, 78, 19);
		add(lblPassword);
		
		JEditorPane passwordField = new JEditorPane();
        passwordField.setBounds(166, 139, 190, 19);
        add(passwordField);
        
        
        // Andrea: Login Button
    	JButton btnLogin = new JButton("Login");
		btnLogin.setBounds(324, 232, 85, 21);
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//Andrea: This is where you add login authentication (back-end database)
			
				System.out.println("login successful!");
				
				
				//  parentFrame.showNextPage();
				// ^ Andrea: not sure if this can help, was doing trial and error
			}
		});
		add(btnLogin);
		
		// Andrea: Signup Button 
		JButton btnSignup = new JButton("Don't have an account? Make one now!");
		btnSignup.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnSignup.setBounds(10, 232, 265, 21);
		add(btnSignup);
        
		
		setVisible(true); 
		// Andrea: I had to set the Visibility here
		// to true because it was not showing up from the homepage.
        
  /*
   * 
   * 
   * Andrea: 
   * 
   * I commented this part out simply because I didn't want to delete someone else's code,
   * and wasn't sure if it might be relevent in the future. 
   * 
   * Because of the formatting issues with panel,
   * I had to fix certain things in order for it to call and display the code. 
   * 
   * "contentPane" is no longer necessary to use, making it a cleaner and easier look.
   * 
   * It was mainly the formatting that needed to be fixed, and since the homepage is now a 
   * (parent panel), this makes it easier to not write the same code over and over again, for example ...
   * "dimensions", "sizing", "color", etc etc. 
   * 
   * I left some methods blank because that would be the place of where you would start to input the
   * back-end code, so I wasn't entirely sure what to do in that part, but hopefully it helps you see where
   * you need to add it and such
   * 
   * I also changed up the name of the buttons for a simpler and easier way to navigate each button.
   * 
   * 
   * 
		JEditorPane dtrpnUsername = new JEditorPane();
		dtrpnUsername.setBounds(166, 83, 190, 19);
		contentPane.add(dtrpnUsername);
		
		
		JEditorPane editorPane = new JEditorPane();
		editorPane.setBounds(166, 139, 190, 19);
		contentPane.add(editorPane);
		
		contentPane.add(btnNewButton);
		
		
		JLabel lblEnterYourClient = new JLabel("Enter your Client information now:");
		lblEnterYourClient.setBounds(112, 36, 244, 19);
		contentPane.add(lblEnterYourClient);
		
		JButton btnNewButton_1 = new JButton("Forgot Password?");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton_1.setBounds(180, 181, 164, 21);
		contentPane.add(btnNewButton_1);
		
	
		
		*/
		
		
	}
}
