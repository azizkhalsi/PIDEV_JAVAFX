/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package recycligne;

import Recyclage.entities.Reclamation;
import Recyclage.entities.Type_Reclamation;
import crud.ReclamationCrud;
import crud.TypeReclamationCrud;
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
public class EditTypeReclamationController implements Initializable {

    @FXML
    private Button btnedit;
    @FXML
    private Button btnret;
    @FXML
    private TextField typeedit;
   
    
    String type_recup = AffichageTypeReclamationController.typerecup;
    Integer id_recup = AffichageTypeReclamationController.id;
    

    void cleardata() {
        typeedit.clear();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
          typeedit.setText(type_recup);
        
    }

    @FXML
    private void edit(ActionEvent event) {
        String typeReclamation = typeedit.getText();
        Integer id = id_recup;

        Type_Reclamation t = new Type_Reclamation(id,typeReclamation);
        TypeReclamationCrud typeCrud = TypeReclamationCrud.getInstance();
        typeCrud.update(t);

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Modification Type Reclamation");
        alert.setContentText("Type Reclamation a ete modifier avec succes!");
        alert.showAndWait();
        cleardata();
        try {
            Parent root = FXMLLoader.load(getClass().getResource("affichageTypeReclamation.fxml"));
            Stage stage = (Stage) btnret.getScene().getWindow();
            stage.close();
            Scene scene = new Scene(root);

            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(EditTypeReclamationController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    @FXML
    private void retour(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("affichageTypeReclamation.fxml"));
            Stage stage = (Stage) btnret.getScene().getWindow();
            stage.close();
            Scene scene = new Scene(root);

            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(EditTypeReclamationController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
