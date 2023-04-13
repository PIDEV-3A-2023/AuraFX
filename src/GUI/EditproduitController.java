/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import entities.categorie;
import entities.produit;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import services.categorieservice;
import services.produitservice;

/**
 * FXML Controller class
 *
 * @author azerb
 */
public class EditproduitController implements Initializable {
    produit p = new produit();
    produitservice ps = new produitservice();
    categorieservice cs = new categorieservice();
    categorie c= new categorie();
    @FXML
    private ComboBox<categorie> cat = new ComboBox<>();
    @FXML
    private TextField nom;
    @FXML
    private TextField desc;
    @FXML
    private TextField image;
    @FXML
    private TextField prix;
    @FXML
    private TextField nbr;
    @FXML
    private Button edit;
    private byte[] fichier;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        load();
    }    

    private void load(){
        List<categorie> cats=cs.getAll();
        for(categorie i : cats) {
                cat.getItems().add(i);
                }
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
    private void modifier(ActionEvent event) {
        if (nom.getText().isEmpty()|| desc.getText().isEmpty()|| image.getText().isEmpty() || prix.getText().isEmpty() || nbr.getText().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("NOT OK");
            alert.setHeaderText("modification non effectue");
            alert.setContentText("Click Cancel to exit.");
            alert.showAndWait();
            
        }
      
        
        
        else{
                c=cat.getValue();
                ps.modifier(nom.getText(), desc.getText(), image.getText(), Float.parseFloat(prix.getText()), Integer.parseInt(nbr.getText()), c, p);
                System.out.println(c.getId());
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("OK");
                alert.setHeaderText("modification effectue");
                alert.setContentText("Click Cancel to exit.");
                alert.showAndWait();
        }
    }
    void setTextField(String nom,String desc,categorie cat,int nbr,float prix,String image,int id){
        //this.cat.setValue(cat);
        this.image.setText(image);
        this.nom.setText(nom);
        this.prix.setText(""+prix);
        this.nbr.setText(""+nbr);
        this.desc.setText(desc);
        p.setId(id);
        
    }
    
}
