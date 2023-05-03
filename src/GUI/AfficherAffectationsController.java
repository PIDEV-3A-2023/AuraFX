/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package GUI;

import entity.Affectations;
import entity.Technicien;
import entity.Terrain;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import static java.time.zone.ZoneRulesProvider.refresh;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListCell;
import javafx.scene.control.TableCell;
import javafx.stage.Stage;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.StringConverter;
import jdk.nashorn.internal.runtime.Property;
import services.CRUDaffectations;
import services.CRUDtechnicien;
import services.CRUDterrain;

/**
 * FXML Controller class
 *
 * @author issamfekih.if
 */
public class AfficherAffectationsController implements Initializable {
    @FXML
    private ComboBox<Technicien> fatechnicien;
    @FXML
    private ComboBox<Terrain> faterrain;
    @FXML
    private DatePicker fadatedebut;
    @FXML
    private DatePicker fadatefin;
    @FXML
    private Button AURAHome;
    @FXML
    private Button AURABack;
    @FXML
    private Button ListeA;

    CRUDaffectations a = new CRUDaffectations();
    Affectations affectations = new Affectations();
    @FXML
    private Button ftupdate;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {  
        refresh();

    }    
    public void setfatechnicien(Technicien technicien) {
        this.fatechnicien.setValue(technicien);
    }
    public void setfaterrain(Terrain terrain) {
        this.faterrain.setValue(terrain);
    }
    public void setfadatedebut(LocalDate date) {
        this.fadatedebut.setValue(date);
    }
    public void setfadatefin(LocalDate date) {
        this.fadatefin.setValue(date);
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
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Affectations.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    private void ListeA(ActionEvent event) throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ListeAffectations.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();   
    }
    void refresh() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @FXML
    private void updateTechnicien(ActionEvent event) {
    }
    
}
