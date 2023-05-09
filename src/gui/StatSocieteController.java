package gui;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



import entites.Societe;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;
import Services.SocieteService;


/**
 * FXML Controller class
 *
 * @author azizk
 */
public class StatSocieteController implements Initializable {

   
        @FXML
    private PieChart pie;
    @FXML
    private Label nv_text;
    @FXML
    private Label np_text;
    @FXML
    private Label k_text;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        calcul() ; 
       

       
    }    
    
     public void calcul() {
        SocieteService ser= new SocieteService();
        int np = 0 , nv=0 ,k=0; 
        List<Societe> li =ser.ListClasse();
        int i = 0; 
        
        
        for ( i=0 ; i<li.size();i++){
        if (li.get(i).getTypeSociete().equals("Plastique"))
        
        nv=nv+1;  
       else if (li.get(i).getTypeSociete().equals("Verre"))
            np=np+1 ;
              
                else 
            k=k+1;
               System.out.println( "k"+k);
        
             
  }
        pie.setTitle("Stats");
        ObservableList <PieChart.Data> ol = FXCollections.observableArrayList(
        
        new PieChart.Data("Plastique", nv),new PieChart.Data("Verre", np),new PieChart.Data("Other",k)
                
                
           );
        nv_text.setText(Integer.toString(nv));
        np_text.setText(Integer.toString(np));
         k_text.setText(Integer.toString(k));
                pie.setData(ol);
     }

    @FXML
    private void back(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        
        loader.setLocation(getClass().getResource("/gui/AfficherSociete.fxml"));
        Parent root = loader.load();
        pie.getScene().setRoot(root);
    }
}






