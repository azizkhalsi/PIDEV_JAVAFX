/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;


import entites.Societe;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import Services.IService;
import util.dbconnection;

/**
 *
 * @author Pc
 */
public class SocieteService implements IService<Societe>{
    
 private static SocieteService instance;
     Connection cn = dbconnection.getInstance().getConnection();
    dbconnection cnx = dbconnection.getInstance();  
  Connection cnx1;
  
   public SocieteService(){
        
    }
    
   public static SocieteService getInstance(){
        if(instance == null)
            instance = new SocieteService();
        return instance;
    }
   
    @Override
    public void ajouter(Societe t) throws SQLException {
   
        
        
         String query = "INSERT INTO Societe (nomSociete,typeSociete) VALUES (?,?) ";
         PreparedStatement st = cn.prepareStatement(query);
            st.setString(1, t.getNomSociete());
            st.setString(2, t.getTypeSociete());
        
              st.executeUpdate();
            System.out.println("Societe ajoutée");
    }
    
     public void add(Societe t) throws SQLException {
   
        
       String query = "INSERT INTO Societe (nomSociete,typeSociete) VALUES (?,?) ";
         PreparedStatement st = cn.prepareStatement(query);
            st.setString(1, t.getNomSociete());
            st.setString(2, t.getTypeSociete());
        
              st.executeUpdate();
            System.out.println("Societe ajoutée");
    }

   
    @Override
    public void supprimer(Societe t) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

  
    
     
   
    @Override
    public void modifier(Societe t) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
     
  
    public ObservableList<Societe> getLivraisonList(){
         ObservableList<Societe> LivraisonList= FXCollections.observableArrayList();
         
         String query = "SELECT * from Societe";
         Statement st ;
         ResultSet rs ;
         try {
             st = cn.createStatement();
             rs=st.executeQuery(query);
             Societe ls ;
             while(rs.next())
             {
                 ls=new Societe( rs.getInt("id"),rs.getString("nomSociete"),  rs.getString("typeSociete"));
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
    public List<Societe> recuperer() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
     public List<Societe> ListClasse() {
        List<Societe> Mylist = new ArrayList<>();
        try {
            String requete = "select * from societe";
            PreparedStatement pst = cn.prepareStatement(requete);
            ResultSet e = pst.executeQuery();
            while (e.next()) {
                Societe pre = new Societe();
              
            pre.setNomSociete(e.getString("nomSociete"));
            pre.setTypeSociete(e.getString("typeSociete"));
            


            
                Mylist.add(pre);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return Mylist;
    }
     
}
