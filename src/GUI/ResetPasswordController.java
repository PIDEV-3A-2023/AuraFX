/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import static controller.LoginController.IdOfUser;
import entity.User;
import entity.mail;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLDataException;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.controlsfx.control.Notifications;
import org.mindrot.jbcrypt.BCrypt;
import service.UserService;

/**
 * FXML Controller class
 *
 * @author Abderrazekbenhamouda
 */
public class ResetPasswordController implements Initializable {

    @FXML
    private TextField email;
    UserService userService = new UserService();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    


    @FXML
    private void Envoyer(ActionEvent event) throws SQLDataException, Exception {
        
        
        if(!userService.checkEmailExist(email.getText())){
        
                Notifications.create().title("Warnning").text("No email Found").position(Pos.BOTTOM_RIGHT).showError();

        
        }else{
            
            User u = userService.findUserByEmail(email.getText());
            
            Random random = new Random();
        int number = random.nextInt(101001);
            
                 String pass = String.valueOf(number);
                pass = BCrypt.hashpw(pass, BCrypt.gensalt(13));
                 pass= pass.replaceFirst("a", "y");
                u.setMdp(pass);
            
            userService.modifiePassword(u);
        
                    mail.envoi(email.getText(), "Nouveau Password", " Votre Noveaux Password est"+number);

         Notifications.create().title("Success").text("Vous avez récu un email avec votre mdp").position(Pos.BOTTOM_RIGHT).showError();

                  Parent root;
                        try {
                            root = FXMLLoader.load(getClass().getResource("/gui/Login.fxml"));
                            Stage myWindow = (Stage) email.getScene().getWindow();
                            Scene sc = new Scene(root);
                            myWindow.setScene(sc);
                            myWindow.setTitle("page name");
                            //myWindow.setFullScreen(true);
                            myWindow.show();
                        } catch (IOException ex) {
                            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                        }
        }
        
        
    }
    
}
