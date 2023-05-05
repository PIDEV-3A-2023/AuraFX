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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import services.UserService;
import utils.Sessions;

/**
 * FXML Controller class
 *
 * @author MSI
 */
public class BaseFrontController implements Initializable {

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
    private HBox GestionTechnicienButon;
    @FXML
    private HBox GestionAffectationButon;
    private HBox GestionUserButon;
    @FXML
    private HBox GestionPostButon;
    @FXML
    private StackPane AnchorePaneLayout;
      public static BaseFrontController baseController;
    @FXML
    private HBox GestionFactureButon;
    @FXML
    private HBox profil;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         UserService us = new UserService();
//        currentUser = us.AfficherU();
//       GUI.BaseController.currentUser = currentUser;
        ConnectedUserNameLabel.setText(Sessions.getLoggedInUser().getNom());
          baseController = this;
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("produitfront.fxml"));
            removeSelectedStyle();
            TitreLabel.setText("Gestion Produit");
            gestionProduitButton.getStyleClass().add("btn-selected");
            AnchorePaneLayout.getChildren().removeAll();
            AnchorePaneLayout.getChildren().setAll(root);
        } catch (IOException ex) {
            Logger.getLogger(BaseController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    

     private void removeSelectedStyle() {
        
        gestionProduitButton.getStyleClass().clear();
        GestionCategorieButon.getStyleClass().clear();
        GestionTechnicienButon.getStyleClass().clear();
       GestionAffectationButon.getStyleClass().clear();
        profil.getStyleClass().clear();
        GestionPostButon.getStyleClass().clear();
       

        
        gestionProduitButton.getStyleClass().add("btns");
        GestionCategorieButon.getStyleClass().add("btns");
         GestionTechnicienButon.getStyleClass().add("btns");
        GestionAffectationButon.getStyleClass().add("btns");
       profil.getStyleClass().add("btns");
        GestionPostButon.getStyleClass().add("btns");
     

    }
    @FXML
    private void LoadGestionProduit(MouseEvent event) {
         Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("produitfront.fxml"));
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
    }

    @FXML
    private void LoadGestionTechnicien(MouseEvent event) {
                  Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("technicien.fxml"));
            removeSelectedStyle();
            TitreLabel.setText("Gestion Technicien ");
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
            root = FXMLLoader.load(getClass().getResource("FrontAffectations.fxml"));
            removeSelectedStyle();
            TitreLabel.setText("Gestion Affectations ");
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
            root = FXMLLoader.load(getClass().getResource("AcceuilClient.fxml"));
            removeSelectedStyle();
            TitreLabel.setText("Profil ");
            profil.getStyleClass().add("btn-selected");
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
            root = FXMLLoader.load(getClass().getResource("postfront.fxml"));
            removeSelectedStyle();
            TitreLabel.setText("Posts ");
            GestionPostButon.getStyleClass().add("btn-selected");
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
            root = FXMLLoader.load(getClass().getResource("facturefront.fxml"));
            removeSelectedStyle();
            TitreLabel.setText("Gestion Facture ");
            GestionFactureButon.getStyleClass().add("btn-selected");
            AnchorePaneLayout.getChildren().removeAll();
            AnchorePaneLayout.getChildren().setAll(root);
        } catch (IOException ex) {
            Logger.getLogger(BaseController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void back(ActionEvent event) throws IOException {
         FXMLLoader loader = new FXMLLoader(getClass().getResource("login.fxml"));
                    Parent root = loader.load();
                    Scene scene = new Scene(root);
                    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    stage.setTitle("Login");
                    stage.setScene(scene);
                    stage.show(); 
    }
    
}
