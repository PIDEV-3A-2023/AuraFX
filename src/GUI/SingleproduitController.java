/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import entities.produit;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import services.panier;

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
    @FXML
    private Button back;
    
    Map<produit,Integer> pan=new HashMap<>();
    @FXML
    private Button panieraff;
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

    @FXML
    private void ajouterpanier(ActionEvent event) {
        
                            try {
            //navigation
            FXMLLoader loader = new FXMLLoader(getClass().getResource("panier.fxml"));
    Parent root = loader.load();
    
   // PanierController PanierController = loader.getController();
   panier.getInstance().ajouterProduit(p);
    //panier.getInstance().getProduitsQuantites().put(p, 2);
    //PanierController.panier.put(p, 1);
            panieraff.getScene().setRoot(root);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void back(ActionEvent event) {
        try {
            //navigation
            Parent loader = FXMLLoader.load(getClass().getResource("produitfront.fxml"));
            back.getScene().setRoot(loader);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void panier(ActionEvent event) {
        try {
            //navigation
            Parent loader = FXMLLoader.load(getClass().getResource("panier.fxml"));
            back.getScene().setRoot(loader);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    
}
