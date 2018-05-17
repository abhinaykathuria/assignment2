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
import helper.CreateTableDAO;
import helper.FileReading;
import view.AddFriend;
import view.AddNewUser;
import view.CheckConnection;
import view.DeleteUser;
import view.FindChildren;
import view.FindParents;
import view.ViewUser;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.File;
import java.awt.event.ActionEvent;

public class MiniNet extends JFrame {

	private JPanel contentPane;
	 private static Logger logger = Logger.getLogger(MiniNet.class);

	/**
	 * Launch the application.
	 */
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					logger.debug("In MiniNet main function");
					MiniNet lMiniNet=new MiniNet();
					File tmpDir = new File("sample.db");
					boolean checkDB=tmpDir.exists();
					if(checkDB==false)
					{
					logger.debug("DataBase Does not exist");
					CreateTableDAO lCreateTableDAO=new CreateTableDAO();
					lCreateTableDAO.createTable();

					FileReading lFileReading=new FileReading();
					boolean readFile=lFileReading.readPeople();
					if(readFile==false)
					{
						JOptionPane.showMessageDialog(null, "People.txt not available!");	
						logger.debug("People.txt not available");
						
					}
					boolean readFileRelations=false;
					try
					{
						logger.debug("In MiniNet -main function");
						logger.debug("Going to read Relations.txt");

					 readFileRelations=lFileReading.readRelations();
					}
					catch(TooYoungException | NotToBeFriendsException | NotToBeColleaguesException e)
					{
						logger.debug("Exception caught in readRelations()"+e.getMessage());
						JOptionPane.showMessageDialog(null, e.getMessage());	
						
					}
					lFileReading.checkYoung();
					}
					else
					{
						logger.debug("Database exists!");

					}
					MiniNet frame = new MiniNet();
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
	public MiniNet() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 349);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("MiniNet");
		lblNewLabel.setBounds(199, 32, 80, 16);
		contentPane.add(lblNewLabel);
		
		JButton btnAddNewUser = new JButton("Add New User");
		btnAddNewUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				logger.debug("Add User Button Clicked");
				dispose();
				AddNewUser lAddNewUser=new AddNewUser();
				lAddNewUser.setVisible(true);
			}
		});
		btnAddNewUser.setBounds(29, 78, 117, 29);
		contentPane.add(btnAddNewUser);
		
		JButton btnViewProfile = new JButton("View Profile");
		btnViewProfile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				logger.debug("View Profile button clicked");
				dispose();
				ViewUser lViewUser=new ViewUser();
				lViewUser.setVisible(true);
			}
		});
		btnViewProfile.setBounds(29, 136, 117, 29);
		contentPane.add(btnViewProfile);
		
		JButton btnFindParents = new JButton("Find Parents");
		btnFindParents.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				logger.debug("Find Parents button clicked");
				dispose();
				FindParents lFindParents=new FindParents();
				lFindParents.setVisible(true);
				
			}
		});
		btnFindParents.setBounds(263, 78, 117, 29);
		contentPane.add(btnFindParents);
		
		JButton btnNewButton = new JButton("Find Children");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				logger.debug("Find Children button clicked");
				dispose();
				FindChildren lFindChildren=new FindChildren();
				lFindChildren.setVisible(true);
			}
		});
		btnNewButton.setBounds(263, 136, 117, 29);
		contentPane.add(btnNewButton);
		
		JButton btnConnectPeople = new JButton("Connect People");
		btnConnectPeople.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				logger.debug("Connect People button clicked");
				dispose();
				AddFriend lAddFriend=new AddFriend();
				lAddFriend.setVisible(true);
			}
		});
		btnConnectPeople.setBounds(29, 210, 132, 29);
		contentPane.add(btnConnectPeople);
		
		JButton btnCheckConnection = new JButton("Check Connection");
		btnCheckConnection.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				logger.debug("Check Connection button clicked");
				dispose();
				CheckConnection lCheckConnection=new CheckConnection();
				lCheckConnection.setVisible(true);
			}
		});
		btnCheckConnection.setBounds(263, 210, 117, 29);
		contentPane.add(btnCheckConnection);
		
		JButton btnNewButton_1 = new JButton("Delete User");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				logger.debug("Delete User button clicked");
				dispose();
				DeleteUser lDeleteUser=new DeleteUser();
				lDeleteUser.setVisible(true);
			}
		});
		btnNewButton_1.setBounds(29, 276, 117, 29);
		contentPane.add(btnNewButton_1);
	}
}
