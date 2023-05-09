/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;
import entites.Reclamation;
import java.sql.SQLException;
import java.util.List;
import javafx.collections.ObservableList;

/**
 *
 * @author WIKI
 */
public interface IReclamation<T> {
    public void ajouterReclamation (Reclamation r);
    public void delete(Reclamation r);
    public boolean update(Reclamation r); 
}
