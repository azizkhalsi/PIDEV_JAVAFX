/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entites.Societe;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import Services.SocieteService;
import pidevuser.PidevUser;

/**
 * FXML Controller class
 *
 * @author Pc
 */
public class AjouterSocieteController implements Initializable {

    @FXML
    private TextField nomSocieteTF;
    @FXML
    private TextField typeSocieteTF;
   

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
        @FXML
    private void AjouterSociete1(ActionEvent event) {
        String nomSociete = nomSocieteTF.getText();
        String typeSociete= typeSocieteTF.getText();
        Societe l = new Societe(nomSociete, typeSociete);
        SocieteService ls = new SocieteService();
        
          if(ValidateEmptyForm(nomSocieteTF,typeSocieteTF)
            && ValidateDescription(nomSocieteTF) && ValidateTypecon(typeSocieteTF))
        {
        try {
            ls.ajouter(l);
             Alert alert = new Alert(Alert.AlertType.WARNING);
             alert.setTitle("Success");
             alert.setHeaderText(null);
             alert.setContentText("Votre Societe a été Ajouter avec sucess");
             alert.showAndWait();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        }
    }
  
    
    
    private boolean ValidateEmptyForm(TextField nomSociete, TextField typeSociete){
         if (nomSociete.getText().equals("")  || typeSociete.getText().equals("") ){
             Alert alert = new Alert(Alert.AlertType.WARNING);
             alert.setTitle("Erreur");
             alert.setHeaderText(null);
             alert.setContentText("Veuillez remplir tous les champs");
             alert.showAndWait();
             
             return false;  
        } else {
             return true;  
         }
     }
    
    private boolean ValidateDescription(TextField t){
         Pattern p = Pattern.compile("[a-zA-Z]+");
         Matcher m = p.matcher(t.getText());
         if (m.find() && m.group().equals(t.getText())){
             return true;
             
         }else{
             Alert alert = new Alert(Alert.AlertType.WARNING);
             alert.setTitle("Erreur");
             alert.setHeaderText(null);
             alert.setContentText(t.getText()+" : nom Societe non valide , votre nom Societe doit contenir seulement que des lettres");
             alert.showAndWait();
             
             return false;
         }
     }
    
     private boolean ValidateTypecon(TextField t){
         Pattern p = Pattern.compile("[a-zA-Z]+");
         Matcher m = p.matcher(t.getText());
         if (m.find() && m.group().equals(t.getText())){
             return true;
             
         }else{
             Alert alert = new Alert(Alert.AlertType.WARNING);
             alert.setTitle("Erreur");
             alert.setHeaderText(null);
             alert.setContentText(t.getText()+" : type Societe non valide , votre type Societe doit contenir seulement que des lettres");
             alert.showAndWait();
             
             return false;
         }
     }
     
     @FXML
    private void logout() throws IOException {  
        PidevUser m = new PidevUser() ;
       
        m.changeScene("/gui/login.fxml");
        
    }
    
      @FXML
    private void AfficherClient() throws IOException {
        PidevUser m = new PidevUser();
        m.changeScene("/gui/LoggedIn.fxml");
    }
    
     @FXML
    private void AfficherSociete()throws IOException {
        PidevUser m = new PidevUser() ;
         m.changeScene("/gui/AfficherSociete.fxml");
        
    }
    
      @FXML
    private void AfficherConseil()throws IOException {
        PidevUser m = new PidevUser() ;
         m.changeScene("/gui/AfficherConseil.fxml");
        
    }
    
      @FXML
    private void AfficherLivraison()throws IOException {
        PidevUser m = new PidevUser() ;
         m.changeScene("/gui/AfficherLivraison.fxml");
        
    }
     @FXML
    private void ajouterReclamation() throws IOException {
        PidevUser m = new PidevUser();
        m.changeScene("/gui/ajoutReclamation.fxml");
    }
}
