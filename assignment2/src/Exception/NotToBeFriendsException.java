/**
 * The NotToBeFriendsException is an exception class.
 * The class is used to throw an exception when trying to make an adult and a child friend or connect two
children with an age gap larger than 3.
 * @author  Abhinay Kathuria
 * @version 1.0
 * @since   2018-05-10
 */


package Exception;

public class NotToBeFriendsException extends Exception{

	public NotToBeFriendsException(String s)
	{
		super(s);
	}
}
