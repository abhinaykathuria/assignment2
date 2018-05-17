package UnitTest;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ AddUser.class, DeleteUser.class, SearchUser.class , UserIdExists.class,SearchParent.class,Delete.class})
public class AllTests {

}


