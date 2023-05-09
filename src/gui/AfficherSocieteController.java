/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;
import entites.Societe;
import pidevuser.PidevUser;
import util.dbconnection;



import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;


/**
 * FXML Controller class
 *
 * @author Pc
 */
public class AfficherSocieteController implements Initializable {
  
        @FXML
    private TableView<Societe> tableview;
         @FXML
    private TextField Recherche_Societe;
     
     @FXML
    private TableColumn<Societe, Integer> id;
    @FXML
    private TableColumn<Societe, String> nomSociete;
    @FXML
    private TableColumn<Societe, String> typeSociete;
   

    @FXML
    private TextField ids;
    @FXML
    private TextField nomSocietes;
     @FXML
    private TextField typeSocietes;

    
    @FXML
    private Button Ajouter;
     @FXML
    private Button refresh;
    @FXML
    private Button modifier;
    @FXML
    private Button supprimer;
    
    
    private Connection cnx;
    private Statement statement;
    private PreparedStatement prepare;
    private ResultSet result;
    
      int index = -1;
      
      
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //showRec();
        ObservableList<Societe> listm = getSocieteList();
             
       id.setCellValueFactory(new PropertyValueFactory<>("id"));
        nomSociete.setCellValueFactory(new PropertyValueFactory<>("nomSociete"));
        typeSociete.setCellValueFactory(new PropertyValueFactory<>("typeSociete"));
   
        tableview.setItems(listm);
        search_Societe();

    }
    
       @FXML
    private void AjouterSociete() throws IOException {  
        PidevUser m = new PidevUser() ;
       
        m.changeScene("/gui/AjouterSociete.fxml");
        
    }
    
 
  @FXML
    public void getSelected(MouseEvent event) throws SQLException {
        index = tableview.getSelectionModel().getSelectedIndex();
        if (index <= -1) {

            return;
        }
        ids.setText(id.getCellData(index).toString());
        nomSocietes.setText(nomSociete.getCellData(index).toString());
       typeSocietes.setText(typeSociete.getCellData(index).toString());
      



    }

    @FXML
    private void showSelectedSociete(MouseEvent event) {
    }

    @FXML
    public ObservableList<Societe> getSocieteList() {
        cnx = dbconnection.getInstance().getConnection();

        ObservableList<Societe> SocieteList = FXCollections.observableArrayList();
        try {
            String query2 = "SELECT * from Societe ";
            PreparedStatement smt = cnx.prepareStatement(query2);
            Societe l;
            ResultSet rs = smt.executeQuery();
            while (rs.next()) {
                l = new Societe(rs.getInt("id"), rs.getString("nomSociete"),  rs.getString("typeSociete"));
                SocieteList.add(l);
            }
            System.out.println(SocieteList);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return SocieteList;

    }

    @FXML
    public void showRec() {

        ObservableList<Societe> list = getSocieteList();
        
       id.setCellValueFactory(new PropertyValueFactory<>("id"));
        nomSociete.setCellValueFactory(new PropertyValueFactory<>("nomSociete"));
        typeSociete.setCellValueFactory(new PropertyValueFactory<>("typeSociete"));
   
        
        tableview.setItems(list);

    }

    @FXML
    private void refresh() {
        ObservableList<Societe> list = getSocieteList();
        
       id.setCellValueFactory(new PropertyValueFactory<>("id"));
        nomSociete.setCellValueFactory(new PropertyValueFactory<>("nomSociete"));
        typeSociete.setCellValueFactory(new PropertyValueFactory<>("typeSociete"));
        
        tableview.setItems(list);

    }


    public void Edit() {

        try {
            
              cnx = dbconnection.getInstance().getConnection();
            String value1 = ids.getText();
            String value2 = nomSocietes.getText();
            String value3 = typeSocietes.getText();

            String query3 = "update Societe set nomSociete='" + value2 + "'  ,typeSociete='" + value3 +"' WHERE id = '" + value1 + "' ";
            PreparedStatement smt = cnx.prepareStatement(query3);
            smt.execute();
            showRec();
            search_Societe();
            
            Alert alert = new Alert(Alert.AlertType.WARNING);
             alert.setTitle("Success");
             alert.setHeaderText(null);
             alert.setContentText("Votre Societe a été modifier avec sucess");
             alert.showAndWait();


        } catch (Exception e) {
        }
    }

    public void Delete() {
         cnx = dbconnection.getInstance().getConnection();
        String sql = "delete from Societe where id = ?";
        try {

            PreparedStatement smt = cnx.prepareStatement(sql);
            smt.setString(1, ids.getText());
            smt.execute();
            showRec();
            search_Societe();
            
             Alert alert = new Alert(Alert.AlertType.WARNING);
             alert.setTitle("Success");
             alert.setHeaderText(null);
             alert.setContentText("Votre Societe a été supprimer avec sucess");
             alert.showAndWait();
             
        } catch (Exception e) {

        }

    }

    @FXML
    void search_Societe() {
          id.setCellValueFactory(new PropertyValueFactory<>("id"));
        nomSociete.setCellValueFactory(new PropertyValueFactory<>("nomSociete"));
        typeSociete.setCellValueFactory(new PropertyValueFactory<>("typeSociete"));
       cnx = dbconnection.getInstance().getConnection();

        ObservableList<Societe> dataList = getSocieteList();

        tableview.setItems(dataList);

        FilteredList<Societe> filteredData = new FilteredList<>(dataList, b -> true);
        Recherche_Societe.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(person -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();
                if (person.getNomSociete().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches description
                } else if (person.getTypeSociete().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches typecon*/
                } else {
                    return false; // Does not match.
                }
            });
        });

        SortedList<Societe> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(tableview.comparatorProperty());
        tableview.setItems(sortedData);

    }
    
       @FXML
    private void stat(ActionEvent event) throws IOException {
         FXMLLoader loader = new FXMLLoader();
        
        loader.setLocation(getClass().getResource("statSociete.fxml"));
        Parent root = loader.load();
        typeSocietes.getScene().setRoot(root);
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
         m.changeScene("/gui/AfficherSocieteClient.fxml");
        
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
   
      
    
    
    
}

