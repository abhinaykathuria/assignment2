/**
 * The NoParentException is an exception class.
 * The class is used to throw an exception when a child or young child has no parent or has only one parent, e.g.
adding a child to one adult, or to two adults who are not a couple
 * @author  Gitansh
 * @version 1.0
 * @since   2018-05-10
 */

package Exception;

public class NoParentException extends Exception{
	public NoParentException(String s)
	{
		super(s);
	}
}
