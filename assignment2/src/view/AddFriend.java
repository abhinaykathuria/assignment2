package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.apache.log4j.Logger;

import Exception.NotToBeColleaguesException;
import Exception.NotToBeFriendsException;
import Exception.TooYoungException;
import controller.AddNewUserController;
import controller.MakeNewConnectionController;
import controller.SearchUserController;
import model.Adult;
import model.User;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.awt.event.ActionEvent;

public class AddFriend extends JFrame {
	private static Logger logger = Logger.getLogger(AddFriend.class);
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
					logger.debug("In Whatever it is ");
					AddFriend frame = new AddFriend();
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
	public AddFriend() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblAddFriend = new JLabel("Add Friend");
		lblAddFriend.setBounds(136, 19, 106, 16);
		contentPane.add(lblAddFriend);

		JLabel lblFriend = new JLabel("Name for first person:");
		lblFriend.setBounds(6, 66, 165, 16);
		contentPane.add(lblFriend);

		textField = new JTextField();
		textField.setBounds(163, 61, 130, 26);
		contentPane.add(textField);
		textField.setColumns(10);

		JLabel lblNameForSecond = new JLabel("Name for second person:");
		lblNameForSecond.setBounds(6, 105, 165, 16);
		contentPane.add(lblNameForSecond);

		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(163, 100, 130, 26);
		contentPane.add(textField_1);

		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"friends", "classmate", "colleague"}));
		comboBox.setBounds(173, 136, 98, 27);
		contentPane.add(comboBox);

		JButton btnConnect = new JButton("Connect");
		btnConnect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				logger.debug("In AddFriend- Connect button clicked");

				if(textField.getText().isEmpty())
				{
					JOptionPane.showMessageDialog(null, "Person 1 cannot be empty");
					logger.debug("Person 1 empty");

					return;
				}
				if(textField_1.getText().isEmpty())
				{
					JOptionPane.showMessageDialog(null, "Person 2 cannot be empty!");
					logger.debug("Person 2 empty");
					return;
				}
				MakeNewConnectionController lMakeNewConnectionController=new MakeNewConnectionController();
				String userName=textField.getText();
				String userName1=textField_1.getText();
				AddNewUserController lAddNewUserController=new AddNewUserController();
				String relation=comboBox.getSelectedItem().toString();

				if(!lAddNewUserController.checkUserId(userName))
				{
					if(!lAddNewUserController.checkUserId(userName1))
					{
						SearchUserController lSearchUserController=new SearchUserController();
						User user;
						User user1;
						if(lMakeNewConnectionController.checkConnection(userName,userName1))
						{
							logger.debug("Already Connected");
							JOptionPane.showMessageDialog(null, "Already Connected!");
							return;
						}
						boolean checkConnection;
						try {
							checkConnection=lMakeNewConnectionController.createConnection(userName, userName1, relation);
						} catch (TooYoungException | NotToBeFriendsException | NotToBeColleaguesException e3) {
							// TODO Auto-generated catch block
							JOptionPane.showMessageDialog(null, e3.getMessage());
							return;
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							JOptionPane.showMessageDialog(null, e1.getMessage());
							return;
						}
						if(checkConnection==true)
						{
							logger.debug("Connected");
							JOptionPane.showMessageDialog(null, "Connected!");	
						}
					}
					else
					{
						logger.debug("Username for Person 2 not Correct!");
						JOptionPane.showMessageDialog(null, "Username for Person 2 not Correct!");	
						return;
					}
				}
				else
				{
					logger.debug("Username for Person 1 not Correct!");
					JOptionPane.showMessageDialog(null, "Username for Person 1 not Correct!");	
					return;

				}

			}
		});
		btnConnect.setBounds(124, 191, 117, 29);
		contentPane.add(btnConnect);



		JLabel lblRelation = new JLabel("Relation");
		lblRelation.setBounds(6, 140, 165, 16);
		contentPane.add(lblRelation);

		JButton btnMenu = new JButton("Menu");
		btnMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				logger.debug("In AddFriend- Menu button clicked");
				dispose();
				MiniNet lMiniNet=new MiniNet();
				lMiniNet.setVisible(true);
			}
		});
		btnMenu.setBounds(327, 6, 117, 29);
		contentPane.add(btnMenu);
	}
}
