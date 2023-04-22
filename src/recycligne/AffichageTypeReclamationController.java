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
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author WIKI
 */
public class AffichageTypeReclamationController implements Initializable {

    @FXML
    private TableView<Type_Reclamation> tablev;
    @FXML
    private TableColumn<Type_Reclamation, String> typereclamation;
    @FXML
    private TableColumn<Type_Reclamation, Integer> idtype;
    @FXML
    private Button edit;
    @FXML
    private Button supp;
    
    private final ObservableList<Type_Reclamation> datta = FXCollections.observableArrayList();
    @FXML
    private Button btnretour;
    
    public static String typerecup;
    public static Integer id ;
   

    /**
     * Initializes the controller class.
     */
    private void settable() {

        TypeReclamationCrud rec = new TypeReclamationCrud();

        ArrayList<Type_Reclamation> t = (ArrayList<Type_Reclamation>) rec.displayTypeReclamation();
        ObservableList<Type_Reclamation> obs = FXCollections.observableArrayList(t);
        tablev.setItems(obs);
        idtype.setCellValueFactory(new PropertyValueFactory<>("id"));
        typereclamation.setCellValueFactory(new PropertyValueFactory<>("typereclamation"));
       
        datta.addAll(t);

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        settable();


    }
    
     @FXML
    private void edit_typeReclamation(ActionEvent event) throws IOException {
       Type_Reclamation t = tablev.getSelectionModel().getSelectedItem();

        AffichageTypeReclamationController.typerecup = t.getTypereclamation();
        AffichageTypeReclamationController.id = t.getId();
       
       

        Parent root = FXMLLoader.load(getClass().getResource("editTypeReclamation.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.hide();
        stage.setScene(scene);
        stage.show();  
        
        
    }

    @FXML
    private void supp_typeReclamation(ActionEvent event) {
        
       Type_Reclamation t = tablev.getSelectionModel().getSelectedItem();
        TypeReclamationCrud type = TypeReclamationCrud.getInstance();
        type.delete(t);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Suppression Type Reclamation");
        alert.setContentText("Type Reclamation a ete supprimer avec succes!");
        alert.showAndWait();
        settable();
    }

    @FXML
    private void retour(ActionEvent event) {
           try {
            Parent root = FXMLLoader.load(getClass().getResource("menu.fxml"));
            Stage stage = (Stage) btnretour.getScene().getWindow();
            stage.close();
            Scene scene = new Scene(root);

            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(MenuController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
