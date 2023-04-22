/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crud;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import recycligneCnxDB.cnx;
import Recyclage.entities.Reclamation;
import Recyclage.entities.Type_Reclamation;
import recycligne.AffichageReclamationController;
import recycligne.IRecycligne.IReclamation;

/**
 *
 * @author WIKI
 */
public class ReclamationCrud implements IReclamation<Reclamation> {
    private static ReclamationCrud instance;
    private Statement st;
    private ResultSet rs;

    public ReclamationCrud() {
        cnx cs = cnx.getInstance();
        try {
            st = cs.getCnx().createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(ReclamationCrud.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static ReclamationCrud getInstance() {
        if (instance == null) {
            instance = new ReclamationCrud();
        }
        return instance;
    }
    @Override
    public void ajouterReclamation(Reclamation r) {
        String req = "insert into reclamation"
                + " (type_reclamations_id,nom,email,description)"
                + " values ('" + r.getType_reclamations_id()+ "','" + r.getNom()+ "','" + r.getEmail() + "','" + r.getDescription()  + "')";

        try {
            st.executeUpdate(req);
        } catch (SQLException ex) {
            Logger.getLogger(ReclamationCrud.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    public void delete(Reclamation r) {
        String req = "delete from reclamation where email ='" + r.getEmail()+"'";

        if (r != null) {
            try {

                st.executeUpdate(req);

            } catch (SQLException ex) {
                Logger.getLogger(Reclamation.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            System.out.println("n'existe pas");
        }
    }

    public boolean update(Reclamation r) {
         String qry = "UPDATE reclamation SET nom = '" + r.getNom() + "', email = '" + r.getEmail() + 
                 "', description = '" + r.getDescription() + "' WHERE email = '" + r.getEmail()+"'";
        
        try {
            if (st.executeUpdate(qry) > 0) {
                return true;
            }

        } catch (SQLException ex) {
            Logger.getLogger(Reclamation.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
 
    public List<Reclamation> displayReclamation() {
        List<Reclamation> myList = new ArrayList<>();
        cnx cs = cnx.getInstance();

        try {
            String requete = "select * from reclamation";
            Statement st = (Statement) cs.getCnx().createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
                Reclamation r = new Reclamation();
                //r.setType_reclamations_id(rs.getInt("type_reclamations_id"));
                r.setNom(rs.getString("nom"));
                r.setEmail(rs.getString("email"));
              
                r.setDescription(rs.getString("description"));              
                myList.add(r);

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return myList;
    }

}
