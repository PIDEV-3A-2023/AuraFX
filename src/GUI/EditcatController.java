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
import javafx.fxml.Initializable;
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
public class EditcatController implements Initializable {

    @FXML
    private TextField nom;
    @FXML
    private TextField image;
    @FXML
    private TextField nbr;
    @FXML
    private Button parcourir;
    categorie c = new categorie();
    @FXML
    private Button modifier;
    private byte[] fichier;
    categorieservice cs = new categorieservice();

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

    void setTextField(String nom,int nbr,String image,int id){
        //this.cat.setValue(cat);
        this.image.setText(image);
        this.nom.setText(nom);
        this.nbr.setText(""+nbr);
        c.setId(id);
        
    }

    @FXML
    private void modifier(ActionEvent event) {
        if (nom.getText().isEmpty()|| image.getText().isEmpty()  || nbr.getText().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("NOT OK");
            alert.setHeaderText("modification non effectue");
            alert.setContentText("Click Cancel to exit.");
            alert.showAndWait();
            
        }
      
        
        
        else{
                
                cs.modifier(nom.getText(), image.getText(), Integer.parseInt(nbr.getText()), c);
                System.out.println(c.getId());
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("OK");
                alert.setHeaderText("modification effectue");
                alert.setContentText("Click Cancel to exit.");
                alert.showAndWait();
        }
    }
    
}
