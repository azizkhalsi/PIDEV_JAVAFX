/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;
import entites.Type_Reclamation;
import java.sql.SQLException;
import java.util.List;
import javafx.collections.ObservableList;

/**
 *
 * @author WIKI
 */
public interface IType<T> {
    public void ajouterTypeReclamation (Type_Reclamation t);
    public void delete(Type_Reclamation t);
    public boolean update(Type_Reclamation t); 
}
