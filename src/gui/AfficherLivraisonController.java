/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import entites.Livraison;
import java.awt.image.BufferedImage;
import pidevuser.PidevUser;
import util.dbconnection;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;
import Services.LivraisonService;

/**
 * FXML Controller class
 *
 * @author Pc
 */
public class AfficherLivraisonController implements Initializable {
     @FXML
    private TableView<Livraison> tableview;
     
     @FXML
    private TableColumn<Livraison, Integer> id;
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
        addButtonToTable();
        ObservableList<Livraison> listm = getLivraisonList();
             
       id.setCellValueFactory(new PropertyValueFactory<>("id"));
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
        ids.setText(id.getCellData(index).toString());
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
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        quantite.setCellValueFactory(new PropertyValueFactory<>("quantite"));
        etat.setCellValueFactory(new PropertyValueFactory<>("etat"));
        categorie.setCellValueFactory(new PropertyValueFactory<>("categorie"));

        
        tableview.setItems(list);

    }
    
     @FXML
    public void showRec() {

         ObservableList<Livraison> list = getLivraisonList();
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        quantite.setCellValueFactory(new PropertyValueFactory<>("quantite"));
        etat.setCellValueFactory(new PropertyValueFactory<>("etat"));
        categorie.setCellValueFactory(new PropertyValueFactory<>("categorie"));

        
        tableview.setItems(list);

    }
    
    
    public void Edit() {

        try {
cnx = dbconnection.getInstance().getConnection();
String value1 = ids.getText();
            String value2 = ids.getText();
            String value3 = quantites.getText();
            String value4 = etats.getText();
            String value5 = categories.getText();
            

            String query3 = "update livraison set id='" + value2 + "'  ,quantite='" + value3 + "'  ,etat='" + value4 + "'  ,categorie='" + value5 + "' ";
            PreparedStatement smt = cnx.prepareStatement(query3);
            smt.execute();
            showRec();
            search_Livraison();

        } catch (Exception e) {
        }
    }

    public void Delete() {
        cnx = dbconnection.getInstance().getConnection();
        String sql = "delete from livraison where id = ?";
        try {

            PreparedStatement smt = cnx.prepareStatement(sql);
            smt.setString(1, ids.getText());
            smt.execute();
            showRec();
            search_Livraison();
        } catch (Exception e) {

        }

    }
    
      @FXML
    void search_Livraison() {
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
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
    
    
     private void addButtonToTable() {
        TableColumn<Livraison, Void> TraiterBtn = new TableColumn("Traiter");

        Callback<TableColumn<Livraison, Void>, TableCell<Livraison, Void>> cellFactory = new Callback<TableColumn<Livraison, Void>, TableCell<Livraison, Void>>() {
            @Override
            public TableCell<Livraison, Void> call(final TableColumn<Livraison, Void> param) {
                final TableCell<Livraison, Void> cell = new TableCell<Livraison, Void>() {

                    private final Button btn = new Button("Traiter");

                    {
                        btn.setOnAction((ActionEvent event) -> {
                            TableColumn<Livraison, String> firstColumn = (TableColumn<Livraison, String>) getTableView().getColumns().get(0);
                            String etat = firstColumn.getCellData(getIndex());
                            System.out.println("selectedData: " + etat);
                           try {
                                LivraisonService.getInstance().Traiter(etat);
                            } catch (SQLException ex) {
                                Logger.getLogger(AfficherLivraisonController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        });
                    }

                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(btn);
                        }
                    }
                };
                return cell;
            }
        };

        TraiterBtn.setCellFactory(cellFactory);

        tableview.getColumns().add(TraiterBtn);

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


    
}
