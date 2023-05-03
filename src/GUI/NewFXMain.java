/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author azerb
 */
public class NewFXMain extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        try {
          //  Parent root=FXMLLoader.load(getClass().getResource("produitfront.fxml"));
            // Parent root=FXMLLoader.load(getClass().getResource("postfront.fxml"));
            // Parent root=FXMLLoader.load(getClass().getResource("ShowCommentBack.fxml"));
           //  Parent root=FXMLLoader.load(getClass().getResource("postBack.fxml"));
            Parent root=FXMLLoader.load(getClass().getResource("login.fxml"));
         //   Parent root=FXMLLoader.load(getClass().getResource("alluser.fxml"));
           //    Parent root=FXMLLoader.load(getClass().getResource("alluser.fxml"));
            Scene scene = new Scene(root);
            //  Application.setUserAgentStylesheet(null);
            primaryStage.setTitle("SHOW POST");
            primaryStage.setScene(scene);
            primaryStage.getIcons().add(new Image("/image/logo.png"));
            primaryStage.show();
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
