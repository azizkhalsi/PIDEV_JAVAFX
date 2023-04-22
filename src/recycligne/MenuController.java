/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package recycligne;

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
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author WIKI
 */
public class MenuController implements Initializable {

    @FXML
    private Button add;
    @FXML
    private Button btnaffich;
    @FXML
    private Button btnaffich_type;
    @FXML
    private Button btnajouttype;
   

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void btnadd(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("ajoutReclamation.fxml"));
            Stage stage = (Stage) add.getScene().getWindow();
            stage.close();
            Scene scene = new Scene(root);

            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(MenuController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void affich(ActionEvent event) {
         try {
            Parent root = FXMLLoader.load(getClass().getResource("affichageReclamation.fxml"));
            Stage stage = (Stage) btnaffich.getScene().getWindow();
            stage.close();
            Scene scene = new Scene(root);

            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(MenuController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
   
    @FXML
    private void affichtype(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("affichageTypeReclamation.fxml"));
            Stage stage = (Stage) add.getScene().getWindow();
            stage.close();
            Scene scene = new Scene(root);

            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(MenuController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @FXML
    private void ajouttype (ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("ajoutTypeReclamation.fxml"));
            Stage stage = (Stage) add.getScene().getWindow();
            stage.close();
            Scene scene = new Scene(root);

            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(MenuController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
