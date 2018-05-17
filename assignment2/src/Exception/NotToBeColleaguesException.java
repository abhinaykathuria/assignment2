/**
 * The NotToBeColleaguesException is an exception class.
 * The class is used to throw an exception when trying to connect a child in a colleague relation. 
 * @author  Abhinay Kathuria
 * @version 1.0
 * @since   2018-05-10
 */

package Exception;

public class NotToBeColleaguesException extends Exception{
	public NotToBeColleaguesException(String s)
	{
		super(s);
	}
}
