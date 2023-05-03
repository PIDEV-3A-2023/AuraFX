/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * FXML Controller class
 *
 * @author issamfekih.if
 */
package GUI;
import entity.Affectations;
import entity.Technicien;
import entity.Terrain;
import java.io.IOException;
import java.net.URL;
import java.util.List;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import services.CRUDaffectations;
import services.CRUDtechnicien;
import services.CRUDterrain;

public class UpdateAffectations implements Initializable {

    @FXML
    private ComboBox<Affectations> fatechnicien;
    @FXML
    private ComboBox<Affectations> faterrain;
    @FXML
    private DatePicker fadatedebut;
    @FXML
    private DatePicker fadatefin;
    private ComboBox<Affectations> faid;
    @FXML
    private Button ftupdate;
    @FXML
    private Button AURAHome;
    @FXML
    private Button AURABack;
    @FXML
    private Button ListeA;


    /**
     * Initializes the controller class.
     */
     @Override
    public void initialize(URL url, ResourceBundle rb) {
         CRUDaffectations a = new CRUDaffectations();
        List<Affectations> affectations = a.afficherAffectations();
        for(Affectations affectation : affectations){
            fatechnicien.getItems().add(affectation);
            faterrain.getItems().add(affectation);
            fadatedebut.getValue();
            fadatefin.getValue();
        }
    }
    
    
    
    
    
    
    
 
        
        
        
        
        
        
        
        


    @FXML
    private void AURAHome(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AURAMain.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle("AURA");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void AURABAck(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Affectations.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle("Ajouter Technicien");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void ListeA(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ListeTechnicien.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle("Ajouter Affectation");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void updateAffectationsU(ActionEvent event) throws IOException {

    }
}
