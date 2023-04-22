/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package recycligneCnxDB;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author WIKI
 */
public class cnx {
    private String url="jdbc:mysql://localhost:3306/dev";
    private String login="root";
    private String pwd="";
    private Connection cnx;
    private static cnx instance;

    public Connection getCnx() {
        return cnx;
    }
    
    
    private cnx() {
        try {
            cnx=DriverManager.getConnection(url, login, pwd);
        } catch (SQLException ex) {
            Logger.getLogger(cnx.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
   public static cnx getInstance(){
       
       if(instance==null)
           instance=new cnx();
       return instance;
   }
    
}
