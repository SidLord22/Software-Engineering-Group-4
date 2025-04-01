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
import javax.swing.JComboBox;

public class JobOwner extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JobOwner frame = new JobOwner();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public JobOwner() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(128, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JEditorPane editorPane = new JEditorPane();
		editorPane.setBounds(166, 139, 190, 19);
		contentPane.add(editorPane);
		
		JButton btnNewButton = new JButton("Login");
		btnNewButton.setBounds(324, 232, 85, 21);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		contentPane.add(btnNewButton);
		
		JEditorPane dtrpnUsername = new JEditorPane();
		dtrpnUsername.setBounds(166, 83, 190, 19);
		contentPane.add(dtrpnUsername);
		
		JLabel lblNewLabel = new JLabel("Username:");
		lblNewLabel.setBounds(48, 83, 78, 19);
		contentPane.add(lblNewLabel);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setBounds(48, 139, 78, 19);
		contentPane.add(lblPassword);
		
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
		
		JButton btnNewButton_1_1 = new JButton("Don't have an account? Make one now!");
		btnNewButton_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton_1_1.setBounds(10, 232, 265, 21);
		contentPane.add(btnNewButton_1_1);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(37, 181, 114, 21);
		contentPane.add(comboBox);
	}
}
