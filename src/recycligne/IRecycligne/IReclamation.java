/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package recycligne.IRecycligne;
import java.sql.SQLException;
import java.util.List;
import javafx.collections.ObservableList;
import Recyclage.entities.Reclamation;

/**
 *
 * @author WIKI
 */
public interface IReclamation<T> {
    public void ajouterReclamation (Reclamation r);
    public void delete(Reclamation r);
    public boolean update(Reclamation r); 
}
