/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entites.User;
import Services.ServiceUser;
import java.io.IOException;
import java.sql.SQLException;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import pidevuser.PidevUser;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.concurrent.Worker.State;
import javafx.fxml.FXML;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import netscape.javascript.JSObject;

/**
 *
 * @author Souid
 */
public class loginController {
    
    
    public loginController(){
        
    }
    
    @FXML
    private Button exitBtn;
   
    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passwordField;
    
    @FXML 
    private Button login_button ;
    
    
            
   @FXML
public void exit(ActionEvent event)throws IOException{
exitBtn.setOnAction(e -> Platform.exit());
} 
  
    
    public void userLogin(ActionEvent event) throws IOException, SQLException, InterruptedException{
        checkLogin();
    }
     public void userSignUp(ActionEvent event) throws IOException{
        checkSignUp();
    }
private void checkLogin() throws IOException, SQLException, InterruptedException {
    PidevUser m = new PidevUser();
    User user = ServiceUser.getInstance().searchUserByEmail(usernameField.getText(),passwordField.getText());
    if(user != null){
        pidevuser.PidevUser.user=user ;
        System.out.println("User found: " + user.getEmail() + ", blocked: " + user.getBlocked());
        if(user.getBlocked() != null && user.getBlocked()) {
            m.changeScene("/gui/UserBlocked.fxml");
        } else if("ROLE_USER".equals(user.getRoles())) {
            m.changeScene("/gui/LoggedInClient.fxml");
        } else if("ROLE_ADMIN".equals(user.getRoles())) {
            m.changeScene("/gui/LoggedIn.fxml");
        }
    } else {
        // if the user is not found, display an alert message and disable the login_button for 20 seconds
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Login Failed");
        alert.setHeaderText("Invalid email or password!");
        alert.setContentText("Please check your email and password and try again.");
        alert.showAndWait();

        login_button.setDisable(true);
        Thread.sleep(20000);
        login_button.setDisable(false);
    }
}




        
        
    
    @FXML
    private void checkSignUp() throws IOException{
        
            PidevUser m = new PidevUser();
             m.changeScene("/gui/SignUp.fxml");
           
    }
    @FXML
    private void ResetPassword() throws IOException{
        
            PidevUser m = new PidevUser();
             m.changeScene("/gui/ResetPassword.fxml");
           
    }
    
    
    

}

