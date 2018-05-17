package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.apache.log4j.Logger;

import controller.AddNewUserController;
import controller.MakeNewConnectionController;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CheckConnection extends JFrame {
	private static Logger logger = Logger.getLogger(CheckConnection.class);
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					logger.debug("In CheckConnection- main function");
					CheckConnection frame = new CheckConnection();
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
	public CheckConnection() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblCheckConnection = new JLabel("Check Connection");
		lblCheckConnection.setBounds(137, 30, 120, 16);
		contentPane.add(lblCheckConnection);
		
		JLabel lblName = new JLabel("Name 1:");
		lblName.setBounds(6, 75, 61, 16);
		contentPane.add(lblName);
		
		JLabel lblName_1 = new JLabel("Name 2:");
		lblName_1.setBounds(6, 110, 61, 16);
		contentPane.add(lblName_1);
		
		textField = new JTextField();
		textField.setBounds(79, 70, 130, 26);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(79, 105, 130, 26);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		JButton btnNewButton = new JButton("Check Connection");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				logger.debug("In CheckConnection- Check Connection button clicked");
				if(textField.getText().isEmpty())
				{
					logger.debug("Person 1 empty");
					JOptionPane.showMessageDialog(null, "Person 1 cannot be empty");
					return;
				}
				if(textField_1.getText().isEmpty())
				{
					logger.debug("Person 2 empty");
					JOptionPane.showMessageDialog(null, "Person 2 cannot be empty!");
					return;
				}
				MakeNewConnectionController lMakeNewConnectionController=new MakeNewConnectionController();
				String userName=textField.getText();
				String userName1=textField_1.getText();
				AddNewUserController lAddNewUserController=new AddNewUserController();
				if(!lAddNewUserController.checkUserId(userName))
				{
					if(!lAddNewUserController.checkUserId(userName1))
					{
						if(!lMakeNewConnectionController.checkConnection(userName,userName1))
						{
							logger.debug("Not Connected");

							JOptionPane.showMessageDialog(null, "Not Connected!");
							return;
						}
						else
						{

							String relation=lMakeNewConnectionController.getConnection(userName,userName1);
							logger.debug("Connected as "+relation);
							JOptionPane.showMessageDialog(null, "Connected as "+relation );

						}
					}
					else
					{
						logger.debug("Username for User 2 not Correct!");
						JOptionPane.showMessageDialog(null, "Username for User 2 not Correct!");	
						return;

					}
				}
				else
				{
					logger.debug("Username for User 1 not Correct!");
					JOptionPane.showMessageDialog(null, "Username for User 1 not Correct!");	
					return;
				}
				
				
			}
		});
		btnNewButton.setBounds(137, 143, 146, 29);
		contentPane.add(btnNewButton);
		
		JButton btnMenu = new JButton("Menu");
		btnMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				logger.debug("In CheckConnection- Menu button clicked");
				dispose();
				MiniNet lMiniNet=new MiniNet();
				lMiniNet.setVisible(true);
			}
		});
		btnMenu.setBounds(311, 17, 117, 29);
		contentPane.add(btnMenu);
	}

}
