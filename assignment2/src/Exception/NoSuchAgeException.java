/**
 * The NoSuchAgeException is an exception class.
 * The class is used to throw an exception when trying to enter a person whose age is negative or over 150. 
 * @author  Gitansh
 * @version 1.0
 * @since   2018-05-10
 */

package Exception;

public class NoSuchAgeException extends Exception{
	public NoSuchAgeException(String s)
	{
		super(s);
	}
}
