/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import entities.produit;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 *
 * @author azerb
 */
public class SingleproduitController implements Initializable {
 produit p =new produit();
    @FXML
    private Label titre;
    @FXML
    private Label categorie;
    @FXML
    private ImageView image;
    @FXML
    private Label prix;
    @FXML
    private Label description;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        // TODO
    }  
    public void setproduit(produit pc){
        p=pc;
       display(); 
        
    }
    private void display(){
        titre.setText(p.getNom());
        categorie.setText(p.getCategorie().getNom());
        prix.setText(""+p.getPrix());
        description.setText(p.getDesc());
        if (p.getImage() != null) {
            image.setImage(new Image(new File(p.getImage()).toURI().toString()));
        }
    }
    
    
}
