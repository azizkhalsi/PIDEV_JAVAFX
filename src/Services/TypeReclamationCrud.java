/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;
import Interfaces.IType;
import entites.Reclamation;
import entites.Type_Reclamation;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import util.dbconnection;

/**
 *
 * @author WIKI
 */
public class TypeReclamationCrud implements IType<Type_Reclamation> {
    private static TypeReclamationCrud instance;
    private Statement st;
    private ResultSet rs;

    


    public TypeReclamationCrud() {
        dbconnection cs = dbconnection.getInstance();
        try {
            st = cs.getConnection().createStatement(); 
        } catch (SQLException ex) {
            Logger.getLogger(TypeReclamationCrud.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static TypeReclamationCrud getInstance() {
        if (instance == null) {
            instance = new TypeReclamationCrud();
        }
        return instance;
    }
    @Override
    public void ajouterTypeReclamation(Type_Reclamation t) {
        String req = "insert into type_reclamation"
                + " (typereclamation)"
                + " values ('" + t.getTypereclamation() + "')";

        try {
            st.executeUpdate(req);
        } catch (SQLException ex) {
            Logger.getLogger(TypeReclamationCrud.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    public void delete(Type_Reclamation t) {
        String req = "delete from type_reclamation where typereclamation ='" + t.getTypereclamation()+"'";

        if (t != null) {
            try {

                st.executeUpdate(req);

            } catch (SQLException ex) {
                Logger.getLogger(Reclamation.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            System.out.println("n'existe pas");
        }
    }

    public boolean update(Type_Reclamation t) {
         String qry = "UPDATE type_reclamation SET typereclamation = '" + t.getTypereclamation() + "' WHERE id = '" + t.getId()+"'";
        
        try {
            if (st.executeUpdate(qry) > 0) {
                return true;
            }

        } catch (SQLException ex) {
            Logger.getLogger(Type_Reclamation.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public List<Type_Reclamation> displayTypeReclamation() {
        List<Type_Reclamation> myList = new ArrayList<>();
        dbconnection cs = dbconnection.getInstance();

        try {
            String requete = "select * from type_reclamation";
            Statement st = (Statement) cs.getConnection().createStatement();
            ResultSet rs = st.executeQuery(requete);

            while (rs.next()) {
                Type_Reclamation t = new Type_Reclamation();
                 t.setId(rs.getInt("id"));
                t.setTypereclamation(rs.getString("typereclamation"));

                myList.add(t);

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return myList;
    }

}
