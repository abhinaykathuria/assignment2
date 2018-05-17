/**
 * The NotToBeColleaguesException is an exception class.
 * The class is used to throw an exception when trying to make a couple when at least one member is not an adult.
 * @author  Abhinay Kathuria
 * @version 1.0
 * @since   2018-05-10
 */

package Exception;

public class NotToBeCoupledException extends Exception{
	public NotToBeCoupledException(String s)
	{
		super(s);
	}
}
