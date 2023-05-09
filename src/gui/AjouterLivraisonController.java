/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entites.Livraison;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import Services.LivraisonService;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import java.awt.image.BufferedImage;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import pidevuser.PidevUser;
import util.dbconnection;

/**
 * FXML Controller class
 *
 * @author Pc
 */
public class AjouterLivraisonController implements Initializable {
      @FXML
    private TextField quantiteTF;
    @FXML
    private TextField etatTF;
    @FXML
    private TextField categorieTF;
   

   
        @FXML
    private void AjouterLivraison(ActionEvent event) {
        String etat = etatTF.getText();
        String categorie= categorieTF.getText();
        int quantite = Integer.parseInt(quantiteTF.getText());
        Livraison l = new Livraison(quantite, etat, categorie);
        LivraisonService ls = new LivraisonService();
        try {
            ls.ajouter(l);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
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
    
     @FXML
    private TableView<Livraison> tableview;
     
  
    @FXML
    private TableColumn<Livraison, Integer> quantite;
    @FXML
    private TableColumn<Livraison, String> etat;
    @FXML
    private TableColumn<Livraison, String> categorie;
    @FXML
    private TextField Recherche_Livraison;
  
        int index = -1;

    @FXML
    private TextField ids;
    @FXML
    private TextField quantites;
     @FXML
    private TextField etats;
    @FXML
    private TextField categories;
    
     @FXML
    private Button refresh;
    @FXML
    private Button modifier;
    @FXML
    private Button supprimer;
private Connection cnx;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        ObservableList<Livraison> listm = getLivraisonList();
             
        quantite.setCellValueFactory(new PropertyValueFactory<>("quantite"));
        etat.setCellValueFactory(new PropertyValueFactory<>("etat"));
        categorie.setCellValueFactory(new PropertyValueFactory<>("categorie"));

   
        tableview.setItems(listm);
        search_Livraison();

    }
    
     
    
  
  
    
       @FXML
    private void Ajouter() throws IOException {
        PidevUser m = new PidevUser();
        m.changeScene("/gui/AjouterLivraison.fxml");
    }
    
      @FXML
    private void showSelectedUser(MouseEvent event) {
    }

     @FXML
    public void getSelected(MouseEvent event) throws SQLException {
        index = tableview.getSelectionModel().getSelectedIndex();
        if (index <= -1) {

            return;
        }
        quantites.setText(quantite.getCellData(index).toString());
        etats.setText(etat.getCellData(index).toString());
        categories.setText(categorie.getCellData(index).toString());
       

    }
    
    @FXML
    public ObservableList<Livraison> getLivraisonList() {
        cnx = dbconnection.getInstance().getConnection();

        ObservableList<Livraison> LivraisonList = FXCollections.observableArrayList();
        try {
            String query2 = "SELECT  * from livraison ";
            PreparedStatement smt = cnx.prepareStatement(query2);
            Livraison livraison;
            ResultSet rs = smt.executeQuery();
            while (rs.next()) {
                livraison = new Livraison(rs.getInt("id"), rs.getInt("quantite"),  rs.getString("etat"),  rs.getString("categorie"));
                LivraisonList.add(livraison);
            }
            System.out.println(LivraisonList);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return LivraisonList;

    }
    


    @FXML
    private void refresh() {
        ObservableList<Livraison> list = getLivraisonList();
       
        quantite.setCellValueFactory(new PropertyValueFactory<>("quantite"));
        etat.setCellValueFactory(new PropertyValueFactory<>("etat"));
        categorie.setCellValueFactory(new PropertyValueFactory<>("categorie"));

        
        tableview.setItems(list);

    }
    
     @FXML
    public void showRec() {

         ObservableList<Livraison> list = getLivraisonList();
      
        quantite.setCellValueFactory(new PropertyValueFactory<>("quantite"));
        etat.setCellValueFactory(new PropertyValueFactory<>("etat"));
        categorie.setCellValueFactory(new PropertyValueFactory<>("categorie"));

        
        tableview.setItems(list);

    }
    
    

    
      @FXML
    void search_Livraison() {
        quantite.setCellValueFactory(new PropertyValueFactory<>("quantite"));
        etat.setCellValueFactory(new PropertyValueFactory<>("etat"));
        categorie.setCellValueFactory(new PropertyValueFactory<>("categorie"));
        
          cnx = dbconnection.getInstance().getConnection();

        ObservableList<Livraison> dataList = getLivraisonList();

        tableview.setItems(dataList);

        FilteredList<Livraison> filteredData = new FilteredList<>(dataList, b -> true);
        Recherche_Livraison.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(person -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();
                if (person.getCategorie().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches username
                } else if (person.getEtat().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches password*/
                } else {
                    return false; // Does not match.
                }
            });
        });

        SortedList<Livraison> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(tableview.comparatorProperty());
        tableview.setItems(sortedData);

    }
    
    
      public void qrcode() throws SQLException {

Livraison data = tableview.getSelectionModel().getSelectedItem();
        

        // Create a button
        // Set an event handler for the button
        // Convert the data to a string
        StringBuilder stringBuilder = new StringBuilder();
       
            stringBuilder.append(data);
            System.out.println(data);
            stringBuilder.append("\n");
        
        String dataString = stringBuilder.toString().trim();

        // Generate the QR code image
        Image qrCodeImage = generateQRCode(dataString);

        // Display the QR code image
        ImageView imageView = new ImageView(qrCodeImage);
        VBox vbox = new VBox(imageView);
        Scene scene = new Scene(vbox);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();

    }

    private Image generateQRCode(String data) {
        try {
            // Create a QR code writer
            QRCodeWriter writer = new QRCodeWriter();

            // Create a BitMatrix from the data and set the size
            BitMatrix bitMatrix = writer.encode(data, BarcodeFormat.QR_CODE, 400, 400);

            // Create a BufferedImage from the BitMatrix
            BufferedImage image = MatrixToImageWriter.toBufferedImage(bitMatrix);

            // Create a JavaFX Image from the BufferedImage
            return SwingFXUtils.toFXImage(image, null);

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
      @FXML
    private void ajouterReclamation() throws IOException {
        PidevUser m = new PidevUser();
        m.changeScene("/gui/ajoutReclamation.fxml");
    }
   
}
