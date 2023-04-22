/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package recycligne;

import Recyclage.entities.Reclamation;
import crud.ReclamationCrud;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author WIKI
 */
public class EditReclamationController implements Initializable {

    @FXML
    private Button btnedit;
    @FXML
    private Button btnret;
    @FXML
    private TextField nomedit;
    @FXML
    private TextField emailedit;
    @FXML
    private TextArea descriptionedit;
    
    String nom_recup = AffichageReclamationController.nomrecup;
    String email_recup = AffichageReclamationController.emailrecup;
    String description_recup = AffichageReclamationController.descriptionrecup;
    

    void cleardata() {
        nomedit.clear();
        emailedit.clear();
        descriptionedit.clear();
       
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
          nomedit.setText(nom_recup);
        emailedit.setText(email_recup);
        descriptionedit.setText(description_recup);
        
    }

    @FXML
    private void edit(ActionEvent event) {
        String Nom = nomedit.getText();
        String Email = emailedit.getText();
        String Description = descriptionedit.getText();

        Reclamation r = new Reclamation(Nom, Email, Description);
        ReclamationCrud udao = ReclamationCrud.getInstance();
        udao.update(r);

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Modification Reclamation");
        alert.setContentText("Reclamation a ete modifier avec succes!");

        alert.showAndWait();
        try {
            Parent root = FXMLLoader.load(getClass().getResource("AffichageReclamation.fxml"));
            Stage stage = (Stage) btnret.getScene().getWindow();
            stage.close();
            Scene scene = new Scene(root);

            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(EditReclamationController.class.getName()).log(Level.SEVERE, null, ex);
        }
        cleardata();
    }

    @FXML
    private void retour(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("AffichageReclamation.fxml"));
            Stage stage = (Stage) btnret.getScene().getWindow();
            stage.close();
            Scene scene = new Scene(root);

            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(EditReclamationController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
