package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

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
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class FindChildren extends JFrame {
	private static Logger logger = Logger.getLogger(FindChildren.class);

	private JPanel contentPane;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					logger.debug("In FindChildren- main function");
					FindChildren frame = new FindChildren();
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
	public FindChildren() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblFindChildren = new JLabel("Find Children");
		lblFindChildren.setBounds(144, 39, 95, 16);
		contentPane.add(lblFindChildren);
		
		JLabel lblName = new JLabel("Name:");
		lblName.setBounds(6, 72, 61, 16);
		contentPane.add(lblName);
		
		textField = new JTextField();
		textField.setBounds(79, 67, 130, 26);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblListOfChildren = new JLabel("List Of children");
		lblListOfChildren.setBounds(6, 144, 117, 16);
		contentPane.add(lblListOfChildren);
		lblListOfChildren.setVisible(false);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(132, 144, 284, 16);
		contentPane.add(lblNewLabel);
		lblNewLabel.setVisible(false);
		
		JButton btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				logger.debug("In FindParents- search button clicked");	
				if(textField.getText().isEmpty())
				{
					logger.debug("Name not entered");	
					JOptionPane.showMessageDialog(null, "Please enter Name to be searched!");
					return;
				}
				String userName=textField.getText();
				AddNewUserController lAddNewUserController=new AddNewUserController();
				if(!lAddNewUserController.checkUserId(userName))
				{
					SearchUserController lSearchUserController=new SearchUserController();
					User user;
					try {
						user=lSearchUserController.searchUser(userName.toLowerCase());
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						JOptionPane.showMessageDialog(null, "File not found");	
						return;
					}
					if(user!=null)
					{
						if(user.getAge()>16)
						{
							logger.debug("User is an Adult");
							lblListOfChildren.setVisible(true);
							lblNewLabel.setVisible(true);
							String list;
							ArrayList<String> childList=lSearchUserController.getChildren(user.getName());
							//System.out.println(childList);
							lblNewLabel.setText(childList.toString());
							
						}
						else
						{
							logger.debug("User is not Adult");	
							JOptionPane.showMessageDialog(null, "User is not Adult");
							return;
						}
					}
				}
				else
				{
					lblListOfChildren.setVisible(false);
					lblNewLabel.setVisible(false);
					JOptionPane.showMessageDialog(null, "Username not available!");
					return;
				}
			}
		});
		btnSearch.setBounds(122, 105, 117, 29);
		contentPane.add(btnSearch);
		
		JButton btnMenu = new JButton("Menu");
		btnMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				MiniNet lMiniNet=new MiniNet();
				lMiniNet.setVisible(true);
			}
		});
		btnMenu.setBounds(327, 6, 117, 29);
		contentPane.add(btnMenu);
		
		
	}

}
