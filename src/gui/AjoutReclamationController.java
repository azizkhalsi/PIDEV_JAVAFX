/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;


import Services.ReclamationCrud;
import Services.TypeReclamationCrud;
import entites.Reclamation;
import entites.Type_Reclamation;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import pidevuser.PidevUser;

/**
 * FXML Controller class
 *
 * @author WIKI
 */
public class AjoutReclamationController implements Initializable {

    @FXML
    private Button add;
    @FXML
    private TextField nomReclamation;
    @FXML
    private TextField emailReclamation;
    @FXML
    private TextArea descriptionReclamation;
    @FXML
    private ChoiceBox <String> typeReclamation;
    @FXML
    private Button btnret;
    
    private Integer typeId;

    /**
     * Initializes the controller class.
     */
    public void initialize(URL url, ResourceBundle rb) {
         TypeReclamationCrud rec = new TypeReclamationCrud();
        ArrayList<Type_Reclamation> t = (ArrayList<Type_Reclamation>) rec.displayTypeReclamation();
        ObservableList<Type_Reclamation> obs = FXCollections.observableArrayList(t);
        obs.forEach((i) -> {
    typeReclamation.getItems().add(i.getTypereclamation());
    typeId = i.getId();
    
    });
       
    }

    @FXML
    private void ajout(ActionEvent event) throws Exception {
        String NomReclamation = nomReclamation.getText();
        String Description = descriptionReclamation.getText();
        String EmailDescription = emailReclamation.getText();
        String TypeReclamation = typeReclamation.getSelectionModel().getSelectedItem();
       
            Reclamation r = new Reclamation(typeId, NomReclamation, EmailDescription, Description);

            ReclamationCrud udao = ReclamationCrud.getInstance();
            udao.ajouterReclamation(r);
           
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Ajout Reclamation");
            alert.setContentText("Reclamation a ete ajouté avec succes!");
            alert.showAndWait();
            try {
                sendmail.sendmail(emailReclamation.getText());
            }catch(Exception e){
               
            alert.setTitle("Erreur mail");
            alert.setContentText("mail introuvable");
            alert.showAndWait();
            }
            
            Parent root = FXMLLoader.load(getClass().getResource("LoggedInClient.fxml"));
            Stage stage = (Stage) add.getScene().getWindow();
            stage.close();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
       
        
    }

    @FXML
    private void retour(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("LoggedInClient.fxml"));
            Stage stage = (Stage) btnret.getScene().getWindow();
            stage.close();
            Scene scene = new Scene(root);

            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(AjoutReclamationController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
      @FXML
    private void AfficherLivraison() throws IOException {
        PidevUser m = new PidevUser();
        m.changeScene("/gui/AfficherLivraison.fxml");
    }
    
       @FXML
    private void logout() throws IOException {  
        PidevUser m = new PidevUser() ;
       
        m.changeScene("/gui/login.fxml");
        
    }
    @FXML
    private void toupdatescene()throws IOException {
        PidevUser m = new PidevUser() ;
         m.changeScene("/gui/editprofile.fxml");
        
    }
    
     @FXML
    private void AjouterSociete() throws IOException {
        PidevUser m = new PidevUser();
        m.changeScene("/gui/AfficherSocieteClient.fxml");
    }
    
      @FXML
    private void AjouterConseil() throws IOException {
        PidevUser m = new PidevUser();
        m.changeScene("/gui/AjouterConseil.fxml");
    }
    
      @FXML
    private void AjouterLivraison() throws IOException {
        PidevUser m = new PidevUser();
        m.changeScene("/gui/AjouterLivraison.fxml");
    }
    
    @FXML
    private void ChatBot() throws IOException {
        PidevUser m = new PidevUser();
        m.changeScene("/gui/ChatBot.fxml");
    }

}
