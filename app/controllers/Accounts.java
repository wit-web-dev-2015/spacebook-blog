package controllers;

import play.*;
import play.mvc.*;
import java.util.*;
import models.*;

public class Accounts extends Controller
{
  public static void signup()
  {
    render();
  }

  public static void login()
  {
    render();
  }

  public static void logout()
  {
    session.clear();
    index();
  }
  
  public static void index()
  {
    render();
  }
  
  public static void register(String firstName, String lastName, String email, String password)
  {
    Logger.info(firstName + " " + lastName + " " + email + " " + password);
    
    User user = new User (firstName, lastName, email, password);
    user.save();
    
    
    index();
  }

  public static void authenticate(String email, String password)
  { 
    Logger.info("Attempting to authenticate with " + email + ":" +  password);
    
    User user = User.findByEmail(email);
    if ((user != null) && (user.checkPassword(password) == true))
    {
      Logger.info("Authentication successful");
      session.put("logged_in_userid", user.id);
      Home.index();
    }
    else
    {
      Logger.info("Authentication failed");
      login();  
    }
  }
}