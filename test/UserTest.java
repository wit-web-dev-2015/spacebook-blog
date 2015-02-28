import models.User;

import org.junit.BeforeClass;
import org.junit.Test;

import play.test.Fixtures;
import play.test.UnitTest;


public class UserTest extends UnitTest
{
  @BeforeClass
  public static void loadDB()
  {
    Fixtures.deleteAllModels();
  }

  @Test
  public void testCreateUser()
  {
    User bob = new User("bob", "jones", "bob@jones.com", "secret", 20, "irish");
    bob.save();
    
    User testUser = User.findByEmail("bob@jones.com");
    assertNotNull (testUser);
  }
  
  @Test
  public void testFindUser()
  {
    User jim = new User("jim", "smith", "jim@smith.com", "secret", 20, "irish");
    jim.save();
    
    User test = User.findByEmail("jim@smith.com");
    assertNotNull (test);

    User alice = User.findByEmail("alice@jones.com");
    assertNull (alice);
  } 
}
