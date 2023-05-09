/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import entites.Conseil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javax.swing.JOptionPane;
import Services.IService;
import util.dbconnection;

/**
 *
 * @author Pc
 */
public class ConseilService implements IService<Conseil>{
    
 private static ConseilService instance;
   public static final String ACCOUNT_SID = "AC2bbf820bd581671f90314d787404370e";
    public static final String AUTH_TOKEN = "ca732ce8296518ef7c6914e54a0cbf70";
    private PreparedStatement ste ;
     Connection cn = dbconnection.getInstance().getConnection();
    dbconnection cnx = dbconnection.getInstance();  
  Connection cnx1;
  
   public ConseilService(){
        
    }
    
   public static ConseilService getInstance(){
        if(instance == null)
            instance = new ConseilService();
        return instance;
    }
   
    @Override
    public void ajouter(Conseil t) throws SQLException {
   
        
        
         String query = "INSERT INTO conseil (description,typecon) VALUES (?,?) ";
         PreparedStatement st = cn.prepareStatement(query);
            st.setString(1, t.getDescription());
            st.setString(2, t.getTypecon());
        
              st.executeUpdate();
            System.out.println("Conseil ajoutée");
            
             JOptionPane.showMessageDialog(null, "Votre conseil a bien éte ajouter et sera traiter par nous adminstrateur !");  
            
            Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
                    Message message = Message.creator(new PhoneNumber("+21698711711"),
        new PhoneNumber("+16813346950"), 
       "Votre conseil a bien éte ajouter et sera traiter par nous adminstrateur").create();   
    }
    
 

   
    @Override
    public void supprimer(Conseil t) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

  
    
     
   
    @Override
    public void modifier(Conseil t) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
     
  
    public ObservableList<Conseil> getLivraisonList(){
         ObservableList<Conseil> LivraisonList= FXCollections.observableArrayList();
         
         String query = "SELECT * from conseil";
         Statement st ;
         ResultSet rs ;
         try {
             st = cn.createStatement();
             rs=st.executeQuery(query);
             Conseil ls ;
             while(rs.next())
             {
                 ls=new Conseil( rs.getInt("id"),rs.getString("description"),  rs.getString("typecon"));
                 System.out.println(rs.getInt("id"));
                 LivraisonList.add(ls);
             }
         }catch(Exception ex )
         {
             ex.printStackTrace();
         }
         return LivraisonList ;
     }

    @Override
    public List<Conseil> recuperer() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
     public List<Conseil> ListClasse() {
        List<Conseil> Mylist = new ArrayList<>();
        try {
            String requete = "select * from conseil";
            PreparedStatement pst = cn.prepareStatement(requete);
            ResultSet e = pst.executeQuery();
            while (e.next()) {
                Conseil pre = new Conseil();
              
            pre.setDescription(e.getString("description"));
            pre.setTypecon(e.getString("typecon"));



            
                Mylist.add(pre);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return Mylist;
    }
}
