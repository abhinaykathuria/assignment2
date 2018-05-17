package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.apache.log4j.Logger;

import controller.AddNewUserController;
import controller.SearchUserController;
import model.User;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class ViewUser extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private static Logger logger = Logger.getLogger(MiniNet.class);

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					logger.debug("In ViewUser- main function");
					ViewUser frame = new ViewUser();
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
	public ViewUser() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 349);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblSearchUser = new JLabel("Search User");
		lblSearchUser.setBounds(192, 25, 109, 16);
		contentPane.add(lblSearchUser);

		JLabel lblNameToBe = new JLabel("Name to be searched");
		lblNameToBe.setBounds(25, 56, 147, 16);
		contentPane.add(lblNameToBe);

		textField = new JTextField();
		textField.setBounds(184, 53, 130, 26);
		contentPane.add(textField);
		textField.setColumns(10);



		JLabel lblName = new JLabel("Name:");
		lblName.setBounds(21, 127, 61, 16);
		contentPane.add(lblName);
		lblName.setVisible(false);

		JLabel label = new JLabel("");
		label.setBounds(111, 127, 91, 16);
		contentPane.add(label);
		label.setVisible(false);

		JLabel lblStatus = new JLabel("Status:");
		lblStatus.setBounds(21, 155, 61, 16);
		contentPane.add(lblStatus);
		lblStatus.setVisible(false);

		JLabel label_1 = new JLabel("");
		label_1.setBounds(111, 155, 91, 16);
		contentPane.add(label_1);
		label_1.setVisible(false);

		JLabel lblAge = new JLabel("Age");
		lblAge.setBounds(21, 184, 61, 16);
		contentPane.add(lblAge);
		lblAge.setVisible(false);

		JLabel label_3 = new JLabel("");
		label_3.setBounds(111, 184, 91, 16);
		contentPane.add(label_3);
		label_3.setVisible(false);

		JLabel lblGender = new JLabel("Gender");
		lblGender.setBounds(21, 212, 61, 16);
		contentPane.add(lblGender);
		lblGender.setVisible(false);

		JLabel lblState = new JLabel("State:");
		lblState.setBounds(21, 243, 61, 16);
		contentPane.add(lblState);
		lblState.setVisible(false);

		JLabel label_4 = new JLabel("");
		label_4.setBounds(111, 243, 91, 16);
		contentPane.add(label_4);
		label_4.setVisible(false);

		JLabel label_2 = new JLabel("");
		label_2.setBounds(111, 212, 81, 16);
		contentPane.add(label_2);
		label_2.setVisible(false);

		JLabel lblParent = new JLabel("Parent 1");
		lblParent.setBounds(21, 274, 61, 16);
		contentPane.add(lblParent);
		lblParent.setVisible(false);

		JLabel label_5 = new JLabel("");
		label_5.setBounds(111, 271, 61, 16);
		contentPane.add(label_5);
		label_5.setVisible(false);

		JLabel lblNewLabel = new JLabel("Parent 2");
		lblNewLabel.setBounds(21, 305, 61, 16);
		contentPane.add(lblNewLabel);
		lblNewLabel.setVisible(false);

		JLabel label_6 = new JLabel("");
		label_6.setBounds(104, 305, 61, 16);
		contentPane.add(label_6);
		label_6.setVisible(false);

		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setBounds(329, 25, 115, 149);
		contentPane.add(lblNewLabel_1);
		lblNewLabel_1.setVisible(true);


		JButton btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				logger.debug("In ViewUser- Search button clicked");

				if(textField.getText().isEmpty())
				{
					logger.debug("Name is empty");
					JOptionPane.showMessageDialog(null, "Please enter Name to be searched!");
					return;
				}
				String userName=textField.getText();
				AddNewUserController lAddNewUserController=new AddNewUserController();
				if(!lAddNewUserController.checkUserId(userName))
				{
					lblName.setVisible(true);
					label.setVisible(true);
					lblStatus.setVisible(true);
					label_1.setVisible(true);
					lblAge.setVisible(true);
					label_3.setVisible(true);
					lblGender.setVisible(true);
					label_2.setVisible(true);
					lblState.setVisible(true);
					label_4.setVisible(true);
					SearchUserController lSearchUserController=new SearchUserController();
					User user;
					try {
						user=lSearchUserController.searchUser(userName.toLowerCase());
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						JOptionPane.showMessageDialog(null, "Image not available");
						logger.debug("Image not available");
						return;
					}
					if(user!=null)
					{
						logger.debug("User found");
						label.setText(user.getName());
						label_1.setText(user.getStatus());
						label_3.setText(user.getAge()+"");
						label_2.setText(user.getGender());
						label_4.setText(user.getState());
						if(user.getDisplay_picture()!=null)
						{
							logger.debug("Image found");
							ImageIcon image = new ImageIcon(new ImageIcon(user.getDisplay_picture()).getImage().getScaledInstance(160, 170, Image.SCALE_SMOOTH));
							lblNewLabel_1.setIcon(image);
							lblNewLabel_1.setVisible(true);
						}
						else
						{
							logger.debug("Image not found");
							lblNewLabel_1.setVisible(false);

						}
						if(user.getAge()<16)
						{
							logger.debug("User is not an adult");
							lblParent.setVisible(true);		
							label_5.setVisible(true);
							lblNewLabel.setVisible(true);
							label_6.setVisible(true);

							ArrayList<String> parentsList=lSearchUserController.getParents(user.getName());
							//System.out.println(parentsList);
							label_5.setText(parentsList.get(0));
							label_6.setText(parentsList.get(1));
						}
					}

				}
				else
				{
					lblName.setVisible(false);
					label.setVisible(false);
					lblStatus.setVisible(false);
					label_1.setVisible(false);
					lblAge.setVisible(false);
					label_3.setVisible(false);
					lblGender.setVisible(false);
					//	label_5.setVisible(false);
					lblState.setVisible(false);
					label_4.setVisible(false);
					lblParent.setVisible(false);		
					label_5.setVisible(false);
					lblNewLabel.setVisible(false);
					label_6.setVisible(false);
					logger.debug("Username not available"+ userName);
					JOptionPane.showMessageDialog(null, "Username not available!");	
					return;
				}
			}
		});
		btnSearch.setBounds(139, 91, 117, 29);
		contentPane.add(btnSearch);

		JButton btnMenu = new JButton("Menu");
		btnMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				logger.debug("In ViewUser-Menu button clicked");
				dispose();
				MiniNet lMiniNet=new MiniNet();
				lMiniNet.setVisible(true);
			}
		});
		btnMenu.setBounds(329, 0, 117, 29);
		contentPane.add(btnMenu);	

	}
}
