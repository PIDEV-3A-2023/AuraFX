/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import entity.Technicien;
import java.io.IOException;
import java.net.URL;
import static java.time.zone.ZoneRulesProvider.refresh;
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
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import services.CRUDtechnicien;

/**
 * FXML Controller class
 *
 * @author issamfekih.if
 */
public class AfficherTechnicienController implements Initializable {
    @FXML
    private TextField ftnomt;
    @FXML
    private TextField ftprenomt;
    @FXML
    private TextField fttelt;
    @FXML
    private TextField ftemailt;
    @FXML
    private TextField ftspecialitet;
    @FXML
    private TextField ftsalairet;
    @FXML
    private Button ftupdate;
    @FXML
    private Button AURAHome;
    @FXML
    private Button AURABack;
    @FXML
    private Button ListeA;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        CRUDtechnicien ct = new CRUDtechnicien();

        
    }

    public void setftnom(String message) {
        this.ftnomt.setText(message);
    }

    public void setftprenom(String message) {
        this.ftprenomt.setText(message);
    }

    public void setfttel(String message) {
        this.fttelt.setText(message);
    }

    public void setftemail(String message) {
        this.ftemailt.setText(message);
    }

    public void setftspecialite(String message) {
        this.ftspecialitet.setText(message);
    }
    public void setftsalaire(String message) {
        this.ftsalairet.setText(message);
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
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Technicien.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle("Ajouter Technicien");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void updateTechnicien(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("UpdateTechnicien.fxml"));
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
    private void ListeA(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ListeTechnicien.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();  
    }
}
