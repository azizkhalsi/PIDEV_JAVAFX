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
public class Livraison {
    private int id, quantite ;
   private String etat , categorie;
    Boolean traiter ;

    
    public Boolean getTraiter() {
        return traiter;
    }

    public void setTraiter(Boolean traiter) {
        this.traiter = traiter;
    }


    public Livraison() {
    }

    public Livraison(int quantite, String etat, String categorie) {
        this.quantite = quantite;
        this.etat = etat;
        this.categorie = categorie;
    }

    public Livraison(int id, int quantite, String etat, String categorie) {
        this.id = id;
        this.quantite = quantite;
        this.etat = etat;
        this.categorie = categorie;
    }
    
 

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    @Override
    public String toString() {
        return "Livraison {" + "id=" + id + ", quantite=" + quantite + ", etat=" + etat + ", categorie=" + categorie + '}';
    }
    
    
}
