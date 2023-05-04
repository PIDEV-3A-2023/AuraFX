/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import services.UserService;
import utils.Sessions;

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
    public static BaseController baseController;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
          UserService us = new UserService();
//        currentUser = us.AfficherU();
        ConnectedUserNameLabel.setText(Sessions.getLoggedInUser().getNom());
        baseController = this;
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("afficherproduit.fxml"));
            removeSelectedStyle();
            TitreLabel.setText("Gestion Evenement");
            gestionProduitButton.getStyleClass().add("btn-selected");
            AnchorePaneLayout.getChildren().removeAll();
            AnchorePaneLayout.getChildren().setAll(root);
        } catch (IOException ex) {
            Logger.getLogger(BaseController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    
     private void removeSelectedStyle() {
      //  gestionProduitButton.getStyleClass().clear();
        GestionUserButon.getStyleClass().clear();
        gestionProduitButton.getStyleClass().clear();
        GestionCategorieButon.getStyleClass().clear();
       // GestionCategorieButon.getStyleClass().clear();
      //  GestionPostButon.getStyleClass().clear();
      //  GestionCommentButon.getStyleClass().clear();
        AnchorePaneLayout.getStyleClass().clear();
        GestionFactureButon.getStyleClass().clear();
        GestionTechnicienButon.getStyleClass().clear();
        GestionAffectationButon.getStyleClass().clear();
        GestionPostButon.getStyleClass().clear();
        GestionCommentButon.getStyleClass().clear();

        gestionProduitButton.getStyleClass().add("btns");
        GestionUserButon.getStyleClass().add("btns");
        GestionCategorieButon.getStyleClass().add("btns");
    //    GestionTicketButon.getStyleClass().add("btns");
        gestionProduitButton.getStyleClass().add("btns");
        GestionCategorieButon.getStyleClass().add("btns");
     //   GestionCommandeButon.getStyleClass().add("btns");
        AnchorePaneLayout.getStyleClass().add("btns");
        GestionFactureButon.getStyleClass().add("btns");
        GestionTechnicienButon.getStyleClass().add("btns");
        GestionAffectationButon.getStyleClass().add("btns");
        GestionPostButon.getStyleClass().add("btns");
        GestionCommentButon.getStyleClass().add("btns");

    }

    @FXML
    private void LoadGestionProduit(MouseEvent event) {
       Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("afficherproduit.fxml"));
            removeSelectedStyle();
            TitreLabel.setText("Gestion Produit ");
            gestionProduitButton.getStyleClass().add("btn-selected");
            AnchorePaneLayout.getChildren().removeAll();
            AnchorePaneLayout.getChildren().setAll(root);
        } catch (IOException ex) {
            Logger.getLogger(BaseController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void LoadGestionCategorie(MouseEvent event) {
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("affichercat.fxml"));
            removeSelectedStyle();
            TitreLabel.setText("Gestion Catégorie ");
            gestionProduitButton.getStyleClass().add("btn-selected");
            AnchorePaneLayout.getChildren().removeAll();
            AnchorePaneLayout.getChildren().setAll(root);
        } catch (IOException ex) {
            Logger.getLogger(BaseController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }



    @FXML
    private void LoadGestionPost(MouseEvent event) {
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("PostBack.fxml"));
            removeSelectedStyle();
            TitreLabel.setText("Gestion Post");
            GestionPostButon.getStyleClass().add("btn-selected");
            AnchorePaneLayout.getChildren().removeAll();
            AnchorePaneLayout.getChildren().setAll(root);
        } catch (IOException ex) {
            Logger.getLogger(BaseController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void LoadGestionComment(MouseEvent event) {
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("ShowCommentBack.fxml"));
            removeSelectedStyle();
            TitreLabel.setText("Gestion Comment");
            GestionCommentButon.getStyleClass().add("btn-selected");
            AnchorePaneLayout.getChildren().removeAll();
            AnchorePaneLayout.getChildren().setAll(root);
        } catch (IOException ex) {
            Logger.getLogger(BaseController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    @FXML
    private void LoadGestionFacture(MouseEvent event) {
       Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("stat.fxml"));
            removeSelectedStyle();
            TitreLabel.setText("Gestion Facture");
            GestionFactureButon.getStyleClass().add("btn-selected");
            AnchorePaneLayout.getChildren().removeAll();
            AnchorePaneLayout.getChildren().setAll(root);
        } catch (IOException ex) {
            Logger.getLogger(BaseController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void LoadGestionTechnicien(MouseEvent event) {
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("technicien.fxml"));
            removeSelectedStyle();
            TitreLabel.setText("Gestion Technicien");
            GestionTechnicienButon.getStyleClass().add("btn-selected");
            AnchorePaneLayout.getChildren().removeAll();
            AnchorePaneLayout.getChildren().setAll(root);
        } catch (IOException ex) {
            Logger.getLogger(BaseController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void LoadGestionAffectation(MouseEvent event) {
         Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("affectations.fxml"));
            removeSelectedStyle();
            TitreLabel.setText("Gestion Affectation");
            GestionAffectationButon.getStyleClass().add("btn-selected");
            AnchorePaneLayout.getChildren().removeAll();
            AnchorePaneLayout.getChildren().setAll(root);
        } catch (IOException ex) {
            Logger.getLogger(BaseController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void LoadGestionUser(MouseEvent event) {
         Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("alluser.fxml"));
            removeSelectedStyle();
            TitreLabel.setText("Gestion User");
            GestionUserButon.getStyleClass().add("btn-selected");
            AnchorePaneLayout.getChildren().removeAll();
            AnchorePaneLayout.getChildren().setAll(root);
        } catch (IOException ex) {
            Logger.getLogger(BaseController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
