/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;


import Services.ReclamationCrud;
import entites.Reclamation;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
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
import org.controlsfx.control.Notifications;
import pidevuser.PidevUser;

/**
 * FXML Controller class
 *
 * @author WIKI
 */
public class AffichageReclamationController implements Initializable {

    @FXML
    private TableView<Reclamation> tablev;
    @FXML
    private TableView<String> tablev1;
    @FXML
    private TableColumn<Reclamation, String> nom;
    @FXML
    private TableColumn<Reclamation, String> email;
    @FXML
    private TableColumn<Reclamation, String> description;
    /*@FXML
    private TableColumn <Reclamation,String> type;*/
    @FXML
    private TextField rech;
    @FXML
    private Button edit;
    @FXML
    private Button supp;
    
    private final ObservableList<Reclamation> datta = FXCollections.observableArrayList();
    private final ObservableList<String> dattatype = FXCollections.observableArrayList();
    @FXML
    private Button btnretour;
    
    public static String nomrecup;
    public static String emailrecup;
    public static String descriptionrecup;
    

    /**
     * Initializes the controller class.
     */
    private void settable() {

        ReclamationCrud rec = new ReclamationCrud();
        ArrayList<Reclamation> r = (ArrayList<Reclamation>) rec.displayReclamation();
        ObservableList<Reclamation> obs = FXCollections.observableArrayList(r);

       description.setCellValueFactory(new PropertyValueFactory<>("description"));
       //type.setCellValueFactory(new PropertyValueFactory<>("type_reclamations_id"));
        tablev.setItems(obs);
        nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        email.setCellValueFactory(new PropertyValueFactory<>("email"));
        
        datta.addAll(r);
        

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        settable();
        FilteredList<Reclamation> filteredData = new FilteredList<>(datta, b -> true);

        // 2. Set the filter Predicate whenever the filter changes.
        rech.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(Rec -> {
                // If filter text is empty, display all persons.

                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                // Compare first name and last name of every person with filter text.
                String lowerCaseFilter = newValue.toLowerCase();

                if (Rec.getEmail().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches first name.
                } else {
                    return false; // Does not match.
                }
            });
        });

        // 3. Wrap the FilteredList in a SortedList. 
        SortedList<Reclamation> sortedData = new SortedList<>(filteredData);

        // 4. Bind the SortedList comparator to the TableView comparator.
        // 	  Otherwise, sorting the TableView would have no effect.
        sortedData.comparatorProperty().bind(tablev.comparatorProperty());

        // 5. Add sorted (and filtered) data to the table.
        tablev.setItems(sortedData);


    }
    
     @FXML
    private void editReclamation(ActionEvent event) throws IOException {
       Reclamation r = tablev.getSelectionModel().getSelectedItem();

        AffichageReclamationController.nomrecup = r.getNom();
        AffichageReclamationController.emailrecup = r.getEmail();
        AffichageReclamationController.descriptionrecup = r.getDescription();
       

        Parent root = FXMLLoader.load(getClass().getResource("/gui/editReclamation.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.hide();
        stage.setScene(scene);
        stage.show();  
        
        
    }

    @FXML
    private void suppReclamation(ActionEvent event) {
        
       Reclamation r = tablev.getSelectionModel().getSelectedItem();
        ReclamationCrud udao = ReclamationCrud.getInstance();
        udao.delete(r);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Suppression Reclamation");
        alert.setContentText("Reclamation a ete supprimer avec succes!");
        alert.showAndWait();
        Notifications.create().title("Suppression Reclamation").text("Reclamation a ete supprimé avec succes!").showConfirm();

        settable();
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
    private void AfficherReclamation()throws IOException {
        PidevUser m = new PidevUser() ;
         m.changeScene("/gui/affichageReclamation.fxml");
        
    }
     @FXML
    private void AfficherTypeReclamation()throws IOException {
        PidevUser m = new PidevUser() ;
         m.changeScene("/gui/affichageTypeReclamation.fxml");
        
    }

}
