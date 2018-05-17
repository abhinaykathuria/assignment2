/**
 * The NoAvailableException is an exception class.
 * The class is used to throw an exception when trying to make two adults a couple and at least one of them isalready connected with another adult as a couple.
 * @author  Gitansh
 * @version 1.0
 * @since   2018-05-10
 */

package Exception;

public class NoAvailableException extends Exception{
	public NoAvailableException(String s)
	{
		super(s);
	}
}
