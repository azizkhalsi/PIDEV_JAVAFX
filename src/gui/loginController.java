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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.fxml.FXML;

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
        System.out.println("+++++++++++++++++"+user.getBlocked());
        
        if(user != null){
//            errorLabel.setText("success");
             pidevuser.PidevUser.user=user ;
           
         if("ROLE_USER".equals(user.getRoles()))
        {
            m.changeScene("/gui/LoggedInClient.fxml");
        }
         
         else if("ROLE_ADMIN".equals(user.getRoles()))
        {
            m.changeScene("/gui/LoggedIn.fxml");
        }
            
        }
        if(user.getBlocked() == true)
        {
            m.changeScene("/gui/UserBlocked.fxml");
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

