/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;




import Entities.Solde;
import Entities.Terrain;
import Services.CrudSolde;
import Services.crudTerrain;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Cyrine
 */
public class ListSoldesController implements Initializable {

    @FXML
    private VBox mainVBox;
    @FXML
    private VBox CommentVBox;
    @FXML
    private Text topText;
    @FXML
    private Button btnRetour;

    public CrudSolde crudSolde = new CrudSolde();
    /**
     * Initializes the controller class.
     */
    @Override
   
    
    public void initialize(URL url, ResourceBundle rb) {
        
List<Solde> listSoldes = crudSolde.displaySoldes(ListTerrainController.terrainId); 
           
          
   if (!listSoldes.isEmpty()) {
            for (Solde solde : listSoldes) {

                CommentVBox.getChildren().add(makeSoldeModel(solde));
            }
        } else { 
            StackPane stackPane = new StackPane();
            stackPane.setAlignment(Pos.CENTER);
            stackPane.setPrefHeight(200);
            stackPane.getChildren().add(new Text("Aucune donnée"));
            CommentVBox.getChildren().add(stackPane);
        
    }    
    }
  
 public Parent makeSoldeModel(Solde solde) {
        Parent parent = null;

        try {
            parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("ModelSolde.fxml")));

            HBox innerContainer = ((HBox) ((AnchorPane) ((AnchorPane) parent).getChildren().get(0)).getChildren().get(0));
            ((Text) innerContainer.lookup("#txtMontant")).setText(" Montant :" + solde.getMontant());
            ((Text) innerContainer.lookup("#txtDate")).setText("Date : " + solde.getDate());
              ((Text) innerContainer.lookup("#txtIdTerrain")).setText("Id_Terrain : " + solde.getTerrain().getId_terrain());
            ((Pane) innerContainer.lookup("#commentPane")).setVisible(false);
                        ((Button) innerContainer.lookup("#btnUpdate")).setOnAction((event) -> {
               // cs.removeLike(pub.getIdPost());
                           ((Pane) innerContainer.lookup("#commentPane")).setVisible(true);

          });
            ((Button) innerContainer.lookup("#btnConfirm")).setOnAction((e)->{
                double montant=0;
                montant= Double.parseDouble(((TextField) innerContainer.lookup("#inputMontant")).getText());
               
                 crudSolde.modiferSolde(montant,solde.getId());
            }
            );

            ((Button) innerContainer.lookup("#btnDelete")).setOnAction((event) -> {
                
                crudSolde.supprimerSolde( solde.getId()); 
            });
           
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return parent;
    }
  


      @FXML
    private void retour(ActionEvent event) {
                 try {
                     Parent root = FXMLLoader.load(getClass().getResource("ListTerrain.fxml"));
                     Scene scene = new Scene(root);
                     Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                        //stage.setFullScreen(true);

                    stage.setScene(scene);
                    stage.show();
                } catch (IOException ex) {
                    System.out.println(ex.getMessage());
      
    }
    }}