/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import Services.TypeReclamationCrud;
import entites.Type_Reclamation;
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
import pidevuser.PidevUser;

/**
 * FXML Controller class
 *
 * @author WIKI
 */
public class EditTypeReclamationController implements Initializable {

    @FXML
    private Button btnedit;
  
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
    private void edit(ActionEvent event) throws IOException {
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
        PidevUser m = new PidevUser() ;
         m.changeScene("/gui/affichageTypeReclamation.fxml");
        
    }
    
    @FXML
    private void back(ActionEvent event) throws IOException {
       PidevUser m = new PidevUser() ;
         m.changeScene("/gui/affichageTypeReclamation.fxml");
        
    }

   

}
