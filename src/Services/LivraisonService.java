/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import static com.oracle.nio.BufferSecrets.instance;
import entites.Livraison;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import Services.IService;
import util.dbconnection;

/**
 *
 * @author Pc
 */
public class LivraisonService implements IService<Livraison>{
    
    Connection cnx;
     private static LivraisonService instance;
     Connection cn = dbconnection.getInstance().getConnection();
    public LivraisonService() {
        cnx = dbconnection.getInstance().getConnection();
    }
    
     public static LivraisonService getInstance(){
        if(instance == null)
            instance = new LivraisonService();
        return instance;
    }

    @Override
    public void ajouter(Livraison t) throws SQLException {
      
           String query = "INSERT INTO livraison (quantite,etat,categorie,traiter) VALUES (?,?,?,?) ";
         PreparedStatement st = cnx.prepareStatement(query);
            st.setInt(1, t.getQuantite());
            st.setString(2, t.getEtat());
            st.setString(3, t.getCategorie());
            st.setInt(4, 0);
            
            st.executeUpdate();
            System.out.println("Livraison ajoutée");
    }
    
    
    public void Traiter(String etat) throws SQLException{
        String req = "UPDATE livraison SET "
                  + "traiter = ?"        
                    + " where etat=?";
        
        System.out.println(req);
        PreparedStatement pre = cn.prepareStatement(req);
        pre.setInt(1, 1);
        pre.setString(2, etat);
        pre.executeUpdate();
        
             
              
       JOptionPane.showMessageDialog(null, "Votre Livraison a été traiter ");  
            
        
    }
    
    
   
    @Override
    public void modifier(Livraison t) throws SQLException {
         String req = "update livraison set quantite = ?, etat = ?, categorie = ? where id = ?";
        PreparedStatement ps = cnx.prepareStatement(req);

        ps.setInt(1, t.getQuantite());
        ps.setString(2, t.getEtat());
        ps.setString(3, t.getCategorie());
        ps.setInt(4, t.getId());

        ps.executeUpdate();
    }

    @Override
    public void supprimer(Livraison t) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Livraison> recuperer() throws SQLException {
       List<Livraison> livraisons = new ArrayList<>();
        String req = "select * from livraison";
        Statement st = cnx.createStatement();
        ResultSet rs = st.executeQuery(req);
        while (rs.next()) {
            Livraison l = new Livraison();
            l.setId(rs.getInt("id"));
           l.setQuantite(rs.getInt("quantite"));
            l.setEtat(rs.getString("etat"));
            l.setCategorie(rs.getString("categorie"));

            livraisons.add(l);
        }
        return livraisons;
    }
     public Livraison recupererById(int id) throws SQLException {
        String req = "select count(*) as nbr from livraison where id = ?";
        PreparedStatement st = cnx.prepareStatement(req);
        st.setInt(1, id);
        ResultSet rs = st.executeQuery();
        Livraison l = new Livraison();
        rs.next();
        l.setId(rs.getInt("id"));
        l.setQuantite(rs.getInt("quantite"));
        l.setEtat(rs.getString("etat"));
        l.setCategorie(rs.getString("categorie"));
        rs.getInt("nbr");

        return l;
    }

    
}
