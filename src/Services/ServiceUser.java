/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import entites.User;
import util.dbconnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javax.swing.JOptionPane;
import org.mindrot.jbcrypt.BCrypt;

/**
 *
 * @author Souid
 */
public class ServiceUser {
     private static ServiceUser instance;
     Connection cn = dbconnection.getInstance().getConnection();
    dbconnection cnx = dbconnection.getInstance();
    
     public static final String ACCOUNT_SID = "ACdbba9bdc1941038b9e92e29eedfa7630";
    public static final String AUTH_TOKEN = "b1a845cdb400e8472fbbc17cdbb6adff";
    
    public ServiceUser(){
        
    }
    
   public static ServiceUser getInstance(){
        if(instance == null)
            instance = new ServiceUser();
        return instance;
    }
    public ObservableList<User> getUserList(){
         ObservableList<User> UserList= FXCollections.observableArrayList();
         
         String query = "SELECT * from user";
         Statement st ;
         ResultSet rs ;
         try {
             st = cn.createStatement();
             rs=st.executeQuery(query);
             User users ;
             while(rs.next())
             {
                 users=new User( rs.getInt("id"), rs.getString("email"), rs.getString("roles"), rs.getString("username"));
                 System.out.println(rs.getString("id"));
                 UserList.add(users);
             }
         }catch(Exception ex )
         {
             ex.printStackTrace();
         }
         return UserList ;
     }
    public void addUser(User user) throws SQLException
    {
        
         
         
        String pw_hash = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
         String query = "INSERT INTO user (email,roles,password,username,blocked) VALUES (?,?,?,?,?) ";
         PreparedStatement st = cn.prepareStatement(query);
            st.setString(1, user.getEmail());
            st.setString(2, "ROLE_USER");
            st.setString(3, pw_hash);
            st.setString(4, user.getUsername());;
            st.setInt(5, 0);
            
            st.executeUpdate();
            System.out.println("User ajoutée");
        
    }
    public void editUser(User user) throws SQLException{
        String req = "UPDATE user SET "
                  + "email = ?,"
                    + "username = ?,"  
                    + " where id=?";
        
        System.out.println(req);
        PreparedStatement pre = cn.prepareStatement(req);
        pre.setString(1, user.getEmail());
        pre.setString(2, user.getUsername().toLowerCase());
 
        pre.setInt(7, pidevuser.PidevUser.user.getId());
        pre.executeUpdate();
        
    }
    
    public void editUserProfile(User user) throws SQLException{
       String req = "UPDATE user SET "
          + "email = ?,"
          + "username = ?"  
          + "WHERE id=?";
        System.out.println(req);
        PreparedStatement pre = cn.prepareStatement(req);
        pre.setString(1, user.getEmail());
        pre.setString(2, user.getUsername().toLowerCase());
                  pre.setInt(3, pidevuser.PidevUser.user.getId());

   pre.executeUpdate();
        
    }
    public void BlockUser(String email) throws SQLException{
        String req = "UPDATE user SET "
                  + "blocked = ?"        
                    + " where email=?";
        
        System.out.println(req);
        PreparedStatement pre = cn.prepareStatement(req);
        pre.setInt(1, 1);
        pre.setString(2, email);
        pre.executeUpdate();
        
             
              
       JOptionPane.showMessageDialog(null, "This account has been temporarily blocked : " + email);  
            
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        Message message = Message.creator(new PhoneNumber("+21690000518"),
        new PhoneNumber("+16812215599"), 
      " New conseil has been added" ).create();   
        
    }
    
     public void DeblockUser(String email) throws SQLException{
        String req = "UPDATE user SET "
                  + "blocked = ?"        
                    + " where email=?";
        
        System.out.println(req);
        PreparedStatement pre = cn.prepareStatement(req);
        pre.setInt(1, 0);
        pre.setString(2, email);
        pre.executeUpdate();   
       
              
        JOptionPane.showMessageDialog(null,  " This account is currently active and not blocked : " + email );  
            
            Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
                    Message message = Message.creator(new PhoneNumber("+21690000518"),
        new PhoneNumber("+16812215599"), 
       email +  " : Your account is currently active and not blocked ").create();
        
    }
      public User searchUserByEmail(String pseudo, String password) throws SQLException {
        User user = null;
      String req="SELECT (password) FROM user where (username=? OR email=?)";
      PreparedStatement st1 = cn.prepareStatement(req);
        st1.setString(1, pseudo.toLowerCase());
        st1.setString(2, pseudo.toLowerCase());
        ResultSet rs1 = st1.executeQuery();
        while (rs1.next()){
            if(BCrypt.checkpw(password,"$2a$"+rs1.getString("password").substring(4, rs1.getString("password").length()))){
                String requete = "SELECT * FROM user where (username=? OR email=?)";
                PreparedStatement st = cn.prepareStatement(requete);
                st.setString(1, pseudo.toLowerCase());
                st.setString(2, pseudo.toLowerCase());


                ResultSet rs = st.executeQuery();
                while (rs.next()) {
                    user = new User();
                    user.setId(rs.getInt("id"));
                    user.setEmail(rs.getString("email"));
                    user.setRoles(rs.getString("roles"));
                    user.setPassword(rs.getString("password"));  
                    user.setBlocked(rs.getBoolean("blocked")); 
                    user.setUsername(rs.getString("username"));
                    System.out.println("User found");

                }
            }else{
                System.out.println("User not found");
            }
        }
        
        return user;
    }
       public void UpdateUser(String email) throws SQLException{
        String req = "UPDATE user SET "
                  + "blocked = ?"        
                    + " where email=?";
        
        System.out.println(req);
        PreparedStatement pre = cn.prepareStatement(req);
        pre.setInt(1, 1);
        pre.setString(2, email);
        pre.executeUpdate();
        
    }
      
      
 

    
}
