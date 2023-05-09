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
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import pidevuser.PidevUser;

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
    
     @FXML
    private Button add;
    @FXML
    private TextField ajouttypeReclamation;
    
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
            settable();
       
        
    }

   
    @FXML
    private void AfficherReclamation()throws IOException {
        PidevUser m = new PidevUser() ;
         m.changeScene("/gui/affichageReclamation.fxml");
        
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
    
    

}
