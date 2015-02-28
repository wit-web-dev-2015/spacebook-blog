import java.util.ArrayList;
import java.util.List;

import models.Message;
import models.Post;
import models.User;

import org.junit.BeforeClass;
import org.junit.Test;

import play.test.Fixtures;
import play.test.UnitTest;


public class BlogTest  extends UnitTest
{
  @BeforeClass
  public static void loadDB()
  {
    Fixtures.deleteAllModels();
  }
  
  @Test
  public void testCreatePost()
  {
    User bob = new User("bob", "jones", "bob@jones.com", "secret",  20, "irish");
    bob.save();
    
    Post aPost = new Post ("Post Title", "This is the post content");
    aPost.save();
    bob.posts.add(aPost);
    bob.save();
    
    User user = User.findByEmail("bob@jones.com");
    List<Post> posts = user.posts;
    assertEquals(1, posts.size());
    
    Post post = posts.get(0);
    assertEquals(post.title, "Post Title");
    assertEquals(post.content, "This is the post content");
  }  
  
  @Test
  public void testCreateMultiplePosts()
  {
    User jim = new User("jim", "jones", "jim@jones.com", "secret", 20, "irish");
    jim.save();
    
    Post post1 = new Post ("Post Title 1", "This is the first post content");
    post1.save();
    Post post2 = new Post ("Post Title 2", "This is the second post content");
    post2.save();
    jim.posts.add(post1);
    jim.posts.add(post2);
    jim.save();
    
    User user = User.findByEmail("jim@jones.com");
    List<Post> posts = user.posts;
    assertEquals(2, posts.size());   

    Post posta = posts.get(0);
    assertEquals(posta.title, "Post Title 1");
    assertEquals(posta.content, "This is the first post content");
    
    Post postb = posts.get(1);
    assertEquals(postb.title, "Post Title 2");
    assertEquals(postb.content, "This is the second post content");      
  }  
}