/**
 * The TooYoungException is an exception class.
 * The class is used to throw an exception when trying to make friend with a young child.
children with an age gap larger than 3.
 * @author  Abhinay Kathuria
 * @version 1.0
 * @since   2018-05-10
 */

package Exception;

public class TooYoungException extends Exception{
	
	public TooYoungException(String s)
	{
		super(s);
	}
}
