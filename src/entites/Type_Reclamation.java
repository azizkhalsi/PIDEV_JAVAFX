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
public class Type_Reclamation {

    public Type_Reclamation() {
    }

    private int id;
    private String typereclamation;

    public Type_Reclamation(String typereclamation) {
        this.typereclamation = typereclamation;
    }

    public Type_Reclamation(int id, String typereclamation) {
        this.id = id;
        this.typereclamation = typereclamation;
    }
    
     public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTypereclamation() {
        return typereclamation;
    }

    public void setTypereclamation(String typereclamation) {
        this.typereclamation = typereclamation;
    }
}

