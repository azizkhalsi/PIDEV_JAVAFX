/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entites;

/**
 *
 * @author Pc
 */
public class Conseil {
     int id ;
    String description , typecon;

    public Conseil() {
    }

    public Conseil(String description, String typecon) {
        this.description = description;
        this.typecon = typecon;
    }

    public Conseil(int id,String description, String typecon) {
        this.id = id;
        this.description = description;
        this.typecon = typecon;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setTypecon(String typecon) {
        this.typecon = typecon;
    }

    public String getDescription() {
        return description;
    }

    public String getTypecon() {
        return typecon;
    }

    @Override
    public String toString() {
        return "Livraison{" + "id=" + id + ", description=" + description + ", typecon=" + typecon + '}';
    }

   
    
    
}
