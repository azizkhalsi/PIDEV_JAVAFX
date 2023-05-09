/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entites.Conseil;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import Services.ConseilService;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import pidevuser.PidevUser;
import util.dbconnection;

/**
 * FXML Controller class
 *
 * @author Pc
 */
public class AjouterConseilController implements Initializable {

    @FXML
    private TextField descriptionTF;
    @FXML
    private TextField typeconTF;
   

   
        @FXML
    private void AjouterConseil(ActionEvent event) {
        String description = descriptionTF.getText();
        String typecon= typeconTF.getText();
        Conseil l = new Conseil(description, typecon);
        ConseilService ls = new ConseilService();
        
          if(ValidateEmptyForm(typeconTF)
             && ValidateTypecon(typeconTF))
        {
        try {
            ls.ajouter(l);
             Alert alert = new Alert(Alert.AlertType.WARNING);
             alert.setTitle("Success");
             alert.setHeaderText(null);
             alert.setContentText("Votre Conseil a été Ajouter avec sucess");
             alert.showAndWait();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        }
    }
  
  
    private boolean ValidateEmptyForm(TextField typecon){
         if ( typecon.getText().equals("") ){
             Alert alert = new Alert(Alert.AlertType.WARNING);
             alert.setTitle("Erreur");
             alert.setHeaderText(null);
             alert.setContentText("Veuillez remplir tous les champs");
             alert.showAndWait();
             
             return false;  
        } else {
             return true;  
         }
     }
    
    
    
     private boolean ValidateTypecon(TextField t){
         Pattern p = Pattern.compile("[a-zA-Z]+");
         Matcher m = p.matcher(t.getText());
         if (m.find() && m.group().equals(t.getText())){
             return true;
             
         }else{
             Alert alert = new Alert(Alert.AlertType.WARNING);
             alert.setTitle("Erreur");
             alert.setHeaderText(null);
             alert.setContentText(t.getText()+" : Typecon non valide , votre Typecon doit contenir seulement que des lettres");
             alert.showAndWait();
             
             return false;
         }
     }
     
       @FXML
    private TableView<Conseil> tableview;
         @FXML
    private TextField Recherche_Conseil;
     
     @FXML
    private TableColumn<Conseil, Integer> id;
    @FXML
    private TableColumn<Conseil, String> description;
    @FXML
    private TableColumn<Conseil, String> typecon;
      @FXML
    private TableColumn<Conseil, Button> pdf;
   

    @FXML
    private TextField ids;
    @FXML
    private TextField descriptions;
     @FXML
    private TextField typecons;

    
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
        ObservableList<Conseil> listm = getConseilList();
             
       id.setCellValueFactory(new PropertyValueFactory<>("id"));
        description.setCellValueFactory(new PropertyValueFactory<>("descriptions"));
        typecon.setCellValueFactory(new PropertyValueFactory<>("typecon"));
     this.pdf();
        tableview.setItems(listm);
        search_Conseil();

    }
    
     
    
 
  @FXML
    public void getSelected(MouseEvent event) throws SQLException {
        index = tableview.getSelectionModel().getSelectedIndex();
        if (index <= -1) {

            return;
        }
        ids.setText(id.getCellData(index).toString());
        descriptions.setText(description.getCellData(index).toString());
       typecons.setText(typecon.getCellData(index).toString());
      



    }

    @FXML
    private void showSelectedConseil(MouseEvent event) {
    }

    @FXML
    public ObservableList<Conseil> getConseilList() {
        cnx = dbconnection.getInstance().getConnection();

        ObservableList<Conseil> ConseilList = FXCollections.observableArrayList();
        try {
            String query2 = "SELECT * from conseil ";
            PreparedStatement smt = cnx.prepareStatement(query2);
            Conseil l;
            ResultSet rs = smt.executeQuery();
            while (rs.next()) {
                l = new Conseil(rs.getInt("id"), rs.getString("description"),  rs.getString("typecon"));
                ConseilList.add(l);
            }
            System.out.println(ConseilList);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return ConseilList;

    }

    @FXML
    public void showRec() {

        ObservableList<Conseil> list = getConseilList();
        
       id.setCellValueFactory(new PropertyValueFactory<>("id"));
        description.setCellValueFactory(new PropertyValueFactory<>("description"));
        typecon.setCellValueFactory(new PropertyValueFactory<>("typecon"));
   
        
        tableview.setItems(list);

    }

    @FXML
    private void refresh() {
        ObservableList<Conseil> list = getConseilList();
        
       id.setCellValueFactory(new PropertyValueFactory<>("id"));
        description.setCellValueFactory(new PropertyValueFactory<>("description"));
        typecon.setCellValueFactory(new PropertyValueFactory<>("typecon"));
        
        tableview.setItems(list);

    }


    public void Edit() {

        try {
            
              cnx = dbconnection.getInstance().getConnection();
            String value1 = ids.getText();
            String value2 = descriptions.getText();
            String value3 = typecons.getText();

            String query3 = "update conseil set description='" + value2 + "'  ,typecon='" + value3 +"' WHERE id = '" + value1 + "' ";
            PreparedStatement smt = cnx.prepareStatement(query3);
            smt.execute();
            showRec();
            search_Conseil();
            
            Alert alert = new Alert(Alert.AlertType.WARNING);
             alert.setTitle("Success");
             alert.setHeaderText(null);
             alert.setContentText("Votre Conseil a été modifier avec sucess");
             alert.showAndWait();


        } catch (Exception e) {
        }
    }

    public void Delete() {
         cnx = dbconnection.getInstance().getConnection();
        String sql = "delete from conseil where id = ?";
        try {

            PreparedStatement smt = cnx.prepareStatement(sql);
            smt.setString(1, ids.getText());
            smt.execute();
            showRec();
            search_Conseil();
            
             Alert alert = new Alert(Alert.AlertType.WARNING);
             alert.setTitle("Success");
             alert.setHeaderText(null);
             alert.setContentText("Votre Conseil a été supprimer avec sucess");
             alert.showAndWait();
             
        } catch (Exception e) {

        }

    }

    @FXML
    void search_Conseil() {
          id.setCellValueFactory(new PropertyValueFactory<>("id"));
        description.setCellValueFactory(new PropertyValueFactory<>("description"));
        typecon.setCellValueFactory(new PropertyValueFactory<>("typecon"));
       cnx = dbconnection.getInstance().getConnection();

        ObservableList<Conseil> dataList = getConseilList();

        tableview.setItems(dataList);

        FilteredList<Conseil> filteredData = new FilteredList<>(dataList, b -> true);
        Recherche_Conseil.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(person -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();
                if (person.getDescription().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches description
                } else if (person.getTypecon().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches typecon*/
                } else {
                    return false; // Does not match.
                }
            });
        });

        SortedList<Conseil> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(tableview.comparatorProperty());
        tableview.setItems(sortedData);

    }
    
@FXML
public void pdf() {
    pdf.setCellFactory((param) -> {
        return new TableCell() {
            @Override
            protected void updateItem(Object item, boolean empty) {
                setGraphic(null);
                if (!empty) {
                    Button b = new Button("imprimer");

                    b.setOnAction((event) -> {
                        // Get the selected Conseil object from the TableView
                        Conseil selectedConseil = (Conseil) getTableView().getItems().get(getIndex());
                        // Create a new Document
                        Document document = new Document();

                        try {
                            // Create a PdfWriter to write the Document to a file or output stream
                            PdfWriter.getInstance(document, new FileOutputStream("Conseil.pdf"));
                            document.open();

                            // Add the green background to the top of the page
                            Rectangle rect = new Rectangle(PageSize.A4);
                            rect.setBackgroundColor(new BaseColor(0, 153, 51));
                            document.add(rect);

                            // Add the logo to the top right corner
                            Image logo = Image.getInstance(getClass().getResource("/image/logo.png"));
                            logo.setAlignment(Image.RIGHT | Image.TOP);
                            logo.scaleAbsolute(100f, 100f);
                            document.add(logo);

                            // Add space to move the next content down
                            document.add(new Paragraph(" "));

                            // Add the table headers
                            PdfPTable table = new PdfPTable(2);
                            table.setWidthPercentage(100);
                            table.setSpacingBefore(10f);
                            table.setSpacingAfter(10f);

                            PdfPCell cell1 = new PdfPCell(new Phrase("Description:", new Font(Font.FontFamily.HELVETICA, 14, Font.BOLD, BaseColor.WHITE)));
                            cell1.setBorderColor(BaseColor.WHITE);
                            cell1.setBackgroundColor(new BaseColor(0, 153, 51));
                            cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
                            cell1.setVerticalAlignment(Element.ALIGN_MIDDLE);

                            PdfPCell cell2 = new PdfPCell(new Phrase("Type Conseil:", new Font(Font.FontFamily.HELVETICA, 14, Font.BOLD, BaseColor.WHITE)));
                            cell2.setBorderColor(BaseColor.WHITE);
                            cell2.setBackgroundColor(new BaseColor(0, 153, 51));
                            cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
                            cell2.setVerticalAlignment(Element.ALIGN_MIDDLE);

                            table.addCell(cell1);
                            table.addCell(cell2);

                            // Add the Conseil details to the table
                            PdfPCell cell3 = new PdfPCell(new Phrase(selectedConseil.getDescription(), new Font(Font.FontFamily.HELVETICA, 12)));
                            cell3.setBorderColor(BaseColor.WHITE);
                            cell3.setHorizontalAlignment(Element.ALIGN_LEFT);
                            cell3.setVerticalAlignment(Element.ALIGN_MIDDLE);

                            PdfPCell cell4 = new PdfPCell(new Phrase(selectedConseil.getTypecon().toString(), new Font(Font.FontFamily.HELVETICA, 12)));
                            cell4.setBorderColor(BaseColor.WHITE);
                            cell4.setHorizontalAlignment(Element.ALIGN_LEFT);
                            cell4.setVerticalAlignment(Element.ALIGN_MIDDLE);

                            table.addCell(cell3);
                            table.addCell(cell4);

                            document.add(table);

                            // Close the Document
                            document.close();

                            if (Desktop.isDesktopSupported()) {
                                Desktop desktop = Desktop.getDesktop();
                                try {
                                    desktop.open(new File("Conseil.pdf"));
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }

                        } catch (Exception ex) {
                            Logger.getLogger(AfficherConseilController.class.getName()).log(Level.SEVERE, null, ex);
                        }

                    });
                    setGraphic(b);
                }
            }
        };

    });

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
    private void ajouterReclamation() throws IOException {
        PidevUser m = new PidevUser();
        m.changeScene("/gui/ajoutReclamation.fxml");
    }
   
}
