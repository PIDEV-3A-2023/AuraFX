/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Terrain;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import Services.crudTerrain ; 

/**
 * FXML Controller class
 *
 * @author Cyrine
 */
public class ADDTerrainController implements Initializable {

    @FXML
    private TextField txtSurface;
    @FXML
    private TextField txtAdresse;
    @FXML
    private TextField txtPotentiel;
    @FXML
    private TextField txtMembre;
    @FXML
    private Button btngetback;
    @FXML
    private Button btnAjouter;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void loadMenu(ActionEvent event) {
                try {
                    // charger le contenu d'un fichier FXML
            Parent root = FXMLLoader.load(getClass().getResource("ListTerrain.fxml"));
            //nouvelle instance de la classe scène est créé avec root 
            Scene scene = new Scene(root);
            //event.getSource() : retourne l'objet qui a déclenché l'événement 
            //cast en node 
            // .getScene() retourne scène parente de l'obj node 
            //getwindow() : fenetre parente 
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            //definir scène à afficher 
            stage.setScene(scene);
            //la fnetre est affiché 
            stage.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

   
    @FXML
    private void AddPub(ActionEvent event) {
         //La méthode equals() est utilisée pour comparer la chaîne de caractères retournée par getText() 
        // trim() : supp les espaces au debut et fin de la chaine 
               // if ("".equals(txtAdresse.getText().trim()) || "".equals(txtPotentiel.getText().trim()) || "".equals(txtSurface.getText().trim())  ) {
                 if   ("".equals(txtAdresse.getText().trim()) || "".equals(txtPotentiel.getText().trim()) || "".equals(txtSurface.getText().trim()) || !isNumeric(txtPotentiel.getText().trim()) || !isPositiveNumeric(txtSurface.getText().trim())) {
            //crée une boîte de dialogue d'erreur
                     
                     Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");  
            alert.setHeaderText("Error");
            alert.setContentText("You have to fill required fields!"); 

            alert.showAndWait();
        } else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation Dialog");
            alert.setHeaderText("Confirm adding a publication");
            alert.setContentText("A new Terrain will be published are you ok with this?");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                // ... user chose OK
                crudTerrain ps = new crudTerrain();
                
        Terrain t = new Terrain(txtAdresse.getText(),Double.parseDouble(txtSurface.getText()),Double.parseDouble(txtPotentiel.getText()),Integer.parseInt(txtMembre.getText()));
        ps.ajouterTerrain(t);
              
               // charger scene qui aff liste des terrains 
               loadMenu(event);
            }
        }

    }
    private boolean isNumeric(String str) {
    try {
        Double.parseDouble(str);
        return true;
    } catch (NumberFormatException e) {
        return false;
    }
}

private boolean isPositiveNumeric(String str) {
    try {
        double value = Double.parseDouble(str);
        return value > 0;
    } catch (NumberFormatException e) {
        return false;
    }
}
    }
    
    

