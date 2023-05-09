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
public class Societe {
     int id ;
    String nomSociete , typeSociete;

    public Societe() {
    }

    public Societe(String nomSociete, String typeSociete) {
        this.nomSociete = nomSociete;
        this.typeSociete = typeSociete;
    }

    public Societe(int id,String nomSociete, String typeSociete) {
        this.id = id;
        this.nomSociete = nomSociete;
        this.typeSociete = typeSociete;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNomSociete(String nomSociete) {
        this.nomSociete = nomSociete;
    }

    public void setTypeSociete(String typeSociete) {
        this.typeSociete = typeSociete;
    }

    public String getNomSociete() {
        return nomSociete;
    }

    public String getTypeSociete() {
        return typeSociete;
    }

    @Override
    public String toString() {
        return "Societe{" + "id=" + id + ", nomSociete=" + nomSociete + ", typeSociete=" + typeSociete + '}';
    }

   
    
    
}
