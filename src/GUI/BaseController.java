/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;

/**
 * FXML Controller class
 *
 * @author azerb
 */
public class BaseController implements Initializable {

    @FXML
    private Label ConnectedUserNameLabel;
    @FXML
    private Label TitreLabel;
    @FXML
    private ImageView logoImageview;
    @FXML
    private HBox gestionProduitButton;
    @FXML
    private HBox GestionCategorieButon;
    @FXML
    private HBox GestionPostButon;
    @FXML
    private HBox GestionCommentButon;
    @FXML
    private StackPane AnchorePaneLayout;
    @FXML
    private HBox GestionFactureButon;
    @FXML
    private HBox GestionTechnicienButon;
    @FXML
    private HBox GestionAffectationButon;
    @FXML
    private HBox GestionUserButon;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    


    @FXML
    private void LoadGestionProduit(MouseEvent event) {
        try {
            //navigation
            Parent loader = FXMLLoader.load(getClass().getResource("afficherproduit.fxml"));
            gestionProduitButton.getScene().setRoot(loader);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void LoadGestionCategorie(MouseEvent event) {
        try {
            //navigation
            Parent loader = FXMLLoader.load(getClass().getResource("affichercat.fxml"));
            GestionCategorieButon.getScene().setRoot(loader);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }



    @FXML
    private void LoadGestionPost(MouseEvent event) {
        try {
            //navigation
            Parent loader = FXMLLoader.load(getClass().getResource("postback.fxml"));
            GestionPostButon.getScene().setRoot(loader);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void LoadGestionComment(MouseEvent event) {
        try {
            //navigation
            Parent loader = FXMLLoader.load(getClass().getResource("showcommentback.fxml"));
            GestionCommentButon.getScene().setRoot(loader);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
       
    }

    @FXML
    private void LoadGestionFacture(MouseEvent event) {
        try {
            //navigation
            Parent loader = FXMLLoader.load(getClass().getResource("showcommentback.fxml"));
            GestionCommentButon.getScene().setRoot(loader);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void LoadGestionTechnicien(MouseEvent event) {
        try {
            //navigation
            Parent loader = FXMLLoader.load(getClass().getResource("affichertechnicien.fxml"));
            GestionTechnicienButon.getScene().setRoot(loader);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void LoadGestionAffectation(MouseEvent event) {
        try {
            //navigation
            Parent loader = FXMLLoader.load(getClass().getResource("affectations.fxml"));
            GestionAffectationButon.getScene().setRoot(loader);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void LoadGestionUser(MouseEvent event) {
        try {
            //navigation
            Parent loader = FXMLLoader.load(getClass().getResource("alluser.fxml"));
            GestionUserButon.getScene().setRoot(loader);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
<<<<<<< Updated upstream
    
=======
   
>>>>>>> Stashed changes
}