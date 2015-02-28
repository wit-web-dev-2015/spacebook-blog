import java.util.List;
import models.Message;
import models.User;
import org.junit.BeforeClass;
import org.junit.Test;
import play.test.Fixtures;
import play.test.UnitTest;


public class MessageTest extends UnitTest
{
  @BeforeClass
  public static void loadDB()
  {
    Fixtures.deleteAllModels();
  }

  @Test
  public void testCreateMessage()
  {
    User mary = new User("mary", "colllins", "mary@collins.com", "secret", 20, "irish");
    mary.save();
    
    User joan = new User("joan", "colllins","joan@collins.com", "secret",  20, "irish");
    joan.save();
    
    Message msg = new Message (mary, joan, "Hi there - how are you");
    msg.save();
    
    List<Message> joansMessages = Message.find("byTo", joan).fetch();
    assertEquals (joansMessages.size(), 1);
  }  

  
  @Test
  public void testNoMessagese()
  {
    User mary = new User("mary", "colllins", "mary@collins.com", "secret",  20, "irish");
    mary.save();
    
    List<Message> joansMessages = Message.find("byTo", mary).fetch();
    assertEquals (joansMessages.size(), 0);
  }
  
  @Test
  public void testMultipleMessages()
  {
    User mary = new User("mary", "colllins", "mary@collins.com", "secret", 20, "irish");
    mary.save();
    
    User joan = new User("joan", "colllins", "joan@collins.com", "secret", 20, "irish");
    joan.save();
    
    Message msg1 = new Message (mary, joan, "Hi there - how are you");
    msg1.save();
    Message msg2 = new Message (mary, joan, "Where are you now?");
    msg2.save();
    
    List<Message> joansMessages = Message.find("byTo", joan).fetch();
    assertEquals (joansMessages.size(), 2);
    Message message1 = joansMessages.get(0);
    assertEquals(message1.messageText, "Hi there - how are you");
    Message message2 = joansMessages.get(1);
    assertEquals(message2.messageText, "Where are you now?");   
  }
}