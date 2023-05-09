/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gui;
import java.io.IOException;
import java.net.URL;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.Initializable;
import pidevuser.PidevUser;

/**
 *
 * @author Souid
 */
public class ChatBotController implements Initializable{
      @FXML
    private Label lblTitle;

    @FXML
    private TextField txtInput;

    @FXML
    private Button btnSend;

    @FXML
    private Label lblOutput;

     @Override
    public void initialize(URL url, ResourceBundle rb){
        // Set button action
        btnSend.setOnAction(event -> {
            try {
                String input = txtInput.getText();
                System.out.println("input"+input);
                String response = getResponse(input);
                System.out.println(response);
                lblOutput.setText(response);
                txtInput.clear();
            } catch (IOException ex) {
                Logger.getLogger(ChatBotController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }
    
    private static final String[] RESPONSES = {
            "Hello",
            "How are you?",
            "Nice to meet you!",
            "What can I help you with today?",
            "I'm sorry, I don't understand. Can you please rephrase your question?"
    };

   

    private String getResponse(String input) throws IOException {
         
        // Loop through responses and return a random one
PidevUser m = new PidevUser() ;
        for (String response : RESPONSES) {
            
            if (input.toLowerCase().contains("salut")) {
                return "salut comment je peux t aider aujourd'hui ?";
            }
            if (input.toLowerCase().contains("dd") || input.toLowerCase().contains("developpement durable") || input.toLowerCase().contains("durable") ) {
                return "Le développement durable est une conception du développement qui s'inscrit dans une perspective de "
                        + "long terme et en intégrant les contraintes environnementales et sociales à l'économie";
            }
             if (input.toLowerCase().contains("i have some question")) {
                return "i will do my best to help you";
            }
            
            if (input.toLowerCase().contains("ou je peux passer des livraisons")|| input.toLowerCase().contains("livraison")) {
           
         m.changeScene("/gui/AjouterLivraison.fxml");
                
            }
            if (input.toLowerCase().contains("ou je peux trouver les societe de recyclage")|| input.toLowerCase().contains("societe de recylage") ) {
            
          
         m.changeScene("/gui/AfficherSocieteClient.fxml");
            }
            
            if (input.toLowerCase().contains("ou je peux demander des conseils")|| input.toLowerCase().contains("conseil")) {
              
         m.changeScene("/gui/AjouterConseil.fxml");
            }
       
        if (input.toLowerCase().contains("ou je peux changer mon mot de passe")|| input.toLowerCase().contains("resetpassword")) {
              
         m.changeScene("/gui/ResetPassword.fxml");
            }
         }
        return RESPONSES[RESPONSES.length];
    }
    
     @FXML
    private void back()throws IOException {
        PidevUser m = new PidevUser() ;
         m.changeScene("/gui/LoggedInClient.fxml");
        
    }
}
