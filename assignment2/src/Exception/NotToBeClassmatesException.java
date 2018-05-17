/**
 * The NotToBeClassmatesException is an exception class.
 * The class is used to throw an exception  when trying to make a young child in a classmate relation. 
 * @author  Gitansh
 * @version 1.0
 * @since   2018-05-10
 */

package Exception;

public class NotToBeClassmatesException extends Exception{
	NotToBeClassmatesException(String s)
	{
		super(s);
	}
}
