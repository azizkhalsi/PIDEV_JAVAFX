/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package recycligne;

import Recyclage.entities.Type_Reclamation;
import crud.TypeReclamationCrud;
import java.io.IOException;
import java.net.URL;
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
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author WIKI
 */
public class AjoutTypeReclamationController implements Initializable {


    
    @FXML
    private Button add;
    @FXML
    private TextField ajouttypeReclamation;
    @FXML
    private Button btnret;

    /**
     * Initializes the controller class.
     */
    public void initialize(URL url, ResourceBundle rb) {
       
       
    }

    @FXML
    private void ajout(ActionEvent event) throws Exception {
        String TypeReclamation = ajouttypeReclamation.getText();
    
       
            Type_Reclamation r = new Type_Reclamation(TypeReclamation);

            TypeReclamationCrud typeCrud = TypeReclamationCrud.getInstance();
            typeCrud.ajouterTypeReclamation(r);
           
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Ajout Type Reclamation");
            alert.setContentText("Type Reclamation a ete ajouté avec succes!");
            alert.showAndWait();
            Parent root = FXMLLoader.load(getClass().getResource("affichageTypeReclamation.fxml"));
            Stage stage = (Stage) add.getScene().getWindow();
            stage.close();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
       
        
    }

    @FXML
    private void retour(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("menu.fxml"));
            Stage stage = (Stage) btnret.getScene().getWindow();
            stage.close();
            Scene scene = new Scene(root);

            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(AjoutReclamationController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
