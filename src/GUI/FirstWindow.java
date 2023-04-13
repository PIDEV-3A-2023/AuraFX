/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.image.Image;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author belkn
 */
public class FirstWindow extends Application {

    @Override
    public void start(Stage primaryStage) {
        Parent root;
        try {
            //root = FXMLLoader.load(getClass().getResource("Association.FXML"));
            root = FXMLLoader.load(getClass().getResource("Don.FXML"));
            Scene scene = new Scene(root);

            primaryStage.setTitle("Gestion Don");
            //primaryStage.setTitle("Gestion Association");
            //primaryStage.setOnShown(e -> primaryStage.getIcons().add(new Image("icon.png")));
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
