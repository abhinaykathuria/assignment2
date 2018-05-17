/**
 * The FileReading reads data from file and stores it in the database.
 * @author  Abhinay Kathuria
 * @version 1.0
 * @since   2018-05-10
 */

package helper;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import Exception.NoSuchAgeException;
import Exception.NotToBeColleaguesException;
import Exception.NotToBeFriendsException;
import Exception.TooYoungException;
import controller.AddNewUserController;
import controller.CheckChildController;
import controller.MakeNewConnectionController;
import model.Adult;
import model.User;

public class FileReading {

	private static Logger logger = Logger.getLogger(FileReading.class);

	/**
	 * This method is used to read the people.txt file and populate the database.
	 * @author  Abhinay Kathuria
	 */
	public  boolean readPeople()
	{
		logger.debug("In FileReading class- readPeople() function");

		try {
			BufferedReader in = new BufferedReader(new FileReader("people.txt"));

			String line = null;
			while ((line = in.readLine()) != null) {
				String[] values = line.split(",");
				User adult=new Adult();
				int count=1;
				for (String str : values) {

					str=str.trim();

					if(count==1)
					{
						if(str!=null)
							adult.setName(str);
						else
						{
							logger.debug("Record can not be added!Missing Name!");
							continue;
						}
					}

					else if(count==2)
					{
						str=str.substring(1, str.length()-1);
						if(str.trim().length()>0)
							adult.setDisplay_picture(str);

					}
					else if(count==3)
					{
						str=str.substring(1, str.length()-1);
						if(str.trim().length()>0)
							adult.setStatus(str);

					}
					else if(count==4)
					{
						if(!str.equals("“”"))
						{
							if(str.charAt(0) == 34 && str.charAt(str.length()-1) == 34)
								str=str.substring(1, str.length()-1);
							adult.setGender(str);
						}
						else
						{
							System.out.println("Record can not be added!Missing information!");
							continue;
						}

					}
					else if(count==5)
					{

						if(!str.equals("“”"))
						{

							if(str.charAt(0) == 34 && str.charAt(str.length()-1) == 34)
								str=str.substring(1, str.length()-1);
							adult.setAge(Integer.parseInt(str));
						}
						else
						{
							System.out.println("Record can not be added!Missing information!");
							logger.debug("Record can not be added!Missing Age!");

							continue;
						}

					}
					else if(count==6)
					{
						if(!str.equals("“”"))
						{
							if(str.charAt(0) == 34 && str.charAt(str.length()-1) == 34)
								str=str.substring(1, str.length()-1);
							adult.setState(str);
						}
						else
						{
							System.out.println("Record can not be added!Missing information!");
							logger.debug("Record can not be added!Missing State!");

							continue;
						}
					}
					count++;

				}


				AddNewUserController lAddNewUserContoller=new AddNewUserController();
				lAddNewUserContoller.createAdult(adult);
			}
			in.close();
		} catch (IOException e) {
			logger.error("IOException:File people.txt doesn't exist!");
			System.out.println("File Read Error");
			return false;
		} catch (NoSuchAgeException e) {
			// TODO Auto-generated catch block
			System.out.println("Record can not be added!Wrong Age");
			logger.error("NoSuchAgeException: Record can not be added!Wrong Age");

		}
		return true;
	}

	/**
	 * This method is used to read the relation.txt file and populate the database.
	 * @author  Abhinay Kathuria
	 */
	public  boolean readRelations() throws TooYoungException, NotToBeFriendsException, NotToBeColleaguesException
	{
		logger.debug("In FileReading -readRelations() function");
		try {
			BufferedReader in = new BufferedReader(new FileReader("relations.txt"));
			logger.debug("relations.txt found");
			String line = null;
			while ((line = in.readLine()) != null) {
				String[] values = line.split(",");
				int count=1;
				String name = null;
				String name1 = null;
				String relation = null;
				for (String str : values) {
					str=str.trim();
					if(count==1)
					{
						name=str;
					}
					else if(count==2)
					{
						name1=str;
					}
					else if(count==3)
					{
						relation=str;
					}
					count++;
				}
				logger.debug("Calling create function with following values");
				logger.debug("User 1: "+name);
				logger.debug("User 2: "+name1);
				logger.debug("Relation: "+relation);

				MakeNewConnectionController lMakeNewConnectionController=new MakeNewConnectionController();
				lMakeNewConnectionController.createConnection(name, name1, relation);

			}
			in.close();
		} catch (IOException e) {
			System.out.println("File Read Error");
		} 
		return true;


	}

	/**
	 * This method is used to check Child and Young child records whose parent information might not be available in the relation file.
	 * If exists, delete those record from the database
	 * @author  Abhinay Kathuria
	 */
	public void checkYoung()
	{
		CheckChildController lCheckChildController=new CheckChildController();
		lCheckChildController.checkYoung();
	}

	/*public static void main(String args[]) throws IOException
	{
		FileReading lFileReading=new FileReading();
		CreateTableDAO lCreateTableDAO=new CreateTableDAO();
		lCreateTableDAO.createTable();
		lFileReading.readPeople();
		try {

			lFileReading.readRelations();

		} catch (TooYoungException | NotToBeFriendsException | NotToBeColleaguesException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		lFileReading.checkYoung();
	}*/
}
