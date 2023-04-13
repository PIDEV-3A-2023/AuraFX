/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import entities.categorie;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import services.categorieservice;

/**
 * FXML Controller class
 *
 * @author azerb
 */
public class AddcatController implements Initializable {

    @FXML
    private TextField nom;
    @FXML
    private TextField image;
    @FXML
    private TextField nbr;
    @FXML
    private Button parcourir;
    @FXML
    private Button ajouter;
    private byte[] fichier;
    categorie c = new categorie();
    categorieservice cs=new categorieservice();
    @FXML
    private Button retour;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void parcourir(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
            File file = fileChooser.showOpenDialog(new Stage());
            if (file != null) {
                image.setText(file.getAbsolutePath());
                try {
                    Path path = Paths.get(file.getAbsolutePath());
                    fichier = Files.readAllBytes(path);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
    }

    @FXML
    private void ajouter(ActionEvent event) {
        if (nom.getText().isEmpty() || image.getText().isEmpty() || nbr.getText().isEmpty() ){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("NOT OK");
            alert.setHeaderText("Ajout non effectue");
            alert.setContentText("Click Cancel to exit.");
            alert.showAndWait();
            
        }
      
        
        
        else{
                
                
                c.setNom(nom.getText());
                c.setImage(image.getText());
                c.setNbrproduit(Integer.parseInt(nbr.getText()));
                cs.ajouter(c);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("OK");
                alert.setHeaderText("Ajout effectue");
                alert.setContentText("Click Cancel to exit.");
                alert.showAndWait();
                
        }
    }

    @FXML
    private void retour(ActionEvent event) {
        try {
            //navigation
            Parent loader = FXMLLoader.load(getClass().getResource("affichercat.fxml"));
            retour.getScene().setRoot(loader);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
}
