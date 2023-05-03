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
import java.sql.Date;
import java.time.LocalDate;
import static java.time.zone.ZoneRulesProvider.refresh;
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
import javafx.stage.Stage;
import services.CRUDaffectations;
import services.CRUDtechnicien;
import services.CRUDterrain;

public class AffectationsController implements Initializable {
    Affectations affectations = new Affectations();
    CRUDaffectations crudAffectations = new CRUDaffectations();
    Technicien technicien = new Technicien();
    Terrain terrain = new Terrain();

    @FXML
    private ComboBox<Technicien> fatechnicien = new ComboBox<>();
    @FXML
    private ComboBox<Terrain> faterrain = new ComboBox<>();
    @FXML
    private DatePicker fadatedebut;
    @FXML
    private DatePicker fadatefin;
    @FXML
    private Button fasave;
    @FXML
    private Button AURAHome;
    @FXML
    private Button AURABack;
    @FXML
    private Button ListeA;
    @FXML
    private Button ftupdate;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadTechniciens();
        loadTerrains();
    }

    private void loadTechniciens() {
        CRUDtechnicien ct = new CRUDtechnicien();
        List<Technicien> techniciens = ct.afficherTechnicien();
          
        for(Technicien technicien : techniciens){
            fatechnicien.getItems().add(technicien);
        }
    }

    private void loadTerrains() {
        CRUDterrain ct = new CRUDterrain();
        List<Terrain> terrains = ct.afficherTerrain();
          
        for(Terrain terrain : terrains){
        faterrain.getItems().add(terrain);
    }
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    @FXML
    private void saveAffectations(ActionEvent event) {
    refresh();
    Technicien technicien = fatechnicien.getValue();
    Terrain terrain = faterrain.getValue();

    LocalDate date_debut = fadatedebut.getValue();
    LocalDate date_fin = fadatefin.getValue();
    
    

    Affectations af = new Affectations();
    af.setTechnicien(technicien);
    af.setTerrain(terrain);
    af.setDateDebut(Date.valueOf(date_debut));
    af.setDateFin(Date.valueOf(date_fin));

    CRUDaffectations a = new CRUDaffectations();
    a.ajouterAffectations(affectations);
    
    FXMLLoader loader = new FXMLLoader(getClass().getResource("AfficherAffectations.fxml"));

    a.afficherAffectations();
    
    fatechnicien.setValue(null);
    faterrain.setValue(null);
    fadatedebut.setValue(null);
    fadatefin.setValue(null);
    
    Alert alert = new Alert(Alert.AlertType.INFORMATION);
    alert.setTitle("Succès");
    alert.setHeaderText("Affectation ajoutée");
    alert.setContentText("L'affectation a été ajoutée avec succès");
    alert.showAndWait();
}
    private boolean checkComboBoxInput(ComboBox comboBox) {
    if(comboBox.getValue() == null) {
        // La valeur n'a pas été sélectionnée, afficher un message d'erreur
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur de saisie");
        alert.setHeaderText(null);
        alert.setContentText("Veuillez sélectionner une valeur dans le " + comboBox.getId() + ".");
        alert.showAndWait();
        return false;
    } else {
        // La valeur a été sélectionnée
        return true;
    }
}

    @FXML
    private void AURAHome(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AURAMain.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void AURABAck(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AURAMain.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle("AURA");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void ListeA(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ListeAffectations.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Liste Affectations");
        stage.show();
    }

    @FXML
    private void updateAffectation(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("UpdateAffectations.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle("AURA");
        stage.setScene(scene);
        stage.show(); 
    }
}
