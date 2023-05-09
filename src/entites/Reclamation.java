/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entites;

import java.util.Date;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author Nour
 */
public class Reclamation {

    public Reclamation(String nom, String email, String description) {
        this.nom = nom;
        this.email = email;
        this.description = description;
    }

  
    
   

    public Reclamation(int type_reclamations_id, String nom, String email, String description) {
        this.type_reclamations_id = type_reclamations_id;
        this.nom = nom;
        this.email = email;
        this.description = description;
    }
    
    private int id ;
    private int type_reclamations_id  ;
    private String nom;
    private String email;
     private String description;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getType_reclamations_id() {
        return type_reclamations_id;
    }

    public void setType_reclamations_id(int type_reclamations_id) {
        this.type_reclamations_id = type_reclamations_id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
   
     public Reclamation() {//To change body of generated methods, choose Tools | Templates.
    }
     
    public Reclamation(int id, int type_reclamations_id, String nom, String email, String description) {
        this.id = id;
        this.type_reclamations_id = type_reclamations_id;
        this.nom = nom;
        this.email = email;
        this.description = description;
    }

}
